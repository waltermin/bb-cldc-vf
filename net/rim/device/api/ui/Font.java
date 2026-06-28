package net.rim.device.api.ui;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.ui.StringBufferGap;
import net.rim.vm.TraceBack;

public class Font {
   private int _style;
   private int _height;
   private int _leading;
   private int _ascent;
   private int _descent;
   private String _name;
   private int _effects;
   private int _antialiasMode;
   private int _A;
   private int _B;
   private int _C;
   private int _D;
   private int _Tx;
   private int _Ty;
   private FontFamily _family;
   private int _effectsStrokeColor;
   private int _effectsFillColor;
   private int[] _transform;
   TextMetrics _textMetrics;
   GlyphMetrics _glyphMetrics;
   public static final int PLAIN;
   public static final int BOLD;
   public static final int ITALIC;
   public static final int EXTRA_BOLD;
   public static final int UNDERLINED;
   public static final int DOTTED_UNDERLINED;
   public static final int BROKEN_LINE_UNDERLINED;
   public static final int STRIKE_THROUGH;
   private static final int FIRST_EFFECT;
   public static final int PLAIN_OUTLINE_EFFECT;
   public static final int GRAY_OUTLINE_EFFECT;
   public static final int COLORED_OUTLINE_EFFECT;
   public static final int ENGRAVED_EFFECT;
   public static final int EMBOSSED_EFFECT;
   public static final int DROP_SHADOW_LEFT_EFFECT;
   public static final int COLORED_DROP_SHADOW_LEFT_EFFECT;
   public static final int DROP_SHADOW_RIGHT_EFFECT;
   public static final int COLORED_DROP_SHADOW_RIGHT_EFFECT;
   public static final int GLOW_EFFECT;
   public static final int MONO_WIDTH_EFFECT;
   public static final int CALLIGRAPHIC_EFFECT;
   public static final int ANTIALIAS_NONE;
   public static final int ANTIALIAS_STANDARD;
   public static final int ANTIALIAS_SUBPIXEL;
   public static final int ANTIALIAS_LOW_RES;
   public static final long GUID_FONT_CHANGED;
   public static final int APPLICATION;
   public static final int TRADITIONAL_CHINESE_HINT;
   public static final int SIMPLIFIED_CHINESE_HINT;
   public static final int JAPANESE_HINT;
   public static final int KOREAN_HINT;
   public static final int HAN_MASK;
   public static final int LATIN_SCRIPT;
   public static final int GREEK_SCRIPT;
   public static final int CYRILLIC_SCRIPT;
   public static final int ARMENIAN_SCRIPT;
   public static final int HEBREW_SCRIPT;
   public static final int ARABIC_SCRIPT;
   public static final int THAI_SCRIPT;
   public static final int HANGUL_SCRIPT;
   public static final int HIRAGANA_SCRIPT;
   public static final int KATAKANA_SCRIPT;
   public static final int BOPOMOFO_SCRIPT;
   public static final int CJK_SCRIPT;
   public static final int COPYRIGHT_NAME_CODE;
   public static final int LICENSE_NAME_CODE;
   private static Font _defaultFont;

   Font(FontFamily family, int style, int height) {
      this(family, style, height, 1, 0, 65536, 0, 0, 65536, 0, 0);
   }

   Font(FontFamily family, int style, int height, int antialiasingMode, int effects, int A, int B, int C, int D, int Tx, int Ty) {
      this(family, style, height, 1, effects, A, B, C, D, Tx, Ty, 0, 16777215);
   }

   Font(
      FontFamily family,
      int style,
      int height,
      int antialiasingMode,
      int effects,
      int A,
      int B,
      int C,
      int D,
      int Tx,
      int Ty,
      int effectsStrokeColor,
      int effectsFillColor
   ) {
   }

   private native void setMetrics();

   public Font derive(int style) {
      return this.derive(style, this._height);
   }

   public Font derive(int style, int height) {
      return this.derive(style, height, 0, this._antialiasMode, this._effects);
   }

   public Font derive(int style, int height, int units) {
      return this.derive(style, height, units, this._antialiasMode, this._effects);
   }

   public Font derive(int style, int height, int units, int antialiasMode, int effects) {
      return this.derive(style, height, units, antialiasMode, effects, this._A, this._B, this._C, this._D, this._Tx, this._Ty);
   }

   public synchronized Font derive(int style, int height, int units, int antialiasMode, int effects, int[] transform) {
      return transform != null && transform.length == 6
         ? this.derive(style, height, units, antialiasMode, effects, transform[0], transform[1], transform[2], transform[3], transform[4], transform[5])
         : this;
   }

   public synchronized Font derive(int style, int height, int units, int antialiasMode, int effects, int A, int B, int C, int D, int Tx, int Ty) {
      Font font = null;
      if (effects >= 0 && 1 <= antialiasMode && 4 >= antialiasMode) {
         if (this._family != null) {
            font = this._family.getFont(style, height, units, antialiasMode, effects, A, B, C, D, Tx, Ty, this._effectsStrokeColor, this._effectsFillColor);
         }

         return font == null ? this : font;
      } else {
         return this;
      }
   }

