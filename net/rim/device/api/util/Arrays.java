package net.rim.device.api.util;

import net.rim.vm.Array;

public final class Arrays {
   private Arrays() {
   }

   public static final void append(Object[] var0, Object[] var1) {
      int var2 = var0.length;
      int var3 = var1.length;
      Array.resize(var0, var2 + var3);
      System.arraycopy(var1, 0, var0, var2, var3);
   }

   public static final void append(long[] var0, long[] var1) {
      int var2 = var0.length;
      int var3 = var1.length;
      Array.resize(var0, var2 + var3);
      System.arraycopy(var1, 0, var0, var2, var3);
   }

   public static final void append(int[] var0, int[] var1) {
      int var2 = var0.length;
      int var3 = var1.length;
      Array.resize(var0, var2 + var3);
      System.arraycopy(var1, 0, var0, var2, var3);
   }

   public static final void append(byte[] var0, byte[] var1) {
      int var2 = var0.length;
      int var3 = var1.length;
      Array.resize(var0, var2 + var3);
      System.arraycopy(var1, 0, var0, var2, var3);
   }

   public static final void add(Object[] var0, Object var1) {
      int var2 = var0.length;
      Array.resize(var0, var2 + 1);
      var0[var2] = var1;
   }

   public static final void add(byte[] var0, byte var1) {
      int var2 = var0.length;
      Array.resize(var0, var2 + 1);
      var0[var2] = var1;
   }

   public static final void add(short[] var0, short var1) {
      int var2 = var0.length;
      Array.resize(var0, var2 + 1);
      var0[var2] = var1;
   }

   public static final void add(int[] var0, int var1) {
      int var2 = var0.length;
      Array.resize(var0, var2 + 1);
      var0[var2] = var1;
   }

   public static final void add(long[] var0, long var1) {
      int var3 = var0.length;
      Array.resize(var0, var3 + 1);
      var0[var3] = var1;
   }

   public static final void add(boolean[] var0, boolean var1) {
      int var2 = var0.length;
      Array.resize(var0, var2 + 1);
      var0[var2] = var1;
   }

   public static final byte[] copy(byte[] var0) {
      if (var0 == null) {
         return null;
      }

      byte[] var1 = new byte[var0.length];
      System.arraycopy(var0, 0, var1, 0, var0.length);
      return var1;
   }

   public static final byte[] copy(byte[] var0, int var1, int var2) {
      if (var0 == null) {
         return null;
      }

      byte[] var3 = new byte[var2];
      System.arraycopy(var0, var1, var3, 0, var2);
      return var3;
   }

   public static final char[] copy(char[] var0) {
      if (var0 == null) {
         return null;
      }

      char[] var1 = new char[var0.length];
      System.arraycopy(var0, 0, var1, 0, var0.length);
      return var1;
   }

   public static final char[] copy(char[] var0, int var1, int var2) {
      if (var0 == null) {
         return null;
      }

      char[] var3 = new char[var2];
      System.arraycopy(var0, var1, var3, 0, var2);
      return var3;
   }

   public static final int[] copy(int[] var0) {
      if (var0 == null) {
         return null;
      }

      int[] var1 = new int[var0.length];
      System.arraycopy(var0, 0, var1, 0, var0.length);
      return var1;
   }

   public static final int[] copy(int[] var0, int var1, int var2) {
      if (var0 == null) {
         return null;
      }

      int[] var3 = new int[var2];
      System.arraycopy(var0, var1, var3, 0, var2);
      return var3;
   }

   public static final long[] copy(long[] var0) {
      if (var0 == null) {
         return null;
      }

      long[] var1 = new long[var0.length];
      System.arraycopy(var0, 0, var1, 0, var0.length);
      return var1;
   }

   public static final long[] copy(long[] var0, int var1, int var2) {
      if (var0 == null) {
         return null;
      }

      long[] var3 = new long[var2];
      System.arraycopy(var0, var1, var3, 0, var2);
      return var3;
   }

   public static final short[] copy(short[] var0) {
      if (var0 == null) {
         return null;
      }

      short[] var1 = new short[var0.length];
      System.arraycopy(var0, 0, var1, 0, var0.length);
      return var1;
   }

   public static final short[] copy(short[] var0, int var1, int var2) {
      if (var0 == null) {
         return null;
      }

      short[] var3 = new short[var2];
      System.arraycopy(var0, var1, var3, 0, var2);
      return var3;
   }

