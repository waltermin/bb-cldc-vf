package net.rim.device.internal.ui;

import net.rim.device.api.ui.UiEngine;

class ApplicationSwitcher$ResumePainting implements Runnable {
   UiEngine _engineToSuspend;

   ApplicationSwitcher$ResumePainting(UiEngine var1) {
      this._engineToSuspend = var1;
   }

   @Override
   public void run() {
      this._engineToSuspend.suspendPainting(false);
      this._engineToSuspend.repaint();
   }
}
