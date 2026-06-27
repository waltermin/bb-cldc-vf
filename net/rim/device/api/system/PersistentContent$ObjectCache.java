package net.rim.device.api.system;

import java.util.Enumeration;
import net.rim.device.api.memorycleaner.MemoryCleanerDaemon;
import net.rim.device.api.memorycleaner.MemoryCleanerListener;
import net.rim.device.api.util.IntHashtable;

final class PersistentContent$ObjectCache implements MemoryCleanerListener {
   private IntHashtable _hashtable = (IntHashtable)(new Object(131));
   private int[] _hashes = new int[131];
   private int _victim;
   private static final int SIZE;

   PersistentContent$ObjectCache() {
      for (int var1 = 130; var1 >= 0; this._hashes[var1] = var1--) {
         this._hashtable.put(var1, new PersistentContent$ObjectCacheElement());
      }

      MemoryCleanerDaemon.addListener(this, false);
   }

   final Object get(char[] var1, boolean var2) {
      int var3 = net.rim.vm.Memory.objectToInt(var1);
      PersistentContent$ObjectCacheElement var4 = (PersistentContent$ObjectCacheElement)this._hashtable.get(var3);
      return var4 == null ? null : var4.get(var1, var2);
   }

   final void put(char[] var1, boolean var2, Object var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final boolean cleanNow(int var1) {
      boolean var2 = false;
      Enumeration var3 = this._hashtable.elements();

      while (var3.hasMoreElements()) {
         PersistentContent$ObjectCacheElement var4 = (PersistentContent$ObjectCacheElement)var3.nextElement();
         var2 |= var4.cleanNow();
      }

      return var2;
   }

   @Override
   public final String getDescription() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
