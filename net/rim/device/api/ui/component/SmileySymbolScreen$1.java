package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Field;

class SmileySymbolScreen$1 implements Runnable {
   private final Field val$field;

   SmileySymbolScreen$1(Field var1) {
      this.val$field = var1;
   }

   @Override
   public void run() {
      if (!SmileySymbolScreen.getSymbolScreen().isDisplayed()) {
         SmileySymbolScreen.getSymbolScreen().doModal(this.val$field);
      }
   }
}
