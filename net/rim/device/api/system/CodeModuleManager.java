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

   public static final int getModuleHandleForObject(Object obj) {
      if (obj == null) {
         throw new NullPointerException();
      } else {
         return getModuleHandleForObjectImpl(obj);
      }
   }

   private static final native int getModuleHandleForObjectImpl(Object var0);

   public static final int getModuleHandleForClass(Class clazz) {
      if (clazz == null) {
         throw new NullPointerException();
      } else {
         return getModuleHandleForClassImpl(clazz);
      }
   }

   private static final native int getModuleHandleForClassImpl(Class var0);

   public static final native int getModuleHandle(String var0);

   public static final native int getModuleHandle(byte[] var0);

   public static final boolean deleteModule(int moduleHandle, boolean force) {
      int rc = deleteModuleEx(moduleHandle, force);
      return rc == 0 || rc == 6;
   }

   public static final int deleteModuleEx(int moduleHandle, boolean force) {
      ApplicationControl.assertCMMApiAllowed(true);
      int rc = deleteModuleExImpl(moduleHandle, force);
      if (rc == 0) {
         ApplicationControl.removeModule(moduleHandle);
         return rc;
      }

      if (rc == 6) {
         setResetRequired();
      }

      return rc;
   }

   private static final native int deleteModuleExImpl(int var0, boolean var1);

   public static final native int getModuleFlags(int var0);

   public static final String getModuleName(int moduleHandle) {
      return getModuleName(moduleHandle, 0);
   }

   public static final String getModuleVersion(int moduleHandle) {
      return getModuleVersion(moduleHandle, 0);
   }

   public static final native byte[] getModuleHash(int var0);

   public static final native boolean getModuleHash(int var0, byte[] var1);

   public static final native int getModuleSignerId(int var0, int var1);

   public static final native byte[] getModuleSignature(int var0, int var1);

   public static final String getModuleVendor(int moduleHandle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String getModuleDescription(int moduleHandle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String getModuleURL(int moduleHandle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long getModuleTimestamp(int moduleHandle) {
      return (long)getModuleTimestamp0(moduleHandle) * 1000;
   }

   private static final native int getModuleTimestamp0(int var0);

   public static final native boolean isLibrary(int var0);

   public static final native boolean isMidlet(int var0);

   public static final native int getNumMidlets();

   public static final boolean isMidlet() {
      int handle = Process.currentProcess().getModuleHandle();
      return isMidlet(handle);
   }

   private static final native boolean hasEntryPoint(int var0, int var1);

   public static final native int getModuleCodeSize(int var0);

   public static final native String getModuleName(int var0, int var1);

   public static final native String getModuleAliasName(int var0, int var1);

   public static final native String getModuleVersion(int var0, int var1);

   public static final int createNewModule(int length) {
      ApplicationControl.assertCMMApiAllowed(true);
      return createNewModuleImpl(length);
   }

   private static final native int createNewModuleImpl(int var0);

   public static final int createNewModule(int totalLength, byte[] data, int length) {
      ApplicationControl.assertCMMApiAllowed(true);
      return createNewModuleImpl(totalLength, data, length);
   }

   private static final native int createNewModuleImpl(int var0, byte[] var1, int var2);

   public static final boolean writeNewModule(int newModuleHandle, byte[] data, int newModuleOffset, int length) {
      return writeNewModule(newModuleHandle, newModuleOffset, data, 0, length);
   }

   public static final boolean writeNewModule(int newModuleHandle, int newModuleOffset, byte[] data, int offset, int length) {
      ApplicationControl.assertCMMApiAllowed(true);
      return writeNewModuleImpl(newModuleHandle, newModuleOffset, data, offset, length);
   }

   private static final native boolean writeNewModuleImpl(int var0, int var1, byte[] var2, int var3, int var4);

   public static final int saveNewModule(int newModuleHandle) {
      return saveNewModule(newModuleHandle, false);
   }

   public static final int saveNewModule(int newModuleHandle, boolean forceOverwrite) {
      return saveNewModule(newModuleHandle, forceOverwrite, 0);
   }

   public static final int saveNewModule(int newModuleHandle, boolean forceOverwrite, int transactionHandle) {
      ApplicationControl.assertCMMApiAllowed(true);
      int rc = saveNewModuleImpl(newModuleHandle, forceOverwrite, transactionHandle);
      if (rc == 6 && transactionHandle == 0) {
         setResetRequired();
      }

      return rc;
   }

   private static final native int saveNewModuleImpl(int var0, boolean var1, int var2);

   public static final int deleteNewModule(int newModuleHandle) {
      ApplicationControl.assertCMMApiAllowed(true);
      int rc = deleteNewModuleImpl(newModuleHandle);
      if (rc == 6) {
         setResetRequired();
      }

      return rc;
   }

   private static final native int deleteNewModuleImpl(int var0);

   public static final int beginTransaction() {
      return beginTransactionImpl();
   }

   private static final native int beginTransactionImpl();

   public static final int endTransaction(int transactionHandle) {
      int rc = endTransactionImpl(transactionHandle);
      if (rc == 15) {
         setResetRequired();
      }

      return rc;
   }

   private static final native int endTransactionImpl(int var0);

   public static final int cancelTransaction(int transactionHandle) {
      return cancelTransactionImpl(transactionHandle);
   }

   private static final native int cancelTransactionImpl(int var0);

   public static final native boolean isResetRequired();

   public static final void promptForResetIfRequired() {
      ApplicationControl.assertCMMApiAllowed(true);
      if (isResetRequired()) {
         ForcedResetManager frm = ForcedResetManager.getInstance();
         String text = CommonResource.getString(10127);
         frm.scheduleDeviceResetNoTimeout(text, 3600000, false);
      }
   }

   public static final byte[] getModuleResourceData(int moduleHandle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte[] getModuleLanguageData(int moduleHandle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final native byte[] getModuleData(int var0, String var1);

   private static final int getAppCount(int moduleHandle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final ApplicationDescriptor[] getApplicationDescriptors(int moduleHandle) {
      int numApps = getAppCount(moduleHandle);
      if (numApps <= 0) {
         return null;
      }

      ApplicationDescriptor[] descriptors = new ApplicationDescriptor[numApps];

      for (int i = 0; i < numApps; i++) {
         descriptors[i] = new ApplicationDescriptor(moduleHandle, i);
      }

      return descriptors;
   }

   static final byte[] getModuleData(int moduleHandle, String id, int index) {
      byte[] data = getModuleData(moduleHandle, id);
      if (data == null) {
         return null;
      }

      int length = 0;
      int offset = 0;

      try {
         for (int i = 0; i <= index; i++) {
            offset += length;
            length = (data[offset++] & 255) << 8;
            length += data[offset++] & 255;
         }

         if (length == 0) {
            return null;
         }

         System.arraycopy(data, offset, data, 0, length);
         Array.resize(data, length);
         return data;
      } catch (ArrayIndexOutOfBoundsException ex) {
         return null;
      }
   }

   static final String getModuleString(int moduleHandle, String id, int index) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final native boolean verifySignature(int var0, int var1, byte[] var2);

   public static final CodeSigningKey getADCCodeSigningKey() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean setADCCodeSigningKey(CodeSigningKey newKey) {
      throw new RuntimeException("cod2jar: array init");
   }

   public static final native boolean isRRTSignaturePresent(int var0);

   public static final native boolean isRRTSignatureRequired(int var0);

   public static final native long getModuleDownloadTimestamp(int var0);

   public static final native int verifyApplicationControlModules();

   public static final native int getModuleTrailerFlags(int var0, int var1, int var2);

   public static final native byte[] getModuleTrailer(int var0, int var1, int var2);

   public static final byte[] makeTrailer(int id, int flags, byte[] data) {
      int len = data.length;
      if ((len & 3) != 0) {
         throw new IllegalArgumentException();
      }

      byte[] trailer = new byte[4 + len];
      trailer[0] = (byte)id;
      trailer[1] = (byte)flags;
      trailer[2] = (byte)len;
      trailer[3] = (byte)(len >> 8);
      System.arraycopy(data, 0, trailer, 4, len);
      return trailer;
   }

   public static final byte[] appendTrailers(byte[] codfile, byte[][][] trailers) {
      int trailerNum = trailers.length;
      int trailerSize = 0;

      for (int i = trailerNum - 1; i >= 0; i--) {
         trailerSize += trailers[i].length;
      }

      int codfileEnd = codfile.length;
      Array.resize(codfile, codfileEnd + trailerSize);

      for (int i = trailerNum - 1; i >= 0; i--) {
         byte[] t = (byte[])trailers[i];
         int len = t.length;
         System.arraycopy(t, 0, codfile, codfileEnd, len);
         codfileEnd += len;
      }

      return codfile;
   }

   public static final boolean deleteThirdPartyApplications() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
