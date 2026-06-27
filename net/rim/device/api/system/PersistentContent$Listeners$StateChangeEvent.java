package net.rim.device.api.system;

import net.rim.device.api.listener.Event;

final class PersistentContent$Listeners$StateChangeEvent implements Event {
   int _state;
   int _lockGeneration;

   PersistentContent$Listeners$StateChangeEvent(int var1, int var2) {
      this._state = var1;
      this._lockGeneration = var2;
   }

   @Override
   public final Thread preUpdateEventListener() {
      return null;
   }

   @Override
   public final Thread updateEventListener(Object var1) {
      if (this._lockGeneration == PersistentContent.getLockGeneration()) {
         ((PersistentContentListener)var1).persistentContentStateChanged(this._state);
      }

      return null;
   }

   @Override
   public final Thread postUpdateEventListener() {
      return null;
   }
}
