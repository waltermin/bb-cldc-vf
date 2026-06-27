package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.ui.MenuItem;
import net.rim.tid.util.Utils;

class BasicEditField$1 extends MenuItem {
   private final BasicEditField this$0;

   BasicEditField$1(BasicEditField var1, ResourceBundle var2, int var3, int var4, int var5) {
      super(var2, var3, var4, var5);
      this.this$0 = var1;
   }

   @Override
   public void run() {
      Locale[] var1 = Utils.getAvailableInputLocales(true);
      new Object(var1, Utils.getInputLocalesDisplayNames(var1), true, null);
   }
}
