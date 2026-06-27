package net.rim.device.api.crypto;

import java.util.Hashtable;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.internal.system.CodeStore;
import net.rim.device.internal.system.MIDletSecurity;
import net.rim.device.internal.util.ByteArray;

public class MIDletSecurityCrypto {
   public static final int CMS_OK;
   public static final int CMS_BAD_SIG;
   public static final int CMS_BAD_CHAIN;
   public static final int CMS_BAD_ROOT;
   public static final int CMS_BAD_POLICY;
   public static final int CMS_BAD_CRYPTO;
   public static final int CMS_BAD_MIDLET;
   protected static String CURVE_NAME;
   protected static final int PRIVATE_KEY_LENGTH;
   protected static final int PUBLIC_KEY_LENGTH;
   protected static final int SIGNATURE_R;
   protected static final int SIGNATURE_S;
   protected static final int SIGNER_ID;
   protected static final int VERSION_OFFSET;
   protected static final int CODE_SIZE_OFFSET;
   protected static final int DATA_SIZE_OFFSET;
   protected static final int CODE_OFFSET;
   protected static final int TRAILER_TYPE;
   protected static final int TRAILER_SIZE;
   protected static final int VERSION_NUMBER;
   private static final long PERSISTENT_STORE_KEY;
   private static PersistentObject _persist;
   private static Hashtable _hashtable;
   private static int _moduleHandle;
   private static byte[] _moduleHash;
   private static ByteArray _moduleHashByteArray;
   private static MIDletSecurityCrypto _impl;
   public static final int VMT_OK;
   public static final int VMT_UNTRUSTED;
   public static final int VMT_VIOLATION;
   public static final int VMT_SIGNED_BY_RIM;

   protected MIDletSecurityCrypto() {
   }

   public static final int checkMIDletSignature(Digest var0, String var1, String[] var2, byte[] var3, byte[] var4) {
      return _impl == null ? 5 : _impl.checkMIDletSignatureImpl(var0, var1, var2, var3, var4);
   }

   protected int checkMIDletSignatureImpl(Digest var1, String var2, String[] var3, byte[] var4, byte[] var5) {
      throw null;
   }

   public static final Digest getMIDletSignatureDigest() {
      return new SHA1Digest();
   }

   public static final byte[] signMIDletTrailer(byte[] var0, byte[] var1) {
      return _impl == null ? null : _impl.signMIDletTrailerImpl(var0, var1);
   }

   protected byte[] signMIDletTrailerImpl(byte[] var1, byte[] var2) {
      throw null;
   }

   public static final int checkJADCertChain(String[] var0) {
      return _impl == null ? 5 : _impl.checkJADCertChainImpl(var0);
   }

   protected int checkJADCertChainImpl(String[] var1) {
      throw null;
   }

   public static final int verifyMIDletTrailer(byte[] var0) {
      if (_moduleHandle == 0) {
         return 2;
      }

      if (!ControlledAccess.verifyCodeModuleSignature(_moduleHandle, 51) && !ControlledAccess.verifyCodeModuleSignature(_moduleHandle, 4342354)) {
         byte[] var1 = CodeModuleManager.getModuleSignature(_moduleHandle, 1346652493);
         byte[] var2 = CodeModuleManager.getModuleTrailer(_moduleHandle, 2, 0);
         if (var1 == null && var2 == null) {
            return 1;
         }

         if (!verify(_moduleHash, var2, var1)) {
            return 2;
         }

         if (var0 != null) {
            MIDletSecurity.copyPolicyFromMIDletTrailer(var2, var0);
         }

         return 0;
      } else {
         return 3;
      }
   }

   private static boolean verify(byte[] var0, byte[] var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] fetchStoredSettings() {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void updateStoredSettings(byte[] var0) {
      _hashtable.put(_moduleHashByteArray, var0);
      _persist.commit();
   }

   public static final boolean checkDRMTrailer() {
      return CodeStore.checkDRMTrailer(_moduleHandle);
   }
}
