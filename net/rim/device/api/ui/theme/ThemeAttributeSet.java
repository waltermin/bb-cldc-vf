package net.rim.device.api.ui.theme;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.XYRect;
import net.rim.device.internal.ui.Background;
import net.rim.device.internal.ui.Border;

public final class ThemeAttributeSet {
   private String _scrollbarName;
   private Bitmap[] _scrollbarImages;
   String _element;
   int _set;
   String[] _palettedColors = new String[8];
   int[] _colors = new int[8];
   int _focusStyle;
   String _fontFamily;
   String _altFontFamily;
   int _fontStyle;
   int _fontSize;
   int _fontSizeUnits;
   int _fontAntialiasMode;
   int _fontStrokeOpacity = 255;
   int _fontRelativeChange = 0;
   float _fontRelativePercent = (float)false;
   Font _font;
   int _textAlign;
   int _maxLineWrap;
   Background _background;
   ResourceFetcher _backgroundLocation;
   String _backgroundName;
   int _opacity = 255;
   String[] _scrollArrowName;
   Bitmap[] _scrollArrow;
   String _borderName;
   Border _border;
   XYEdges _padding;
   XYEdges _margin;
   String _layout;
   String[] _layoutParams;
   XYRect _position;
   public static final int EDGES_PADDING;
   public static final int EDGES_BORDER;
   public static final int EDGES_MARGIN;
   public static final int COLOR_BACKGROUND;
   public static final int COLOR_FOREGROUND;
   public static final int COLOR_CARET_BACKGROUND;
   public static final int COLOR_CARET_FOREGROUND;
   public static final int COLOR_SELECTION_BACKGROUND;
   public static final int COLOR_SELECTION_FOREGROUND;
   public static final int COLOR_FONT_STROKE;
   public static final int COLOR_FONT_FILL;
   public static final int COLOR_COUNT;
   public static final int SCROLL_ARROW_UP;
   public static final int SCROLL_ARROW_DOWN;
   public static final int SCROLL_ARROW_LEFT;
   public static final int SCROLL_ARROW_RIGHT;
   public static final int FONT_SIZE_XX_SMALL;
   public static final int FONT_SIZE_X_SMALL;
   public static final int FONT_SIZE_SMALL;
   public static final int FONT_SIZE_MEDIUM;
   public static final int FONT_SIZE_LARGE;
   public static final int FONT_SIZE_X_LARGE;
   public static final int FONT_SIZE_XX_LARGE;
   public static final int FONT_SIZE_LARGER;
   public static final int FONT_SIZE_SMALLER;
   static final int COLOR_SET_SHIFT;
   static final int FOCUS_STYLE_SET;
   static final int FONT_SET;
   static final int BACKGROUND_IMAGE_SET;
   static final int BORDER_SET;
   static final int EDGES_SET_SHIFT;
   static final int SCROLL_ARROW_SET_SHIFT;
   static final int BACKGROUND_SET;
   static final int BACKGROUND_OPACITY_SET;
   static final int TEXT_ALIGN_SET;
   static final int FONT_STYLE_SET;

   public ThemeAttributeSet() {
      this._border = this._border;
   }

   public ThemeAttributeSet(String var1) {
      this._element = var1;
   }

   public ThemeAttributeSet(ThemeAttributeSet var1) {
      if (var1 == null) {
         throw new Object();
      }

      this.clone(var1);
   }

   private final void clone(ThemeAttributeSet var1) {
      this._element = var1._element;
      this._set = var1._set;
      System.arraycopy(var1._colors, 0, this._colors, 0, this._colors.length);
      System.arraycopy(var1._palettedColors, 0, this._palettedColors, 0, this._palettedColors.length);
      this._focusStyle = var1._focusStyle;
      this._fontFamily = var1._fontFamily;
      this._altFontFamily = var1._altFontFamily;
      this._fontStyle = var1._fontStyle;
      this._fontSize = var1._fontSize;
      this._fontSizeUnits = var1._fontSizeUnits;
      this._fontStrokeOpacity = var1._fontStrokeOpacity;
      this._fontAntialiasMode = var1._fontAntialiasMode;
      this._font = var1._font;
      this._textAlign = var1._textAlign;
      if (var1._scrollArrowName != null) {
         this._scrollArrowName = new String[var1._scrollArrowName.length];
         System.arraycopy(var1._scrollArrowName, 0, this._scrollArrowName, 0, this._scrollArrowName.length);
      }

      if (var1._scrollArrow != null) {
         this._scrollArrow = new Bitmap[var1._scrollArrow.length];
         System.arraycopy(var1._scrollArrow, 0, this._scrollArrow, 0, this._scrollArrow.length);
      }

      this._borderName = var1._borderName;
      this._padding = var1._padding;
      this._border = var1._border;
      this._margin = var1._margin;
      this._scrollbarName = var1._scrollbarName;
      this._scrollbarImages = var1._scrollbarImages;
      this._position = var1._position;
      this._layout = var1._layout;
      this._layoutParams = var1._layoutParams;
   }

   public final void apply() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void calculateRelativeFontSize() {
      Font var1 = Font.getDefault();
      if (this._fontRelativeChange != 0) {
         this._fontSize = var1.getHeight(this._fontSizeUnits) + this._fontRelativeChange;
      } else {
         if (this._fontRelativePercent > 0) {
            this._fontSize = (int)(var1.getHeight() * this._fontRelativePercent);
            this._fontSizeUnits = 0;
         }
      }
   }

