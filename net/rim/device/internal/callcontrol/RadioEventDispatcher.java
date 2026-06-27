package net.rim.device.internal.callcontrol;

import net.rim.device.api.system.PhoneCallListener;
import net.rim.device.api.system.PhoneControlListener;
import net.rim.device.api.system.PhoneTimerListener;
import net.rim.device.internal.system.EventDispatcher;
import net.rim.device.internal.system.Events;
import net.rim.vm.Message;

class RadioEventDispatcher extends EventDispatcher {
   @Override
   public void dispatch(Message var1, Object var2) {
      int var3 = var1.getEvent();
      int var4 = var1.getSubMessage();
      int var5 = var1.getData0();
      int var6 = var1.getData1();
      Object var7 = var1.getObject0();
      if (!Events.dispatchPhoneCallEvent(var3, var4, var5, (PhoneCallListener)var2)) {
         if (!Events.dispatchPhoneTimerEvent(var3, var4, var5, (PhoneTimerListener)var2)) {
            if (!Events.dispatchPhoneControlEvent(var3, var4, var5, var6, var7, (PhoneControlListener)var2)) {
               ;
            }
         }
      }
   }
}
