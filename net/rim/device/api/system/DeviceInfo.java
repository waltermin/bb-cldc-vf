package net.rim.device.api.system;

import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.system.Security;
import net.rim.vm.TraceBack;

public final class DeviceInfo {
   public static final int BSTAT_DEAD;
   public static final int BSTAT_TOO_COLD;
   public static final int BSTAT_TOO_HOT;
   public static final int BSTAT_LOW;
   public static final int BSTAT_NONE;
   public static final int BSTAT_REVERSED;
   public static final int BSTAT_UNKNOWN_BATTERY;
   public static final int BSTAT_NO_TURN_ON;
   public static final int BSTAT_NO_RADIO;
   public static final int BSTAT_NO_CAMERA_FLASH;
   public static final int BSTAT_CHARGING;
   public static final int BSTAT_LOW_RATE_CHARGING;
   public static final int BSTAT_IS_USING_EXTERNAL_POWER;
   public static final int BSTAT_LEVEL_CHANGED;
   public static final int BSTAT_NO_WLAN;
   public static final int BSTAT_AC_CONTACTS;
   public static final int INVALID_DEVICE_ID;
   private static final String EMPTY_STRING;

   private DeviceInfo() {
   }

   public static final native int getDeviceId();

   public static final native int getBatteryLevel();

   public static final native int getBatteryStatus();

   public static final boolean isBatteryRemovable() {
      return InternalServices.isDeviceCapable(4);
   }

   public static final int getOSVersion() {
      int[] var0 = InternalServices.parsePlatformVersionString(getPlatformVersion());
      if (var0 != null && var0.length == 4) {
         int var1 = 0;

         for (int var2 = 3; var2 >= 0; var2--) {
            if (var0[var2] <= 255) {
               var1 += (var0[3 - var2] & 0xFF) << var2 * 8;
            }
         }

         return var1;
      } else {
         return 0;
      }
   }

   public static final native String getPlatformVersion();

   public static final String getSoftwareVersion() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final native boolean isInHolster();

   public static final native long getIdleTime();

   public static final native boolean isSimulator();

   public static final String getDeviceName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final String getElectronHACID() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final native int getBatteryVoltage();

   public static final native int getBatteryTemperature();

   public static final boolean isPasswordEnabled() {
      Security var0 = Security.getInstance();
      return var0 != null ? var0.isPasswordEnabled() : false;
   }

   public static final native String getManufacturerName();

   public static final boolean hasCamera() {
      return InternalServices.isDeviceCapable(21);
   }

   public static final boolean canResetIdleTime() {
      return canResetIdleTime(TraceBack.getCallingModule(0));
   }

   public static final boolean canResetIdleTime(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getLockTimeout() {
      return Security.getInstance().getTimeout();
   }
}
