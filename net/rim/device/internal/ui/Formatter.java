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
      FormatParams input, Field field, int width, AttributedString text, int cursor, boolean cursorLeadingEdge, int anchor, boolean doUpdateLayout
   ) {
      return incrementalFormatHelper(input, field, width, text, cursor, cursorLeadingEdge, anchor, doUpdateLayout, false);
   }

   private static ArticInterface$Line incrementalFormatHelper(
      FormatParams input,
      Field field,
      int width,
      AttributedString text,
      int cursor,
      boolean cursorLeadingEdge,
      int anchor,
      boolean doUpdateLayout,
      boolean afterFailure
   ) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   private static ArticInterface$Line adjustLengths(
      int aDelta, ArticInterface$Layout aLayout, XYRect aInvalid, ArticInterface$Line lineList, ArticInterface$Line startLine
   ) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static ArticInterface$Line readLines(ArticInterface$Layout aLayout, ArticInterface$Line lineList, ArticInterface$Line startLine) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   private static synchronized ArticInterface$Line getLineFromPool() {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   private static synchronized void returnLineToPool(ArticInterface$Line line) {
      line._prev = null;
      line._next = _linesPool;
      _linesPool = line;
   }

   public static ArticInterface$Line getLineInfoForDocPos(
      int docPos, boolean leadingEdge, ArticInterface$Line lineList, ArticInterface$LineInfo info, boolean updateFormatParams
   ) {
      if (docPos <= 0) {
         if (updateFormatParams) {
            info._line = lineList;
            info._start = 0;
            info._top = 0;
         }

         return lineList;
      } else {
         ArticInterface$Line line = info._line;
         int start = info._start;

         int top;
         for (top = info._top; docPos < start || docPos == start && docPos > 0 && !leadingEdge; top -= line._boundsBottom - line._boundsTop) {
            line = line._prev;
            start -= line._textLength + line._skippedCharacters;
         }

         while (true) {
            int end = start + line._textLength + line._skippedCharacters;
            if (docPos < end || line._next == null || docPos == end && !leadingEdge) {
               if (updateFormatParams) {
                  info._line = line;
                  info._start = start;
                  info._top = top;
               }

               return line;
            }

            start = end;
            top += line._boundsBottom - line._boundsTop;
            line = line._next;
         }
      }
   }

   public static void getLineInfoForYPos(int aY, ArticInterface$LineInfo info) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static void paint(
      Graphics aGraphics,
      DrawTextParam drawTextParam,
      ArticInterface$LineInfo info,
      AttributedString$Iterator iterator,
      Field field,
      Formatter$TextRenderer renderer
   ) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static final void drawHighlightRegion(
      Graphics graphics,
      int style,
      boolean on,
      int x,
      int y,
      int width,
      int height,
      int baseline_y,
      int offset,
      int length,
      DrawTextParam drawTextParam,
      AttributedString$Picture picture,
      int run_flags,
      Field field,
      Formatter$TextRenderer renderer
   ) {
      switch (ThemeAttributeSet.getFocusStyle(field)) {
         case -1:
            break;
         case 0:
         default:
            switch (style) {
               case -1:
                  return;
               case 0:
                  throw new IllegalArgumentException();
               case 1:
               case 2:
               case 3:
               default:
                  graphics.invert(x, y, width, height);
                  return;
            }
         case 1:
            switch (style) {
               case -1:
                  return;
               case 0:
                  throw new IllegalArgumentException();
               case 2:
                  graphics.invert(x, y, width, height);
                  return;
               case 3:
               default:
                  graphics.invert(x, y, width, height);
               case 1:
                  int mid = y + (height >> 1);
                  graphics.invert(x, mid, width, 1);
                  return;
            }
         case 2:
            if (!on) {
               graphics.pushContext(x, y, width, height, 0, 0);
               if (on) {
                  graphics.setDrawingStyle(8, (style & 1) != 0);
                  graphics.setDrawingStyle(16, (style & 2) != 0);
               }

               graphics.clear();
               drawPictureOrText(graphics, x, baseline_y, offset, length, drawTextParam, picture, run_flags, renderer);
               graphics.popContext();
               return;
            }

            switch (style) {
               case -1:
                  return;
               case 0:
                  throw new IllegalArgumentException();
               case 1:
                  graphics.drawRect(x, y, width, height);
                  return;
               case 3:
               default:
                  graphics.drawRect(x, y, width, height);
               case 2:
                  graphics.invert(x, y, width, height);
                  return;
            }
         case 3:
            graphics.pushContext(x, y, width, height, 0, 0);
            if (on) {
               graphics.setDrawingStyle(8, (style & 1) != 0);
               graphics.setDrawingStyle(16, (style & 2) != 0);
               switch (style) {
                  case -1:
                     break;
                  case 0:
                     throw new IllegalArgumentException();
                  case 1:
                  case 3:
                  default:
                     graphics.setColor(ThemeAttributeSet.getColor(field, 3));
                     graphics.setBackgroundColor(ThemeAttributeSet.getColor(field, 2));
                     break;
                  case 2:
                     graphics.setColor(ThemeAttributeSet.getColor(field, 5));
                     graphics.setBackgroundColor(ThemeAttributeSet.getColor(field, 4));
               }

               graphics.setBackgroundImage(null, 0, 0);
            }

            graphics.clear();
            drawPictureOrText(graphics, x, baseline_y, offset, length, drawTextParam, picture, run_flags, renderer);
            graphics.popContext();
            return;
         case 4:
            graphics.pushContext(x, y, width, height, 0, 0);
            if (on) {
               graphics.setDrawingStyle(8, (style & 1) != 0);
               graphics.setDrawingStyle(16, (style & 2) != 0);
            }

            graphics.clear();
            drawPictureOrText(graphics, x, baseline_y, offset, length, drawTextParam, picture, run_flags, renderer);
            graphics.popContext();
      }
   }

   private static void drawPictureOrText(
      Graphics graphics,
      int x,
      int y,
      int offset,
      int length,
      DrawTextParam drawTextParam,
      AttributedString$Picture picture,
      int run_flags,
      Formatter$TextRenderer renderer
   ) {
      if (picture != null) {
         picture.draw(graphics, x, y + picture.getInfo()._y);
      } else if ((run_flags & 16) != 0) {
         graphics.drawText('‐', x, y, 8, -1);
      } else if ((run_flags & 8) == 0) {
         renderer.drawText(graphics, offset, length, x, y, drawTextParam);
      }
   }

   public static ArticInterface$Line setSelection(
      ArticInterface$Line lineList,
      int oldAnchor,
      int oldCursor,
      int newAnchor,
      int newCursor,
      boolean newCursorLeadingEdge,
      int width,
      AttributedString text,
      FormatParams formatParams
   ) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static ArticInterface$Line reformatAfterSelectionChange(
      ArticInterface$Line lineList,
      int lineStart,
      int lineEnd,
      int width,
      AttributedString text,
      int cursor,
      boolean cursorLeadingEdge,
      int anchor,
      ArticInterface$LineInfo cursorLineInfo,
      int formatFlags
   ) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static void getTextBounds(int offset1, int offset2, XYRect rect, ArticInterface$Line line, int lineStart, int lineTop) {
      rect.set(0, 0, 0, 0);
      int y = lineTop;
      int height = line._boundsBottom - line._boundsTop;
      offset1 -= lineStart;
      offset2 -= lineStart;
      int runs = line._layoutRun == null ? 0 : line._layoutRun.length;
      int run_x = line._originX;

      for (int i = 0; i < runs; i++) {
         ArticInterface$LayoutRun run = line._layoutRun[i];
         if (offset1 <= run._textStart && run._textStart + run._textLength <= offset2) {
            rect.union(run_x, y, run._advance, height);
         }

         run_x += run._advance;
      }
   }

   public static void printPerformanceStats(long aTotalTime) {
      long one_type = _formattingTime * 1000 / aTotalTime;
      System.out.println("*In formatting: " + one_type / 10 + "." + one_type % 10 + "%");
      one_type = _paintingTime * 1000 / aTotalTime;
      System.out.println("*In painting: " + one_type / 10 + "." + one_type % 10 + "%");
   }

   private static ArticInterface$Line recoverArticFailure(
      RuntimeException e,
      FormatParams input,
      Field field,
      int width,
      AttributedString text,
      int cursor,
      boolean cursorLeadingEdge,
      int anchor,
      boolean doUpdateLayout,
      boolean afterFailure
   ) {
      if (afterFailure) {
         throw e;
      }

      e.printStackTrace();
      reportArticException(
         text,
         width,
         input,
         cursor,
         cursorLeadingEdge,
         0,
         0,
         anchor,
         input._cursorLineInfo._start,
         input._cursorLineInfo._top,
         input._cursorLineInfo._line,
         input._lineList
      );
      ArticInterface$Line lineList = new ArticInterface$Line();
      lineList._flags = 3;
      input.init(0, 0, text.length(), cursor, false, lineList);
      return incrementalFormatHelper(input, field, width, text, cursor, cursorLeadingEdge, anchor, doUpdateLayout, true);
   }

   public static void reportArticException(
      AttributedString text,
      int width,
      FormatParams input,
      int cursor,
      boolean cursorLeadingEdge,
      int x,
      int y,
      int anchor,
      int cursorLineStart,
      int cursorLineTop,
      ArticInterface$Line cursorLine,
      ArticInterface$Line lineList
   ) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
