package net.rim.device.api.util;

public final class CRC16 {
   public static final int INITIAL_VALUE;

   private CRC16() {
   }

   public static final native int update(int var0, int var1);

   public static final int update(int var0, byte[] var1) {
      return update(var0, var1, 0, var1.length);
   }

   public static final native int update(int var0, byte[] var1, int var2, int var3);
}
