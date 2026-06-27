package net.rim.device.internal.rms;

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

   public RecordStoreData(String var1) {
      this._name = var1;
      this._availableId = 1;
      this._records = (IntHashtable)(new Object());
      this._version = 16777216;
      this.setLastModified();
   }

   RecordStoreData(String var1, int var2, int var3, int var4, long var5, IntHashtable var7, int var8) {
      this._name = var1;
      this._version = var2;
      this._availableId = var3;
      this._lastModified = var5;
      this._records = var7;
      this._numRecords = var8;
      this._memoryUsed = var4 - this._numRecords * 8 - 8;
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

   public void setAuthMode(int var1) {
      if (var1 != this._authMode) {
         this._authMode = var1;
         this.setLastModified();
      }
   }

   void setLastModified() {
      this._lastModified = System.currentTimeMillis();
      this._version++;
      Object var1 = ApplicationRegistry.getApplicationRegistry().getOrWaitFor(6635119920104263588L);
      if (var1 != null) {
         ((RecordStoreManagerProxy)var1).commit(this);
      }
   }

   public int getNextRecordID() {
      return this._availableId;
   }

   public synchronized int addRecord(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public synchronized void deleteRecord(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized int getRecordSize(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized int getRecord(int var1, byte[] var2, int var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   public byte[] getRecord(int var1) {
      byte[] var2 = this.getRecordReadOnly(var1);
      int var3 = var2.length;
      byte[] var4 = null;
      if (var3 > 0) {
         var4 = new byte[var3];
         System.arraycopy(var2, 0, var4, 0, var3);
      }

      return var4;
   }

   public synchronized byte[] getRecordReadOnly(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized void setRecord(int var1, byte[] var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized void loadRecordIDs(int[] var1) {
      Array.resize(var1, this.getNumRecords());
      this._records.keysToArray(var1);
   }

   void throwInvalidRecordIDException(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
