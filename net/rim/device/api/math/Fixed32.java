package net.rim.device.api.math;

public final class Fixed32 {
   public static final int MAX_VALUE;
   public static final int MIN_VALUE;
   public static final int PI;
   public static final int E;
   public static final short NUM_FRACTION_BITS;
   public static final int FP090;
   public static final int FP180;
   public static final int FP270;
   public static final int FP360;
   public static final int RAD2DEG;
   public static final int ONE;
   public static final int HALF;
   public static final int QUARTER;
   private static final int INT_TEN_THOU;
   private static final int INT_FIVE_THOU;
   private static final int[] POWERS_OF_TEN;
   public static final int TWOPI;
   public static final int PI_OVER_2;

   private Fixed32() {
   }

   public static final native int radToDeg(int var0);

   public static final native int degToRad(int var0);

   public static final int abs(int var0) {
      if (var0 < 0) {
         if (var0 == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
         }

         var0 = -var0;
      }

      return var0;
   }

   public static final native int mul(int var0, int var1);

   public static final native int div(int var0, int var1);

   public static final int toInt(int var0) {
      return var0 >> 16;
   }

   public static final int toRoundedInt(int var0) {
      if ((var0 & 32768) != 0) {
         var0 += 65536;
      }

      return var0 >> 16;
   }

   public static final int toIntTenThou(int var0) {
      long var1 = (long)var0 * 10000;
      if ((var1 & 32768) != 0) {
         var1 += 65536;
      }

      return (int)(var1 >> 16);
   }

   public static final int toFP(int var0) {
      return var0 << 16;
   }

   public static final int tenThouToFP(int var0) {
      int var1 = var0 < 0 ? -5000 : 5000;
      return (int)((((long)var0 << 16) + var1) / 10000);
   }

   public static final native int sqrt(int var0);

   public static final native int sind(int var0);

   public static final native int cosd(int var0);

   public static final native int tand(int var0);

   public static final native int atand2(int var0, int var1);

   public static final int ceil(int var0) {
      return -floor(-var0);
   }

   public static final native int floor(int var0);

   public static final native int round(int var0);

   public static final native int Sin(int var0);

   public static final native int Cos(int var0);

   public static final native int Tan(int var0);

   public static final native int ArcTan(int var0);

   public static final native int ArcCos(int var0);

   public static final int parseFixed32(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int divtoInt(int var0, int var1) {
      long var2 = var0;
      long var4 = var1;
      long var6 = (var2 << 32) / var4;
      var6 += 2147483648L;
      return (int)(var6 >> 32);
   }
}
