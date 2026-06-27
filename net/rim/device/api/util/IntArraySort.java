package net.rim.device.api.util;

final class IntArraySort {
   private IntArraySort() {
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

   public static final void sort(int[] var0, int var1, int var2) {
      int var3 = rangeCheck(var0.length, var1, var2);
      if (var3 != 0) {
         int var4 = var3 + 1 >> 1;
         int[] var5 = new int[var4];
         mergeSort(var0, var5, var1, var3);
      }
   }

   private static final boolean exchange(int[] var0, int var1, int var2) {
      int var3 = var0[var1];
      int var4 = var0[var2];
      if (var3 > var4) {
         var0[var1] = var4;
         var0[var2] = var3;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(int[] var0, int[] var1, int var2, int var3) {
      switch (var3) {
         case -1:
            int var4 = var3 + 1 >> 1;
            mergeSort(var0, var1, var2, var4);
            mergeSort(var0, var1, var2 + var4, var3 - var4);
            int var5 = var2 + var4;
            if (var0[var5 - 1] <= var0[var5]) {
               return;
            } else {
               System.arraycopy(var0, var2, var1, 0, var4);
               int var6 = 0;
               int var7 = var6 + var4;
               int var8 = var2 + var4;
               int var9 = var8 + var3 - var4;
               int var10 = var2 + var3;

               for (; var2 < var10; var2++) {
                  if (var6 >= var7) {
                     while (var2 < var10) {
                        var0[var2] = var0[var8];
                        var8++;
                        var2++;
                     }

                     return;
                  }

                  if (var8 >= var9) {
                     while (var2 < var10) {
                        var0[var2] = var1[var6];
                        var6++;
                        var2++;
                     }

                     return;
                  }

                  if (var1[var6] > var0[var8]) {
                     var0[var2] = var0[var8];
                     var8++;
                  } else {
                     var0[var2] = var1[var6];
                     var6++;
                  }
               }

               return;
            }
         case 0:
         case 1:
         default:
            return;
         case 2:
            exchange(var0, var2, var2 + 1);
            return;
         case 3:
            exchange(var0, var2, var2 + 1);
            if (exchange(var0, var2 + 1, var2 + 2)) {
               exchange(var0, var2, var2 + 1);
            }
      }
   }
}
