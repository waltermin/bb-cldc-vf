package net.rim.device.api.system;

final class MyWLANConnectionListener implements WLANListenerInternal {
   private WLANConnectionListener _listener;

   MyWLANConnectionListener(WLANConnectionListener var1) {
      this._listener = var1;
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void networkSuccess() {
      this._listener.networkConnected();
   }

   @Override
   public final void networkFail(int var1, int var2, int var3) {
      this._listener.networkDisconnected(var1);
   }

   @Override
   public final void radioStatus(boolean var1) {
   }

   @Override
   public final void networkFound(int var1) {
   }

   @Override
   public final void networkApChange() {
   }
}
