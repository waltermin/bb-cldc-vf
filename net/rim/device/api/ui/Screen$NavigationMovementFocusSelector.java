package net.rim.device.api.ui;

class Screen$NavigationMovementFocusSelector implements Screen$FocusSelector {
   private int _dx;
   private int _dy;
   private int _status;
   private int _time;
   private Screen _screen;
   private boolean _success;
   private static Screen$NavigationMovementFocusSelector _selector;

   public static Screen$NavigationMovementFocusSelector getSelector(Screen var0, int var1, int var2, int var3, int var4) {
      _selector._screen = var0;
      _selector._dx = var1;
      _selector._dy = var2;
      _selector._status = var3;
      _selector._time = var4;
      return _selector;
   }

   @Override
   public void select() {
      this._success = true;
      int var1 = 0;
      if (this._dy != 0) {
         var1 |= this._screen._delegate.moveFocus(this._dy, this._status | 131072, this._time);
      }

      if (this._dx != 0) {
         var1 |= this._screen._delegate.moveFocus(this._dx, this._status | 65536, this._time);
      }

      if (var1 != 0) {
         this._success = false;
      }
   }

   public final boolean getSuccess() {
      return this._success;
   }
}
