package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Application;

class SimplePasswordDialog$ClearPasswordOnIdleThread extends Thread {
   private Application _app;
   private boolean _stopThread;
   private final SimplePasswordDialog this$0;

   SimplePasswordDialog$ClearPasswordOnIdleThread(SimplePasswordDialog _1, Application app) {
      this.this$0 = _1;
      this._app = app;
   }

   public void stopThread() {
      this._stopThread = true;
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
