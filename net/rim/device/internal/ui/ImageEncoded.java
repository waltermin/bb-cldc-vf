package net.rim.device.internal.ui;

import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Graphics;

public final class ImageEncoded implements Image {
   private EncodedImage _image;
   private boolean _stamp;

   public static final Image create(EncodedImage var0) {
      return new ImageEncoded(var0);
   }

   private ImageEncoded(EncodedImage var1) {
   }

   @Override
   public final int getHeight(int var1, int var2) {
      if (var1 >= this._image.getWidth() && var2 >= this._image.getHeight()) {
         return this._image.getHeight();
      }

      int var3 = (this._image.getWidth() + var1 - 1) / var1;
      var3 = Math.max(var3, (this._image.getHeight() + var2 - 1) / var2);
      return this._image.getHeight() / var3;
   }

   @Override
   public final int getWidth(int var1, int var2) {
      if (var1 >= this._image.getWidth() && var2 >= this._image.getHeight()) {
         return this._image.getWidth();
      }

      int var3 = (this._image.getWidth() + var1 - 1) / var1;
      var3 = Math.max(var3, (this._image.getHeight() + var2 - 1) / var2);
      return this._image.getWidth() / var3;
   }

   @Override
   public final void paint(Graphics var1, int var2, int var3, int var4, int var5) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
