package net.rim.device.internal.system;

import java.io.EOFException;
import java.io.IOException;
import net.rim.device.api.crypto.ITPolicyAuthentication;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.itpolicy.WipeITPolicyDirectory;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.TLEUtilities;
import net.rim.device.internal.crypto.WipeablePolicyCryptoBlock;
import net.rim.vm.Memory;

public final class ITPolicyInternal {
   private static final int PROCESSED_TIMESTAMP;
   public static final int OTA_ITADMIN_ENABLED;
   public static final int POLICY_BINDING_ENABLED;
   private static final long UPDATE_MONITOR_KEY;

   private ITPolicyInternal() {
   }

   public static final void initialize() {
      updateOSPolicy();
   }

   public static final boolean policyHasChanged() {
      return NvStore.readInt(6, 0) != 0;
   }

   public static final void markUpdate() {
      NvStore.writeInt(6, 0);
   }

   public static final boolean isITAdminEnabled() {
      return NvStore.readData(5) != null;
   }

   public static final boolean isOTAITAdminEnabled() {
      return itadminDataIsEnabled(2);
   }

   public static final boolean isPolicyBindingEnabled() {
      return itadminDataIsEnabled(3);
   }

   private static final boolean itadminDataIsEnabled(int flag) {
      byte[] itadminData = NvStore.readData(5);
      if (itadminData != null && itadminData.length > 0) {
         DataBuffer data = new DataBuffer(itadminData, 0, itadminData.length, true);
         data.skipBytes(5);

         try {
            while (!data.eof()) {
               int tag = TLEUtilities.getType(data);
               switch (tag) {
                  case 1:
                     TLEUtilities.skipField(data);
                     break;
                  case 2:
                  default:
                     return true;
                  case 3:
                     return true;
               }
            }
         } catch (EOFException var4) {
         }
      }

      return false;
   }

   public static final boolean isITPolicyEnabled() {
      byte[] itPolicyData = NvStore.readData(4);
      return itPolicyData != null && itPolicyData.length > 0;
   }

   public static final byte[] getPinKey() {
      return NvStore.readData(8);
   }

   public static final void setPinKey(byte[] pinKey) {
      NvStore.writeData(8, pinKey);
      RIMGlobalMessagePoster.postGlobalEvent(-2475029172703491550L);
   }

   public static final boolean setITPolicy(DataBuffer data) {
      return isITAdminEnabled() ? false : updateStatusAndNotify(data);
   }

   public static final boolean setOTAITPolicy(DataBuffer data) {
      return updateStatusAndNotify(data);
   }

   private static final Object getUpdateMonitor() {
      ApplicationRegistry ar = ApplicationRegistry.getApplicationRegistry();
      Object monitor = ar.getOrWaitFor(-2240729434218296975L);
      if (monitor == null) {
         monitor = new Object();
         ar.put(-2240729434218296975L, monitor);
      }

      return monitor;
   }

