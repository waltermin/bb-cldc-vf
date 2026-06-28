package java.lang;

class FloatingDecimal {
   boolean isExceptional;
   boolean isNegative;
   int decExponent;
   char[] digits;
   int nDigits;
   int bigIntExp;
   int bigIntNBits;
   boolean mustSetRoundDir = false;
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
      if (p < 0) {
         throw new RuntimeException("Assertion botch: negative power of 5");
      }

      if (b5p == null) {
         b5p = new FDBigInt[p + 1];
      } else if (b5p.length <= p) {
         FDBigInt[] t = new FDBigInt[p + 1];
         System.arraycopy(b5p, 0, t, 0, b5p.length);
         b5p = t;
      }

      if (b5p[p] != null) {
         return b5p[p];
      }

      if (p < small5pow.length) {
         return b5p[p] = new FDBigInt(small5pow[p]);
      }

      if (p < long5pow.length) {
         return b5p[p] = new FDBigInt(long5pow[p]);
      }

      int q = p >> 1;
      int r = p - q;
      FDBigInt bigq = b5p[q];
      if (bigq == null) {
         bigq = big5pow(q);
      }

      if (r < small5pow.length) {
         return b5p[p] = bigq.mult(small5pow[r]);
      }

      FDBigInt bigr = b5p[r];
      if (bigr == null) {
         bigr = big5pow(r);
      }

      return b5p[p] = bigq.mult(bigr);
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
      long lbits = Double.doubleToLongBits(dval) & Long.MAX_VALUE;
      int binexp = (int)(lbits >>> 52);
      lbits &= 4503599627370495L;
      if (binexp > 0) {
         lbits |= 4503599627370496L;
      } else {
         if (lbits == 0) {
            throw new RuntimeException("Assertion botch: doubleToBigInt(0.0)");
         }

         binexp++;

         while ((lbits & 4503599627370496L) == 0) {
            lbits <<= 1;
            binexp--;
         }
      }

      binexp -= 1023;
      int nbits = countBits(lbits);
      int lowOrderZeros = 53 - nbits;
      lbits >>>= lowOrderZeros;
      this.bigIntExp = binexp + 1 - nbits;
      this.bigIntNBits = nbits;
      return new FDBigInt(lbits);
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
      int i;
      for (i = 0; insignificant >= 10; i++) {
         insignificant /= 10;
      }

      if (i != 0) {
         long pow10 = long5pow[i] << i;
         long residue = lvalue % pow10;
         lvalue /= pow10;
         decExponent += i;
         if (residue >= pow10 >> 1) {
            lvalue += 1;
         }
      }

      char[] digits;
      int ndigits;
      int digitno;
      if (lvalue > Integer.MAX_VALUE) {
         ndigits = 20;
         digits = new char[20];
         digitno = ndigits - 1;
         int c = (int)(lvalue % 10);

         for (lvalue /= 10; c == 0; lvalue /= 10) {
            decExponent++;
            c = (int)(lvalue % 10);
         }

         while (lvalue != 0) {
            digits[digitno--] = (char)(c + 48);
            decExponent++;
            c = (int)(lvalue % 10);
            lvalue /= 10;
         }

         digits[digitno] = (char)(c + 48);
      } else {
         if (lvalue <= 0) {
            throw new RuntimeException("Assertion botch: value " + lvalue + " <= 0");
         }

         int ivalue = (int)lvalue;
         ndigits = 10;
         digits = new char[10];
         digitno = ndigits - 1;
         int c = ivalue % 10;

         for (ivalue /= 10; c == 0; ivalue /= 10) {
            decExponent++;
            c = ivalue % 10;
         }

         while (ivalue != 0) {
            digits[digitno--] = (char)(c + 48);
            decExponent++;
            c = ivalue % 10;
            ivalue /= 10;
         }

         digits[digitno] = (char)(c + 48);
      }

      ndigits -= digitno;
      char[] result;
      if (digitno == 0) {
         result = digits;
      } else {
         result = new char[ndigits];
         System.arraycopy(digits, digitno, result, 0, ndigits);
      }

      this.digits = result;
      this.decExponent = decExponent + 1;
      this.nDigits = ndigits;
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
      long dBits = Double.doubleToLongBits(d);
      if ((dBits & Long.MIN_VALUE) != 0) {
         this.isNegative = true;
         dBits ^= Long.MIN_VALUE;
      } else {
         this.isNegative = false;
      }

      int binExp = (int)((dBits & 9218868437227405312L) >> 52);
      long fractBits = dBits & 4503599627370495L;
      if (binExp == 2047) {
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

            while ((fractBits & 4503599627370496L) == 0) {
               fractBits <<= 1;
               binExp--;
            }

            nSignificantBits = 52 + binExp + 1;
            binExp++;
         } else {
            fractBits |= 4503599627370496L;
            nSignificantBits = 53;
         }

         binExp -= 1023;
         this.dtoa(binExp, fractBits, nSignificantBits);
      }
   }

   public FloatingDecimal(float f) {
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
      StringBuffer result = new StringBuffer(this.nDigits + 8);
      if (this.isNegative) {
         result.append('-');
      }

      if (this.isExceptional) {
         result.append(this.digits, 0, this.nDigits);
      } else {
         result.append("0.");
         result.append(this.digits, 0, this.nDigits);
         result.append('e');
         result.append(this.decExponent);
      }

      return new String(result);
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

      return new String(result, 0, i);
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
