package net.rim.device.api.ui;

final class UiEngineImpl$StatusDisplayedHandler implements Runnable {
   private Screen _screen;
   private boolean _redisplay;
   private XYRect _revokedInvalid;
   private final UiEngineImpl this$0;

   UiEngineImpl$StatusDisplayedHandler(UiEngineImpl _1, Screen screen, boolean inputRequired, boolean redisplay, XYRect revokedInvalid) {
      this.this$0 = _1;
      this._screen = screen;
      this._redisplay = redisplay;
      this._revokedInvalid = revokedInvalid;
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
