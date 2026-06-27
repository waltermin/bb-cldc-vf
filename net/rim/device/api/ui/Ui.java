package net.rim.device.api.ui;

import java.util.Stack;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.ui.accessibility.AccessibleEventListener;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.UiInternalListener;
import net.rim.device.internal.ui.UiOptionsRegistry;
import net.rim.device.internal.util.OptionsRegistry$IntParameter;
import net.rim.tid.text.AttributedString;
import net.rim.vm.TraceBack;

public final class Ui {
   static final boolean DEBUG;
   public static final long UI_LOG_GUID;
   static boolean DRAW_FOCUS_IN_PAINT;
   static boolean IN_MAKE_REGION_VISIBLE;
   static XYEdges _tmpEdges;
   private static Stack _tmpDrawStack;
   private static Stack _tmpTextMetricsStack;
   private static Stack _tmpXYRectStack;
   public static final int MODE_BEGINNER;
   public static final int MODE_NORMAL;
   public static final int MODE_ADVANCED;
   public static final int INCREASE_UP;
   public static final int INCREASE_DOWN;
   public static final int CLICK_ACTION_OPEN;
   public static final int CLICK_ACTION_MENU;
   private static final int UNITS_PREFIX_Mb;
   private static final int UNITS_PREFIX_Kb;
   private static final int UNITS_PREFIX_T;
   private static final int UNITS_PREFIX_G;
   private static final int UNITS_PREFIX_M;
   private static final int UNITS_PREFIX_k;
   private static final int UNITS_PREFIX_d;
   private static final int UNITS_PREFIX_c;
   private static final int UNITS_PREFIX_m;
   private static final int UNITS_PREFIX_µ;
   private static final int UNITS_PREFIX_n;
   private static final int UNITS_PREFIX_p;
   private static final int UNITS_PREFIX_FIX8;
   private static final int UNITS_PREFIX_FIX16;
   public static final int UNITS_m;
   public static final int UNITS_pt;
   public static final int UNITS_ptd;
   public static final int UNITS_px;
   public static final int UNITS_cpt;
   public static final int UNITS_cptd;
   public static final int UNITS_cm;
   public static final int UNITS_mm;
   public static final int UNITS_µm;
   public static final int UNITS_pm;
   public static int DEFAULT_16BIT_COLOR;
   public static final long DEFAULT_COLOR_ATTRIBS;
   private static long _pxScale;
   private static long _ptdScale;
   private static final OptionsRegistry$IntParameter _mode;
   private static final OptionsRegistry$IntParameter _increaseDirection;
   private static final OptionsRegistry$IntParameter _trackballClickAction;
   private static final OptionsRegistry$IntParameter _trackwheelClickAction;
   static Runnable nullRunnable;

   private Ui() {
   }

   public static final void addUiEngineListener(UiEngineListener var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      UiEngineImpl.getUiEngine().addUiEngineListener(var0);
   }

   public static final int convertColorTo16bit(int var0) {
      int var1 = (var0 & 248) >> 3 | (var0 & 64512) >> 5 | (var0 & 16252928) >> 8;
      if (var1 == DEFAULT_16BIT_COLOR) {
         var1--;
      }

      return var1;
   }

   public static final int convertColorFrom16bit(int var0, int var1) {
      return var0 == DEFAULT_16BIT_COLOR
         ? var1
         : (var0 & 31) << 3 | (var0 & 28) >> 2 | (var0 & 2016) << 5 | (var0 & 1536) >> 1 | (var0 & 63488) << 8 | (var0 & 57344) << 3;
   }

