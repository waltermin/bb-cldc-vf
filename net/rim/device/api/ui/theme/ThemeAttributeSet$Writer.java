package net.rim.device.api.ui.theme;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.XYRect;
import net.rim.device.internal.ui.Background;

public final class ThemeAttributeSet$Writer {
   private ResourceFetcher _resourceFetcher;
   private final ThemeAttributeSet this$0;

   public ThemeAttributeSet$Writer(ThemeAttributeSet var1, ResourceFetcher var2) {
      this.this$0 = var1;
      this._resourceFetcher = var2;
   }

   public final ThemeAttributeSet getThemeAttributeSet() {
      return this.this$0;
   }

   public final void setBackground(Background var1) {
      this.this$0._background = var1;
   }

   public final void setBackgroundPosition(String var1) {
   }

   public final void setAltFontFamily(String var1) {
      this.this$0._altFontFamily = var1;
   }

   public final void setFontStrokeOpacity(int var1) {
      this.this$0._fontStrokeOpacity = var1;
   }

   public final void setBackgroundImage(String var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setBackgroundImage(Bitmap var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setBackgroundOpacity(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setBackgroundPosition(int var1, int var2) {
      this.this$0._background.setPosition(var1, var2);
   }

   public final void setRepeat(int var1) {
      this.this$0._background.setRepeat(var1);
   }

   public final void setBorder(String var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setScrollbar(String var1) {
      this.this$0._scrollbarName = var1;
   }

   public final void setColor(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setColor(int var1, String var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setMaximumLineWrapping(int var1) {
      this.this$0._maxLineWrap = var1;
   }

   public final void setEdges(int var1, int var2, int var3, int var4, int var5) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setPosition(int var1, int var2, int var3, int var4) {
      this.this$0._position = (XYRect)(new Object(var1, var2, var3, var4));
   }

   public final void setFocusStyle(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setElement(String var1) {
      this.this$0._element = var1;
   }

   public final void setFontFamily(String var1) {
      this.this$0._fontFamily = var1;
   }

   public final void setFontStyle(int var1) {
      this.this$0._fontStyle = var1 | 1073741824;
   }

   public final void setFontSize(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final float powerHelper(int var1, boolean var2) {
      float var3 = (float)1065353216;

      for (int var4 = var1; var4 > 0; var4--) {
         var3 = (float)(var3 * 4608083138725491507L);
      }

      return var2 ? 1065353216 / var3 : var3;
   }

   public final void setFontSize(int var1, int var2) {
      this.setFontSize(var1, var2, false);
   }

   public final void setFontSize(int var1, int var2, boolean var3) {
      this.this$0._fontSizeUnits = var2;
      if (var3) {
         this.this$0._fontRelativeChange = var1;
      } else {
         this.this$0._fontSize = var1;
      }
   }

   public final void setFontAntialiasMode(int var1) {
      this.this$0._fontAntialiasMode = var1;
   }

   public final void setLayout(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void setLayoutParameters(String[] var1) {
      this.this$0._layoutParams = var1;
   }

   public final void setScrollArrow(int var1, String var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setTextAlign(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
