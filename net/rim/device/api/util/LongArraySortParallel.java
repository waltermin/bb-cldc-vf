package net.rim.device.api.util;

final class LongArraySortParallel {
   private LongArraySortParallel() {
   }

   private static final int rangeCheck(int arrayLen, int from, int to) {
      if (from > to) {
         throw new IllegalArgumentException();
      } else if (from < 0) {
         throw new ArrayIndexOutOfBoundsException(from);
      } else if (to > arrayLen) {
         throw new ArrayIndexOutOfBoundsException(to);
      } else {
         return to - from;
      }
   }

   public static final void sort(long[] a, int from, int to, Object[] parallel) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   private static final boolean exchange(long[] a, Object[] parallel, int x1, int x2) {
      long t1 = a[x1];
      long t2 = a[x2];
      if (t1 > t2) {
         a[x1] = t2;
         a[x2] = t1;
         Object tempObj = parallel[x1];
         parallel[x1] = parallel[x2];
         parallel[x2] = tempObj;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(long[] a, long[] aux, Object[] parallel, Object[] auxParallel, int from, int length) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }
}
