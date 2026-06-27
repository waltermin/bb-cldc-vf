package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.system.ForcedResetManager;
import net.rim.vm.Array;
import net.rim.vm.Process;

public final class CodeModuleManager {
   public static final int CMM_OK;
   public static final int CMM_OK_MODULE_OVERWRITTEN;
   public static final int CMM_HASH_INVALID;
   public static final int CMM_SIGNATURE_INVALID;
   public static final int CMM_MODULE_INVALID;
   public static final int CMM_MODULE_INCOMPATIBLE;
   public static final int CMM_OK_MODULE_MARKED_FOR_DELETION;
   public static final int CMM_MODULE_IN_USE;
   public static final int CMM_MODULE_IN_USE_BY_PERSISTENT_STORE;
   public static final int CMM_MODULE_REQUIRED;
   public static final int CMM_HANDLE_INVALID;
   public static final int CMM_OUT_OF_MEMORY;
   public static final int CMM_MODULE_EXCLUDED;
   public static final int CMM_TRANSACTION_INVALID;
   public static final int CMM_TRANSACTION_COMPLETE;
   public static final int CMM_TRANSACTION_RESET_REQUIRED;
   public static final int MODULE_FLAG_DELETE;
   public static final int MODULE_FLAG_OTA;
   public static final int MODULE_FLAG_INSTALLED;

   private CodeModuleManager() {
   }

   private static final native void setResetRequired();

   public static final native int[] getModuleHandles();

   public static final native int[] getModuleHandles(boolean var0);

   public static final int getModuleHandleForObject(Object var0) {
      if (var0 == null) {
         throw new Object();
      } else {
         return getModuleHandleForObjectImpl(var0);
      }
   }

   private static final native int getModuleHandleForObjectImpl(Object var0);

   public static final int getModuleHandleForClass(Class var0) {
      if (var0 == null) {
         throw new Object();
      } else {
         return getModuleHandleForClassImpl(var0);
      }
   }

   private static final native int getModuleHandleForClassImpl(Class var0);

   public static final native int getModuleHandle(String var0);

   public static final native int getModuleHandle(byte[] var0);

   public static final boolean deleteModule(int var0, boolean var1) {
      int var2 = deleteModuleEx(var0, var1);
      return var2 == 0 || var2 == 6;
   }

   public static final int deleteModuleEx(int var0, boolean var1) {
      ApplicationControl.assertCMMApiAllowed(true);
      int var2 = deleteModuleExImpl(var0, var1);
      if (var2 == 0) {
         ApplicationControl.removeModule(var0);
         return var2;
      }

      if (var2 == 6) {
         setResetRequired();
      }

      return var2;
   }

   private static final native int deleteModuleExImpl(int var0, boolean var1);

   public static final native int getModuleFlags(int var0);

   public static final String getModuleName(int var0) {
      return getModuleName(var0, 0);
   }

   public static final String getModuleVersion(int var0) {
      return getModuleVersion(var0, 0);
   }

   public static final native byte[] getModuleHash(int var0);

   public static final native boolean getModuleHash(int var0, byte[] var1);

   public static final native int getModuleSignerId(int var0, int var1);

   public static final native byte[] getModuleSignature(int var0, int var1);

