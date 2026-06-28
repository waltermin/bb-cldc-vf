package net.rim.device.api.system;

import net.rim.device.api.util.Persistable;
import net.rim.vm.Persistence;
import net.rim.vm.TraceBack;

public final class RIMPersistentStore implements Persistable {
   private static final int IT_POLICY_PERSISTENT_STORE_ACCESS;

   private RIMPersistentStore() {
   }

   public static final PersistentObject getPersistentObject(long key) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void destroyPersistentObject(long key) {
      destroyPersistentObject(key, TraceBack.getCallingModule(0));
   }

   static final void destroyPersistentObject(long key, int callingModule) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object getSynchObject() {
      return Persistence.getSynchObject();
   }
}
