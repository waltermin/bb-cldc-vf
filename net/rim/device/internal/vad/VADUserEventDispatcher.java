package net.rim.device.internal.vad;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class VADUserEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      VADUserEventListener var3 = (VADUserEventListener)var2;
      var3.vadEvent(var1.getEvent(), var1.getSubMessage());
   }
}
