package net.rim.device.api.system;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class SIMCardPBEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      SIMCardPhoneBookListener var3 = (SIMCardPhoneBookListener)var2;
      int var4 = var1.getEvent();
      int var5 = var1.getSubMessage();
      int var6 = var1.getData0();
      int var7 = var1.getData1();
      switch (var5) {
         case 0:
         default:
            var3.responsePhoneBookRead(var4 == 2344);
            return;
         case 1:
            var3.responsePhoneBookWrite(var4 == 2344, var6, var7);
            return;
         case 2:
            var3.responsePhoneBookDelete(var4 == 2344, var6);
         case -1:
      }
   }
}
