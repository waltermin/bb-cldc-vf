package net.rim.device.api.system;

import net.rim.device.internal.system.InternalServices;

final class Application$TimerCleanupRunnable implements Runnable {
   private final Application this$0;

   Application$TimerCleanupRunnable(Application var1) {
      this.this$0 = var1;
   }

   @Override
   public final void run() {
      for (int var1 = 0; var1 < 20; var1++) {
         if (this.this$0._timedRunnables[var1] != null) {
            InternalServices.killTimer(this.this$0._process.getOSTimerId(var1));
         }
      }
   }
}
