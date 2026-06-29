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
      int nFractBits = countBits(fractBits);
      int nTinyBits = Math.max(0, nFractBits - binExp - 1);
      if (binExp <= 62 && binExp >= -21 && nTinyBits < long5pow.length && nFractBits + n5bits[nTinyBits] < 64 && nTinyBits == 0) {
         long halfULP;
         if (binExp > nSignificantBits) {
            halfULP = (long)1 << binExp - nSignificantBits - 1;
         } else {
            halfULP = 0;
         }

         if (binExp >= 52) {
            fractBits <<= binExp - 52;
         } else {
            fractBits >>>= 52 - binExp;
         }

         this.developLongDigits(0, fractBits, halfULP);
      } else {
         double d2 = Double.longBitsToDouble(4607182418800017408L | fractBits & -4503599627370497L);
         int decExp = (int)Math.floor((d2 - 4609434218613702656L) * 4598887322485374355L + 4595512376517860236L + binExp * 4599094494223104507L);
         int B5 = Math.max(0, -decExp);
         int B2 = B5 + nTinyBits + binExp;
         int S5 = Math.max(0, decExp);
         int S2 = S5 + nTinyBits;
         int M5 = B5;
         int M2 = B2 - nSignificantBits;
         fractBits >>>= 53 - nFractBits;
         B2 -= nFractBits - 1;
         int common2factor = Math.min(B2, S2);
         B2 -= common2factor;
         S2 -= common2factor;
         M2 -= common2factor;
         if (nFractBits == 1) {
            M2--;
         }

         if (M2 < 0) {
            B2 -= M2;
            S2 -= M2;
            M2 = 0;
         }

         char[] digits = this.digits = new char[18];
         int ndigit = 0;
         int Bbits = nFractBits + B2 + (B5 < n5bits.length ? n5bits[B5] : B5 * 3);
         int tenSbits = S2 + 1 + (S5 + 1 < n5bits.length ? n5bits[S5 + 1] : (S5 + 1) * 3);
         boolean low;
         boolean high;
         long lowDigitDifference;
         if (Bbits >= 64 || tenSbits >= 64) {
            FDBigInt Bval = multPow52(new FDBigInt(fractBits), B5, B2);
            FDBigInt Sval = constructPow52(S5, S2);
            FDBigInt Mval = constructPow52(M5, M2);
            int shiftBias;
            Bval.lshiftMe(shiftBias = Sval.normalizeMe());
            Mval.lshiftMe(shiftBias);
            FDBigInt tenSval = Sval.mult(10);
            ndigit = 0;
            int q = Bval.quoRemIteration(Sval);
            Mval = Mval.mult(10);
            low = Bval.cmp(Mval) < 0;
            high = Bval.add(Mval).cmp(tenSval) > 0;
            if (q >= 10) {
               throw new RuntimeException("Assertion botch: excessivly large digit " + q);
            }

            if (q == 0 && !high) {
               decExp--;
            } else {
               digits[ndigit++] = (char)(48 + q);
            }

            if (decExp <= -3 || decExp >= 8) {
               low = false;
               high = false;
            }

            while (!low && !high) {
               q = Bval.quoRemIteration(Sval);
               Mval = Mval.mult(10);
               if (q >= 10) {
                  throw new RuntimeException("Assertion botch: excessivly large digit " + q);
               }

               low = Bval.cmp(Mval) < 0;
               high = Bval.add(Mval).cmp(tenSval) > 0;
               digits[ndigit++] = (char)(48 + q);
            }

            if (high && low) {
               Bval.lshiftMe(1);
               lowDigitDifference = Bval.cmp(tenSval);
            } else {
               lowDigitDifference = 0;
            }
         } else if (Bbits < 32 && tenSbits < 32) {
            int b = (int)fractBits * small5pow[B5] << B2;
            int s = small5pow[S5] << S2;
            int m = small5pow[M5] << M2;
            int tens = s * 10;
            ndigit = 0;
            int q = b / s;
            b = 10 * (b % s);
            m *= 10;
            low = b < m;
            high = b + m > tens;
            if (q >= 10) {
               throw new RuntimeException("Assertion botch: excessivly large digit " + q);
            }

            if (q == 0 && !high) {
               decExp--;
            } else {
               digits[ndigit++] = (char)(48 + q);
            }

            if (decExp <= -3 || decExp >= 8) {
               low = false;
               high = false;
            }

            while (!low && !high) {
               q = b / s;
               b = 10 * (b % s);
               m *= 10;
               if (q >= 10) {
                  throw new RuntimeException("Assertion botch: excessivly large digit " + q);
               }

               if (m > 0) {
                  low = b < m;
                  high = b + m > tens;
               } else {
                  low = true;
                  high = true;
               }

               digits[ndigit++] = (char)(48 + q);
            }

            lowDigitDifference = (b << 1) - tens;
         } else {
            long b = fractBits * long5pow[B5] << B2;
            long s = long5pow[S5] << S2;
            long m = long5pow[M5] << M2;
            long tens = s * 10;
            ndigit = 0;
            int q = (int)(b / s);
            b = 10 * (b % s);
            m *= 10;
            low = b < m;
            high = b + m > tens;
            if (q >= 10) {
               throw new RuntimeException("Assertion botch: excessivly large digit " + q);
            }

            if (q == 0 && !high) {
               decExp--;
            } else {
               digits[ndigit++] = (char)(48 + q);
            }

            if (decExp <= -3 || decExp >= 8) {
               low = false;
               high = false;
            }

            while (!low && !high) {
               q = (int)(b / s);
               b = 10 * (b % s);
               m *= 10;
               if (q >= 10) {
                  throw new RuntimeException("Assertion botch: excessivly large digit " + q);
               }

               if (m > 0) {
                  low = b < m;
                  high = b + m > tens;
               } else {
                  low = true;
                  high = true;
               }

               digits[ndigit++] = (char)(48 + q);
            }

            lowDigitDifference = (b << 1) - tens;
         }

         this.decExponent = decExp + 1;
         this.digits = digits;
         this.nDigits = ndigit;
         if (high) {
            if (low) {
               if (lowDigitDifference == 0) {
                  if ((digits[this.nDigits - 1] & 1) != 0) {
                     this.roundup();
                     return;
                  }
               } else if (lowDigitDifference > 0) {
                  this.roundup();
                  return;
               }
            } else {
               this.roundup();
            }
         }
      }
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
      boolean isNegative = false;
      boolean signSeen = false;

      try {
         in = in.trim();
         int l = in.length();
         if (l == 0) {
            throw new NumberFormatException("empty String");
         }

         int i = 0;
         char c;
         char[] digits;
         int nDigits;
         boolean decSeen;
         int decPt;
         int nLeadZero;
         int nTrailZero;
         switch (c = in.charAt(i)) {
            case '-':
               isNegative = true;
            case '+':
               i++;
               signSeen = true;
            default:
               digits = new char[l];
               nDigits = 0;
               decSeen = false;
               decPt = 0;
               nLeadZero = 0;
               nTrailZero = 0;
         }

         label92:
         for (; i < l; i++) {
            switch (c = in.charAt(i)) {
               case '-':
               case '/':
                  break label92;
               case '.':
                  if (decSeen) {
                     throw new NumberFormatException("multiple points");
                  }

                  decPt = i;
                  if (signSeen) {
                     decPt--;
                  }

                  decSeen = true;
                  continue;
               case '0':
               default:
                  if (nDigits > 0) {
                     nTrailZero++;
                  } else {
                     nLeadZero++;
                  }
                  continue;
               case '1':
               case '2':
               case '3':
               case '4':
               case '5':
               case '6':
               case '7':
               case '8':
               case '9':
            }

            while (nTrailZero > 0) {
               digits[nDigits++] = '0';
               nTrailZero--;
            }

            digits[nDigits++] = c;
         }

         if (nDigits == 0) {
            digits = zero;
            nDigits = 1;
            if (nLeadZero == 0) {
               throw new NumberFormatException(in);
            }
         }

         int decExp;
         if (decSeen) {
            decExp = decPt - nLeadZero;
         } else {
            decExp = nDigits + nTrailZero;
         }

         if (i < l && (c = in.charAt(i)) == 'e' || c == 'E') {
            int expSign = 1;
            int expVal = 0;
            int reallyBig = 214748364;
            boolean expOverflow = false;
            i++;
            int expAt;
            switch (in.charAt(i)) {
               case '-':
                  expSign = -1;
               case '+':
                  i++;
               default:
                  expAt = i;
            }

            label117:
            while (i < l) {
               if (expVal >= reallyBig) {
                  expOverflow = true;
               }

               switch (c = in.charAt(i++)) {
                  case '/':
                     i--;
                     break label117;
                  case '0':
                  case '1':
                  case '2':
                  case '3':
                  case '4':
                  case '5':
                  case '6':
                  case '7':
                  case '8':
                  case '9':
                  default:
                     expVal = expVal * 10 + (c - '0');
               }
            }

            int expLimit = 324 + nDigits + nTrailZero;
            if (!expOverflow && expVal <= expLimit) {
               decExp += expSign * expVal;
            } else {
               decExp = expSign * expLimit;
            }

            if (i == expAt) {
               throw new NumberFormatException(in);
            }
         }

         if (i >= l || i == l - 1 && (in.charAt(i) == 'f' || in.charAt(i) == 'F' || in.charAt(i) == 'd' || in.charAt(i) == 'D')) {
            return new FloatingDecimal(isNegative, decExp, digits, nDigits, false);
         }
      } catch (StringIndexOutOfBoundsException var19) {
      }

      throw new NumberFormatException(in);
   }

   public double doubleValue() {
      int kDigits = Math.min(this.nDigits, 16);
      this.roundDir = 0;
      int iValue = this.digits[0] - '0';
      int iDigits = Math.min(kDigits, 9);

      for (int i = 1; i < iDigits; i++) {
         iValue = iValue * 10 + this.digits[i] - 48;
      }

      long lValue = iValue;

      for (int i = iDigits; i < kDigits; i++) {
         lValue = lValue * 10 + (this.digits[i] - '0');
      }

      double dValue = lValue;
      int exp = this.decExponent - kDigits;
      if (this.nDigits <= 15) {
         if (exp == 0 || dValue == 0L) {
            return this.isNegative ? -dValue : dValue;
         }

         if (exp < 0) {
            if (exp >= -maxSmallTen) {
               double rValue = dValue / small10pow[-exp];
               double tValue = rValue * small10pow[-exp];
               if (this.mustSetRoundDir) {
                  this.roundDir = tValue == dValue ? 0 : (tValue < dValue ? 1 : -1);
               }

               if (this.isNegative) {
                  return -rValue;
               }

               return rValue;
            }
         } else {
            if (exp <= maxSmallTen) {
               double rValue = dValue * small10pow[exp];
               if (this.mustSetRoundDir) {
                  double tValue = rValue / small10pow[exp];
                  this.roundDir = tValue == dValue ? 0 : (tValue < dValue ? 1 : -1);
               }

               if (this.isNegative) {
                  return -rValue;
               }

               return rValue;
            }

            int slop = 15 - kDigits;
            if (exp <= maxSmallTen + slop) {
               dValue *= small10pow[slop];
               double rValue = dValue * small10pow[exp - slop];
               if (this.mustSetRoundDir) {
                  double tValue = rValue / small10pow[exp - slop];
                  this.roundDir = tValue == dValue ? 0 : (tValue < dValue ? 1 : -1);
               }

               if (this.isNegative) {
                  return -rValue;
               }

               return rValue;
            }
         }
      }

      if (exp <= 0) {
         if (exp < 0) {
            exp = -exp;
            if (this.decExponent < -325) {
               if (this.isNegative) {
                  return (double)Long.MIN_VALUE;
               }

               return (double)0L;
            }

            if ((exp & 15) != 0) {
               dValue /= small10pow[exp & 15];
            }

            if ((exp = exp >> 4) != 0) {
               int j = 0;

               while (exp > 1) {
                  if ((exp & 1) != 0) {
                     dValue *= tiny10pow[j];
                  }

                  j++;
                  exp >>= 1;
               }

               double t = dValue * tiny10pow[j];
               if (t == 0L) {
                  t = dValue * 4611686018427387904L;
                  t *= tiny10pow[j];
                  if (t == 0L) {
                     if (this.isNegative) {
                        return (double)Long.MIN_VALUE;
                     }

                     return (double)0L;
                  }

                  t = Double.MIN_VALUE;
               }

               dValue = t;
            }
         }
      } else {
         if (this.decExponent > 309) {
            if (this.isNegative) {
               return (double)-4503599627370496L;
            }

            return (double)9218868437227405312L;
         }

         if ((exp & 15) != 0) {
            dValue *= small10pow[exp & 15];
         }

         if ((exp = exp >> 4) != 0) {
            int j = 0;

            while (exp > 1) {
               if ((exp & 1) != 0) {
                  dValue *= big10pow[j];
               }

               j++;
               exp >>= 1;
            }

            double t = dValue * big10pow[j];
            if (Double.isInfinite(t)) {
               t = dValue / 4611686018427387904L;
               t *= big10pow[j];
               if (Double.isInfinite(t)) {
                  if (this.isNegative) {
                     return (double)-4503599627370496L;
                  }

                  return (double)9218868437227405312L;
               }

               t = (double)9218868437227405311L;
            }

            dValue = t;
         }
      }

      FDBigInt bigD0 = new FDBigInt(lValue, this.digits, kDigits, this.nDigits);
      exp = this.decExponent - this.nDigits;

      do {
         FDBigInt bigB = this.doubleToBigInt(dValue);
         int B2;
         int B5;
         int D2;
         int D5;
         if (exp >= 0) {
            B5 = 0;
            B2 = 0;
            D5 = exp;
            D2 = exp;
         } else {
            B2 = B5 = -exp;
            D5 = 0;
            D2 = 0;
         }

         if (this.bigIntExp >= 0) {
            B2 += this.bigIntExp;
         } else {
            D2 -= this.bigIntExp;
         }

         int Ulp2 = B2;
         int hulpbias;
         if (this.bigIntExp + this.bigIntNBits <= -1022) {
            hulpbias = this.bigIntExp + 1023 + 52;
         } else {
            hulpbias = 54 - this.bigIntNBits;
         }

         B2 += hulpbias;
         D2 += hulpbias;
         int common2 = Math.min(B2, Math.min(D2, Ulp2));
         B2 -= common2;
         D2 -= common2;
         Ulp2 -= common2;
         bigB = multPow52(bigB, B5, B2);
         FDBigInt bigD = multPow52(new FDBigInt(bigD0), D5, D2);
         FDBigInt diff;
         int cmpResult;
         boolean overvalue;
         if ((cmpResult = bigB.cmp(bigD)) > 0) {
            overvalue = true;
            diff = bigB.sub(bigD);
            if (this.bigIntNBits == 1 && this.bigIntExp > -1023) {
               if (--Ulp2 < 0) {
                  Ulp2 = 0;
                  diff.lshiftMe(1);
               }
            }
         } else {
            if (cmpResult >= 0) {
               break;
            }

            overvalue = false;
            diff = bigD.sub(bigB);
         }

         FDBigInt halfUlp = constructPow52(B5, Ulp2);
         if ((cmpResult = diff.cmp(halfUlp)) < 0) {
            this.roundDir = overvalue ? -1 : 1;
            break;
         }

         if (cmpResult == 0) {
            dValue += 4602678819172646912L * ulp(dValue, overvalue);
            this.roundDir = overvalue ? -1 : 1;
            break;
         }

         dValue += ulp(dValue, overvalue);
      } while (dValue != 0L && dValue != 9218868437227405312L);

      return this.isNegative ? -dValue : dValue;
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
