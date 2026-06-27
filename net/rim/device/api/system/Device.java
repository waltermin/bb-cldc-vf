package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;

public final class Device {
   public static final long DEFAULT_DEVICE_TIME;

   private Device() {
   }

   private static final void assertPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   public static final boolean requestPowerOff(boolean var0) {
      assertPermission();
      return DeviceInternal.requestPowerOff(var0);
   }

   public static final boolean requestStorageMode() {
      assertPermission();
      return DeviceInternal.requestStorageMode();
   }

   public static final boolean setDateTime(long var0) {
      assertPermission();
      return DeviceInternal.setDateTime(var0);
   }
}
