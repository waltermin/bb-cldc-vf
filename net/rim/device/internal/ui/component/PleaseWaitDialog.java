package net.rim.device.internal.ui.component;

import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.vm.Process;

public class PleaseWaitDialog extends PopupScreen {
   private boolean _displayed;
   private PleaseWaitWorkerThread _workerThread;
   private Throwable _throwable;
   private RichTextField _messageField;
   private Process _workerThreadProcess;
   ResourceBundleFamily _rb;

   public PleaseWaitDialog(PleaseWaitWorkerThread var1) {
      this(null, var1, null);
   }

   public PleaseWaitDialog(PleaseWaitWorkerThread var1, Process var2) {
      this(null, var1, var2);
   }

   public PleaseWaitDialog(String var1, PleaseWaitWorkerThread var2) {
      this(var1, var2, null);
   }

   public PleaseWaitDialog(String var1, PleaseWaitWorkerThread var2, Process var3) {
   }

   @Override
   protected void onUiEngineAttached(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   synchronized void waitForDialog() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void display() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setMessage(String var1) {
      this._messageField.setText(var1);
   }

   void setThrowable(Throwable var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public Throwable getThrowable() {
      return this._throwable;
   }
}
