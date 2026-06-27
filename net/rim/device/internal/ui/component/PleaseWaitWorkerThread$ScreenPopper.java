package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Ui;

class PleaseWaitWorkerThread$ScreenPopper implements Runnable {
   private final PleaseWaitWorkerThread this$0;

   public PleaseWaitWorkerThread$ScreenPopper(PleaseWaitWorkerThread var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      Ui.getUiEngine().popScreen(this.this$0._pleaseWaitDialog);
   }
}
