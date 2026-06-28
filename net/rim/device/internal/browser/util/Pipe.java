package net.rim.device.internal.browser.util;

import java.io.EOFException;
import net.rim.device.api.io.IOCancelledException;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Persistable;
import net.rim.vm.Array;
import net.rim.vm.Memory;

public final class Pipe implements Persistable {
   private boolean _writeClosed;
   private int _totalLength;
   private int _estimatedSize;
   private int _timeout;
   private boolean _readKicked;
   private boolean _notifyOnWrite;
   private byte[][][] _window;
   private static final int WAIT_START;
   private static final int WAIT_FINISH;

   public Pipe() {
   }

   public Pipe(byte[] data, int length) {
      this(data, length, !Memory.isPlaintext(data));
   }

   public Pipe(byte[] data, int length, boolean groupData) {
   }

   public Pipe(byte[][][] data) {
      this(data, true);
   }

   public Pipe(byte[][][] data, boolean groupData) {
   }

   public final synchronized void write(byte[] bytes, int off, int length, int packetNo) {
      if (!this._writeClosed && length != 0) {
         if (off != 0 || length != bytes.length) {
            bytes = Arrays.copy(bytes, off, length);
         }

         if (packetNo >= this._window.length) {
            if (packetNo != this._window.length) {
               this._notifyOnWrite = false;
            }

            Array.resize(this._window, packetNo + 1);
         }

         this._window[packetNo] = (byte[][])bytes;
         this._totalLength += bytes.length;
         if (!this._notifyOnWrite) {
            this._notifyOnWrite = true;

            for (int i = 0; i < packetNo; i++) {
               if (this._window[i] == null) {
                  this._notifyOnWrite = false;
                  break;
               }
            }
         }

         if (this._notifyOnWrite && this._window[0] != null && this._window[0].length > 0) {
            this.notify();
         }
      }
   }

   public final synchronized int read(PipeContext position) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final synchronized int removeSections(int[] offsets, int[] lengths) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public final synchronized void removeSection(int offset, int length) {
      if (length > 0) {
         if (Memory.isObjectInGroup(this._window)) {
            this._window = (byte[][][])((byte[][])Memory.expandGroup(this._window));

            for (int i = 0; i < this._window.length; i++) {
               if (Memory.isObjectInGroup(this._window[i])) {
                  this._window[i] = (byte[][])((byte[])Memory.expandGroup(this._window[i]));
               }
            }
         }

         int packetsSoFar = 0;

         for (int i = 0; i < this._window.length; i++) {
            int packetLength = this._window[i].length;
            int packetOffset = offset - packetsSoFar;
            if (packetOffset == 0 && length >= packetLength) {
               this._totalLength -= packetLength;
               length -= packetLength;
               offset += packetLength;
               System.arraycopy(this._window, i + 1, this._window, i, this._window.length - i - 1);
               Array.resize(this._window, this._window.length - 1);
               i--;
            } else if (packetOffset < packetLength && packetOffset + length > packetLength) {
               if (i > 0 && packetOffset < 2048 && this._window[i - 1].length < 8192) {
                  int oldWindowSize = this._window[i - 1].length;
                  Array.resize(this._window[i - 1], oldWindowSize + packetOffset);
                  System.arraycopy(this._window[i], 0, this._window[i - 1], oldWindowSize, packetOffset);
                  System.arraycopy(this._window, i + 1, this._window, i, this._window.length - i - 1);
                  Array.resize(this._window, this._window.length - 1);
                  i--;
               } else {
                  Array.resize(this._window[i], packetOffset);
               }

               int truncationSize = packetLength - packetOffset;
               this._totalLength -= truncationSize;
               length -= truncationSize;
               offset += truncationSize;
            } else if (packetOffset < packetLength) {
               if (i > 0 && packetLength - length < 2048 && this._window[i - 1].length < 8192) {
                  int oldWindowSize = this._window[i - 1].length;
                  Array.resize(this._window[i - 1], oldWindowSize + (packetLength - length));
                  if (packetOffset > 0) {
                     System.arraycopy(this._window[i], 0, this._window[i - 1], oldWindowSize, packetOffset);
                     oldWindowSize += packetOffset;
                  }

                  int endOffset = packetOffset + length;
                  System.arraycopy(this._window[i], endOffset, this._window[i - 1], oldWindowSize, packetLength - endOffset);
                  System.arraycopy(this._window, i + 1, this._window, i, this._window.length - i - 1);
                  Array.resize(this._window, this._window.length - 1);
                  i--;
               } else {
                  int endOffset = packetOffset + length;
                  System.arraycopy(this._window[i], endOffset, this._window[i], packetOffset, packetLength - endOffset);
                  Array.resize(this._window[i], packetLength - length);
               }

               this._totalLength -= length;
               length = 0;
            }

            packetsSoFar += packetLength;
            if (length == 0) {
               return;
            }
         }
      }
   }

   public final synchronized int read(byte[] bytes, int offset, int length, PipeContext position) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final synchronized long skip(PipeContext position, long length) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final int fastRead(PipeContext position) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final synchronized int readCompressedInt(PipeContext position) {
      if (position._readClosed) {
         throw new IOCancelledException();
      }

      int i = 0;
      int result = 0;

      do {
         i = this.read(position);
         if (i == -1) {
            throw new EOFException();
         }

         result = result << 7 | i & 127;
      } while ((i & 128) != 0);

      return result;
   }

   public final synchronized boolean skipInlineString(PipeContext position) {
      boolean result = false;
      int i = 0;

      while (true) {
         i = this.read(position);
         switch (i) {
            case -1:
               throw new EOFException();
            case 13:
               result = true;
            default:
               if (i == 0) {
                  return result;
               }
         }
      }
   }

