package net.rim.device.api.system;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class SIMCardEFEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      SIMCardEFListener var3 = (SIMCardEFListener)var2;
      int var4 = var1.getEvent();
      int var5 = var1.getSubMessage();
      int var6 = var1.getData0();
      int var7 = var1.getData1();
      switch (var4) {
         case 2316:
            var3.responseEFRead(0, var5, var6 & 65535, var7 & 65535, var7 >> 16);
            return;
         case 2317:
         default:
            var3.responseEFInfo(0, var5 & 65535, var5 >> 16, var6 & 65535, var6 >> 16, var7 & 65535, var7 >> 16);
            return;
         case 2318:
            var3.responseEFInfo(var6, var5 & 65535, var5 >> 16, -1, -1, -1, -1);
            return;
         case 2319:
            var3.responseEFRead(var6, var5, -1, -1, -1);
            return;
         case 2320:
            var3.responseEFWrite(0, var5, var6, var7);
            return;
         case 2321:
            var3.responseEFWrite(var6, var5, -1, -1);
         case 2315:
      }
   }
}
