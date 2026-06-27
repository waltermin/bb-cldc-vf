package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.system.InternalServices;
import net.rim.vm.TraceBack;

public final class Backlight {
   public static final int BACKLIGHT_LCD;
   public static final int BACKLIGHT_KEYPAD;
   public static final int BACKLIGHT_LCD_KEYPAD;

   private static final void assertPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   private static final native void enable0(boolean var0, int var1);

   public static final void enable(boolean var0) {
      assertPermission();
      if (var0) {
         resetIdleTime(TraceBack.getCallingModule(0));
      }

      enable0(var0, 0);
      Thread.yield();
   }

   public static final void enable(boolean var0, int var1) {
      assertPermission();
      if (var1 < 1) {
         throw new Object();
      }

      if (var1 > 255) {
         var1 = 255;
      }

      if (var0) {
         resetIdleTime(TraceBack.getCallingModule(0));
      }

      enable0(var0, var1);
      Thread.yield();
   }

   private static final void resetIdleTime(int var0) {
      if (DeviceInfo.canResetIdleTime(var0)) {
         InternalServices.resetIdleTime();
      }
   }

   public static final native boolean isEnabled();

   public static final native int getTimeoutDefault();

   public static final void setTimeout(int var0) {
      assertPermission();
      setTimeout0(var0);
   }

   private static final native void setTimeout0(int var0);

   public static final native int getBrightness();

   public static final native boolean isBrightnessConfigurable();

   public static final native int getBrightnessIncrement();

   public static final native int getBrightnessDefault();

   public static final void setBrightness(int var0) {
      assertPermission();
      setBrightness0(var0);
   }

   private static final native void setBrightness0(int var0);

   public static final native long getCumulativeOnTime();

   public static final void resetElapsedTime() {
      assertPermission();
      resetElapsedTime0();
   }

   private static final native void resetElapsedTime0();

   public static final void enable(int var0, boolean var1) {
      assertPermission();
      enable0(var0, var1);
   }

   private static final native void enable0(int var0, boolean var1);
}
