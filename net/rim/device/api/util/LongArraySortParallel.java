package net.rim.device.api.util;

final class LongArraySortParallel {
   private LongArraySortParallel() {
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

   public static final void sort(long[] var0, int var1, int var2, Object[] var3) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   private static final boolean exchange(long[] var0, Object[] var1, int var2, int var3) {
      long var4 = var0[var2];
      long var6 = var0[var3];
      if (var4 > var6) {
         var0[var2] = var6;
         var0[var3] = var4;
         Object var8 = var1[var2];
         var1[var2] = var1[var3];
         var1[var3] = var8;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(long[] var0, long[] var1, Object[] var2, Object[] var3, int var4, int var5) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }
}
