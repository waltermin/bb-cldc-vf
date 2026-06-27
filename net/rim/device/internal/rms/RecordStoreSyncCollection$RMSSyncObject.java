package net.rim.device.internal.rms;

import java.util.Hashtable;
import net.rim.device.api.synchronization.SyncObject;

class RecordStoreSyncCollection$RMSSyncObject implements SyncObject {
   int _uid;
   Hashtable _data;
   String _midletSuiteName;
   boolean _dirty;

   public void setDirty(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public Hashtable getData() {
      return this._data;
   }

   public String getMidletSuiteName() {
      return this._midletSuiteName;
   }

   @Override
   public int getUID() {
      return this._uid;
   }

   public boolean isDirty() {
      return this._dirty;
   }

   public RecordStoreSyncCollection$RMSSyncObject(String var1, Hashtable var2) {
      this._uid = RecordStoreSyncCollection.getUID(var1);
      this._midletSuiteName = var1;
      this._data = var2;
      this._dirty = false;
   }
}
