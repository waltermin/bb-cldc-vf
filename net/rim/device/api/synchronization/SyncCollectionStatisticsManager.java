package net.rim.device.api.synchronization;

import net.rim.vm.Memory;

public final class SyncCollectionStatisticsManager {
   private static final boolean DEBUG;

   public static final int getSyncCollectionSize(SyncCollection var0) {
      SyncObject[] var1 = var0.getSyncObjects();
      return Memory.objectSize(var1);
   }
}
