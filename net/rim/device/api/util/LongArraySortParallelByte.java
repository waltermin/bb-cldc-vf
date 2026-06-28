package net.rim.device.api.util;

final class LongArraySortParallelByte {
   private LongArraySortParallelByte() {
   }

   private static final int rangeCheck(int arrayLen, int from, int to) {
      if (from > to) {
         throw new Object();
      } else if (from < 0) {
         throw new Object(from);
      } else if (to > arrayLen) {
         throw new Object(to);
      } else {
         return to - from;
      }
   }

   public static final void sort(long[] a, int from, int to, byte[] parallel) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   private static final boolean exchange(long[] a, byte[] parallel, int x1, int x2) {
      long t1 = a[x1];
      long t2 = a[x2];
      if (t1 > t2) {
         a[x1] = t2;
         a[x2] = t1;
         byte tempObj = parallel[x1];
         parallel[x1] = parallel[x2];
         parallel[x2] = tempObj;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(long[] a, long[] aux, byte[] parallel, byte[] auxParallel, int from, int length) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }
}
