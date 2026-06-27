package net.rim.device.internal.ui.component;

class BackgroundDialog$GetInputDialogDisplayRunnable extends BackgroundDialog$DialogDisplayRunnable {
   private String _label;
   private int _minLength;
   private int _maxLength;
   private int _type;

   BackgroundDialog$GetInputDialogDisplayRunnable(String var1, int var2, int var3, int var4) {
      this._label = var1;
      this._minLength = var2;
      this._maxLength = var3;
      this._type = var4;
   }

   @Override
   public PopupDialog getDialog() {
      return new SimpleOKCancelInputDialog(this._type, this._label, this._minLength, this._maxLength, 134217728);
   }
}
