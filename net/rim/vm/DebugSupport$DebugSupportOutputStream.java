package net.rim.vm;

import java.io.OutputStream;

class DebugSupport$DebugSupportOutputStream extends OutputStream {
   private int _fileno;
   private byte[] _byteBuf = new byte[1];

   DebugSupport$DebugSupportOutputStream(int var1) {
      this._fileno = var1;
   }

   @Override
   public void write(int var1) {
      this._byteBuf[0] = (byte)var1;
      DebugSupport.write(this._fileno, this._byteBuf, 0, 1);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      DebugSupport.write(this._fileno, var1, var2, var3);
   }

   @Override
   public void close() {
      DebugSupport.closeFile(this._fileno);
   }
}
