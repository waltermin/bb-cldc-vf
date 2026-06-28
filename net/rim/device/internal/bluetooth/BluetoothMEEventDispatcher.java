package net.rim.device.internal.bluetooth;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class BluetoothMEEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message message, Object listener) {
      throw new RuntimeException("cod2jar: type check");
   }
}
