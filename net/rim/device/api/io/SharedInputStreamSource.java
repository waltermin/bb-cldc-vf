package net.rim.device.api.io;

import java.io.InputStream;
import net.rim.vm.Array;

final class SharedInputStreamSource {
   private byte[] _data;
   private InputStream _sourceStream;
   private static final int RESIZE_DELTA;

   public SharedInputStreamSource(byte[] var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._data = var1;
      this._sourceStream = null;
   }

   public SharedInputStreamSource(InputStream var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._data = new byte[0];
      this._sourceStream = var1;
   }

   public final synchronized int read(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      while (var1 >= this._data.length) {
         if (this._sourceStream == null) {
            return -1;
         }

         this.expandData();
      }

      return this._data[var1] & 0xFF;
   }

   public final synchronized int read(int var1, byte[] var2) {
      return this.read(var1, var2, 0, var2 == null ? 0 : var2.length);
   }

   public final synchronized int read(int var1, byte[] var2, int var3, int var4) {
      if (var1 < 0 || var2 == null || var3 < 0 || var4 < 0 || var2.length - var4 < var3) {
         throw new Object();
      }

      if (var4 == 0) {
         return 0;
      }

      int var5 = var1 + var4;

      while (var5 > this._data.length) {
         if (this._sourceStream == null) {
            var4 = this._data.length - var1;
            if (var4 <= 0) {
               return -1;
            }
            break;
         }

         this.expandData();
      }

      System.arraycopy(this._data, var1, var2, var3, var4);
      return var4;
   }

   public final synchronized int available(int var1) {
      if (var1 < 0) {
         throw new Object();
      } else {
         int var2 = this._data.length - var1;
         if (var2 <= 0 && this._sourceStream != null) {
            return Math.max(0, this._sourceStream.available());
         } else {
            return var2 <= 0 ? 0 : var2;
         }
      }
   }

   public final synchronized long skip(int var1, long var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void expandData() {
      int var1 = this._data.length;
      Array.resize(this._data, var1 + 1024);
      int var2 = this._sourceStream.read(this._data, var1, 1024);
      if (var2 < 1024) {
         if (var2 > 0) {
            var1 += var2;
         }

         Array.resize(this._data, var1);
         this._sourceStream = null;
      }
   }
}
