package net.rim.device.api.util;

final class ObjectArraySortParallel {
   private ObjectArraySortParallel() {
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

   public static final void sort(Object[] var0, int var1, int var2, Object[] var3, Comparator var4) {
      int var5 = rangeCheck(var0.length, var1, var2);
      if (var5 != 0) {
         int var6 = var5 + 1 >> 1;
         Object[] var7 = new Object[var6];
         Object[] var8 = new Object[var6];
         mergeSort(var0, var7, var3, var8, var4, var1, var5);
      }
   }

   private static final boolean exchange(Object[] var0, Object[] var1, Comparator var2, int var3, int var4) {
      Object var5 = var0[var3];
      Object var6 = var0[var4];
      if (var2.compare(var5, var6) > 0) {
         var0[var3] = var6;
         var0[var4] = var5;
         Object var7 = var1[var3];
         var1[var3] = var1[var4];
         var1[var4] = var7;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(Object[] var0, Object[] var1, Object[] var2, Object[] var3, Comparator var4, int var5, int var6) {
      switch (var6) {
         case -1:
            int var7 = var6 + 1 >> 1;
            mergeSort(var0, var1, var2, var3, var4, var5, var7);
            mergeSort(var0, var1, var2, var3, var4, var5 + var7, var6 - var7);
            int var8 = var5 + var7;
            if (var4.compare(var0[var8 - 1], var0[var8]) <= 0) {
               return;
            } else {
               System.arraycopy(var0, var5, var1, 0, var7);
               System.arraycopy(var2, var5, var3, 0, var7);
               int var9 = 0;
               int var10 = var9 + var7;
               int var11 = var5 + var7;
               int var12 = var11 + var6 - var7;
               int var13 = var5 + var6;

               for (; var5 < var13; var5++) {
                  if (var9 >= var10) {
                     while (var5 < var13) {
                        var0[var5] = var0[var11];
                        var2[var5] = var2[var11];
                        var11++;
                        var5++;
                     }

                     return;
                  }

                  if (var11 >= var12) {
                     while (var5 < var13) {
                        var0[var5] = var1[var9];
                        var2[var5] = var3[var9];
                        var9++;
                        var5++;
                     }

                     return;
                  }

                  if (var4.compare(var1[var9], var0[var11]) > 0) {
                     var0[var5] = var0[var11];
                     var2[var5] = var2[var11];
                     var11++;
                  } else {
                     var0[var5] = var1[var9];
                     var2[var5] = var3[var9];
                     var9++;
                  }
               }

               return;
            }
         case 0:
         case 1:
         default:
            return;
         case 2:
            exchange(var0, var2, var4, var5, var5 + 1);
            return;
         case 3:
            exchange(var0, var2, var4, var5, var5 + 1);
            if (exchange(var0, var2, var4, var5 + 1, var5 + 2)) {
               exchange(var0, var2, var4, var5, var5 + 1);
            }
      }
   }
}
