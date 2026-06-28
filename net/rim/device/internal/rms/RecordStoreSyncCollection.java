package net.rim.device.internal.rms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.synchronization.SyncCollection;
import net.rim.device.api.synchronization.SyncCollectionStatistics;
import net.rim.device.api.synchronization.SyncCollectionStatisticsManager;
import net.rim.device.api.synchronization.SyncConverter;
import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.IntHashtable;

public class RecordStoreSyncCollection implements SyncCollection, SyncConverter, SyncCollectionStatistics {
   private static final long RECORD_STORE_ID;
   private static PersistentObject _persistentObject;
   private static Hashtable _allRecordStores;
   private static Hashtable _activeStores;
   private static RecordStoreSyncCollection _recordStoreSyncCollection;

   private RecordStoreSyncCollection() {
   }

   public static RecordStoreSyncCollection getInstance() {
      return _recordStoreSyncCollection;
   }

   private static int getUID(String midletSuiteName) {
      return midletSuiteName.hashCode();
   }

   @Override
   public boolean convert(SyncObject object, DataBuffer buffer, int version) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public SyncObject convert(DataBuffer data, int version, int UID) {
      try {
         ByteArrayOutputStream buffer = (ByteArrayOutputStream)(new Object());

         boolean more;
         do {
            int sectionLength = data.readUnsignedShort();
            more = data.readByte() == 1;
            byte[] section = new byte[sectionLength];
            int read = data.read(section);
            if (read < sectionLength) {
               return null;
            }

            buffer.write(section);
         } while (more);

         DataBuffer dataBuffer = (DataBuffer)(new Object(buffer.toByteArray(), 0, buffer.size(), data.isBigEndian()));
         String midletSuiteName = dataBuffer.readUTF();
         int recordStoreCount = dataBuffer.readInt();
         Hashtable hashtable = (Hashtable)(new Object());

         for (int i = 0; i < recordStoreCount; i++) {
            IntHashtable values = (IntHashtable)(new Object());
            String recordStoreName = dataBuffer.readUTF();
            int rs_version = dataBuffer.readInt();
            int availableId = dataBuffer.readInt();
            int size = dataBuffer.readInt();
            long lastModified = dataBuffer.readLong();
            int recordCount = dataBuffer.readInt();
            boolean containsMIDP_2_0_Data = false;
            int rs_authmode = 0;
            if ((rs_version & 16777216) != 0) {
               containsMIDP_2_0_Data = true;
               rs_authmode = dataBuffer.readInt();
            }

            for (int j = 0; j < recordCount; j++) {
               int recordId = dataBuffer.readInt();
               byte[] value = dataBuffer.readByteArray();
               values.put(recordId, value);
            }

            RecordStoreData recordStoreData = (RecordStoreData)(new Object(recordStoreName, rs_version, availableId, size, lastModified, values, recordCount));
            if (containsMIDP_2_0_Data) {
               recordStoreData.setAuthMode(rs_authmode);
            }

            hashtable.put(recordStoreName, recordStoreData);
         }

         return new RecordStoreSyncCollection$RMSSyncObject(midletSuiteName, hashtable);
      } catch (IOException e) {
         return null;
      }
   }

   @Override
   public boolean addSyncObject(SyncObject object) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public boolean updateSyncObject(SyncObject oldObject, SyncObject newObject) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public boolean removeSyncObject(SyncObject object) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public boolean removeAllSyncObjects() {
      synchronized (_allRecordStores) {
         _allRecordStores.clear();
      }

      synchronized (_activeStores) {
         _activeStores.clear();
         _persistentObject.commit();
         return true;
      }
   }

   @Override
   public SyncObject[] getSyncObjects() {
      synchronized (_allRecordStores) {
         int size = _allRecordStores.size();
         SyncObject[] so = new SyncObject[size];
         Enumeration keys = _allRecordStores.keys();

         for (int i = 0; keys.hasMoreElements(); i++) {
            String midletSuiteName = (String)keys.nextElement();
            Hashtable data = (Hashtable)_allRecordStores.get(midletSuiteName);
            so[i] = new RecordStoreSyncCollection$RMSSyncObject(midletSuiteName, data);
         }

         return so;
      }
   }

   @Override
   public SyncObject getSyncObject(int uid) {
      return null;
   }

   @Override
   public boolean isSyncObjectDirty(SyncObject object) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void setSyncObjectDirty(SyncObject object) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void clearSyncObjectDirty(SyncObject object) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int getSyncObjectCount() {
      return _allRecordStores.size();
   }

   @Override
   public int getSyncVersion() {
      return 1;
   }

   @Override
   public String getSyncName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String getSyncName(Locale locale) {
      return null;
   }

   @Override
   public SyncConverter getSyncConverter() {
      return this;
   }

   @Override
   public void beginTransaction() {
   }

   @Override
   public void endTransaction() {
   }

   @Override
   public synchronized int getSyncCollectionSize() {
      return SyncCollectionStatisticsManager.getSyncCollectionSize(this);
   }
}
