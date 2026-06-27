package net.rim.device.cldc.io.utility;

import net.rim.device.api.util.NumberUtilities;

public final class URIEncoder {
   private static final String UTF_8;
   private static final String ISO_8859_1;

   public static final String encodeBlanks(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final boolean fixEntityBeforeChar(char var0) {
      return (var0 < 'a' || var0 > 'z') && (var0 < 'A' || var0 > 'Z') && (var0 < '0' || var0 > '9');
   }

   public static final String encodeNonUSASCII(String var0, boolean var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String encode(StringBuffer var0, String var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final void handleSpecialCharacter(StringBuffer var0, char var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final int unicodeToWin1252(char var0) {
      if (var0 <= 255) {
         return var0;
      }

      switch (var0) {
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
            return var0;
      }
   }

   private static final void writeByte(StringBuffer var0, int var1) {
      var0.append('%');
      var0.append(NumberUtilities.intToUpperHexDigit(var1 >> 4));
      var0.append(NumberUtilities.intToUpperHexDigit(var1));
   }

   public static final void writeUTF8Char(StringBuffer var0, int var1) {
      if (var1 <= 127) {
         writeByte(var0, var1);
      } else if (var1 <= 2047) {
         int var7 = 192 | var1 >> 6;
         writeByte(var0, var7);
         var7 = 128 | var1 & 63;
         writeByte(var0, var7);
      } else if (var1 <= 65535) {
         int var4 = 224 | var1 >> 12;
         writeByte(var0, var4);
         var4 = 128 | var1 >> 6 & 63;
         writeByte(var0, var4);
         var4 = 128 | var1 & 63;
         writeByte(var0, var4);
      } else {
         int var2 = (var1 >> 16) + 1;
         int var3 = 240 | var2 >> 2;
         writeByte(var0, var3);
         var3 = 128 | (var2 & 3) << 4 | var1 >> 12 & 15;
         writeByte(var0, var3);
         var3 = 128 | var1 >> 6 & 63;
         writeByte(var0, var3);
         var3 = 128 | var1 & 63;
         writeByte(var0, var3);
      }
   }
}
