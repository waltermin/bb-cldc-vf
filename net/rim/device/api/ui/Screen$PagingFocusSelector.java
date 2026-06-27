package net.rim.device.api.ui;

class Screen$PagingFocusSelector implements Screen$FocusSelector {
   private int _status;
   private Screen _screen;
   private boolean _success;
   private static Screen$PagingFocusSelector _selector;

   public static Screen$PagingFocusSelector getSelector(Screen var0, int var1) {
      _selector._screen = var0;
      _selector._status = var1;
      return _selector;
   }

   @Override
   public void select() {
      this._success = true;
      this._screen._delegate.moveFocus(this._status);
   }

   public final boolean getSuccess() {
      return this._success;
   }
}
