package net.rim.device.api.ui;

class Screen$TrackwheelRollFocusSelector implements Screen$FocusSelector {
   private int _amount;
   private int _status;
   private int _time;
   private Screen _screen;
   private boolean _success;
   private static Screen$TrackwheelRollFocusSelector _selector;

   public static Screen$TrackwheelRollFocusSelector getSelector(Screen var0, int var1, int var2, int var3) {
      _selector._screen = var0;
      _selector._amount = var1;
      _selector._status = var2;
      _selector._time = var3;
      return _selector;
   }

   @Override
   public void select() {
      this._success = true;
      int var1 = this._screen._delegate.moveFocus(this._amount, this._status, this._time);
      if (var1 != 0) {
         this._success = false;
      }
   }

   public final boolean getSuccess() {
      return this._success;
   }
}
