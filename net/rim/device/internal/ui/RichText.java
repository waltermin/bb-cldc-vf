package net.rim.device.internal.ui;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

public final class RichText {
   private static Edit$Helper _helper;
   private static Edit$BidiLineRuns _runs;
   private static int NO_DATA;
   static long RADIO_LOGWORTHY_REPORT_REQUEST;
   private static int ALIGNMENT_BITS;
   public static final byte DIR_LTR;
   public static final byte DIR_NEUTRAL;
   public static final byte DIR_RTL;
   public static final String PARAG_DELIMITERS;

   private RichText() {
   }

   public static final Edit$Helper calculateLengths(int var0, String var1, int[] var2, byte[] var3, Font[] var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Edit$Helper calculateLengths(int var0, int var1, int var2, StringBufferGap var3, int[] var4, byte[] var5, Font[] var6, boolean var7) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final void validateEntries(byte[] var0, Font[] var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final int getDefaultParagDirection() {
      int var0 = Locale.getDefaultForSystem().getCode() & -65536;
      return var0 != 1751449600 && var0 != 1634861056 && var0 != 1717633024 ? 0 : 1;
   }

   public static final int getParagraphOrdering(long var0) {
      if ((var0 & 549755813888L) != 0) {
         return 2;
      } else {
         return (var0 & 274877906944L) != 0 ? 3 : getDefaultParagDirection();
      }
   }

   private static final native void calculateLengths(Edit$Helper var0, int var1, String var2, int[] var3, byte[] var4, Font[] var5);

   private static final native void calculateLengths(
      Edit$Helper var0, int var1, int var2, int var3, StringBufferGap var4, int[] var5, byte[] var6, Font[] var7, int var8
   );

   public static final Edit$BidiLineRuns getBidiOrder(StringBufferGap var0, int var1, int var2, byte[] var3, boolean var4, int[] var5) {
      return getBidiOrder(var0, var1, var2, var3, var4, getDefaultParagDirection(), var5, 0, var5 == null ? 0 : var5.length);
   }

   public static final Edit$BidiLineRuns getBidiOrder(
      StringBufferGap var0, int var1, int var2, byte[] var3, boolean var4, int var5, int[] var6, int var7, int var8
   ) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Edit$BidiLineRuns getBidiOrder(StringBuffer var0, int var1, int var2, byte[] var3, boolean var4, int[] var5) {
      return getBidiOrder(var0, var1, var2, var3, var4, getDefaultParagDirection(), var5, 0, var5 == null ? 0 : var5.length);
   }

   public static final Edit$BidiLineRuns getBidiOrder(
      StringBuffer var0, int var1, int var2, byte[] var3, boolean var4, int var5, int[] var6, int var7, int var8
   ) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Edit$BidiLineRuns getBidiOrder(String var0, int var1, int var2, byte[] var3, boolean var4, int[] var5) {
      return getBidiOrder(var0, var1, var2, var3, var4, getDefaultParagDirection(), var5, 0, var5 == null ? 0 : var5.length);
   }

   public static final Edit$BidiLineRuns getBidiOrder(String var0, int var1, int var2, byte[] var3, boolean var4, int var5, int[] var6, int var7, int var8) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final void sendLog(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final native int getBidiOrder(
      Edit$BidiLineRuns var0, StringBufferGap var1, int var2, int var3, byte[] var4, int var5, boolean var6, int[] var7, int var8, int var9
   );

   private static final native int getBidiOrder(
      Edit$BidiLineRuns var0, StringBuffer var1, int var2, int var3, byte[] var4, int var5, boolean var6, int[] var7, int var8, int var9
   );

   private static final native int getBidiOrder(
      Edit$BidiLineRuns var0, String var1, int var2, int var3, byte[] var4, int var5, boolean var6, int[] var7, int var8, int var9
   );

   public static final void drawTextWithEllipses(Graphics var0, String var1, int var2, int var3, int var4, int var5, int var6) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte getLineDirection(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte getLineDirection(String var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte getLineDirection(StringBufferGap var0) {
      return var0 != null && var0.length() > 0 ? getLineDirection(var0, 0, var0.length()) : 0;
   }

   public static final byte getLineDirection(StringBufferGap var0, int var1, int var2) {
      if (var0 != null && var0.length() > 0) {
         int var3 = var1 + var2;
         if (var1 >= 0 && var2 >= 0 && var3 <= var0.length()) {
            for (int var4 = var1; var4 < var3; var4++) {
               char var5 = var0.charAt(var4);
               if (isRTL(var5)) {
                  return 2;
               }

               if (!isNeutral(var5)) {
                  return 0;
               }
            }

            return 0;
         } else {
            throw new Object();
         }
      } else {
         return 0;
      }
   }

   public static final int getRTLCount(String var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getRTLCount(StringBufferGap var0, int var1, int var2) {
      if (var0 != null && var0.length() > 0) {
         int var3 = 0;
         int var4 = var1 + var2;
         if (var1 >= 0 && var2 >= 0 && var4 <= var0.length()) {
            for (int var5 = var1; var5 < var4; var5++) {
               char var6 = var0.charAt(var5);
               if (isRTL(var6)) {
                  var3++;
               }
            }

            return var3;
         } else {
            throw new Object();
         }
      } else {
         return 0;
      }
   }

   public static final boolean isRTL(char var0) {
      return var0 >= 1425 && var0 <= 1524 || var0 >= 1548 && var0 <= 1866 || var0 >= 'יִ' && var0 <= 'ﷻ' || var0 >= 'ﹰ' && var0 <= 'ﻼ';
   }

   public static final boolean isNeutral(char var0) {
      return var0 == '\n'
         || var0 >= 0 && var0 <= '@'
         || var0 >= '[' && var0 <= '`'
         || var0 >= '{' && var0 <= 191
         || var0 == 215
         || var0 == 247
         || var0 >= 768 && var0 <= 866
         || var0 >= 8192 && var0 <= 8334
         || var0 >= 8352 && var0 <= 8367
         || var0 >= 8400 && var0 <= 8419
         || var0 >= 8448 && var0 <= 9371
         || var0 >= 9472 && var0 <= 10174
         || var0 >= 12288 && var0 <= 12351
         || var0 >= 13056 && var0 <= 13310
         || var0 >= '︠' && var0 <= '﹫'
         || var0 >= '\ufeff' && var0 <= '＠'
         || var0 >= '［' && var0 <= '｀'
         || var0 >= '｛' && var0 <= '､'
         || var0 >= '\ufff9' && var0 <= '\uffff';
   }

   public static final int[] getParagraphOffsets(StringBufferGap var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }
}
