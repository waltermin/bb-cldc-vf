package net.rim.device.api.util;

final class IntMultiMap$KeysEnumeration implements IntEnumeration {
   private IntMultiMap _map;
   private int _i;

   IntMultiMap$KeysEnumeration(IntMultiMap var1) {
      this._map = var1;
      this._i = 0;
   }

   @Override
   public final boolean hasMoreElements() {
      return this._i < this._map._num;
   }

   @Override
   public final int nextElement() {
      if (this._i >= this._map._num) {
         throw new Object();
      }

      int var1 = this._map._ints[this._i];

      do {
         this._i++;
      } while (this._i < this._map._num && var1 == this._map._ints[this._i]);

      return var1;
   }
}
