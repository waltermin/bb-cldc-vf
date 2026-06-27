package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Application;

public class PleaseWaitWorkerThread extends Thread {
   private PleaseWaitWorkerThread$ScreenPopper _screenPopper = new PleaseWaitWorkerThread$ScreenPopper(this);
   private Application _application;
   public PleaseWaitDialog _pleaseWaitDialog;

   public void setDialog(PleaseWaitDialog var1) {
      this._pleaseWaitDialog = var1;
      this._application = Application.getApplication();
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void doWork() {
      throw null;
   }

   protected void setThrowable(Throwable var1) {
      this._pleaseWaitDialog.setThrowable(var1);
   }
}
