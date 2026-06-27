package net.rim.device.api.io;

import java.io.InputStream;
import net.rim.vm.Array;

public final class BufferedInputStream extends InputStream {
   private InputStream _in;
   private byte[] _buffer;
   private int _bufferPos;
   private int _currentMarkPos = -1;
   private int _maxMarkPos;
   private boolean _closed;
   private static final int BUFFER_SIZE;

   public BufferedInputStream(InputStream var1) {
      this(var1, 2048);
   }

   public BufferedInputStream(InputStream var1, int var2) {
      this._in = var1;
      if (var2 > 0 && var1 != null) {
         this._buffer = new byte[var2];
         this._bufferPos = this._buffer.length;
      } else {
         throw new Object();
      }
   }

   @Override
   public final synchronized void reset() {
      if (!this._closed && this._currentMarkPos >= 0) {
         this._bufferPos = this._currentMarkPos;
      } else {
         throw new Object();
      }
   }

   @Override
   public final boolean markSupported() {
      return true;
   }

   @Override
   public final void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final synchronized int available() {
      if (this._closed) {
         throw new Object();
      } else {
         return this._buffer.length - this._bufferPos;
      }
   }

   @Override
   public final synchronized void mark(int var1) {
      this._maxMarkPos = var1;
      this._currentMarkPos = this._bufferPos;
   }

   @Override
   public final synchronized int read() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final boolean fillBuffer() {
      if (this._currentMarkPos < 0) {
         this._bufferPos = 0;
      } else if (this._currentMarkPos > 0) {
         int var1 = this._bufferPos - this._currentMarkPos;
         System.arraycopy(this._buffer, this._currentMarkPos, this._buffer, 0, var1);
         this._currentMarkPos = var1;
         this._currentMarkPos = 0;
      } else if (this._buffer.length >= this._maxMarkPos) {
         this._currentMarkPos = -1;
         this._bufferPos = 0;
      } else {
         int var3 = this._buffer.length + 2048;
         Array.resize(this._buffer, var3);
      }

      int var4 = this._buffer.length - this._bufferPos;
      int var2 = this._in.read(this._buffer, this._bufferPos, var4);
      if (var2 > 0 && var2 != var4) {
         Array.resize(this._buffer, this._bufferPos + var2);
      }

      return var2 > 0;
   }

   @Override
   public final synchronized int read(byte[] var1, int var2, int var3) {
      if (this._closed) {
         throw new Object();
      }

      if (var1 == null || var2 < 0 || var3 < 0 || var1.length - var3 < var2) {
         throw new Object();
      }

      if (var3 == 0) {
         return 0;
      }

      int var4 = 0;

      while (var3 > 0) {
         if (this._buffer.length == this._bufferPos) {
            if (var3 >= this._buffer.length && this._currentMarkPos < 0) {
               int var6 = this._in.read(var1, var2, var3);
               if (var6 >= 0) {
                  return var6 + var4;
               }

               if (var4 > 0) {
                  return var4;
               }

               return -1;
            }

            if (!this.fillBuffer()) {
               break;
            }
         }

         int var5 = Math.min(this._buffer.length - this._bufferPos, var3);
         System.arraycopy(this._buffer, this._bufferPos, var1, var2, var5);
         this._bufferPos += var5;
         var4 += var5;
         var3 -= var5;
         var2 += var5;
      }

      return var4 > 0 ? var4 : -1;
   }
}
