package net.rim.device.api.system;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class SIMCardSecurityEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      SIMCardSecurityListener var3 = (SIMCardSecurityListener)var2;
      int var4 = var1.getEvent();
      int var5 = var1.getSubMessage();
      int var6 = var1.getData0();
      int var7 = var1.getData1();
      switch (var4) {
         case 1632:
            var3.wtlsKeyWriteComplete(var5);
            break;
         case 2306:
            if (var5 == 1) {
               var3.requestSendPUK(var7);
               return;
            }

            var3.requestSendPIN(var6);
            return;
         case 2308:
         case 2309:
            switch (var5) {
               case -1:
                  return;
               case 0:
                  var3.responseValidatePIN(var6 >> 8, var7 >> 16, var7 & 0xFF);
                  return;
               case 1:
                  var3.responseChangePIN(var6 >> 8, var7 >> 16, var7 & 0xFF);
                  return;
               case 2:
                  var3.responseDisablePIN(var6 >> 8, var7 >> 16, var7 & 0xFF);
                  return;
               case 3:
               default:
                  var3.responseEnablePIN(var6 >> 8, var7 >> 16, var7 & 0xFF);
                  return;
               case 4:
                  var3.responseChangePIN(var6 >> 8, var7 >> 16, var7 >> 8 & 0xFF);
                  return;
            }
         case 2310:
            var3.pinValid();
            return;
         case 2311:
         case 2312:
            var3.responseDeactivateMEP(var4 == 2311);
            return;
      }
   }
}
