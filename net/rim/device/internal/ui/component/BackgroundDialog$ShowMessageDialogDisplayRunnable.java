package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.internal.i18n.CommonResource;

class BackgroundDialog$ShowMessageDialogDisplayRunnable extends BackgroundDialog$DialogDisplayRunnable {
   private String _label;
   private int _priority;

   BackgroundDialog$ShowMessageDialogDisplayRunnable(String var1, int var2) {
      this._label = var1;
      this._priority = var2;
   }

   @Override
   public PopupDialog getDialog() {
      SimpleChoiceDialog var1 = new SimpleChoiceDialog(this._label, CommonResource.getStringArray(10004), 0, Bitmap.getPredefinedBitmap(0), 134217728);
      var1.setStatusPriority(this._priority);
      return var1;
   }
}
