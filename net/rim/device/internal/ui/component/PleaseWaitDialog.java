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

   public PleaseWaitDialog(PleaseWaitWorkerThread workerThread) {
      this(null, workerThread, null);
   }

   public PleaseWaitDialog(PleaseWaitWorkerThread workerThread, Process workerThreadProcess) {
      this(null, workerThread, workerThreadProcess);
   }

   public PleaseWaitDialog(String message, PleaseWaitWorkerThread workerThread) {
      this(message, workerThread, null);
   }

   public PleaseWaitDialog(String message, PleaseWaitWorkerThread workerThread, Process workerThreadProcess) {
   }

   @Override
   protected void onUiEngineAttached(boolean attached) {
      super.onUiEngineAttached(attached);
      if (attached) {
         synchronized (this) {
            this.notify();
            this._displayed = true;
         }
      }
   }

   synchronized void waitForDialog() {
      if (!this._displayed) {
         try {
            this.wait();
         } catch (InterruptedException var2) {
         }
      }

      this._displayed = false;
   }

   public void display() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setMessage(String message) {
      this._messageField.setText(message);
   }

   void setThrowable(Throwable t) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public Throwable getThrowable() {
      return this._throwable;
   }
}
