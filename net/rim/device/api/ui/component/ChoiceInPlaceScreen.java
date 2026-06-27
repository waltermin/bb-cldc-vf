package net.rim.device.api.ui.component;

import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.container.InPlaceScreen;
import net.rim.device.internal.ui.UiInternal;

class ChoiceInPlaceScreen extends InPlaceScreen {
   private XYRect _rect = new XYRect();

   public ChoiceInPlaceScreen(ChoiceField var1, ChoiceField var2, long var3) {
      super(var1, var2, var3 | 281474976710656L | 1);
   }

   @Override
   protected void applyFont() {
      ChoiceField var1 = (ChoiceField)this.getOriginalField();
      ChoiceField var2 = (ChoiceField)this.getField();
      var2.setFont(var1.getFontIfSet());
   }

   @Override
   protected void onDisplay() {
      UiInternal.setKeyStateIconsVisible(false);
      super.onDisplay();
   }

   @Override
   protected void onUndisplay() {
      UiInternal.setKeyStateIconsVisible(true);
      super.onUndisplay();
   }

   @Override
   protected void sublayout(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
