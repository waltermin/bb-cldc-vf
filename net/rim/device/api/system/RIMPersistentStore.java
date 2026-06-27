package net.rim.device.api.system;

import net.rim.device.api.util.Persistable;
import net.rim.vm.Persistence;
import net.rim.vm.TraceBack;

public final class RIMPersistentStore implements Persistable {
   private static final int IT_POLICY_PERSISTENT_STORE_ACCESS;

   private RIMPersistentStore() {
   }

   public static final PersistentObject getPersistentObject(long var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void destroyPersistentObject(long var0) {
      destroyPersistentObject(var0, TraceBack.getCallingModule(0));
   }

   static final void destroyPersistentObject(long var0, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Object getSynchObject() {
      return Persistence.getSynchObject();
   }
}
