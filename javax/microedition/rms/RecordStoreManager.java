package javax.microedition.rms;

import java.util.Enumeration;
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

   static RecordStore getRecordStore(String recordStoreName, boolean createIfNecessary) {
      RecordStoreData recordStoreData;
      synchronized (_midletStores) {
         recordStoreData = (RecordStoreData)_midletStores.get(recordStoreName);
         if (recordStoreData == null) {
            if (!createIfNecessary) {
               throw new RecordStoreNotFoundException(recordStoreName);
            }

            recordStoreData = new RecordStoreData(recordStoreName);
            _midletStores.put(recordStoreName, recordStoreData);
            PersistentObject.commit(_midletStores);
         }
      }

      synchronized (_activeStores) {
         RecordStore recordStore = (RecordStore)_activeStores.get(recordStoreData);
         if (recordStore == null) {
            recordStore = new RecordStore(recordStoreData);
            _activeStores.put(recordStoreData, recordStore);
         }

         return recordStore;
      }
   }

   static RecordStore getRecordStore(String recordStoreName, String vendorName, String suiteName) {
      String midletPropertiesHashString = suiteName + vendorName;
      Hashtable allRecordStores = (Hashtable)_persistentObject.getContents();
      RecordStoreData recordStoreData;
      synchronized (allRecordStores) {
         Hashtable midletSuiteStores = (Hashtable)allRecordStores.get(midletPropertiesHashString);
         if (midletSuiteStores == null) {
            throw new RecordStoreNotFoundException();
         }

         recordStoreData = (RecordStoreData)midletSuiteStores.get(recordStoreName);
         if (recordStoreData == null) {
            throw new RecordStoreNotFoundException(recordStoreName);
         }
      }

      synchronized (_activeStores) {
         RecordStore recordStore = (RecordStore)_activeStores.get(recordStoreData);
         if (recordStore == null) {
            recordStore = new RecordStore(recordStoreData);
            _activeStores.put(recordStoreData, recordStore);
         }

         return recordStore;
      }
   }

   static void deleteRecordStore(String recordStoreName) {
      synchronized (_activeStores) {
         RecordStore recordStore = getRecordStore(recordStoreName, false);
         if (recordStore.isOpen()) {
            throw new RecordStoreException(recordStoreName);
         }

         _activeStores.remove(recordStore._recordStoreData);
         synchronized (_midletStores) {
            _midletStores.remove(recordStoreName);
            PersistentObject.commit(_midletStores);
         }
      }
   }

   static String[] getRecordStoreList() {
      String[] list = null;
      synchronized (_midletStores) {
         Enumeration keys = _midletStores.keys();
         if (_midletStores.size() > 0) {
            list = new String[_midletStores.size()];

            for (int i = 0; keys.hasMoreElements(); i++) {
               list[i] = (String)keys.nextElement();
            }
         }

         return list;
      }
   }

   static void commit() {
      _persistentObject.commit();
   }

   @Override
   public void commit(RecordStoreData recordStoreData) {
      PersistentObject.commit(recordStoreData);
   }

   static boolean checkOwner(RecordStore recordStore) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   private static String generateMidletSuiteHashKey(boolean includeVendorName) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void deleteRecordStores(String midletSuiteName, String midletSuiteVendor) {
      String midletPropertiesHashString = midletSuiteVendor == null ? midletSuiteName : midletSuiteName + midletSuiteVendor;
      synchronized (_persistentObject) {
         Hashtable allRecordStores = (Hashtable)_persistentObject.getContents();
         _rsml.deleteRecordStoresWithKey(midletPropertiesHashString, allRecordStores);
         _persistentObject.commit();
      }
   }

   @Override
   public boolean recordStoresExistForSuite(String midletSuiteName, String midletSuiteVendor) {
      String midletPropertiesHashString = midletSuiteVendor == null ? midletSuiteName : midletSuiteName + midletSuiteVendor;
      synchronized (_persistentObject) {
         Hashtable allRecordStores = (Hashtable)_persistentObject.getContents();
         return allRecordStores.containsKey(midletPropertiesHashString);
      }
   }

   @Override
   public RecordStore createRecordStore(RecordStoreData data) {
      return new RecordStore(data);
   }
}
