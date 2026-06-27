package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.internal.i18n.CommonResource;

class TextField$1 extends MenuItem {
   TextField$1(ResourceBundle var1, int var2, int var3, int var4) {
   }

   @Override
   public void run() {
      TextField var1 = (TextField)this.getTarget();
      ResourceBundleFamily var2 = CommonResource.getBundle();
      String[] var3 = new String[]{var2.getString(10043), var2.getString(10044)};
      if (var1.isStyle(2147483648L)) {
         var1.clear(0);
      } else {
         int var4 = Dialog.ask(var2.getString(10042), var3, 0);
         switch (var4) {
            case 0:
               var1.clear(0);
         }
      }
   }
}