   public static final void fill(byte[] var0, byte var1) {
      fill(var0, var1, 0, -1);
   }

   public static final void fill(byte[] var0, byte var1, int var2, int var3) {
      int var4 = var1 & 255;
      var4 |= var4 << 8;
      var4 |= var4 << 16;
      fillArray(var0, var4, var2, var3);
   }

   public static final void fill(char[] var0, char var1) {
      fill(var0, var1, 0, -1);
   }

   public static final void fill(char[] var0, char var1, int var2, int var3) {
      int var4 = var1 << 16;
      var4 |= var1;
      fillArray(var0, var4, var2, var3);
   }

   public static final void fill(int[] var0, int var1) {
      fillArray(var0, var1, 0, -1);
   }

   public static final void fill(int[] var0, int var1, int var2, int var3) {
      fillArray(var0, var1, var2, var3);
   }

   public static final void fill(short[] var0, short var1) {
      fill(var0, var1, 0, -1);
   }

   public static final void fill(short[] var0, short var1, int var2, int var3) {
      int var4 = var1 << 16;
      var4 |= var4 >>> 16;
      fillArray(var0, var4, var2, var3);
   }

   public static final void fill(long[] var0, long var1) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public static final native void fill(long[] var0, long var1, int var3, int var4);

   private static final native void fillArray(Object var0, int var1, int var2, int var3);

   public static final void insertAt(byte[] var0, byte var1, int var2) {
      int var3 = var0.length;
      Array.resize(var0, var3 + 1);
      System.arraycopy(var0, var2, var0, var2 + 1, var3 - var2);
      var0[var2] = var1;
   }

   public static final void insertAt(int[] var0, int var1, int var2) {
      int var3 = var0.length;
      Array.resize(var0, var3 + 1);
      System.arraycopy(var0, var2, var0, var2 + 1, var3 - var2);
      var0[var2] = var1;
   }

   public static final void insertAt(long[] var0, long var1, int var3) {
      int var4 = var0.length;
      Array.resize(var0, var4 + 1);
      System.arraycopy(var0, var3, var0, var3 + 1, var4 - var3);
      var0[var3] = var1;
   }

   public static final void insertAt(Object[] var0, Object var1, int var2) {
      int var3 = var0.length;
      Array.resize(var0, var3 + 1);
      System.arraycopy(var0, var2, var0, var2 + 1, var3 - var2);
      var0[var2] = var1;
   }

   public static final void removeAt(Object[] var0, int var1) {
      var0[var1] = null;
      int var2 = var0.length - 1;
      System.arraycopy(var0, var1 + 1, var0, var1, var2 - var1);
      Array.resize(var0, var2);
   }

   public static final void removeAt(int[] var0, int var1) {
      int var2 = var0.length - 1;
      System.arraycopy(var0, var1 + 1, var0, var1, var2 - var1);
      Array.resize(var0, var2);
   }

   public static final void removeAt(long[] var0, int var1) {
      int var2 = var0.length - 1;
      System.arraycopy(var0, var1 + 1, var0, var1, var2 - var1);
      Array.resize(var0, var2);
   }

   public static final void removeAt(boolean[] var0, int var1) {
      int var2 = var0.length - 1;
      System.arraycopy(var0, var1 + 1, var0, var1, var2 - var1);
      Array.resize(var0, var2);
   }

   public static final int sum(byte[] var0, int var1, int var2, boolean var3) {
      if (var2 < 0) {
         throw new Object();
      }

      int var4 = 0;
      if (var3) {
         for (int var5 = var1 + var2 - 1; var5 >= var1; var5--) {
            var4 += var0[var5];
         }
      } else {
         for (int var6 = var1 + var2 - 1; var6 >= var1; var6--) {
            var4 += var0[var6] & 255;
         }
      }

      return var4;
   }

   public static final void zero(byte[] var0) {
      fill(var0, (byte)0);
   }

   public static final void zero(char[] var0) {
      fill(var0, '\u0000', 0, -1);
   }

   public static final void zero(int[] var0) {
      fill(var0, 0, 0, -1);
   }

   public static final void zero(long[] var0) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public static final void zero(short[] var0) {
      fill(var0, (short)0, 0, -1);
   }

   private static final native boolean equalsArray(Object var0, int var1, Object var2, int var3, int var4);

