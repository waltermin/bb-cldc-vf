package net.rim.device.api.system;

final class TimedRunnable {
   private Runnable _runnable;
   private long _time;
   private boolean _repeat;

   TimedRunnable(Runnable var1, long var2, boolean var4) {
      this.reset(var1, var2, var4);
   }

   final void reset(Runnable var1, long var2, boolean var4) {
      this._runnable = var1;
      this._time = var2;
      this._repeat = var4;
   }

   final void clear() {
      this._runnable = null;
   }

   final boolean getRepeat() {
      return this._repeat;
   }

   final long getTime() {
      return this._time;
   }

   final Runnable getRunnable() {
      return this._runnable;
   }
}