   public static final byte[] authenticateITPolicy(byte[] policy) {
      boolean policyBindingEnabled = isPolicyBindingEnabled();
      byte[] primaryKey = ITPolicy.getByteArray(252);
      byte[][][] secondaryKeys = getSecondaryPublicKeys();
      byte[] policyWithoutSignature = null;
      byte[] signedPolicyWithoutVersionAndSignature = null;
      DataBuffer policyBuffer = new DataBuffer(policy, 0, policy.length, true);

      try {
         policyBuffer.skipBytes(1);
         if ((primaryKey != null || secondaryKeys != null) && policyBindingEnabled) {
            try {
               while (!policyBuffer.eof()) {
                  int tag = TLEUtilities.getType(policyBuffer);
                  switch (tag) {
                     case 251:
                        byte[] secondarySignatures = TLEUtilities.readDataField(policyBuffer, 251);
                        DataBuffer signaturesBuffer = new DataBuffer(secondarySignatures, 0, secondarySignatures.length, true);

                        while (!signaturesBuffer.eof()) {
                           int subTag = TLEUtilities.getType(signaturesBuffer);
                           switch (subTag) {
                              case 1:
                                 byte[] signature = TLEUtilities.readDataField(signaturesBuffer, 1);
                                 if (verifyITPolicy(signature, signedPolicyWithoutVersionAndSignature)) {
                                    return Arrays.copy(policy, 0, policyBuffer.getPosition());
                                 }
                                 break;
                              default:
                                 TLEUtilities.skipField(signaturesBuffer);
                           }
                        }
                        break;
                     case 253:
                        signedPolicyWithoutVersionAndSignature = Arrays.copy(policy, 1, policyBuffer.getPosition() - 1);
                        byte[] primarySignature = TLEUtilities.readDataField(policyBuffer, 253);
                        if (primaryKey != null && ITPolicyAuthentication.verifyITPolicy(primaryKey, primarySignature, signedPolicyWithoutVersionAndSignature)) {
                           return Arrays.copy(policy, 0, policyBuffer.getPosition());
                        }
                        break;
                     case 255:
                        TLEUtilities.skipField(policyBuffer);
                        int lengthTagValue = policyBuffer.readCompressedInt();
                        policyBuffer.skipBytes(lengthTagValue);
                        break;
                     default:
                        TLEUtilities.skipField(policyBuffer);
                  }
               }

               return null;
            } catch (EOFException var14) {
               return null;
            }
         } else {
            try {
               while (!policyBuffer.eof()) {
                  if (TLEUtilities.getType(policyBuffer) == 253) {
                     TLEUtilities.readDataField(policyBuffer, 253);
                     return Arrays.copy(policy, 0, policyBuffer.getPosition());
                  }

                  TLEUtilities.skipField(policyBuffer);
               }
            } catch (EOFException var13) {
            }

            return policy;
         }
      } catch (Exception e) {
         logMalformedPolicy(e.toString());
         throw new ITPolicyParseException(e.toString());
      }
   }

   private static final boolean verifyITPolicy(byte[] prependSignature, byte[] signedPolicyWithoutVersionAndSignature) {
      if (verifiedPrependSignature(ITPolicy.getByteArray(252), prependSignature, signedPolicyWithoutVersionAndSignature)) {
         return true;
      }

      byte[][][] keys = getSecondaryPublicKeys();
      if (keys != null) {
         for (int i = 0; i < keys.length; i++) {
            if (verifiedPrependSignature((byte[])keys[i], prependSignature, signedPolicyWithoutVersionAndSignature)) {
               return true;
            }
         }
      }

      return false;
   }

   private static final boolean verifiedPrependSignature(byte[] key, byte[] prependSignature, byte[] signedPolicyWithoutVersionAndSignature) {
      if (key != null && Arrays.equals(key, 69, prependSignature, 0, 4)) {
         byte[] signature = Arrays.copy(prependSignature, 4, prependSignature.length - 4);
         return ITPolicyAuthentication.verifyITPolicy(key, signature, signedPolicyWithoutVersionAndSignature);
      } else {
         return false;
      }
   }

   private static final byte[][][] getSecondaryPublicKeys() {
      throw new RuntimeException("cod2jar: type check");
   }

   private static final boolean updateStatusAndNotify(DataBuffer data) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean verifyITAdminService(String serviceUID, boolean defaultValue) {
      String besUIDs = ITPolicy.getString(24, 51);
      return besUIDs != null && serviceUID != null ? besUIDs.toLowerCase().indexOf(serviceUID.toLowerCase()) != -1 : defaultValue;
   }

   public static final boolean verifyBoundITAdminService(String serviceUID, boolean defaultValue) {
      return isPolicyBindingEnabled() ? verifyITAdminService(serviceUID, defaultValue) : defaultValue;
   }

   private static final void setProcessedItadminTimeStamp() {
      DataBuffer buff = new DataBuffer(true);
      DataBuffer itadminStatusBuffer = new DataBuffer(true);
      buff.writeLong(System.currentTimeMillis());
      TLEUtilities.writeDataField(itadminStatusBuffer, 1, buff.toArray());
      NvStore.writeData(7, itadminStatusBuffer.getArray());
   }

   public static final long getProcessedTimeStamp() {
      long result = 0;
      DataBuffer itadminStatusBuffer = null;
      byte[] itadminStatus = NvStore.readData(7);
      if (itadminStatus != null) {
         itadminStatusBuffer = new DataBuffer(true);
         itadminStatusBuffer.setData(itadminStatus, 0, itadminStatus.length, true);
      }

      if (itadminStatusBuffer != null) {
         try {
            while (!itadminStatusBuffer.eof()) {
               int tag = TLEUtilities.getType(itadminStatusBuffer);
               switch (tag) {
                  case 1:
                     itadminStatusBuffer.readCompressedInt();
                     itadminStatusBuffer.readCompressedInt();
                     result = itadminStatusBuffer.readLong();
                     break;
                  default:
                     TLEUtilities.skipField(itadminStatusBuffer);
               }
            }
         } catch (EOFException var5) {
         }
      }

      return result;
   }

