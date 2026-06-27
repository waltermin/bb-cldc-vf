package net.rim.device.api.itpolicy;

import net.rim.device.api.system.ControlledAccess;
import net.rim.device.internal.system.ITPolicyInternal;
import net.rim.device.internal.system.InternalServices;
import net.rim.vm.TraceBack;

public final class ITPolicy {
   public static final long GUID_ITADMIN_DEBUG_MODE;
   public static final long GUID_IT_POLICY_CHANGED;
   public static final long GUID_IT_POLICY_CHANGED_LOCKED_HANDHELD;
   public static final long GUID_OWNER_INFO_CHANGED;
   public static final long GUID_P2P_CHANGED;
   public static final long DURESS_NOTIFICATION;
   public static final long OTA_FLUSH_NOTIFICATION;
   public static final long GUID_WIPEABLE_IT_POLICY_CHANGED;
   public static final long GUID_PASSWORD_SET_BY_ITADMIN;
   public static final int ENABLE_PHONE;
   public static final int ENABLE_BROWSER;
   public static final int SERVICE_BOOK_POLICY_1;
   public static final int SERVICE_BOOK_POLICY;
   public static final int ITPOLICY_NAME;
   public static final int PASSWORD_REQUIRED;
   public static final int ALLOW_PEER_TO_PEER;
   public static final int PASSWORD_MIN_LENGTH;
   public static final int PASSWORD_MAX_TIMEOUT;
   public static final int PASSWORD_MAX_AGE;
   public static final int ALLOW_USER_CHANGE_PASSWORD_TIMEOUT;
   public static final int PASSWORD_PATTERN_CHECKS;
   public static final int ENABLE_LONG_TERM_TIMEOUT;
   public static final int ENABLE_SMS;
   public static final int ALLOW_BCC_RECIPIENTS;
   public static final int HOMEPAGE_ADDRESS;
   public static final int HOMEPAGE_ADDRESS_READONLY;
   public static final int ENABLE_WAP_CONFIG;
   public static final int DEFAULT_BROWSER_CONFIG_UID;
   public static final int COMMON_POLICY;
   public static final int PASSWORD_POLICY;
   public static final int CMIME_APP_POLICY;
   public static final int SECURITY_POLICY;
   public static final int SMIME_APP_POLICY;
   public static final int PGP_APP_POLICY;
   public static final int MEMORY_CLEANER_APP_POLICY;
   public static final int TLS_POLICY;
   public static final int WTLS_POLICY;
   public static final int BROWSER_POLICY;
   public static final int STK_POLICY;
   public static final int TCP_POLICY;
   public static final int SYNC_POLICY;
   public static final int BLUETOOTH_POLICY;
   public static final int VOIP_POLICY;
   public static final int SMART_DIALING_POLICY;
   public static final int VPN_POLICY;
   public static final int WLAN_POLICY;
   public static final int HELP_POLICY;
   public static final int BLUETOOTH_SMART_CARD_READER_POLICY;
   public static final int MDS_POLICY;
   public static final int SECURE_EMAIL_POLICY;
   public static final int CAMERA_POLICY;
   public static final int AUTO_SIGNATURE;
   public static final int ENTERPRISE_VOICE_CLIENT_POLICY;
   public static final int FIREWALL_POLICY;
   public static final int OTA_UPGRADE_POLICY;
   public static final int CONTENT_PROTECTION_PUBLIC_KEY;
   public static final int WLAN_AGGREGATED_PROFILES_POLICY;
   public static final int ITPOLICY_VERIFICATION_KEYS;
   public static final int ITPOLICY_SIGNATURES;
   public static final int ITPOLICY_VERIFICATION_KEY;
   public static final int ITPOLICY_SIGNATURE;
   public static final int RESERVED_POLICY;
   public static final int USER_POLICY;
   public static final boolean ENABLE_PHONE_DEFAULT;
   public static final boolean ENABLE_BROWSER_DEFAULT;
   public static final boolean HOMEPAGE_ADDRESS_READONLY_DEFAULT;
   public static final boolean ENABLE_WAP_CONFIG_DEFAULT;
   public static final boolean ALLOW_PEER_TO_PEER_DEFAULT;
   public static final boolean ENABLE_LONG_TERM_TIMEOUT_DEFAULT;
   public static final boolean ENABLE_SMS_DEFAULT;
   public static final boolean ALLOW_BCC_RECIPIENTS_DEFAULT;
   public static final boolean ALLOW_USER_CHANGE_PASSWORD_TIMEOUT_DEFAULT;
   public static final int PASSWORD_MAX_AGE_DEFAULT;
   public static final int PASSWORD_MAX_TIMEOUT_DEFAULT;
   public static final int PASSWORD_MIN_LENGTH_DEFAULT;
   public static final int PASSWORD_PATTERN_CHECKS_DEFAULT;
   public static final boolean PASSWORD_REQUIRED_DEFAULT;
   public static final int TERNARY_TRUE;
   public static final int TERNARY_FALSE;
   public static final int TERNARY_PROMPT;
   public static final int PUBLIC_KEY_TAG;
   public static final int SIGNATURE_KEY_TAG;