   public final void applyToGraphics(Graphics var1) {
      if ((this._set & 2) != 0) {
         var1.setColor(this._colors[1]);
      }

      if ((this._set & 512) != 0) {
         if (this._font == null) {
            var1.setFont(Font.getDefault());
         } else {
            var1.setFont(this._font);
         }
      }

      if ((this._set & 1) != 0) {
         var1.setBackgroundImage(null, 0, 0);
         var1.setBackgroundColor(this._colors[0]);
      }
   }

   final boolean freeStaleObject(int var1) {
      return false;
   }

   public final Background getBackground() {
      return this._background;
   }

   public static final Background getBackground(Field var0) {
      ThemeAttributeSet var1 = getInheritedAttribute(var0, 524288);
      return var1 == null ? null : var1._background;
   }

   public final int getBackgroundOpacity() {
      return this._opacity;
   }

   public final int getFontStrokeOpacity() {
      return this._fontStrokeOpacity;
   }

   public final String getAltFontFamily() {
      return this._altFontFamily;
   }

   public final int getColor(int var1) {
      return this._colors[var1];
   }

   public final String getElement() {
      return this._element;
   }

   public final int getMaximumLineWrapping() {
      return this._maxLineWrap;
   }

   private static final ThemeAttributeSet getInheritedAttribute(Field var0, int var1) {
      if (var0 != null) {
         ThemeAttributeSet var2 = var0.getThemeAttributeSetSpecial();
         if (var2 != null && (var2._set & var1) != 0) {
            return var2;
         }
      }

      while (var0 != null) {
         ThemeAttributeSet var3 = var0.getThemeAttributeSet();
         if (var3 != null && (var3._set & var1) != 0) {
            return var3;
         }

         var0 = var0.getManager();
      }

      return null;
   }

   private static final ThemeAttributeSet getNonInheritedAttribute(Field var0, int var1) {
      if (var0 != null) {
         ThemeAttributeSet var2 = var0.getThemeAttributeSetSpecial();
         if (var2 != null && (var2._set & var1) != 0) {
            return var2;
         }

         var2 = var0.getThemeAttributeSet();
         if (var2 != null && (var2._set & var1) != 0) {
            return var2;
         }
      }

      return null;
   }

   public static final Border getBorder(Field var0) {
      ThemeAttributeSet var1 = getNonInheritedAttribute(var0, 2048);
      return var1 == null ? null : var1._border;
   }

   public static final int getColor(Field var0, int var1) {
      ThemeAttributeSet var2 = getInheritedAttribute(var0, 1 << 0 + var1);
      if (var2 == null) {
         switch (var1) {
            case -1:
               throw new Object();
            case 0:
            default:
               return 16777215;
            case 1:
               return 0;
            case 2:
               return 0;
            case 3:
               return 16777215;
            case 4:
               return 0;
            case 5:
               return 16777215;
            case 6:
               return 0;
            case 7:
               return 0;
         }
      } else {
         return var2._colors[var1];
      }
   }

   public static final XYEdges getEdges(Field var0, int var1) {
      ThemeAttributeSet var2 = getNonInheritedAttribute(var0, 1 << 12 + var1);
      if (var2 != null) {
         switch (var1) {
            case -1:
               break;
            case 0:
            default:
               return var2._padding;
            case 1:
               if (var2._border != null) {
                  return var2._border.getEdges();
               }

               return null;
            case 2:
               return var2._margin;
         }
      }

      return null;
   }

   public final XYRect getPosition() {
      return this._position;
   }

   public static final Bitmap getScrollArrow(Field var0, int var1) {
      ThemeAttributeSet var2 = getInheritedAttribute(var0, 1 << 15 + var1);
      if (var2 != null) {
         if (var2._scrollArrow[var1] == null) {
            var2._scrollArrow[var1] = ThemeManager.getActiveTheme().getBitmap(var2._scrollArrowName[var1]);
            var2._scrollArrowName[var1] = null;
         }

         return var2._scrollArrow[var1];
      } else {
         switch (var1) {
            case -1:
               return null;
            case 0:
            default:
               return Theme.getThemeBitmap(0);
            case 1:
               return Theme.getThemeBitmap(1);
         }
      }
   }

   public static final int getFocusStyle(Field var0) {
      ThemeAttributeSet var1 = getInheritedAttribute(var0, 256);
      if (var1 == null) {
         return Graphics.isColor() ? 3 : 0;
      } else {
         return var1._focusStyle;
      }
   }

   public final Font getFont() {
      return this._font;
   }

   public final Manager getLayout(Object var1) {
      return this._layout != null ? this.getTheme().getLayout(this._layout, var1) : null;
   }

   public final String getLayoutName() {
      return this._layout;
   }

   public final String[] getLayoutParameters() {
      return this._layoutParams;
   }

   public static final int getTextAlignAsDrawStyle(Field var0) {
      ThemeAttributeSet var1 = getInheritedAttribute(var0, 2097152);
      if (var1 == null) {
         return 0;
      }

      switch (var1._textAlign) {
         case 0:
            return 0;
         case 1:
         default:
            return 6;
         case 2:
            return 5;
         case 3:
            return 4;
         case 4:
            return 7;
         case 5:
            return 2;
         case 6:
            return 1;
      }
   }

   final Theme getTheme() {
      return ThemeManager.getActiveTheme();
   }

   public final ThemeAttributeSet$Writer getWriterInternal() {
      return this.getWriterInternal((ResourceFetcher)(new Object()));
   }

   public final ThemeAttributeSet$Writer getWriterInternal(ResourceFetcher var1) {
      return new ThemeAttributeSet$Writer(this, var1);
   }

   public final boolean isBackgroundDefined() {
      return (this._set & 524288) != 0;
   }

   public final String getScrollbarName() {
      return this._scrollbarName;
   }
}
