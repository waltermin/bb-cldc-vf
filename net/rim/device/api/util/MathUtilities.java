package net.rim.device.api.util;

public final class MathUtilities {
   private MathUtilities() {
   }

   public static final int clamp(int var0, int var1, int var2) {
      if (var1 < var0) {
         return var0;
      } else {
         return var1 > var2 ? var2 : var1;
      }
   }

   public static final int wrap(int var0, int var1, int var2) {
      if (var1 < var0) {
         return var2;
      } else {
         return var1 > var2 ? var0 : var1;
      }
   }

   public static final int log2(int var0) {
      return log2(var0 & 4294967295L);
   }

   public static final int log2(long var0) {
      byte var2 = 0;
      if ((var0 & -4294967296L) != 0) {
         var0 >>>= 32;
         var2 |= 32;
      }

      if ((var0 & -65536) != 0) {
         var0 >>>= 16;
         var2 |= 16;
      }

      if ((var0 & 65280) != 0) {
         var0 >>>= 8;
         var2 |= 8;
      }

      if ((var0 & 240) != 0) {
         var0 >>>= 4;
         var2 |= 4;
      }

      if ((var0 & 12) != 0) {
         var0 >>>= 2;
         var2 |= 2;
      }

      if ((var0 & 2) != 0) {
         var2 |= 1;
      }

      return var2;
   }

   public static final native double log(double var0);

   public static final native double exp(double var0);
}
