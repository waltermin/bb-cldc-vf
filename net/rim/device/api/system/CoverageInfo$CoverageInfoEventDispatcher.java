package net.rim.device.api.system;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class CoverageInfo$CoverageInfoEventDispatcher extends EventDispatcher {
   private CoverageInfo$CoverageInfoEventDispatcher() {
   }

   @Override
   public final void dispatch(Message var1, Object var2) {
      int var3 = var1.getEvent();
      CoverageStatusListener var4 = (CoverageStatusListener)var2;
      switch (var3) {
         case 0:
            int var5 = var1.getSubMessage();
            var4.coverageStatusChanged(var5);
      }
   }

   CoverageInfo$CoverageInfoEventDispatcher(CoverageInfo$1 var1) {
      this();
   }
}
