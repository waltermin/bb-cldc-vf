package net.rim.device.api.util;

final class HashtableLongEnumerator implements LongEnumeration {
   private long[] _table;
   private Object[] _occupied;
   private Object _empty;
   private int _index;

   HashtableLongEnumerator(long[] var1, Object[] var2, Object var3) {
   }

   final void resetEnumeration(long[] var1, Object[] var2, Object var3) {
      this._table = var1;
      this._occupied = var2;
      this._empty = var3;
      this._index = 0;
   }

   final boolean getNextElement() {
      int var1 = this._index;

      for (int var2 = this._table.length; var1 < var2; var1++) {
         if (this._occupied[var1] != null && this._occupied[var1] != this._empty) {
            this._index = var1;
            return true;
         }
      }

      this._index = var1;
      return false;
   }

   @Override
   public final boolean hasMoreElements() {
      return this.getNextElement();
   }

   @Override
   public final long nextElement() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
