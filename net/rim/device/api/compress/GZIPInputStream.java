package net.rim.device.api.compress;

import java.io.InputStream;
import net.rim.device.internal.compress.Inflater;

public class GZIPInputStream extends InputStream {
   private InputStream _inputStream;
   private byte[] _currentChunk;
   private int _currentOffset;
   private byte[] _tempBuffer;
   private Inflater _inflater;
   private boolean _isClosed;

   public GZIPInputStream(InputStream var1) {
      this(var1, 5120);
   }

   public GZIPInputStream(InputStream var1, int var2) {
      if (var1 != null && var2 >= 1024) {
         this._inputStream = var1;
         this._tempBuffer = new byte[var2];
         this._inflater = (Inflater)(new Object(31));
      } else {
         throw new Object();
      }
   }

   @Override
   public synchronized int read() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public synchronized int read(byte[] var1, int var2, int var3) {
      if (var1 == null) {
         throw new Object();
      }

      if (var2 >= 0 && var3 >= 0 && var2 + var3 <= var1.length) {
         if (this._isClosed) {
            throw new Object();
         }

         if (var3 == 0) {
            return 0;
         }

         int var4 = 0;

         while (var3 > 0) {
            if (this._currentChunk != null && this._currentOffset < this._currentChunk.length) {
               int var5 = Math.min(this._currentChunk.length - this._currentOffset, var3);
               System.arraycopy(this._currentChunk, this._currentOffset, var1, var2, var5);
               this._currentOffset += var5;
               var2 += var5;
               var4 += var5;
               var3 -= var5;
            } else if (!this.readNextChunk()) {
               if (var4 > 0) {
                  return var4;
               }

               return -1;
            }
         }

         return var4 > 0 ? var4 : -1;
      } else {
         throw new Object();
      }
   }

   @Override
   public synchronized int available() {
      if (this._isClosed) {
         throw new Object();
      } else {
         return this._currentChunk != null ? this._currentChunk.length - this._currentOffset : 0;
      }
   }

   @Override
   public void close() {
      this._inputStream.close();
      this._isClosed = true;
   }

   private boolean readNextChunk() {
      if (this._isClosed) {
         throw new Object();
      }

      int var1 = this._inputStream.read(this._tempBuffer);
      if (var1 < 0) {
         return false;
      }

      this._currentChunk = this._inflater.decompress(this._tempBuffer, 0, var1);
      this._currentOffset = 0;
      return true;
   }
}
