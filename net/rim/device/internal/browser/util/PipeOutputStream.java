package net.rim.device.internal.browser.util;

import java.io.OutputStream;

public class PipeOutputStream extends OutputStream {
   Pipe _pipe;
   int _currentPacketNo;

   PipeOutputStream(Pipe var1) {
      this._pipe = var1;
   }

   @Override
   public void write(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      this._pipe.write(var1, var2, var3, this._currentPacketNo);
   }

   @Override
   public void close() {
      this._pipe.closeWrite();
   }

   public void setPacketNo(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean isPacketIncluded(int var1) {
      return this._pipe.isPacketIncluded(var1);
   }
}
