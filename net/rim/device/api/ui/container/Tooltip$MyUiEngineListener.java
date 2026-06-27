package net.rim.device.api.ui.container;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiEngineListener;

class Tooltip$MyUiEngineListener extends UiEngineListener implements FocusChangeListener {
   private Screen _current;

   @Override
   public void focusChanged(Field var1, int var2) {
      if (var2 == 1 && var1 instanceof Tooltip$TooltipProvider) {
         Tooltip$TooltipPollingThread.reset();
      }
   }

   @Override
   public void onFocus(Screen var1, Screen var2) {
      if (this._current != null) {
         this._current.removeFocusChangeListener(this);
         this._current = null;
      }

      if (var2 != null) {
         var2.addFocusChangeListener(this);
         this._current = var2;
      }
   }
}
