package net.rim.device.api.system;

final class PersistentContent$Listeners$ModeChangeThread extends Thread {
   private PersistentContentListener _listener;
   private int _modeGeneration;
   private Object _ticket;

   PersistentContent$Listeners$ModeChangeThread(PersistentContentListener var1, int var2, Object var3) {
      this._listener = var1;
      this._modeGeneration = var2;
      this._ticket = var3;
      if (this._ticket != null) {
         this._ticket.hashCode();
      }
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
