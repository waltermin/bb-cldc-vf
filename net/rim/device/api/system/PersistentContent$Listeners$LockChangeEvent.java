package net.rim.device.api.system;

import net.rim.device.api.listener.Event;

final class PersistentContent$Listeners$LockChangeEvent implements Event {
   int _lockGeneration;

   PersistentContent$Listeners$LockChangeEvent(int var1) {
      this._lockGeneration = var1;
   }

   @Override
   public final Thread preUpdateEventListener() {
      return null;
   }

   @Override
   public final Thread updateEventListener(Object var1) {
      return null;
   }

   @Override
   public final Thread postUpdateEventListener() {
      PersistentContent._instance.lock2(this._lockGeneration);
      return null;
   }
}
