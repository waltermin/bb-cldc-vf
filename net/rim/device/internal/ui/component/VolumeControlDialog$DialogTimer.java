package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Application;
import net.rim.device.internal.system.InternalServices;

class VolumeControlDialog$DialogTimer implements Runnable {
   private final VolumeControlDialog this$0;

   VolumeControlDialog$DialogTimer(VolumeControlDialog var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      long var1 = InternalServices.getUptime();
      if (var1 - this.this$0._keyClickTime >= 2500) {
         this.this$0.close(0);
      } else {
         Application.getApplication().invokeLater(this, this.this$0._keyClickTime + 2500 - var1, false);
      }
   }
}
