package net.rim.device.api.ui.theme;

import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Graphics;

class Theme$ImageDescriptor {
   private String _filename;
   private String _name;
   private boolean _isDefault;
   private ResourceFetcher _resourceFetcher;
   private EncodedImage _image;

   Theme$ImageDescriptor(String var1, ResourceFetcher var2, boolean var3) {
   }

   EncodedImage getImage() {
      if (this._image == null) {
         byte[] var1 = this._resourceFetcher.fetchResource(this._filename);
         this._image = EncodedImage.createEncodedImage(var1, 0, var1.length);
         int var2 = (Graphics.getNumColors() > 2 ? 1 : 0) | 4;
         if (this._name.equals(ThemeConstants.NAVIGATION_UP_ARROW) || this._name.equals(ThemeConstants.NAVIGATION_DOWN_ARROW)) {
            var2 |= 2;
         }

         this._image.setDecodeMode(var2);
      }

      return this._image;
   }

   String getName() {
      return this._name;
   }

   boolean isDefault() {
      return this._isDefault;
   }
}
