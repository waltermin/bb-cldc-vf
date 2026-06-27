package net.rim.device.internal.crypto;

import java.io.DataInput;
import java.util.Hashtable;
import java.util.Vector;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.CRC32;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.StringUtilities;
import net.rim.vm.OTAUpgrade;

public final class CryptoBlock {
   private static final int SASTREK_CONTEXT_TIMER_TASK;
   private static final int SASTREK_CONTEXT_STARTUP;
   private static final int SASTREK_ADD_SYMMETRIC;
   private static final int SASTREK_DELETE_SYMMETRIC;
   public static final byte TRANSPORT_ENCRYPTION_ALGORITHM_NONE;
   public static final byte TRANSPORT_ENCRYPTION_ALGORITHM_3DES;
   public static final byte TRANSPORT_ENCRYPTION_ALGORITHM_AES256;
   public static String CURRENT_BES_SCRAMBLE_KEY;
   public static String PREVIOUS_BES_SCRAMBLE_KEY;
   public static final byte[] TRANSPORT_ENCRYPTION_ALGORITHM_BITFIELD;
   private static final byte ENCRYPTION_SCHEME_3DES_OR_NONE;
   private static final byte ENCRYPTION_SCHEME_AES256;
   private static final int PADDING_NONE;
   private static final int PADDING_ZERO;
   private static final int PADDING_PKCS5;
   private static final int KEY_LENGTH;
   private static final int VERSION;
   private static final byte COMPRESS_FLAG;
   private static final byte ENCRYPT_FLAG;
   private static final byte NO_CE_FLAG;
   private static final int ONE_WEEK;
   private static final long NEVER_EXPIRE;
   private static final long GME_CBKBN;
   private static final long GME_CBKBI;
   private static PersistentObject _persistentKeysByName;
   private static Hashtable _keysByName;
   private static PersistentObject _persistentKeysById;
   private static Hashtable _keysById;
   public static final byte REMOVE_ONLY_UID;
   public static final byte REMOVE_ONLY_KEYID;
   public static final byte EXPIRE_KEY;
   private static CryptoBlock$ExpireKeysTimerTask _expireKeyTask;
   public static final long EVENT_LOGGER_GUID;
   public static final String EVENT_LOGGER_TITLE;
   private static final long ID_EXPIRE_TIME_TASK;
   private static final long ID_TEST_CRYPTOBLOCK;

   public static final boolean areMasterKeysEncrypted() {
      return CryptoBlock$CryptoBlockKey.areMasterKeysEncrypted();
   }

   public static final boolean areMasterKeysAvailable() {
      return CryptoBlock$CryptoBlockKey.areMasterKeysAvailable();
   }

   private static final native byte[] compress(byte[] var0, int var1, int var2);

   private static final native byte[] encrypt(byte[] var0, byte var1, byte[] var2, int var3, int var4, int var5);

   private static final native byte[] compressEncrypt(byte[] var0, byte var1, byte[] var2, int var3, int var4, int var5);

   private static final native byte[] decrypt(byte[] var0, byte var1, byte[] var2, int var3, int var4, int var5);

   private static final native byte[] decompress(byte[] var0, int var1, int var2);

   private static final native byte[] decryptDecompress(byte[] var0, byte var1, byte[] var2, int var3, int var4, int var5);

   private CryptoBlock() {
   }

