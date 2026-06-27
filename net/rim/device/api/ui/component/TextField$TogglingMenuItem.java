package net.rim.device.api.ui.component;

import net.rim.device.api.ui.MenuItem;
import net.rim.tid.im.SLControlObject;

final class TextField$TogglingMenuItem extends MenuItem {
   int _inputMode;

   final void setInputMode(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public final void run() {
      TextField var1 = (TextField)this.getTarget();
      Object var2 = var1.getInputContext().getInputMethodControlObject();
      ((SLControlObject)var2).actionPerformed(106, new Object(this._inputMode));
   }
}
