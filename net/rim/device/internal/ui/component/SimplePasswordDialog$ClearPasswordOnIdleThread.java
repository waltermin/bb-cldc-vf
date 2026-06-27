package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Application;

class SimplePasswordDialog$ClearPasswordOnIdleThread extends Thread {
   private Application _app;
   private boolean _stopThread;
   private final SimplePasswordDialog this$0;

   SimplePasswordDialog$ClearPasswordOnIdleThread(SimplePasswordDialog var1, Application var2) {
      this.this$0 = var1;
      this._app = var2;
   }

   public void stopThread() {
      this._stopThread = true;
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
