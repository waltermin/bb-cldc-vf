package javax.microedition.lcdui;

import net.rim.device.api.ui.XYPoint;
import net.rim.device.api.ui.XYRect;

public class Graphics {
   private net.rim.device.api.ui.Graphics _peer;
   private Font _font;
   private int _strokeStyle = 0;
   private XYRect _clipRect = new XYRect();
   private XYPoint _translation = new XYPoint();
   private int[] _xPts4 = new int[4];
   private int[] _yPts4 = new int[4];
   private int[] _xPts3 = new int[3];
   private int[] _yPts3 = new int[3];
   private Image _image;
   public static final int HCENTER;
   public static final int VCENTER;
   public static final int LEFT;
   public static final int RIGHT;
   public static final int TOP;
   public static final int BOTTOM;
   public static final int BASELINE;
   public static final int SOLID;
   public static final int DOTTED;
   static final int[] DUX;
   static final int[] DUY;
   static final int[] DVX;
   static final int[] DVY;

   final net.rim.device.api.ui.Graphics getPeer() {
      return this._peer;
   }

   Graphics(Image image) {
      this._image = image;
   }

   Graphics() {
   }

   void setGraphics(net.rim.device.api.ui.Graphics graphics, boolean isCustomItem) {
      this._font = Font.getDefaultFont();
      this._peer = graphics;
      this._peer.setFont(this._font.getPeer());
      if (isCustomItem) {
         this._clipRect.set(this._peer.getClippingRect());
      } else {
         this._peer.getAbsoluteClippingRect(this._clipRect);
      }

      this._translation.set(0, 0);
      this._peer.setColor(0);
      this.setStrokeStyle(this._strokeStyle);
   }

   public synchronized void translate(int x, int y) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final void updateClipAndOffset() {
      int colour = this._peer.getColor();
      Font font = this._font;
      this._peer.popContext();
      this._peer
         .pushContext(
            this._clipRect.x + this._translation.x,
            this._clipRect.y + this._translation.y,
            this._clipRect.width,
            this._clipRect.height,
            this._translation.x,
            this._translation.y
         );
      this.setFont(font);
      this._peer.setColor(colour);
      this.setStrokeStyle(this._strokeStyle);
   }

   public synchronized int getTranslateX() {
      return this._translation.x;
   }

   public synchronized int getTranslateY() {
      return this._translation.y;
   }

   public synchronized int getColor() {
      return this._peer.getColor();
   }

   public synchronized int getRedComponent() {
      return (this._peer.getColor() & 0xFF0000) >> 16;
   }

   public synchronized int getGreenComponent() {
      return (this._peer.getColor() & 0xFF00) >> 8;
   }

   public synchronized int getBlueComponent() {
      return this._peer.getColor() & 0xFF;
   }

   public synchronized int getGrayScale() {
      int color = this._peer.getColor();
      return (((color & 0xFF0000) >> 16) + ((color & 0xFF00) >> 8) + (color & 0xFF)) / 3;
   }

   public synchronized void setColor(int red, int green, int blue) {
      if (red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255) {
         this._peer.setColor(red << 16 | green << 8 | blue);
      } else {
         throw new IllegalArgumentException("color component must be in range 0-255");
      }
   }

   public synchronized void setColor(int RGB) {
      this._peer.setColor(RGB);
   }

   public void setGrayScale(int value) {
      if (value >= 0 && value <= 255) {
         this.setColor(value, value, value);
      } else {
         throw new IllegalArgumentException("value must be in range 0-255");
      }
   }

   public Font getFont() {
      return this._font;
   }

   public void setStrokeStyle(int style) {
      if (style == 0) {
         this._peer.setStipple(-1);
      } else {
         if (style != 1) {
            throw new IllegalArgumentException("invalid stroke style");
         }

         this._peer.setStipple(-858993460);
      }

      this._strokeStyle = style;
   }

   public int getStrokeStyle() {
      return this._strokeStyle;
   }

   public synchronized void setFont(Font font) {
      if (font == null) {
         this._font = Font.getDefaultFont();
      } else {
         this._font = font;
      }

      this._peer.setFont(this._font.getPeer());
   }

