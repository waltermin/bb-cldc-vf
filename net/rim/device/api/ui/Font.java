package net.rim.device.api.ui;

import net.rim.device.api.system.ControlledAccess;
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

   Font(FontFamily var1, int var2, int var3) {
      this(var1, var2, var3, 1, 0, 65536, 0, 0, 65536, 0, 0);
   }

   Font(FontFamily var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
      this(var1, var2, var3, 1, var5, var6, var7, var8, var9, var10, var11, 0, 16777215);
   }

   Font(FontFamily var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13) {
   }

   private native void setMetrics();

   public Font derive(int var1) {
      return this.derive(var1, this._height);
   }

   public Font derive(int var1, int var2) {
      return this.derive(var1, var2, 0, this._antialiasMode, this._effects);
   }

   public Font derive(int var1, int var2, int var3) {
      return this.derive(var1, var2, var3, this._antialiasMode, this._effects);
   }

   public Font derive(int var1, int var2, int var3, int var4, int var5) {
      return this.derive(var1, var2, var3, var4, var5, this._A, this._B, this._C, this._D, this._Tx, this._Ty);
   }

   public synchronized Font derive(int var1, int var2, int var3, int var4, int var5, int[] var6) {
      return var6 != null && var6.length == 6 ? this.derive(var1, var2, var3, var4, var5, var6[0], var6[1], var6[2], var6[3], var6[4], var6[5]) : this;
   }

   public synchronized Font derive(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
      Font var12 = null;
      if (var5 >= 0 && 1 <= var4 && 4 >= var4) {
         if (this._family != null) {
            var12 = this._family.getFont(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, this._effectsStrokeColor, this._effectsFillColor);
         }

         return var12 == null ? this : var12;
      } else {
         return this;
      }
   }

   public synchronized Font derive(
      int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13
   ) {
      Font var14 = null;
      if (var5 >= 0 && 1 <= var4 && 4 >= var4) {
         if (this._family != null) {
            var14 = this._family.getFont(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
         }

         return var14 == null ? this : var14;
      } else {
         return this;
      }
   }

   public final int getAdvance(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public native int getAdvance(char var1);

   public int getAdvance(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private native int _getAdvance(String var1, int var2, int var3);

   public int getAdvance(StringBuffer var1, int var2, int var3) {
      if (var1 != null) {
         int var4 = var1.length();
         if (var3 == Integer.MAX_VALUE) {
            var3 = var4 - var2;
         }

         if (var2 < 0 || var3 < 0 || var2 + var3 > var4) {
            throw new Object();
         } else {
            return var4 == 0 ? 0 : this._getAdvance(var1, var2, var3);
         }
      } else if ((var2 != 0 || var3 != 0) && var3 != Integer.MAX_VALUE) {
         throw new Object();
      } else {
         return 0;
      }
   }

   private native int _getAdvance(StringBuffer var1, int var2, int var3);

   public int getAdvance(char[] var1, int var2, int var3) {
      if (var1 != null) {
         if (var3 == Integer.MAX_VALUE) {
            var3 = var1.length - var2;
         }

         if (var2 < 0 || var3 < 0 || var2 + var3 > var1.length) {
            throw new Object();
         } else {
            return var1.length == 0 ? 0 : this._getAdvance(var1, var2, var3);
         }
      } else if ((var2 != 0 || var3 != 0) && var3 != Integer.MAX_VALUE) {
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

   public static int getDefaultHeight(int var0) {
      return FontRegistry.getDefaultHeight(var0);
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

   public int getHeight(int var1) {
      return Ui.convertSize(this._height, 0, var1);
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

   public synchronized int measureText(String var1, int var2, int var3, DrawTextParam var4, TextMetrics var5) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private native int _measureText(String var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   public synchronized int measureText(StringBufferGap var1, int var2, int var3, DrawTextParam var4, TextMetrics var5) {
      if (var1 != null && var2 >= 0 && var3 >= 0) {
         int var6 = var1.length();
         return var2 + var3 <= var6 && var6 != 0 ? this._measureText(var1, var2, var3, var4, var5) : 0;
      } else {
         return 0;
      }
   }

   private native int _measureText(StringBufferGap var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   public synchronized int measureText(StringBuffer var1, int var2, int var3, DrawTextParam var4, TextMetrics var5) {
      if (var1 != null && var2 >= 0 && var3 >= 0) {
         int var6 = var1.length();
         return var2 + var3 <= var6 && var6 != 0 ? this._measureText(var1, var2, var3, var4, var5) : 0;
      } else {
         return 0;
      }
   }

   public synchronized int measureText(char[] var1, int var2, int var3, DrawTextParam var4, TextMetrics var5) {
      if (var1 != null && var2 >= 0 && var3 >= 0) {
         int var6 = var1.length;
         return var2 + var3 <= var6 && var6 != 0 ? this._measureText(var1, var2, var3, var4, var5) : 0;
      } else {
         return 0;
      }
   }

   private native int _measureText(StringBuffer var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   private native int _measureText(char[] var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   public int getBounds(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public int getBounds(StringBuffer var1) {
      if (var1 == null) {
         return 0;
      }

      int var2 = var1.length();
      return var2 == 0 ? 0 : this.getBounds(var1, 0, var2);
   }

   public synchronized int getBounds(char var1) {
      return this.getGlyphMetrics(var1, this._glyphMetrics) == 0
         ? Math.max(
            this._glyphMetrics.iBearingX + this._glyphMetrics.iBitmapWidth,
            this._glyphMetrics.iAdvance >= this._glyphMetrics.iBitmapWidth ? this._glyphMetrics.iAdvance : this._glyphMetrics.iBitmapWidth
         )
         : this.getAdvance(var1);
   }

   public synchronized int getBounds(StringBuffer var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var3 >= 0) {
         int var4 = var1.length();
         if (var2 + var3 <= var4 && var4 != 0) {
            this._measureText(var1, var2, var3, null, this._textMetrics);
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

   public synchronized int getBounds(StringBufferGap var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var3 >= 0) {
         int var4 = var1.length();
         if (var2 + var3 <= var4 && var4 != 0) {
            this._measureText(var1, var2, var3, null, this._textMetrics);
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

   public synchronized int getBounds(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized int getBounds(char[] var1, int var2, int var3) {
      if (var1 == null) {
         return 0;
      } else if (var2 >= 0 && var3 >= 0 && var2 + var3 <= var1.length && var1.length != 0) {
         this._measureText(var1, var2, var3, null, this._textMetrics);
         return Math.max(
            this._textMetrics.iBoundsBrX - this._textMetrics.iBoundsTlX,
            this._textMetrics.iAdvanceX >= this._textMetrics.iBoundsBrX ? this._textMetrics.iAdvanceX : this._textMetrics.iBoundsBrX
         );
      } else {
         return 0;
      }
   }

   public void getMetrics(FontMetrics var1) {
      this.getMetrics(var1, 0, 0);
   }

   public void getMetrics(FontMetrics var1, int var2) {
      this.getMetrics(var1, var2, 0);
   }

   public void getMetricsForLocale(FontMetrics var1, int var2) {
      this.getMetrics(var1, 0, var2);
   }

   public native void getMetrics(FontMetrics var1, int var2, int var3);

   public native int getGlyphMetrics(char var1, GlyphMetrics var2);

   public static void setDefaultFont(Font var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void setDefaultFontForSystem(Font var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void setDefaultFontForSystem(String var0, int var1, int var2, int var3) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      if (var0 != null && var2 != 0) {
         FontRegistry.setDefaultFont(var0, var1, var2, var3);
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

   public String getName(int var1) {
      char[] var2 = new char[0];
      this.getName(var2, var1);
      return (String)(new Object(var2));
   }

   private native void getName(char[] var1, int var2);

   public boolean hasAttributes(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      return var1 == this._style
         && var2 == this._height
         && var3 == this._antialiasMode
         && var4 == this._effects
         && var5 == this._A
         && var6 == this._B
         && var7 == this._C
         && var8 == this._D
         && var9 == this._Tx
         && var10 == this._Ty
         && var11 == this._effectsStrokeColor
         && var12 == this._effectsFillColor;
   }
}
