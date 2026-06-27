package net.rim.tid.text;

public final class TextHitInfo {
   public int _index;
   public boolean _leadingEdge;
   private static TextHitInfo _tempHitInfo;

   public TextHitInfo() {
   }

   public TextHitInfo(int var1, boolean var2) {
      this._index = var1;
      this._leadingEdge = var2;
   }

   public final void set(int var1, boolean var2) {
      this._index = var1;
      this._leadingEdge = var2;
   }

   public final boolean less(TextHitInfo var1) {
      return this._index < var1._index || this._index == var1._index && !this._leadingEdge && var1._leadingEdge;
   }

   public final Object clone() {
      return new TextHitInfo(this._index, this._leadingEdge);
   }

   public final boolean equals(TextHitInfo var1) {
      return this._index == var1._index && this._leadingEdge == var1._leadingEdge;
   }

   public final int getIndex() {
      return this._index;
   }

   public final boolean getLeadingEdge() {
      return this._leadingEdge;
   }

   public static final TextHitInfo leading(int var0) {
      _tempHitInfo._index = var0;
      _tempHitInfo._leadingEdge = true;
      return _tempHitInfo;
   }

   public static final TextHitInfo trailing(int var0) {
      _tempHitInfo._index = var0;
      _tempHitInfo._leadingEdge = false;
      return _tempHitInfo;
   }

   @Override
   public final String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
