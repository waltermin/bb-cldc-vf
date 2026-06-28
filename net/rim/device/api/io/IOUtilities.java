package net.rim.device.api.io;

import java.io.InputStream;

public class IOUtilities {
   private IOUtilities() {
   }

   public static String getBindName(String name) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static byte[] streamToBytes(InputStream stream) {
      return streamToBytes(stream, 1024);
   }

   public static byte[] streamToBytes(InputStream stream, int increment) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
