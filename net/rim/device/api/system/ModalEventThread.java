package net.rim.device.api.system;

public class ModalEventThread extends Thread {
   private boolean _exiting;

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean isExiting() {
      return this._exiting;
   }

   protected boolean shouldExit() {
      throw null;
   }
}
