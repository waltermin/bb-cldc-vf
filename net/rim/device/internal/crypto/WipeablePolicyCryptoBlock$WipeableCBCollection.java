package net.rim.device.internal.crypto;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.synchronization.SyncCollection;
import net.rim.device.api.synchronization.SyncConverter;
import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.util.DataBuffer;

public final class WipeablePolicyCryptoBlock$WipeableCBCollection implements SyncCollection, SyncConverter {
   SyncObject _keySyncObject = new WipeablePolicyCryptoBlock$WipeableCBSyncObject();
   private static final int CURRENT_VERSION;

   WipeablePolicyCryptoBlock$WipeableCBCollection() {
   }

   @Override
   public final SyncObject[] getSyncObjects() {
      return new SyncObject[]{this._keySyncObject};
   }

   @Override
   public final boolean addSyncObject(SyncObject var1) {
      this._keySyncObject = var1;
      return true;
   }

   @Override
   public final boolean removeSyncObject(SyncObject var1) {
      return true;
   }

   @Override
   public final int getSyncObjectCount() {
      return 1;
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
      WipeablePolicyCryptoBlock.getWLANKey();
   }

   @Override
   public final void endTransaction() {
      this._keySyncObject = null;
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
      return var3 != 1 ? false : WipeablePolicyCryptoBlock$WipeableCBSyncObject.convert(var1, var2);
   }

   @Override
   public final SyncObject convert(DataBuffer var1, int var2, int var3) {
      return var2 != 1 ? null : WipeablePolicyCryptoBlock$WipeableCBSyncObject.convert(var1, var3);
   }
}
