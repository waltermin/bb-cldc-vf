package net.rim.device.internal.ui;

import net.rim.device.api.math.Fixed32;
import net.rim.device.api.system.Bitmap;

public class ScaleBitmap {
   public static final int FILTER_LANCZOS;
   public static final int FILTER_BOX;
   public static final int FILTER_BILINEAR;
   static Filter _filterBox;
   static Filter _filterBilinear;
   static Filter _filterLanczos;

   public static int[] scale(Bitmap var0, int var1, int var2) {
      return scale(1, var0, var1, var2);
   }

   public static int[] scale(int var0, Bitmap var1, int var2, int var3) {
      Filter var4;
      switch (var0) {
         case -1:
            var4 = _filterLanczos;
            break;
         case 0:
         default:
            var4 = _filterLanczos;
            break;
         case 1:
            var4 = _filterBox;
            break;
         case 2:
            var4 = _filterBilinear;
      }

      return scale(var4, var1, var2, var3);
   }

   public static Bitmap scaleBitmap(Bitmap var0, int var1, int var2) {
      return scaleBitmap(1, var0, var1, var2);
   }

   public static Bitmap scaleBitmap(int var0, Bitmap var1, int var2, int var3) {
      Bitmap var4 = var1.createScaledBitmap(var2, var3);
      var1.getScaled(var4, var0);
      return var4;
   }

   public static int[] scale(Filter var0, Bitmap var1, int var2, int var3) {
      int var4 = var1.getWidth();
      int var5 = var1.getHeight();
      Weights[] var6 = null;
      int[] var7 = new int[var2 * var5];
      if (var4 == var2) {
         var1.getARGB(var7, 0, var4, 0, 0, var4, var5);
      } else {
         HorizontalFetcher var8 = new HorizontalFetcher();
         var6 = calcWeights(var0, var4, var2);
         int var9 = 0;
         int[] var10 = new int[var4];

         for (int var11 = 0; var11 < var5; var11++) {
            var1.getARGB(var10, 0, var4, 0, var11, var4, 1);
            var8.set(var10);

            for (int var12 = 0; var12 < var2; var12++) {
               var7[var9++] = var6[var12].filter(var8);
            }
         }
      }

      int[] var13;
      if (var5 == var3) {
         var13 = var7;
      } else {
         var13 = new int[var2 * var3];
         VerticalFetcher var14 = new VerticalFetcher(var7, var2);
         if (var4 != var5 || var2 != var3 || var6 == null) {
            var6 = calcWeights(var0, var5, var3);
         }

         for (int var15 = 0; var15 < var2; var15++) {
            var14.set(var15);

            for (int var16 = 0; var16 < var3; var16++) {
               var13[var16 * var2 + var15] = var6[var16].filter(var14);
            }
         }
      }

      return var13;
   }

   private static Weights[] calcWeights(Filter var0, int var1, int var2) {
      int var3 = Fixed32.toFP(var2) / var1;
      int var4 = 65536;
      int var5 = var0.widthFP();
      if (var3 < 65536) {
         var5 = Fixed32.div(var5, var3);
         var4 = var3;
      }

      int var6 = Fixed32.toInt(Fixed32.ceil(var5) << 1) + 1;
      Weights[] var7 = new Weights[var2];

      for (int var8 = 0; var8 < var2; var8++) {
         int var9 = Fixed32.div(32768, var3) - 32768;
         int var10 = Fixed32.div(Fixed32.toFP(var8), var3) + var9;
         int var11 = Math.max(0, Fixed32.toInt(Fixed32.floor(var10 - var5)));
         int var12 = Math.min(Fixed32.toInt(Fixed32.ceil(var10 + var5)), var1 - 1);
         if (var12 - var11 + 1 > var6) {
            if (var11 < var1 - 0) {
               var11++;
            } else {
               var12++;
            }
         }

         var7[var8] = new Weights(var0, var11, var12, var10, var4);
      }

      return var7;
   }
}
