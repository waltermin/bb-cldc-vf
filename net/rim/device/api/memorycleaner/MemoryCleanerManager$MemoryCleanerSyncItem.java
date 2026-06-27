package net.rim.device.api.memorycleaner;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.synchronization.ConverterUtilities;
import net.rim.device.api.synchronization.OTASyncCapableSyncItem;
import net.rim.device.api.util.DataBuffer;

class MemoryCleanerManager$MemoryCleanerSyncItem extends OTASyncCapableSyncItem {
   private final MemoryCleanerManager this$0;
   private static final int CLEAN_WHEN_HOLSTERED;
   private static final int CLEAN_WHEN_IDLE;
   private static final int SHOW_APP_ON_RIBBON;
   private static final int IDLE_TIMEOUT;
   private static final int USER_CLEAN_ENABLED;

   MemoryCleanerManager$MemoryCleanerSyncItem(MemoryCleanerManager var1) {
      this.this$0 = var1;
   }

   @Override
   public String getSyncName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String getSyncName(Locale var1) {
      return null;
   }

   @Override
   public int getSyncVersion() {
      return 0;
   }

   @Override
   public synchronized boolean getSyncData(DataBuffer var1, int var2) {
      ConverterUtilities.convertInt(var1, 5, this.this$0._settings._userCleanEnabled ? 1 : 0, 1);
      ConverterUtilities.convertInt(var1, 1, this.this$0._settings._cleanWhenHolstered ? 1 : 0, 1);
      ConverterUtilities.convertInt(var1, 2, this.this$0._settings._cleanWhenIdle ? 1 : 0, 1);
      ConverterUtilities.convertInt(var1, 3, this.this$0._settings._showAppOnRibbon ? 1 : 0, 1);
      ConverterUtilities.writeLong(var1, 4, this.this$0._settings._idleTimeoutSeconds);
      return true;
   }

   @Override
   public boolean setSyncData(DataBuffer var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean removeAllSyncObjects() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
