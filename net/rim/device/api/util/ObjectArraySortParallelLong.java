package net.rim.device.api.util;

final class ObjectArraySortParallelLong {
   private ObjectArraySortParallelLong() {
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

   public static final void sort(Object[] var0, int var1, int var2, long[] var3, Comparator var4) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   private static final boolean exchange(Object[] var0, long[] var1, Comparator var2, int var3, int var4) {
      Object var5 = var0[var3];
      Object var6 = var0[var4];
      if (var2.compare(var5, var6) > 0) {
         var0[var3] = var6;
         var0[var4] = var5;
         long var7 = var1[var3];
         var1[var3] = var1[var4];
         var1[var4] = var7;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(Object[] var0, Object[] var1, long[] var2, long[] var3, Comparator var4, int var5, int var6) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }
}