   private ITPolicy() {
   }

   public static final String getString(String var0) {
      return readString(255, var0);
   }

   public static final byte[] getByteArray(String var0) {
      return readByteArray(255, var0);
   }

   public static final byte getByte(String var0) {
      return readByte(255, var0, (byte)0);
   }

   public static final boolean getBoolean(String var0, boolean var1) {
      return readByte(255, var0, (byte)(var1 ? 1 : 0)) != 0;
   }

   public static final int getInteger(String var0, int var1) {
      return readInt(255, var0, var1);
   }

   public static final String getString(int var0) {
      return readString(var0, null);
   }

   public static final String getString(int var0, int var1) {
      String var2 = null;
      if (WipeITPolicyDirectory.isWipeableId(var0, var1)) {
         if (!ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(0), 51)) {
            return null;
         }

         var2 = readStringWipeable(var0, var1);
      }

      return var2 == null ? readString(var0, var1) : var2;
   }

   public static final byte[] getByteArray(int var0) {
      return readByteArray(var0, null);
   }

   public static final byte[] getByteArray(int var0, int var1) {
      byte[] var2 = null;
      if (WipeITPolicyDirectory.isWipeableId(var0, var1)) {
         if (!ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(0), 51)) {
            return null;
         }

         var2 = readByteArrayWipeable(var0, var1);
      }

      return var2 == null ? readByteArray(var0, var1) : var2;
   }

   public static final boolean getBoolean(int var0, boolean var1) {
      if (var0 != 6 || !getBoolean(24, 2, false) && !getBoolean(24, 63, false) && !getBoolean(24, 1, false)) {
         return var0 == 7 && !InternalServices.isPINMessagingSupported() ? false : readByte(var0, null, (byte)(var1 ? 1 : 0)) != 0;
      } else {
         return true;
      }
   }

   public static final boolean getBoolean(int var0, int var1, boolean var2) {
      if (var0 != 24 || var1 != 2 || !getBoolean(24, 63, false) && !getBoolean(24, 1, false)) {
         Byte var3 = null;
         if (WipeITPolicyDirectory.isWipeableId(var0, var1)) {
            if (!ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(0), 51)) {
               return var2;
            }

            var3 = readByteWipeable(var0, var1);
         }

         return var3 == null ? readByte(var0, var1, (byte)(var2 ? 1 : 0)) != 0 : var3 != 0;
      } else {
         return true;
      }
   }

   public static final int getInteger(int var0, int var1) {
      return readInt(var0, null, var1);
   }

   public static final int getInteger(int var0, int var1, int var2) {
      Integer var3 = null;
      if (WipeITPolicyDirectory.isWipeableId(var0, var1)) {
         if (!ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(0), 51)) {
            return var2;
         }

         var3 = readIntegerWipeable(var0, var1);
      }

      return var3 == null ? readInt(var0, var1, var2) : var3;
   }

   public static final byte getByte(int var0, byte var1) {
      return readByte(var0, null, var1);
   }

   public static final byte getByte(int var0, int var1, byte var2) {
      Byte var3 = null;
      if (WipeITPolicyDirectory.isWipeableId(var0, var1)) {
         if (!ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(0), 51)) {
            return var2;
         }

         var3 = readByteWipeable(var0, var1);
      }

      return var3 == null ? readByte(var0, var1, var2) : var3;
   }

   private static final native byte readByte(int var0, String var1, byte var2);

   private static final native byte readByte(int var0, int var1, byte var2);

   private static final native int readInt(int var0, String var1, int var2);

   private static final native int readInt(int var0, int var1, int var2);

   private static final native String readString(int var0, String var1);

   private static final native String readString(int var0, int var1);

   private static final native byte[] readByteArray(int var0, String var1);

   private static final native byte[] readByteArray(int var0, int var1);

   private static final Byte readByteWipeable(int var0, int var1) {
      return ITPolicyInternal.readByteInternal(readWipeablePolicyData(), var0, var1);
   }

   private static final String readStringWipeable(int var0, int var1) {
      return ITPolicyInternal.readStringInternal(readWipeablePolicyData(), var0, var1);
   }

   private static final Integer readIntegerWipeable(int var0, int var1) {
      return ITPolicyInternal.readIntegerInternal(readWipeablePolicyData(), var0, var1);
   }

   private static final byte[] readByteArrayWipeable(int var0, int var1) {
      return ITPolicyInternal.readByteArrayInternal(readWipeablePolicyData(), var0, var1);
   }

   private static final byte[] readWipeablePolicyData() {
      return ITPolicyInternal.readWipeablePolicyData();
   }
}
