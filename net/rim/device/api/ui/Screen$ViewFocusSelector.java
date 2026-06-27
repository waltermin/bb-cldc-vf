package net.rim.device.api.ui;

class Screen$ViewFocusSelector implements Screen$FocusSelector {
   private int _amount;
   private int _status;
   private int _time;
   private Screen _screen;
   private boolean _success;
   private static Screen$ViewFocusSelector _selector;

   @Override
   public void select() {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public final boolean getSuccess() {
      return this._success;
   }

   public static Screen$ViewFocusSelector getSelector(Screen var0, int var1, int var2, int var3) {
      _selector._screen = var0;
      _selector._amount = var1;
      _selector._status = var2;
      _selector._time = var3;
      _selector._success = false;
      return _selector;
   }
}