   public static final int getITAdminTimeStamp() {
      int itAdminTimeStamp = 0;
      byte[] itadminData = NvStore.readData(5);
      if (itadminData != null) {
         DataBuffer itadminDataBuffer = new DataBuffer(itadminData, 0, itadminData.length, true);

         try {
            itadminDataBuffer.skipBytes(1);
            return itadminDataBuffer.readInt();
         } catch (IOException var4) {
         }
      }

      return itAdminTimeStamp;
   }

   private static final void scrubITPolicy(DataBuffer originalITPolicy, DataBuffer scrubbedITPolicy, DataBuffer wipeableITPoliy) {
      originalITPolicy.rewind();
      byte[] dataArray = originalITPolicy.getArray();

      try {
         scrubbedITPolicy.write(originalITPolicy.readByte());
      } catch (EOFException var9) {
      }

      int currGroup = -1;
      int previousEnd = originalITPolicy.getArrayPosition();
      int start = -1;
      int end = -1;

      while (true) {
         try {
            currGroup = TLEUtilities.getType(originalITPolicy);
            if (WipeITPolicyDirectory.isWipeableGroup(currGroup)) {
               start = originalITPolicy.getArrayPosition();
               TLEUtilities.skipField(originalITPolicy);
               end = originalITPolicy.getArrayPosition();
               scrubbedITPolicy.write(dataArray, previousEnd, start - previousEnd);
               wipeableITPoliy.write(dataArray, start, end - start);
               scrubWipeableBlob(currGroup, scrubbedITPolicy, dataArray, start, end - start);
               previousEnd = end;
            } else if (currGroup != 255) {
               TLEUtilities.skipField(originalITPolicy);
            } else {
               TLEUtilities.skipField(originalITPolicy);
               int lengthUserData = originalITPolicy.readCompressedInt();
               originalITPolicy.skipBytes(lengthUserData);
            }
         } catch (EOFException e) {
            scrubbedITPolicy.write(dataArray, previousEnd, originalITPolicy.getLength() - previousEnd);
            return;
         } catch (Exception e) {
            logMalformedPolicy(e.toString());
            throw new ITPolicyParseException(e.toString());
         }
      }
   }

   private static final void scrubWipeableBlob(int group, DataBuffer scrubbedITPolicy, byte[] buffer, int offset, int length) {
      DataBuffer blobToScrub = new DataBuffer(buffer, offset, length, true);
      DataBuffer persistableSettings = new DataBuffer();

      try {
         blobToScrub.readByte();
         int id = blobToScrub.readCompressedInt();
      } catch (EOFException e) {
         return;
      }

      int id = -1;
      int start = -1;
      int end = -1;

      while (true) {
         try {
            id = TLEUtilities.getType(blobToScrub);
            start = blobToScrub.getArrayPosition();
            TLEUtilities.skipField(blobToScrub);
            end = blobToScrub.getArrayPosition();
            if (!WipeITPolicyDirectory.isWipeableId(group, id)) {
               persistableSettings.write(buffer, start, end - start);
            }
         } catch (EOFException var12) {
            TLEUtilities.writeDataField(scrubbedITPolicy, group, persistableSettings.getArray());
            return;
         }
      }
   }

   private static final void updateOSPolicy() {
      int stkPolicy = 0;
      if (ITPolicy.getBoolean(31, 1, false)) {
         stkPolicy |= 2;
      }

      if (ITPolicy.getBoolean(31, 2, false)) {
         stkPolicy |= 4;
      }

      if (ITPolicy.getBoolean(31, 3, false)) {
         stkPolicy |= 8;
      }

      setOSPolicy(1, stkPolicy);
      boolean modemDisabled = ITPolicy.getBoolean(24, 49, false);
      setOSPolicy(4, modemDisabled ? 0 : 1);
      boolean btModemDisabled = ITPolicy.getBoolean(34, 16, false);
      setOSPolicy(5, btModemDisabled ? 0 : 1);
   }

