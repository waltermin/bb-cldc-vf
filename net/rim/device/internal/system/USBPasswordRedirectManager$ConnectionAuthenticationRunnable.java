package net.rim.device.internal.system;

class USBPasswordRedirectManager$ConnectionAuthenticationRunnable implements Runnable {
   int _channel;
   private final USBPasswordRedirectManager this$0;

   USBPasswordRedirectManager$ConnectionAuthenticationRunnable(USBPasswordRedirectManager var1, int var2) {
      this.this$0 = var1;
      this._channel = var2;
   }

   @Override
   public void run() {
      this.this$0.connectionAuthenticationRequiredPrivate(this._channel);
   }
}
