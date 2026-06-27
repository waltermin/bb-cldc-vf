package net.rim.device.internal.ui;

import net.rim.device.api.ui.DrawTextParam;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.AttributedString$Iterator;
import net.rim.tid.text.AttributedString$Picture;

public class Formatter {
   private static ArticInterface$Line _linesPool;
   private static long _formattingTime;
   private static long _paintingTime;
   public static boolean _debug;
   private static int _errorReportCount;

   public static ArticInterface$Line incrementalFormat(
      FormatParams var0, Field var1, int var2, AttributedString var3, int var4, boolean var5, int var6, boolean var7
   ) {
      return incrementalFormatHelper(var0, var1, var2, var3, var4, var5, var6, var7, false);
   }

   private static ArticInterface$Line incrementalFormatHelper(
      FormatParams var0, Field var1, int var2, AttributedString var3, int var4, boolean var5, int var6, boolean var7, boolean var8
   ) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static ArticInterface$Line adjustLengths(int var0, ArticInterface$Layout var1, XYRect var2, ArticInterface$Line var3, ArticInterface$Line var4) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static ArticInterface$Line readLines(ArticInterface$Layout var0, ArticInterface$Line var1, ArticInterface$Line var2) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   private static synchronized ArticInterface$Line getLineFromPool() {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   private static synchronized void returnLineToPool(ArticInterface$Line var0) {
      var0._prev = null;
      var0._next = _linesPool;
      _linesPool = var0;
   }

   public static ArticInterface$Line getLineInfoForDocPos(int var0, boolean var1, ArticInterface$Line var2, ArticInterface$LineInfo var3, boolean var4) {
      if (var0 <= 0) {
         if (var4) {
            var3._line = var2;
            var3._start = 0;
            var3._top = 0;
         }

         return var2;
      } else {
         ArticInterface$Line var5 = var3._line;
         int var6 = var3._start;

         int var7;
         for (var7 = var3._top; var0 < var6 || var0 == var6 && var0 > 0 && !var1; var7 -= var5._boundsBottom - var5._boundsTop) {
            var5 = var5._prev;
            var6 -= var5._textLength + var5._skippedCharacters;
         }

         while (true) {
            int var8 = var6 + var5._textLength + var5._skippedCharacters;
            if (var0 < var8 || var5._next == null || var0 == var8 && !var1) {
               if (var4) {
                  var3._line = var5;
                  var3._start = var6;
                  var3._top = var7;
               }

               return var5;
            }

            var6 = var8;
            var7 += var5._boundsBottom - var5._boundsTop;
            var5 = var5._next;
         }
      }
   }

   public static void getLineInfoForYPos(int var0, ArticInterface$LineInfo var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static void paint(
      Graphics var0, DrawTextParam var1, ArticInterface$LineInfo var2, AttributedString$Iterator var3, Field var4, Formatter$TextRenderer var5
   ) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static final void drawHighlightRegion(
      Graphics var0,
      int var1,
      boolean var2,
      int var3,
      int var4,
      int var5,
      int var6,
      int var7,
      int var8,
      int var9,
      DrawTextParam var10,
      AttributedString$Picture var11,
      int var12,
      Field var13,
      Formatter$TextRenderer var14
   ) {
      switch (ThemeAttributeSet.getFocusStyle(var13)) {
         case -1:
            break;
         case 0:
         default:
            switch (var1) {
               case -1:
                  return;
               case 0:
                  throw new Object();
               case 1:
               case 2:
               case 3:
               default:
                  var0.invert(var3, var4, var5, var6);
                  return;
            }
         case 1:
            switch (var1) {
               case -1:
                  return;
               case 0:
                  throw new Object();
               case 2:
                  var0.invert(var3, var4, var5, var6);
                  return;
               case 3:
               default:
                  var0.invert(var3, var4, var5, var6);
               case 1:
                  int var15 = var4 + (var6 >> 1);
                  var0.invert(var3, var15, var5, 1);
                  return;
            }
         case 2:
            if (!var2) {
               var0.pushContext(var3, var4, var5, var6, 0, 0);
               if (var2) {
                  var0.setDrawingStyle(8, (var1 & 1) != 0);
                  var0.setDrawingStyle(16, (var1 & 2) != 0);
               }

               var0.clear();
               drawPictureOrText(var0, var3, var7, var8, var9, var10, var11, var12, var14);
               var0.popContext();
               return;
            }

            switch (var1) {
               case -1:
                  return;
               case 0:
                  throw new Object();
               case 1:
                  var0.drawRect(var3, var4, var5, var6);
                  return;
               case 3:
               default:
                  var0.drawRect(var3, var4, var5, var6);
               case 2:
                  var0.invert(var3, var4, var5, var6);
                  return;
            }
         case 3:
            var0.pushContext(var3, var4, var5, var6, 0, 0);
            if (var2) {
               var0.setDrawingStyle(8, (var1 & 1) != 0);
               var0.setDrawingStyle(16, (var1 & 2) != 0);
               switch (var1) {
                  case -1:
                     break;
                  case 0:
                     throw new Object();
                  case 1:
                  case 3:
                  default:
                     var0.setColor(ThemeAttributeSet.getColor(var13, 3));
                     var0.setBackgroundColor(ThemeAttributeSet.getColor(var13, 2));
                     break;
                  case 2:
                     var0.setColor(ThemeAttributeSet.getColor(var13, 5));
                     var0.setBackgroundColor(ThemeAttributeSet.getColor(var13, 4));
               }

               var0.setBackgroundImage(null, 0, 0);
            }

            var0.clear();
            drawPictureOrText(var0, var3, var7, var8, var9, var10, var11, var12, var14);
            var0.popContext();
            return;
         case 4:
            var0.pushContext(var3, var4, var5, var6, 0, 0);
            if (var2) {
               var0.setDrawingStyle(8, (var1 & 1) != 0);
               var0.setDrawingStyle(16, (var1 & 2) != 0);
            }

            var0.clear();
            drawPictureOrText(var0, var3, var7, var8, var9, var10, var11, var12, var14);
            var0.popContext();
      }
   }

   private static void drawPictureOrText(
      Graphics var0, int var1, int var2, int var3, int var4, DrawTextParam var5, AttributedString$Picture var6, int var7, Formatter$TextRenderer var8
   ) {
      if (var6 != null) {
         var6.draw(var0, var1, var2 + var6.getInfo()._y);
      } else if ((var7 & 16) != 0) {
         var0.drawText('‐', var1, var2, 8, -1);
      } else if ((var7 & 8) == 0) {
         var8.drawText(var0, var3, var4, var1, var2, var5);
      }
   }

   public static ArticInterface$Line setSelection(
      ArticInterface$Line var0, int var1, int var2, int var3, int var4, boolean var5, int var6, AttributedString var7, FormatParams var8
   ) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static ArticInterface$Line reformatAfterSelectionChange(
      ArticInterface$Line var0, int var1, int var2, int var3, AttributedString var4, int var5, boolean var6, int var7, ArticInterface$LineInfo var8, int var9
   ) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void getTextBounds(int var0, int var1, XYRect var2, ArticInterface$Line var3, int var4, int var5) {
      var2.set(0, 0, 0, 0);
      int var6 = var5;
      int var7 = var3._boundsBottom - var3._boundsTop;
      var0 -= var4;
      var1 -= var4;
      int var8 = var3._layoutRun == null ? 0 : var3._layoutRun.length;
      int var9 = var3._originX;

      for (int var10 = 0; var10 < var8; var10++) {
         ArticInterface$LayoutRun var11 = var3._layoutRun[var10];
         if (var0 <= var11._textStart && var11._textStart + var11._textLength <= var1) {
            var2.union(var9, var6, var11._advance, var7);
         }

         var9 += var11._advance;
      }
   }

   public static void printPerformanceStats(long var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static ArticInterface$Line recoverArticFailure(
      RuntimeException var0, FormatParams var1, Field var2, int var3, AttributedString var4, int var5, boolean var6, int var7, boolean var8, boolean var9
   ) {
      if (var9) {
         throw var0;
      }

      var0.printStackTrace();
      reportArticException(
         var4, var3, var1, var5, var6, 0, 0, var7, var1._cursorLineInfo._start, var1._cursorLineInfo._top, var1._cursorLineInfo._line, var1._lineList
      );
      ArticInterface$Line var10 = new ArticInterface$Line();
      var10._flags = 3;
      var1.init(0, 0, var4.length(), var5, false, var10);
      return incrementalFormatHelper(var1, var2, var3, var4, var5, var6, var7, var8, true);
   }

   public static void reportArticException(
      AttributedString var0,
      int var1,
      FormatParams var2,
      int var3,
      boolean var4,
      int var5,
      int var6,
      int var7,
      int var8,
      int var9,
      ArticInterface$Line var10,
      ArticInterface$Line var11
   ) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
