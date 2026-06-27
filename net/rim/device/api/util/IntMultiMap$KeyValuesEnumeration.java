package net.rim.device.api.util;

import java.util.Enumeration;

final class IntMultiMap$KeyValuesEnumeration implements Enumeration {
   private IntMultiMap _map;
   private int _i;
   private int _key;

   IntMultiMap$KeyValuesEnumeration(IntMultiMap var1, int var2) {
      this._map = var1;
      this._i = this._map.findKey(var2);
      if (this._i < 0) {
         this._i = 0;
      }

      this._key = var2;
   }

   @Override
   public final boolean hasMoreElements() {
      return this._i < this._map._num && this._map._ints[this._i] == this._key;
   }

   @Override
   public final Object nextElement() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
