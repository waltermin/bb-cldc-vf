package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.ui.MenuItem;

class DateField$1 extends MenuItem {
   DateField$1(ResourceBundle var1, int var2, int var3, int var4) {
   }

   @Override
   public void run() {
      ((DateField)this.getTarget()).changeOptionDialog();
   }

   @Override
   public int getPriority() {
      return this.getTarget().isMuddy() ? 100 + 1000 : 100 + 0;
   }
}
