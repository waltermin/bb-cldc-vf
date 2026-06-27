package net.rim.device.internal.system;

import net.rim.device.internal.proxy.Proxy;

class ForcedResetManager$ResetRunnable implements Runnable {
   int _warningNumber = 1;
   String _message;
   int _numResetWarnings;
   long _timeBetweenResetWarnings;
   boolean _delayedResetOption;
   boolean _dialogShouldTimeout;
   boolean _dialogCurrentlyDisplayed;
   int _id;

   ForcedResetManager$ResetRunnable(String var1, int var2, long var3, boolean var5, boolean var6) {
      this._message = var1;
      this._numResetWarnings = var2;
      this._timeBetweenResetWarnings = var3;
      this._delayedResetOption = var5;
      this._dialogShouldTimeout = var6;
   }

   public void update(String var1, int var2, long var3, boolean var5, boolean var6) {
      if (var2 < this._numResetWarnings) {
         if (var2 <= this._warningNumber) {
            this._numResetWarnings = this._warningNumber;
         } else {
            this._numResetWarnings = var2;
         }
      }

      if (var3 < this._timeBetweenResetWarnings) {
         this._timeBetweenResetWarnings = var3;
         this._message = var1;
      }

      this._delayedResetOption &= var5;
      this._dialogShouldTimeout |= var6;
      if (var6) {
         this.enableDialogTimeout();
      }
   }

   public void enableDialogTimeout() {
      if (this._id == 0 && this._dialogCurrentlyDisplayed) {
         this._id = Proxy.getInstance().invokeLaterInternal(new ForcedResetManager$ResetRunnable$DelayedResetRunnable(this, null, 1), 600000, false);
      }
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
