package net.rim.vm;

public final class Memory implements VMConstants {
   private Memory() {
   }

   public static final native void startHibernation();

   public static final native void getRAMStats(MemStats var0);

   public static final native void getObjectStats(MemStats var0);

   public static final native void getTransientStats(MemStats var0);

   public static final native void getPersistentStats(MemStats var0);

   public static final native void getCodeStats(MemStats var0);

   public static final native int getFlashFree();

   public static final native int getFlashReady();

   public static final native int getFlashTotal();

   public static final native int getRAMReady();

   public static final void getFlashStats(MemStats var0) {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }

   public static final native void RAMRecover();

   public static final native void supressHourglass(boolean var0);

   public static final native void thoroughGC();

   public static final native void emergencyGC();

   public static final native void persistentGC();

   public static final native void suggestThoroughGC();

   public static final native void commitOnNextIdle();

   public static final native void secureThoroughGC();

   public static final native void scrubUnusedMemory();

   public static final native void scheduleIdleGC();

   public static final native void fullGC();

   public static final native void quickGC();

   public static final native void heapCompact();

   public static final native String stringIntern(String var0);

   public static final native boolean isStringAllBytes(String var0);

   public static final native boolean createGroup(Object var0);

   public static final native Object expandGroup(Object var0);

   public static final native boolean isObjectInGroup(Object var0);

   public static final native Object getGroupedHandle(Object var0);

   public static final native int getFlashNeeded(boolean var0);

   public static final native int getHandlesNeeded(boolean var0);

   public static final native int getHandlesRecovered();

   public static final native int getRecoverableCalls();

   public static final native boolean stopRecoveringFlash(boolean var0);

   public static final native int objectFlashSize(Object var0);

   public static final native int markAsRecoverable(Object var0);

   public static final native void moveToFlash(Object var0);

   public static final native boolean moveToRAM(Object var0);

   public static final native int objectSize(Object var0);

   public static final native int classSize(Object var0);

   public static final native int getFlashSlack();

   public static final native int objectToInt(Object var0);

   public static final native Object intToObject(int var0);

   public static final native boolean recoverFlash(int var0);

   public static final native boolean isRecoveringFlash();

   public static final native int sizeof(Class var0);

   public static final native int sizeof(int var0);

   public static final native int sizeofArray(int var0, int var1);

   public static final native void setSecureOldObjects(boolean var0);

   public static final native boolean getSecureOldObjects();

   public static final native void setPlaintext(Object var0);

   public static final native void clearPlaintext(Object var0);

   public static final native boolean anyPlaintext();

   public static final native int numPlaintext();

   public static final native int numPlaintextSpecial();

   public static final native boolean anyPersistentPlaintext();

   public static final native int numPersistentPlaintext();

   public static final native boolean isPlaintext(Object var0);

   public static final native void resetPlaintext();

   public static final native void resetLastIdle();

   public static final void maximizeContiguousRAM() {
      maximizeContiguousRAM(1048576);
   }

   public static final void maximizeContiguousRAM(int var0) {
      if (var0 >= 0 && var0 <= 1073741823) {
         var0 += var0 >> 2;
         MemStats var1 = new MemStats();
         getRAMStats(var1);
         if (var1.getFree() < var0) {
            RAMRecover();
         }

         Thread.yield();
      }
   }

   public static final native int getMaxGroupSize();

   public static final native int getMaxNumCodeSections();

   public static final native byte[] allocRAMOnlyBytes(int var0);

   public static final byte[] copyToRAMOnlyBytes(byte[] var0) {
      return copyToRAMOnlyBytes(var0, 0, var0.length);
   }

   public static final byte[] copyToRAMOnlyBytes(byte[] var0, int var1, int var2) {
      setPlaintext(var0);
      byte[] var3 = allocRAMOnlyBytes(var2);
      if (var3 != null) {
         System.arraycopy(var0, var1, var3, 0, var2);
      }

      return var3;
   }

   public static final native Object[] getAllInstances(Class var0);

   public static final native boolean setReservedSpaceMode(boolean var0);
}
