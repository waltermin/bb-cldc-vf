package net.rim.device.internal.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.theme.ResourceFetcher;

class NamedBackgroundBitmap extends BackgroundBitmap {
   private ResourceFetcher _resource;
   private String _name;

   NamedBackgroundBitmap(ResourceFetcher var1, String var2) {
   }

   @Override
   public void draw(Graphics var1, XYRect var2) {
      this.initializeBitmap();
      super.draw(var1, var2);
   }

   @Override
   public boolean freeStaleObject(int var1) {
      if (this.getBitmap() != null) {
         this.setBitmap(null);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean isTransparent() {
      this.initializeBitmap();
      return super.isTransparent();
   }

   private void initializeBitmap() {
      if (this.getBitmap() == null) {
         byte[] var1 = this._resource.fetchResource(this._name);
         Bitmap var2 = Bitmap.createBitmapFromPNG(var1, 0, -1);
         this.setBitmap(var2);
      }
   }
}
