package net.rim.device.api.ui.menu;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.util.Arrays;

public final class CascadingMenuItem extends MenuItem {
   private MenuItem[] _subItems;

   public CascadingMenuItem(MenuItem[] var1, ResourceBundle var2, int var3, int var4, int var5) {
      super(var2, var3, var4, var5);
      this._subItems = var1;
   }

   public final void addItem(MenuItem var1) {
      if (this._subItems != null) {
         Arrays.add(this._subItems, var1);
      } else {
         this._subItems = new MenuItem[]{var1};
      }
   }

   public final void invokeSubMenu(Menu var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void run() {
   }
}
