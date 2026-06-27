package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.ui.MenuItem;

class TextField$2 extends MenuItem {
   TextField$2(ResourceBundle var1, int var2, int var3, int var4) {
   }

   @Override
   public void run() {
      Object var1 = this.getTarget();
      if (var1 != null) {
         ((BasicEditField)var1).keyControl('\u0080', 0, 0);
      }
   }
}
