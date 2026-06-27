package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Bitmap;

class BackgroundDialog$GetChoiceDialogDisplayRunnable extends BackgroundDialog$DialogDisplayRunnable {
   private String _label;
   private Object[] _choices;
   private int _defaultChoice;
   private Bitmap _bitmap;
   private int _priority;

   BackgroundDialog$GetChoiceDialogDisplayRunnable(String var1, Object[] var2, int var3, Bitmap var4, int var5) {
      this._label = var1;
      this._choices = var2;
      this._defaultChoice = var3;
      this._bitmap = var4;
      this._priority = var5;
   }

   @Override
   public PopupDialog getDialog() {
      SimpleChoiceDialog var1 = new SimpleChoiceDialog(this._label, this._choices, this._defaultChoice, this._bitmap, 134217728);
      var1.setStatusPriority(this._priority);
      return var1;
   }
}
