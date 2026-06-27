package net.rim.device.internal.ui;

import net.rim.device.api.util.AbstractString;

class StringBufferGap$SBGAbstractString implements AbstractString {
   private int _labelLength;
   private final StringBufferGap this$0;

   public void setLabelLength(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void getChars(int var1, int var2, char[] var3, int var4) {
      this.this$0.getChars(this._labelLength + var1, this._labelLength + var2, var3, var4);
   }

   @Override
   public int indexOf(char var1, int var2, int var3) {
      int var4 = this.this$0.indexOf(var1, this._labelLength + var2, this._labelLength + var3);
      return var4 >= this._labelLength ? var4 - this._labelLength : -1;
   }

   @Override
   public int length() {
      return this.this$0._size - this._labelLength;
   }

   @Override
   public char charAt(int var1) {
      return this.this$0.charAt(this._labelLength + var1);
   }

   StringBufferGap$SBGAbstractString(StringBufferGap var1) {
      this.this$0 = var1;
   }

   @Override
   public String toString() {
      return this.this$0.getText(this._labelLength, this.this$0._size - this._labelLength);
   }
}
