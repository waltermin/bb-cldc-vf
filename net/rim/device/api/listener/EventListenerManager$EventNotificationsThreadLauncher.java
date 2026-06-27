package net.rim.device.api.listener;

import net.rim.vm.Process;

class EventListenerManager$EventNotificationsThreadLauncher implements Runnable {
   private final EventListenerManager this$0;

   private EventListenerManager$EventNotificationsThreadLauncher(EventListenerManager var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      if (Process.currentProcess().getProcessId() != this.this$0._proxyProcessID) {
         this.this$0._proxy.invokeLater(this);
      } else {
         this.this$0._eventNotificationsThread = new EventListenerManager$EventNotificationsThread(this.this$0, null);
         this.this$0._eventNotificationsThread.start();
      }
   }

   EventListenerManager$EventNotificationsThreadLauncher(EventListenerManager var1, EventListenerManager$1 var2) {
      this(var1);
   }
}
