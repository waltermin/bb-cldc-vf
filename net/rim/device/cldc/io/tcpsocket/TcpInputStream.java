package net.rim.device.cldc.io.tcpsocket;

import java.io.InputStream;

final class TcpInputStream extends InputStream {
   private TcpSocket _socket;

   public TcpInputStream(TcpSocket var1) {
      this._socket = var1;
   }

   @Override
   public final int read() {
      return this._socket.read();
   }

   @Override
   public final int read(byte[] var1, int var2, int var3) {
      return this._socket.read(var1, var2, var3);
   }

   @Override
   public final int available() {
      return this._socket.available();
   }

   @Override
   public final void close() {
      this._socket.inputClosed();
   }
}
