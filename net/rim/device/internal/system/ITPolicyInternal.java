package net.rim.device.internal.system;

import net.rim.device.api.crypto.ITPolicyAuthentication;
import net.rim.device.api.itpolicy.ITPolicy;
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

   private static final boolean itadminDataIsEnabled(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isITPolicyEnabled() {
      byte[] var0 = NvStore.readData(4);
      return var0 != null && var0.length > 0;
   }

   public static final byte[] getPinKey() {
      return NvStore.readData(8);
   }

   public static final void setPinKey(byte[] var0) {
      NvStore.writeData(8, var0);
      RIMGlobalMessagePoster.postGlobalEvent(-2475029172703491550L);
   }

   public static final boolean setITPolicy(DataBuffer var0) {
      return isITAdminEnabled() ? false : updateStatusAndNotify(var0);
   }

   public static final boolean setOTAITPolicy(DataBuffer var0) {
      return updateStatusAndNotify(var0);
   }

   private static final Object getUpdateMonitor() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      Object var1 = var0.getOrWaitFor(-2240729434218296975L);
      if (var1 == null) {
         var1 = new Object();
         var0.put(-2240729434218296975L, var1);
      }

      return var1;
   }

   public static final byte[] authenticateITPolicy(byte[] var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final boolean verifyITPolicy(byte[] var0, byte[] var1) {
      if (verifiedPrependSignature(ITPolicy.getByteArray(252), var0, var1)) {
         return true;
      }

      byte[][][] var2 = getSecondaryPublicKeys();
      if (var2 != null) {
         for (int var3 = 0; var3 < var2.length; var3++) {
            if (verifiedPrependSignature((byte[])var2[var3], var0, var1)) {
               return true;
            }
         }
      }

      return false;
   }

   private static final boolean verifiedPrependSignature(byte[] var0, byte[] var1, byte[] var2) {
      if (var0 != null && Arrays.equals(var0, 69, var1, 0, 4)) {
         byte[] var3 = Arrays.copy(var1, 4, var1.length - 4);
         return ITPolicyAuthentication.verifyITPolicy(var0, var3, var2);
      } else {
         return false;
      }
   }

   private static final byte[][][] getSecondaryPublicKeys() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final boolean updateStatusAndNotify(DataBuffer var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean verifyITAdminService(String var0, boolean var1) {
      String var2 = ITPolicy.getString(24, 51);
      return var2 != null && var0 != null ? var2.toLowerCase().indexOf(var0.toLowerCase()) != -1 : var1;
   }

   public static final boolean verifyBoundITAdminService(String var0, boolean var1) {
      return isPolicyBindingEnabled() ? verifyITAdminService(var0, var1) : var1;
   }

   private static final void setProcessedItadminTimeStamp() {
      Object var0 = new Object(true);
      Object var1 = new Object(true);
      ((DataBuffer)var0).writeLong(System.currentTimeMillis());
      TLEUtilities.writeDataField((DataBuffer)var1, 1, ((DataBuffer)var0).toArray());
      NvStore.writeData(7, ((DataBuffer)var1).getArray());
   }

   public static final long getProcessedTimeStamp() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getITAdminTimeStamp() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void scrubITPolicy(DataBuffer var0, DataBuffer var1, DataBuffer var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void scrubWipeableBlob(int var0, DataBuffer var1, byte[] var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void updateOSPolicy() {
      byte var0 = 0;
      if (ITPolicy.getBoolean(31, 1, false)) {
         var0 |= 2;
      }

      if (ITPolicy.getBoolean(31, 2, false)) {
         var0 |= 4;
      }

      if (ITPolicy.getBoolean(31, 3, false)) {
         var0 |= 8;
      }

      setOSPolicy(1, var0);
      boolean var1 = ITPolicy.getBoolean(24, 49, false);
      setOSPolicy(4, var1 ? 0 : 1);
      boolean var2 = ITPolicy.getBoolean(34, 16, false);
      setOSPolicy(5, var2 ? 0 : 1);
   }

   private static final native boolean setOSPolicy(int var0, int var1);

   public static final Byte readByteInternal(byte[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Byte readByte(byte[] var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String readStringInternal(byte[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String readString(byte[] var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Integer readIntegerInternal(byte[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Integer readInteger(byte[] var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] readByteArrayInternal(byte[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] readByteArray(byte[] var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] readWipeablePolicyData() {
      byte[] var0 = NvStore.readData(42);
      byte[] var1 = null;
      if (var0 != null) {
         var1 = WipeablePolicyCryptoBlock.decrypt(var0);
         Memory.setPlaintext(var1);
      }

      return var1;
   }

   public static final void writeWipeablePolicyData(byte[] var0) {
      if (var0 != null) {
         Memory.setPlaintext(var0);
         new ITPolicyInternal$WipeablePolicyWriter(var0).start();
      }
   }

   private static final void logMalformedPolicy(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
