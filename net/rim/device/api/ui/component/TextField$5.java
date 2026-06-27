package net.rim.device.api.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Screen;
import net.rim.device.internal.i18n.CommonResource;

class TextField$5 implements Runnable {
   private final Screen val$screen;
   private final TextField this$0;

   TextField$5(TextField var1, Screen var2) {
      this.this$0 = var1;
      this.val$screen = var2;
   }

   @Override
   public void run() {
      if (this.val$screen.isGlobal()) {
         Status.show(CommonResource.getString(1010), Bitmap.getPredefinedBitmap(0), 2000, 33554432, true, true, -2147483647);
      } else {
         Status.show(CommonResource.getString(1010));
      }

      this.this$0._fieldFullMsgInvoker = null;
   }
}
