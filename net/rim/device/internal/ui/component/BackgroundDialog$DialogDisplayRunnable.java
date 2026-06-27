package net.rim.device.internal.ui.component;

class BackgroundDialog$DialogDisplayRunnable implements Runnable, PopupDialogClosedListener {
   private PopupDialog _dialog;
   private boolean _dialogOnDisplay;
   private boolean _forceRunInProxy;

   public PopupDialog getDialog() {
      throw null;
   }

   public void forceRunInProxy() {
      this._forceRunInProxy = true;
   }

   public final PopupDialog runInCorrectProcess() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final synchronized void dialogClosed(PopupDialog var1, int var2) {
      this._dialogOnDisplay = false;
      super.notifyAll();
   }
}
