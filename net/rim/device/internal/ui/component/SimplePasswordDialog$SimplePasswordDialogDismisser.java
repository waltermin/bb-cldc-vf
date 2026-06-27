package net.rim.device.internal.ui.component;

class SimplePasswordDialog$SimplePasswordDialogDismisser implements Runnable {
   private SimplePasswordDialog _dialog;

   public SimplePasswordDialog$SimplePasswordDialogDismisser(SimplePasswordDialog var1) {
      this._dialog = var1;
   }

   @Override
   public void run() {
      this._dialog.cancel();
   }
}
