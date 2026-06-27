package net.rim.device.api.system;

import net.rim.device.internal.system.RadioInternal;
import net.rim.device.internal.system.SE13NetworkTable;
import net.rim.device.internal.timesync.TimeSync;

public final class RadioInfo {
   public static final int WAF_3GPP;
   public static final int WAF_CDMA;
   public static final int WAF_IDEN;
   public static final int WAF_WLAN;
   public static final int NETWORK_NONE;
   public static final int NETWORK_MOBITEX;
   public static final int NETWORK_DATATAC;
   public static final int NETWORK_GPRS;
   public static final int NETWORK_CDMA;
   public static final int NETWORK_IDEN;
   public static final int NETWORK_802_11;
   public static final int NETWORK_UMTS;
   public static final int STATE_OFF;
   public static final int STATE_ON;
   public static final int STATE_LOWBATT;
   public static final int STATE_SERVICE_REQUIRED;
   public static final int STATE_TURNING_OFF;
   public static final int STATE_TURNING_ON;
   public static final int STATE_RELOAD_REQUIRED;
   public static final int LEVEL_NO_COVERAGE;
   public static final int NETWORK_SERVICE_EMERGENCY_ONLY;
   public static final int NETWORK_SERVICE_VOICE;
   public static final int NETWORK_SERVICE_DATA;
   public static final int NETWORK_SERVICE_DIRECT_CONNECT;
   public static final int NETWORK_SERVICE_ROAMING;
   public static final int NETWORK_SERVICE_SUPPRESS_ROAMING;
   public static final int NETWORK_SERVICE_ROAMING_OFF_CAMPUS;
   public static final int NETWORK_SERVICE_IN_HOME_ZONE;
   public static final int NETWORK_SERVICE_IN_CITY_ZONE;
   public static final int NETWORK_SERVICE_E911_CALLBACK_MODE;
   public static final int NETWORK_SERVICE_EVDO;
   public static final int NETWORK_SERVICE_EVDO_ONLY;
   public static final int NETWORK_SERVICE_EDGE;
   public static final int NETWORK_SERVICE_MODEM_MODE_ENABLED;
   public static final int NETWORK_SERVICE_UMTS;
   public static final int NETWORK_SERVICE_GAN;
   public static final int NETWORK_SERVICE_SIMULTANEOUS_VOICE_AND_DATA;
   public static final int BAND_MOBITEX_900;
   public static final int BAND_DATATAC_800;
   public static final int BAND_GSM_900;
   public static final int BAND_GSM_1800;
   public static final int BAND_GSM_1900;
   public static final int BAND_CDMA_800;
   public static final int BAND_CDMA_1900;
   public static final int BAND_IDEN_800;
   public static final int BAND_GSM_850;
   public static final int BAND_UMTS_2100;
   private static final int HWE_BAND_900;
   private static final int HWE_BAND_1800;
   private static final int HWE_BAND_1900;
   private static final int HWE_BAND_850;
   private static final int HWE_BAND_2100;
   private static final int HWE_BAND_LAST;

   private RadioInfo() {
   }

   public static final native int getSupportedWAFs();

   private static final int getDefaultWAF() {
      int var0 = getSupportedWAFs();
      if ((var0 & 2) != 0) {
         return 2;
      } else if ((var0 & 1) != 0) {
         return 1;
      } else if ((var0 & 8) != 0) {
         return 8;
      } else {
         return (var0 & 4) != 0 ? 4 : 0;
      }
   }

   public static final boolean areWAFsSupported(int var0) {
      return (getSupportedWAFs() & var0) != 0;
   }

   public static final native int getActiveWAFs();

   public static final native int getEnabledWAFs();

   public static final native int getNetworkType();

   public static final native int getState();

   public static final int getSignalLevel() {
      return getSignalLevel(getActiveWAFs() & -5);
   }

   public static final native int getSignalLevel(int var0);

   public static final native int getNetworkService();

   public static final int getSupportedBands() {
      int var0 = getSupportedWAFs();
      short var1 = 0;
      if ((var0 & 2) != 0) {
         var1 |= 96;
      }

      if ((var0 & 8) != 0) {
         var1 |= 128;
      }

      if ((var0 & 1) != 0) {
         int var2 = getNetworkBands();
         if ((var2 & 8) != 0) {
            var1 |= 256;
         }

         if ((var2 & 1) != 0) {
            var1 |= 4;
         }

         if ((var2 & 2) != 0) {
            var1 |= 8;
         }

         if ((var2 & 4) != 0) {
            var1 |= 16;
         }

         if ((var2 & 16) != 0) {
            var1 |= 512;
         }
      }

      return var1;
   }

