package net.rim.device.internal.applicationcontrol;

import java.util.Vector;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.util.CollectionListenerManager;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.synchronization.OTASyncCapable;
import net.rim.device.api.synchronization.SyncCollection;
import net.rim.device.api.synchronization.SyncCollectionSchema;
import net.rim.device.api.synchronization.SyncCollectionStatistics;
import net.rim.device.api.synchronization.SyncCollectionStatisticsManager;
import net.rim.device.api.synchronization.SyncConverter;
import net.rim.device.api.synchronization.SyncManager;
import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.util.DataBuffer;

final class UserSettingsSync implements SyncCollection, SyncCollectionStatistics, SyncConverter, OTASyncCapable, CollectionEventSource, Runnable {
   private UserPermissions _userPermissions;
   private CollectionListenerManager _collectionListenerManager = (CollectionListenerManager)(new Object());
   private SyncCollectionSchema _schema;
   private static final int USR_RESET_INTERVAL;
   private static final int DEFAULT_RECORD_TYPE;
   private static final int[] KEY_FIELD_IDS;

   final void settingRemoved(UserSetting var1) {
      this._collectionListenerManager.fireElementRemoved(this, var1);
   }

   final void reset() {
      this._collectionListenerManager.fireReset(this);
   }

   final void settingAdded(UserSetting var1) {
      this._collectionListenerManager.fireElementAdded(this, var1);
   }

   final void settingUpdated(UserSetting var1, UserSetting var2) {
      this._collectionListenerManager.fireElementUpdated(this, var1, var2);
   }

   @Override
   public final void run() {
      SyncManager var1 = SyncManager.getInstance();
      if (var1 != null) {
         var1.enableSynchronization(this);
      }
   }

   @Override
   public final boolean addSyncObject(SyncObject var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final boolean removeSyncObject(SyncObject var1) {
      if (var1 instanceof Object && this._userPermissions.getStorage() != null) {
         Object var2 = var1;
         if (!((UserSetting)var2).hashEquals(ApplicationControlConstants.EMPTY_HASH)) {
            if (((UserSetting)var2).hashEquals(ApplicationControlConstants.FILLED_HASH)) {
               this._userPermissions.removeBackedUpDefaultSetting();
            } else {
               this._userPermissions.getStorage().removeElement(var2);
               this._userPermissions.commit();
            }

            ApplicationControl.reloadModulePermissions();
            this._collectionListenerManager.fireElementRemoved(this, var2);
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public final boolean removeAllSyncObjects() {
      int var1 = this._userPermissions.getStorage().size();

      while (--var1 >= 0) {
         this.removeSyncObject((SyncObject)this._userPermissions.getStorage().elementAt(var1));
      }

      return true;
   }

   @Override
   public final boolean updateSyncObject(SyncObject var1, SyncObject var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void beginTransaction() {
   }

   @Override
   public final void endTransaction() {
   }

   @Override
   public final void addCollectionListener(Object var1) {
      this._collectionListenerManager.addCollectionListener(var1);
   }

   @Override
   public final void removeCollectionListener(Object var1) {
      this._collectionListenerManager.removeCollectionListener(var1);
   }

   @Override
   public final SyncConverter getSyncConverter() {
      return this;
   }

   @Override
   public final String getSyncName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final String getSyncName(Locale var1) {
      return this.getSyncName();
   }

   @Override
   public final int getSyncObjectCount() {
      return this._userPermissions.getStorage() == null ? 0 : this._userPermissions.getStorage().size();
   }

   @Override
   public final synchronized int getSyncCollectionSize() {
      return SyncCollectionStatisticsManager.getSyncCollectionSize(this);
   }

   @Override
   public final SyncObject[] getSyncObjects() {
      Vector var1 = this._userPermissions.getStorage();
      int var2 = var1 == null ? 0 : var1.size();
      if (var2 <= 0) {
         return new SyncObject[0];
      }

      SyncObject[] var3 = new SyncObject[var2];
      if (var1 != null) {
         for (int var4 = 0; var4 < var2; var4++) {
            var3[var4] = (SyncObject)var1.elementAt(var4);
         }
      }

      return var3;
   }

   @Override
   public final SyncObject getSyncObject(int var1) {
      Vector var2 = this._userPermissions.getStorage();
      int var3 = var2 == null ? 0 : var2.size();

      for (int var4 = 0; var4 < var3; var4++) {
         Object var5 = var2.elementAt(var4);
         if (((UserSetting)var5).getUID() == var1) {
            return (SyncObject)var5;
         }
      }

      return null;
   }

   @Override
   public final int getSyncVersion() {
      return 1;
   }

   @Override
   public final boolean isSyncObjectDirty(SyncObject var1) {
      return false;
   }

   @Override
   public final void setSyncObjectDirty(SyncObject var1) {
   }

   @Override
   public final void clearSyncObjectDirty(SyncObject var1) {
   }

   @Override
   public final SyncObject convert(DataBuffer var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean convert(SyncObject var1, DataBuffer var2, int var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final SyncCollectionSchema getSchema() {
      return this._schema;
   }

   UserSettingsSync(UserPermissions var1) {
      this._userPermissions = var1;
      this._schema = (SyncCollectionSchema)(new Object());
      this._schema.setDefaultRecordType(1);
      this._schema.setKeyFieldIds(1, KEY_FIELD_IDS);
   }
}
