package net.rim.device.api.ui.component;

import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.container.InPlaceScreen;

class DateInPlaceScreen extends InPlaceScreen {
   private XYRect _rect = (XYRect)(new Object());

   public DateInPlaceScreen(DateField var1, DateField var2, long var3) {
      super(var1, var2, var3 | 1);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
