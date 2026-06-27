package net.rim.tid.im.conv.jp.util;

import net.rim.device.api.util.Arrays;

public class KanaConversionUtils implements ISLJpConst {
   protected static final char[] hira2UNICODE;
   protected static final char[] hiraToKatakanaHalf;
   protected static final int[] hiraganaNigori;
   protected static final int[] hiraganaMaru;

   public static int kanaToHalfWidth(StringBuffer var0, int var1, int var2, StringBuffer var3) {
      return kanaToHalfWidth(var0, var1, var2, var3, false);
   }

   public static int kanaToHalfWidth(StringBuffer var0, int var1, int var2, StringBuffer var3, boolean var4) {
      return kanaToHalfWidth(var0, var1, var2, var3, null, null, var4);
   }

   public static int kanaToHalfWidth(
      StringBuffer var0,
      int var1,
      int var2,
      StringBuffer var3,
      KanaConversionUtils$ConversionFilter var4,
      KanaConversionUtils$ConversionFilter var5,
      boolean var6
   ) {
      if (var0 != null && var0.length() > var1 && var3 != null) {
         int var7 = var0.length();
         if (var1 + var2 < var7) {
            var7 = var1 + var2;
         }

         int var12 = 0;

         for (int var13 = var1; var13 < var7; var13++) {
            char var9 = var0.charAt(var13);
            if (var4 == null || var4.accept(var9)) {
               int var8;
               if (12353 <= var9 && var9 <= 12435) {
                  var8 = var9 - 12352;
               } else if (12449 <= var9 && var9 <= 12531) {
                  var8 = var9 - 12448;
               } else if (var9 == 12532) {
                  var8 = 84;
               } else if (var9 == 12539) {
                  var8 = 97;
               } else {
                  if (var9 != 12540) {
                     if ((var5 == null || var5.accept(var9)) && !var6) {
                        var3.append(var9);
                     }
                     continue;
                  }

                  var8 = 92;
               }

               char var10 = 0;
               if (Arrays.getIndex(hiraganaNigori, var8) >= 0) {
                  if (var8 == 84) {
                     var8 = 6;
                  } else {
                     var8--;
                  }

                  var10 = 'ﾞ';
               } else if (Arrays.getIndex(hiraganaMaru, var8) >= 0) {
                  var8 -= 2;
                  var10 = 'ﾟ';
               }

               char var11 = hiraToKatakanaHalf[var8 - 1];
               if (var11 < '｡') {
                  if ((var5 == null || var5.accept(var9)) && !var6) {
                     var3.append(var9);
                  }
               } else if (var5 == null || var5.accept(var11)) {
                  var12++;
                  var3.append(var11);
                  if (var10 != 0) {
                     var3.append(var10);
                  }
               }
            }
         }

         return var12;
      } else {
         return 0;
      }
   }

   public static void halfWidthToHiragana(StringBuffer var0, StringBuffer var1) {
      int var4 = var0.length();
      int var5 = hiraToKatakanaHalf.length;

      for (int var2 = 0; var2 < var4; var2++) {
         char var6 = var0.charAt(var2);
         int var3 = 0;

         while (var3 < var5 && var6 != hiraToKatakanaHalf[var3]) {
            var3++;
         }

         if (var3 == var5) {
            if (var6 == 'ﾞ') {
               var6 = '゛';
            }

            if (var6 == 'ﾟ') {
               var6 = '゜';
            }

            var1.append(var6);
         } else {
            var6 = hira2UNICODE[var3];
            var3++;
            char var7 = 0;
            if (var2 + 1 < var4) {
               var7 = var0.charAt(var2 + 1);
            }

            if (var7 == 'ﾞ') {
               if (var3 == 6) {
                  var1.append('ヴ');
                  var2++;
                  continue;
               }

               if (Arrays.getIndex(hiraganaNigori, ++var3) >= 0) {
                  var1.append((char)(var6 + 1));
                  var2++;
                  continue;
               }
            } else if (var7 == 'ﾟ') {
               var3 += 2;
               if (Arrays.getIndex(hiraganaMaru, var3) >= 0) {
                  var1.append((char)(var6 + 2));
                  var2++;
                  continue;
               }
            }

            var1.append(var6);
         }
      }
   }

   public static boolean isHiragana(char var0) {
      return var0 >= 12353 && var0 <= 12446 || var0 == 12540;
   }

   public static String composeAdjustedSearchPatternForJapanese(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
