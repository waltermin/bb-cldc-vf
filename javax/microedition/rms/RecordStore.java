package javax.microedition.rms;

import net.rim.device.internal.rms.RecordStoreData;

public class RecordStore {
   RecordStoreData _recordStoreData;
   RecordEventGenerator _eventGenerator;
   private int _openCount;
   public static final int AUTHMODE_PRIVATE;
   public static final int AUTHMODE_ANY;
   static final int AUTHMODE_ANY_RO;

   RecordStore(RecordStoreData var1) {
      this._recordStoreData = var1;
      this._eventGenerator = new RecordStore$RSRecordEventGenerator(this);
   }

   private static void validateRecordStoreName(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static void deleteRecordStore(String var0) {
      validateRecordStoreName(var0);
      RecordStoreManager.deleteRecordStore(var0);
   }

   public static RecordStore openRecordStore(String var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static RecordStore openRecordStore(String var0, boolean var1, int var2, boolean var3) {
      RecordStore var4 = openRecordStore(var0, var1);
      var4.setMode(var2, var3);
      return var4;
   }

   public static RecordStore openRecordStore(String var0, String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setMode(int var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void closeRecordStore() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static String[] listRecordStores() {
      return RecordStoreManager.getRecordStoreList();
   }

   boolean isOpen() {
      return this._openCount > 0;
   }

   private void mustBeOpen() {
      if (!this.isOpen()) {
         throw new RecordStoreNotOpenException(this._recordStoreData.getName());
      }
   }

   public String getName() {
      this.mustBeOpen();
      return this._recordStoreData.getName();
   }

   public int getVersion() {
      this.mustBeOpen();
      return this._recordStoreData.getVersion();
   }

   public int getNumRecords() {
      this.mustBeOpen();
      return this._recordStoreData.getNumRecords();
   }

   public int getSize() {
      this.mustBeOpen();
      return this._recordStoreData.getSize();
   }

   public int getSizeAvailable() {
      this.mustBeOpen();
      return this._recordStoreData.getSizeAvailable();
   }

   public long getLastModified() {
      this.mustBeOpen();
      return this._recordStoreData.getLastModified();
   }

   public int getNextRecordID() {
      this.mustBeOpen();
      return this._recordStoreData.getNextRecordID();
   }

   public int addRecord(byte[] var1, int var2, int var3) {
      this.mustBeOpen();
      if (!this.checkWritable()) {
         throw new Object();
      }

      int var4 = this._recordStoreData.addRecord(var1, var2, var3);
      this._eventGenerator.notifyRecordAdded(this, var4);
      return var4;
   }

   public void addRecordListener(RecordListener var1) {
      this._eventGenerator.addRecordListener(var1);
   }

   public void removeRecordListener(RecordListener var1) {
      this._eventGenerator.removeRecordListener(var1);
   }

   public void deleteRecord(int var1) {
      this.mustBeOpen();
      if (!this.checkWritable()) {
         throw new Object();
      }

      this._recordStoreData.deleteRecord(var1);
      this._eventGenerator.notifyRecordDeleted(this, var1);
   }

   public int getRecordSize(int var1) {
      this.mustBeOpen();
      return this._recordStoreData.getRecordSize(var1);
   }

   public int getRecord(int var1, byte[] var2, int var3) {
      this.mustBeOpen();
      return this._recordStoreData.getRecord(var1, var2, var3);
   }

   public byte[] getRecord(int var1) {
      this.mustBeOpen();
      return this._recordStoreData.getRecord(var1);
   }

   byte[] getRecordReadOnly(int var1) {
      return this._recordStoreData.getRecordReadOnly(var1);
   }

   public void setRecord(int var1, byte[] var2, int var3, int var4) {
      this.mustBeOpen();
      if (!this.checkWritable()) {
         throw new Object();
      }

      this._recordStoreData.setRecord(var1, var2, var3, var4);
      this._eventGenerator.notifyRecordChanged(this, var1);
   }

   synchronized void loadRecordIDs(int[] var1) {
      this._recordStoreData.loadRecordIDs(var1);
   }

   public RecordEnumeration enumerateRecords(RecordFilter var1, RecordComparator var2, boolean var3) {
      this.mustBeOpen();
      if (var1 == null && var2 == null) {
         return new AllRecordEnumeration(this, var3);
      }

      RecordEventGenerator var4 = this._eventGenerator;
      if (var1 != null) {
         var4 = new FilterRecordEnumeration(this, var4, var1, var3);
      }

      if (var2 != null) {
         var4 = new SortRecordEnumeration(this, var4, var2, var3);
      }

      return (RecordEnumeration)var4;
   }

   private boolean checkWritable() {
      return RecordStoreManager.checkOwner(this) ? true : this._recordStoreData.getAuthMode() == 1;
   }
}
