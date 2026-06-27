package net.rim.device.cldc.io.comm;

import java.io.OutputStream;

class IOPortOutputStream extends OutputStream {
   private Protocol _conn;
   private byte[] _data;

   IOPortOutputStream(Protocol var1) {
      this._conn = var1;
      this._data = new byte[1];
   }

   @Override
   public void write(int var1) {
      this._data[0] = (byte)var1;
      this._conn.write(this._data, 0, 1);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      this._conn.write(var1, var2, var3);
   }
}
