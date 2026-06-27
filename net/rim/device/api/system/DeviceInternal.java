package net.rim.device.api.system;

public class DeviceInternal {
   private DeviceInternal() {
   }

   private static native boolean requestPowerOff0(boolean var0);

   public static boolean requestPowerOff(boolean var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static native boolean requestStorageMode();

   public static native boolean setDateTime(long var0);
}
