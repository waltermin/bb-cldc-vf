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

   ForcedResetManager$ResetRunnable(
      String message, int numResetWarnings, long timeBetweenResetWarnings, boolean delayedResetOption, boolean dialogShouldTimeout
   ) {
      this._message = message;
      this._numResetWarnings = numResetWarnings;
      this._timeBetweenResetWarnings = timeBetweenResetWarnings;
      this._delayedResetOption = delayedResetOption;
      this._dialogShouldTimeout = dialogShouldTimeout;
   }

   public void update(String message, int numResetWarnings, long timeBetweenResetWarnings, boolean delayedResetOption, boolean dialogShouldTimeout) {
      if (numResetWarnings < this._numResetWarnings) {
         if (numResetWarnings <= this._warningNumber) {
            this._numResetWarnings = this._warningNumber;
         } else {
            this._numResetWarnings = numResetWarnings;
         }
      }

      if (timeBetweenResetWarnings < this._timeBetweenResetWarnings) {
         this._timeBetweenResetWarnings = timeBetweenResetWarnings;
         this._message = message;
      }

      this._delayedResetOption &= delayedResetOption;
      this._dialogShouldTimeout |= dialogShouldTimeout;
      if (dialogShouldTimeout) {
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
