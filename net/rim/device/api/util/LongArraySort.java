package net.rim.device.api.util;

final class LongArraySort {
   private LongArraySort() {
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

   public static final void sort(long[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   private static final boolean exchange(long[] var0, int var1, int var2) {
      long var3 = var0[var1];
      long var5 = var0[var2];
      if (var3 > var5) {
         var0[var1] = var5;
         var0[var2] = var3;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(long[] var0, long[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }
}
