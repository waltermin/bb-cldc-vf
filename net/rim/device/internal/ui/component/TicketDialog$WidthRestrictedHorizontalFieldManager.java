package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.container.HorizontalFieldManager;

class TicketDialog$WidthRestrictedHorizontalFieldManager extends HorizontalFieldManager {
   private int _restrictedWidth;

   public TicketDialog$WidthRestrictedHorizontalFieldManager(int var1) {
      this._restrictedWidth = var1;
   }

   @Override
   protected void sublayout(int var1, int var2) {
      super.sublayout(var1 - this._restrictedWidth, var2);
   }
}
