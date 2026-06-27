package net.rim.device.cldc.io.comm;

import java.io.InputStream;

final class IOPortInputStream extends InputStream {
   private Protocol _conn;
   private byte[] _data;
   private int _offset;
   private int _length;

   IOPortInputStream(Protocol var1) {
      this._conn = var1;
      this._data = new byte[1024];
   }

   private final void fill() {
      if (this._length < 1) {
         this._offset = 0;
         this._length = this._conn.read(this._data, 0, this._data.length);
      }
   }

   @Override
   public final int read() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final int read(byte[] var1, int var2, int var3) {
      this.fill();
      int var4 = this._length;
      if (var4 > var3) {
         var4 = var3;
      }

      System.arraycopy(this._data, this._offset, var1, var2, var4);
      this._offset += var4;
      this._length -= var4;
      return var4;
   }

   @Override
   public final int available() {
      return this._length;
   }
}
