package javax.microedition.lcdui;

public final class Font {
   private net.rim.device.api.ui.Font _font;
   private static Font _defaultFont;
   private static net.rim.device.api.ui.Font _rimDefaultFont;
   public static final int STYLE_PLAIN;
   public static final int STYLE_BOLD;
   public static final int STYLE_ITALIC;
   public static final int STYLE_UNDERLINED;
   public static final int SIZE_SMALL;
   public static final int SIZE_MEDIUM;
   public static final int SIZE_LARGE;
   public static final int FACE_SYSTEM;
   public static final int FACE_MONOSPACE;
   public static final int FACE_PROPORTIONAL;
   public static final int FONT_STATIC_TEXT;
   public static final int FONT_INPUT_TEXT;

   public static final Font getFont(int var0) {
      switch (var0) {
         case -1:
            throw new Object();
         case 0:
         case 1:
         default:
            return getDefaultFont();
      }
   }

   Font(net.rim.device.api.ui.Font var1) {
      this._font = var1;
   }

   final net.rim.device.api.ui.Font getPeer() {
      return this._font;
   }

   public static final Font getDefaultFont() {
      net.rim.device.api.ui.Font var0 = net.rim.device.api.ui.Font.getDefault();
      if (_rimDefaultFont != var0) {
         _rimDefaultFont = var0;
         _defaultFont = new Font(var0);
      }

      return _defaultFont;
   }

   public static final Font getFont(int var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int getStyle() {
      byte var1 = 0;
      if (this._font.isBold()) {
         var1 |= 1;
      }

      if (this._font.isItalic()) {
         var1 |= 2;
      }

      if (this._font.isUnderlined()) {
         var1 |= 4;
      }

      return var1;
   }

   public final int getSize() {
      int var1 = this._font.getHeight(3);
      if (var1 <= 8) {
         return 8;
      } else {
         return var1 <= 10 ? 0 : 16;
      }
   }

   public final int getFace() {
      return 0;
   }

   public final boolean isPlain() {
      return this._font.isPlain();
   }

   public final boolean isBold() {
      return this._font.isBold();
   }

   public final boolean isItalic() {
      return this._font.isItalic();
   }

   public final boolean isUnderlined() {
      return this._font.isUnderlined();
   }

   public final int getHeight() {
      return this._font.getHeight();
   }

   public final int getBaselinePosition() {
      return this._font.getAscent();
   }

   public final int charWidth(char var1) {
      return this._font.getAdvance(var1);
   }

   public final int charsWidth(char[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int stringWidth(String var1) {
      if (var1 == null) {
         throw new Object();
      } else {
         return this._font.getAdvance(var1);
      }
   }

   public final int substringWidth(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
