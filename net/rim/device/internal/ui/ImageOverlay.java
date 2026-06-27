package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;

public final class ImageOverlay implements Image {
   private Image _image;
   private Image _overlay;

   public static final Image create(Image var0, Image var1) {
      if (var0 != null && var1 != null) {
         return new ImageOverlay(var0, var1);
      } else {
         throw new Object();
      }
   }

   private ImageOverlay(Image var1, Image var2) {
      this._image = var1;
      this._overlay = var2;
   }

   @Override
   public final int getHeight(int var1, int var2) {
      return this._image.getHeight(var1, var2);
   }

   @Override
   public final int getWidth(int var1, int var2) {
      return this._image.getWidth(var1, var2);
   }

   @Override
   public final void paint(Graphics var1, int var2, int var3, int var4, int var5) {
      this._image.paint(var1, var2, var3, var4, var5);
      this._overlay.paint(var1, var2, var3, var4, var5);
   }
}
