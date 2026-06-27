package net.rim.device.internal.system;

final class USBPortInternal$USBPortCleanupRunnable implements Runnable {
   private final USBPortInternal this$0;

   USBPortInternal$USBPortCleanupRunnable(USBPortInternal var1) {
      this.this$0 = var1;
   }

   @Override
   public final void run() {
      USBPortInternal.closeChannel(this.this$0._channel);
   }
}