   private static final void scanAndScheduleToRemoveExpiredKeys(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String getKeyIDForUID(String var0) {
      if (var0 == null) {
         throw new Object();
      }

      Object var1 = _keysByName.get(var0.toLowerCase());
      return var1 == null ? null : ((CryptoBlock$CryptoBlockKey)var1)._id;
   }

   public static final byte getKeyAlgorithmForUID(String var0) {
      if (var0 == null) {
         throw new Object();
      }

      Object var1 = _keysByName.get(var0.toLowerCase());
      return var1 == null ? 0 : ((CryptoBlock$CryptoBlockKey)var1)._algorithm;
   }

   public static final String getUIDForKeyId(String var0) {
      if (var0 == null) {
         throw new Object();
      }

      Object var1 = _keysById.get(var0);
      return var1 == null ? null : ((CryptoBlock$CryptoBlockKey)var1)._name;
   }

   public static final boolean moveKey(String var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isEnterpriseClassKey(String var0, String var1) {
      Object var2 = _keysById != null ? _keysById.get(var0) : null;
      if (var2 != null) {
         return ((CryptoBlock$CryptoBlockKey)var2)._enterpriseClassKey;
      }

      Object var3 = var1 != null ? _keysByName.get(var1.toLowerCase()) : null;
      return var3 != null ? ((CryptoBlock$CryptoBlockKey)var3)._enterpriseClassKey : false;
   }

   public static final boolean isGlobalPeerToPeerKey(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean isCorporatePeerToPeerKey(String var0) {
      if (var0 == null) {
         throw new Object();
      }

      Object var1 = _keysById.get(var0);
      return var1 != null
         && (
            StringUtilities.strEqualIgnoreCase(((CryptoBlock$CryptoBlockKey)var1)._name, CURRENT_BES_SCRAMBLE_KEY, 1701707776)
               || StringUtilities.strEqualIgnoreCase(((CryptoBlock$CryptoBlockKey)var1)._name, PREVIOUS_BES_SCRAMBLE_KEY, 1701707776)
         );
   }

   public static final boolean isPeerToPeerKey(String var0) {
      return isGlobalPeerToPeerKey(var0) || isCorporatePeerToPeerKey(var0);
   }

   public static final byte[] getSymmetricKey(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean validateSenderByUid(String var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void addNonEnterpriseClassSymmetricKey(String var0, DataInput var1) {
      addSymmetricKey(var0, var1, -1, false);
   }

   public static final void addSymmetricKey(String var0, DataInput var1) {
      addSymmetricKey(var0, var1, -1);
   }

   public static final void addSymmetricKey(String var0, DataInput var1, long var2) {
      addSymmetricKey(var0, var1, var2, true);
   }

   public static final void addSymmetricKeyAsSecondaryKey(String var0, DataInput var1) {
      addSymmetricKeyAsSecondaryKey(var0, var1, -1);
   }

   public static final void addSymmetricKeyAsSecondaryKey(String var0, DataInput var1, long var2) {
      addSymmetricKey(var0, var1, var2, true, true);
   }

   public static final void addSymmetricKey(String var0, DataInput var1, long var2, boolean var4) {
      addSymmetricKey(var0, var1, var2, var4, false);
   }

   public static final void addSymmetricKey(String var0, DataInput var1, long var2, boolean var4, boolean var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean revertSymmetricKey(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean removeSymmetricKey(String var0, byte var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean removeSymmetricKeyByKeyID(String var0, byte var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final boolean deleteSymmetricKey(CryptoBlock$CryptoBlockKey var0, byte var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isKeyPresent(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getKeyLength(byte var0) {
      switch (var0) {
         case 0:
            throw new CryptoBlockException();
         case 1:
         default:
            return 16;
         case 2:
            return 32;
      }
   }

   private static final byte[] createSessionKey(byte var0) {
      byte var1 = encryptionSchemeToKeyAlgorithm(var0);
      int var2 = getKeyLength(var1);
      byte[] var3 = RandomSource.getBytes(var2);
      if (var0 == 0) {
         for (int var4 = 0; var4 < var2; var4++) {
            var3[var4] = (byte)(var3[var4] & 127);
         }
      }

      return var3;
   }

   private static final boolean checkSessionKey(byte[] var0, byte var1) {
      if (var0 == null) {
         return false;
      }

      byte var2 = encryptionSchemeToKeyAlgorithm(var1);
      int var3 = getKeyLength(var2);
      if (var0.length != var3) {
         return false;
      }

      if (var1 == 0) {
         byte var4 = 0;

         for (int var5 = 0; var5 < var3; var5++) {
            var4 |= var0[var5];
         }

         if ((var4 & 128) != 0) {
            return false;
         }
      }

      return true;
   }

   private static final String readKeyID(DataInput var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final byte encryptionSchemeToKeyAlgorithm(byte var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final byte keyAlgorithmToEncryptionScheme(byte var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final int checkRedundancy(byte[] var0, byte var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final void addRedundancy(DataBuffer var0, byte var1) {
      if (var1 != 0) {
         int var2 = CRC32.update(-1, var0.getArray(), var0.getArrayPosition(), var0.available());
         int var3 = var0.getPosition();
         var0.setPosition(var0.getLength());
         var0.writeInt(var2);
         var0.setPosition(var3);
      }
   }

   private static final void removeRedundancy(DataBuffer var0, byte var1) {
      if (var1 != 0) {
         var0.setLength(var0.getLength() - 4);
      }
   }

   public static final boolean encode(Vector var0, DataBuffer var1, DataBuffer var2, boolean var3, boolean var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String decode(DataBuffer var0, DataBuffer var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void registerKeyCollections() {
      OTAUpgrade.addOTASLOnlyCollection(new CryptoBlock$CBCollection(_persistentKeysByName));
      OTAUpgrade.addOTASLOnlyCollection(new CryptoBlock$CBCollection(_persistentKeysById));
   }
}
