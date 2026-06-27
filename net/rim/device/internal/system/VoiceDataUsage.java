package net.rim.device.internal.system;

import net.rim.vm.PersistentInteger;

public final class VoiceDataUsage {
   public static final int DATA_MAX;
   public static final int VOICE_MAX;
   public static final int DATA_COMMIT;
   public static final int VOICE_COMMIT;
   public static final int ILLEGAL_VALUE;
   private static final long DATA_PERSIST_KEY;
   private static final long VOICE_PERSIST_KEY;
   private static int _persistDataHandle;
   private static int _persistVoiceHandle;

   private VoiceDataUsage() {
   }

   public static final synchronized void addVoiceSeconds(int var0) {
      if (!itPolicyEnabled()) {
         if (!exceededVoiceLimit()) {
            addValue(var0, _persistVoiceHandle, 37, 120);
         }
      }
   }

   public static final synchronized void addDataBytes(int var0) {
      if (!itPolicyEnabled()) {
         if (!exceededDataLimit()) {
            addValue(var0, _persistDataHandle, 38, 65536);
         }
      }
   }

   public static final synchronized int getVoiceSeconds() {
      return getValue(_persistVoiceHandle, 37);
   }

   public static final synchronized int getDataBytes() {
      return getValue(_persistDataHandle, 38);
   }

   public static final synchronized boolean exceededVoiceLimit() {
      return getVoiceSeconds() > 3600;
   }

   public static final synchronized boolean exceededDataLimit() {
      return getDataBytes() > 256000;
   }

   public static final synchronized boolean itPolicyEnabled() {
      return ITPolicyInternal.isITPolicyEnabled();
   }

   public static final synchronized void reset() {
      PersistentInteger.set(_persistVoiceHandle, -1);
      PersistentInteger.set(_persistDataHandle, -1);
      NvStore.deleteData(37);
      NvStore.deleteData(38);
   }

   private static final int getValue(int var0, int var1) {
      int var2 = PersistentInteger.get(var0);
      if (var2 == -1) {
         var2 = getFromNvStore(var1);
         if (var2 == -1) {
            var2 = 0;
         }

         PersistentInteger.set(var0, var2);
      }

      return var2;
   }

   private static final int getFromNvStore(int var0) {
      return NvStore.readInt(var0, -1);
   }

   private static final void addValue(int var0, int var1, int var2, int var3) {
      int var4 = getValue(var1, var2);
      int var5 = var4 + var0;
      if (var5 / var3 != var4 / var3) {
         commitToNvStore(var2, var5);
      }

      PersistentInteger.set(var1, var5);
   }

   private static final boolean commitToNvStore(int var0, int var1) {
      return NvStore.writeInt(var0, var1);
   }
}
