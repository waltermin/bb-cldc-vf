package net.rim.device.internal.bluetooth;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.AudioRouter;
import net.rim.device.api.system.DeviceInfo;

public final class BluetoothAVRCP {
   public static final boolean isSupported() {
      return DeviceInfo.isSimulator() ? true : AudioRouter.isAudioModeSupported(36);
   }

   public static final boolean isEnabled() {
      return !ITPolicy.getBoolean(34, 19, false);
   }

   public static final native int registerChannel(int var0);

   public static final native int deregisterChannel(int var0);

   public static final native int connect(int var0, byte[] var1);

   public static final native int disconnect(int var0);

   public static final native int connectResponse(int var0, boolean var1);

   public static final native int setPanelKey(int var0, int var1, boolean var2);

   public static final void addListener(Application var0, BluetoothAVRCPListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, BluetoothAVRCPListener var1) {
      var0.removeListener(25, var1);
   }
}
