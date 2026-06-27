package com.sun.cldc.i18n.j2me;

class TranscodingGateway {
   static String ASCII;
   static String ISO8859_1;

   static int sizeOf(int var0, int var1, int var2, boolean var3) {
      if (var3) {
         if (var0 == 0 || var0 == 1) {
            if (var1 > var2) {
               return var2;
            }

            return var1;
         }
      } else {
         if (var0 >= 0 && var0 <= 26) {
            if (var1 > var2) {
               return var2;
            }

            return var1;
         }

         switch (var0) {
            case 28:
            case 39:
               var1 *= 2;
               if (var1 <= var2) {
                  return var1;
               }

               return var2 - (var2 & 1);
         }
      }

      return -1;
   }

   static native int L2U(int var0, byte[] var1, int var2, int var3, Object var4, int var5, int var6, int[] var7, byte[] var8, int var9);

   static native int U2L(int var0, char[] var1, int var2, int var3, byte[] var4, int var5, int var6, int[] var7, byte[] var8, int var9);
}
