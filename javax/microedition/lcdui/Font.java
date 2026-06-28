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

   public static final Font getFont(int fontSpecifier) {
      switch (fontSpecifier) {
         case -1:
            throw new Object();
         case 0:
         case 1:
         default:
            return getDefaultFont();
      }
   }

   Font(net.rim.device.api.ui.Font font) {
      this._font = font;
   }

   final net.rim.device.api.ui.Font getPeer() {
      return this._font;
   }

   public static final Font getDefaultFont() {
      net.rim.device.api.ui.Font rimDefaultFont = net.rim.device.api.ui.Font.getDefault();
      if (_rimDefaultFont != rimDefaultFont) {
         _rimDefaultFont = rimDefaultFont;
         _defaultFont = new Font(rimDefaultFont);
      }

      return _defaultFont;
   }

   public static final Font getFont(int face, int style, int size) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int getStyle() {
      int style = 0;
      if (this._font.isBold()) {
         style |= 1;
      }

      if (this._font.isItalic()) {
         style |= 2;
      }

      if (this._font.isUnderlined()) {
         style |= 4;
      }

      return style;
   }

   public final int getSize() {
      int size = this._font.getHeight(3);
      if (size <= 8) {
         return 8;
      } else {
         return size <= 10 ? 0 : 16;
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

   public final int charWidth(char ch) {
      return this._font.getAdvance(ch);
   }

   public final int charsWidth(char[] ch, int offset, int length) {
      if (ch == null) {
         throw new Object();
      }

      if (offset >= 0 && offset <= ch.length && offset + length <= ch.length && length >= 0 && length <= ch.length) {
         try {
            return this._font.getAdvance(ch, offset, length);
         } catch (IllegalArgumentException iae) {
            throw new Object();
         }
      } else {
         throw new Object();
      }
   }

   public final int stringWidth(String str) {
      if (str == null) {
         throw new Object();
      } else {
         return this._font.getAdvance(str);
      }
   }

   public final int substringWidth(String str, int offset, int len) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
