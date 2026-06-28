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

   public static final int abs(int a) {
      if (a < 0) {
         if (a == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
         }

         a = -a;
      }

      return a;
   }

   public static final native int mul(int var0, int var1);

   public static final native int div(int var0, int var1);

   public static final int toInt(int fp) {
      return fp >> 16;
   }

   public static final int toRoundedInt(int value) {
      if ((value & 32768) != 0) {
         value += 65536;
      }

      return value >> 16;
   }

   public static final int toIntTenThou(int fp) {
      long value = (long)fp * 10000;
      if ((value & 32768) != 0) {
         value += 65536;
      }

      return (int)(value >> 16);
   }

   public static final int toFP(int i) {
      return i << 16;
   }

   public static final int tenThouToFP(int tenThou) {
      int rounder = tenThou < 0 ? -5000 : 5000;
      return (int)((((long)tenThou << 16) + rounder) / 10000);
   }

   public static final native int sqrt(int var0);

   public static final native int sind(int var0);

   public static final native int cosd(int var0);

   public static final native int tand(int var0);

   public static final native int atand2(int var0, int var1);

   public static final int ceil(int val) {
      return -floor(-val);
   }

   public static final native int floor(int var0);

   public static final native int round(int var0);

   public static final native int Sin(int var0);

   public static final native int Cos(int var0);

   public static final native int Tan(int var0);

   public static final native int ArcTan(int var0);

   public static final native int ArcCos(int var0);

   public static final int parseFixed32(String value) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int divtoInt(int n, int m) {
      long ln = n;
      long lm = m;
      long ret = (ln << 32) / lm;
      ret += 2147483648L;
      return (int)(ret >> 32);
   }
}
