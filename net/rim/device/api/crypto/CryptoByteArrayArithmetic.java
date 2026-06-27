package net.rim.device.api.crypto;

import net.rim.device.api.util.Arrays;

public class CryptoByteArrayArithmetic {
   private static final int MAX_LENGTH;
   private static final byte[] ONE;

   private CryptoByteArrayArithmetic() {
   }

   public static boolean isZero(byte[] var0, int var1, int var2) {
      if (var0 != null && var0.length - var2 >= var1 && var1 >= 0 && var2 >= 0) {
         int var3 = var1 + var2;

         while (var1 < var3) {
            if (var0[var1++] != 0) {
               return false;
            }
         }

         return true;
      } else {
         throw new Object();
      }
   }

   public static boolean isZero(byte[] var0) {
      if (var0 == null) {
         throw new Object();
      } else {
         return isZero(var0, 0, var0.length);
      }
   }

   public static boolean isOne(byte[] var0, int var1, int var2) {
      if (var0 != null && var1 >= 0 && var2 >= 0 && var0.length - var2 >= var1) {
         int var3 = var1 + var2 - 1;
         if (var0[var3] != 1) {
            return false;
         }

         while (var1 < var3) {
            if (var0[var1++] != 0) {
               return false;
            }
         }

         return true;
      } else {
         throw new Object();
      }
   }

   public static boolean isOne(byte[] var0) {
      if (var0 == null) {
         throw new Object();
      } else {
         return isOne(var0, 0, var0.length);
      }
   }

   public static int findFirstNonZeroByte(byte[] var0, int var1, int var2) {
      if (var0 == null || var2 < 0 || var1 < 0 || var0.length - var2 < var1) {
         throw new Object();
      }

      if (var2 != 0 && var0.length != 0 && var1 <= var0.length) {
         int var3 = var1 + var2 - 1;
         int var4 = var1;

         while (var4 < var3 && var0[var4] == 0) {
            var4++;
         }

         return var4;
      } else {
         return -1;
      }
   }

   public static native int compare(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5);

