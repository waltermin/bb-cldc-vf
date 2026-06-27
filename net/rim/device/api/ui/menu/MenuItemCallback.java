package net.rim.device.api.ui.menu;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Menu;

public class MenuItemCallback extends MenuItem {
   private MenuHandler _handler;

   public MenuItemCallback(MenuHandler var1, ResourceBundle var2, int var3, int var4, int var5) {
      super(var2, var3, var4, var5);
      if (var1 == null) {
         throw new Object();
      }

      this._handler = var1;
   }

   public MenuItemCallback(MenuHandler var1, String var2, int var3, int var4) {
      super(var2, var3, var4);
      if (var1 == null) {
         throw new Object();
      }

      this._handler = var1;
   }

   public static void add(Menu var0, MenuHandler var1, ResourceBundle var2, int[] var3) {
      if (var3.length % 3 != 0) {
         throw new Object();
      }

      int var4 = var3.length;

      for (byte var5 = 0; var5 < var4; var5 += 3) {
         int var6 = var3[var5];
         int var7 = var3[var5 + 1];
         int var8 = var3[var5 + 2];
         if (var6 == -1) {
            var0.add(MenuItem.separator(var7));
         } else {
            var0.add(new MenuItemCallback(var1, var2, var6, var7, var8));
         }
      }
   }

   public static void add(Menu var0, MenuHandler var1, ResourceBundle var2, int[] var3, int var4, int var5) {
      for (int var8 : var3) {
         int var9 = var5 == var8 ? 0 : Integer.MAX_VALUE;
         if (var8 == -1) {
            var0.add(MenuItem.separator(var4));
         } else {
            var0.add(new MenuItemCallback(var1, var2, var8, var4, var9));
         }

         var4++;
      }
   }

   @Override
   public void run() {
      this._handler.menuInvoke(this.getId(), this);
   }
}
