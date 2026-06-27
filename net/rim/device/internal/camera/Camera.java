package net.rim.device.internal.camera;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.Bitmap;

public final class Camera {
   public static final int OPTION_FLASH;
   public static final int OPTION_WHITE_BALANCE;
   public static final int OPTION_CAMERA_EFFECT;
   public static final int OPTION_PICTURE_SIZE;
   public static final int OPTION_QUALITY;
   public static final int OPTION_SET_FREQUENCY;
   public static final int FEATURE_WHITE_BALANCE;
   public static final int FEATURE_DIGITAL_ZOOM;
   public static final int FEATURE_OPTICAL_ZOOM;
   public static final int FEATURE_PORTRAIT_MODE;
   public static final int FEATURE_LANDSCAPE_MODE;
   public static final int FEATURE_COLOUR_EFFECT;
   public static final int FEATURE_JPEG_COMPRESSION;
   public static final int FEATURE_PICTURE_SIZE;
   public static final int FEATURE_FREQ_MANUAL_SELECT;
   public static final int FLASH_OFF;
   public static final int FLASH_ON;
   public static final int FLASH_AUTO;
   public static final int QUALITY_LOW;
   public static final int QUALITY_MID;
   public static final int QUALITY_HIGH;
   public static final int QUALITY_COUNT;
   public static final int WB_AUTO;
   public static final int WB_SUNNY;
   public static final int WB_CLOUDY;
   public static final int WB_NIGHT;
   public static final int WB_INDOOR;
   public static final int WB_TUNGSTEN;
   public static final int WB_FLUORESCENT;
   public static final int CE_NORMAL;
   public static final int CE_NEGATIVE;
   public static final int CE_SOLARISE;
   public static final int CE_SKETCH;
   public static final int CE_EMBOSS;
   public static final int CE_BLACKWHITE;
   public static final int CE_SEPIA;
   public static final int CE_ANTIQUE;
   public static final int FREQUENCY_60_HZ;
   public static final int FREQUENCY_50_HZ;
   private static final int UNSPECIFED_PARM;
   public static int _pictureWidth;
   public static int _pictureHeight;
   public static int _pictureQuality;
   private static int UNKNOWN_MODE_HANDLE;
   public static int CAMERA_MODE_HANDLE;
   public static int VIDEO_MODE_HANDLE;
   private static int _internalHandle;

   public static final native int[] getMaxPictureDimensions();

   public static final int openViewfinder(int var0, int var1, int var2, int var3) {
      int var4 = UNKNOWN_MODE_HANDLE;
      startViewfinder(var0, var1, var2, var3);
      var4 = CAMERA_MODE_HANDLE;
      _internalHandle = var4;
      return var4;
   }

   private static final native void startViewfinder(int var0, int var1, int var2, int var3);

   private static final native void stopViewfinder();

   public static final void closeViewfinder(int var0) {
      if (var0 == _internalHandle || _internalHandle == UNKNOWN_MODE_HANDLE) {
         stopViewfinder();
         _internalHandle = UNKNOWN_MODE_HANDLE;
      }
   }

   public static final native void pauseViewfinder(Bitmap var0);

   public static final native void resumeViewfinder();

   public static final native boolean takePicture();

   public static final native void getPreview(Bitmap var0);

   public static final native byte[] getPicture(int var0, int var1, int var2, String var3, String var4);

   public static final byte[] getPicture(String var0, String var1) {
      if (_pictureWidth != -1 && _pictureHeight != -1 && _pictureQuality != -1) {
         return getPicture(_pictureWidth, _pictureHeight, _pictureQuality, var0, var1);
      } else {
         throw new Object();
      }
   }

   public static final native int[] getZoomLevels();

   public static final native void setZoomLevel(int var0);

   public static final native void setOption(int var0, int var1);

   public static final void setPictureResolution(int var0, int var1, int var2) {
      if (var2 >= 0 && var2 < 3) {
         if (_pictureWidth != var0 || _pictureHeight != var1) {
            _pictureWidth = var0 & 65535;
            _pictureHeight = var1 & 65535;
            if ((getFeatures(0) & 128) != 0) {
               setOption(8, _pictureWidth << 16 | _pictureHeight);
            }
         }

         if (_pictureQuality != var2) {
            _pictureQuality = var2;
            if ((getFeatures(0) & 128) != 0) {
               setOption(16, var2);
            }
         }
      } else {
         throw new Object();
      }
   }

   public static final native int queryStatus();

   public static final boolean isViewfinderActive() {
      return queryStatus() == 1;
   }

   public static final native int getFeatures(int var0);

   public static final int getColourEffects() {
      return getFeatures(32);
   }

   public static final void addListener(Application var0, CameraListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, CameraListener var1) {
      var0.removeListener(55, var1);
   }

   public static final int openVideoViewfinder(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      startVideoViewfinder(var0, var1, var2, var3, var4, var5, var6, var7);
      _internalHandle = VIDEO_MODE_HANDLE;
      return _internalHandle;
   }

   private static final native void startVideoViewfinder(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public static final native void recordVideoToFile(int var0, int var1);

   public static final native void recordVideoToStream(int var0, int var1);

   public static final native void startVideoRecord();

   public static final native void stopVideoRecord();

   public static final native void finalizeVideoRecord();

   public static final native void transcodeVideoFile(int var0, int var1);

   public static final native void transcodeVideoStream(int var0, int var1);
}
