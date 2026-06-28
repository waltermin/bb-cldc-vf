package net.rim.device.api.gps;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class GPSEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message message, Object _listener) {
      throw new RuntimeException("cod2jar: type check");
   }
}
