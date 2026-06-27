package com.sun.cldc.i18n.j2me;

import com.sun.cldc.i18n.StreamReader;

public final class SMS_Reader extends StreamReader {
   private static char[][][] TABLES;

   @Override
   public final int read() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int read(char[] var1, int var2, int var3) {
      for (int var4 = 0; var4 < var3; var4++) {
         int var5 = this.read();
         if (var5 == -1) {
            if (var4 == 0) {
               return -1;
            }

            return var4;
         }

         var1[var2++] = (char)var5;
      }

      return var3;
   }

   @Override
   public final int sizeOf(byte[] var1, int var2, int var3) {
      int var4 = 0;
      int var5 = var2 + var3;

      while (var2 < var5) {
         int var6 = var1[var2] & 255;
         var2++;
         if (var6 != 27 && var6 != 13) {
            if (var6 == 255) {
               break;
            }

            var4++;
         }
      }

      return var4;
   }
}
