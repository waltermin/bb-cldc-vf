package net.rim.device.api.listener;

import net.rim.vm.WeakReference;

class EventListenerManager$EventNotification implements Runnable {
   WeakReference _applicationWR;
   Object _listener;
   Event _event;
   Thread _thread;
   boolean _run;
   boolean _invoked;

   Thread getThread() {
      return this._thread;
   }

   Thread invoke() {
      throw null;
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   EventListenerManager$EventNotification(WeakReference var1, Object var2, Event var3) {
      this._applicationWR = var1;
      this._listener = var2;
      this._event = var3;
   }
}
