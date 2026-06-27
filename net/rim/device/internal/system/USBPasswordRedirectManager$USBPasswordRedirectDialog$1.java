package net.rim.device.internal.system;

class USBPasswordRedirectManager$USBPasswordRedirectDialog$1 implements Runnable {
   private final USBPasswordRedirectManager$USBPasswordRedirectDialog this$1;

   USBPasswordRedirectManager$USBPasswordRedirectDialog$1(USBPasswordRedirectManager$USBPasswordRedirectDialog var1) {
      this.this$1 = var1;
   }

   @Override
   public void run() {
      this.this$1.close(1);
   }
}
