package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYDimension;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.Theme;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.internal.ui.Image;

public class ApplicationIconField extends Field {
   private String _appName;
   private String _appState;
   private Image _iconCustom;
   private Image _iconCustomFocus;
   private Image _iconDefault;
   protected Image _bitmap;
   protected Image _bitmapFocus;
   protected Image _bitmapHidden;
   protected Image _bitmapFocusHidden;
   private int _x;
   private int _y;
   private int _width;
   private int _height;
   private static Tag TAG;
   private static String THEME_ICON_UNDERLAY;
   private static String THEME_ICON_OVERLAY;
   private static int _themeGeneration;
   private static Image _underlay;
   private static Image _underlayFocus;
   private static Image _overlay;
   private static Image _overlayFocus;

   public ApplicationIconField(String var1, String var2) {
      super(18014398509481984L);
      this.setTag(TAG);
      this._appName = var1;
      this._appState = var2;
      Theme var3 = ThemeManager.getActiveTheme();
      this._width = var3.getRibbonIconWidth();
      this._height = var3.getRibbonIconHeight();
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      int var1 = ThemeManager.getGeneration();
      if (_themeGeneration != var1) {
         _themeGeneration = var1;
         Theme var2 = ThemeManager.getActiveTheme();
         _underlay = var2.getApplicationIcon(THEME_ICON_UNDERLAY, 0, Integer.MAX_VALUE, null, 2);
         _overlay = var2.getApplicationIcon(THEME_ICON_OVERLAY, 0, Integer.MAX_VALUE, null, 2);
         _underlayFocus = var2.getApplicationIcon(THEME_ICON_UNDERLAY, 6, Integer.MAX_VALUE, _underlay, 2);
         _overlayFocus = var2.getApplicationIcon(THEME_ICON_OVERLAY, 6, Integer.MAX_VALUE, _overlay, 2);
      }

      this.setBitmapInternal();
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      if (this._bitmapFocus == null && _underlayFocus == _underlay && _overlayFocus == _overlay) {
         super.drawFocus(var1, var2);
      } else {
         this.paint(var1);
      }
   }

   protected Image convertToImage(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private XYDimension getScaledDimensions(int var1, int var2) {
      int var3 = this.getPreferredWidth();
      int var4 = this.getPreferredHeight();
      int var5 = var3 - var1;
      int var6 = var4 - var2;
      int var7 = var3 >> 3;
      int var8 = var4 >> 3;
      if (var5 < 0 || var6 < 0 || var5 > var7 && var6 > var8) {
         if (var5 > 0 && var6 > 0) {
            int var9 = Math.min(var5 / var7, 3);
            int var10 = Math.min(var6 / var8, 3);
            var3 -= var9 * var7;
            var4 -= var10 * var8;
         } else {
            var3 -= var7;
            var4 -= var8;
         }

         return (XYDimension)(var3 * var2 / var1 > var4 ? new Object(var4 * var1 / var2, var4) : new Object(var3, var3 * var2 / var1));
      } else {
         return (XYDimension)(new Object(var1, var2));
      }
   }

   @Override
   public int getPreferredHeight() {
      return this._height + this.getPaddingTop() + this.getPaddingBottom();
   }

   @Override
   public int getPreferredWidth() {
      return this._width + this.getPaddingLeft() + this.getPaddingRight();
   }

   @Override
   protected void layout(int var1, int var2) {
      this._x = this.getPaddingLeft();
      var1 = this._width + this.getPaddingLeft() + this.getPaddingRight();
      this._y = this.getPaddingTop();
      var2 = this._height + this.getPaddingTop() + this.getPaddingBottom();
      this.setExtent(var1, var2);
   }

   protected void fillPaintExtent(XYRect var1) {
      if (var1 != null) {
         var1.set(this._x, this._y, this._width, this._height);
      }
   }

   @Override
   protected void paint(Graphics var1) {
      boolean var2 = var1.isDrawingStyleSet(8);
      Image var3 = var2 ? _underlayFocus : _underlay;
      if (var3 != null) {
         var3.paint(var1, this._x, this._y, this._width, this._height);
      }

      if (this._bitmap != null) {
         Image var4 = this._bitmapFocus != null && var2 ? this._bitmapFocus : this._bitmap;
         var4.paint(var1, this._x, this._y, this._width, this._height);
      }

      Image var5 = var2 ? _overlayFocus : _overlay;
      if (var5 != null) {
         var5.paint(var1, this._x, this._y, this._width, this._height);
      }
   }

   public void setAppState(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setBitmap() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   private void setBitmapInternal() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setIconCustom(Object var1, Object var2) {
      if (this._iconCustom != var1) {
         this._iconCustom = this.convertToImage(var1);
      }

      if (this._iconCustomFocus != var2) {
         this._iconCustomFocus = this.convertToImage(var2);
      }
   }

   public void setIconCustom(Image var1, Image var2) {
      this._iconCustom = var1;
      this._iconCustomFocus = var2;
   }

   public void setIconDefault(Object var1) {
      if (this._iconDefault != var1) {
         this._iconDefault = this.convertToImage(var1);
      }
   }

   public void setIconDefault(Image var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setSize(int var1, int var2) {
      this._width = var1;
      this._height = var2;
   }

   public String getAppName() {
      return this._appName;
   }

   @Override
   public int getAccessibleRole() {
      return 5;
   }

   @Override
   public String getAccessibleName() {
      return this.getAppName();
   }
}
