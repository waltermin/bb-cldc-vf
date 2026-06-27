package net.rim.device.api.collection.util;

import java.util.Enumeration;
import net.rim.device.api.collection.ReadableLongMap;
import net.rim.device.api.collection.ReadableSet;
import net.rim.device.api.collection.WritableLongMap;
import net.rim.device.api.util.LongEnumeration;
import net.rim.device.api.util.LongHashtable;
import net.rim.vm.Persistable;

public final class LongHashtableCollection implements Persistable, ReadableSet, ReadableLongMap, WritableLongMap {
   private LongHashtable _ht = new LongHashtable();

   public final LongEnumeration getKeys() {
      return this._ht.keys();
   }

   @Override
   public final void remove(long var1) {
      this._ht.remove(var1);
   }

   @Override
   public final void removeAll() {
      this._ht.clear();
   }

   @Override
   public final int size() {
      return this._ht.size();
   }

   @Override
   public final Object get(long var1) {
      return this._ht.get(var1);
   }

   @Override
   public final long getKey(Object var1) {
      return this._ht.getKey(var1);
   }

   @Override
   public final boolean contains(long var1) {
      return this._ht.containsKey(var1);
   }

   @Override
   public final boolean contains(Object var1) {
      return this._ht.contains(var1);
   }

   @Override
   public final Enumeration getElements() {
      return this._ht.elements();
   }

   @Override
   public final void put(long var1, Object var3) {
      this._ht.put(var1, var3);
   }

   @Override
   public final int getElements(Object[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
