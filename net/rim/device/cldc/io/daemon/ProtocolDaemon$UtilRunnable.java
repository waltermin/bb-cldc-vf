package net.rim.device.cldc.io.daemon;

final class ProtocolDaemon$UtilRunnable implements Runnable {
   private Thread _thread;

   protected ProtocolDaemon$UtilRunnable(Runnable var1, boolean var2) {
   }

   @Override
   public final void run() {
      this._thread.start();
   }
}
