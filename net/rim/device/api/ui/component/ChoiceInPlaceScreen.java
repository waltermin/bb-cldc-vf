package net.rim.device.api.ui.component;

import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.container.InPlaceScreen;
import net.rim.device.internal.ui.UiInternal;

class ChoiceInPlaceScreen extends InPlaceScreen {
   private XYRect _rect = new XYRect();

   public ChoiceInPlaceScreen(ChoiceField original, ChoiceField fake, long style) {
      super(original, fake, style | 281474976710656L | 1);
   }

   @Override
   protected void applyFont() {
      ChoiceField original = (ChoiceField)this.getOriginalField();
      ChoiceField fake = (ChoiceField)this.getField();
      fake.setFont(original.getFontIfSet());
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
   protected void sublayout(int width, int height) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
