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

   public static Object encode(Object obj) {
      if (obj == null) {
         return null;
      }

      Object encoding = null;

      try {
         encoding = encrypt(PersistentContent.convertEncodingToByteArray(obj));
         PersistentContent.markAsPlaintext(obj);
         return encoding;
      } catch (IllegalArgumentException var3) {
         return encoding;
      }
   }

   public static Object decode(Object obj) {
      if (obj != null && obj instanceof byte[]) {
         Object decoding = null;

         try {
            decoding = PersistentContent.convertByteArrayToEncoding(decrypt((byte[])obj));
            PersistentContent.markAsPlaintext(decoding);
            return decoding;
         } catch (IllegalArgumentException var3) {
            return decoding;
         }
      } else {
         return null;
      }
   }

   public static byte[] encrypt(byte[] input) {
      PersistentContent.markAsPlaintext(input);
      if (input == null) {
         throw new IllegalArgumentException();
      }

      byte[] cipherText = new byte[EncryptionUtilities.getCiphertextLength(input.length)];
      EncryptionUtilities.encrypt(getWLANKey(), input, 0, input.length, cipherText, 0);
      return cipherText;
   }

   public static byte[] decrypt(byte[] input) {
      if (input == null) {
         throw new IllegalArgumentException();
      }

      byte[] plainText = new byte[input.length];
      int plainTextLength = EncryptionUtilities.decrypt(getWLANKey(), input, 0, input.length, plainText, 0);
      Array.resize(plainText, plainTextLength);
      PersistentContent.markAsPlaintext(plainText);
      return plainText;
   }

   private static byte[] getWLANKey() {
      return getWLANKey(true);
   }

   private static byte[] getWLANKey(boolean blockingCall) {
      throw new RuntimeException("cod2jar: array creation");
   }

   private static void setNvStoreWLANKey(byte[] key) {
      Object encoding = PersistentContent.encode(key, false, getEncryptFlag());
      setWLANKeyData(PersistentContent.convertEncodingToByteArray(encoding));
   }

   private static byte[] getNvStoreWLANKey() {
      byte[] wlanKeyData = getWLANKeyData();
      if (wlanKeyData == null) {
         return null;
      }

      Object encoding = PersistentContent.convertByteArrayToEncoding(wlanKeyData);
      return PersistentContentInternal.decodeByteArray(encoding, false, true);
   }

   private static void setWLANKeyData(byte[] data) {
      NvStore.writeData(41, data);
   }

   private static byte[] getWLANKeyData() {
      return NvStore.readData(41);
   }

   private static void handleInternalUpgradeCase(byte[] key) {
      byte[] wipeableData = NvStore.readData(42);
      if (wipeableData != null) {
         byte[] cipherText = new byte[EncryptionUtilities.getCiphertextLength(wipeableData.length)];
         EncryptionUtilities.encrypt(key, wipeableData, 0, wipeableData.length, cipherText, 0);
         NvStore.writeData(42, cipherText);
      }
   }

   private static boolean isWLANKeyAvailable() {
      try {
         return getWLANKey(false) != null;
      } catch (IllegalStateException e) {
         return false;
      }
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

   static byte[][][] access$702(byte[][][] x0) {
      _wlanKey = x0;
      return x0;
   }
}
