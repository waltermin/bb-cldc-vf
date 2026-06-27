package net.rim.device.api.system;

class PersistentContent$Listeners$Indicator implements Runnable {
   private PersistentContentListener _listener;
   private int _state;
   private boolean _pending;
   private final PersistentContent$Listeners this$0;

   PersistentContent$Listeners$Indicator(PersistentContent$Listeners var1, PersistentContentListener var2) {
      this.this$0 = var1;
      this._listener = var2;
      this._pending = false;
   }

   synchronized void update(int var1) {
      this._state = var1;
      if (!this._pending) {
         this._pending = true;
         this.this$0._proxy.invokeLater(this);
      }
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
