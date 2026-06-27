package net.rim.device.internal.ui;

import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.DialogClosedListener;

public class DialogListenerImpl implements DialogClosedListener {
   private Object _sync;
   private Dialog _dialog;

   public DialogListenerImpl(Dialog var1, Object var2) {
      this._sync = var2;
      this._dialog = var1;
   }

   @Override
   public void dialogClosed(Dialog var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
