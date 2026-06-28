package net.rim.device.api.system;

import net.rim.vm.Message;
import net.rim.vm.MessageQueue;

class ApplicationManagerImpl$StartupGetMessageThread extends Thread {
   private MessageQueue _messageQueue = new MessageQueue();
   private boolean _done;

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private synchronized MessageQueue getMessages() {
      this._done = true;
      Message.abortGet(this);

      try {
         this.wait();
      } catch (InterruptedException var2) {
      }

      return this._messageQueue;
   }
}
