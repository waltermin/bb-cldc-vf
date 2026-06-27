package net.rim.device.internal.crypto.fips;

import net.rim.device.api.crypto.SelfTestModule;
import net.rim.device.api.system.Radio;

class SelfTests$Tester extends Thread {
   private final SelfTests this$0;

   public SelfTests$Tester(SelfTests var1) {
      this.this$0 = var1;
   }

   private int testModule(SelfTestModule var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void run() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.this$0._modules.length; var2++) {
         var1 = this.testModule(this.this$0._modules[var2], var1);
      }

      if (this.this$0._testsFailed) {
         Radio.requestPowerOff();
         if (this.this$0._startupRun) {
            this.this$0._startupDialog.testsFailed();
         } else {
            this.this$0._detailedDialog.testsFailed();
         }
      } else if (this.this$0._startupRun) {
         this.this$0._startupDialog.testsPassed();
      } else {
         this.this$0._detailedDialog.testsPassed();
      }
   }
}
