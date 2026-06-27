package net.rim.device.api.ui;

class Screen$FindNewFocusSelector implements Screen$FocusSelector {
   private boolean _down;
   private Screen _screen;
   private static Screen$FindNewFocusSelector _selector;

   public static Screen$FocusSelector getSelector(Screen var0, boolean var1) {
      _selector._screen = var0;
      _selector._down = var1;
      return _selector;
   }

   @Override
   public void select() {
      int var1 = this._down ? 1 : -1;
      Manager var2 = this._screen._delegate;
      if (var2.moveFocus(var1, 32768, 0) != 0) {
         this._screen.onUnfocus();
         if (!var2.isFocusable()) {
            return;
         }

         this._screen.onFocus(-var1);
         if (var2.moveFocus(-var1, 32768, 0) != 0) {
            this._screen.onUnfocus();
            return;
         }
      }
   }
}
