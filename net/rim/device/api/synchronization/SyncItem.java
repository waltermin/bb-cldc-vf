package net.rim.device.api.synchronization;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.util.DataBuffer;

public class SyncItem implements SyncObject, SyncCollection, SyncConverter {
   public boolean setSyncData(DataBuffer var1, int var2) {
      throw null;
   }

   public boolean getSyncData(DataBuffer var1, int var2) {
      throw null;
   }

   @Override
   public boolean convert(SyncObject var1, DataBuffer var2, int var3) {
      return this.getSyncData(var2, var3);
   }

   @Override
   public int getUID() {
      return 1;
   }

   @Override
   public SyncObject convert(DataBuffer var1, int var2, int var3) {
      return this.setSyncData(var1, var2) ? this : null;
   }

   @Override
   public int getSyncVersion() {
      throw null;
   }

   @Override
   public String getSyncName() {
      throw null;
   }

   @Override
   public String getSyncName(Locale var1) {
      throw null;
   }

   @Override
   public boolean addSyncObject(SyncObject var1) {
      return true;
   }

   @Override
   public boolean updateSyncObject(SyncObject var1, SyncObject var2) {
      return true;
   }

   @Override
   public boolean removeSyncObject(SyncObject var1) {
      return true;
   }

   @Override
   public boolean removeAllSyncObjects() {
      return true;
   }

   @Override
   public SyncObject[] getSyncObjects() {
      return new SyncObject[]{this};
   }

   @Override
   public SyncObject getSyncObject(int var1) {
      return var1 == this.getUID() ? this : null;
   }

   @Override
   public boolean isSyncObjectDirty(SyncObject var1) {
      return true;
   }

   @Override
   public void setSyncObjectDirty(SyncObject var1) {
   }

   @Override
   public void clearSyncObjectDirty(SyncObject var1) {
   }

   @Override
   public int getSyncObjectCount() {
      return 1;
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

   protected SyncItem() {
   }
}
