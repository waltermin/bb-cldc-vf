package net.rim.device.internal.rms;

import javax.microedition.rms.InvalidRecordIDException;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.Persistable;
import net.rim.vm.Array;

public class RecordStoreData implements Persistable {
   private String _name;
   private long _lastModified;
   private int _version;
   private int _availableId;
   private int _numRecords;
   private int _memoryUsed;
   private IntHashtable _records;
   private int _authMode;
   private static final int MAXIMUM_RECORDSTORE_SIZE;
   private static final int OVERHEAD_PER_RECORD;
   private static final int OVERHEAD_PER_RECORDSTORE;
   static final int MIDP_2_0_INITIAL_VERSION_NUMBER;

   public RecordStoreData(String name) {
      this._name = name;
      this._availableId = 1;
      this._records = new IntHashtable();
      this._version = 16777216;
      this.setLastModified();
   }

   RecordStoreData(String name, int version, int availableId, int size, long lastModified, IntHashtable records, int numRecords) {
      this._name = name;
      this._version = version;
      this._availableId = availableId;
      this._lastModified = lastModified;
      this._records = records;
      this._numRecords = numRecords;
      this._memoryUsed = size - this._numRecords * 8 - 8;
   }

   public String getName() {
      return this._name;
   }

   public int getVersion() {
      return this._version;
   }

   public int getNumRecords() {
      return this._numRecords;
   }

   public int getSize() {
      return this._memoryUsed + this._numRecords * 8 + 8;
   }

   public int getSizeAvailable() {
      return 65536 - this.getSize();
   }

   public long getLastModified() {
      return this._lastModified;
   }

   public int getAuthMode() {
      return this._authMode;
   }

   public void setAuthMode(int mode) {
      if (mode != this._authMode) {
         this._authMode = mode;
         this.setLastModified();
      }
   }

   void setLastModified() {
      this._lastModified = System.currentTimeMillis();
      this._version++;
      RecordStoreManagerProxy rsmp = (RecordStoreManagerProxy)ApplicationRegistry.getApplicationRegistry().getOrWaitFor(6635119920104263588L);
      if (rsmp != null) {
         rsmp.commit(this);
      }
   }

   public int getNextRecordID() {
      return this._availableId;
   }

   public synchronized int addRecord(byte[] data, int offset, int numBytes) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public synchronized void deleteRecord(int recordId) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized int getRecordSize(int recordId) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized int getRecord(int recordId, byte[] buffer, int offset) {
      throw new RuntimeException("cod2jar: type check");
   }

   public byte[] getRecord(int recordId) {
      byte[] data = this.getRecordReadOnly(recordId);
      int size = data.length;
      byte[] newData = null;
      if (size > 0) {
         newData = new byte[size];
         System.arraycopy(data, 0, newData, 0, size);
      }

      return newData;
   }

   public synchronized byte[] getRecordReadOnly(int recordId) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized void setRecord(int recordId, byte[] newData, int offset, int numBytes) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized void loadRecordIDs(int[] recordIds) {
      Array.resize(recordIds, this.getNumRecords());
      this._records.keysToArray(recordIds);
   }

   void throwInvalidRecordIDException(int recordID) {
      throw new InvalidRecordIDException(this._name + " #" + recordID);
   }
}
