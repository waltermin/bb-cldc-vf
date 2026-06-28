package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.ListenerUtilities;

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
      ApplicationRegistry registry = ApplicationRegistry.getApplicationRegistry();
      ActiveMediaObservable observable = (ActiveMediaObservable)registry.getOrWaitFor(-5866557420524450530L);
      if (observable == null) {
         observable = new ActiveMediaObservable();
         registry.put(-5866557420524450530L, observable);
      }

      return observable;
   }

   public static final void setActive(ActiveMedia newUser) {
      ActiveMediaObservable observable = getInstance();
      ActiveMedia user;
      Object[] listeners;
      synchronized (observable) {
         if (observable._user != newUser) {
            user = observable._user;
            listeners = observable._listeners;
            observable._user = newUser;
         } else {
            user = null;
            listeners = null;
         }
      }

      if (listeners != null) {
         observable.notifyChange(listeners, user, newUser);
      }
   }

   public static final void setInactive(ActiveMedia fromUser) {
      ActiveMediaObservable observable = getInstance();
      ActiveMedia user;
      Object[] listeners;
      synchronized (observable) {
         if (observable._user == fromUser) {
            user = observable._user;
            listeners = observable._listeners;
            observable._user = null;
         } else {
            user = null;
            listeners = null;
         }
      }

      if (listeners != null) {
         observable.notifyChange(listeners, user, null);
      }
   }

   public static final ActiveMedia getActiveMedia() {
      ActiveMediaObservable observable = getInstance();
      return observable._user;
   }

   private final void notifyChange(Object[] listeners, ActiveMedia fromUser, ActiveMedia toUser) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public static final void addListener(ActiveMediaObserver listener) {
      ActiveMediaObservable observable = getInstance();
      synchronized (observable) {
         observable._listeners = ListenerUtilities.addListener(observable._listeners, new Object(listener));
         observable.cleanupWeakRefs();
      }
   }

   public static final void removeListener(ActiveMediaObserver listener) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }
}
