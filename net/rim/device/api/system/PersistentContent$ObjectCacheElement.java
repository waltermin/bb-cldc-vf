package net.rim.device.api.system;

import net.rim.vm.WeakReference;

final class PersistentContent$ObjectCacheElement {
   private WeakReference _encodingWR = (WeakReference)(new Object(null));
   private boolean _firstChunkOnly;
   private int _encodingLength;
   private Object _object;

   final Object get(char[] var1, boolean var2) {
      if (this._encodingWR.get() != var1) {
         return null;
      } else {
         return var2 || !this._firstChunkOnly && var1.length == this._encodingLength ? copy(this._object) : null;
      }
   }

   final void put(char[] var1, boolean var2, Object var3) {
      this._encodingWR.set(var1);
      this._firstChunkOnly = var2;
      this._encodingLength = var1.length;
      this._object = copy(var3);
   }

   final boolean cleanNow() {
      if (this._object == null) {
         return false;
      }

      this._encodingWR.set(null);
      this._encodingLength = 0;
      this._object = null;
      return true;
   }

   private static final Object copy(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }
}
