package java.lang;

class FloatingDecimal {
   boolean isExceptional;
   boolean isNegative;
   int decExponent;
   char[] digits;
   int nDigits;
   int bigIntExp;
   int bigIntNBits;
   boolean mustSetRoundDir;
   int roundDir;
   static final long signMask;
   static final long expMask;
   static final long fractMask;
   static final int expShift;
   static final int expBias;
   static final long fractHOB;
   static final long expOne;
   static final int maxSmallBinExp;
   static final int minSmallBinExp;
   static final int maxDecimalDigits;
   static final int maxDecimalExponent;
   static final int minDecimalExponent;
   static final int bigDecimalExponent;
   static final long highbyte;
   static final long highbit;
   static final long lowbytes;
   static final int singleSignMask;
   static final int singleExpMask;
   static final int singleFractMask;
   static final int singleExpShift;
   static final int singleFractHOB;
   static final int singleExpBias;
   static final int singleMaxDecimalDigits;
   static final int singleMaxDecimalExponent;
   static final int singleMinDecimalExponent;
   static final int intDecimalDigits;
   private static FDBigInt[] b5p;
   private static final double[] small10pow;
   private static final float[] singleSmall10pow;
   private static final double[] big10pow;
   private static final double[] tiny10pow;
   private static final int maxSmallTen;
   private static final int singleMaxSmallTen;
   private static final int[] small5pow;
   private static final long[] long5pow;
   private static final int[] n5bits;
   private static final char[] infinity;
   private static final char[] notANumber;
   private static final char[] zero;

   private FloatingDecimal(boolean var1, int var2, char[] var3, int var4, boolean var5) {
      this.mustSetRoundDir = false;
      this.isNegative = var1;
      this.isExceptional = var5;
      this.decExponent = var2;
      this.digits = var3;
      this.nDigits = var4;
   }

   private static int countBits(long var0) {
      if (var0 == 0) {
         return 0;
      }

      while ((var0 & -72057594037927936L) == 0) {
         var0 <<= 8;
      }

      while (var0 > 0) {
         var0 <<= 1;
      }

      int var2;
      for (var2 = 0; (var0 & 72057594037927935L) != 0; var2 += 8) {
         var0 <<= 8;
      }

      while (var0 != 0) {
         var0 <<= 1;
         var2++;
      }

      return var2;
   }