   private static final native boolean setOSPolicy(int var0, int var1);

   public static final Byte readByteInternal(byte[] data, int group, int type) {
      if (data != null) {
         DataBuffer policyBuffer = new DataBuffer(data, 0, data.length, true);

         try {
            if (TLEUtilities.findType(policyBuffer, group)) {
               byte[] groupData = TLEUtilities.readDataField(policyBuffer, group);
               return readByte(groupData, type);
            }
         } catch (Exception var5) {
         }
      }

      return null;
   }

   public static final Byte readByte(byte[] groupData, int id) {
      if (groupData != null) {
         DataBuffer groupBuffer = new DataBuffer(groupData, 0, groupData.length, true);

         try {
            if (TLEUtilities.findType(groupBuffer, id)) {
               byte[] field = TLEUtilities.readDataField(groupBuffer, id);
               if (field != null && field.length > 0) {
                  return new Byte(field[0]);
               }
            }
         } catch (Exception var4) {
         }
      }

      return null;
   }

   public static final String readStringInternal(byte[] data, int group, int id) {
      if (data != null) {
         DataBuffer policyBuffer = new DataBuffer(data, 0, data.length, true);

         try {
            if (TLEUtilities.findType(policyBuffer, group)) {
               byte[] groupData = TLEUtilities.readDataField(policyBuffer, group);
               return readString(groupData, id);
            }
         } catch (Exception var5) {
         }
      }

      return null;
   }

   public static final String readString(byte[] groupData, int id) {
      if (groupData != null) {
         DataBuffer groupBuffer = new DataBuffer(groupData, 0, groupData.length, true);

         try {
            if (TLEUtilities.findType(groupBuffer, id)) {
               return TLEUtilities.readStringField(groupBuffer, id);
            }
         } catch (Exception var4) {
         }
      }

      return null;
   }

   public static final Integer readIntegerInternal(byte[] data, int group, int id) {
      if (data != null) {
         DataBuffer policyBuffer = new DataBuffer(data, 0, data.length, true);

         try {
            if (TLEUtilities.findType(policyBuffer, group)) {
               byte[] groupData = TLEUtilities.readDataField(policyBuffer, group);
               return readInteger(groupData, id);
            }
         } catch (Exception var5) {
         }
      }

      return null;
   }

   public static final Integer readInteger(byte[] groupData, int id) {
      if (groupData != null) {
         DataBuffer groupBuffer = new DataBuffer(groupData, 0, groupData.length, true);

         try {
            if (TLEUtilities.findType(groupBuffer, id)) {
               return new Integer(TLEUtilities.readIntegerField(groupBuffer, id));
            }
         } catch (Exception var4) {
         }
      }

      return null;
   }

   public static final byte[] readByteArrayInternal(byte[] data, int group, int id) {
      if (data != null) {
         DataBuffer policyBuffer = new DataBuffer(data, 0, data.length, true);

         try {
            if (TLEUtilities.findType(policyBuffer, group)) {
               byte[] groupData = TLEUtilities.readDataField(policyBuffer, group);
               return readByteArray(groupData, id);
            }
         } catch (EOFException var5) {
         }
      }

      return null;
   }

   public static final byte[] readByteArray(byte[] groupData, int id) {
      if (groupData != null) {
         DataBuffer groupBuffer = new DataBuffer(groupData, 0, groupData.length, true);

         try {
            if (TLEUtilities.findType(groupBuffer, id)) {
               return TLEUtilities.readDataField(groupBuffer, id);
            }
         } catch (Exception var4) {
         }
      }

      return null;
   }

   public static final byte[] readWipeablePolicyData() {
      byte[] policyData = NvStore.readData(42);
      byte[] decryptedPolicyData = null;
      if (policyData != null) {
         decryptedPolicyData = WipeablePolicyCryptoBlock.decrypt(policyData);
         Memory.setPlaintext(decryptedPolicyData);
      }

      return decryptedPolicyData;
   }

   public static final void writeWipeablePolicyData(byte[] data) {
      if (data != null) {
         Memory.setPlaintext(data);
         new ITPolicyInternal$WipeablePolicyWriter(data).start();
      }
   }

   private static final void logMalformedPolicy(String msg) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
