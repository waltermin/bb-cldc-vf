package net.rim.device.internal.bluetooth;

import net.rim.device.api.system.Application;

public final class BluetoothL2CAP {
   public static final native int registerPSM(int var0, int var1, int var2, boolean var3, int var4);

   public static final native void deregisterPSM(int var0);

   public static final native int connectRequest(int var0, int var1, int var2);

   public static final native void connectResponse(int var0, int var1);

   public static final native void disconnectRequest(int var0);

   public static final native int getPSM(int var0);

   public static final native void sendData(int var0, int var1, byte[] var2);

   public static final native int getTransmitMTU(int var0);

   public static final void addListener(Application var0, BluetoothL2CAPListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, BluetoothL2CAPListener var1) {
      var0.removeListener(12, var1);
   }
}
