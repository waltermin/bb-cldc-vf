package net.rim.device.internal.ui.component;

class MIDletPlatformRequestDialog$1 implements Runnable {
   private final MIDletPlatformRequestDialog this$0;

   MIDletPlatformRequestDialog$1(MIDletPlatformRequestDialog var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      this.this$0.close(-1);
   }
}
