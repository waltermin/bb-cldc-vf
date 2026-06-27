package net.rim.device.internal.system;

class LEDEventProcessor extends Thread implements LEDConstants {
   private LEDEventProcessor$LEDEngineGuard _guard;
   private int _guardInvokeId;
   private LEDEventProcessor$EventStack _events;
   private LEDEngine _ledEngine;

   LEDEventProcessor(LEDEngine var1) {
      this._ledEngine = var1;
      this._events = new LEDEventProcessor$EventStack();
      this._guard = new LEDEventProcessor$LEDEngineGuard(this);
      this._guardInvokeId = -1;
   }

   void addEvent(long var1, boolean var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void removeEvents(long var1, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   boolean contains(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void notifyLEDThread() {
      throw new RuntimeException("cod2jar: exception table");
   }

   Object getLockObject() {
      return this._events;
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
