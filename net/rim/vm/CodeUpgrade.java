package net.rim.vm;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.IntHashtable;

public class CodeUpgrade {
   private static final long MESSAGE_GUID;

   private static IntHashtable getMessages() {
      ApplicationRegistry appRegistry = ApplicationRegistry.getApplicationRegistry();
      synchronized (appRegistry) {
         IntHashtable ht = (IntHashtable)appRegistry.get(-4292848197358216020L);
         if (ht == null) {
            ht = (IntHashtable)(new Object());
            appRegistry.put(-4292848197358216020L, ht);
         }

         return ht;
      }
   }

   public static void setMessage(int id, byte[] msg) {
      getMessages().put(id, msg);
   }

   private static native void setMessage0(int var0, byte[] var1);

   public static void addPatch(long guid, String name, byte[] patch, int length) {
      FlashOutputStream newCode = new FlashOutputStream(guid, true);
      newCode.write(name.getBytes());
      newCode.write(0);
      newCode.write(length >>> 24 & 0xFF);
      newCode.write(length >>> 16 & 0xFF);
      newCode.write(length >>> 8 & 0xFF);
      newCode.write(length >>> 0 & 0xFF);
      newCode.write(patch, 0, length);
      newCode.close();
   }

   public static void start(boolean undo) {
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

   public static long getOSGUID(String str) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static native long getOSGUID(byte[] var0);

   public static native long getCodFileGUID();

   public static native long getNewPatchListGUID();

   public static native long getOldDeviceStateGUID();

   public static native String[] getOSSectionNames();

   public static native byte[] getOSSectionHash(String var0);

   private static void putBE(byte[] b, int at, int i) {
      b[at] = (byte)(i >> 8);
      b[at + 1] = (byte)i;
   }

   public static void setOTABitmap(int id, String text) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
