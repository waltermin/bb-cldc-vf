package net.rim.device.api.ui;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.AudioRouter;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.internal.ui.UiOptionsRegistry;
import net.rim.vm.TraceBack;

public final class Trackball {
   public static final int SENSITIVITY_UNSET;
   public static final int SENSITIVITY_OFF;
   public static final int FILTER_DEFAULT;
   public static final int FILTER_XY_SNAP;
   public static final int FILTER_NO_TIME_WINDOW;
   public static final int FILTER_ACCELERATION;
   private static boolean _supported;
   private static int _sensitivityX;
   private static int _sensitivityY;
   private static int _filterMask;

   private Trackball() {
   }

   public static final int getFilter() {
      return _filterMask;
   }

   public static final int getFilterForSystem() {
      return UiOptionsRegistry.getInstance().getInt(-1211370300138911215L);
   }

   public static final int getSensitivityIncrement() {
      return 10;
   }

   public static final int getSensitivityX() {
      return _sensitivityX;
   }

   public static final int getSensitivityXForSystem() {
      return UiOptionsRegistry.getInstance().getInt(4925806619770988503L);
   }

   public static final native int getSensitivityXFromOS();

   public static final int getSensitivityY() {
      return _sensitivityY;
   }

   public static final int getSensitivityYForSystem() {
      return UiOptionsRegistry.getInstance().getInt(1105523701474371332L);
   }

   public static final native int getSensitivityYFromOS();

   public static final void getStats(Trackball$Stats var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(2));
      getStats0(var0);
   }

   private static final native void getStats0(Trackball$Stats var0);

   public static final boolean isFeedbackAudibleForSystem() {
      return UiOptionsRegistry.getInstance().getBoolean(9173869926753706073L);
   }

   public static final boolean isSupported() {
      return _supported;
   }

   private static final native boolean isSupported0();

   public static final void resetStats() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(2));
      resetStats0();
   }

   private static final native void resetStats0();

   public static final void setFeedbackAudibleForSystem(boolean var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(2));
      UiOptionsRegistry.getInstance().setBoolean(9173869926753706073L, var0);
      AudioRouter.getInstance().enableInputFeedback(0, var0);
   }

   public static final void setFilter(int var0) {
      setFilterInternal(var0);
      updateDeviceWithAppSettings();
   }

   static final void setFilterInternal(int var0) {
      if (var0 != -1 && (var0 & -8) != 0) {
         throw new Object();
      }

      _filterMask = var0;
   }

   private static final native void setFilterMask0(int var0);

   public static final void setFilterForSystem(int var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(2));
      if (var0 != -1 && (var0 & -8) != 0) {
         throw new Object();
      }

      UiOptionsRegistry.getInstance().setInt(-1211370300138911215L, var0);
      updateDeviceWithAppSettings();
   }

   public static final void setSensitivityX(int var0) {
      setSensitivityXInternal(var0);
      updateDeviceWithAppSettings();
   }

   static final void setSensitivityXInternal(int var0) {
      if ((var0 < 0 || 100 < var0) && var0 != Integer.MAX_VALUE) {
         throw new Object();
      }

      _sensitivityX = var0;
   }

   private static final native void setSensitivityX0(int var0);

   public static final void setSensitivityXForSystem(int var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(2));
      if ((var0 < 0 || 100 < var0) && var0 != Integer.MAX_VALUE) {
         throw new Object();
      }

      UiOptionsRegistry.getInstance().setInt(4925806619770988503L, var0);
      updateDeviceWithAppSettings();
   }

   public static final void setSensitivityY(int var0) {
      setSensitivityYInternal(var0);
      updateDeviceWithAppSettings();
   }

   static final void setSensitivityYInternal(int var0) {
      if ((var0 < 0 || 100 < var0) && var0 != Integer.MAX_VALUE) {
         throw new Object();
      }

      _sensitivityY = var0;
   }

   private static final native void setSensitivityY0(int var0);

   public static final void setSensitivityYForSystem(int var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(2));
      if ((var0 < 0 || 100 < var0) && var0 != Integer.MAX_VALUE) {
         throw new Object();
      }

      UiOptionsRegistry.getInstance().setInt(1105523701474371332L, var0);
      updateDeviceWithAppSettings();
   }

   static final void updateDeviceWithAppSettings() {
      if (isSupported() && Application.getApplication().isForeground()) {
         UiOptionsRegistry var0 = UiOptionsRegistry.getInstance();
         int var1 = _sensitivityX != Integer.MAX_VALUE ? _sensitivityX : var0.getInt(4925806619770988503L);
         int var2 = _sensitivityY != Integer.MAX_VALUE ? _sensitivityY : var0.getInt(1105523701474371332L);
         setSensitivityX0(var1);
         setSensitivityY0(var2);
         AudioRouter.getInstance().enableInputFeedback(0, var0.getBoolean(9173869926753706073L));
         int var3 = _filterMask != -1 ? _filterMask : var0.getInt(-1211370300138911215L);
         if (var3 == -1) {
            var3 = 1;
         }

         setFilterMask0(var3);
      }
   }
}
