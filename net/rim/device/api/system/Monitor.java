package net.rim.device.api.system;

final class Monitor {
   private long _guid;
   private boolean _hasBeenNotified;
   private Thread _owner;

   Monitor(long var1) {
      this._guid = var1;
   }

   Monitor(long var1, Thread var3) {
      this._guid = var1;
      this._owner = var3;
   }

   final boolean hasBeenNotified() {
      return this._hasBeenNotified;
   }

   final Thread getOwner() {
      return this._owner;
   }

   final long getGUID() {
      return this._guid;
   }

   final void wakeyWakey() {
      this._hasBeenNotified = true;
      super.notifyAll();
   }
}
