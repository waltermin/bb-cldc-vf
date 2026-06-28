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

   public final void set(StringBuffer s) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void setLabelLength(int length) {
      this._abstractString.setLabelLength(length);
   }

   public final void clear() {
      int startSize = 10;
      this._text = new byte[startSize];
      this._gapStart = 0;
      this._gapLength = startSize;
      this._size = 0;
   }

   public final void wipe() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void delete(int count) {
      if (count < 0) {
         throw new IndexOutOfBoundsException();
      }

      count = Math.min(this._gapStart, count);
      this._gapStart -= count;
      this._gapLength += count;
      this._size -= count;
   }

   public final int draw(Graphics graphics, int x, int y, int offset, int length) {
      return graphics.drawText(this, offset, length, x, y, 0, -1);
   }

   public final AbstractString getAbstractString() {
      return this._abstractString;
   }

   public final void seek(int index) {
      if (index < this._gapStart) {
         System.arraycopy(this._text, index, this._text, this._gapLength + index, this._gapStart - index);
      } else {
         System.arraycopy(this._text, this._gapStart + this._gapLength, this._text, this._gapStart, index - this._gapStart);
      }

      this._gapStart = index;
   }

   public final StringBuffer getPrevWord() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final StringBuffer getPrevChars(int len) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int getPrevNonSpaceCharOffset() {
      return this.previousIndexOf((char)32, false);
   }

   public final String getText(int offset, int length) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void getText(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void insert(char c) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void insert(String s) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void insert(StringBuffer s) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void insert(StringBufferGap s) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int indexOf(char c, boolean match) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int previousIndexOf(char c) {
      return this.previousIndexOf(c, true);
   }

   public final int previousIndexOf(String s) {
      return this.previousIndexOf(s, true);
   }

   public final int previousIndexOf(char c, boolean match) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int previousIndexOf(String s, boolean match) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int indexOf(char c) {
      return this.indexOf(c, true);
   }

   @Override
   public final int length() {
      return this._size;
   }

   @Override
   public final int indexOf(char ch, int startIndex, int endIndex) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final char charAt(int index) {
      throw new RuntimeException("cod2jar: type check");
   }

   public StringBufferGap() {
      this.clear();
   }

   @Override
   public final int hashCode() {
      throw new RuntimeException("cod2jar: type check");
   }

   public StringBufferGap(StringBuffer s) {
      this.clear();
      this.insert(s);
   }

   private final void promote() {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void grow(int count) {
      throw new RuntimeException("cod2jar: type check");
   }

   public StringBufferGap(String s) {
      this.clear();
      this.insert(s);
   }

   @Override
   public final String toString() {
      return this.getText(0, this._size);
   }

   private static final native int stringToWords0(StringBufferGap var0, int var1, int var2, int[] var3, int[] var4);

   public static final int stringToWords(StringBufferGap SBG, int startIndex, int length, int[] offsets, int[] lengths) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
