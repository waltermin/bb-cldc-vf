package net.rim.device.internal.system;

import net.rim.vm.Message;

final class SmartCardPortEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      if (var2 instanceof SmartCardPortListener) {
         int var3 = var1.getEvent();
         int var4 = var1.getSubMessage();
         SmartCardPortListener var5 = (SmartCardPortListener)var2;
         switch (var3) {
            case 4097:
               var5.dataReceived();
               return;
            case 4098:
               var5.dataSent();
               return;
            case 4099:
               if (var4 == 4101) {
                  var5.cardRemoved();
                  return;
               }

               if (var4 == 4100) {
                  var5.cardInserted();
                  return;
               }

               if (var4 == 4102) {
                  var5.openSuccessful();
                  return;
               }

               if (var4 == 4107) {
                  var5.setProtocolSuccessful();
                  return;
               }
               break;
            case 4103:
               if (var4 == 4104) {
                  var5.openError(var1.getData0());
               }

               if (var4 == 4108) {
                  var5.setProtocolError();
               }
         }
      }
   }
}
