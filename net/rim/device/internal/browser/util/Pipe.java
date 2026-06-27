package net.rim.device.internal.browser.util;

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

   public Pipe(byte[] var1, int var2) {
      this(var1, var2, !Memory.isPlaintext(var1));
   }

   public Pipe(byte[] var1, int var2, boolean var3) {
   }

   public Pipe(byte[][][] var1) {
      this(var1, true);
   }

   public Pipe(byte[][][] var1, boolean var2) {
   }

   public final synchronized void write(byte[] var1, int var2, int var3, int var4) {
      if (!this._writeClosed && var3 != 0) {
         if (var2 != 0 || var3 != var1.length) {
            var1 = Arrays.copy(var1, var2, var3);
         }

         if (var4 >= this._window.length) {
            if (var4 != this._window.length) {
               this._notifyOnWrite = false;
            }

            Array.resize(this._window, var4 + 1);
         }

         this._window[var4] = (byte[][])var1;
         this._totalLength += var1.length;
         if (!this._notifyOnWrite) {
            this._notifyOnWrite = true;

            for (int var5 = 0; var5 < var4; var5++) {
               if (this._window[var5] == null) {
                  this._notifyOnWrite = false;
                  break;
               }
            }
         }

         if (this._notifyOnWrite && this._window[0] != null && this._window[0].length > 0) {
            super.notify();
         }
      }
   }

   public final synchronized int read(PipeContext var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized int removeSections(int[] var1, int[] var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized void removeSection(int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized int read(byte[] var1, int var2, int var3, PipeContext var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized long skip(PipeContext var1, long var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int fastRead(PipeContext var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized int readCompressedInt(PipeContext var1) {
      if (var1._readClosed) {
         throw new Object();
      }

      int var2 = 0;
      int var3 = 0;

      do {
         var2 = this.read(var1);
         if (var2 == -1) {
            throw new Object();
         }

         var3 = var3 << 7 | var2 & 127;
      } while ((var2 & 128) != 0);

      return var3;
   }

   public final synchronized boolean skipInlineString(PipeContext var1) {
      boolean var2 = false;
      int var3 = 0;

      while (true) {
         var3 = this.read(var1);
         switch (var3) {
            case -1:
               throw new Object();
            case 13:
               var2 = true;
            default:
               if (var3 == 0) {
                  return var2;
               }
         }
      }
   }

   public final synchronized String readInlineString(PipeContext var1, String var2) {
      if (var1._readClosed) {
         throw new Object();
      }

      int var3 = var1._currentPacket;
      int var4 = var1._currentReadPos;
      boolean var5 = this.skipInlineString(var1);
      if (!var5 && var1._currentPacket == var3) {
         return new String((byte[])this._window[var3], var4, var1._currentReadPos - var4 - 1, var2);
      }

      int var6 = 0;

      for (int var7 = var3; var7 <= var1._currentPacket; var7++) {
         var6 += this._window[var7].length;
      }

      var6 -= var4;
      var6 -= this._window[var1._currentPacket].length - var1._currentReadPos;
      byte[] var15 = new byte[var6];
      var1._currentPacket = var3;
      var1._currentReadPos = var4;
      int var8 = this.read(var15, 0, var6, var1);
      if (var8 != var6) {
         throw new Object();
      }

      var6--;
      boolean var9 = false;
      int var10 = 0;

      for (int var11 = 0; var11 < var6; var11++) {
         if (var15[var11] == 10 && var9) {
            var9 = false;
         } else {
            if (var15[var11] == 13) {
               var9 = true;
               var15[var11] = 10;
            } else {
               var9 = false;
            }

            var15[var10++] = var15[var11];
         }
      }

      return new String(var15, 0, var10, var2);
   }

   public final synchronized byte[] readPacket(int var1) {
      if (var1 < 0) {
         return null;
      } else {
         return (byte[])(var1 >= this._window.length ? null : this._window[var1]);
      }
   }

   public final synchronized void closeWrite() {
      this._writeClosed = true;
      super.notify();
   }

   public final synchronized void closeRead(PipeContext var1) {
      var1._readClosed = true;
      super.notify();
   }

   final synchronized boolean isPacketIncluded(int var1) {
      if (var1 < 0) {
         return true;
      } else if (var1 >= this._window.length) {
         return false;
      } else {
         return this._window[var1] == null ? false : var1 != 0 || this._window[0].length != 0;
      }
   }

   public final synchronized int available(PipeContext var1) {
      if (var1._availableBytes != Integer.MAX_VALUE) {
         return var1._availableBytes;
      }

      int var2 = 0;

      for (int var3 = var1._currentPacket; var3 < this._window.length; var3++) {
         if (this._window[var3] != null) {
            var2 += this._window[var3].length;
         }
      }

      var2 -= var1._currentReadPos;
      if (var2 > 0) {
         return var2;
      } else {
         return this._writeClosed ? -1 : 0;
      }
   }

   public final synchronized void waitUntilClosed() {
      throw new RuntimeException("cod2jar: exception table");
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
         byte[] var1 = new byte[this._totalLength];
         int var2 = 0;

         for (int var3 = 0; var3 < this._window.length; var3++) {
            if (this._window[var3] != null) {
               System.arraycopy(this._window[var3], 0, var1, var2, this._window[var3].length);
               var2 += this._window[var3].length;
            }
         }

         return var1;
      } else {
         return (byte[])this._window[0];
      }
   }

   public final synchronized byte[][][] toSegmentedArray() {
      return this._window;
   }

   public final synchronized void setCacheStart(int var1, int var2) {
      if (this._window[var1] != null) {
         this._totalLength -= var2;
         int var3 = this._window[var1].length - var2;
         if (var3 >= 0) {
            System.arraycopy(this._window[var1], var2, this._window[var1], 0, var3);
            Array.resize(this._window[var1], var3);
         }

         int var4 = 0;

         for (int var5 = var1; var5 < this._window.length; var5++) {
            this._window[var4] = this._window[var5];
            var4++;
         }

         while (var4 < this._window.length) {
            this._window[var4] = null;
            var4++;
         }
      }
   }

   public final int getEstimatedSize() {
      return Math.max(this._estimatedSize, this._totalLength);
   }

   public final void setEstimatedSize(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final synchronized int readByteArray(PipePtr var1, int var2, PipeContext var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final synchronized PipeInputStream getInputStream() {
      return (PipeInputStream)(this._writeClosed ? new Object(this) : new Object(this));
   }

   public final synchronized PipeInputStream getInputStream(int var1, int var2, int var3) {
      return (PipeInputStream)(this._writeClosed ? new Object(this, var1, var2, var3) : new Object(this, var1, var2, var3));
   }

   public final PipeOutputStream getOutputStream() {
      return (PipeOutputStream)(new Object(this));
   }

   public final void setTimeout(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final synchronized void kickReadTimer() {
      this._readKicked = true;
      super.notify();
   }
}
