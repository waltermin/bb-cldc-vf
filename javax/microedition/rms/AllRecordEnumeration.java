package javax.microedition.rms;

class AllRecordEnumeration extends BaseRecordEnumeration {
   AllRecordEnumeration(RecordStore var1, boolean var2) {
      super(var1, var1._eventGenerator, var2);
      this.rebuild();
   }
}
