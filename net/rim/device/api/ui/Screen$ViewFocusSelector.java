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

   public static Screen$ViewFocusSelector getSelector(Screen screen, int amount, int status, int time) {
      _selector._screen = screen;
      _selector._amount = amount;
      _selector._status = status;
      _selector._time = time;
      _selector._success = false;
      return _selector;
   }
}
