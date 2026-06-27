package javax.microedition.lcdui;

import net.rim.device.api.ui.XYPoint;
import net.rim.device.api.ui.XYRect;

public class Graphics {
   private net.rim.device.api.ui.Graphics _peer;
   private Font _font;
   private int _strokeStyle = 0;
   private XYRect _clipRect = (XYRect)(new Object());
   private XYPoint _translation = (XYPoint)(new Object());
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

   Graphics(Image var1) {
      this._image = var1;
   }

   Graphics() {
   }

   void setGraphics(net.rim.device.api.ui.Graphics var1, boolean var2) {
      this._font = Font.getDefaultFont();
      this._peer = var1;
      this._peer.setFont(this._font.getPeer());
      if (var2) {
         this._clipRect.set(this._peer.getClippingRect());
      } else {
         this._peer.getAbsoluteClippingRect(this._clipRect);
      }

      this._translation.set(0, 0);
      this._peer.setColor(0);
      this.setStrokeStyle(this._strokeStyle);
   }

   public synchronized void translate(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final void updateClipAndOffset() {
      int var1 = this._peer.getColor();
      Font var2 = this._font;
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
      this.setFont(var2);
      this._peer.setColor(var1);
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
      int var1 = this._peer.getColor();
      return (((var1 & 0xFF0000) >> 16) + ((var1 & 0xFF00) >> 8) + (var1 & 0xFF)) / 3;
   }

   public synchronized void setColor(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void setColor(int var1) {
      this._peer.setColor(var1);
   }

   public void setGrayScale(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public Font getFont() {
      return this._font;
   }

   public void setStrokeStyle(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public int getStrokeStyle() {
      return this._strokeStyle;
   }

   public synchronized void setFont(Font var1) {
      if (var1 == null) {
         this._font = Font.getDefaultFont();
      } else {
         this._font = var1;
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

   public synchronized void clipRect(int var1, int var2, int var3, int var4) {
      Object var5 = new Object(var1, var2, var3, var4);
      this._clipRect.intersect((XYRect)var5);
      this.updateClipAndOffset();
   }

   public synchronized void setClip(int var1, int var2, int var3, int var4) {
      this._clipRect.set(var1, var2, var3, var4);
      this.updateClipAndOffset();
   }

   public void drawLine(int var1, int var2, int var3, int var4) {
      this._peer.drawLine(var1, var2, var3, var4);
   }

   public void fillRect(int var1, int var2, int var3, int var4) {
      this._peer.fillRect(var1, var2, var3, var4);
   }

   public void drawRect(int var1, int var2, int var3, int var4) {
      this._peer.drawRect(var1, var2, var3 + 1, var4 + 1);
   }

   public void drawRoundRect(int var1, int var2, int var3, int var4, int var5, int var6) {
      this._peer.drawRoundRect(var1, var2, var3 + 1, var4 + 1, var5, var6);
   }

   public void fillRoundRect(int var1, int var2, int var3, int var4, int var5, int var6) {
      this._peer.fillRoundRect(var1, var2, var3, var4, var5, var6);
   }

   public void fillArc(int var1, int var2, int var3, int var4, int var5, int var6) {
      this._peer.fillArc(var1, var2, var3, var4, var5, var6);
   }

   public void drawArc(int var1, int var2, int var3, int var4, int var5, int var6) {
      this._peer.drawArc(var1, var2, var3 + 1, var4 + 1, var5, var6);
   }

   public synchronized void drawString(String var1, int var2, int var3, int var4) {
      if (var1 == null) {
         throw new Object();
      }

      this.validateTextAnchor(var4);
      if ((var4 & -21) == 0) {
         this._peer.drawText(var1, var2, var3);
      } else {
         int var5 = this._font.getPeer().getBounds(var1);
         var2 = this.translateHorizontalAnchor(var2, var5, var4);
         int var6 = this.translateVerticalAnchor(var4);
         this._peer.drawText(var1, var2, var3, var6, var5);
      }
   }

   public synchronized void drawSubstring(String var1, int var2, int var3, int var4, int var5, int var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized void drawChar(char var1, int var2, int var3, int var4) {
      if ((var4 & -21) == 0) {
         this._peer.drawText(var1, var2, var3, this.translateAnchorToDrawStyle(var4), -1);
      } else {
         this.validateTextAnchor(var4);
         int var5 = this._font.getPeer().getBounds(var1);
         var2 = this.translateHorizontalAnchor(var2, var5, var4);
         int var6 = this.translateVerticalAnchor(var4);
         this._peer.drawText(var1, var2, var3, var6, var5);
      }
   }

   private int translateAnchorToDrawStyle(int var1) {
      if ((var1 & -128) != 0) {
         throw new Object();
      }

      byte var2;
      switch (var1 & 114) {
         case 0:
         case 16:
            var2 = 48;
            break;
         case 32:
            var2 = 40;
            break;
         case 64:
            var2 = 8;
            break;
         default:
            throw new Object();
      }

      switch (var1 & 13) {
         case 0:
         case 4:
            return var2 | 6;
         case 1:
            return var2 | 4;
         case 8:
            return var2 | 5;
         default:
            throw new Object();
      }
   }

   private void validateTextAnchor(int var1) {
      if ((var1 & -128) != 0) {
         throw new Object();
      }
   }

   private int translateHorizontalAnchor(int var1, int var2, int var3) {
      switch (var3 & 13) {
         case 0:
         case 4:
            return var1;
         case 1:
            return var1 - (var2 >> 1);
         case 8:
            return var1 - var2;
         default:
            throw new Object();
      }
   }

   private int translateVerticalAnchor(int var1) {
      switch (var1 & 114) {
         case 0:
         case 16:
            return 48;
         case 32:
            return 40;
         case 64:
            return 8;
         default:
            throw new Object();
      }
   }

   public synchronized void drawChars(char[] var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = var1.length;
      if (var2 >= 0 && var2 <= var7 && var2 + var3 <= var7 && var3 >= 0) {
         if (var3 > 0) {
            if ((var6 & -21) == 0) {
               this._peer.drawText(var1, var2, var3, var4, var5, this.translateAnchorToDrawStyle(var6), -1);
               return;
            }

            this.validateTextAnchor(var6);
            int var8 = this._font.getPeer().getBounds(var1, var2, var3);
            var4 = this.translateHorizontalAnchor(var4, var8, var6);
            int var9 = this.translateVerticalAnchor(var6);
            this._peer.drawText(var1, var2, var3, var4, var5, var9, var8);
         }
      } else {
         throw new Object();
      }
   }

   public void drawImage(Image var1, int var2, int var3, int var4) {
      int var5 = var1.getWidth();
      int var6 = var1.getHeight();
      switch (var4 & 13) {
         case 0:
         case 4:
            break;
         case 1:
            var2 -= var5 >> 1;
            break;
         case 8:
            var2 -= var5;
            break;
         default:
            throw new Object();
      }

      switch (var4 & 114) {
         case 0:
         case 16:
            break;
         case 2:
            var3 -= var6 >> 1;
            break;
         case 32:
            var3 -= var6;
            break;
         default:
            throw new Object();
      }

      this._peer.drawBitmap(var2, var3, var5, var6, var1.getBitmap(), 0, 0);
   }

   private int calcXShift(int var1, int var2, int var3, int var4) {
      int var5;
      switch (var4 & 13) {
         case 0:
         case 4:
            var5 = var1 - var2;
            break;
         case 1:
            var5 = var1 - (var2 + (var3 - var2 >> 1));
            break;
         case 8:
            var5 = var1 - var3;
            break;
         default:
            throw new Object();
      }

      return var5;
   }

   private int calcYShift(int var1, int var2, int var3, int var4) {
      int var5;
      switch (var4 & 114) {
         case 0:
         case 16:
            var5 = var1 - var2;
            break;
         case 2:
            var5 = var1 - (var2 + (var3 - var2 >> 1));
            break;
         case 32:
            var5 = var1 - var3;
            break;
         default:
            throw new Object();
      }

      return var5;
   }

   public void drawRegion(Image var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   public synchronized void copyArea(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      if (this._image == null) {
         throw new Object();
      }

      if (var1 + this._translation.x >= 0
         && var2 + this._translation.y >= 0
         && var1 + this._translation.x + var3 <= this._image.getWidth()
         && var2 + this._translation.y + var4 <= this._image.getHeight()) {
         int var8 = var5 - var1;
         int var9 = var6 - var2;
         switch (var7 & 13) {
            case 0:
            case 4:
               break;
            case 1:
               var8 -= var3 >> 1;
               break;
            case 8:
               var8 -= var3;
               break;
            default:
               throw new Object();
         }

         switch (var7 & 114) {
            case 0:
            case 16:
               break;
            case 2:
               var9 -= var4 >> 1;
               break;
            case 32:
               var9 -= var4;
               break;
            default:
               throw new Object();
         }

         this._peer.copyArea(var1, var2, var3, var4, var8, var9);
      } else {
         throw new Object();
      }
   }

   public void fillTriangle(int var1, int var2, int var3, int var4, int var5, int var6) {
      int[] var7 = this._xPts3;
      int[] var8 = this._yPts3;
      var7[0] = var1;
      var7[1] = var3;
      var7[2] = var5;
      var8[0] = var2;
      var8[1] = var4;
      var8[2] = var6;
      this._peer.drawFilledPath(var7, var8, null, null);
   }

   public void drawRGB(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      int var9 = var1.length;
      if (var5 >= 0 && var4 >= 0 && var3 >= 0 && var2 >= 0 && var2 + var7 * var3 <= var9) {
         if (var6 > 0 && var7 > 0) {
            if (var8) {
               this._peer.drawARGB(var1, var2, var3, var4, var5, var6, var7);
               return;
            }

            this._peer.drawRGB(var1, var2, var3, var4, var5, var6, var7);
         }
      } else {
         throw new Object();
      }
   }

   public int getDisplayColor(int var1) {
      return net.rim.device.api.ui.Graphics.getDisplayColor(var1);
   }
}
