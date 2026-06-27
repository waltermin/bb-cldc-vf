package javax.microedition.lcdui;

import net.rim.device.api.ui.container.VerticalFieldManager;

final class ImageItem$ImageItemManager extends VerticalFieldManager {
   ImageItem _img;

   public ImageItem$ImageItemManager(ImageItem var1) {
      super(1152921504606846976L);
      this._img = var1;
   }

   @Override
   protected final void sublayout(int var1, int var2) {
      if (this._img.getOwner() instanceof Form) {
         super.sublayout(var1, this._img.getPreferredHeight());
      } else {
         super.sublayout(var1, var2);
      }
   }
}
