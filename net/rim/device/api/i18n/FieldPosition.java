package net.rim.device.api.i18n;

public class FieldPosition {
   private int _field;
   private int _beginindex;
   private int _endindex;

   public FieldPosition(int var1) {
      this._field = var1;
   }

   public int getBeginIndex() {
      return this._beginindex;
   }

   public int getEndIndex() {
      return this._endindex;
   }

   public int getField() {
      return this._field;
   }

   public void setField(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setBeginIndex(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setEndIndex(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
