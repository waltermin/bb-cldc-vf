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

   private FloatingDecimal(boolean negSign, int decExponent, char[] digits, int n, boolean e) {
      this.mustSetRoundDir = false;
      this.isNegative = negSign;
      this.isExceptional = e;
      this.decExponent = decExponent;
      this.digits = digits;
      this.nDigits = n;
   }

   private static int countBits(long v) {
      if (v == 0) {
         return 0;
      }

      while ((v & -72057594037927936L) == 0) {
         v <<= 8;
      }

      while (v > 0) {
         v <<= 1;
      }

      int n;
      for (n = 0; (v & 72057594037927935L) != 0; n += 8) {
         v <<= 8;
      }

      while (v != 0) {
         v <<= 1;
         n++;
      }

      return n;
   }

   private static synchronized FDBigInt big5pow(int p) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static FDBigInt multPow52(FDBigInt v, int p5, int p2) {
      if (p5 != 0) {
         if (p5 < small5pow.length) {
            v = v.mult(small5pow[p5]);
         } else {
            v = v.mult(big5pow(p5));
         }
      }

      if (p2 != 0) {
         v.lshiftMe(p2);
      }

      return v;
   }

   private static FDBigInt constructPow52(int p5, int p2) {
      FDBigInt v = new FDBigInt(big5pow(p5));
      if (p2 != 0) {
         v.lshiftMe(p2);
      }

      return v;
   }

   private FDBigInt doubleToBigInt(double dval) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static double ulp(double dval, boolean subtracting) {
      long lbits = Double.doubleToLongBits(dval) & Long.MAX_VALUE;
      int binexp = (int)(lbits >>> 52);
      if (subtracting && binexp >= 52 && (lbits & 4503599627370495L) == 0) {
         binexp--;
      }

      double ulpval;
      if (binexp > 52) {
         ulpval = Double.longBitsToDouble((long)(binexp - 52) << 52);
      } else if (binexp == 0) {
         ulpval = Double.MIN_VALUE;
      } else {
         ulpval = Double.longBitsToDouble((long)1 << binexp - 1);
      }

      if (subtracting) {
         ulpval = -ulpval;
      }

      return ulpval;
   }

   float stickyRound(double dval) {
      long lbits = Double.doubleToLongBits(dval);
      long binexp = lbits & 9218868437227405312L;
      if (binexp != 0 && binexp != 9218868437227405312L) {
         lbits += this.roundDir;
         return (float)Double.longBitsToDouble(lbits);
      } else {
         return (float)dval;
      }
   }

   private void developLongDigits(int decExponent, long lvalue, long insignificant) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void roundup() {
      int i;
      int q = this.digits[i = this.nDigits - 1];
      if (q == 57) {
         while (q == 57 && i > 0) {
            this.digits[i] = '0';
            q = this.digits[--i];
         }

         if (q == 57) {
            this.decExponent++;
            this.digits[0] = '1';
            return;
         }
      }

      this.digits[i] = (char)(q + 1);
   }

   public FloatingDecimal(double d) {
   }

   public FloatingDecimal(float f) {
      this.mustSetRoundDir = false;
      int fBits = Float.floatToIntBits(f);
      if ((fBits & -2147483648) != 0) {
         this.isNegative = true;
         fBits ^= Integer.MIN_VALUE;
      } else {
         this.isNegative = false;
      }

      int binExp = (fBits & 2139095040) >> 23;
      int fractBits = fBits & 8388607;
      if (binExp == 255) {
         this.isExceptional = true;
         if (fractBits == 0) {
            this.digits = infinity;
         } else {
            this.digits = notANumber;
            this.isNegative = false;
         }

         this.nDigits = this.digits.length;
      } else {
         this.isExceptional = false;
         int nSignificantBits;
         if (binExp == 0) {
            if (fractBits == 0) {
               this.decExponent = 0;
               this.digits = zero;
               this.nDigits = 1;
               return;
            }

            while ((fractBits & 8388608) == 0) {
               fractBits <<= 1;
               binExp--;
            }

            nSignificantBits = 23 + binExp + 1;
            binExp++;
         } else {
            fractBits |= 8388608;
            nSignificantBits = 24;
         }

         binExp -= 127;
         this.dtoa(binExp, (long)fractBits << 29, nSignificantBits);
      }
   }

   private void dtoa(int binExp, long fractBits, int nSignificantBits) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public String toJavaFormatString() {
      char[] result = new char[this.nDigits + 10];
      int i = 0;
      if (this.isNegative) {
         result[0] = '-';
         i = 1;
      }

      if (this.isExceptional) {
         System.arraycopy(this.digits, 0, result, i, this.nDigits);
         i += this.nDigits;
      } else if (this.decExponent > 0 && this.decExponent < 8) {
         int charLength = Math.min(this.nDigits, this.decExponent);
         System.arraycopy(this.digits, 0, result, i, charLength);
         i += charLength;
         if (charLength < this.decExponent) {
            charLength = this.decExponent - charLength;
            System.arraycopy(zero, 0, result, i, charLength);
            i += charLength;
            result[i++] = '.';
            result[i++] = '0';
         } else {
            result[i++] = '.';
            if (charLength < this.nDigits) {
               int t = this.nDigits - charLength;
               System.arraycopy(this.digits, charLength, result, i, t);
               i += t;
            } else {
               result[i++] = '0';
            }
         }
      } else if (this.decExponent <= 0 && this.decExponent > -3) {
         result[i++] = '0';
         result[i++] = '.';
         if (this.decExponent != 0) {
            System.arraycopy(zero, 0, result, i, -this.decExponent);
            i -= this.decExponent;
         }

         System.arraycopy(this.digits, 0, result, i, this.nDigits);
         i += this.nDigits;
      } else {
         result[i++] = this.digits[0];
         result[i++] = '.';
         if (this.nDigits > 1) {
            System.arraycopy(this.digits, 1, result, i, this.nDigits - 1);
            i += this.nDigits - 1;
         } else {
            result[i++] = '0';
         }

         result[i++] = 'E';
         int e;
         if (this.decExponent <= 0) {
            result[i++] = '-';
            e = -this.decExponent + 1;
         } else {
            e = this.decExponent - 1;
         }

         if (e <= 9) {
            result[i++] = (char)(e + 48);
         } else if (e <= 99) {
            result[i++] = (char)(e / 10 + 48);
            result[i++] = (char)(e % 10 + 48);
         } else {
            result[i++] = (char)(e / 100 + 48);
            e %= 100;
            result[i++] = (char)(e / 10 + 48);
            result[i++] = (char)(e % 10 + 48);
         }
      }

      return (String)(new Object(result, 0, i));
   }

   public static FloatingDecimal readJavaFormatString(String in) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public double doubleValue() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public float floatValue() {
      int kDigits = Math.min(this.nDigits, 8);
      int iValue = this.digits[0] - '0';

      for (int i = 1; i < kDigits; i++) {
         iValue = iValue * 10 + this.digits[i] - 48;
      }

      float fValue = iValue;
      int exp = this.decExponent - kDigits;
      if (this.nDigits > 7) {
         if (this.decExponent >= this.nDigits && this.nDigits + this.decExponent <= 15) {
            long lValue = iValue;

            for (int i = kDigits; i < this.nDigits; i++) {
               lValue = lValue * 10 + (this.digits[i] - '0');
            }

            double dValue = lValue;
            exp = this.decExponent - this.nDigits;
            dValue *= small10pow[exp];
            fValue = (float)dValue;
            if (this.isNegative) {
               return -fValue;
            }

            return fValue;
         }
      } else {
         if (exp == 0 || fValue == false) {
            if (this.isNegative) {
               return -fValue;
            }

            return fValue;
         }

         if (exp >= 0) {
            if (exp <= singleMaxSmallTen) {
               fValue *= singleSmall10pow[exp];
               if (this.isNegative) {
                  return -fValue;
               }

               return fValue;
            }

            int slop = 7 - kDigits;
            if (exp <= singleMaxSmallTen + slop) {
               fValue *= singleSmall10pow[slop];
               fValue *= singleSmall10pow[exp - slop];
               if (this.isNegative) {
                  return -fValue;
               }

               return fValue;
            }
         } else if (exp >= -singleMaxSmallTen) {
            fValue /= singleSmall10pow[-exp];
            if (this.isNegative) {
               return -fValue;
            }

            return fValue;
         }
      }

      if (this.decExponent > 39) {
         return (float)(this.isNegative ? -8388608 : 2139095040);
      }

      if (this.decExponent < -46) {
         return (float)(this.isNegative ? Integer.MIN_VALUE : 0);
      }

      this.mustSetRoundDir = true;
      double dValue = this.doubleValue();
      return this.stickyRound(dValue);
   }
}
