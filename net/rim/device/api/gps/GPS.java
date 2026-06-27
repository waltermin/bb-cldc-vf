package net.rim.device.api.gps;

import net.rim.device.api.bluetooth.BluetoothSerialPort;
import net.rim.device.api.bluetooth.BluetoothSerialPortInfo;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.internal.bluetooth.BluetoothME;
import net.rim.device.internal.system.InternalServices;

public final class GPS {
   public static final int GPS_MODE_EMERGENCY_ONLY;
   public static final int GPS_MODE_LOCATION_ON;
   public static final int GPS_LOCATION_TYPE_STANDARD;
   public static final int GPS_LOCATION_TYPE_DETAILED;
   public static final int GPS_LOCATION_TYPE_EXTENDED;
   public static final int GPS_AID_MODE_CELLSITE;
   public static final int GPS_AID_MODE_ASSIST;
   public static final int GPS_AID_MODE_AUTONOMOUS;
   public static final int GPS_ERROR_NONE;
   public static final int GPS_ERROR_NO_FIX_IN_ALLOTTED_TIME;
   public static final int GPS_ERROR_DEGRADED_FIX_IN_ALLOTTED_TIME;
   public static final int GPS_ERROR_TIMEOUT_NO_FIX_NO_ASSIST_DATA;
   public static final int GPS_ERROR_TIMEOUT_DEGRADED_FIX_NO_ASSIST_DATA;
   public static final int GPS_ERROR_LOW_BATTERY;
   public static final int GPS_ERROR_CHIPSET_DEAD;
   public static final int GPS_ERROR_INVALID_REQUEST;
   public static final int GPS_ERROR_PRIVACY_ACCESS_DENIED;
   public static final int GPS_ERROR_ALMANAC_OUTDATED;
   public static final int LPS_RESULT_COMPLETE;
   public static final int LPS_RESULT_FAILED;
   public static final int LPS_STATUS_INVALID_PARAMETER;
   public static final int LPS_STATUS_PENDING;
   public static final int LPS_STATUS_OS_BUSY;
   public static final int LPS_STATUS_SUCCESS;
   public static final int LPS_ID_LOCATION_BY_PERMISSION;
   public static final int LPS_ID_LOCATION_RESTRICTED;
   public static final int LPS_ID_LOCATION_UNRESTRICTED;
   public static final int GPS_AID_MODE_CELLSITE_FLAG;
   public static final int GPS_AID_MODE_ASSIST_FLAG;
   public static final int GPS_AID_MODE_AUTONOMOUS_FLAG;
   public static final int GPS_EPHEMERIS_FORMAT_SIRF_UNCOMPRESSED;
   public static final int GPS_EPHEMERIS_STATUS_VALID;
   public static final int GPS_EPHEMERIS_STATUS_UNAVAILABLE;
   public static final int GPS_EPHEMERIS_STATUS_UNSUPPORTED_TYPE;
   public static final int GPS_POWER_USAGE_NOT_REQ;
   public static final int GPS_POWER_USAGE_LOW;
   public static final int GPS_POWER_USAGE_MED;
   public static final int GPS_POWER_USAGE_HIGH;
   public static final int GPS_LOCATION_AIDING_STATUS_UNKNOWN;
   public static final int GPS_LOCATION_AIDING_STATUS_VALID;
   public static final int GPS_LOCATION_AIDING_STATUS_MOVED;
   public static long GPS_DATA_SOURCE_KEY;
   public static String GPS_SOURCE_DEVICE;
   public static long GPS_MODE_KEY;
   private static PersistentObject _gpsModeStore;

   private GPS() {
   }

   public static final boolean isSupported() {
      return InternalServices.isDeviceCapable(6);
   }

   public static final boolean isSupportedOnCurrentNetwork() {
      return InternalServices.getHardwareID() == 67112452 && (RadioInfo.getActiveWAFs() & 1) == 1 ? false : isSupported();
   }

   public static final void setLAPIDataSource(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String getLAPIDataSource() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isLAPIDataSourceInternalGPS() {
      String var0 = getLAPIDataSource();
      return var0 == null ? isSupported() : var0.equals(GPS_SOURCE_DEVICE);
   }

   public static final boolean isLAPISupported() {
      if (isSupported()) {
         return true;
      }

      if (BluetoothME.isSupported()) {
         BluetoothSerialPortInfo[] var0 = BluetoothSerialPort.getSerialPortInfo();
         if (var0 != null && var0.length > 0) {
            return true;
         }
      }

      return false;
   }

   public static final native int getAidCapability();

   public static final native boolean nativeRequestModeChange(int var0);

   public static final boolean requestModeChange(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native int nativeGetMode();

   public static final int getMode() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native int getMaxFixPeriod();

   public static final native boolean startLocationUpdate(int var0, int var1, int var2, GPS$AppCriteria var3);

   public static final boolean startUpdate(int var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native boolean stopLocationUpdate(int var0);

   public static final boolean stopUpdate(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native int getLocation(GPSLocation var0, int var1);

   public static final native boolean getRequestedGPSAssistData(GPSRequestedAssistData var0);

   public static final native int requestGetLPS();

   public static final native int requestSetLPS(int var0, byte[] var1);

   public static final native int requestEnablePIN(boolean var0, byte[] var1);

   public static final native int requestChangePIN(byte[] var0, byte[] var1);

   public static final void addListener(Application var0, GPSListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, GPSListener var1) {
      var0.removeListener(23, var1);
   }

   public static final GPS$GPSPDEInfo getPDEInfo() {
      GPS$GPSPDEInfo var0 = new GPS$GPSPDEInfo();
      getPDEInfo(var0);
      return var0;
   }

   private static final native void getPDEInfo(GPS$GPSPDEInfo var0);

   public static final native boolean setPDEInfo(GPS$GPSPDEInfo var0);

   public static final native boolean startFledgeGPS();

   public static final native void stopFledgeGPS();

   public static final native void ephemerisDataAvailable(int var0);

   public static final native void provideEphemerisData(int var0, byte[] var1);

   public static final native void provideLocationAiding(GPSLocationAiding var0);
}
