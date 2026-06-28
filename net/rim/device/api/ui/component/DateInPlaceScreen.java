package net.rim.device.api.ui.component;

import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.container.InPlaceScreen;

class DateInPlaceScreen extends InPlaceScreen {
   private XYRect _rect = (XYRect)(new Object());

   public DateInPlaceScreen(DateField original, DateField fake, long style) {
      super(original, fake, style | 1);
   }

   @Override
   protected void sublayout(int width, int height) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
