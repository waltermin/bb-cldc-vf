package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.system.LEDEngine;

public final class LED {
   public static final int STATE_OFF;
   public static final int STATE_ON;
   public static final int STATE_BLINKING;
   public static final int STATE_PATTERN;
   public static final int STATE_AUDIO_SYNC;
   public static final int BRIGHTNESS_12;
   public static final int BRIGHTNESS_25;
   public static final int BRIGHTNESS_50;
   public static final int BRIGHTNESS_100;
   public static final int LED_TYPE_STATUS;
   public static final int LED_TYPE_TRACKBALL;
   private static LEDEngine _ledEngine;

   private LED() {
   }

   private static final void assertPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   public static final boolean isSupported(int var0) {
      return LEDEngine.isSupported(var0);
   }

   public static final boolean isPolychromatic() {
      return LEDEngine.isPolychromatic();
   }

   public static final boolean isPolychromatic(int var0) {
      return LEDEngine.isPolychromatic(var0);
   }

   public static final void setConfiguration(int var0, int var1, int var2) {
      setConfiguration(0, var0, var1, var2);
   }

   public static final void setConfiguration(int var0, int var1, int var2, int var3) {
      assertPermission();
      _ledEngine.setConfigurationInternal(var0, var1, var2, var3);
   }

   public static final void setColorConfiguration(int var0, int var1, int var2) {
      setColorConfiguration(0, var0, var1, var2);
   }

   public static final void setColorConfiguration(int var0, int var1, int var2, int var3) {
      assertPermission();
      _ledEngine.setColorConfigurationInternal(var0, var1, var2, var3);
   }

   public static final void setState(int var0) {
      setState(0, var0);
   }

   public static final void setState(int var0, int var1) {
      assertPermission();
      _ledEngine.setStateInternal(var0, var1);
   }

   public static final void setColorPattern(int[] var0, boolean var1) {
      setColorPattern(0, var0, var1);
   }

   public static final void setColorPattern(int var0, int[] var1, boolean var2) {
      assertPermission();
      _ledEngine.setColorPatternInternal(var0, var1, var2);
   }
}