   public static final String getModuleVendor(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String getModuleDescription(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String getModuleURL(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long getModuleTimestamp(int var0) {
      return (long)getModuleTimestamp0(var0) * 1000;
   }

   private static final native int getModuleTimestamp0(int var0);

   public static final native boolean isLibrary(int var0);

   public static final native boolean isMidlet(int var0);

   public static final native int getNumMidlets();

   public static final boolean isMidlet() {
      int var0 = Process.currentProcess().getModuleHandle();
      return isMidlet(var0);
   }

   private static final native boolean hasEntryPoint(int var0, int var1);

   public static final native int getModuleCodeSize(int var0);

   public static final native String getModuleName(int var0, int var1);

   public static final native String getModuleAliasName(int var0, int var1);

   public static final native String getModuleVersion(int var0, int var1);

   public static final int createNewModule(int var0) {
      ApplicationControl.assertCMMApiAllowed(true);
      return createNewModuleImpl(var0);
   }

   private static final native int createNewModuleImpl(int var0);

   public static final int createNewModule(int var0, byte[] var1, int var2) {
      ApplicationControl.assertCMMApiAllowed(true);
      return createNewModuleImpl(var0, var1, var2);
   }

   private static final native int createNewModuleImpl(int var0, byte[] var1, int var2);

   public static final boolean writeNewModule(int var0, byte[] var1, int var2, int var3) {
      return writeNewModule(var0, var2, var1, 0, var3);
   }

   public static final boolean writeNewModule(int var0, int var1, byte[] var2, int var3, int var4) {
      ApplicationControl.assertCMMApiAllowed(true);
      return writeNewModuleImpl(var0, var1, var2, var3, var4);
   }

   private static final native boolean writeNewModuleImpl(int var0, int var1, byte[] var2, int var3, int var4);

   public static final int saveNewModule(int var0) {
      return saveNewModule(var0, false);
   }

   public static final int saveNewModule(int var0, boolean var1) {
      return saveNewModule(var0, var1, 0);
   }

   public static final int saveNewModule(int var0, boolean var1, int var2) {
      ApplicationControl.assertCMMApiAllowed(true);
      int var3 = saveNewModuleImpl(var0, var1, var2);
      if (var3 == 6 && var2 == 0) {
         setResetRequired();
      }

      return var3;
   }

   private static final native int saveNewModuleImpl(int var0, boolean var1, int var2);

   public static final int deleteNewModule(int var0) {
      ApplicationControl.assertCMMApiAllowed(true);
      int var1 = deleteNewModuleImpl(var0);
      if (var1 == 6) {
         setResetRequired();
      }

      return var1;
   }

   private static final native int deleteNewModuleImpl(int var0);

   public static final int beginTransaction() {
      return beginTransactionImpl();
   }

   private static final native int beginTransactionImpl();

   public static final int endTransaction(int var0) {
      int var1 = endTransactionImpl(var0);
      if (var1 == 15) {
         setResetRequired();
      }

      return var1;
   }

   private static final native int endTransactionImpl(int var0);

   public static final int cancelTransaction(int var0) {
      return cancelTransactionImpl(var0);
   }

   private static final native int cancelTransactionImpl(int var0);

   public static final native boolean isResetRequired();

   public static final void promptForResetIfRequired() {
      ApplicationControl.assertCMMApiAllowed(true);
      if (isResetRequired()) {
         ForcedResetManager var0 = ForcedResetManager.getInstance();
         String var1 = CommonResource.getString(10127);
         var0.scheduleDeviceResetNoTimeout(var1, 3600000, false);
      }
   }

   public static final byte[] getModuleResourceData(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte[] getModuleLanguageData(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final native byte[] getModuleData(int var0, String var1);

   private static final int getAppCount(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final ApplicationDescriptor[] getApplicationDescriptors(int var0) {
      int var1 = getAppCount(var0);
      if (var1 <= 0) {
         return null;
      }

      ApplicationDescriptor[] var2 = new ApplicationDescriptor[var1];

      for (int var3 = 0; var3 < var1; var3++) {
         var2[var3] = new ApplicationDescriptor(var0, var3);
      }

      return var2;
   }

   static final byte[] getModuleData(int var0, String var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final String getModuleString(int var0, String var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native boolean verifySignature(int var0, int var1, byte[] var2);

   public static final CodeSigningKey getADCCodeSigningKey() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean setADCCodeSigningKey(CodeSigningKey var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native boolean isRRTSignaturePresent(int var0);

   public static final native boolean isRRTSignatureRequired(int var0);

   public static final native long getModuleDownloadTimestamp(int var0);

   public static final native int verifyApplicationControlModules();

   public static final native int getModuleTrailerFlags(int var0, int var1, int var2);

   public static final native byte[] getModuleTrailer(int var0, int var1, int var2);

   public static final byte[] makeTrailer(int var0, int var1, byte[] var2) {
      int var3 = var2.length;
      if ((var3 & 3) != 0) {
         throw new Object();
      }

      byte[] var4 = new byte[4 + var3];
      var4[0] = (byte)var0;
      var4[1] = (byte)var1;
      var4[2] = (byte)var3;
      var4[3] = (byte)(var3 >> 8);
      System.arraycopy(var2, 0, var4, 4, var3);
      return var4;
   }

   public static final byte[] appendTrailers(byte[] var0, byte[][][] var1) {
      int var2 = var1.length;
      int var3 = 0;

      for (int var4 = var2 - 1; var4 >= 0; var4--) {
         var3 += var1[var4].length;
      }

      int var8 = var0.length;
      Array.resize(var0, var8 + var3);

      for (int var5 = var2 - 1; var5 >= 0; var5--) {
         byte[][] var6 = var1[var5];
         int var7 = var6.length;
         System.arraycopy(var6, 0, var0, var8, var7);
         var8 += var7;
      }

      return var0;
   }

   public static final boolean deleteThirdPartyApplications() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
