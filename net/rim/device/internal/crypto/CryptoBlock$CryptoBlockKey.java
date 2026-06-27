package net.rim.device.internal.crypto;

import java.io.DataInput;
import java.util.Date;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentContentListener;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.Persistable;

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

   final String getHashKey(boolean var1) {
      return var1 ? this._id : this._name;
   }

   final void logAction(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final byte[] getKey() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int getUID() {
      return this._uid;
   }

   CryptoBlock$CryptoBlockKey(DataInput var1, String var2, long var3, boolean var5) {
   }

   static final SyncObject convert(DataBuffer var0, int var1, Object[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final int sameDeviceKey(Object var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void setDeviceKey(Object var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void refreshDeviceKey() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final boolean getEncryptFlag() {
      return PersistentContent.isEncryptionEnabled() && ITPolicy.getBoolean(24, 53, false);
   }

   private static final byte[] getDeviceKey() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean areMasterKeysEncrypted() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean areMasterKeysAvailable() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final boolean convert(SyncObject var0, DataBuffer var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void registerPersistentContentListener() {
      PersistentContent.addListener((PersistentContentListener)(new Object(null)));
   }

   CryptoBlock$CryptoBlockKey(String var1, String var2, byte var3, int var4, int var5, byte[] var6, long var7, boolean var9) {
      this._name = var1;
      this._id = var2;
      this._algorithm = var3;
      this._uid = var4;
      this._keyLength = var5;
      this._data = var6;
      this._expireTime = var7;
      this._enterpriseClassKey = var9;
      RandomSource.add(var6);
      RandomSource.add(var1);
   }

   @Override
   public final String toString() {
      StringBuffer var1 = new StringBuffer();
      var1.append('"').append(this._name).append('"');
      var1.append('(').append(this._id).append(')');
      var1.append(Integer.toHexString(this._uid));
      if (this._expireTime != -1) {
         Date var2 = new Date(this._expireTime);
         var1.append(' ').append(var2);
      }

      return var1.toString();
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
