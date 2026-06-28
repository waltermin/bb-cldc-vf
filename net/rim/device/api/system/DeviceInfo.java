package net.rim.device.api.system;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.system.ITPolicyInternal;
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
      int[] array = InternalServices.parsePlatformVersionString(getPlatformVersion());
      if (array != null && array.length == 4) {
         int version = 0;

         for (int i = 3; i >= 0; i--) {
            if (array[i] <= 255) {
               version += (array[3 - i] & 0xFF) << i * 8;
            }
         }

         return version;
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
      Security s = Security.getInstance();
      return s != null ? s.isPasswordEnabled() : false;
   }

   public static final native String getManufacturerName();

   public static final boolean hasCamera() {
      return InternalServices.isDeviceCapable(21);
   }

   public static final boolean canResetIdleTime() {
      return canResetIdleTime(TraceBack.getCallingModule(0));
   }

   public static final boolean canResetIdleTime(int callingModule) {
      boolean result = false;
      boolean allowResetIdleTimePolicyDefault = !ITPolicyInternal.isITPolicyEnabled();
      if (ITPolicy.getBoolean(24, 76, allowResetIdleTimePolicyDefault)
         && !ControlledAccess.verifyCodeModuleSignature(callingModule, 51)
         && ControlledAccess.verifySignatures(true, 5526098)) {
         try {
            ApplicationControl.assertIdleTimerPermitted(true, CommonResource.getBundle(), 10166);
            return true;
         } catch (ControlledAccessException var4) {
         }
      }

      return result;
   }

   public static final int getLockTimeout() {
      return Security.getInstance().getTimeout();
   }
}
