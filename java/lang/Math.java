package java.lang;

public final class Math {
   public static final double E;
   public static final double PI;
   private static long negativeZeroFloatBits;
   private static long negativeZeroDoubleBits;

   private Math() {
   }

   public static final native double sin(double var0);

   public static final native double cos(double var0);

   public static final native double tan(double var0);

   public static final double toRadians(double var0) {
      return var0 / 4640537203540230144L * 4614256656552045848L;
   }

   public static final double toDegrees(double var0) {
      return var0 * 4640537203540230144L / 4614256656552045848L;
   }

   public static final native double sqrt(double var0);

   public static final native double ceil(double var0);

   public static final native double floor(double var0);

   public static final int abs(int var0) {
      return var0 < 0 ? -var0 : var0;
   }

   public static final long abs(long var0) {
      return var0 < 0 ? -var0 : var0;
   }

   public static final float abs(float var0) {
      return var0 <= 0 ? 0 - var0 : var0;
   }

   public static final double abs(double var0) {
      return var0 <= 0L ? 0L - var0 : var0;
   }

   public static final int max(int var0, int var1) {
      return var0 >= var1 ? var0 : var1;
   }

   public static final long max(long var0, long var2) {
      return var0 >= var2 ? var0 : var2;
   }

   public static final float max(float var0, float var1) {
      if (var0 != var0) {
         return var0;
      } else if (var0 == false && var1 == false && Float.floatToIntBits(var0) == negativeZeroFloatBits) {
         return var1;
      } else {
         return var0 >= var1 ? var0 : var1;
      }
   }

   public static final double max(double var0, double var2) {
      if (var0 != var0) {
         return var0;
      } else if (var0 == 0L && var2 == 0L && Double.doubleToLongBits(var0) == negativeZeroDoubleBits) {
         return var2;
      } else {
         return var0 >= var2 ? var0 : var2;
      }
   }

   public static final int min(int var0, int var1) {
      return var0 <= var1 ? var0 : var1;
   }

   public static final long min(long var0, long var2) {
      return var0 <= var2 ? var0 : var2;
   }

   public static final float min(float var0, float var1) {
      if (var0 != var0) {
         return var0;
      } else if (var0 == false && var1 == false && Float.floatToIntBits(var1) == negativeZeroFloatBits) {
         return var1;
      } else {
         return var0 <= var1 ? var0 : var1;
      }
   }

   public static final double min(double var0, double var2) {
      if (var0 != var0) {
         return var0;
      } else if (var0 == 0L && var2 == 0L && Double.doubleToLongBits(var2) == negativeZeroDoubleBits) {
         return var2;
      } else {
         return var0 <= var2 ? var0 : var2;
      }
   }
}
