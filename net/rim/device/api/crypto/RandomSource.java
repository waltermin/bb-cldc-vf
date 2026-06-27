package net.rim.device.api.crypto;

public final class RandomSource {
   private static final long ID_TEST;

   private static final void selfTest() {
      FIPS186PseudoRandomSource.selfTest();
   }

   private RandomSource() {
   }

   public static final native long getLong();

   public static final native int getInt();

   public static final long getLong(long var0) {
      if (var0 <= 0) {
         throw new Object();
      }

      if ((var0 & -var0) == var0) {
         return getLong() & var0 - 1;
      }

      long var2;
      long var4;
      do {
         var2 = getLong() >>> 1;
         var4 = var2 % var0;
      } while (var2 - var4 + (var0 - 1) < 0);

      return var4;
   }

   public static final int getInt(int var0) {
      if (var0 <= 0) {
         throw new Object();
      }

      if ((var0 & -var0) == var0) {
         return getInt() & var0 - 1;
      }

      int var1;
      int var2;
      do {
         var1 = getInt() >>> 1;
         var2 = var1 % var0;
      } while (var1 - var2 + (var0 - 1) < 0);

      return var2;
   }

   public static final native void getBytes(byte[] var0, int var1, int var2);

   public static final byte[] getBytes(int var0) {
      if (var0 < 0) {
         throw new Object();
      }

      byte[] var1 = new byte[var0];
      getBytes(var1, 0, var0);
      return var1;
   }

   public static final void getBytes(byte[] var0) {
      if (var0 == null) {
         throw new Object();
      }

      getBytes(var0, 0, var0 == null ? 0 : var0.length);
   }

   public static final native void add(Object var0);
}
