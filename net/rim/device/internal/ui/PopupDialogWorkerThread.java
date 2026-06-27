package net.rim.device.internal.ui;

import net.rim.device.internal.ui.component.PleaseWaitWorkerThread;
import net.rim.device.internal.ui.component.PopupDialog;
import net.rim.device.internal.ui.component.PopupDialogClosedListener;

public class PopupDialogWorkerThread extends PleaseWaitWorkerThread implements PopupDialogClosedListener {
   PopupDialog _popupDialog;
   boolean _popupDialogDisplayed;

   public PopupDialogWorkerThread(PopupDialog var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._popupDialog = var1;
   }

   @Override
   public void doWork() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void dialogClosed(PopupDialog var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
