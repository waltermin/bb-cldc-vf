package net.rim.device.internal.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;

public class BorderBitmap extends Border {
   private int _topCorners;
   private int _rightCorners;
   private int _bottomCorners;
   private int _leftCorners;
   private int _x;
   private int _y;
   private int _width;
   private int _height;
   private Bitmap _bitmap;
   private int _rop;
   private Bitmap _bitmapTop;
   private Bitmap _bitmapBottom;
   private Bitmap _bitmapLeft;
   private Bitmap _bitmapRight;
   private Background _background;

   public BorderBitmap(int var1, int var2, int var3, int var4, Bitmap var5) {
      this(var1, var2, var3, var4, var5, var1, var2, var3, var4);
   }

   public BorderBitmap(int var1, int var2, int var3, int var4, Bitmap var5, int var6, int var7, int var8, int var9) {
   }

   private void $initBitmaps() {
      byte var1 = 16;
      int var2 = this._width != 0 ? this._width * ((var1 + this._width - 1) / this._width) : 0;
      int var3 = this._height != 0 ? this._height * ((var1 + this._height - 1) / this._height) : 0;
      this._bitmapTop = (Bitmap)(new Object(var2, this._y));
      this._bitmapBottom = (Bitmap)(new Object(var2, this._bitmap.getHeight() - this._height - this._y));
      this._bitmapLeft = (Bitmap)(new Object(this._x, var3));
      this._bitmapRight = (Bitmap)(new Object(this._bitmap.getWidth() - this._width - this._x, var3));
      this.copy(this._bitmap, this._x, 0, this._width, this._y, this._bitmapTop);
      this.copy(this._bitmap, this._x, this._y + this._height, this._width, this._bitmapBottom.getHeight(), this._bitmapBottom);
      this.copy(this._bitmap, 0, this._y, this._x, this._height, this._bitmapLeft);
      this.copy(this._bitmap, this._x + this._width, this._y, this._bitmapRight.getWidth(), this._height, this._bitmapRight);
      if (this._width == 1 && this._height == 1) {
         int[] var7 = new int[1];
         this._bitmap.getARGB(var7, 0, 1, this._x, this._y, this._width, this._height);
         if (var7[0] >>> 24 != 0) {
            this._background = Background.createSolidBackground(var7[0]);
         }
      } else {
         int[] var4 = new int[this._width * this._height];
         this._bitmap.getARGB(var4, 0, this._width, this._x, this._y, this._width, this._height);

         for (int var5 = var4.length - 1; var5 >= 0; var5--) {
            if (var4[var5] >>> 24 != 0) {
               Object var6 = new Object(var2, var3);
               this.copy(this._bitmap, this._x, this._y, this._width, this._height, (Bitmap)var6);
               this._background = Background.createBitmapBackground((Bitmap)var6);
               return;
            }
         }
      }
   }

   private void copy(Bitmap var1, int var2, int var3, int var4, int var5, Bitmap var6) {
      int[] var7 = new int[var4 * var5];
      var1.getARGB(var7, 0, var4, var2, var3, var4, var5);

      for (int var8 = 0; var8 < var6.getWidth(); var8 += var4) {
         for (int var9 = 0; var9 < var6.getHeight(); var9 += var5) {
            var6.setARGB(var7, 0, var4, var8, var9, var4, var5);
         }
      }
   }

   @Override
   public Background getBackground() {
      return this._background;
   }

   @Override
   public void paint(Graphics var1, XYRect var2) {
      int var3 = this.getTop();
      int var4 = this.getRight();
      int var5 = this.getBottom();
      int var6 = this.getLeft();
      int var7 = var2.Y2() - var5;
      int var8 = var2.y + var3;
      int var9 = var2.X2() - var4;
      int var10 = var2.x + var6;
      int var11 = var2.width - var6 - var4;
      int var12 = var2.height - var3 - var5;
      if (var3 != 0) {
         var1.tileRop(this._rop, var10, var2.y, var11, var3, this._bitmapTop, 0, 0);
      }

      if (var5 != 0) {
         var1.tileRop(this._rop, var10, var7, var11, var5, this._bitmapBottom, 0, 0);
      }

      if (var6 != 0) {
         var1.tileRop(this._rop, var2.x, var8, var6, var12, this._bitmapLeft, 0, 0);
      }

      if (var4 != 0) {
         var1.tileRop(this._rop, var9, var8, var4, var12, this._bitmapRight, 0, 0);
      }

      if (this._topCorners != 0) {
         if (this._leftCorners != 0) {
            var1.drawBitmap(var2.x, var2.y, this._leftCorners, this._topCorners, this._bitmap, 0, 0);
         }

         if (this._rightCorners != 0) {
            var1.drawBitmap(var9, var2.y, this._rightCorners, this._topCorners, this._bitmap, this._x + this._width, 0);
         }
      }

      if (this._bottomCorners != 0) {
         if (this._leftCorners != 0) {
            var1.drawBitmap(var2.x, var7, this._leftCorners, this._bottomCorners, this._bitmap, 0, this._y + this._height);
         }

         if (this._rightCorners != 0) {
            var1.drawBitmap(var9, var7, this._rightCorners, this._bottomCorners, this._bitmap, this._x + this._width, this._y + this._height);
         }
      }
   }
}
