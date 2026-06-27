package net.rim.device.internal.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;

public final class ImageBitmap implements Image {
   private Bitmap _bitmap;

   public static final Image create(Bitmap var0) {
      if (var0 == null) {
         throw new Object();
      } else {
         return new ImageBitmap(var0);
      }
   }

   private ImageBitmap(Bitmap var1) {
      this._bitmap = var1;
   }

   @Override
   public final int getHeight(int var1, int var2) {
      return Math.min(this._bitmap.getHeight(), var2);
   }

   @Override
   public final int getWidth(int var1, int var2) {
      return Math.min(this._bitmap.getWidth(), var1);
   }

   @Override
   public final void paint(Graphics var1, int var2, int var3, int var4, int var5) {
      var2 += var4 - this._bitmap.getWidth() >> 1;
      var3 += var5 - this._bitmap.getHeight() >> 1;
      if (Graphics.isColor() && this._bitmap.getType() == 129 && this._bitmap.hasAlpha()) {
         var1.rop(-96, var2, var3, var4, var5, this._bitmap, 0, 0);
      } else {
         var1.drawBitmap(var2, var3, var4, var5, this._bitmap, 0, 0);
      }
   }
}