   public synchronized Font derive(
      int style,
      int height,
      int units,
      int antialiasMode,
      int effects,
      int A,
      int B,
      int C,
      int D,
      int Tx,
      int Ty,
      int effectsStrokeColor,
      int effectsFillColor
   ) {
      Font font = null;
      if (effects >= 0 && 1 <= antialiasMode && 4 >= antialiasMode) {
         if (this._family != null) {
            font = this._family.getFont(style, height, units, antialiasMode, effects, A, B, C, D, Tx, Ty, effectsStrokeColor, effectsFillColor);
         }

         return font == null ? this : font;
      } else {
         return this;
      }
   }

   public final int getAdvance(String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public native int getAdvance(char var1);

   public int getAdvance(String text, int offset, int length) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private native int _getAdvance(String var1, int var2, int var3);

   public int getAdvance(StringBuffer text, int offset, int length) {
      if (text != null) {
         int tLength = text.length();
         if (length == Integer.MAX_VALUE) {
            length = tLength - offset;
         }

         if (offset < 0 || length < 0 || offset + length > tLength) {
            throw new Object();
         } else {
            return tLength == 0 ? 0 : this._getAdvance(text, offset, length);
         }
      } else if ((offset != 0 || length != 0) && length != Integer.MAX_VALUE) {
         throw new Object();
      } else {
         return 0;
      }
   }

   private native int _getAdvance(StringBuffer var1, int var2, int var3);

   public int getAdvance(char[] text, int offset, int length) {
      if (text != null) {
         if (length == Integer.MAX_VALUE) {
            length = text.length - offset;
         }

         if (offset < 0 || length < 0 || offset + length > text.length) {
            throw new Object();
         } else {
            return text.length == 0 ? 0 : this._getAdvance(text, offset, length);
         }
      } else if ((offset != 0 || length != 0) && length != Integer.MAX_VALUE) {
         throw new Object();
      } else {
         return 0;
      }
   }

   private native int _getAdvance(char[] var1, int var2, int var3);

   public int getAntialiasMode() {
      return this._antialiasMode;
   }

   public int getAscent() {
      return this._ascent;
   }

   public int getBaseline() {
      return this._ascent + this._leading;
   }

   public static Font getDefault() {
      return _defaultFont != null ? _defaultFont : FontRegistry.getDefaultFont();
   }

   public static int getDefaultHeight(int units) {
      return FontRegistry.getDefaultHeight(units);
   }

   public int getDescent() {
      return this._descent;
   }

   public int getEffects() {
      return this._effects;
   }

   public FontFamily getFontFamily() {
      return this._family;
   }

   public int getHeight() {
      return this._height;
   }

   public int getHeight(int units) {
      return Ui.convertSize(this._height, 0, units);
   }

   public int getLeading() {
      return this._leading;
   }

   public int getStyle() {
      return this._style;
   }

   public int[] getTransform() {
      return Arrays.copy(this._transform);
   }

   public int getEffectsStrokeColor() {
      return this._effectsStrokeColor;
   }

   public int getEffectsFillColor() {
      return this._effectsFillColor;
   }

   public boolean isBold() {
      return (this._style & 1) != 0;
   }

   public boolean isItalic() {
      return (this._style & 2) != 0;
   }

   public boolean isUnderlined() {
      return (this._style & 4) != 0;
   }

   public boolean isPlain() {
      return (this._style & 15) == 0;
   }

   public synchronized int measureText(String text, int offset, int length, DrawTextParam param, TextMetrics metrics) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private native int _measureText(String var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   public synchronized int measureText(StringBufferGap text, int offset, int length, DrawTextParam param, TextMetrics metrics) {
      if (text != null && offset >= 0 && length >= 0) {
         int tLength = text.length();
         return offset + length <= tLength && tLength != 0 ? this._measureText(text, offset, length, param, metrics) : 0;
      } else {
         return 0;
      }
   }

   private native int _measureText(StringBufferGap var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   public synchronized int measureText(StringBuffer text, int offset, int length, DrawTextParam param, TextMetrics metrics) {
      if (text != null && offset >= 0 && length >= 0) {
         int tLength = text.length();
         return offset + length <= tLength && tLength != 0 ? this._measureText(text, offset, length, param, metrics) : 0;
      } else {
         return 0;
      }
   }

   public synchronized int measureText(char[] text, int offset, int length, DrawTextParam param, TextMetrics metrics) {
      if (text != null && offset >= 0 && length >= 0) {
         int tLength = text.length;
         return offset + length <= tLength && tLength != 0 ? this._measureText(text, offset, length, param, metrics) : 0;
      } else {
         return 0;
      }
   }

   private native int _measureText(StringBuffer var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   private native int _measureText(char[] var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   public int getBounds(String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public int getBounds(StringBuffer text) {
      if (text == null) {
         return 0;
      }

      int tLength = text.length();
      return tLength == 0 ? 0 : this.getBounds(text, 0, tLength);
   }

   public synchronized int getBounds(char aChar) {
      return this.getGlyphMetrics(aChar, this._glyphMetrics) == 0
         ? Math.max(
            this._glyphMetrics.iBearingX + this._glyphMetrics.iBitmapWidth,
            this._glyphMetrics.iAdvance >= this._glyphMetrics.iBitmapWidth ? this._glyphMetrics.iAdvance : this._glyphMetrics.iBitmapWidth
         )
         : this.getAdvance(aChar);
   }

   public synchronized int getBounds(StringBuffer text, int offset, int length) {
      if (text != null && offset >= 0 && length >= 0) {
         int tLength = text.length();
         if (offset + length <= tLength && tLength != 0) {
            this._measureText(text, offset, length, null, this._textMetrics);
            return Math.max(
               this._textMetrics.iBoundsBrX - this._textMetrics.iBoundsTlX,
               this._textMetrics.iAdvanceX >= this._textMetrics.iBoundsBrX ? this._textMetrics.iAdvanceX : this._textMetrics.iBoundsBrX
            );
         } else {
            return 0;
         }
      } else {
         return 0;
      }
   }

   public synchronized int getBounds(StringBufferGap text, int offset, int length) {
      if (text != null && offset >= 0 && length >= 0) {
         int tLength = text.length();
         if (offset + length <= tLength && tLength != 0) {
            this._measureText(text, offset, length, null, this._textMetrics);
            return Math.max(
               this._textMetrics.iBoundsBrX - this._textMetrics.iBoundsTlX,
               this._textMetrics.iAdvanceX >= this._textMetrics.iBoundsBrX ? this._textMetrics.iAdvanceX : this._textMetrics.iBoundsBrX
            );
         } else {
            return 0;
         }
      } else {
         return 0;
      }
   }

   public synchronized int getBounds(String text, int offset, int length) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized int getBounds(char[] text, int offset, int length) {
      if (text == null) {
         return 0;
      } else if (offset >= 0 && length >= 0 && offset + length <= text.length && text.length != 0) {
         this._measureText(text, offset, length, null, this._textMetrics);
         return Math.max(
            this._textMetrics.iBoundsBrX - this._textMetrics.iBoundsTlX,
            this._textMetrics.iAdvanceX >= this._textMetrics.iBoundsBrX ? this._textMetrics.iAdvanceX : this._textMetrics.iBoundsBrX
         );
      } else {
         return 0;
      }
   }

   public void getMetrics(FontMetrics aFontMetrics) {
      this.getMetrics(aFontMetrics, 0, 0);
   }

   public void getMetrics(FontMetrics aFontMetrics, int aScripts) {
      this.getMetrics(aFontMetrics, aScripts, 0);
   }

   public void getMetricsForLocale(FontMetrics aFontMetrics, int aLocale) {
      this.getMetrics(aFontMetrics, 0, aLocale);
   }

   public native void getMetrics(FontMetrics var1, int var2, int var3);

   public native int getGlyphMetrics(char var1, GlyphMetrics var2);

   public static void setDefaultFont(Font defaultFont) {
      _defaultFont = defaultFont;

      try {
         RIMGlobalMessagePoster.postGlobalEvent(Application.getApplication().getProcessId(), -4394903006263251010L, 1, 0, null, null);
      } catch (IllegalStateException var2) {
      }
   }

   public static void setDefaultFontForSystem(Font defaultFont) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void setDefaultFontForSystem(String family, int style, int size, int units) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      if (family != null && size != 0) {
         FontRegistry.setDefaultFont(family, style, size, units);
      } else {
         throw new Object();
      }
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public String getCopyright() {
      return this.getName(0);
   }

   public String getLicense() {
      return this.getName(13);
   }

   public String getName(int aNameCode) {
      char[] s = new char[0];
      this.getName(s, aNameCode);
      return (String)(new Object(s));
   }

   private native void getName(char[] var1, int var2);

   public boolean hasAttributes(
      int aStyle,
      int aHeight,
      int aAntialiasMode,
      int aEffects,
      int aA,
      int aB,
      int aC,
      int aD,
      int aTx,
      int aTy,
      int aEffectsStrokeColor,
      int aEffectsFillColor
   ) {
      return aStyle == this._style
         && aHeight == this._height
         && aAntialiasMode == this._antialiasMode
         && aEffects == this._effects
         && aA == this._A
         && aB == this._B
         && aC == this._C
         && aD == this._D
         && aTx == this._Tx
         && aTy == this._Ty
         && aEffectsStrokeColor == this._effectsStrokeColor
         && aEffectsFillColor == this._effectsFillColor;
   }
}