   public static final boolean equals(byte[] var0, byte[] var1) {
      return equalsArray(var0, 0, var1, 0, -1);
   }

   public static final boolean equals(byte[] var0, int var1, byte[] var2, int var3, int var4) {
      if (var4 < 0) {
         throw new Object();
      } else {
         return equalsArray(var0, var1, var2, var3, var4);
      }
   }

   public static final boolean equals(char[] var0, char[] var1) {
      return equalsArray(var0, 0, var1, 0, -1);
   }

   public static final boolean equals(char[] var0, int var1, char[] var2, int var3, int var4) {
      if (var4 < 0) {
         throw new Object();
      } else {
         return equalsArray(var0, var1, var2, var3, var4);
      }
   }

   public static final boolean equals(short[] var0, short[] var1) {
      return equalsArray(var0, 0, var1, 0, -1);
   }

   public static final boolean equals(int[] var0, int[] var1) {
      return equalsArray(var0, 0, var1, 0, -1);
   }

   public static final boolean equals(long[] var0, long[] var1) {
      return equalsArray(var0, 0, var1, 0, -1);
   }

   public static final boolean equals(Object[] var0, Object[] var1) {
      if (var0 == var1) {
         return true;
      }

      if (var0 != null && var1 != null) {
         int var2 = var0.length;
         if (var1.length != var2) {
            return false;
         }

         for (int var3 = 0; var3 < var2; var3++) {
            Object var4 = var0[var3];
            Object var5 = var1[var3];
            if (var4 == null ? var5 != null : !var4.equals(var5)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static final void sort(Object[] var0, Comparator var1) {
      ObjectArraySort.sort(var0, 0, var0.length, var1);
   }

   public static final void sort(Object[] var0, int var1, int var2, Comparator var3) {
      ObjectArraySort.sort(var0, var1, var2, var3);
   }

   public static final void sort(int[] var0, int var1, int var2) {
      IntArraySort.sort(var0, var1, var2);
   }

   public static final void sort(int[] var0, int var1, int var2, IntComparator var3) {
      IntIndexArraySort.sort(var0, var1, var2, var3);
   }

   public static final void sort(long[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public static final void sort(Object[] var0, int var1, int var2, Object[] var3, Comparator var4) {
      ObjectArraySortParallel.sort(var0, var1, var2, var3, var4);
   }

   public static final void sort(Object[] var0, int var1, int var2, long[] var3, Comparator var4) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public static final void sort(int[] var0, int var1, int var2, Object[] var3) {
      IntArraySortParallel.sort(var0, var1, var2, var3);
   }

   public static final void sort(long[] var0, int var1, int var2, Object[] var3) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public static final void sort(long[] var0, int var1, int var2, int[] var3) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public static final void sort(long[] var0, int var1, int var2, byte[] var3) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public static final void sort(int[] var0, int var1, int var2, byte[] var3) {
      IntArraySortParallelByte.sort(var0, var1, var2, var3);
   }

   public static final void sort(int[] var0, int var1, int var2, int[] var3) {
      IntArraySortParallelInt.sort(var0, var1, var2, var3);
   }

   public static final void sort(byte[] var0, int var1, int var2, char[] var3) {
      ByteArraySortParallelChar.sort(var0, var1, var2, var3);
   }

   public static final int binarySearch(short[] var0, short var1) {
      int var2 = 0;
      int var3 = var0.length - 1;

      while (var2 <= var3) {
         int var4 = var2 + var3 >> 1;
         short var5 = var0[var4];
         if (var5 < var1) {
            var2 = var4 + 1;
         } else {
            if (var5 <= var1) {
               return var4;
            }

            var3 = var4 - 1;
         }
      }

      return -(var2 + 1);
   }

   public static final int binarySearch(int[] var0, int var1) {
      return binarySearch(var0, var1, 0, var0.length);
   }

   public static final int binarySearch(int[] var0, int var1, int var2, int var3) {
      if (var0 == null) {
         throw new Object();
      }

      checkIndices(var0.length, var2, var3);
      int var4 = var2;
      int var5 = var3 - 1;

      while (var4 <= var5) {
         int var6 = var4 + var5 >> 1;
         int var7 = var0[var6];
         if (var7 < var1) {
            var4 = var6 + 1;
         } else {
            if (var7 <= var1) {
               return var6;
            }

            var5 = var6 - 1;
         }
      }

      return -(var4 + 1);
   }

   public static final int binarySearch(int[] var0, int var1, IntComparator var2, int var3, int var4) {
      if (var0 == null) {
         throw new Object();
      }

      checkIndices(var0.length, var3, var4);
      int var5 = var3;
      int var6 = var4 - 1;

      while (var5 <= var6) {
         int var7 = var5 + var6 >> 1;
         int var8 = var0[var7];
         int var9 = var2.compare(var1, var8);
         if (var9 > 0) {
            var5 = var7 + 1;
         } else {
            if (var9 >= 0) {
               return var7;
            }

            var6 = var7 - 1;
         }
      }

      return -(var5 + 1);
   }

   public static final int binarySearch(long[] var0, long var1, int var3, int var4) {
      if (var0 == null) {
         throw new Object();
      }

      checkIndices(var0.length, var3, var4);
      int var5 = var3;
      int var6 = var4 - 1;

      while (var5 <= var6) {
         int var7 = var5 + var6 >> 1;
         if (var1 == var0[var7]) {
            var6 = var7;
            if (var7 == var5) {
               return var5;
            }
         } else if (var1 < var0[var7]) {
            var6 = var7 - 1;
         } else {
            var5 = var7 + 1;
         }
      }

      return -(var5 + 1);
   }

   public static final int binarySearch(Object[] var0, Object var1, Comparator var2, int var3, int var4) {
      if (var0 == null) {
         throw new Object();
      }

      checkIndices(var0.length, var3, var4);
      int var5 = var3;
      int var6 = var4 - 1;

      while (var5 <= var6) {
         int var7 = var5 + var6 >> 1;
         Object var8 = var0[var7];
         int var9 = var2.compare(var1, var8);
         if (var9 < 0) {
            var6 = var7 - 1;
         } else if (var9 > 0) {
            var5 = var7 + 1;
         } else {
            if (var1 == var8) {
               return var7;
            }

            var6 = var7;
            if (var6 == var5) {
               for (int var10 = var5 + 1; var10 < var4; var10++) {
                  Object var11 = var0[var10];
                  if (var1 == var11) {
                     return var10;
                  }

                  int var12 = var2.compare(var1, var11);
                  if (var12 != 0) {
                     break;
                  }
               }

               return var5;
            }
         }
      }

      return -(var5 + 1);
   }

   private static final void checkIndices(int var0, int var1, int var2) {
      if (var1 > var2) {
         throw new Object();
      } else if (var1 < 0 || var2 > var0) {
         throw new Object();
      }
   }

   public static final int getIndex(Object[] var0, Object var1) {
      for (int var2 = var0.length - 1; var2 >= 0; var2--) {
         Object var3 = var0[var2];
         if (var1 == var3 || var1 != null && var1.equals(var3)) {
            return var2;
         }
      }

      return -1;
   }

   public static final boolean contains(Object[] var0, Object var1) {
      return getIndex(var0, var1) >= 0;
   }

   public static final boolean contains(int[] var0, int var1) {
      return getIndex(var0, var1) >= 0;
   }

   public static final boolean contains(long[] var0, long var1) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public static final void remove(Object[] var0, Object var1) {
      int var2 = getIndex(var0, var1);
      if (var2 != -1) {
         removeAt(var0, var2);
      }
   }

   public static final void remove(int[] var0, int var1) {
      int var2 = getIndex(var0, var1);
      if (var2 != -1) {
         removeAt(var0, var2);
      }
   }

   public static final int getIndex(int[] var0, int var1) {
      int var2 = var0.length;

      for (int var3 = 0; var3 < var2; var3++) {
         if (var0[var3] == var1) {
            return var3;
         }
      }

      return -1;
   }

   public static final int getIndex(byte[] var0, byte var1) {
      int var2 = var0.length;

      for (int var3 = 0; var3 < var2; var3++) {
         if (var0[var3] == var1) {
            return var3;
         }
      }

      return -1;
   }

   public static final int getIndex(char[] var0, char var1) {
      int var2 = var0.length;

      for (int var3 = 0; var3 < var2; var3++) {
         if (var0[var3] == var1) {
            return var3;
         }
      }

      return -1;
   }

   public static final int getIndex(long[] var0, long var1) {
      int var3 = var0.length;

      for (int var4 = 0; var4 < var3; var4++) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }
}
