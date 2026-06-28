package net.rim.device.api.gps;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class FledgeEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message message, Object listener) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }
}
