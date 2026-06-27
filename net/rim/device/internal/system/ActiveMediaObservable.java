package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationRegistry;

public final class ActiveMediaObservable {
   private ActiveMedia _user;
   private Object[] _listeners;
   private static final long ACTIVEMEDIAOBSERVABLE_GUID;

   private ActiveMediaObservable() {
   }

   private final synchronized void cleanupWeakRefs() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public static final ActiveMediaObservable getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      ActiveMediaObservable var1 = (ActiveMediaObservable)var0.getOrWaitFor(-5866557420524450530L);
      if (var1 == null) {
         var1 = new ActiveMediaObservable();
         var0.put(-5866557420524450530L, var1);
      }

      return var1;
   }

   public static final void setActive(ActiveMedia var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void setInactive(ActiveMedia var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final ActiveMedia getActiveMedia() {
      ActiveMediaObservable var0 = getInstance();
      return var0._user;
   }

   private final void notifyChange(Object[] var1, ActiveMedia var2, ActiveMedia var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void addListener(ActiveMediaObserver var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(ActiveMediaObserver var0) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
