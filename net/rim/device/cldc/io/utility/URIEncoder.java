package net.rim.device.cldc.io.utility;

import net.rim.device.api.util.NumberUtilities;

public final class URIEncoder {
   private static final String UTF_8;
   private static final String ISO_8859_1;

   public static final String encodeBlanks(String str) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final boolean fixEntityBeforeChar(char ch) {
      return (ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9');
   }

   public static final String encodeNonUSASCII(String str, boolean useHTMLBrowserCompatibility) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String encode(StringBuffer outputStringBuffer, String str, String encoding, boolean encodeBlanks) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final void handleSpecialCharacter(StringBuffer outputStringBuffer, char character, String encoding) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final int unicodeToWin1252(char character) {
      if (character <= 255) {
         return character;
      }

      switch (character) {
         case 'Œ':
            return 140;
         case 'œ':
            return 156;
         case 'Š':
            return 138;
         case 'š':
            return 154;
         case 'Ÿ':
            return 159;
         case 'Ž':
            return 142;
         case 'ž':
            return 158;
         case 'ƒ':
            return 131;
         case 'ˆ':
            return 136;
         case '˜':
            return 152;
         case '–':
            return 150;
         case '—':
            return 151;
         case '‘':
            return 145;
         case '’':
            return 146;
         case '‚':
            return 130;
         case '“':
            return 147;
         case '”':
            return 148;
         case '„':
            return 132;
         case '†':
            return 134;
         case '‡':
            return 135;
         case '•':
            return 149;
         case '…':
            return 133;
         case '‰':
            return 137;
         case '‹':
            return 139;
         case '›':
            return 155;
         case '€':
            return 128;
         case '™':
            return 153;
         default:
            return character;
      }
   }

   private static final void writeByte(StringBuffer outputStringBuffer, int aByte) {
      outputStringBuffer.append('%');
      outputStringBuffer.append(NumberUtilities.intToUpperHexDigit(aByte >> 4));
      outputStringBuffer.append(NumberUtilities.intToUpperHexDigit(aByte));
   }

   public static final void writeUTF8Char(StringBuffer outputStringBuffer, int character) {
      if (character <= 127) {
         writeByte(outputStringBuffer, character);
      } else if (character <= 2047) {
         int intToConvert = 192 | character >> 6;
         writeByte(outputStringBuffer, intToConvert);
         intToConvert = 128 | character & 63;
         writeByte(outputStringBuffer, intToConvert);
      } else if (character <= 65535) {
         int intToConvert = 224 | character >> 12;
         writeByte(outputStringBuffer, intToConvert);
         intToConvert = 128 | character >> 6 & 63;
         writeByte(outputStringBuffer, intToConvert);
         intToConvert = 128 | character & 63;
         writeByte(outputStringBuffer, intToConvert);
      } else {
         int u = (character >> 16) + 1;
         int intToConvert = 240 | u >> 2;
         writeByte(outputStringBuffer, intToConvert);
         intToConvert = 128 | (u & 3) << 4 | character >> 12 & 15;
         writeByte(outputStringBuffer, intToConvert);
         intToConvert = 128 | character >> 6 & 63;
         writeByte(outputStringBuffer, intToConvert);
         intToConvert = 128 | character & 63;
         writeByte(outputStringBuffer, intToConvert);
      }
   }
}
