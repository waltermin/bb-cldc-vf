package java.lang;

class FDBigInt {
   int nWords;
   int[] data;

   public FDBigInt(int v) {
      this.nWords = 1;
      this.data = new int[1];
      this.data[0] = v;
   }

   public FDBigInt(long v) {
   }

   public FDBigInt(FDBigInt other) {
      this.data = new int[this.nWords = other.nWords];
      System.arraycopy(other.data, 0, this.data, 0, this.nWords);
   }

   private FDBigInt(int[] d, int n) {
      this.data = d;
      this.nWords = n;
   }

   public FDBigInt(long seed, char[] digit, int nd0, int nd) {
   }

   public void lshiftMe(int c) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public int normalizeMe() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public FDBigInt mult(int iv) {
      long v = iv;
      int[] r = new int[v * (this.data[this.nWords - 1] & 4294967295L) > 268435455 ? this.nWords + 1 : this.nWords];
      long p = 0;

      for (int i = 0; i < this.nWords; i++) {
         p += v * (this.data[i] & 4294967295L);
         r[i] = (int)p;
         p >>>= 32;
      }

      if (p == 0) {
         return new FDBigInt(r, this.nWords);
      }

      r[this.nWords] = (int)p;
      return new FDBigInt(r, this.nWords + 1);
   }

   public void multaddMe(int iv, int addend) {
      long v = iv;
      long p = v * (this.data[0] & 4294967295L) + (addend & 4294967295L);
      this.data[0] = (int)p;
      p >>>= 32;

      for (int i = 1; i < this.nWords; i++) {
         p += v * (this.data[i] & 4294967295L);
         this.data[i] = (int)p;
         p >>>= 32;
      }

      if (p != 0) {
         this.data[this.nWords] = (int)p;
         this.nWords++;
      }
   }

   public FDBigInt mult(FDBigInt other) {
      int[] r = new int[this.nWords + other.nWords];

      for (int i = 0; i < this.nWords; i++) {
         long v = this.data[i] & 4294967295L;
         long p = 0;

         int j;
         for (j = 0; j < other.nWords; j++) {
            p += (r[i + j] & 4294967295L) + v * (other.data[j] & 4294967295L);
            r[i + j] = (int)p;
            p >>>= 32;
         }

         r[i + j] = (int)p;
      }

      int var9 = r.length - 1;

      while (var9 > 0 && r[var9] == 0) {
         var9--;
      }

      return new FDBigInt(r, var9 + 1);
   }

   public FDBigInt add(FDBigInt other) {
      long c = 0;
      int[] a;
      int[] b;
      int n;
      int m;
      if (this.nWords >= other.nWords) {
         a = this.data;
         n = this.nWords;
         b = other.data;
         m = other.nWords;
      } else {
         a = other.data;
         n = other.nWords;
         b = this.data;
         m = this.nWords;
      }

      int[] r = new int[n];

      int i;
      for (i = 0; i < n; i++) {
         c += a[i] & 4294967295L;
         if (i < m) {
            c += b[i] & 4294967295L;
         }

         r[i] = (int)c;
         c >>= 32;
      }

      if (c != 0) {
         int[] s = new int[r.length + 1];
         System.arraycopy(r, 0, s, 0, r.length);
         s[i++] = (int)c;
         return new FDBigInt(s, i);
      } else {
         return new FDBigInt(r, i);
      }
   }

   public FDBigInt sub(FDBigInt other) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   public int cmp(FDBigInt other) {
      int i;
      if (this.nWords > other.nWords) {
         int j = other.nWords - 1;

         for (i = this.nWords - 1; i > j; i--) {
            if (this.data[i] != 0) {
               return 1;
            }
         }
      } else if (this.nWords < other.nWords) {
         int j = this.nWords - 1;

         for (i = other.nWords - 1; i > j; i--) {
            if (other.data[i] != 0) {
               return -1;
            }
         }
      } else {
         i = this.nWords - 1;
      }

      while (i > 0 && this.data[i] == other.data[i]) {
         i--;
      }

      int a = this.data[i];
      int b = other.data[i];
      if (a < 0) {
         return b < 0 ? a - b : 1;
      } else {
         return b < 0 ? -1 : a - b;
      }
   }

   public int quoRemIteration(FDBigInt S) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public long longValue() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
