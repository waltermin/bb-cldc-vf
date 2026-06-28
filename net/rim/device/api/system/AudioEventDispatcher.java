package net.rim.device.api.system;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.device.internal.system.InternalServices;
import net.rim.vm.Message;

final class AudioEventDispatcher extends EventDispatcher {
   private boolean _hasMultibuttonHeadset = InternalServices.isSoftwareCapable(14);

   @Override
   public final void dispatch(Message message, Object listener) {
      throw new RuntimeException("cod2jar: type check");
   }
}
