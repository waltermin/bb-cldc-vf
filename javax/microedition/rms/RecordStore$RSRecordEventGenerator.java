package javax.microedition.rms;

class RecordStore$RSRecordEventGenerator extends RecordEventGenerator {
   private final RecordStore this$0;

   RecordStore$RSRecordEventGenerator(RecordStore var1) {
      this.this$0 = var1;
   }

   @Override
   synchronized void loadRecordIDs(int[] var1) {
      this.this$0._recordStoreData.loadRecordIDs(var1);
   }
}
