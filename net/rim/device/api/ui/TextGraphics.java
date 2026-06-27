package net.rim.device.api.ui;

import net.rim.device.internal.ui.StringBufferGap;

public class TextGraphics {
   private int _style;
   private int _height;
   private String _name;
   private int _effects;
   private int _antialiasMode;
   private int _A;
   private int _B;
   private int _C;
   private int _D;
   private int _Tx;
   private int _Ty;
   private int _effectsStrokeColor;
   private int _effectsFillColor;
   private int[] _transform;
   private static FontMetrics _fontMetrics;

   public TextGraphics(String var1, int var2) {
   }

   public TextGraphics(Font var1) {
   }

   public int getAntialiasMode() {
      return this._antialiasMode;
   }

   public String getTypefaceName() {
      return this._name;
   }

   public int getEffects() {
      return this._effects;
   }

   public int getHeight() {
      return this._height;
   }

   public int getHeightWithLeading() {
      this.getFontMetrics(_fontMetrics);
      return _fontMetrics.iHeight + _fontMetrics.iLeadingAbove + _fontMetrics.iLeadingBelow;
   }

   public int getStyle() {
      return this._style;
   }

   public int[] getTransform() {
      this._transform[0] = this._A;
      this._transform[1] = this._B;
      this._transform[2] = this._C;
      this._transform[3] = this._D;
      this._transform[4] = this._Tx;
      this._transform[5] = this._Ty;
      return this._transform;
   }

   public int getEffectsStrokeColor() {
      return this._effectsStrokeColor;
   }

   public int getEffectsFillColor() {
      return this._effectsFillColor;
   }

   public void setStyle(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setHeight(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setHeightWithLeading(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private native int _convertToFontEngineSize(int var1);

   public void setTransform(int[] var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setTransform(int var1, int var2, int var3, int var4, int var5, int var6) {
      this._A = var1;
      this._B = var2;
      this._C = var3;
      this._D = var4;
      this._Tx = var5;
      this._Ty = var6;
   }

   public void setEffects(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setAntialiasingMode(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setEffectsStrokeColor(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setEffectsFillColor(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setFontSpec(Font var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setTypefaceName(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public int measureText(String var1, int var2, int var3, DrawTextParam var4, TextMetrics var5) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private native int _measureText(String var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   public int measureText(StringBufferGap var1, int var2, int var3, DrawTextParam var4, TextMetrics var5) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private native int _measureText(StringBufferGap var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   public int measureText(StringBuffer var1, int var2, int var3, DrawTextParam var4, TextMetrics var5) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private native int _measureText(StringBuffer var1, int var2, int var3, DrawTextParam var4, TextMetrics var5);

   public void getFontMetrics(FontMetrics var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private native void _getFontMetrics(FontMetrics var1);

   public int drawText(Graphics var1, String var2, int var3, int var4, int var5, int var6, DrawTextParam var7, TextMetrics var8) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private native int _drawText(Graphics var1, String var2, int var3, int var4, int var5, int var6, DrawTextParam var7, TextMetrics var8);

   public int drawText(Graphics var1, StringBuffer var2, int var3, int var4, int var5, int var6, DrawTextParam var7, TextMetrics var8) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private native int _drawText(Graphics var1, StringBuffer var2, int var3, int var4, int var5, int var6, DrawTextParam var7, TextMetrics var8);

   public int drawText(Graphics var1, StringBufferGap var2, int var3, int var4, int var5, int var6, DrawTextParam var7, TextMetrics var8) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private native int _drawText(Graphics var1, StringBufferGap var2, int var3, int var4, int var5, int var6, DrawTextParam var7, TextMetrics var8);
}
