package net.rim.device.cldc.io.datarecovery;

final class DataRecoveryRunnable implements Runnable {
   private Object[] _listeners;
   private int _event;
   private int _linkType;

   protected DataRecoveryRunnable(Object[] var1, int var2, int var3) {
      this._listeners = var1;
      this._event = var2;
      this._linkType = var3;
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
