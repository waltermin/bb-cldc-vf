package net.rim.device.internal.system;

import net.rim.device.api.itpolicy.ITPolicy;

public final class ITPolicyExtended {
   private ITPolicyExtended() {
   }

   public static final String getString(int var0, int var1, int var2, boolean var3) {
      return var3 ? ITPolicy.getString(var0, var1) : readString(249, var0, var2, var1);
   }

   public static final byte[] getByteArray(int var0, int var1, int var2, boolean var3) {
      return var3 ? ITPolicy.getByteArray(var0, var1) : readByteArray(249, var0, var2, var1);
   }

   public static final byte[] getByteArray(int var0, int var1) {
      return readByteArray(249, var0, var1);
   }

   public static final int getGroupNumber(int var0) {
      return readCount(249, var0);
   }

   public static final boolean getBoolean(int var0, int var1, boolean var2, int var3, boolean var4) {
      return var4 ? ITPolicy.getBoolean(var0, var1, var2) : readByte(249, var0, var3, var1, (byte)(var2 ? 1 : 0)) != 0;
   }

   public static final int getInteger(int var0, int var1, int var2, int var3, boolean var4) {
      return var4 ? ITPolicy.getInteger(var0, var1, var2) : readInt(249, var0, var3, var1, var2);
   }

   public static final byte getByte(int var0, int var1, byte var2, int var3, boolean var4) {
      return var4 ? ITPolicy.getByte(var0, var1, var2) : readByte(249, var0, var3, var1, var2);
   }

   private static final byte readByte(int var0, int var1, int var2, int var3, byte var4) {
      byte[] var5 = getPolicyGroupData(var0, var1, var2);
      Byte var6 = ITPolicyInternal.readByte(var5, var3);
      return var6 != null ? var6 : var4;
   }

   private static final int readInt(int var0, int var1, int var2, int var3, int var4) {
      byte[] var5 = getPolicyGroupData(var0, var1, var2);
      Integer var6 = ITPolicyInternal.readInteger(var5, var3);
      return var6 != null ? var6 : var4;
   }

   private static final String readString(int var0, int var1, int var2, int var3) {
      byte[] var4 = getPolicyGroupData(var0, var1, var2);
      return ITPolicyInternal.readString(var4, var3);
   }

   private static final byte[] readByteArray(int var0, int var1, int var2, int var3) {
      byte[] var4 = getPolicyGroupData(var0, var1, var2);
      return ITPolicyInternal.readByteArray(var4, var3);
   }

   private static final byte[] readByteArray(int var0, int var1, int var2) {
      return getPolicyGroupData(var0, var1, var2);
   }

   private static final int readCount(int var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final byte[] getPolicyGroupData(int var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final byte[] getAggregatedPolicyGroupData(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final byte[] readWipeablePolicyData() {
      return ITPolicyInternal.readWipeablePolicyData();
   }

   private static final byte[] readPersistablePolicyData() {
      return NvStore.readData(4);
   }
}
