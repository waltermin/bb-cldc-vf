package net.rim.device.api.listener;

import java.util.Vector;
import net.rim.device.api.system.Application;
import net.rim.device.internal.proxy.Proxy;
import net.rim.vm.WeakReference;

public class EventListenerManager {
   private Vector _listeners = (Vector)(new Object());
   private Vector _applications = (Vector)(new Object());
   private Vector _eventNotifications = (Vector)(new Object());
   private Thread _eventNotificationsThread;
   private boolean _eventNotificationsThreadRunning;
   private Application _proxy = Proxy.getInstance();
   private WeakReference _proxyWR = (WeakReference)(new Object(this._proxy));
   private int _proxyProcessID = this._proxy.getProcessId();

   public synchronized void add(Object var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized boolean isListener(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized void remove(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized Object[] getListeners(Object[] var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized void update(Object var1, Event var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized void update(Event var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private synchronized void startNotificationThread() {
      if (!this._eventNotificationsThreadRunning) {
         new EventListenerManager$EventNotificationsThreadLauncher(this, null).run();
         this._eventNotificationsThreadRunning = true;
      }
   }

   public synchronized boolean isUpdateComplete() {
      return !this._eventNotificationsThreadRunning;
   }
}
