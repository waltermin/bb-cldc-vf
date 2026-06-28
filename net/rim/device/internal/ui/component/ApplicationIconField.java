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

   public ApplicationIconField(String appName, String appState) {
      super(18014398509481984L);
      this.setTag(TAG);
      this._appName = appName;
      this._appState = appState;
      Theme theme = ThemeManager.getActiveTheme();
      this._width = theme.getRibbonIconWidth();
      this._height = theme.getRibbonIconHeight();
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      int themeGeneration = ThemeManager.getGeneration();
      if (_themeGeneration != themeGeneration) {
         _themeGeneration = themeGeneration;
         Theme theme = ThemeManager.getActiveTheme();
         _underlay = theme.getApplicationIcon(THEME_ICON_UNDERLAY, 0, Integer.MAX_VALUE, null, 2);
         _overlay = theme.getApplicationIcon(THEME_ICON_OVERLAY, 0, Integer.MAX_VALUE, null, 2);
         _underlayFocus = theme.getApplicationIcon(THEME_ICON_UNDERLAY, 6, Integer.MAX_VALUE, _underlay, 2);
         _overlayFocus = theme.getApplicationIcon(THEME_ICON_OVERLAY, 6, Integer.MAX_VALUE, _overlay, 2);
      }

      this.setBitmapInternal();
   }

   @Override
   protected void drawFocus(Graphics graphics, boolean on) {
      if (this._bitmapFocus == null && _underlayFocus == _underlay && _overlayFocus == _overlay) {
         super.drawFocus(graphics, on);
      } else {
         this.paint(graphics);
      }
   }

   protected Image convertToImage(Object icon) {
      throw new RuntimeException("cod2jar: type check");
   }

   private XYDimension getScaledDimensions(int width, int height) {
      int desiredWidth = this.getPreferredWidth();
      int desiredHeight = this.getPreferredHeight();
      int dW = desiredWidth - width;
      int dH = desiredHeight - height;
      int max_dW = desiredWidth >> 3;
      int max_dH = desiredHeight >> 3;
      if (dW < 0 || dH < 0 || dW > max_dW && dH > max_dH) {
         if (dW > 0 && dH > 0) {
            int inc_dW = Math.min(dW / max_dW, 3);
            int inc_dH = Math.min(dH / max_dH, 3);
            desiredWidth -= inc_dW * max_dW;
            desiredHeight -= inc_dH * max_dH;
         } else {
            desiredWidth -= max_dW;
            desiredHeight -= max_dH;
         }

         return desiredWidth * height / width > desiredHeight
            ? new XYDimension(desiredHeight * width / height, desiredHeight)
            : new XYDimension(desiredWidth, desiredWidth * height / width);
      } else {
         return new XYDimension(width, height);
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
   protected void layout(int width, int height) {
      this._x = this.getPaddingLeft();
      width = this._width + this.getPaddingLeft() + this.getPaddingRight();
      this._y = this.getPaddingTop();
      height = this._height + this.getPaddingTop() + this.getPaddingBottom();
      this.setExtent(width, height);
   }

   protected void fillPaintExtent(XYRect paintExtent) {
      if (paintExtent != null) {
         paintExtent.set(this._x, this._y, this._width, this._height);
      }
   }

   @Override
   protected void paint(Graphics graphics) {
      boolean isDrawingFocus = graphics.isDrawingStyleSet(8);
      Image underlay = isDrawingFocus ? _underlayFocus : _underlay;
      if (underlay != null) {
         underlay.paint(graphics, this._x, this._y, this._width, this._height);
      }

      if (this._bitmap != null) {
         Image bitmap = this._bitmapFocus != null && isDrawingFocus ? this._bitmapFocus : this._bitmap;
         bitmap.paint(graphics, this._x, this._y, this._width, this._height);
      }

      Image overlay = isDrawingFocus ? _overlayFocus : _overlay;
      if (overlay != null) {
         overlay.paint(graphics, this._x, this._y, this._width, this._height);
      }
   }

   public void setAppState(String appState) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setBitmap() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   private void setBitmapInternal() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setIconCustom(Object iconCustom, Object iconCustomFocus) {
      if (this._iconCustom != iconCustom) {
         this._iconCustom = this.convertToImage(iconCustom);
      }

      if (this._iconCustomFocus != iconCustomFocus) {
         this._iconCustomFocus = this.convertToImage(iconCustomFocus);
      }
   }

   public void setIconCustom(Image iconCustom, Image iconCustomFocus) {
      this._iconCustom = iconCustom;
      this._iconCustomFocus = iconCustomFocus;
   }

   public void setIconDefault(Object iconDefault) {
      if (this._iconDefault != iconDefault) {
         this._iconDefault = this.convertToImage(iconDefault);
      }
   }

   public void setIconDefault(Image iconDefault) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setSize(int width, int height) {
      this._width = width;
      this._height = height;
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
