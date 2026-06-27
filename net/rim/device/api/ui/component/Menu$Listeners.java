package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.ScreenUiEngineAttachedListener;

class Menu$Listeners implements ScreenUiEngineAttachedListener {
   private final Menu this$0;

   Menu$Listeners(Menu var1) {
      this.this$0 = var1;
   }

   @Override
   public void onScreenUiEngineAttached(Screen var1, boolean var2) {
      if (!var2) {
         this.this$0.close();
         var1.removeScreenUiEngineAttachedListener(this);
      }
   }
}
