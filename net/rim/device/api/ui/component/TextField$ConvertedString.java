package net.rim.device.api.ui.component;

import net.rim.device.api.util.AbstractString;

final class TextField$ConvertedString implements AbstractString {
   private char[] _insertedFilteredChars;
   private int _insertPos;
   private int _filteredCharsCount;
   private final TextField this$0;

   private TextField$ConvertedString(TextField var1) {
      this.this$0 = var1;
   }

   final void init(int var1, int var2) {
      if (this._insertedFilteredChars == null || this._insertedFilteredChars.length < var1) {
         this._insertedFilteredChars = new char[var1];
      }

      this._filteredCharsCount = 0;
      this._insertPos = var2;
   }

   final void appendFilteredChar(char var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   final String getConvertedInsertionString() {
      return (String)(new Object(this._insertedFilteredChars, 0, this._filteredCharsCount));
   }

   @Override
   public final int length() {
      return this.this$0.getTextLength() + this._filteredCharsCount;
   }

   @Override
   public final int indexOf(char var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         if (this.charAt(var4) == var1) {
            return var4;
         }
      }

      return -1;
   }

   @Override
   public final char charAt(int var1) {
      var1 += this.this$0.getLabelLength();
      if (var1 < this._insertPos) {
         return this.this$0._text.charAt(var1);
      } else {
         return var1 < this._insertPos + this._filteredCharsCount
            ? this._insertedFilteredChars[var1 - this._insertPos]
            : this.this$0._text.charAt(var1 - this._filteredCharsCount);
      }
   }

   @Override
   public final void getChars(int var1, int var2, char[] var3, int var4) {
      int var5 = var1;

      for (int var6 = var4; var5 < var2; var6++) {
         var3[var6] = this.charAt(var5);
         var5++;
      }
   }

   TextField$ConvertedString(TextField var1, TextField$1 var2) {
      this(var1);
   }
}
