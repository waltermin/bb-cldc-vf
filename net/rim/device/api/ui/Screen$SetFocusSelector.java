package net.rim.device.api.ui;

class Screen$SetFocusSelector implements Screen$FocusSelector {
   private Field _field;
   private Screen _screen;
   private static Screen$SetFocusSelector _selector;

   public static Screen$FocusSelector getSelector(Screen var0, Field var1) {
      _selector._screen = var0;
      _selector._field = var1;
      return _selector;
   }

   @Override
   public void select() {
      this._screen.onUnfocus();
      this._screen.setFocusChain(this._field);
      this._field = null;
   }
}
