package net.rim.tid.im.conv.jp.util;

import net.rim.device.api.util.Arrays;

public class KanaConversionUtils implements ISLJpConst {
   protected static final char[] hira2UNICODE;
   protected static final char[] hiraToKatakanaHalf;
   protected static final int[] hiraganaNigori;
   protected static final int[] hiraganaMaru;

   public static int kanaToHalfWidth(StringBuffer input, int inputBegin, int inputLength, StringBuffer output) {
      return kanaToHalfWidth(input, inputBegin, inputLength, output, false);
   }

   public static int kanaToHalfWidth(StringBuffer input, int inputBegin, int inputLength, StringBuffer output, boolean skipUnconverted) {
      return kanaToHalfWidth(input, inputBegin, inputLength, output, null, null, skipUnconverted);
   }

   public static int kanaToHalfWidth(
      StringBuffer input,
      int inputBegin,
      int inputLength,
      StringBuffer output,
      KanaConversionUtils$ConversionFilter preConversionFltr,
      KanaConversionUtils$ConversionFilter postConversionFltr,
      boolean skipUnconverted
   ) {
      if (input != null && input.length() > inputBegin && output != null) {
         int len = input.length();
         if (inputBegin + inputLength < len) {
            len = inputBegin + inputLength;
         }

         int totalConverted = 0;

         for (int i = inputBegin; i < len; i++) {
            char ch = input.charAt(i);
            if (preConversionFltr == null || preConversionFltr.accept(ch)) {
               int index;
               if (12353 <= ch && ch <= 12435) {
                  index = ch - 12352;
               } else if (12449 <= ch && ch <= 12531) {
                  index = ch - 12448;
               } else if (ch == 12532) {
                  index = 84;
               } else if (ch == 12539) {
                  index = 97;
               } else {
                  if (ch != 12540) {
                     if ((postConversionFltr == null || postConversionFltr.accept(ch)) && !skipUnconverted) {
                        output.append(ch);
                     }
                     continue;
                  }

                  index = 92;
               }

               char accent = 0;
               if (Arrays.getIndex(hiraganaNigori, index) >= 0) {
                  if (index == 84) {
                     index = 6;
                  } else {
                     index--;
                  }

                  accent = 'ﾞ';
               } else if (Arrays.getIndex(hiraganaMaru, index) >= 0) {
                  index -= 2;
                  accent = 'ﾟ';
               }

               char result = hiraToKatakanaHalf[index - 1];
               if (result < '｡') {
                  if ((postConversionFltr == null || postConversionFltr.accept(ch)) && !skipUnconverted) {
                     output.append(ch);
                  }
               } else if (postConversionFltr == null || postConversionFltr.accept(result)) {
                  totalConverted++;
                  output.append(result);
                  if (accent != 0) {
                     output.append(accent);
                  }
               }
            }
         }

         return totalConverted;
      } else {
         return 0;
      }
   }

   public static void halfWidthToHiragana(StringBuffer input, StringBuffer output) {
      int len = input.length();
      int lenTable = hiraToKatakanaHalf.length;

      for (int i = 0; i < len; i++) {
         char ch = input.charAt(i);
         int index = 0;

         while (index < lenTable && ch != hiraToKatakanaHalf[index]) {
            index++;
         }

         if (index == lenTable) {
            if (ch == 'ﾞ') {
               ch = '゛';
            }

            if (ch == 'ﾟ') {
               ch = '゜';
            }

            output.append(ch);
         } else {
            ch = hira2UNICODE[index];
            index++;
            char next = 0;
            if (i + 1 < len) {
               next = input.charAt(i + 1);
            }

            if (next == 'ﾞ') {
               if (index == 6) {
                  output.append('ヴ');
                  i++;
                  continue;
               }

               if (Arrays.getIndex(hiraganaNigori, ++index) >= 0) {
                  output.append((char)(ch + 1));
                  i++;
                  continue;
               }
            } else if (next == 'ﾟ') {
               index += 2;
               if (Arrays.getIndex(hiraganaMaru, index) >= 0) {
                  output.append((char)(ch + 2));
                  i++;
                  continue;
               }
            }

            output.append(ch);
         }
      }
   }

   public static boolean isHiragana(char ch) {
      return ch >= 12353 && ch <= 12446 || ch == 12540;
   }

   public static String composeAdjustedSearchPatternForJapanese(String pattern) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
