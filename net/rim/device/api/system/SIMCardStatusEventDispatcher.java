package net.rim.device.api.system;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class SIMCardStatusEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      SIMCardStatusListener var3 = (SIMCardStatusListener)var2;
      int var4 = var1.getEvent();
      int var5 = var1.getSubMessage();
      int var6 = var1.getData0();
      switch (var4) {
         case 2304:
            if (var5 == 0) {
               var3.cardInserted();
               return;
            }

            var3.cardReady();
            return;
         case 2305:
            var3.cardInvalid(var5, var6);
            return;
         case 2307:
            var3.cardFault(var5);
            return;
         case 2315:
            var3.smsEFFull();
            return;
         case 2323:
            var3.cardUpdated();
            return;
         case 2324:
            var3.responseDeleteSMS(var5, var6);
            return;
         case 2342:
            var3.responseMarkSMSAsRead(var5, var6);
      }
   }
}
