package net.rim.device.api.memorycleaner;

import net.rim.device.api.synchronization.SyncManager;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.internal.proxy.Proxy;

class MemoryCleanerManager$RemoveListeners implements Runnable {
   private final MemoryCleanerManager this$0;

   private MemoryCleanerManager$RemoveListeners(MemoryCleanerManager var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      Proxy var1 = Proxy.getInstance();
      var1.removeHolsterListener(this.this$0);
      var1.removeRealtimeClockListener(this.this$0);
      var1.removeGlobalEventListener(this.this$0);
      var1.removeSystemListener(this.this$0);
      RIMGlobalMessagePoster.postGlobalEvent(5924166216341050021L);
      SyncManager var2 = SyncManager.getInstance();
      if (var2 != null) {
         var2.removeSyncEventListener(this.this$0);
      }
   }

   MemoryCleanerManager$RemoveListeners(MemoryCleanerManager var1, MemoryCleanerManager$1 var2) {
      this(var1);
   }
}
