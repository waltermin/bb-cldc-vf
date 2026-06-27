package net.rim.device.api.bluetooth;

final class BluetoothSerialPort$BluetoothSerialPortCleanupRunnable implements Runnable {
   private final BluetoothSerialPort this$0;

   BluetoothSerialPort$BluetoothSerialPortCleanupRunnable(BluetoothSerialPort var1) {
      this.this$0 = var1;
   }

   @Override
   public final void run() {
      this.this$0.closePort();
   }
}
