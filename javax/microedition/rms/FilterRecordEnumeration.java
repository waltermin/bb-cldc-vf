package javax.microedition.rms;

class FilterRecordEnumeration extends BaseRecordEnumeration {
   private RecordFilter _filter;

   FilterRecordEnumeration(RecordStore var1, RecordEventGenerator var2, RecordFilter var3, boolean var4) {
      super(var1, var2, var4);
      this._filter = var3;
      this.rebuild();
   }

   @Override
   public synchronized void rebuild() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void destroy() {
      this._filter = null;
      super.destroy();
   }

   @Override
   public void recordAdded(RecordStore var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void recordChanged(RecordStore var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
