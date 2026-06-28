package net.rim.device.api.util;

import java.util.Enumeration;

final class IntMultiMap$ValuesEnumeration implements Enumeration {
   private IntMultiMap _map;
   private int _i;

   IntMultiMap$ValuesEnumeration(IntMultiMap map) {
      this._map = map;
      this._i = 0;
   }

   @Override
   public final boolean hasMoreElements() {
      return this._i < this._map._num;
   }

   @Override
   public final Object nextElement() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
