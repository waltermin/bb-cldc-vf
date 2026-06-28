package net.rim.device.internal.crypto;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.util.Date;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.synchronization.ConverterUtilities;
import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.synchronization.UIDGenerator;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentContentListener;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.Persistable;
import net.rim.vm.Memory;

final class CryptoBlock$CryptoBlockKey implements Persistable, SyncObject {
   String _name;
   String _id;
   byte _algorithm;
   int _uid;
   int _keyLength;
   byte[] _data;
   long _expireTime;
   boolean _enterpriseClassKey;
   static final int CBK_NAME;
   static final int CBK_ID;
   static final int CBK_ALGORITHM;
   static final int CBK_KEY_LENGTH;
   static final int CBK_DATA;
   static final int CBK_EXPIRE_TIME;
   static final int CBK_ENTERPRISE_CLASS_KEY;
   static final int CBK_DEVICE_KEY;
   static final int CBK_DEVICE_KEY_ENCODED;
   static final int CBK_MASK_ALL;
   private static final long ID;
   private static PersistentObject _persistentDeviceKey;
   private static byte[][][] _deviceKey;

   final String getHashKey(boolean useID) {
      return useID ? this._id : this._name;
   }

   final void logAction(String action) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final byte[] getKey() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final int getUID() {
      return this._uid;
   }

   CryptoBlock$CryptoBlockKey(DataInput input, String name, long expireTime, boolean enterpriseClassKey) {
      try {
         PersistentContent.markAsPlaintext(input);
         this._algorithm = input.readByte();
         this._id = CryptoBlock.readKeyID(input);
         this._uid = UIDGenerator.getUID();
         this._keyLength = CryptoBlock.getKeyLength(this._algorithm);
         byte[] data = Memory.allocRAMOnlyBytes(this._keyLength);
         PersistentContent.markAsPlaintext(data);
         input.readFully(data);
         RandomSource.add(data);
         RandomSource.add(name);
         this._data = new byte[EncryptionUtilities.getCiphertextLength(this._keyLength)];
         EncryptionUtilities.encrypt(getDeviceKey(), data, 0, this._keyLength, this._data, 0);
         this._name = name;
         this._expireTime = expireTime;
         this._enterpriseClassKey = enterpriseClassKey;
      } catch (IOException e) {
         throw new Object();
      }
   }

   static final SyncObject convert(DataBuffer buff, int uid, Object[] key) {
      try {
         String name = null;
         String id = null;
         byte algorithm = 0;
         int keyLength = 0;
         byte[] data = null;
         long expire = 0;
         boolean enterprise = false;
         int mask = 0;

         while (true) {
            int tag = ConverterUtilities.getType(buff);
            switch (tag) {
               case -1:
                  ConverterUtilities.skipField(buff);
                  continue;
               case 0:
               default:
                  name = ConverterUtilities.readString(buff);
                  break;
               case 1:
                  id = ConverterUtilities.readString(buff);
                  break;
               case 2:
                  algorithm = (byte)ConverterUtilities.readInt(buff);
                  break;
               case 3:
                  keyLength = ConverterUtilities.readInt(buff);
                  break;
               case 4:
                  data = ConverterUtilities.readByteArray(buff);
                  break;
               case 5:
                  expire = ConverterUtilities.readLong(buff);
                  break;
               case 6:
                  enterprise = ConverterUtilities.readInt(buff) != 0;
                  break;
               case 7:
                  key[0] = ConverterUtilities.readByteArray(buff);
                  break;
               case 8:
                  char[][][] encodedDeviceKey = ConverterUtilities.readCharArrayArray(buff, false);
                  key[0] = encodedDeviceKey[0];
                  tag = 7;
            }

            mask |= 1 << tag;
            if (tag == 0 && mask == 255) {
               return new CryptoBlock$CryptoBlockKey(name, id, algorithm, uid, keyLength, data, expire, enterprise);
            }
         }
      } catch (EOFException var14) {
         return null;
      }
   }

   static final int sameDeviceKey(Object oldKey) {
      throw new RuntimeException("cod2jar: type check");
   }

   static final void setDeviceKey(Object key, boolean refreshDeviceKey) {
      synchronized (_persistentDeviceKey) {
         _persistentDeviceKey.setContents(key, 4801362);
         _persistentDeviceKey.commit();
         if (refreshDeviceKey) {
            refreshDeviceKey();
         }
      }
   }

   private static final void refreshDeviceKey() {
      synchronized (_persistentDeviceKey) {
         _deviceKey[0] = null;
         getDeviceKey();
      }
   }

   private static final boolean getEncryptFlag() {
      return PersistentContent.isEncryptionEnabled() && ITPolicy.getBoolean(24, 53, false);
   }

   private static final byte[] getDeviceKey() {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean areMasterKeysEncrypted() {
      synchronized (_persistentDeviceKey) {
         return PersistentContent.isEncrypted(_persistentDeviceKey.getContents());
      }
   }

   public static final boolean areMasterKeysAvailable() {
      try {
         return getDeviceKey() != null;
      } catch (IllegalStateException e) {
         return false;
      }
   }

   static final boolean convert(SyncObject object, DataBuffer buff) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void registerPersistentContentListener() {
      PersistentContent.addListener((PersistentContentListener)(new Object(null)));
   }

   CryptoBlock$CryptoBlockKey(String name, String id, byte algorithm, int uid, int keyLength, byte[] data, long expireTime, boolean enterpriseClassKey) {
      this._name = name;
      this._id = id;
      this._algorithm = algorithm;
      this._uid = uid;
      this._keyLength = keyLength;
      this._data = data;
      this._expireTime = expireTime;
      this._enterpriseClassKey = enterpriseClassKey;
      RandomSource.add(data);
      RandomSource.add(name);
   }

   @Override
   public final String toString() {
      StringBuffer buff = new StringBuffer();
      buff.append('"').append(this._name).append('"');
      buff.append('(').append(this._id).append(')');
      buff.append(Integer.toHexString(this._uid));
      if (this._expireTime != -1) {
         Date d = new Date(this._expireTime);
         buff.append(' ').append(d);
      }

      return buff.toString();
   }

   @Override
   public final boolean equals(Object o) {
      throw new RuntimeException("cod2jar: type check");
   }
}
