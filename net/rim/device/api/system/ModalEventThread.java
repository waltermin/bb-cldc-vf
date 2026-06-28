package net.rim.device.api.system;

import net.rim.vm.Message;

public class ModalEventThread extends Thread {
   private boolean _exiting;

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void run() {
      Application app = Application.getApplication();
      boolean var10 = false /* VF: Semaphore variable */;

      try {
         var10 = true;
         Object lock = new Object();

         while (!this.shouldExit()) {
            app.processNextMessage((Message)lock);
         }

         var10 = false;
      } finally {
         if (var10) {
            Object lock = app.getAppEventLock();
            synchronized (lock) {
               this._exiting = true;
               lock.notifyAll();
            }
         }
      }

      Object lock = app.getAppEventLock();
      synchronized (lock) {
         this._exiting = true;
         lock.notifyAll();
      }
   }

   public boolean isExiting() {
      return this._exiting;
   }

   protected boolean shouldExit() {
      throw null;
   }
}
