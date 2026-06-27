package net.rim.device.api.ui.component;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.internal.i18n.CommonResource;

class RadioButtonField$ChangeOptionMenuItem extends MenuItem {
   RadioButtonField$ChangeOptionMenuItem() {
      super(CommonResource.getBundle(), 1, 30270, 10);
   }

   RadioButtonField$ChangeOptionMenuItem(String var1) {
      super(var1, 30270, 10);
   }

   @Override
   public void run() {
      RadioButtonField var1 = (RadioButtonField)this.getTarget();
      var1.keyChar('\n', 0, 0);
   }

   @Override
   public int getPriority() {
      return this.getTarget().isMuddy() ? 100 + 1000 : 100 + 0;
   }
}
