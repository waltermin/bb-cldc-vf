package net.rim.device.api.ui.theme;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.XYRect;
import net.rim.device.internal.ui.Background;

public final class ThemeAttributeSet$Writer {
   private ResourceFetcher _resourceFetcher;
   private final ThemeAttributeSet this$0;

   public ThemeAttributeSet$Writer(ThemeAttributeSet _1, ResourceFetcher resourceFetcher) {
      this.this$0 = _1;
      this._resourceFetcher = resourceFetcher;
   }

   public final ThemeAttributeSet getThemeAttributeSet() {
      return this.this$0;
   }

   public final void setBackground(Background background) {
      this.this$0._background = background;
   }

   public final void setBackgroundPosition(String name) {
   }

   public final void setAltFontFamily(String alt) {
      this.this$0._altFontFamily = alt;
   }

   public final void setFontStrokeOpacity(int opacity) {
      this.this$0._fontStrokeOpacity = opacity;
   }

   public final void setBackgroundImage(String name) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setBackgroundImage(Bitmap bitmap) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setBackgroundOpacity(int opacity) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setBackgroundPosition(int horizontalPosition, int verticalPosition) {
      this.this$0._background.setPosition(horizontalPosition, verticalPosition);
   }

   public final void setRepeat(int repeat) {
      this.this$0._background.setRepeat(repeat);
   }

   public final void setBorder(String borderName) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setScrollbar(String scrollbarName) {
      this.this$0._scrollbarName = scrollbarName;
   }

   public final void setColor(int colorType, int color) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setColor(int colorType, String color) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setMaximumLineWrapping(int maxLines) {
      this.this$0._maxLineWrap = maxLines;
   }

   public final void setEdges(int edgeType, int top, int right, int bottom, int left) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setPosition(int x, int y, int width, int height) {
      this.this$0._position = new XYRect(x, y, width, height);
   }

   public final void setFocusStyle(int style) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setElement(String element) {
      this.this$0._element = element;
   }

   public final void setFontFamily(String fontFamily) {
      this.this$0._fontFamily = fontFamily;
   }

   public final void setFontStyle(int fontStyle) {
      this.this$0._fontStyle = fontStyle | 1073741824;
   }

   public final void setFontSize(int relativeFontSize) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final float powerHelper(int power, boolean negative) {
      float result = (float)1065353216;

      for (int i = power; i > 0; i--) {
         result = (float)(result * 4608083138725491507L);
      }

      return negative ? 1065353216 / result : result;
   }

   public final void setFontSize(int fontSize, int fontSizeUnits) {
      this.setFontSize(fontSize, fontSizeUnits, false);
   }

   public final void setFontSize(int fontSize, int fontSizeUnits, boolean relative) {
      this.this$0._fontSizeUnits = fontSizeUnits;
      if (relative) {
         this.this$0._fontRelativeChange = fontSize;
      } else {
         this.this$0._fontSize = fontSize;
      }
   }

   public final void setFontAntialiasMode(int fontAntialiasMode) {
      this.this$0._fontAntialiasMode = fontAntialiasMode;
   }

   public final void setLayout(String layout) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void setLayoutParameters(String[] params) {
      this.this$0._layoutParams = params;
   }

   public final void setScrollArrow(int type, String filename) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setTextAlign(int align) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
