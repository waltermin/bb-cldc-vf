package net.rim.device.internal.bluetooth;

public final class UUIDUtilities {
   private static final byte[] BASE_UUID;

   private UUIDUtilities() {
   }

   public static final String toString(byte[] var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte[] toBytes(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte[] promoteTo128Bits(String var0) {
      byte[] var1 = toBytes(var0);
      int var2 = var1.length;
      if (var2 == 16) {
         return var1;
      }

      byte[] var3 = new byte[16];
      System.arraycopy(BASE_UUID, 0, var3, 0, BASE_UUID.length);
      System.arraycopy(var1, 0, var3, 4 - var2, var2);
      return var3;
   }

   public static final byte[] serialize(String var0) {
      return serialize(toBytes(var0));
   }

   private static final byte[] serialize(byte[] var0) {
      byte[] var1 = new byte[var0.length + 1];
      System.arraycopy(var0, 0, var1, 1, var0.length);
      var1[0] = 24;
      switch (var0.length) {
         case 2:
            var1[0] = (byte)(var1[0] | 1);
            return var1;
         case 4:
            var1[0] = (byte)(var1[0] | 2);
            return var1;
         case 16:
            var1[0] = (byte)(var1[0] | 4);
         default:
            return var1;
      }
   }
}