   public static final int convertSize(int var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final long convertSizeGetScale(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long getAttributesFromFont(Font var0) {
      long var1 = 0;
      int var3 = FontRegistry.getInstance().getTypefaceNameIndex(var0.getFontFamily().getName());
      var1 |= var3 << 10;
      int var4 = var0.getHeight();
      if (var4 > 63) {
         var4 = 63;
      }

      var1 |= var4 << 0;
      int var5 = var0.getStyle();
      if ((var5 & 1) != 0) {
         var1 |= 256;
      }

      if ((var5 & 2) != 0) {
         var1 |= 512;
      }

      if ((var5 & 64) != 0) {
         var1 |= 134217984;
      }

      if ((var5 & 8) != 0) {
         var1 |= 786432;
      } else if ((var5 & 16) != 0) {
         var1 |= 786432;
      } else if ((var5 & 4) != 0) {
         var1 |= 262144;
      }

      if ((var5 & 32) != 0) {
         var1 |= 1048576;
      }

      switch (var0.getAntialiasMode()) {
         case 2:
         default:
            return var1 | 64;
         case 3:
            return var1 | 128;
         case 4:
            var1 |= 192;
         case 1:
            return var1;
      }
   }

   public static final Font getFontFromAttributes(long var0, Font var2) {
      int var3 = AttributedString.fontNameIndex(var0);
      FontFamily var4 = FontRegistry.get(FontRegistry.getTypefaceNameByIndex(var3));
      int var5 = AttributedString.fontHeight(var0);
      int var6 = 0;
      if ((var0 & 134217984) == 256) {
         var6 |= 1;
      } else if ((var0 & 134217984) == 134217984) {
         var6 |= 64;
      }

      if ((var0 & 512) != 0) {
         var6 |= 2;
      }

      if ((var0 & 786432) != 0) {
         var6 &= -29;
         long var7 = var0 & 786432;
         if (var7 == 524288) {
            var6 |= 20;
         } else if (var7 == 262144) {
            var6 |= 4;
         } else if (var7 == 786432) {
            var6 |= 12;
         }
      }

      if ((var0 & 3145728) != 0) {
         var6 &= -33;
         long var11 = var0 & 3145728;
         if (var11 == 1048576) {
            var6 |= 32;
         }
      }

      long var12 = var0 & 192;
      byte var9;
      if (var12 == 64) {
         var9 = 2;
      } else if (var12 == 128) {
         var9 = 3;
      } else if (var12 == 192) {
         var9 = 4;
      } else {
         var9 = 1;
      }

      var6 |= var2.getStyle() & 7168;
      return var4.getFont(var6, var5, 0, var9, 0);
   }

   public static final int getGlobalScreenCount() {
      return GlobalScreenManager.getVisibleScreenCount();
   }

   public static final int getIncreaseDirection() {
      if (UiOptionsRegistry.getInstance().getBoolean(-920146301111564303L)) {
         return _increaseDirection.getValue();
      } else {
         return Trackball.isSupported() ? -1 : 1;
      }
   }

   public static final int getTrackballClickAction() {
      return _trackballClickAction.getValue();
   }

   public static final int getTrackwheelClickAction() {
      return _trackwheelClickAction.getValue();
   }

   public static final int getMode() {
      return _mode.getValue();
   }

   public static final DrawTextParam getTmpDrawTextParam() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final TextMetrics getTmpTextMetrics() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final XYRect getTmpXYRect() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeUiEngineListener(UiEngineListener var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      UiEngineImpl.getUiEngine().removeUiEngineListener(var0);
   }

   public static final void returnTmpDrawTextParam(DrawTextParam var0) {
      var0.reset();
      _tmpDrawStack.push(var0);
   }

   public static final void returnTmpTextMetrics(TextMetrics var0) {
      var0.reset();
      _tmpTextMetricsStack.push(var0);
   }

   public static final void returnTmpXYRect(XYRect var0) {
      var0.set(0, 0, 0, 0);
      _tmpXYRectStack.push(var0);
   }

   public static final native boolean isStylusSupported();

   public static final void setTrackballClickAction(int var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      UiOptionsRegistry.getInstance().setInt(-792512479819739610L, var0);
   }

   public static final void setTrackwheelClickAction(int var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      UiOptionsRegistry.getInstance().setInt(-4786794427536080535L, var0);
   }

   public static final void setMode(int var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      if (var0 >= 0 && 2 >= var0) {
         UiOptionsRegistry.getInstance().setInt(-7317849688793253196L, var0);
      } else {
         throw new Object();
      }
   }

   public static final void setIncreaseDirection(int var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      if (var0 != -1 && var0 != 1) {
         throw new Object();
      }

      UiOptionsRegistry var1 = UiOptionsRegistry.getInstance();
      var1.setInt(7732797588618697066L, var0);
      var1.setBoolean(-920146301111564303L, true);
   }

   public static final void setNewInvalidate(boolean var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(2));
      UiOptionsRegistry.getInstance().setBoolean(-9149825875359456202L, var0);
   }

   public static final UiEngine getUiEngine() {
      return UiEngineImpl.getUiEngine();
   }

   public static final void setInternalListener(UiInternalListener var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      GlobalScreenManager.setUiInternalListener(var0);
   }

   static final native void setCurrentGlobalStatus(Screen var0, XYRect var1);

   public static final boolean isTTSEnabled() {
      return GlobalScreenManager.getAccessibleEventListener() != null;
   }

   public static final void setAccessibleEventListener(AccessibleEventListener var0) {
      ApplicationControl.assertScreenCapturePermitted(true, CommonResource.getBundle(), 10176);
      GlobalScreenManager.setAccessibleEventListener(var0);
   }

   public static final void removeAccessibleEventListener(AccessibleEventListener var0) {
      ApplicationControl.assertScreenCapturePermitted(true, CommonResource.getBundle(), 10176);
      GlobalScreenManager.removeAccessibleEventListener(var0);
   }
}
