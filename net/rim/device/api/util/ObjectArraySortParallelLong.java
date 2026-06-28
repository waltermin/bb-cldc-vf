package net.rim.device.api.util;

final class ObjectArraySortParallelLong {
   private ObjectArraySortParallelLong() {
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

   public static final void sort(Object[] a, int from, int to, long[] parallel, Comparator comparator) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   private static final boolean exchange(Object[] a, long[] parallel, Comparator comparator, int x1, int x2) {
      Object t1 = a[x1];
      Object t2 = a[x2];
      if (comparator.compare(t1, t2) > 0) {
         a[x1] = t2;
         a[x2] = t1;
         long tempObj = parallel[x1];
         parallel[x1] = parallel[x2];
         parallel[x2] = tempObj;
         return true;
      } else {
         return false;
      }
   }

   private static final void mergeSort(Object[] a, Object[] aux, long[] parallel, long[] auxParallel, Comparator comparator, int from, int length) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }
}
