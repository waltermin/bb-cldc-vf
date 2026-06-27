package net.rim.device.api.util;

final class ObjectArraySort {
   private ObjectArraySort() {
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

   public static final void sort(Object[] var0, int var1, int var2, Comparator var3) {
      int var4 = rangeCheck(var0.length, var1, var2);
      if (var4 != 0) {
         int var5 = var4 + 1 >> 1;
         Object[] var6 = new Object[var5];
         mergeSort(var0, var6, var3, var1, var4);
      }
   }

   private static final boolean exchange(Object[] var0, Comparator var1, int var2, int var3) {
      Object var4 = var0[var2];
      Object var5 = var0[var3];
      if (var1.compare(var4, var5) > 0) {
         var0[var2] = var5;
         var0[var3] = var4;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(Object[] var0, Object[] var1, Comparator var2, int var3, int var4) {
      switch (var4) {
         case -1:
            int var5 = var4 + 1 >> 1;
            mergeSort(var0, var1, var2, var3, var5);
            mergeSort(var0, var1, var2, var3 + var5, var4 - var5);
            int var6 = var3 + var5;
            if (var2.compare(var0[var6 - 1], var0[var6]) <= 0) {
               return;
            } else {
               System.arraycopy(var0, var3, var1, 0, var5);
               int var7 = 0;
               int var8 = var7 + var5;
               int var9 = var3 + var5;
               int var10 = var9 + var4 - var5;
               int var11 = var3 + var4;

               for (; var3 < var11; var3++) {
                  if (var7 >= var8) {
                     while (var3 < var11) {
                        var0[var3] = var0[var9];
                        var9++;
                        var3++;
                     }

                     return;
                  }

                  if (var9 >= var10) {
                     while (var3 < var11) {
                        var0[var3] = var1[var7];
                        var7++;
                        var3++;
                     }

                     return;
                  }

                  if (var2.compare(var1[var7], var0[var9]) > 0) {
                     var0[var3] = var0[var9];
                     var9++;
                  } else {
                     var0[var3] = var1[var7];
                     var7++;
                  }
               }

               return;
            }
         case 0:
         case 1:
         default:
            return;
         case 2:
            exchange(var0, var2, var3, var3 + 1);
            return;
         case 3:
            exchange(var0, var2, var3, var3 + 1);
            if (exchange(var0, var2, var3 + 1, var3 + 2)) {
               exchange(var0, var2, var3, var3 + 1);
            }
      }
   }
}
