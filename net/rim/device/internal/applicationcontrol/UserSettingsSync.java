package net.rim.device.internal.applicationcontrol;

import java.io.IOException;
import java.util.Vector;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.util.CollectionListenerManager;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.synchronization.ConverterUtilities;
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

   final void settingRemoved(UserSetting us) {
      this._collectionListenerManager.fireElementRemoved(this, us);
   }

   final void reset() {
      this._collectionListenerManager.fireReset(this);
   }

   final void settingAdded(UserSetting us) {
      this._collectionListenerManager.fireElementAdded(this, us);
   }

   final void settingUpdated(UserSetting oldUS, UserSetting newUS) {
      this._collectionListenerManager.fireElementUpdated(this, oldUS, newUS);
   }

   @Override
   public final void run() {
      SyncManager manager = SyncManager.getInstance();
      if (manager != null) {
         manager.enableSynchronization(this);
      }
   }

   @Override
   public final boolean addSyncObject(SyncObject object) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final boolean removeSyncObject(SyncObject object) {
      if (object instanceof Object && this._userPermissions.getStorage() != null) {
         UserSetting element = (UserSetting)object;
         if (!element.hashEquals(ApplicationControlConstants.EMPTY_HASH)) {
            if (element.hashEquals(ApplicationControlConstants.FILLED_HASH)) {
               this._userPermissions.removeBackedUpDefaultSetting();
            } else {
               this._userPermissions.getStorage().removeElement(element);
               this._userPermissions.commit();
            }

            ApplicationControl.reloadModulePermissions();
            this._collectionListenerManager.fireElementRemoved(this, element);
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public final boolean removeAllSyncObjects() {
      int elements = this._userPermissions.getStorage().size();

      while (--elements >= 0) {
         this.removeSyncObject((SyncObject)this._userPermissions.getStorage().elementAt(elements));
      }

      return true;
   }

   @Override
   public final boolean updateSyncObject(SyncObject oldObject, SyncObject newObject) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void beginTransaction() {
   }

   @Override
   public final void endTransaction() {
   }

   @Override
   public final void addCollectionListener(Object listener) {
      this._collectionListenerManager.addCollectionListener(listener);
   }

   @Override
   public final void removeCollectionListener(Object listener) {
      this._collectionListenerManager.removeCollectionListener(listener);
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
   public final String getSyncName(Locale locale) {
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
      Vector permissions = this._userPermissions.getStorage();
      int size = permissions == null ? 0 : permissions.size();
      if (size <= 0) {
         return new SyncObject[0];
      }

      SyncObject[] array = new SyncObject[size];
      if (permissions != null) {
         for (int i = 0; i < size; i++) {
            array[i] = (SyncObject)permissions.elementAt(i);
         }
      }

      return array;
   }

   @Override
   public final SyncObject getSyncObject(int uid) {
      Vector permissions = this._userPermissions.getStorage();
      int size = permissions == null ? 0 : permissions.size();

      for (int i = 0; i < size; i++) {
         UserSetting element = (UserSetting)permissions.elementAt(i);
         if (element.getUID() == uid) {
            return element;
         }
      }

      return null;
   }

   @Override
   public final int getSyncVersion() {
      return 1;
   }

   @Override
   public final boolean isSyncObjectDirty(SyncObject object) {
      return false;
   }

   @Override
   public final void setSyncObjectDirty(SyncObject object) {
   }

   @Override
   public final void clearSyncObjectDirty(SyncObject object) {
   }

   @Override
   public final SyncObject convert(DataBuffer buffer, int version, int uid) {
      byte[] hash = null;
      long permissions = 0;
      long dontPrompt = 0;
      long isSet = 0;

      try {
         buffer.rewind();
         if (ConverterUtilities.findType(buffer, -1)) {
            int var13 = ConverterUtilities.readShort(buffer);
         }

         buffer.rewind();
         if (ConverterUtilities.findType(buffer, 0)) {
            hash = ConverterUtilities.readByteArray(buffer);
            buffer.rewind();
            if (ConverterUtilities.findType(buffer, 4)) {
               permissions = ConverterUtilities.readLong(buffer);
               permissions ^= 7769595838464L;
               permissions &= Integer.MIN_VALUE;
            } else {
               buffer.rewind();
               if (ConverterUtilities.findType(buffer, 1)) {
                  permissions = (long)ConverterUtilities.readInt(buffer) << 32;
                  permissions ^= 7769595838464L;
                  permissions &= Integer.MIN_VALUE;
               }
            }

            buffer.rewind();
            if (ConverterUtilities.findType(buffer, 5)) {
               dontPrompt = ConverterUtilities.readLong(buffer);
               dontPrompt &= Integer.MIN_VALUE;
            } else {
               buffer.rewind();
               if (ConverterUtilities.findType(buffer, 2)) {
                  dontPrompt = (long)ConverterUtilities.readInt(buffer) << 32;
                  dontPrompt &= Integer.MIN_VALUE;
               }
            }

            buffer.rewind();
            if (ConverterUtilities.findType(buffer, 6)) {
               isSet = ConverterUtilities.readLong(buffer);
               isSet &= Integer.MIN_VALUE;
            } else {
               buffer.rewind();
               if (ConverterUtilities.findType(buffer, 3)) {
                  isSet = (long)ConverterUtilities.readInt(buffer) << 32;
                  isSet &= Integer.MIN_VALUE;
               }
            }

            return (SyncObject)(new Object(hash, permissions, dontPrompt, isSet, uid));
         } else {
            return null;
         }
      } catch (IOException var12) {
         return null;
      }
   }

   @Override
   public final boolean convert(SyncObject object, DataBuffer buffer, int version) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final SyncCollectionSchema getSchema() {
      return this._schema;
   }

   UserSettingsSync(UserPermissions userPermissions) {
      this._userPermissions = userPermissions;
      this._schema = (SyncCollectionSchema)(new Object());
      this._schema.setDefaultRecordType(1);
      this._schema.setKeyFieldIds(1, KEY_FIELD_IDS);
   }
}
