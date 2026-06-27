package net.rim.device.internal.system;

import net.rim.device.api.system.Branding;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.GAN;
import net.rim.device.api.system.QOSInfo;
import net.rim.device.api.system.Radio;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.RadioPacketHeader;
import net.rim.device.api.system.SMSParameters;
import net.rim.device.api.system.UDPPacketHeader;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.vm.Array;
import net.rim.vm.PersistentInteger;
import net.rim.vm.TraceBack;

public final class RadioInternal {
   public static final int NETWORK_MODE_UMTS;
   public static final int NETWORK_MODE_GPRS;
   public static final int NETWORK_MODE_DUAL;
   public static final int NETWORK_MODE_UMTS_ENABLED;
   public static final int NETWORK_MODE_GPRS_ENABLED;
   public static final int NETWORK_MODE_DUAL_ENABLED;
   public static final int NETWORK_SELECTION_MODE_AUTOMATIC;
   public static final int NETWORK_SELECTION_MODE_AUTOMATIC_A;
   public static final int NETWORK_SELECTION_MODE_AUTOMATIC_B;
   public static final int NETWORK_SELECTION_MODE_CUSTOM;
   public static final int NETWORK_SELECTION_MODE_MANUAL;
   public static final int NETWORK_SELECTION_MODE_HOME_ONLY;
   public static final int NETWORK_SELECTION_MODE_CDMA_ONLY;
   public static final int NETWORK_SELECTION_MODE_EVDO_ONLY;
   public static final int NETWORK_SELECTION_MODE_ROAM_ONLY;
   public static final int NETWORK_SELECTION_MODE_ENABLED_AUTOMATIC;
   public static final int NETWORK_SELECTION_MODE_ENABLED_AUTOMATIC_A;
   public static final int NETWORK_SELECTION_MODE_ENABLED_AUTOMATIC_B;
   public static final int NETWORK_SELECTION_MODE_ENABLED_CUSTOM;
   public static final int NETWORK_SELECTION_MODE_ENABLED_MANUAL;
   public static final int NETWORK_SELECTION_MODE_ENABLED_HOME_ONLY;
   public static final int NETWORK_SELECTION_MODE_ENABLED_CDMA_ONLY;
   public static final int NETWORK_SELECTION_MODE_ENABLED_EVDO_ONLY;
   public static final int NETWORK_SELECTION_MODE_ENABLED_ROAM_ONLY;
   public static final int NETWORK_CATEGORY_REGISTERED;
   public static final int NETWORK_CATEGORY_FORBIDDEN;
   public static final int NETWORK_CATEGORY_HOME;
   public static final int NETWORK_CATEGORY_WEAK;
   public static final int NETWORK_CATEGORY_GPRS_SUPPORT;
   public static final int NETWORK_CATEGORY_PREFERRED;
   public static final int NETWORK_CATEGORY_UMTS_SUPPORT;
   public static final int NETWORK_TYPE_OPERATOR_PREFERRED;
   public static final int NETWORK_TYPE_USER_PREFERRED;
   public static final int GAN_PREFERENCE_CELL_ONLY;
   public static final int GAN_PREFERENCE_CELL_PREFERRED;
   public static final int GAN_PREFERENCE_GAN_ONLY;
   public static final int GAN_PREFERENCE_GAN_PREFERRED;
   private static final long GAN_PREFERENCE_KEY;
   private static final int _ganPreferenceKeyId;
   private static final long ENABLED_RATS_KEY;
   private static final int _enabled3GPPRatsKeyId;
   private static int _activeRadios;
   public static final int INVALID_NETWORK_ID;
   private static final long MANUALLY_SELECTED_NETWORK_KEY;
   private static final int _manuallySelectedNetworkKeyId;
   private static SIMCardEfHandler _efHandler;
   public static final int PRIMARY_DNS_ADDRESS;
   public static final int SECONDARY_DNS_ADDRESS;
   public static final int PACKET_ERROR_APP_NOT_REGISTERED;
   public static final int PACKET_ERROR_PACKET_NOT_FOUND;
   public static final int PACKET_ERROR_NO_FREE_BUFFERS;
   public static final int PACKET_ERROR_BAD_TAG;
   public static final int PACKET_ERROR_GENERAL;
   public static final int PACKET_ERROR_BAD_HEADER;
   public static final int PACKET_ERROR_BAD_LENGTH;
   public static final int PACKET_ERROR_BUFFER_TOO_SMALL;
   public static final int PACKET_ERROR_APN_NOT_REGISTERED;
   public static final int PACKET_ERROR_NOT_APN_OWNER;
   public static final int PACKET_ERROR_APN_INVALID;
   private static final long PLMN_SELECTION_MODE_KEY;
   private static final int _plmnSelectionModeID;
   public static final int PROTOCOL_NUM_TCP;
   public static final int PROTOCOL_NUM_UDP;
   public static final int RADIO_PORTS_UNDEFINED_PROTO_NUM;
   public static final int RADIO_PORTS_ALREADY_REGISTERED;
   public static final int RADIO_PORTS_OUT_OF_RANGE;
   public static final int RADIO_PORTS_RESERVED;
   public static final int RADIO_PORTS_TOO_MANY_REGISTERED;
   public static final int RADIO_PORTS_NOT_REGISTERED;
   public static final int RADIO_PORTS_INVALID_OP;
   public static final int RADIO_PORTS_OP_DEREGISTER;
   public static final int RADIO_PORTS_OP_REGISTER;
   public static final int RADIO_PORTS_OP_QUERY;
   public static final int RADIO_PARAM_SUBNET_MASK;
   public static final int RADIO_PARAM_DEFAULT_GATEWAY;
   public static final int RADIO_PARAM_DNS_SERVERS;
   public static final int RADIO_PARAM_DNS_DOMAIN_NAME;
   public static final int RADIO_PARAM_NTP_SERVERS;
   public static final int RADIO_PARAM_HOSTNAME;
   public static final int RADIO_PARAM_UDP_PAYLOAD_MTU;
   public static final int RADIO_PARAM_UDP_PAYLOAD_MRU;
   public static final int RADIO_PARAM_TCP_PAYLOAD_MTU;
   public static final int RADIO_PARAM_TCP_PAYLOAD_MRU;
   public static final int SMS_ROUTE_PACKET_SWITCHED;
   public static final int SMS_ROUTE_CIRCUIT_SWITCHED;
   public static final int SMS_ROUTE_PACKET_SWITCHED_PREFERRED;
   public static final int SMS_ROUTE_CIRCUIT_SWITCHED_PREFERRED;
   public static final int SRVPGM_LOCK_READ;
   public static final int SRVPGM_BASIC_DATA_READ;
   public static final int SRVPGM_ADVANCED_DATA_READ;
   public static final int SRVPGM_GET_BASIC_DATA;
   public static final int SRVPGM_GET_ADVANCED_DATA;
   public static final int SRVPGM_BASIC_DATA_WRITE;
   public static final int SRVPGM_ADVANCED_DATA_WRITE;
   public static final int SRVPGM_CONFIG_READ;
   public static final int SRVPGM_CONFIG_WRITE;
   public static final int SRVPGM_INVALID_INPUT;
   public static final int SRVPGM_OPERATION_OK;
   public static final int SRVPGM_OPERATION_WRONG;
   public static final int BB_XP_DISABLED;
   public static final int BB_XP_ENABLED;
   public static final int DATA_CALL_GO_ACTIVE;
   public static final int IOTA_MESSAGE_TYPE_IS683;
   public static final int IOTA_MESSAGE_TYPE_PRLBIN;
   public static final int IOTA_MESSAGE_TYPE_NAMBIN;
   public static final int IOTA_MESSAGE_TYPE_AKEYBIN;
   public static final int RETURN_REQUEST_TYPE_SAVE;
   public static final int RETURN_REQUEST_TYPE_RESET;
   public static final int RETURN_REQUEST_INVALID_INPUT;
   public static final int RETURN_REQUEST_OPERATION_OK;
   public static final int RETURN_REQUEST_OPERATION_FAILED;
   public static final int NV_STRING_VOICEMAIL_NUMBER;
   public static final int NV_STRING_VOICEMAIL_NUMBER_2;
   public static final int NV_STRING_EMERGENCY_NUMBER;
   public static final int NV_STRING_NATIONAL_CODE_NUMBER;
   public static final int NV_STRING_INTERNATIONAL_CODE_NUMBER;
   public static final int NV_BOOLEAN_USE_NATIONAL_CODE_FOR_VOICE;
   public static final int NV_BOOLEAN_USE_NATIONAL_CODE_FOR_SMS;
   public static final int RAT_3GPP_GERAN;
   public static final int RAT_3GPP_UTRAN;
   public static final int RAT_3GPP_GAN;
   public static final int RAT_PREFERENCE_UNSPECIFIED;
   public static final int RAT_PREFERENCE_3GPP_CELLULAR_GAN;
   public static final int RAT_PREFERENCE_3GPP_GAN_CELLULAR;
   public static final int RADIO_3GPP;
   public static final int RADIO_CDMA;
   public static final int RADIO_WLAN;
   public static final int RADIO_IDEN;

