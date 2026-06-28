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
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   EventListenerManager$EventNotification(WeakReference applicationWR, Object listener, Event event) {
      this._applicationWR = applicationWR;
      this._listener = listener;
      this._event = event;
   }
}
