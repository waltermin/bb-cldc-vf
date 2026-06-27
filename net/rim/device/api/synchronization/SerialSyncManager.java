package net.rim.device.api.synchronization;

import net.rim.device.api.system.ApplicationRegistry;

public class SerialSyncManager {
   public static final long APP_REG_KEY;

   protected SerialSyncManager() {
   }

   public static SerialSyncManager getInstance() {
      return (SerialSyncManager)ApplicationRegistry.getApplicationRegistry().waitForStartup(-8492808042306585331L);
   }

   public void enableSynchronization(SyncCollection var1) {
      throw null;
   }

   public void disableSynchronization(SyncCollection var1) {
      throw null;
   }

   public void addSyncListener(SerialSyncListener var1) {
      throw null;
   }

   public void removeSyncListener(SerialSyncListener var1) {
      throw null;
   }

   public boolean isSyncInProgress() {
      throw null;
   }

   public void setStatusMessage(String var1) {
      throw null;
   }
}
