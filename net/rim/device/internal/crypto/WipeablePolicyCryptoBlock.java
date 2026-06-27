package net.rim.device.internal.crypto;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentContentInternal;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.internal.system.NvStore;
import net.rim.vm.Array;
import net.rim.vm.OTAUpgrade;

public class WipeablePolicyCryptoBlock {
   private static final long ID;
   private static PersistentObject _persistentWLANLock;
   private static byte[][][] _wlanKey;
   private static final long ID_WIPEABLE_POLICY_CRYPTOBLOCK;

   private WipeablePolicyCryptoBlock() {
   }

   public static boolean keyAvailable() {
      return isWLANKeyAvailable();
   }

   public static Object encode(Object var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static Object decode(Object var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static byte[] encrypt(byte[] var0) {
      PersistentContent.markAsPlaintext(var0);
      if (var0 == null) {
         throw new Object();
      }

      byte[] var1 = new byte[EncryptionUtilities.getCiphertextLength(var0.length)];
      EncryptionUtilities.encrypt(getWLANKey(), var0, 0, var0.length, var1, 0);
      return var1;
   }

   public static byte[] decrypt(byte[] var0) {
      if (var0 == null) {
         throw new Object();
      }

      byte[] var1 = new byte[var0.length];
      int var2 = EncryptionUtilities.decrypt(getWLANKey(), var0, 0, var0.length, var1, 0);
      Array.resize(var1, var2);
      PersistentContent.markAsPlaintext(var1);
      return var1;
   }

   private static byte[] getWLANKey() {
      return getWLANKey(true);
   }

   private static byte[] getWLANKey(boolean var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static void setNvStoreWLANKey(byte[] var0) {
      Object var1 = PersistentContent.encode(var0, false, getEncryptFlag());
      setWLANKeyData(PersistentContent.convertEncodingToByteArray(var1));
   }

   private static byte[] getNvStoreWLANKey() {
      byte[] var0 = getWLANKeyData();
      if (var0 == null) {
         return null;
      }

      Object var1 = PersistentContent.convertByteArrayToEncoding(var0);
      return PersistentContentInternal.decodeByteArray(var1, false, true);
   }

   private static void setWLANKeyData(byte[] var0) {
      NvStore.writeData(41, var0);
   }

   private static byte[] getWLANKeyData() {
      return NvStore.readData(41);
   }

   private static void handleInternalUpgradeCase(byte[] var0) {
      byte[] var1 = NvStore.readData(42);
      if (var1 != null) {
         byte[] var2 = new byte[EncryptionUtilities.getCiphertextLength(var1.length)];
         EncryptionUtilities.encrypt(var0, var1, 0, var1.length, var2, 0);
         NvStore.writeData(42, var2);
      }
   }

   private static boolean isWLANKeyAvailable() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static boolean getEncryptFlag() {
      return PersistentContent.isEncryptionEnabled() && ITPolicy.getBoolean(24, 53, false);
   }

   private static void registerPersistentContentListener() {
      PersistentContent.addListener(new WipeablePolicyCryptoBlock$MyPersistentContentListener(null));
   }

   private static void registerKeyCollection() {
      OTAUpgrade.addOTASLOnlyCollection(new WipeablePolicyCryptoBlock$WipeableCBCollection());
   }

   static byte[][][] access$702(byte[][][] var0) {
      _wlanKey = var0;
      return var0;
   }
}
