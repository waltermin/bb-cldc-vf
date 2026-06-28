package net.rim.device.internal.crypto;

import java.io.DataInput;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.Arrays;
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

   private static final void scanAndScheduleToRemoveExpiredKeys(int context) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String getKeyIDForUID(String uid) {
      if (uid == null) {
         throw new Object();
      }

      CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysByName.get(uid.toLowerCase());
      return key == null ? null : key._id;
   }

   public static final byte getKeyAlgorithmForUID(String uid) {
      if (uid == null) {
         throw new Object();
      }

      CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysByName.get(uid.toLowerCase());
      return key == null ? 0 : key._algorithm;
   }

   public static final String getUIDForKeyId(String keyId) {
      if (keyId == null) {
         throw new Object();
      }

      CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysById.get(keyId);
      return key == null ? null : key._name;
   }

   public static final boolean moveKey(String oldUid, String newUid) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean isEnterpriseClassKey(String keyId, String uid) {
      CryptoBlock$CryptoBlockKey keyByID = (CryptoBlock$CryptoBlockKey)(_keysById != null ? _keysById.get(keyId) : null);
      if (keyByID != null) {
         return keyByID._enterpriseClassKey;
      }

      CryptoBlock$CryptoBlockKey keyByUid = (CryptoBlock$CryptoBlockKey)(uid != null ? _keysByName.get(uid.toLowerCase()) : null);
      return keyByUid != null ? keyByUid._enterpriseClassKey : false;
   }

   public static final boolean isGlobalPeerToPeerKey(String keyID) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean isCorporatePeerToPeerKey(String keyID) {
      if (keyID == null) {
         throw new Object();
      }

      CryptoBlock$CryptoBlockKey keyByID = (CryptoBlock$CryptoBlockKey)_keysById.get(keyID);
      return keyByID != null
         && (
            StringUtilities.strEqualIgnoreCase(keyByID._name, CURRENT_BES_SCRAMBLE_KEY, 1701707776)
               || StringUtilities.strEqualIgnoreCase(keyByID._name, PREVIOUS_BES_SCRAMBLE_KEY, 1701707776)
         );
   }

   public static final boolean isPeerToPeerKey(String keyID) {
      return isGlobalPeerToPeerKey(keyID) || isCorporatePeerToPeerKey(keyID);
   }

   public static final byte[] getSymmetricKey(String uid) {
      synchronized (_persistentKeysById) {
         if (uid == null) {
            throw new Object();
         }

         CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysByName.get(uid.toLowerCase());
         return key == null ? null : key.getKey();
      }
   }

   public static final boolean validateSenderByUid(String keyId, String uid) {
      synchronized (_persistentKeysById) {
         boolean result = false;
         if (uid == null) {
            throw new Object();
         }

         CryptoBlock$CryptoBlockKey key1 = (CryptoBlock$CryptoBlockKey)_keysById.get(keyId);
         CryptoBlock$CryptoBlockKey key2 = (CryptoBlock$CryptoBlockKey)_keysByName.get(uid.toLowerCase());
         if (key1 != null && key2 != null) {
            result = Arrays.equals(key1.getKey(), key2.getKey());
            result &= key1._enterpriseClassKey == key2._enterpriseClassKey;
         }

         return result;
      }
   }

   public static final void addNonEnterpriseClassSymmetricKey(String uid, DataInput input) {
      addSymmetricKey(uid, input, -1, false);
   }

   public static final void addSymmetricKey(String uid, DataInput input) {
      addSymmetricKey(uid, input, -1);
   }

   public static final void addSymmetricKey(String uid, DataInput input, long keyExpiryDate) {
      addSymmetricKey(uid, input, keyExpiryDate, true);
   }

   public static final void addSymmetricKeyAsSecondaryKey(String uid, DataInput input) {
      addSymmetricKeyAsSecondaryKey(uid, input, -1);
   }

   public static final void addSymmetricKeyAsSecondaryKey(String uid, DataInput input, long keyExpiryDate) {
      addSymmetricKey(uid, input, keyExpiryDate, true, true);
   }

   public static final void addSymmetricKey(String uid, DataInput input, long keyExpiryDate, boolean enterpriseClassKey) {
      addSymmetricKey(uid, input, keyExpiryDate, enterpriseClassKey, false);
   }

   public static final void addSymmetricKey(String uid, DataInput input, long keyExpiryDate, boolean enterpriseClassKey, boolean secondaryOnly) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean revertSymmetricKey(String keyid) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean removeSymmetricKey(String uid, byte flags) {
      synchronized (_persistentKeysById) {
         if (uid == null) {
            throw new Object();
         }

         uid = uid.toLowerCase();
         CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysByName.get(uid);
         return deleteSymmetricKey(key, flags);
      }
   }

   public static final boolean removeSymmetricKeyByKeyID(String keyid, byte flags) {
      synchronized (_persistentKeysById) {
         if (keyid == null) {
            throw new Object();
         }

         CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysById.get(keyid);
         return deleteSymmetricKey(key, flags);
      }
   }

   private static final boolean deleteSymmetricKey(CryptoBlock$CryptoBlockKey key, byte flags) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean isKeyPresent(String name) {
      synchronized (_persistentKeysById) {
         return _keysByName.containsKey(name.toLowerCase());
      }
   }

   public static final int getKeyLength(byte keyAlgorithm) {
      switch (keyAlgorithm) {
         case 0:
            throw new CryptoBlockException();
         case 1:
         default:
            return 16;
         case 2:
            return 32;
      }
   }

   private static final byte[] createSessionKey(byte encryptionScheme) {
      byte keyAlgorithm = encryptionSchemeToKeyAlgorithm(encryptionScheme);
      int keyLength = getKeyLength(keyAlgorithm);
      byte[] keyData = RandomSource.getBytes(keyLength);
      if (encryptionScheme == 0) {
         for (int i = 0; i < keyLength; i++) {
            keyData[i] = (byte)(keyData[i] & 127);
         }
      }

      return keyData;
   }

   private static final boolean checkSessionKey(byte[] keyData, byte encryptionScheme) {
      if (keyData == null) {
         return false;
      }

      byte keyAlgorithm = encryptionSchemeToKeyAlgorithm(encryptionScheme);
      int keyLength = getKeyLength(keyAlgorithm);
      if (keyData.length != keyLength) {
         return false;
      }

      if (encryptionScheme == 0) {
         byte b = 0;

         for (int i = 0; i < keyLength; i++) {
            b |= keyData[i];
         }

         if ((b & 128) != 0) {
            return false;
         }
      }

      return true;
   }

   private static final String readKeyID(DataInput input) {
      byte[] buffer = new byte[32];

      try {
         for (int i = 0; i < 32; i++) {
            int b = input.readUnsignedByte();
            if (b == 0) {
               return (String)(new Object(buffer, 0, i));
            }

            buffer[i] = (byte)b;
         }
      } catch (IOException var4) {
      }

      throw new CryptoBlockException();
   }

   private static final byte encryptionSchemeToKeyAlgorithm(byte encryptionScheme) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final byte keyAlgorithmToEncryptionScheme(byte keyAlgorithm) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final int checkRedundancy(byte[] data, byte encryptionScheme) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final void addRedundancy(DataBuffer data, byte encryptionScheme) {
      if (encryptionScheme != 0) {
         int crc32 = CRC32.update(-1, data.getArray(), data.getArrayPosition(), data.available());
         int currentPosition = data.getPosition();
         data.setPosition(data.getLength());
         data.writeInt(crc32);
         data.setPosition(currentPosition);
      }
   }

   private static final void removeRedundancy(DataBuffer data, byte encryptionScheme) {
      if (encryptionScheme != 0) {
         data.setLength(data.getLength() - 4);
      }
   }

   public static final boolean encode(Vector uids, DataBuffer input, DataBuffer output, boolean doCompress, boolean doEncrypt) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String decode(DataBuffer input, DataBuffer output) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: array init");
   }

   private static final void registerKeyCollections() {
      OTAUpgrade.addOTASLOnlyCollection(new CryptoBlock$CBCollection(_persistentKeysByName));
      OTAUpgrade.addOTASLOnlyCollection(new CryptoBlock$CBCollection(_persistentKeysById));
   }
}
