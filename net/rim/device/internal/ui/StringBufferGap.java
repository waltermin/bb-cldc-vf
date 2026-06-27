package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.util.AbstractString;

public final class StringBufferGap implements AbstractString {
   private Object _text;
   private int _gapStart;
   private int _gapLength;
   private int _size;
   private StringBufferGap$SBGAbstractString _abstractString = new StringBufferGap$SBGAbstractString(this);
   private static final int GAPSIZE;

   public final void set(StringBuffer var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void setLabelLength(int var1) {
      this._abstractString.setLabelLength(var1);
   }

   public final void clear() {
      byte var1 = 10;
      this._text = new byte[var1];
      this._gapStart = 0;
      this._gapLength = var1;
      this._size = 0;
   }

   public final void wipe() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void delete(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      var1 = Math.min(this._gapStart, var1);
      this._gapStart -= var1;
      this._gapLength += var1;
      this._size -= var1;
   }

   public final int draw(Graphics var1, int var2, int var3, int var4, int var5) {
      return var1.drawText(this, var4, var5, var2, var3, 0, -1);
   }

   public final AbstractString getAbstractString() {
      return this._abstractString;
   }

   public final void seek(int var1) {
      if (var1 < this._gapStart) {
         System.arraycopy(this._text, var1, this._text, this._gapLength + var1, this._gapStart - var1);
      } else {
         System.arraycopy(this._text, this._gapStart + this._gapLength, this._text, this._gapStart, var1 - this._gapStart);
      }

      this._gapStart = var1;
   }

   public final StringBuffer getPrevWord() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final StringBuffer getPrevChars(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int getPrevNonSpaceCharOffset() {
      return this.previousIndexOf((char)32, false);
   }

   public final String getText(int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void getText(int var1, int var2, char[] var3, int var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void insert(char var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void insert(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void insert(StringBuffer var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void insert(StringBufferGap var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int indexOf(char var1, boolean var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int previousIndexOf(char var1) {
      return this.previousIndexOf(var1, true);
   }

   public final int previousIndexOf(String var1) {
      return this.previousIndexOf(var1, true);
   }

   public final int previousIndexOf(char var1, boolean var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int previousIndexOf(String var1, boolean var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int indexOf(char var1) {
      return this.indexOf(var1, true);
   }

   @Override
   public final int length() {
      return this._size;
   }

   @Override
   public final int indexOf(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void getChars(int var1, int var2, char[] var3, int var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final char charAt(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public StringBufferGap() {
      this.clear();
   }

   @Override
   public final int hashCode() {
      throw new RuntimeException("cod2jar: type check");
   }

   public StringBufferGap(StringBuffer var1) {
      this.clear();
      this.insert(var1);
   }

   private final void promote() {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void grow(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public StringBufferGap(String var1) {
      this.clear();
      this.insert(var1);
   }

   @Override
   public final String toString() {
      return this.getText(0, this._size);
   }

   private static final native int stringToWords0(StringBufferGap var0, int var1, int var2, int[] var3, int[] var4);

   public static final int stringToWords(StringBufferGap var0, int var1, int var2, int[] var3, int[] var4) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
