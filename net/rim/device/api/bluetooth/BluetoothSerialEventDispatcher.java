package net.rim.device.api.bluetooth;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class BluetoothSerialEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      BluetoothSerialPort var3 = (BluetoothSerialPort)var2;
      var3.dispatch(var1);
   }
}
