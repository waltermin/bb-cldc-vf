package net.rim.device.api.system;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class SIMCardATEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message message, Object listener) {
      throw new RuntimeException("cod2jar: type check");
   }
}