   public final synchronized String readInlineString(PipeContext position, String encoding) {
      if (position._readClosed) {
         throw new IOCancelledException();
      }

      int packet = position._currentPacket;
      int pos = position._currentReadPos;
      boolean crlfEncountered = this.skipInlineString(position);
      if (!crlfEncountered && position._currentPacket == packet) {
         return new String((byte[])this._window[packet], pos, position._currentReadPos - pos - 1, encoding);
      }

      int size = 0;

      for (int i = packet; i <= position._currentPacket; i++) {
         size += this._window[i].length;
      }

      size -= pos;
      size -= this._window[position._currentPacket].length - position._currentReadPos;
      byte[] tmpArray = new byte[size];
      position._currentPacket = packet;
      position._currentReadPos = pos;
      int readSize = this.read(tmpArray, 0, size, position);
      if (readSize != size) {
         throw new EOFException();
      }

      size--;
      boolean carriageReturn = false;
      int j = 0;

      for (int i = 0; i < size; i++) {
         if (tmpArray[i] == 10 && carriageReturn) {
            carriageReturn = false;
         } else {
            if (tmpArray[i] == 13) {
               carriageReturn = true;
               tmpArray[i] = 10;
            } else {
               carriageReturn = false;
            }

            tmpArray[j++] = tmpArray[i];
         }
      }

      return new String(tmpArray, 0, j, encoding);
   }

   public final synchronized byte[] readPacket(int packetNo) {
      if (packetNo < 0) {
         return null;
      } else {
         return (byte[])(packetNo >= this._window.length ? null : this._window[packetNo]);
      }
   }

   public final synchronized void closeWrite() {
      this._writeClosed = true;
      this.notify();
   }

   public final synchronized void closeRead(PipeContext position) {
      position._readClosed = true;
      this.notify();
   }

   final synchronized boolean isPacketIncluded(int packetNo) {
      if (packetNo < 0) {
         return true;
      } else if (packetNo >= this._window.length) {
         return false;
      } else {
         return this._window[packetNo] == null ? false : packetNo != 0 || this._window[0].length != 0;
      }
   }

   public final synchronized int available(PipeContext context) {
      if (context._availableBytes != Integer.MAX_VALUE) {
         return context._availableBytes;
      }

      int count = 0;

      for (int i = context._currentPacket; i < this._window.length; i++) {
         if (this._window[i] != null) {
            count += this._window[i].length;
         }
      }

      count -= context._currentReadPos;
      if (count > 0) {
         return count;
      } else {
         return this._writeClosed ? -1 : 0;
      }
   }

   public final synchronized void waitUntilClosed() {
      while (!this._writeClosed) {
         try {
            long timeBefore = System.currentTimeMillis();
            if (TimeLogger._loggingEnabled) {
               TimeLogger.getInstance().startTimer(13, (int)timeBefore);
            }

            EventLogger.logEvent(1907089860548946979L, 1114666867, 5);
            this.wait(this._timeout);
            EventLogger.logEvent(1907089860548946979L, 1114666854, 5);
            if (TimeLogger._loggingEnabled) {
               TimeLogger.getInstance().stopTimer(13, (int)timeBefore);
            }

            if (this._readKicked) {
               this._readKicked = false;
            } else if (System.currentTimeMillis() > timeBefore + this._timeout) {
               return;
            }
         } catch (InterruptedException var3) {
         }
      }
   }

   public final boolean isAborted() {
      return false;
   }

   public final int getLength() {
      return this._totalLength;
   }

   public final boolean isClosed() {
      return this._writeClosed;
   }

   public final synchronized byte[] toArray() {
      if (this._window.length > 1) {
         byte[] data = new byte[this._totalLength];
         int offset = 0;

         for (int i = 0; i < this._window.length; i++) {
            if (this._window[i] != null) {
               System.arraycopy(this._window[i], 0, data, offset, this._window[i].length);
               offset += this._window[i].length;
            }
         }

         return data;
      } else {
         return (byte[])this._window[0];
      }
   }

   public final synchronized byte[][][] toSegmentedArray() {
      return this._window;
   }

   public final synchronized void setCacheStart(int packet, int start) {
      if (this._window[packet] != null) {
         this._totalLength -= start;
         int length = this._window[packet].length - start;
         if (length >= 0) {
            System.arraycopy(this._window[packet], start, this._window[packet], 0, length);
            Array.resize(this._window[packet], length);
         }

         int i = 0;

         for (int j = packet; j < this._window.length; j++) {
            this._window[i] = this._window[j];
            i++;
         }

         while (i < this._window.length) {
            this._window[i] = null;
            i++;
         }
      }
   }

   public final int getEstimatedSize() {
      return Math.max(this._estimatedSize, this._totalLength);
   }

   public final void setEstimatedSize(int size) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final synchronized int readByteArray(PipePtr ptr, int length, PipeContext position) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final synchronized PipeInputStream getInputStream() {
      return this._writeClosed ? new FastPipeInputStream(this) : new PipeInputStream(this);
   }

   public final synchronized PipeInputStream getInputStream(int packet, int offset, int length) {
      return this._writeClosed ? new FastPipeInputStream(this, packet, offset, length) : new PipeInputStream(this, packet, offset, length);
   }

   public final PipeOutputStream getOutputStream() {
      return new PipeOutputStream(this);
   }

   public final void setTimeout(int timeout) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final synchronized void kickReadTimer() {
      this._readKicked = true;
      this.notify();
   }
}
