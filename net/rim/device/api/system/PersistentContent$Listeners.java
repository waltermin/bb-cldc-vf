package net.rim.device.api.system;

import net.rim.device.api.listener.EventListenerManager;
import net.rim.device.internal.proxy.Proxy;

final class PersistentContent$Listeners {
   private EventListenerManager _listeners = (EventListenerManager)(new Object());
   private PersistentContent$Listeners$Indicator _indicator;
   private int _state;
   private int _modeGeneration;
   private Application _proxy;

   PersistentContent$Listeners(int var1, int var2) {
      this._state = var1;
      this._modeGeneration = var2;
      this._proxy = Proxy.getInstance();
   }

   final void addIndicator(PersistentContentListener var1) {
      this._indicator = new PersistentContent$Listeners$Indicator(this, var1);
   }

   final void updateStateIndicator() {
      this.updateStateIndicator(this._state);
   }

   private final void updateStateIndicator(int var1) {
      if (this._indicator != null) {
         this._indicator.update(var1);
      }
   }

   final void add(PersistentContentListener var1, boolean var2) {
      this._listeners.add(var1, var2);
   }

   final void remove(PersistentContentListener var1) {
      this._listeners.remove(var1);
   }

   final boolean isListener(PersistentContentListener var1) {
      return this._listeners.isListener(var1);
   }

   final int getState() {
      return this._state;
   }

   final boolean isUpdateComplete() {
      return this._listeners.isUpdateComplete();
   }

   final void stateChanged(int var1, int var2) {
      this.updateStateIndicator(var1);
      if (this._state != var1) {
         this._state = var1;
         this._proxy
            .invokeLater(new PersistentContent$Listeners$EventLauncher(this._listeners, new PersistentContent$Listeners$StateChangeEvent(var1, var2), true));
      }
   }

   final void lockChanged(int var1) {
      this._proxy.invokeLater(new PersistentContent$Listeners$EventLauncher(this._listeners, new PersistentContent$Listeners$LockChangeEvent(var1), false));
   }

   final void modeChanged(int var1) {
      if (this._modeGeneration != var1) {
         this._modeGeneration = var1;
         this._proxy.invokeLater(new PersistentContent$Listeners$EventLauncher(this._listeners, new PersistentContent$Listeners$ModeChangeEvent(var1), true));
      }
   }
}
