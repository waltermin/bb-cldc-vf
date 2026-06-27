package net.rim.vm;

import net.rim.device.api.util.IntHashtable;

public class CodeUpgrade {
   private static final long MESSAGE_GUID;

   private static IntHashtable getMessages() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void setMessage(int var0, byte[] var1) {
      getMessages().put(var0, var1);
   }

   private static native void setMessage0(int var0, byte[] var1);

   public static void addPatch(long var0, String var2, byte[] var3, int var4) {
      FlashOutputStream var5 = new FlashOutputStream(var0, true);
      var5.write(var2.getBytes());
      var5.write(0);
      var5.write(var4 >>> 24 & 0xFF);
      var5.write(var4 >>> 16 & 0xFF);
      var5.write(var4 >>> 8 & 0xFF);
      var5.write(var4 >>> 0 & 0xFF);
      var5.write(var3, 0, var4);
      var5.close();
   }

   public static void start(boolean var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static native void start0(boolean var0);

   public static boolean validatePatches() {
      Memory.maximizeContiguousRAM();
      return validateAllPatches();
   }

   static native boolean validateAllPatches();

   public static int getFlashRequired() {
      Memory.maximizeContiguousRAM();
      return getFlashRequired0();
   }

   static native int getFlashRequired0();

   public static native void eraseAll();

   public static native boolean isQuickFix();

   public static native boolean getModuleHash(int var0, byte[] var1);

   public static native String[] getPatchNames();

   public static native String[] getPatchNames(long var0);

   public static long getOSGUID(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static native long getOSGUID(byte[] var0);

   public static native long getCodFileGUID();

   public static native long getNewPatchListGUID();

   public static native long getOldDeviceStateGUID();

   public static native String[] getOSSectionNames();

   public static native byte[] getOSSectionHash(String var0);

   private static void putBE(byte[] var0, int var1, int var2) {
      var0[var1] = (byte)(var2 >> 8);
      var0[var1 + 1] = (byte)var2;
   }

   public static void setOTABitmap(int var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
