package net.rim.device.api.system;

import net.rim.device.api.util.IntHashtable;

final class PersistentContent$SymmetricKeyCache {
   private IntHashtable _keys = (IntHashtable)(new Object(2053));
   private IntHashtable _references = (IntHashtable)(new Object(2053));
   private int[] _hashes = new int[2053];
   private int _victim = 0;
   private static final int SIZE;

   final byte[] get(char[] var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   final void put(char[] var1, int var2, byte[] var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
