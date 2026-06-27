package net.rim.device.internal.crypto;

import java.util.Hashtable;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.synchronization.OTASyncPriorityProvider;
import net.rim.device.api.synchronization.SyncCollection;
import net.rim.device.api.synchronization.SyncConverter;
import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.DataBuffer;

public final class CryptoBlock$CBCollection implements SyncCollection, SyncConverter, OTASyncPriorityProvider {
   PersistentObject _root;
   Object[] _key = new Object[1];
   private static final int CURRENT_VERSION;

   CryptoBlock$CBCollection(PersistentObject var1) {
      this._root = var1;
   }

   private final boolean isByID() {
      return this._root == CryptoBlock._persistentKeysById;
   }

   @Override
   public final SyncObject[] getSyncObjects() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void clearHashtable(PersistentObject var0) {
      Object var1 = var0.getContents();
      if (var1 != null) {
         ((Hashtable)var1).clear();
         var0.commit();
      }
   }

   private final boolean okToInject() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean addSyncObject(SyncObject var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean removeSyncObject(SyncObject var1) {
      return true;
   }

   @Override
   public final int getSyncObjectCount() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean removeAllSyncObjects() {
      return true;
   }

   @Override
   public final int getSyncVersion() {
      return 1;
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
   public final SyncConverter getSyncConverter() {
      return this;
   }

   @Override
   public final void beginTransaction() {
      CryptoBlock$CryptoBlockKey.getDeviceKey();
   }

   @Override
   public final void endTransaction() {
      this._key[0] = null;
   }

   @Override
   public final boolean isSyncObjectDirty(SyncObject var1) {
      return true;
   }

   @Override
   public final void setSyncObjectDirty(SyncObject var1) {
   }

   @Override
   public final void clearSyncObjectDirty(SyncObject var1) {
   }

   @Override
   public final SyncObject getSyncObject(int var1) {
      return null;
   }

   @Override
   public final boolean updateSyncObject(SyncObject var1, SyncObject var2) {
      return false;
   }

   @Override
   public final boolean convert(SyncObject var1, DataBuffer var2, int var3) {
      return var3 != 1 ? false : CryptoBlock$CryptoBlockKey.convert(var1, var2);
   }

   @Override
   public final SyncObject convert(DataBuffer var1, int var2, int var3) {
      if (var2 != 1) {
         return null;
      }

      this._key[0] = null;
      return CryptoBlock$CryptoBlockKey.convert(var1, var3, this._key);
   }

   @Override
   public final int getSyncPriority() {
      return 0;
   }
}
