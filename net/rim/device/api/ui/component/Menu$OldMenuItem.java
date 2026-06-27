package net.rim.device.api.ui.component;

import net.rim.device.api.ui.MenuItem;

class Menu$OldMenuItem extends MenuItem {
   Object _cookie;
   int _id;

   public Menu$OldMenuItem(String var1, Object var2, int var3) {
      super(var1, 0, 0);
      this._cookie = var2;
      this._id = var3;
   }

   public Object getCookie() {
      return this._cookie;
   }

   @Override
   public int getId() {
      return this._id;
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: type check");
   }
}
