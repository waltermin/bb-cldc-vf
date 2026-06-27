package net.rim.device.api.synchronization;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.ApplicationRegistry;

public class SyncManager {
   public static final long APP_REG_KEY;
   public static final long GUID_KICK_SERIAL_SYNC_DAEMON;
   public static final long GUID_SET_SERIAL_SYNC_STATUS_MESSAGE;

   protected SyncManager() {
   }

   public static SyncManager getInstance() {
      return (SyncManager)ApplicationRegistry.getApplicationRegistry().waitForStartup(8853100293560663175L);
   }

   public void enableSynchronization(SyncCollection var1) {
      throw null;
   }

   public void enableSynchronization(SyncCollection var1, boolean var2) {
      throw null;
   }

   public void enableSynchronization(SyncCollection var1, boolean var2, int var3) {
      throw null;
   }

   public void disableSynchronization(SyncCollection var1) {
      throw null;
   }

   public void allowOTASync(SyncCollection var1, boolean var2) {
      throw null;
   }

   public boolean isOTASyncAvailable(SyncCollection var1, boolean var2) {
      throw null;
   }

   public void enableOTASync(boolean var1) {
      throw null;
   }

   public void addSyncEventListener(SyncEventListener var1) {
      throw null;
   }

   public void removeSyncEventListener(SyncEventListener var1) {
      throw null;
   }

   public void syncImmediately(SyncCollection var1) {
      throw null;
   }

   public boolean isCollectionResetSupported() {
      throw null;
   }

   public boolean isSyncCompleted(SyncCollection var1) {
      throw null;
   }

   public void triggerSlowSync(SyncCollection var1) {
      throw null;
   }

   public boolean isSerialSyncInProgress() {
      throw null;
   }

   public void setSerialSyncStatusMessage(String var1) {
      throw null;
   }

   public String getLocalizedCollectionName(long var1, String var3, Locale var4) {
      throw null;
   }
}
