package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.ui.MenuItem;

class GaugeField$1 extends MenuItem {
   GaugeField$1(ResourceBundle var1, int var2, int var3, int var4) {
   }

   @Override
   public void run() {
      ((GaugeField)this.getTarget()).changeOptionDialog();
   }
}
