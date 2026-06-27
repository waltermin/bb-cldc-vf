package net.rim.device.api.io;

import java.io.ByteArrayOutputStream;

public class NoCopyByteArrayOutputStream extends ByteArrayOutputStream {
   public NoCopyByteArrayOutputStream() {
   }

   public NoCopyByteArrayOutputStream(int var1) {
   }

   public NoCopyByteArrayOutputStream(byte[] var1, int var2) {
      super.buf = var1;
      super.count = var2;
   }

   public byte[] getByteArray() {
      return super.buf;
   }
}
