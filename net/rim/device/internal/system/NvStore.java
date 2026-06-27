package net.rim.device.internal.system;

import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentContentInternal;

public final class NvStore {
   public static final int FIELD_FLAGS;
   public static final int FLAG_DEVICE_UNDER_ATTACK;
   public static final int FLAG_DISALLOW_3RD_PARTY_APP_DOWNLOAD;
   public static final int FLAG_DISALLOW_PERSISTED_PLAINTEXT;
   public static final int FLAG_CONTENT_PROTECTION_ENABLED;
   public static final int FLAG_CONTENT_PROTECT_MASTER_KEYS;
   public static final int FLAG_DEVICE_HAS_BOOTED_BEFORE;
   public static final int FIELD_CODE_CRC;
   public static final int FIELD_TLS_SESSION_RESUMPTION;
   public static final int FIELD_ITPOLICY_DATA;
   public static final int FIELD_ITPOLICY_STATUS;
   public static final int FIELD_ITADMIN_DATA;
   public static final int FIELD_ITADMIN_TOOL;
   public static final int FIELD_ITADMIN_STATUS;
   public static final int FIELD_PIN2PIN_KEY;
   public static final int FIELD_PASSWORD_MAX_ATTEMPTS;
   public static final int FIELD_PASSWORD_HISTORY;
   public static final int FIELD_CONTENT_PROTECTION_KEYS;
   public static final int FIELD_APP_DOWNLOAD_CONTROL_SIGNER_ID;
   public static final int FIELD_APP_DOWNLOAD_CONTROL_PUBLIC_KEY;
   public static final int FIELD_APP_DOWNLOAD_CONTROL_DESCRIPTION;
   public static final int FIELD_APP_USER_AUTHENTICATOR_CLASS_NAME;
   public static final int FIELD_APP_USER_AUTHENTICATOR_DATA;
   public static final int FIELD_DRM_DEVICE_SECRET;
   public static final int FIELD_LONG_TERM_PUBLIC_KEY;
   public static final int FIELD_LONG_TERM_PRIVATE_KEY;
   public static final int FIELD_SERVER_LONG_TERM_PUBLIC_KEY_STORE;
   public static final int FIELD_DYNAMIC_PIN;
   public static final int FIELD_MIDLET_SIGNING_PUBLIC_KEY;
   public static final int FIELD_MIDLET_SIGNING_PRIVATE_KEY;
   public static final int FIELD_MIDLET_RIM_DOMAIN;
   public static final int FIELD_MIDLET_CARRIER_DOMAIN1;
   public static final int FIELD_MIDLET_CARRIER_DOMAIN2;
   public static final int FIELD_MIDLET_CARRIER_DOMAIN3;
   public static final int FIELD_MIDLET_CARRIER_DOMAIN4;
   public static final int FIELD_MIDLET_CARRIER_DOMAIN5;
   public static final int FIELD_OTAKEYGEN_DEVICE_WIPED;
   public static final int FIELD_REG_RESPONSE_KEY;
   public static final int FIELD_MIDLET_SIMCARD_DOMAIN;
   public static final int FIELD_RANDOM_SEED;
   public static final int FIELD_ICS_PROV_PROXY_INFO;
   public static final int FIELD_SECURE_PIN_IP_KEY;
   public static final int FIELD_USAGE_VOICE;
   public static final int FIELD_USAGE_DATA;
   public static final int FIELD_PASSWORD_ENABLED_TIMESTAMP;
   public static final int FIELD_MDS_CLIENT_ADMIN_POLICY;
   public static final int FIELD_KEYNEGO_DEVICE_AUTHENTICATION_KEY;
   public static final int FIELD_FILESYSTEM_DEVICE_SECRET;
   public static final int FIELD_MDS_RESET_KEYS;
   public static final int FIELD_MDS_RECOVERY_RESET_KEYS;
   public static final int FIELD_ITPOLICY_DATA_WIPEABLE;
   public static final int FIELD_WLAN_KEY;
   public static final int FIELD_ERASE_COUNT;
   private static final long ID;

   private NvStore() {
   }

   private static final native byte[] readDataInternal(int var0);

   private static final native boolean writeDataInternal(int var0, byte[] var1, int var2, int var3);

   private static final boolean isContentProtected(int var0) {
      switch (var0) {
         case 17:
         case 21:
         case 22:
            return false;
         case 18:
         case 19:
         case 20:
         case 23:
         case 24:
         default:
            return true;
      }
   }

   private static final boolean isRAMOnly(int var0) {
      switch (var0) {
         case 19:
         case 24:
         case 41:
            return true;
         default:
            return false;
      }
   }

   public static final byte[] readData(int var0) {
      byte[] var1 = readDataInternal(var0);
      if (isContentProtected(var0)) {
         Object var2 = PersistentContent.waitForTicket();
         var2.hashCode();
         Object var3 = PersistentContent.convertByteArrayToEncoding(var1);
         var1 = PersistentContentInternal.decodeByteArray(var3, false, isRAMOnly(var0));
      }

      return var1;
   }

   public static final boolean writeData(int var0, byte[] var1) {
      return writeData(var0, var1, 0, var1.length);
   }

   public static final boolean writeData(int var0, byte[] var1, int var2, int var3) {
      if (isContentProtected(var0)) {
         Object var4 = PersistentContent.encode(var1, var2, var3, false, true);
         var1 = PersistentContent.convertEncodingToByteArray(var4);
         var2 = 0;
         var3 = var1.length;
      }

      return writeDataInternal(var0, var1, var2, var3);
   }

   public static final native int readInt(int var0, int var1);

   public static final native boolean writeInt(int var0, int var1);

   public static final boolean getFlag(int var0) {
      return (readInt(1, 0) & var0) != 0;
   }

   public static final boolean setFlag(int var0, boolean var1) {
      int var2 = readInt(1, 0);
      if ((var2 & var0) != 0 == var1) {
         return true;
      }

      if (var1) {
         var2 |= var0;
      } else {
         var2 &= ~var0;
      }

      return writeInt(1, var2);
   }

   public static final native boolean deleteData(int var0);

   public static final void persistentContentModeChanged() {
      reCryptField(18);
      reCryptField(19);
      reCryptField(20);
      reCryptField(23);
      reCryptField(24);
   }

   private static final void reCryptField(int var0) {
      byte[] var1 = readDataInternal(var0);
      Object var2 = PersistentContent.convertByteArrayToEncoding(var1);
      if (!PersistentContent.checkEncoding(var2)) {
         var2 = PersistentContent.reEncode(var2);
         var1 = PersistentContent.convertEncodingToByteArray(var2);
         writeDataInternal(var0, var1, 0, var1.length);
      }
   }

   public static final native void resetToFactoryDefaults();
}
