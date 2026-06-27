package net.rim.device.api.memorycleaner;

import net.rim.device.api.synchronization.SyncManager;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.internal.proxy.Proxy;

class MemoryCleanerManager$AddListeners implements Runnable {
   private final MemoryCleanerManager this$0;

   private MemoryCleanerManager$AddListeners(MemoryCleanerManager var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      Proxy var1 = Proxy.getInstance();
      var1.addHolsterListener(this.this$0);
      var1.addRealtimeClockListener(this.this$0);
      var1.addGlobalEventListener(this.this$0);
      var1.addSystemListener(this.this$0);
      RIMGlobalMessagePoster.postGlobalEvent(5924166216341050021L);
      SyncManager var2 = SyncManager.getInstance();
      if (var2 != null) {
         var2.addSyncEventListener(this.this$0);
      }
   }

   MemoryCleanerManager$AddListeners(MemoryCleanerManager var1, MemoryCleanerManager$1 var2) {
      this(var1);
   }
}