   private static final native int getNetworkBands();

   public static final native long getNumberOfPacketsSent();

   public static final native long getNumberOfPacketsReceived();

   public static final native int getNumberOfNetworks();

   public static final native int getCurrentNetworkIndex();

   public static final int getNetworkId(int var0) {
      int var1 = getNetworkIdFromOS(var0);
      return convertNetworkId(var1);
   }

   private static final native int getNetworkIdFromOS(int var0);

   public static final String getNetworkName(int var0) {
      String var1 = null;
      if (!DeviceInfo.isSimulator() && (getActiveWAFs() & 1) != 0) {
         Object var2 = ApplicationRegistry.getApplicationRegistry().waitFor(-7927117593081548760L);
         if (var2 != null) {
            return ((SE13NetworkTable)var2).getNetworkName(getNetworkId(var0));
         }
      } else {
         var1 = getNetworkNameFromOS(var0);
      }

      return var1;
   }

   private static final native String getNetworkNameFromOS(int var0);

   public static final String getNetworkCountryCode(int var0) {
      String var1 = null;
      if (!DeviceInfo.isSimulator() && (getActiveWAFs() & 1) != 0) {
         Object var2 = ApplicationRegistry.getApplicationRegistry().waitFor(-7927117593081548760L);
         if (var2 != null) {
            return ((SE13NetworkTable)var2).getCountryInitials((short)(getNetworkId(var0) & 65535));
         }
      } else {
         var1 = getNetworkCountryCodeFromOS(var0);
      }

      return var1;
   }

   private static final native String getNetworkCountryCodeFromOS(int var0);

   public static final int convertNetworkId(int var0) {
      if (!DeviceInfo.isSimulator() && (getActiveWAFs() & 1) != 0 && (var0 & 983040) == 983040) {
         Object var1 = ApplicationRegistry.getApplicationRegistry().waitFor(-7927117593081548760L);
         if (var1 != null) {
            if (((SE13NetworkTable)var1).is3DigitMNC(var0)) {
               return var0 & -983041;
            }

            var0 = (var0 & 267386880) >>> 4 | var0 & 4095;
         }
      }

      return var0;
   }

   public static final int calculateNetworkId(int var0, int var1) {
      int var2 = var0 % 100;
      int var3 = var0 / 100 << 8 | var2 / 10 << 4;
      var3 |= var2 % 10;
      var2 = var1 % 100;
      int var4 = var1 / 100 << 8 | var2 / 10 << 4;
      var4 |= var2 % 10;
      return var4 << 16 | var3;
   }

   public static final int getMNC(int var0) {
      int var1 = getNetworkId(var0);
      return (var1 & -65536) >> 16;
   }

   public static final int getMCC(int var0) {
      int var1 = getNetworkId(var0);
      return var1 & 65535;
   }

   public static final native boolean isPDPContextActive(int var0);

   public static final native String getAccessPointName(int var0);

   public static final int getAccessPointNumber(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final native int getAccessPointNumber(String var0, int var1, int var2);

   public static final byte[] getIPAddress(int var0) {
      return UDPPacketHeader.IPv4IntToByteArray(getIPv4Address(var0));
   }

   private static final native int getIPv4Address(int var0);

   public static final long getNetworkTime(long var0) {
      return TimeSync.GetNetworkTime(var0);
   }

   public static final boolean isDataServiceOperational() {
      return getState() == 1 && getSignalLevel() != -256 && (getNetworkService() & 4) != 0 && !isDataServiceSuspended();
   }

   public static final boolean isDataServiceSuspended() {
      int var0 = getNetworkService();
      int var1 = getSupportedWAFs();
      if ((var1 & 2) != 0 && (var0 & 2048) != 0) {
         return true;
      }

      if ((var1 & 1) != 0) {
         if ((RadioInternal.get3GPPSupportedRats() & 2) != 0 && (var0 & 4096) != 0) {
            return false;
         }

         if ((var0 & 32768) != 0) {
            return false;
         }
      }

      return (var1 & 4) != 0 && WLAN.isRadioOn() && WLAN.isAssociated() != null ? false : Phone.isSupported() && Phone.getInstance().isActive();
   }

   public static final String getCurrentNetworkName() {
      int var0 = getCurrentNetworkIndex();
      return var0 != -1 ? getNetworkName(var0) : null;
   }
}
