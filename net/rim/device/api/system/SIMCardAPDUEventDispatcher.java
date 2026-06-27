package net.rim.device.api.system;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class SIMCardAPDUEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      SIMCardAPDUListener var3 = (SIMCardAPDUListener)var2;
      int var4 = var1.getEvent();
      int var5 = var1.getSubMessage();
      int var6 = var1.getData0();
      int var7 = var1.getData1();
      int var8 = var1.getDataLength();
      switch (var4) {
         case 2347:
            break;
         case 2348:
         default:
            switch (var5) {
               case -1:
                  return;
               case 0:
               default:
                  var3.openSuccessful((byte)var6, (byte)var7);
                  return;
               case 1:
               case 2:
               case 3:
               case 4:
                  var3.openError((byte)var5, (byte)var7);
                  return;
            }
         case 2349:
            switch (var5) {
               case -1:
                  return;
               case 0:
               default:
                  var3.exchangeAPDUSuccessful((byte)var6, (byte)var7);
                  return;
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
                  var3.exchangeAPDUError((byte)var6, (byte)var5, (byte)var7);
                  return;
            }
         case 2350:
            switch (var5) {
               case -1:
                  return;
               case 0:
               default:
                  var3.closeSuccessful((byte)var6, (byte)var7);
                  return;
               case 1:
               case 2:
                  var3.closeError((byte)var6, (byte)var5, (byte)var7);
                  return;
            }
         case 2351:
            switch (var5) {
               case 0:
                  var3.pinOpeartionSuccessful((byte)var8, var7, (byte)var6);
                  return;
               case 1:
               case 5:
                  var3.pinOperationUnSuccessful((byte)var8, var7, (byte)var5, (byte)var6);
            }
      }
   }
}