   public static int compare(byte[] var0, byte[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void multiplyByTwo(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8);

   public static void multiplyByTwo(byte[] var0, byte[] var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void multiplyByTwo(byte[] var0, int var1, int var2, int var3, byte[] var4, int var5, int var6);

   public static void multiplyByTwo(byte[] var0, int var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void divideByTwo(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8);

   public static void divideByTwo(byte[] var0, byte[] var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void divideByTwo(byte[] var0, int var1, int var2, int var3, byte[] var4, int var5, int var6);

   public static void divideByTwo(byte[] var0, int var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void add(
      byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8, byte[] var9, int var10, int var11
   );

   public static void add(byte[] var0, byte[] var1, byte[] var2, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void add(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, int var6, byte[] var7, int var8, int var9);

   public static void add(byte[] var0, byte[] var1, int var2, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void subtract(
      byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8, byte[] var9, int var10, int var11
   );

   public static void subtract(byte[] var0, byte[] var1, byte[] var2, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void subtract(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, int var6, byte[] var7, int var8, int var9);

   public static void subtract(byte[] var0, byte[] var1, int var2, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void multiply(
      byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8, byte[] var9, int var10, int var11
   );

   public static void multiply(byte[] var0, byte[] var1, byte[] var2, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void multiply(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, int var6, byte[] var7, int var8, int var9);

   public static void multiply(byte[] var0, byte[] var1, int var2, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void square(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8);

   public static void square(byte[] var0, byte[] var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void square(byte[] var0, int var1, int var2, int var3, byte[] var4, int var5, int var6);

   public static void square(byte[] var0, int var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void mod(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8);

   public static void mod(byte[] var0, byte[] var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void mod(byte[] var0, int var1, int var2, int var3, byte[] var4, int var5, int var6);

   public static void mod(byte[] var0, int var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void invert(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8);

   public static void invert(byte[] var0, byte[] var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static native void gcd(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8);

   public static void gcd(byte[] var0, byte[] var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void exponent(
      byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8, byte[] var9, int var10, int var11
   ) {
      Certicom.assertAccessAllowed();
      exponent0(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
   }

   private static native void exponent0(
      byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8, byte[] var9, int var10, int var11
   );

   public static void exponent(byte[] var0, byte[] var1, byte[] var2, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void increment(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void increment(byte[] var0, byte[] var1, byte[] var2) {
      add(var0, ONE, var1, var2);
   }

   public static void increment(byte[] var0, int var1, int var2, int var3, byte[] var4, int var5, int var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void increment(byte[] var0, int var1, byte[] var2) {
      add(var0, ONE, var1, var2);
   }

   public static void decrement(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void decrement(byte[] var0, byte[] var1, byte[] var2) {
      subtract(var0, ONE, var1, var2);
   }

   public static void decrement(byte[] var0, int var1, int var2, int var3, byte[] var4, int var5, int var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void decrement(byte[] var0, int var1, byte[] var2) {
      subtract(var0, ONE, var1, var2);
   }

   public static native void divide(
      byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, byte[] var6, int var7, int var8, byte[] var9, int var10, int var11
   );

   public static void divide(byte[] var0, byte[] var1, byte[] var2, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static byte[] trim(byte[] var0) {
      if (var0 == null) {
         throw new Object();
      }

      int var1 = 0;

      for (int var2 = 0; var2 < var0.length && var0[var2] == 0; var2++) {
         var1++;
      }

      return var1 == 0 ? var0 : Arrays.copy(var0, var1, var0.length - var1);
   }

   public static byte[] pad(byte[] var0, int var1) {
      if (var0 == null || var1 < 0) {
         throw new Object();
      }

      if (var1 == 0) {
         return var0;
      }

      byte[] var2 = new byte[var0.length + var1];
      System.arraycopy(var0, 0, var2, var1, var0.length);
      return var2;
   }

   public static byte[] ensureLength(byte[] var0, int var1) {
      if (var0 != null && var1 >= 0) {
         if (var0.length < var1) {
            return pad(var0, var1 - var0.length);
         }

         if (var0.length > var1) {
            int var2 = var0.length - var1;

            for (int var3 = 0; var3 < var2; var3++) {
               if (var0[var3] != 0) {
                  throw new Object();
               }
            }

            var0 = Arrays.copy(var0, var2, var0.length - var2);
         }

         return var0;
      } else {
         throw new Object();
      }
   }

   public static byte[] valueOf(long var0) {
      if (var0 < 0) {
         throw new Object();
      }

      if (var0 == 0) {
         return new byte[1];
      }

      int var2 = 8;

      byte var3;
      for (var3 = 56; (var0 >>> var3 & 255) == 0; var3 -= 8) {
         var2--;
      }

      byte[] var4 = new byte[var2];

      for (int var5 = 0; var5 < var2; var5++) {
         var4[var5] = (byte)(var0 >>> var3);
         var3 -= 8;
      }

      return var4;
   }

   public static long valueOf(byte[] var0) {
      var0 = trim(var0);
      int var1 = var0.length;
      if (var1 > 8) {
         throw new Object();
      }

      long var2 = 0;

      for (int var4 = 0; var4 < var1; var4++) {
         var2 <<= 8;
         var2 += var0[var4] & 0xFF;
      }

      return var2;
   }

   public static byte[] createArray(int var0) {
      if (var0 < 0) {
         throw new Object();
      }

      byte[] var1 = new byte[(var0 + 8) / 8];
      var1[0] = (byte)(1 << var0 % 8);
      return var1;
   }

   public static int getNumBits(byte[] var0) {
      if (var0 == null) {
         throw new Object();
      }

      int var1 = var0.length;
      int var2 = 0;

      for (int var3 = 0; var3 < var1; var2 += 8) {
         byte var4 = var0[var3];
         if (var4 != 0) {
            while ((var4 & 128) == 0) {
               var2++;
               var4 = (byte)(var4 << 1);
            }
            break;
         }

         var3++;
      }

      return var1 * 8 - var2;
   }
}
