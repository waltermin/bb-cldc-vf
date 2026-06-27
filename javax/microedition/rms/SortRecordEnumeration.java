package javax.microedition.rms;

class SortRecordEnumeration extends BaseRecordEnumeration {
   private RecordComparator _comparator;

   SortRecordEnumeration(RecordStore var1, RecordEventGenerator var2, RecordComparator var3, boolean var4) {
      super(var1, var2, var4);
      this._comparator = var3;
      this.rebuild();
   }

   @Override
   public synchronized void rebuild() {
      this.mustBeValid();
      super.rebuild();
      this.sort(0, super._recordIds.length - 1);
   }

   @Override
   public void destroy() {
      this._comparator = null;
      super.destroy();
   }

   private void sort(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public synchronized void recordAdded(RecordStore var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void recordChanged(RecordStore var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