   public synchronized int getClipX() {
      return this._clipRect.x;
   }

   public synchronized int getClipY() {
      return this._clipRect.y;
   }

   public synchronized int getClipWidth() {
      return this._clipRect.width;
   }

   public synchronized int getClipHeight() {
      return this._clipRect.height;
   }

   public synchronized void clipRect(int x, int y, int width, int height) {
      XYRect newClip = new XYRect(x, y, width, height);
      this._clipRect.intersect(newClip);
      this.updateClipAndOffset();
   }

   public synchronized void setClip(int x, int y, int width, int height) {
      this._clipRect.set(x, y, width, height);
      this.updateClipAndOffset();
   }

   public void drawLine(int x1, int y1, int x2, int y2) {
      this._peer.drawLine(x1, y1, x2, y2);
   }

   public void fillRect(int x, int y, int width, int height) {
      this._peer.fillRect(x, y, width, height);
   }

   public void drawRect(int x, int y, int width, int height) {
      this._peer.drawRect(x, y, width + 1, height + 1);
   }

   public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
      this._peer.drawRoundRect(x, y, width + 1, height + 1, arcWidth, arcHeight);
   }

   public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
      this._peer.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
   }

   public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
      this._peer.fillArc(x, y, width, height, startAngle, arcAngle);
   }

   public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
      this._peer.drawArc(x, y, width + 1, height + 1, startAngle, arcAngle);
   }

   public synchronized void drawString(String str, int x, int y, int anchor) {
      if (str == null) {
         throw new NullPointerException();
      }

      this.validateTextAnchor(anchor);
      if ((anchor & -21) == 0) {
         this._peer.drawText(str, x, y);
      } else {
         int width = this._font.getPeer().getBounds(str);
         x = this.translateHorizontalAnchor(x, width, anchor);
         int flags = this.translateVerticalAnchor(anchor);
         this._peer.drawText(str, x, y, flags, width);
      }
   }

   public synchronized void drawSubstring(String str, int offset, int len, int x, int y, int anchor) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public synchronized void drawChar(char character, int x, int y, int anchor) {
      if ((anchor & -21) == 0) {
         this._peer.drawText(character, x, y, this.translateAnchorToDrawStyle(anchor), -1);
      } else {
         this.validateTextAnchor(anchor);
         int width = this._font.getPeer().getBounds(character);
         x = this.translateHorizontalAnchor(x, width, anchor);
         int flags = this.translateVerticalAnchor(anchor);
         this._peer.drawText(character, x, y, flags, width);
      }
   }

   private int translateAnchorToDrawStyle(int anchor) {
      if ((anchor & -128) != 0) {
         throw new IllegalArgumentException();
      }

      int flags;
      switch (anchor & 114) {
         case 0:
         case 16:
            flags = 48;
            break;
         case 32:
            flags = 40;
            break;
         case 64:
            flags = 8;
            break;
         default:
            throw new IllegalArgumentException();
      }

      switch (anchor & 13) {
         case 0:
         case 4:
            return flags | 6;
         case 1:
            return flags | 4;
         case 8:
            return flags | 5;
         default:
            throw new IllegalArgumentException();
      }
   }

   private void validateTextAnchor(int anchor) {
      if ((anchor & -128) != 0) {
         throw new IllegalArgumentException();
      }
   }

   private int translateHorizontalAnchor(int x, int width, int anchor) {
      switch (anchor & 13) {
         case 0:
         case 4:
            return x;
         case 1:
            return x - (width >> 1);
         case 8:
            return x - width;
         default:
            throw new IllegalArgumentException();
      }
   }

   private int translateVerticalAnchor(int anchor) {
      switch (anchor & 114) {
         case 0:
         case 16:
            return 48;
         case 32:
            return 40;
         case 64:
            return 8;
         default:
            throw new IllegalArgumentException();
      }
   }

   public synchronized void drawChars(char[] data, int offset, int length, int x, int y, int anchor) {
      int dlen = data.length;
      if (offset >= 0 && offset <= dlen && offset + length <= dlen && length >= 0) {
         if (length > 0) {
            if ((anchor & -21) == 0) {
               this._peer.drawText(data, offset, length, x, y, this.translateAnchorToDrawStyle(anchor), -1);
               return;
            }

            this.validateTextAnchor(anchor);
            int width = this._font.getPeer().getBounds(data, offset, length);
            x = this.translateHorizontalAnchor(x, width, anchor);
            int flags = this.translateVerticalAnchor(anchor);
            this._peer.drawText(data, offset, length, x, y, flags, width);
         }
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public void drawImage(Image img, int x, int y, int anchor) {
      int width = img.getWidth();
      int height = img.getHeight();
      switch (anchor & 13) {
         case 0:
         case 4:
            break;
         case 1:
            x -= width >> 1;
            break;
         case 8:
            x -= width;
            break;
         default:
            throw new IllegalArgumentException();
      }

      switch (anchor & 114) {
         case 0:
         case 16:
            break;
         case 2:
            y -= height >> 1;
            break;
         case 32:
            y -= height;
            break;
         default:
            throw new IllegalArgumentException();
      }

      this._peer.drawBitmap(x, y, width, height, img.getBitmap(), 0, 0);
   }

   private int calcXShift(int x_dest, int rectLeft, int rectRight, int anchor) {
      int result;
      switch (anchor & 13) {
         case 0:
         case 4:
            result = x_dest - rectLeft;
            break;
         case 1:
            result = x_dest - (rectLeft + (rectRight - rectLeft >> 1));
            break;
         case 8:
            result = x_dest - rectRight;
            break;
         default:
            throw new IllegalArgumentException();
      }

      return result;
   }

   private int calcYShift(int y_dest, int rectTop, int rectBottom, int anchor) {
      int result;
      switch (anchor & 114) {
         case 0:
         case 16:
            result = y_dest - rectTop;
            break;
         case 2:
            result = y_dest - (rectTop + (rectBottom - rectTop >> 1));
            break;
         case 32:
            result = y_dest - rectBottom;
            break;
         default:
            throw new IllegalArgumentException();
      }

      return result;
   }

   public void drawRegion(Image src, int x_src, int y_src, int width, int height, int transform, int x_dest, int y_dest, int anchor) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   public synchronized void copyArea(int x_src, int y_src, int width, int height, int x_dest, int y_dest, int anchor) {
      if (this._image == null) {
         throw new IllegalStateException();
      }

      if (x_src + this._translation.x >= 0
         && y_src + this._translation.y >= 0
         && x_src + this._translation.x + width <= this._image.getWidth()
         && y_src + this._translation.y + height <= this._image.getHeight()) {
         int dx = x_dest - x_src;
         int dy = y_dest - y_src;
         switch (anchor & 13) {
            case 0:
            case 4:
               break;
            case 1:
               dx -= width >> 1;
               break;
            case 8:
               dx -= width;
               break;
            default:
               throw new IllegalArgumentException();
         }

         switch (anchor & 114) {
            case 0:
            case 16:
               break;
            case 2:
               dy -= height >> 1;
               break;
            case 32:
               dy -= height;
               break;
            default:
               throw new IllegalArgumentException();
         }

         this._peer.copyArea(x_src, y_src, width, height, dx, dy);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
      int[] xPts = this._xPts3;
      int[] yPts = this._yPts3;
      xPts[0] = x1;
      xPts[1] = x2;
      xPts[2] = x3;
      yPts[0] = y1;
      yPts[1] = y2;
      yPts[2] = y3;
      this._peer.drawFilledPath(xPts, yPts, null, null);
   }

   public void drawRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha) {
      int rgbDataLength = rgbData.length;
      if (y >= 0 && x >= 0 && scanlength >= 0 && offset >= 0 && offset + height * scanlength <= rgbDataLength) {
         if (width > 0 && height > 0) {
            if (processAlpha) {
               this._peer.drawARGB(rgbData, offset, scanlength, x, y, width, height);
               return;
            }

            this._peer.drawRGB(rgbData, offset, scanlength, x, y, width, height);
         }
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public int getDisplayColor(int color) {
      return net.rim.device.api.ui.Graphics.getDisplayColor(color);
   }
}
