package java.util;

class HashtableEnumerator implements Enumeration {
   Object[] _table;
   Object _empty;
   int _index;

   HashtableEnumerator(Object[] var1, Object var2) {
      this._table = var1;
      this._index = 0;
      this._empty = var2;
   }

   boolean getNextElement() {
      int var1 = this._index;

      for (int var2 = this._table.length; var1 < var2; var1++) {
         Object var3 = this._table[var1];
         if (var3 != null && var3 != this._empty) {
            this._index = var1;
            return true;
         }
      }

      this._index = var1;
      return false;
   }

   @Override
   public boolean hasMoreElements() {
      return this.getNextElement();
   }

   @Override
   public Object nextElement() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
