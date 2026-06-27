package net.rim.device.api.util;

class IntLongHashtableEnumeratorKeys implements IntEnumeration {
   int[] _table;
   byte[] _occupied;
   int _index;

   IntLongHashtableEnumeratorKeys(int[] var1, byte[] var2) {
      this._table = var1;
      this._index = 0;
      this._occupied = var2;
   }

   boolean getNextElement() {
      int var1 = this._index;

      for (int var2 = this._table.length; var1 < var2; var1++) {
         if (this._occupied[var1] == 1) {
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
   public int nextElement() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
