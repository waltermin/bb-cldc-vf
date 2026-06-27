package net.rim.device.internal.ui.component;

class BackgroundDialog$ShowDialogDisplayRunnable extends BackgroundDialog$DialogDisplayRunnable {
   private PopupDialog _dialogToShow;

   BackgroundDialog$ShowDialogDisplayRunnable(PopupDialog var1) {
      this._dialogToShow = var1;
   }

   @Override
   public PopupDialog getDialog() {
      return this._dialogToShow;
   }
}