   public static final native int serviceProgramSetup(int var0, byte[] var1);

   public static final native int setup(int var0, int var1);

   public static final native boolean requestMasterReset(byte[] var0);

   public static final native int simulTCPCommand(int var0, int var1, int var2, int var3, int var4);

   public static final native int getBlackBerryExperienceMode();

   public static final native void setBlackBerryExperienceMode(int var0);

   public static final native int gprsAttach(boolean var0);

   public static final native void smsStoreOnSIM(boolean var0);

   public static final native void smsCountUpdated(int var0);

   public static final native void smsSetRoute(int var0);

   public static final native void enableIPModem(boolean var0);

   public static final native boolean processOTASPMessage(int var0, byte[] var1);

   public static final native byte[] getNAI(int var0);

   public static final native int getPatriotSoftwareVersion();

   public static final native int processReturnRequest(int var0);

   public static final native String readNVString(int var0);

   public static final native boolean readNVBoolean(int var0);

   public static final void dataCallGoActive() {
      if (RadioInfo.getNetworkType() == 7) {
         setup(112, 0);
      }
   }

   public static final native void setDataServiceMode(int var0);

   public static final native int getDataServiceMode();

   public static final native int sendICMPTestData(int var0, int var1, int var2, int var3);

   public static final native int sendLLCTestData(int var0, int var1, int var2);

