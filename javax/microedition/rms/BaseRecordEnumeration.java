package javax.microedition.rms;

class BaseRecordEnumeration extends RecordEventGenerator implements RecordEnumeration, RecordListener {
   private boolean _destroyed;
   protected RecordStore _recordStore;
   protected RecordEventGenerator _recordSource;
   protected boolean _keepUpdated;
   protected int[] _recordIds;
   protected boolean _inError;
   protected int _currentIndex;

   BaseRecordEnumeration(RecordStore var1, RecordEventGenerator var2, boolean var3) {
      this._recordStore = var1;
      this._recordSource = var2;
      this._recordIds = new int[0];
      this.keepUpdated(var3);
   }

   @Override
   void loadRecordIDs(int[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void mustBeValid() {
      if (this._destroyed) {
         throw new Object();
      }
   }

   @Override
   public boolean isKeptUpdated() {
      this.mustBeValid();
      return this._keepUpdated;
   }

   @Override
   public void keepUpdated(boolean var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public synchronized void addRecordListener(RecordListener var1) {
      this._recordStore.addRecordListener(var1);
   }

   @Override
   public synchronized void removeRecordListener(RecordListener var1) {
      this._recordStore.removeRecordListener(var1);
   }

   @Override
   public int numRecords() {
      this.mustBeValid();
      return this._recordIds.length;
   }

   @Override
   public byte[] nextRecord() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int nextRecordId() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean hasNextElement() {
      this.mustBeValid();
      return !this._inError && this._currentIndex + 1 < this._recordIds.length;
   }

   @Override
   public byte[] previousRecord() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int previousRecordId() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean hasPreviousElement() {
      this.mustBeValid();
      return !this._inError && this._currentIndex == -1 && this._recordIds.length > 0 ? true : !this._inError && this._currentIndex - 1 >= 0;
   }

   @Override
   public void reset() {
      this.mustBeValid();
      this._inError = false;
      this._currentIndex = -1;
   }

   @Override
   public void rebuild() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void destroy() {
      this._recordSource.removeRecordListener(this);
      this._recordSource = null;
      this._recordIds = null;
      this._destroyed = true;
   }

   @Override
   public void recordAdded(RecordStore var1, int var2) {
      this.mustBeValid();
      this.notifyRecordAdded(var1, var2);
   }

   @Override
   public void recordChanged(RecordStore var1, int var2) {
      this.mustBeValid();
      this.notifyRecordChanged(var1, var2);
   }

   @Override
   public void recordDeleted(RecordStore var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
