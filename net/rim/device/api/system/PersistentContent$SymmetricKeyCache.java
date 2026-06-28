package net.rim.device.api.system;

import net.rim.device.api.util.IntHashtable;
import net.rim.vm.WeakReference;

final class PersistentContent$SymmetricKeyCache {
   private IntHashtable _keys = new IntHashtable(2053);
   private IntHashtable _references = new IntHashtable(2053);
   private int[] _hashes = new int[2053];
   private int _victim = 0;
   private static final int SIZE;

   final byte[] get(char[] encoding, int offset) {
      int hash = net.rim.vm.Memory.objectToInt(encoding) + (offset << 16);
      WeakReference wr = (WeakReference)this._references.get(hash);
      if (wr == null) {
         return null;
      }

      if (wr.get() != null) {
         return (byte[])this._keys.get(hash);
      }

      this._keys.remove(hash);
      this._references.remove(hash);
      return null;
   }

   final void put(char[] encoding, int offset, byte[] symmetricKey) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
