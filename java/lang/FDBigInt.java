package java.lang;

class FDBigInt {
   int nWords;
   int[] data;

   public FDBigInt(int var1) {
      this.nWords = 1;
      this.data = new int[1];
      this.data[0] = var1;
   }

   public FDBigInt(long var1) {
   }

   public FDBigInt(FDBigInt var1) {
      this.data = new int[this.nWords = var1.nWords];
      System.arraycopy(var1.data, 0, this.data, 0, this.nWords);
   }

   private FDBigInt(int[] var1, int var2) {
      this.data = var1;
      this.nWords = var2;
   }

   public FDBigInt(long var1, char[] var3, int var4, int var5) {
   }

   public void lshiftMe(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public int normalizeMe() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public FDBigInt mult(int var1) {
      long var2 = var1;
      int[] var4 = new int[var2 * (this.data[this.nWords - 1] & 4294967295L) > 268435455 ? this.nWords + 1 : this.nWords];
      long var5 = 0;

      for (int var7 = 0; var7 < this.nWords; var7++) {
         var5 += var2 * (this.data[var7] & 4294967295L);
         var4[var7] = (int)var5;
         var5 >>>= 32;
      }

      if (var5 == 0) {
         return new FDBigInt(var4, this.nWords);
      }

      var4[this.nWords] = (int)var5;
      return new FDBigInt(var4, this.nWords + 1);
   }

   public void multaddMe(int var1, int var2) {
      long var3 = var1;
      long var5 = var3 * (this.data[0] & 4294967295L) + (var2 & 4294967295L);
      this.data[0] = (int)var5;
      var5 >>>= 32;

      for (int var7 = 1; var7 < this.nWords; var7++) {
         var5 += var3 * (this.data[var7] & 4294967295L);
         this.data[var7] = (int)var5;
         var5 >>>= 32;
      }

      if (var5 != 0) {
         this.data[this.nWords] = (int)var5;
         this.nWords++;
      }
   }

   public FDBigInt mult(FDBigInt var1) {
      int[] var2 = new int[this.nWords + var1.nWords];

      for (int var3 = 0; var3 < this.nWords; var3++) {
         long var4 = this.data[var3] & 4294967295L;
         long var6 = 0;

         int var8;
         for (var8 = 0; var8 < var1.nWords; var8++) {
            var6 += (var2[var3 + var8] & 4294967295L) + var4 * (var1.data[var8] & 4294967295L);
            var2[var3 + var8] = (int)var6;
            var6 >>>= 32;
         }

         var2[var3 + var8] = (int)var6;
      }

      int var9 = var2.length - 1;

      while (var9 > 0 && var2[var9] == 0) {
         var9--;
      }

      return new FDBigInt(var2, var9 + 1);
   }

   public FDBigInt add(FDBigInt var1) {
      long var7 = 0;
      int[] var3;
      int[] var4;
      int var5;
      int var6;
      if (this.nWords >= var1.nWords) {
         var3 = this.data;
         var5 = this.nWords;
         var4 = var1.data;
         var6 = var1.nWords;
      } else {
         var3 = var1.data;
         var5 = var1.nWords;
         var4 = this.data;
         var6 = this.nWords;
      }

      int[] var9 = new int[var5];

      int var2;
      for (var2 = 0; var2 < var5; var2++) {
         var7 += var3[var2] & 4294967295L;
         if (var2 < var6) {
            var7 += var4[var2] & 4294967295L;
         }

         var9[var2] = (int)var7;
         var7 >>= 32;
      }

      if (var7 != 0) {
         int[] var10 = new int[var9.length + 1];
         System.arraycopy(var9, 0, var10, 0, var9.length);
         var10[var2++] = (int)var7;
         return new FDBigInt(var10, var2);
      } else {
         return new FDBigInt(var9, var2);
      }
   }

   public FDBigInt sub(FDBigInt var1) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   public int cmp(FDBigInt var1) {
      int var2;
      if (this.nWords > var1.nWords) {
         int var3 = var1.nWords - 1;

         for (var2 = this.nWords - 1; var2 > var3; var2--) {
            if (this.data[var2] != 0) {
               return 1;
            }
         }
      } else if (this.nWords < var1.nWords) {
         int var5 = this.nWords - 1;

         for (var2 = var1.nWords - 1; var2 > var5; var2--) {
            if (var1.data[var2] != 0) {
               return -1;
            }
         }
      } else {
         var2 = this.nWords - 1;
      }

      while (var2 > 0 && this.data[var2] == var1.data[var2]) {
         var2--;
      }

      int var6 = this.data[var2];
      int var4 = var1.data[var2];
      if (var6 < 0) {
         return var4 < 0 ? var6 - var4 : 1;
      } else {
         return var4 < 0 ? -1 : var6 - var4;
      }
   }

   public int quoRemIteration(FDBigInt var1) {
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
