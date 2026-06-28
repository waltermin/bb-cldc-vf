package net.rim.device.internal.crypto;

import java.io.DataInput;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.CRC32;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.internal.proxy.Proxy;
import net.rim.device.internal.system.ITPolicyInternal;
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
      synchronized (_persistentKeysById) {
         long currentTimeMillis = System.currentTimeMillis();
         long currentTimeMillisPlusOneMinute = currentTimeMillis + 60000;
         long earliestTime = Long.MAX_VALUE;
         Enumeration enumeration = _keysById.elements();

         while (enumeration.hasMoreElements()) {
            CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)enumeration.nextElement();
            if (key._expireTime == -1 && !key.equals(_keysByName.get(key._name))) {
               key._expireTime = currentTimeMillis + 604800000;
               key.logAction("name orphan expire (id) " + context);
            }

            if (key._expireTime != -1) {
               if (key._expireTime < currentTimeMillisPlusOneMinute) {
                  _keysById.remove(key._id);
                  key.logAction("expire remove (id) " + context);
               } else if (key._expireTime < earliestTime) {
                  earliestTime = key._expireTime;
               }
            }
         }

         _persistentKeysById.commit();

         try {
            if (_expireKeyTask._expireKeyTaskID != -1) {
               Proxy.getInstance().cancelInvokeLater(_expireKeyTask._expireKeyTaskID);
            }

            _expireKeyTask._expireKeyTaskID = -1;
            if (earliestTime != Long.MAX_VALUE) {
               long time = earliestTime - System.currentTimeMillis();
               if (time > 104400000) {
                  time = 104400000;
               }

               _expireKeyTask._expireKeyTaskID = Proxy.getInstance().invokeLater(_expireKeyTask, time, false);
            }
         } catch (Throwable var12) {
         }
      }
   }

   public static final String getKeyIDForUID(String uid) {
      if (uid == null) {
         throw new IllegalArgumentException();
      }

      CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysByName.get(uid.toLowerCase());
      return key == null ? null : key._id;
   }

   public static final byte getKeyAlgorithmForUID(String uid) {
      if (uid == null) {
         throw new IllegalArgumentException();
      }

      CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysByName.get(uid.toLowerCase());
      return key == null ? 0 : key._algorithm;
   }

   public static final String getUIDForKeyId(String keyId) {
      if (keyId == null) {
         throw new IllegalArgumentException();
      }

      CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysById.get(keyId);
      return key == null ? null : key._name;
   }

   public static final boolean moveKey(String oldUid, String newUid) {
      synchronized (_persistentKeysById) {
         if (oldUid != null && newUid != null) {
            oldUid = oldUid.toLowerCase();
            newUid = newUid.toLowerCase();
            CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysByName.get(oldUid);
            if (key != null) {
               key._name = newUid;
               _keysByName.remove(oldUid);
               _keysByName.put(newUid, key);
               key.logAction("move (name)");
               _persistentKeysById.commit();
               _persistentKeysByName.commit();
               return true;
            } else {
               return false;
            }
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   public static final boolean isEnterpriseClassKey(String keyId, String uid) {
      CryptoBlock$CryptoBlockKey keyByID = _keysById != null ? (CryptoBlock$CryptoBlockKey)_keysById.get(keyId) : null;
      if (keyByID != null) {
         return keyByID._enterpriseClassKey;
      }

      CryptoBlock$CryptoBlockKey keyByUid = uid != null ? (CryptoBlock$CryptoBlockKey)_keysByName.get(uid.toLowerCase()) : null;
      return keyByUid != null ? keyByUid._enterpriseClassKey : false;
   }

   public static final boolean isGlobalPeerToPeerKey(String keyID) {
      if (keyID == null) {
         throw new IllegalArgumentException();
      } else {
         return StringUtilities.strEqualIgnoreCase(keyID, "0+s+0", 1701707776);
      }
   }

   public static final boolean isCorporatePeerToPeerKey(String keyID) {
      if (keyID == null) {
         throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
         }

         CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysByName.get(uid.toLowerCase());
         return key == null ? null : key.getKey();
      }
   }

   public static final boolean validateSenderByUid(String keyId, String uid) {
      synchronized (_persistentKeysById) {
         boolean result = false;
         if (uid == null) {
            throw new IllegalArgumentException();
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
      synchronized (_persistentKeysById) {
         if (uid != null && input != null) {
            uid = uid.toLowerCase();
            if (!uid.equals("key: 0+s+0")) {
               CryptoBlock$CryptoBlockKey key = new CryptoBlock$CryptoBlockKey(input, uid, keyExpiryDate, enterpriseClassKey);
               CryptoBlock$CryptoBlockKey oldKeyByKeyId = (CryptoBlock$CryptoBlockKey)_keysById.get(key._id);
               CryptoBlock$CryptoBlockKey oldKeyByName = (CryptoBlock$CryptoBlockKey)_keysByName.get(key._name);
               if (oldKeyByKeyId != null && enterpriseClassKey && !oldKeyByKeyId._enterpriseClassKey) {
                  throw new CryptoBlockException("Security Violation");
               }

               if (oldKeyByName != null && enterpriseClassKey && !oldKeyByName._enterpriseClassKey) {
                  throw new CryptoBlockException("Security Violation");
               }

               key.logAction("add symmetric (id)");
               _keysById.put(key._id, key);
               if (!secondaryOnly) {
                  key.logAction("add symmetric (name)");
                  CryptoBlock$CryptoBlockKey oldKey = (CryptoBlock$CryptoBlockKey)_keysByName.put(key._name, key);
                  if (oldKey != null && oldKey.equals(_keysById.get(oldKey._id))) {
                     oldKey._expireTime = System.currentTimeMillis() + 604800000;
                     oldKey.logAction("old expire (name)");
                  }
               }

               scanAndScheduleToRemoveExpiredKeys(3);
               _persistentKeysByName.commit();
               _persistentKeysById.commit();
            }
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   public static final boolean revertSymmetricKey(String keyid) {
      if (keyid == null) {
         throw new IllegalArgumentException();
      }

      synchronized (_persistentKeysById) {
         CryptoBlock$CryptoBlockKey oldKey = (CryptoBlock$CryptoBlockKey)_keysById.get(keyid);
         if (oldKey != null && oldKey._name != null) {
            oldKey._expireTime = -1;
            CryptoBlock$CryptoBlockKey newKey = (CryptoBlock$CryptoBlockKey)_keysByName.get(oldKey._name);
            if (!oldKey.equals(newKey)) {
               String newKeyId = null;
               if (newKey != null) {
                  newKeyId = newKey._id;
               }

               oldKey.logAction("revert symmetric restore (name)");
               _keysByName.put(oldKey._name, oldKey);
               if (newKeyId != null && !newKeyId.equalsIgnoreCase(keyid)) {
                  newKey.logAction("revert symmetric remove (id)");
                  _keysById.remove(newKeyId);
               }
            } else if (newKey == null) {
               oldKey.logAction("revert symmetric add (name)");
               _keysByName.put(oldKey._name, oldKey);
            }

            _persistentKeysByName.commit();
            _persistentKeysById.commit();
         }

         return true;
      }
   }

   public static final boolean removeSymmetricKey(String uid, byte flags) {
      synchronized (_persistentKeysById) {
         if (uid == null) {
            throw new IllegalArgumentException();
         }

         uid = uid.toLowerCase();
         CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysByName.get(uid);
         return deleteSymmetricKey(key, flags);
      }
   }

   public static final boolean removeSymmetricKeyByKeyID(String keyid, byte flags) {
      synchronized (_persistentKeysById) {
         if (keyid == null) {
            throw new IllegalArgumentException();
         }

         CryptoBlock$CryptoBlockKey key = (CryptoBlock$CryptoBlockKey)_keysById.get(keyid);
         return deleteSymmetricKey(key, flags);
      }
   }

   private static final boolean deleteSymmetricKey(CryptoBlock$CryptoBlockKey key, byte flags) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
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
               return new String(buffer, 0, i);
            }

            buffer[i] = (byte)b;
         }
      } catch (IOException var4) {
      }

      throw new CryptoBlockException();
   }

   private static final byte encryptionSchemeToKeyAlgorithm(byte encryptionScheme) {
      switch (encryptionScheme) {
         case 0:
            return 1;
         case 2:
            return 2;
         default:
            throw new CryptoBlockException("unsupported scheme");
      }
   }

   private static final byte keyAlgorithmToEncryptionScheme(byte keyAlgorithm) {
      switch (keyAlgorithm) {
         case -1:
            throw new CryptoBlockException("unsupported algorithm");
         case 0:
         case 1:
         default:
            return 0;
         case 2:
            return 2;
      }
   }

   private static final int checkRedundancy(byte[] data, byte encryptionScheme) {
      if (encryptionScheme == 0) {
         return 0;
      } else {
         int crcPosition = data.length - 4;
         int crc32 = (data[crcPosition] & 255) << 24 | (data[crcPosition + 1] & 255) << 16 | (data[crcPosition + 2] & 255) << 8 | data[crcPosition + 3] & 255;
         int expectedCRC32 = CRC32.update(-1, data, 0, crcPosition);
         if (expectedCRC32 != crc32) {
            throw new CryptoBlockException("Redundancy check failed");
         } else {
            return 4;
         }
      }
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

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static final boolean encode(Vector uids, DataBuffer input, DataBuffer output, boolean doCompress, boolean doEncrypt) {
      if (input != null && output != null) {
         RandomSource.add(input.getArray());
         output.writeByte(0);
         if (!doEncrypt) {
            output.writeByte(0);
            output.writeByte(0);
            if (doCompress) {
               output.writeByte(16);
               output.write(compress(input.getArray(), input.getArrayPosition(), input.available()));
               return false;
            } else {
               output.writeByte(0);
               output.write(input, input.available());
               return false;
            }
         } else {
            PersistentContent.markAsPlaintext(input.getArray());
            if (uids != null && uids.size() != 0) {
               int numUIDs = uids.size();
               CryptoBlock$CryptoBlockKey[] masterKeys = new CryptoBlock$CryptoBlockKey[numUIDs];
               byte selectedAlgorithm = 0;

               for (int i = 0; i < numUIDs; i++) {
                  String currentUID = ((String)uids.elementAt(i)).toLowerCase();
                  CryptoBlock$CryptoBlockKey masterKey = null;
                  synchronized (_persistentKeysById) {
                     masterKey = (CryptoBlock$CryptoBlockKey)_keysByName.get(currentUID);
                  }

                  if (masterKey == null) {
                     throw new CryptoBlockException("No key for " + currentUID);
                  }

                  if (i == 0) {
                     selectedAlgorithm = masterKey._algorithm;
                  } else if (masterKey._algorithm != selectedAlgorithm) {
                     throw new CryptoBlockException("Inconsistent algorithms");
                  }

                  if (masterKey._algorithm == 1 && ITPolicy.getBoolean(24, 43, false) && ITPolicyInternal.verifyITAdminService(currentUID, false)) {
                     throw new CryptoBlockException("3DES crypto disabled");
                  }

                  masterKeys[i] = masterKey;
               }

               byte encryptionScheme = keyAlgorithmToEncryptionScheme(selectedAlgorithm);
               output.writeByte(encryptionScheme);
               byte[] sessionKey = createSessionKey(encryptionScheme);
               int sessionKeyLength = sessionKey.length;

               for (int i = 0; i < numUIDs; i++) {
                  output.write(masterKeys[i]._id.getBytes());
                  output.writeByte(0);
                  output.write(encrypt(masterKeys[i].getKey(), selectedAlgorithm, sessionKey, 0, sessionKeyLength, 1));
               }

               output.writeByte(0);
               addRedundancy(input, encryptionScheme);
               boolean var16 = false /* VF: Semaphore variable */;

               try {
                  var16 = true;
                  if (doCompress) {
                     output.writeByte(25);
                     output.write(compressEncrypt(sessionKey, selectedAlgorithm, input.getArray(), input.getArrayPosition(), input.available(), 2));
                     var16 = false;
                  } else {
                     output.writeByte(9);
                     output.write(encrypt(sessionKey, selectedAlgorithm, input.getArray(), input.getArrayPosition(), input.available(), 3));
                     var16 = false;
                  }
               } finally {
                  if (var16) {
                     removeRedundancy(input, encryptionScheme);
                  }
               }

               removeRedundancy(input, encryptionScheme);
               return true;
            } else {
               throw new CryptoBlockException("No destinations specified");
            }
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static final String decode(DataBuffer input, DataBuffer output) {
      if (input != null && output != null) {
         byte encryptionScheme;
         try {
            if (input.readUnsignedByte() != 0) {
               throw new CryptoBlockException();
            }

            encryptionScheme = (byte)input.readUnsignedByte();
         } catch (IOException e) {
            throw new CryptoBlockException();
         }

         byte keyAlgorithm = encryptionSchemeToKeyAlgorithm(encryptionScheme);
         int keyLength = getKeyLength(keyAlgorithm);
         byte[] validEncryptedSessionKey = null;
         String validKeyID = null;
         CryptoBlock$CryptoBlockKey validMasterKey = null;
         byte[] encryptedSessionKey = new byte[keyLength];

         while (true) {
            String keyID = readKeyID(input);
            if (keyID.length() == 0) {
               int header;
               try {
                  header = input.readUnsignedByte();
               } catch (IOException e) {
                  throw new CryptoBlockException();
               }

               boolean decompress = (header & 240) == 16;
               boolean decrypt = (header & 15) == 9;
               if (!decrypt) {
                  if (encryptionScheme != 0) {
                     throw new CryptoBlockException();
                  }

                  if (decompress) {
                     byte[] data = decompress(input.getArray(), input.getArrayPosition(), input.available());
                     output.setData(data, 0, data.length);
                     output.skipBytes(data.length);
                  } else {
                     int length = input.available();
                     output.setData(input.getArray(), input.getArrayPosition(), length);
                     output.skipBytes(length);
                  }
               } else {
                  if (validMasterKey == null) {
                     throw new CryptoBlockException("No master key");
                  }

                  if (validMasterKey._algorithm == 1
                     && ITPolicy.getBoolean(24, 43, false)
                     && ITPolicyInternal.verifyITAdminService(validMasterKey._name, false)) {
                     throw new CryptoBlockException("3DES crypto disabled");
                  }

                  byte[] sessionKey = decrypt(validMasterKey.getKey(), keyAlgorithm, validEncryptedSessionKey, 0, validEncryptedSessionKey.length, 1);
                  RandomSource.add(sessionKey);
                  if (!checkSessionKey(sessionKey, encryptionScheme)) {
                     throw new CryptoBlockException();
                  }

                  byte[] data;
                  if (decompress) {
                     data = decryptDecompress(sessionKey, keyAlgorithm, input.getArray(), input.getArrayPosition(), input.available(), 2);
                  } else {
                     data = decrypt(sessionKey, keyAlgorithm, input.getArray(), input.getArrayPosition(), input.available(), 3);
                  }

                  int dataLength = data.length - checkRedundancy(data, encryptionScheme);
                  output.setData(data, 0, dataLength);
                  output.skipBytes(dataLength);
                  PersistentContent.markAsPlaintext(output.getArray());
               }

               RandomSource.add(output.getArray());
               return decrypt ? validKeyID : null;
            }

            try {
               input.readFully(encryptedSessionKey);
            } catch (IOException e) {
               throw new CryptoBlockException();
            }

            if (validMasterKey == null) {
               CryptoBlock$CryptoBlockKey masterKeyForKeyID;
               synchronized (_persistentKeysById) {
                  masterKeyForKeyID = (CryptoBlock$CryptoBlockKey)_keysById.get(keyID);
               }

               if (masterKeyForKeyID != null && masterKeyForKeyID._algorithm == keyAlgorithm) {
                  validMasterKey = masterKeyForKeyID;
                  validEncryptedSessionKey = Arrays.copy(encryptedSessionKey);
                  validKeyID = keyID;
               }
            }
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: array init");
   }

   private static final void registerKeyCollections() {
      OTAUpgrade.addOTASLOnlyCollection(new CryptoBlock$CBCollection(_persistentKeysByName));
      OTAUpgrade.addOTASLOnlyCollection(new CryptoBlock$CBCollection(_persistentKeysById));
   }
}
