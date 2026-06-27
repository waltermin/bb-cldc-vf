package javax.microedition.rms;

import java.util.Hashtable;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.internal.rms.RecordStoreData;
import net.rim.device.internal.rms.RecordStoreManagerProxy;

class RecordStoreManager implements RecordStoreManagerProxy {
   private static final long RECORD_STORE_ID;
   private static final long RECORD_STORE_LISTENER_ID;
   private static PersistentObject _persistentObject;
   private static Hashtable _midletStores;
   private static Hashtable _activeStores;
   private static RecordStoreManager$RecordStoreManagerListener _rsml;

   private RecordStoreManager() {
   }

   static RecordStore getRecordStore(String var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static RecordStore getRecordStore(String var0, String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static void deleteRecordStore(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static String[] getRecordStoreList() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static void commit() {
      _persistentObject.commit();
   }

   @Override
   public void commit(RecordStoreData var1) {
      PersistentObject.commit(var1);
   }

   static boolean checkOwner(RecordStore var0) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   private static String generateMidletSuiteHashKey(boolean var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void deleteRecordStores(String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean recordStoresExistForSuite(String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public RecordStore createRecordStore(RecordStoreData var1) {
      return new RecordStore(var1);
   }
}
