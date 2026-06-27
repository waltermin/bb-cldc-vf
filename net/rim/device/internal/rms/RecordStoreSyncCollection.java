package net.rim.device.internal.rms;

import java.util.Hashtable;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.synchronization.SyncCollection;
import net.rim.device.api.synchronization.SyncCollectionStatistics;
import net.rim.device.api.synchronization.SyncCollectionStatisticsManager;
import net.rim.device.api.synchronization.SyncConverter;
import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.DataBuffer;

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

   private static int getUID(String var0) {
      return var0.hashCode();
   }

   @Override
   public boolean convert(SyncObject var1, DataBuffer var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public SyncObject convert(DataBuffer var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean addSyncObject(SyncObject var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean updateSyncObject(SyncObject var1, SyncObject var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean removeSyncObject(SyncObject var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean removeAllSyncObjects() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public SyncObject[] getSyncObjects() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public SyncObject getSyncObject(int var1) {
      return null;
   }

   @Override
   public boolean isSyncObjectDirty(SyncObject var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void setSyncObjectDirty(SyncObject var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void clearSyncObjectDirty(SyncObject var1) {
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
   public String getSyncName(Locale var1) {
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
