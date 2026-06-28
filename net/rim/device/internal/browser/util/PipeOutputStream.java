package net.rim.device.internal.browser.util;

import java.io.OutputStream;

public class PipeOutputStream extends OutputStream {
   Pipe _pipe;
   int _currentPacketNo;

   PipeOutputStream(Pipe pipe) {
      this._pipe = pipe;
   }

   @Override
   public void write(int x) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void write(byte[] data, int off, int len) {
      this._pipe.write(data, off, len, this._currentPacketNo);
   }

   @Override
   public void close() {
      this._pipe.closeWrite();
   }

   public void setPacketNo(int currentPacketNo) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean isPacketIncluded(int packetNo) {
      return this._pipe.isPacketIncluded(packetNo);
   }
}
