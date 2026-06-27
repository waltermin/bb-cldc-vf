package net.rim.device.cldc.io.tcpsocket;

import java.io.OutputStream;

final class TcpOutputStream extends OutputStream {
   private TcpSocket _socket;

   public TcpOutputStream(TcpSocket var1) {
      this._socket = var1;
   }

   @Override
   public final void write(int var1) {
      this._socket.write(var1);
   }

   @Override
   public final void write(byte[] var1, int var2, int var3) {
      this._socket.write(var1, var2, var3);
   }

   @Override
   public final void close() {
      this._socket.outputClosed();
   }
}
