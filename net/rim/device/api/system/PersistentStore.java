package net.rim.device.api.system;

import net.rim.device.api.util.Persistable;
import net.rim.device.internal.applicationcontrol.ApplicationControl;

public final class PersistentStore implements Persistable {
   private PersistentStore() {
   }

   private static final void assertPermission() {
      ApplicationControl.assertIPCAllowed(true);
   }

   public static final PersistentObject getPersistentObject(long var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final void destroyPersistentObject(long var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Object getSynchObject() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
