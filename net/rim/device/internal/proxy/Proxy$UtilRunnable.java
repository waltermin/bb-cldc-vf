package net.rim.device.internal.proxy;

final class Proxy$UtilRunnable implements Runnable {
   private Thread _thread;

   protected Proxy$UtilRunnable(Runnable var1, boolean var2) {
   }

   @Override
   public final void run() {
      this._thread.start();
   }
}
