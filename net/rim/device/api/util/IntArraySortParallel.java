package net.rim.device.api.util;

final class IntArraySortParallel {
   private IntArraySortParallel() {
   }

   private static final int rangeCheck(int var0, int var1, int var2) {
      if (var1 > var2) {
         throw new Object();
      } else if (var1 < 0) {
         throw new Object(var1);
      } else if (var2 > var0) {
         throw new Object(var2);
      } else {
         return var2 - var1;
      }
   }

   public static final void sort(int[] var0, int var1, int var2, Object[] var3) {
      int var4 = rangeCheck(var0.length, var1, var2);
      if (var4 != 0) {
         int var5 = var4 + 1 >> 1;
         int[] var6 = new int[var5];
         Object[] var7 = new Object[var5];
         mergeSort(var0, var6, var3, var7, var1, var4);
      }
   }

   private static final boolean exchange(int[] var0, Object[] var1, int var2, int var3) {
      int var4 = var0[var2];
      int var5 = var0[var3];
      if (var4 > var5) {
         var0[var2] = var5;
         var0[var3] = var4;
         Object var6 = var1[var2];
         var1[var2] = var1[var3];
         var1[var3] = var6;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(int[] var0, int[] var1, Object[] var2, Object[] var3, int var4, int var5) {
      switch (var5) {
         case -1:
            int var6 = var5 + 1 >> 1;
            mergeSort(var0, var1, var2, var3, var4, var6);
            mergeSort(var0, var1, var2, var3, var4 + var6, var5 - var6);
            int var7 = var4 + var6;
            if (var0[var7 - 1] <= var0[var7]) {
               return;
            } else {
               System.arraycopy(var0, var4, var1, 0, var6);
               System.arraycopy(var2, var4, var3, 0, var6);
               int var8 = 0;
               int var9 = var8 + var6;
               int var10 = var4 + var6;
               int var11 = var10 + var5 - var6;
               int var12 = var4 + var5;

               for (; var4 < var12; var4++) {
                  if (var8 >= var9) {
                     while (var4 < var12) {
                        var0[var4] = var0[var10];
                        var2[var4] = var2[var10];
                        var10++;
                        var4++;
                     }

                     return;
                  }

                  if (var10 >= var11) {
                     while (var4 < var12) {
                        var0[var4] = var1[var8];
                        var2[var4] = var3[var8];
                        var8++;
                        var4++;
                     }

                     return;
                  }

                  if (var1[var8] > var0[var10]) {
                     var0[var4] = var0[var10];
                     var2[var4] = var2[var10];
                     var10++;
                  } else {
                     var0[var4] = var1[var8];
                     var2[var4] = var3[var8];
                     var8++;
                  }
               }

               return;
            }
         case 0:
         case 1:
         default:
            return;
         case 2:
            exchange(var0, var2, var4, var4 + 1);
            return;
         case 3:
            exchange(var0, var2, var4, var4 + 1);
            if (exchange(var0, var2, var4 + 1, var4 + 2)) {
               exchange(var0, var2, var4, var4 + 1);
            }
      }
   }
}
