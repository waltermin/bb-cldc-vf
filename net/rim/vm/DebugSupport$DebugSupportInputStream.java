package net.rim.vm;

import java.io.InputStream;

class DebugSupport$DebugSupportInputStream extends InputStream {
   private int _fileno;
   private byte[] _byteBuf = new byte[1];

   DebugSupport$DebugSupportInputStream(int var1) {
      this._fileno = var1;
   }

   @Override
   public int read() {
      return DebugSupport.read(this._fileno, this._byteBuf, 0, 1) != 1 ? -1 : this._byteBuf[0] & 0xFF;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      return DebugSupport.read(this._fileno, var1, var2, var3);
   }

   @Override
   public void close() {
      DebugSupport.closeFile(this._fileno);
   }
}