   public static final native int get3GPPSupportedRats();

   public static final int get3GPPEnabledRats() {
      return PersistentInteger.get(_enabled3GPPRatsKeyId);
   }

   public static final void set3GPPEnabledRats(int var0) {
      PersistentInteger.set(_enabled3GPPRatsKeyId, var0);
   }

   public static final int get3GPPActiveRats() {
      int var0 = getGANPreference();
      int var1 = get3GPPEnabledRats();
      switch (var0) {
         case 0:
            return var1 & -5;
         case 2:
            var1 &= 4;
         default:
            return var1;
      }
   }

   public static final int get3GPPRATPreference(int var0) {
      byte var1 = 0;
      int var2 = get3GPPEnabledRats();
      switch (var0) {
         case 1:
            if ((var2 & 4) != 0 && (var2 & -5) != 0) {
               return 1;
            }
            break;
         case 3:
            if ((var2 & 4) != 0 && (var2 & -5) != 0) {
               var1 = 2;
            }
      }

      return var1;
   }

   public static final void set3GPPRatConfig(int var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native boolean set3GPPRatConfiguration(int var0, int var1);

   public static final int getSupportedRadios() {
      return translateWAFsToRadios(RadioInfo.getSupportedWAFs());
   }

   public static final boolean setEnabledRadios(int var0) {
      return Radio.setEnabledWAFs(translateRadiosToWAFs(var0));
   }

   public static final int getEnabledRadios() {
      return translateWAFsToRadios(RadioInfo.getEnabledWAFs());
   }

   private static final int translateRadiosToWAFs(int var0) {
      byte var1 = 0;
      if ((var0 & 1) != 0) {
         var1 |= 1;
      }

      if ((var0 & 2) != 0) {
         var1 |= 2;
      }

      if ((var0 & 4) != 0) {
         var1 |= 4;
      }

      if ((var0 & 8) != 0) {
         var1 |= 8;
      }

      return var1;
   }

   private static final int translateWAFsToRadios(int var0) {
      byte var1 = 0;
      if ((var0 & 1) != 0) {
         var1 |= 1;
      }

      if ((var0 & 2) != 0) {
         var1 |= 2;
      }

      if ((var0 & 4) != 0) {
         var1 |= 4;
      }

      if ((var0 & 8) != 0) {
         var1 |= 8;
      }

      return var1;
   }

   public static final int getActiveRadios() {
      byte var0 = 0;
      int var1 = RadioInfo.getActiveWAFs();
      if ((var1 & 1) != 0 && (get3GPPActiveRats() & get3GPPSupportedRats() & -5) != 0) {
         var0 |= 1;
      }

      if ((var1 & 2) != 0) {
         var0 |= 2;
      }

      if ((var1 & 4) != 0) {
         var0 |= 4;
      }

      if ((var1 & 8) != 0) {
         var0 |= 8;
      }

      return var0;
   }

   public static final boolean reactivateRadios() {
      return activateRadios(_activeRadios);
   }

   public static final boolean activateRadios(int var0) {
      int var1 = translateRadiosToWAFs(var0);
      if (var0 == 0) {
         return true;
      }

      _activeRadios = var0;
      return Radio.activateWAFs(var1);
   }

   public static final native void activateWAFsInternal(int var0);

   public static final native void deactivateWAFsInternal(int var0);

   public static final void deactivateRadios(int var0) {
      Radio.deactivateWAFs(translateRadiosToWAFs(var0));
      _activeRadios &= ~var0;
   }

   public static final int sendPacket(RadioPacketHeader var0, byte[] var1) {
      return sendPacket(var0, var1, 0, var1.length);
   }

   public static final native int sendPacket(RadioPacketHeader var0, byte[] var1, int var2, int var3);

   public static final int registerAccessPointNumber(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int registerAccessPointNumber(String var0, int var1, int var2) {
      return registerAccessPointNumber(var0, var1, var2, null);
   }

   public static final int registerAccessPointNumber(String var0, int var1, int var2, QOSInfo var3) {
      return registerAccessPointNumber(var0, var1, var2, null, var3, null, null);
   }

   public static final int registerAccessPointNumber(String var0, int var1, int var2, QOSInfo var3, String var4, String var5) {
      return registerAccessPointNumber(var0, var1, var2, null, var3, var4, var5);
   }

   public static final native int registerAccessPointNumber(String var0, int var1, int var2, byte[] var3, QOSInfo var4, String var5, String var6);

   public static final native void deregisterAccessPointNumber(int var0);

   public static final byte[] getDNSIPAddress(int var0, int var1) {
      int var2 = getIPv4DNSAddress(var0, var1);
      return var2 != 0 ? UDPPacketHeader.IPv4IntToByteArray(var2) : null;
   }

   public static final native void getDefaultSMSParameters(SMSParameters var0);

   public static final native void setDefaultSMSParameters(SMSParameters var0);

   public static final native void scanForNetworks();

   public static final native void abortScanForNetworks();

   public static final native int getAvailableNetworkSelectionModes();

   public static final int getNetworkSelectionMode() {
      switch (RadioInfo.getNetworkType()) {
         case 4:
         case 7:
            return getNetworkSelectionModeOS();
         default:
            return PersistentInteger.get(_plmnSelectionModeID);
      }
   }

   public static final native int getAvailableNetworkModes();

   public static final void setNetworkSelectionMode(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native void setNetworkSelectionModeOS(int var0);

   private static final native int getNetworkSelectionModeOS();

   public static final native int getNetworkMode();

   public static final native void setNetworkMode(int var0);

   public static final int getGANPreference() {
      return PersistentInteger.get(_ganPreferenceKeyId);
   }

   public static final void setGANPreference(int var0) {
      int var1 = 0;
      switch (var0) {
         case -1:
            return;
         case 0:
         case 1:
         case 2:
         case 3:
         default:
            PersistentInteger.set(_ganPreferenceKeyId, var0);
            if (var0 != 2 && (getActiveRadios() & 1) != 0) {
               var1 |= get3GPPSupportedRats() & -5;
            }

            if (var0 != 0 && GAN.isGANAllowed() && (getActiveRadios() & 4) != 0) {
               var1 |= get3GPPSupportedRats() & 4;
            }

            if (get3GPPEnabledRats() != 0) {
               set3GPPEnabledRats(var1);
            }

            set3GPPRatConfig(get3GPPActiveRats(), get3GPPRATPreference(var0));
            if (get3GPPActiveRats() != 0) {
               if ((RadioInfo.getActiveWAFs() & 1) == 0) {
                  activateWAFsInternal(1);
                  return;
               }
            } else if ((RadioInfo.getActiveWAFs() & 1) != 0) {
               deactivateWAFsInternal(1);
            }
      }
   }

   public static final native void setFastDormancy(int var0, boolean var1);

   private static final native int getIPv4DNSAddress(int var0, int var1);

   public static final native void setQos(int var0, QOSInfo var1);

   public static final native void getQos(int var0, QOSInfo var1);

   public static final native int registerPort(int var0, int var1, int var2, int var3);

   private static final native int getNetworkParameter(int var0, int var1, int var2, int var3, byte[] var4, byte[] var5);

   public static final byte[] getNetworkParameter(int var0, int var1, int var2) {
      if (var2 >= 0 && var0 >= 0) {
         byte[] var3 = new byte[1];
         int var4 = getNetworkParameter(-1, var0, var1, var2, null, var3) - 1;
         if (var4 >= var2 && var2 >= 0 && var3[0] > 0) {
            Array.resize(var3, var3[0] & 255);
            Arrays.zero(var3);
            getNetworkParameter(-1, var0, var1, var2, var3, null);
            return var3;
         }
      }

      return null;
   }

   public static final int getNetworkParameterListSize(int var0, int var1) {
      return var0 >= 0 ? getNetworkParameter(-1, var0, var1, 0, null, null) : 0;
   }

   public static final native void reportNetworkDisplayName(String var0, int var1);

   public static final native boolean getServingNetworkNameString(Object var0);

   public static final native int getMaxSMSPacketSize();

   public static final native int getNetworkCategory(int var0);

   public static final native int getNetworkLAC(int var0);

   public static final void changeNetworks(int var0) {
      int var1 = RadioInfo.getNetworkId(var0);
      changeNetworksOS(var0);
      setManuallySelectedNetworkID(var1);
   }

   public static final native void changeNetworksOS(int var0);

   public static final boolean isUMTSCapable() {
      return RadioInfo.areWAFsSupported(1) && (get3GPPSupportedRats() & 2) != 0;
   }

   public static final boolean isDTMCapable() {
      if (RadioInfo.areWAFsSupported(1)) {
         switch (InternalServices.getHardwareID()) {
            case -2080372477:
            case -2080372473:
            case -2080371965:
            case -2080371961:
            case -2080371453:
            case 67112452:
               return false;
            default:
               return true;
         }
      } else {
         return false;
      }
   }

   public static final int getPrimaryWAF() {
      int var1 = RadioInfo.getSupportedWAFs();
      byte var0;
      if ((var1 & 2) != 0) {
         var0 = 2;
      } else if ((var1 & 1) != 0) {
         var0 = 1;
      } else if ((var1 & 8) != 0) {
         var0 = 8;
      } else {
         var0 = 4;
      }

      byte[] var2 = Branding.getData(14336);
      if (var2 != null && var2.length > 0) {
         int var3 = var2[0] & 255;
         if (var3 == 2) {
            return 2;
         }

         if (var3 == 1) {
            return 1;
         }

         if (var3 == 4) {
            return 8;
         }

         if (var3 == 3) {
            var0 = 4;
         }
      }

      return var0;
   }

   public static final native void enableDTM(boolean var0);

   public static final native boolean isDTMEnabled();

   public static final native void setAGPSOptions(boolean var0, boolean var1);

   public static final native boolean isSUPLEnabled();

   public static final boolean isSIMCardPresent() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void setManuallySelectedNetworkID(int var0) {
      if (getNetworkSelectionMode() == 3) {
         PersistentInteger.set(_manuallySelectedNetworkKeyId, var0);
      }
   }

   public static final int getManuallySelectedNetworkID() {
      int var0 = -1;
      if (getNetworkSelectionMode() == 3) {
         var0 = PersistentInteger.get(_manuallySelectedNetworkKeyId);
      }

      return var0;
   }

   public static final void assertWAFAccessPermission(int var0) {
      if (!ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(2), 51)) {
         ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
         if ((var0 & 4) != 0) {
            ApplicationControl.assertWiFiPermitted(true, CommonResource.getBundle(), 10165);
         }
      }
   }
}
