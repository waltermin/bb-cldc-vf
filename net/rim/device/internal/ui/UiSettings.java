package net.rim.device.internal.ui;

import net.rim.device.api.system.AudioRouter;
import net.rim.device.api.system.Backlight;
import net.rim.device.api.system.Display;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.system.InternalServices;

public class UiSettings {
   private boolean _listenersActive = true;
   private RegistryListener[] _listeners = new RegistryListener[0];
   private static final long GUID;
   public static final long LED_COVERAGE_INDICATOR_GUID;
   public static final long OFF_PROFILE_ENABLED_GUID;
   public static final char CURRENCY_KEY_NONE;
   private static final int SCREEN_CONTRAST_DEFAULT;
   private static UiSettings _instance;

   private UiSettings() {
   }

   public static void addListener(RegistryListener var0) {
      Arrays.add(_instance._listeners, var0);
   }

   public static int getBacklightBrightness() {
      return UiOptionsRegistry.getInstance().getInt(1685157992482037073L);
   }

   public static int getBacklightTimeout() {
      return UiOptionsRegistry.getInstance().getInt(-4413078574218726736L);
   }

   public static int getTrackballKeylockBacklightTimeout() {
      return UiOptionsRegistry.getInstance().getInt(5292311981504290757L);
   }

   public static int getDisplayContrast() {
      return UiOptionsRegistry.getInstance().getInt(-1460892010845079752L);
   }

   public static boolean getLEDCoverageIndicatorStatus() {
      return UiOptionsRegistry.getInstance().getBoolean(669566532873263048L);
   }

   public static boolean getOffProfileEnabled() {
      return UiOptionsRegistry.getInstance().getBoolean(-3239010168274370595L);
   }

   public static boolean getAutomaticBacklightEnabled() {
      return UiOptionsRegistry.getInstance().getBoolean(-4779732858771257140L);
   }

   public static char getCurrencyKey() {
      return UiOptionsRegistry.getInstance().getChar(-9137283790714193735L);
   }

   public static boolean getKeypadToneEnabled() {
      return UiOptionsRegistry.getInstance().getBoolean(4710809342279106215L);
   }

   public static int getKeypadRepeatRate() {
      return UiOptionsRegistry.getInstance().getInt(3372005855522553662L);
   }

   public static int getKeypadRepeatDelay() {
      return UiOptionsRegistry.getInstance().getInt(4484666050398206415L);
   }

   public static void initialize() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static void notifyRegistryChanged() {
      if (_instance._listenersActive) {
         RegistryListener[] var0 = _instance._listeners;
         if (var0 != null) {
            for (int var1 = var0.length - 1; var1 >= 0; var1--) {
               var0[var1].registryChanged();
            }
         }
      }
   }

   static void notifyRegistryChanged(long var0) {
      if (_instance._listenersActive) {
         RegistryListener[] var2 = _instance._listeners;
         if (var2 != null) {
            for (int var3 = var2.length - 1; var3 >= 0; var3--) {
               var2[var3].registryChanged(var0);
            }
         }
      }
   }

   public static void setBacklightBrightness(int var0) {
      Backlight.setBrightness(var0);
      UiOptionsRegistry.getInstance().setInt(1685157992482037073L, var0);
   }

   public static void setBacklightTimeout(int var0) {
      Backlight.setTimeout(var0);
      UiOptionsRegistry.getInstance().setInt(-4413078574218726736L, var0);
   }

   public static void setTrackballKeyLockBacklightTimeout(int var0) {
      Backlight.setTimeout(var0);
      UiOptionsRegistry.getInstance().setInt(5292311981504290757L, var0);
   }

   public static void setDisplayContrast(int var0) {
      Display.setContrast(var0);
      UiOptionsRegistry.getInstance().setInt(-1460892010845079752L, var0);
   }

   public static void setLEDCoverageIndicatorStatus(boolean var0) {
      UiOptionsRegistry.getInstance().setBoolean(669566532873263048L, var0);
   }

   public static void setCurrencyKey(char var0) {
      UiOptionsRegistry.getInstance().setChar(-9137283790714193735L, var0);
   }

   public static void setOffProfileEnabled(boolean var0) {
      UiOptionsRegistry.getInstance().setBoolean(-3239010168274370595L, var0);
   }

   public static void setKeypadToneEnabled(boolean var0) {
      UiOptionsRegistry.getInstance().setBoolean(4710809342279106215L, var0);
      AudioRouter.getInstance().enableInputFeedback(1, var0);
   }

   public static void setKeypadRepeatRate(int var0) {
      UiOptionsRegistry.getInstance().setInt(3372005855522553662L, var0);
      setRepeat(getKeypadRepeatDelay(), var0);
   }

   public static void setKeypadRepeatDelay(int var0) {
      UiOptionsRegistry.getInstance().setInt(4484666050398206415L, var0);
      setRepeat(var0, getKeypadRepeatRate());
   }

   public static void setAutomaticBacklightEnabled(boolean var0) {
      boolean var1 = InternalServices.isToggleAutomaticBacklightSupported() ? var0 : true;
      if (InternalServices.isDeviceCapable(16)) {
         InternalServices.setLightSensorMode(var1 ? 1 : 3);
      }

      UiOptionsRegistry.getInstance().setBoolean(-4779732858771257140L, var1);
   }

   public static void setListenersActive(boolean var0) {
      if (var0 != _instance._listenersActive) {
         _instance._listenersActive = var0;
         if (var0) {
            notifyRegistryChanged();
         }
      }
   }

   private static native void setRepeat(int var0, int var1);
}
