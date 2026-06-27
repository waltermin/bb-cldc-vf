package net.rim.device.api.ui.component;

import net.rim.device.api.ui.UiEngine;

class Status$PopScreenRunnable implements Runnable {
   private final Status this$0;

   Status$PopScreenRunnable(Status var1) {
      this.this$0 = var1;
   }

   public void init() {
   }

   @Override
   public void run() {
      UiEngine var1 = this.this$0.getUiEngine();
      if (var1 != null) {
         var1.popScreen(this.this$0);
      }
   }
}