   private static synchronized FDBigInt big5pow(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static FDBigInt multPow52(FDBigInt var0, int var1, int var2) {
      if (var1 != 0) {
         if (var1 < small5pow.length) {
            var0 = var0.mult(small5pow[var1]);
         } else {
            var0 = var0.mult(big5pow(var1));
         }
      }

      if (var2 != 0) {
         var0.lshiftMe(var2);
      }

      return var0;
   }

   private static FDBigInt constructPow52(int var0, int var1) {
      FDBigInt var2 = new FDBigInt(big5pow(var0));
      if (var1 != 0) {
         var2.lshiftMe(var1);
      }

      return var2;
   }

   private FDBigInt doubleToBigInt(double var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static double ulp(double var0, boolean var2) {
      long var3 = Double.doubleToLongBits(var0) & Long.MAX_VALUE;
      int var5 = (int)(var3 >>> 52);
      if (var2 && var5 >= 52 && (var3 & 4503599627370495L) == 0) {
         var5--;
      }

      double var6;
      if (var5 > 52) {
         var6 = Double.longBitsToDouble((long)(var5 - 52) << 52);
      } else if (var5 == 0) {
         var6 = Double.MIN_VALUE;
      } else {
         var6 = Double.longBitsToDouble((long)1 << var5 - 1);
      }

      if (var2) {
         var6 = -var6;
      }

      return var6;
   }

   float stickyRound(double var1) {
      long var3 = Double.doubleToLongBits(var1);
      long var5 = var3 & 9218868437227405312L;
      if (var5 != 0 && var5 != 9218868437227405312L) {
         var3 += this.roundDir;
         return (float)Double.longBitsToDouble(var3);
      } else {
         return (float)var1;
      }
   }

   private void developLongDigits(int var1, long var2, long var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void roundup() {
      int var1;
      char var2 = this.digits[var1 = this.nDigits - 1];
      if (var2 == '9') {
         while (var2 == '9' && var1 > 0) {
            this.digits[var1] = '0';
            var2 = this.digits[--var1];
         }

         if (var2 == '9') {
            this.decExponent++;
            this.digits[0] = '1';
            return;
         }
      }

      this.digits[var1] = (char)(var2 + 1);
   }

   public FloatingDecimal(double var1) {
   }

   public FloatingDecimal(float var1) {
      this.mustSetRoundDir = false;
      int var2 = Float.floatToIntBits(var1);
      if ((var2 & -2147483648) != 0) {
         this.isNegative = true;
         var2 ^= Integer.MIN_VALUE;
      } else {
         this.isNegative = false;
      }

      int var4 = (var2 & 2139095040) >> 23;
      int var3 = var2 & 8388607;
      if (var4 == 255) {
         this.isExceptional = true;
         if (var3 == 0) {
            this.digits = infinity;
         } else {
            this.digits = notANumber;
            this.isNegative = false;
         }

         this.nDigits = this.digits.length;
      } else {
         this.isExceptional = false;
         int var5;
         if (var4 == 0) {
            if (var3 == 0) {
               this.decExponent = 0;
               this.digits = zero;
               this.nDigits = 1;
               return;
            }

            while ((var3 & 8388608) == 0) {
               var3 <<= 1;
               var4--;
            }

            var5 = 23 + var4 + 1;
            var4++;
         } else {
            var3 |= 8388608;
            var5 = 24;
         }

         var4 -= 127;
         this.dtoa(var4, (long)var3 << 29, var5);
      }
   }

   private void dtoa(int var1, long var2, int var4) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public String toJavaFormatString() {
      char[] var1 = new char[this.nDigits + 10];
      int var2 = 0;
      if (this.isNegative) {
         var1[0] = '-';
         var2 = 1;
      }

      if (this.isExceptional) {
         System.arraycopy(this.digits, 0, var1, var2, this.nDigits);
         var2 += this.nDigits;
      } else if (this.decExponent > 0 && this.decExponent < 8) {
         int var20 = Math.min(this.nDigits, this.decExponent);
         System.arraycopy(this.digits, 0, var1, var2, var20);
         var2 += var20;
         if (var20 < this.decExponent) {
            var20 = this.decExponent - var20;
            System.arraycopy(zero, 0, var1, var2, var20);
            var2 += var20;
            var1[var2++] = '.';
            var1[var2++] = '0';
         } else {
            var1[var2++] = '.';
            if (var20 < this.nDigits) {
               int var4 = this.nDigits - var20;
               System.arraycopy(this.digits, var20, var1, var2, var4);
               var2 += var4;
            } else {
               var1[var2++] = '0';
            }
         }
      } else if (this.decExponent <= 0 && this.decExponent > -3) {
         var1[var2++] = '0';
         var1[var2++] = '.';
         if (this.decExponent != 0) {
            System.arraycopy(zero, 0, var1, var2, -this.decExponent);
            var2 -= this.decExponent;
         }

         System.arraycopy(this.digits, 0, var1, var2, this.nDigits);
         var2 += this.nDigits;
      } else {
         var1[var2++] = this.digits[0];
         var1[var2++] = '.';
         if (this.nDigits > 1) {
            System.arraycopy(this.digits, 1, var1, var2, this.nDigits - 1);
            var2 += this.nDigits - 1;
         } else {
            var1[var2++] = '0';
         }

         var1[var2++] = 'E';
         int var3;
         if (this.decExponent <= 0) {
            var1[var2++] = '-';
            var3 = -this.decExponent + 1;
         } else {
            var3 = this.decExponent - 1;
         }

         if (var3 <= 9) {
            var1[var2++] = (char)(var3 + 48);
         } else if (var3 <= 99) {
            var1[var2++] = (char)(var3 / 10 + 48);
            var1[var2++] = (char)(var3 % 10 + 48);
         } else {
            var1[var2++] = (char)(var3 / 100 + 48);
            var3 %= 100;
            var1[var2++] = (char)(var3 / 10 + 48);
            var1[var2++] = (char)(var3 % 10 + 48);
         }
      }

      return (String)(new Object(var1, 0, var2));
   }

   public static FloatingDecimal readJavaFormatString(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public double doubleValue() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public float floatValue() {
      int var1 = Math.min(this.nDigits, 8);
      int var2 = this.digits[0] - '0';

      for (int var4 = 1; var4 < var1; var4++) {
         var2 = var2 * 10 + this.digits[var4] - 48;
      }

      float var3 = var2;
      int var14 = this.decExponent - var1;
      if (this.nDigits > 7) {
         if (this.decExponent >= this.nDigits && this.nDigits + this.decExponent <= 15) {
            long var17 = var2;

            for (int var7 = var1; var7 < this.nDigits; var7++) {
               var17 = var17 * 10 + (this.digits[var7] - '0');
            }

            double var18 = var17;
            var14 = this.decExponent - this.nDigits;
            var18 *= small10pow[var14];
            var3 = (float)var18;
            if (this.isNegative) {
               return -var3;
            }

            return var3;
         }
      } else {
         if (var14 == 0 || var3 == false) {
            if (this.isNegative) {
               return -var3;
            }

            return var3;
         }

         if (var14 >= 0) {
            if (var14 <= singleMaxSmallTen) {
               var3 *= singleSmall10pow[var14];
               if (this.isNegative) {
                  return -var3;
               }

               return var3;
            }

            int var5 = 7 - var1;
            if (var14 <= singleMaxSmallTen + var5) {
               var3 *= singleSmall10pow[var5];
               var3 *= singleSmall10pow[var14 - var5];
               if (this.isNegative) {
                  return -var3;
               }

               return var3;
            }
         } else if (var14 >= -singleMaxSmallTen) {
            var3 /= singleSmall10pow[-var14];
            if (this.isNegative) {
               return -var3;
            }

            return var3;
         }
      }

      if (this.decExponent > 39) {
         return (float)(this.isNegative ? -8388608 : 2139095040);
      }

      if (this.decExponent < -46) {
         return (float)(this.isNegative ? Integer.MIN_VALUE : 0);
      }

      this.mustSetRoundDir = true;
      double var16 = this.doubleValue();
      return this.stickyRound(var16);
   }
}
