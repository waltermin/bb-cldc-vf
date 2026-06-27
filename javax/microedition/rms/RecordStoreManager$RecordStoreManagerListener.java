package javax.microedition.rms;

import java.util.Hashtable;
import net.rim.device.api.system.GlobalEventListener;

class RecordStoreManager$RecordStoreManagerListener implements GlobalEventListener {
   private Hashtable _toDelete = (Hashtable)(new Object());
   private static final long TIMEOUT;

   public void deleteRecordStoresWithKey(String var1, Hashtable var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == -4232371946002803201L) {
         this.deleteUnusedRecords(false);
      } else {
         if (var1 == 256826950193107649L) {
            this.cleanUpRecords();
         }
      }
   }

   private void cleanUpRecords() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void deleteUnusedRecords(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public RecordStoreManager$RecordStoreManagerListener() {
      this.deleteUnusedRecords(true);
   }
}
