package net.rim.device.internal.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;

class BackgroundBitmap extends Background {
   private Bitmap _bitmap;
   private int _rop;
   private int _left;
   private int _top;

   BackgroundBitmap(Bitmap var1) {
      this.setBitmap(var1);
   }

   protected Bitmap getBitmap() {
      return this._bitmap;
   }

   @Override
   public void draw(Graphics var1, XYRect var2) {
      if (this._bitmap != null && var2.width != 0 && var2.height != 0) {
         int var3;
         switch (this.getPositionY()) {
            case 1:
            default:
               var3 = 0;
               break;
            case 2:
               var3 = var2.x - this._bitmap.getWidth();
               break;
            case 3:
               var3 = var2.x - this._bitmap.getWidth() >> 1;
         }

         int var4;
         switch (this.getPositionY()) {
            case 1:
            default:
               var4 = 0;
               break;
            case 2:
               var4 = var2.y - this._bitmap.getHeight();
               break;
            case 3:
               var4 = var2.y - this._bitmap.getHeight() >> 1;
         }

         if (this.getRepeat() == 1) {
            var1.drawBitmap(var2.x, var2.y, var2.width, var2.height, this._bitmap, this._left + var3, this._top + var4);
            return;
         }

         var1.tileRop(this._rop, var2.x, var2.y, var2.width, var2.height, this._bitmap, this._left + var3, this._top + var4);
      }
   }

   protected void setBitmap(Bitmap var1) {
      this._bitmap = var1;
      if (this._bitmap != null && this._bitmap.hasAlpha()) {
         this._rop = -97;
      } else {
         this._rop = -99;
      }
   }

   public void setOrigin(int var1, int var2) {
      this._left = var1;
      this._top = var2;
   }

   @Override
   public boolean isTransparent() {
      return this._bitmap != null && this._bitmap.hasAlpha();
   }
}
