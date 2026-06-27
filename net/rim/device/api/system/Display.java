package net.rim.device.api.system;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.ui.Graphics;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.system.InternalServices;

public final class Display {
   public static final int DISPLAY_PROPERTY_NO_SUBPIXELS;
   public static final int DISPLAY_PROPERTY_REQUIRES_BACKLIGHT;
   public static final int ORIENTATION_SQUARE;
   public static final int ORIENTATION_LANDSCAPE;
   public static final int ORIENTATION_PORTRAIT;
   public static final long SIZE_CHANGE_EVENT;
   private static int _width;
   private static int _height;
   private static int _horizontalResolution;
   private static int _verticalResolution;

   public static final void $init() {
      _width = getWidth0();
      _height = getHeight0();
      _horizontalResolution = getHorizontalResolution0();
      _verticalResolution = getVerticalResolution0();
   }

   private static final void assertPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   public static final native int getContrast();

   public static final native int getContrastIncrement();

   public static final int getHeight() {
      return _height;
   }

   private static final native int getHeight0();

   public static final int getHorizontalResolution() {
      return _horizontalResolution;
   }

   private static final native int getHorizontalResolution0();

   public static final native int getNumColors();

   public static final int getOrientation() {
      if (getWidth() > getHeight()) {
         return 1;
      } else {
         return getHeight() > getWidth() ? 2 : 0;
      }
   }

   public static final native int getProperties();

   public static final int getVerticalResolution() {
      return _verticalResolution;
   }

   private static final native int getVerticalResolution0();

   public static final int getWidth() {
      return _width;
   }

   private static final native int getWidth0();

   public static final boolean isColor() {
      return getNumColors() > 256;
   }

   public static final native boolean isContrastConfigurable();

   public static final native boolean isRowwise();

   public static final void setContrast(int var0) {
      assertPermission();
      setContrastImpl(var0);
   }

   private static final native void setContrastImpl(int var0);

   public static final void screenshot(Bitmap var0) {
      if (var0 != null && var0.getWidth() == getWidth() && var0.getHeight() == getHeight()) {
         screenshot(var0, 0, 0, getWidth(), getHeight());
      } else {
         throw new Object();
      }
   }

   public static final void screenshot(Bitmap var0, int var1, int var2, int var3, int var4) {
      if (var0 == null
         || var0.getWidth() < var3
         || var0.getHeight() < var4
         || var1 + var3 > getWidth()
         || var2 + var4 > getHeight()
         || var1 < 0
         || var2 < 0
         || var3 < 0
         || var4 < 0) {
         throw new Object();
      }

      if (InternalServices.isDeviceSecure() && !ITPolicy.getBoolean(24, 78, true)) {
         throw new ControlledAccessException();
      }

      Bitmap var5 = var0;
      int var6 = ApplicationControl.isScreenCaptureAllowed(true);
      switch (var6) {
         case 1:
         default:
            throw new ControlledAccessException();
         case 2:
            var5 = new Bitmap(var3, var4);
         case 0:
            screenshot0(var0, var1, var2, var3, var4);
            if (var5 != var0) {
               ApplicationControl.doPromptWork(var6, CommonResource.getBundle(), 10162, 25, 26);
               Object var7 = new Object(var0);
               ((Graphics)var7).drawBitmap(0, 0, var3, var4, var5, 0, 0);
            }
      }
   }

   private static final native void screenshot0(Bitmap var0, int var1, int var2, int var3, int var4);
}
