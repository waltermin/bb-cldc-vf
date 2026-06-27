package net.rim.device.api.system;

import net.rim.device.api.listener.Event;

final class PersistentContent$Listeners$ModeChangeEvent implements Event {
   private int _modeGeneration;
   private Object _ticket;

   PersistentContent$Listeners$ModeChangeEvent(int var1) {
      this._modeGeneration = var1;
      this._ticket = PersistentContent.getTicket();
   }

   @Override
   public final Thread preUpdateEventListener() {
      PersistentContent._instance.setMode(this._modeGeneration);
      return null;
   }

   @Override
   public final Thread updateEventListener(Object var1) {
      if (this._modeGeneration == PersistentContent.getModeGeneration()) {
         PersistentContent$Listeners$ModeChangeThread var2 = new PersistentContent$Listeners$ModeChangeThread(
            (PersistentContentListener)var1, this._modeGeneration, this._ticket
         );
         var2.start();
         return var2;
      } else {
         return null;
      }
   }

   @Override
   public final Thread postUpdateEventListener() {
      PersistentContent._instance.setModeComplete(this._modeGeneration);
      this._ticket = null;
      return null;
   }
}
