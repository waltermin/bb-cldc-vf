package net.rim.device.internal.util;

import java.io.InputStream;
import java.io.OutputStream;
import net.rim.vm.Array;

public class RingBuffer {
   private int _startIndex;
   private int _endIndex;
   private byte[] _buffer;
   private int _dataLength;
   private boolean _isOpen;
   private int _maxBytesToReadEntirely;
   private static final int BUFFER_SECTION_SIZE;

   public RingBuffer(int var1) {
      this._startIndex = 0;
      this._endIndex = 0;
      this._dataLength = 0;
      this._isOpen = true;
      this._maxBytesToReadEntirely = Integer.MAX_VALUE;
      this._buffer = new byte[var1];
      Array.setSectionSize(this._buffer, 2048);
   }

   public RingBuffer(byte[] var1) {
   }

   public synchronized void clear() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public synchronized void close() {
      this._isOpen = false;
      super.notify();
   }

   public int getBufferSize() {
      return this._buffer.length;
   }

   public int getDataSize() {
      return this._dataLength;
   }

   int getEndIndex() {
      return this._endIndex;
   }

   int getStartIndex() {
      return this._startIndex;
   }

   public boolean isEmpty() {
      return this._dataLength == 0;
   }

   public boolean isFull() {
      return this._dataLength == this._buffer.length;
   }

   public synchronized int read(byte[] var1, int var2, int var3) {
      int var4 = Math.min(var3, this._dataLength);
      int var5 = Math.min(var4, this._buffer.length - this._startIndex);
      System.arraycopy(this._buffer, this._startIndex, var1, var2, var5);
      this._startIndex += var5;
      if (var5 < var4) {
         var2 += var5;
         this._startIndex = 0;
         var5 = var4 - var5;
         System.arraycopy(this._buffer, this._startIndex, var1, var2, var5);
         this._startIndex += var5;
      }

      if (this._startIndex == this._buffer.length) {
         this._startIndex = 0;
         if (this._endIndex == this._buffer.length) {
            this._endIndex = 0;
         }
      }

      this._dataLength -= var4;
      return var4;
   }

   public synchronized int read(OutputStream var1, int var2) {
      int var3 = Math.min(this._dataLength, var2);
      int var4 = 0;

      while (var3 > 0) {
         int var5 = Math.min(var3, this._buffer.length - this._startIndex);
         var1.write(this._buffer, this._startIndex, var5);
         this._startIndex += var5;
         var4 += var5;
         this._dataLength -= var5;
         var3 -= var5;
         if (this._startIndex == this._buffer.length) {
            this._startIndex = 0;
            if (this._endIndex == this._buffer.length) {
               this._endIndex = 0;
            }
         }
      }

      return var4;
   }

   public synchronized int read(RingBuffer$UnassertiveOutputStream var1, int var2) {
      int var3 = Math.min(this._dataLength, var2);
      int var4 = 0;
      if (var3 == 0) {
         var1.write(this._buffer, 0, 0);
      }

      while (var3 > 0) {
         int var5 = Math.min(var3, this._buffer.length - this._startIndex);
         int var6 = var1.write(this._buffer, this._startIndex, var5);
         if (var6 == 0) {
            break;
         }

         this._startIndex += var6;
         var4 += var6;
         this._dataLength -= var6;
         var3 -= var6;
         if (this._startIndex == this._buffer.length) {
            this._startIndex = 0;
            if (this._endIndex == this._buffer.length) {
               this._endIndex = 0;
            }
         }
      }

      return var4;
   }

   public synchronized int read(OutputStream var1) {
      return this.read(var1, Integer.MAX_VALUE);
   }

   public synchronized void readEntirely(OutputStream var1) {
      this.readEntirely(var1, 0);
   }

   public synchronized void readEntirely(OutputStream var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized void setReadEntirelySizeLimit(int var1) {
      this._maxBytesToReadEntirely = var1;
      super.notify();
   }

   public synchronized int write(byte[] var1, int var2, int var3) {
      int var4 = Math.min(var3, this._buffer.length - this._dataLength);
      int var5 = Math.min(var4, this._buffer.length - this._endIndex);
      System.arraycopy(var1, var2, this._buffer, this._endIndex, var5);
      this._endIndex += var5;
      if (var5 < var4) {
         var2 += var5;
         this._endIndex = 0;
         var5 = var4 - var5;
         System.arraycopy(var1, var2, this._buffer, this._endIndex, var5);
         this._endIndex += var5;
      }

      this._dataLength += var4;
      super.notify();
      return var4;
   }

   public synchronized int write(InputStream var1) {
      int var3 = 0;
      int var4 = 0;

      while (this._dataLength < this._buffer.length) {
         if (this._endIndex == this._buffer.length) {
            this._endIndex = 0;
         }

         int var2 = Math.min(this._buffer.length - this._endIndex, this._buffer.length - this._dataLength);
         var4 = var1.read(this._buffer, this._endIndex, var2);
         if (var4 <= 0) {
            break;
         }

         var3 += var4;
         this._dataLength += var4;
         this._endIndex += var4;
      }

      if (var3 == 0 && var4 < 0) {
         var3 = -1;
      }

      if (this._endIndex == 0 && this._dataLength != 0) {
         this._endIndex = this._buffer.length;
      }

      super.notify();
      return var3;
   }
}
