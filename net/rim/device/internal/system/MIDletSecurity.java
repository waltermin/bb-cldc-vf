package net.rim.device.internal.system;

import net.rim.device.api.crypto.Digest;
import net.rim.device.api.crypto.MIDletSecurityCrypto;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.util.Arrays;
import net.rim.vm.Array;

public final class MIDletSecurity {
   public static final long MIDLET_UNTRUSTED_POLICY_GUID;
   private static final boolean ENABLE_HTTP_NEVER_DENY_KLUDGE;
   private static final byte DEFAULT_SETTING;
   private static final byte UNKNOWN_SETTING;
   private static final byte ALLOW_SETTING;
   private static final byte DISALLOW_SETTING;
   private static final int MAX_SIG_LEN;
   private static final int MAX_HASH_LEN;
   private static final String CONNECTOR_PREFIX;
   private static final byte[] DEFAULT_MIDLET_PERM_SETTING;
   private static byte[] _sessionCache;
   private static byte[] _installedCache;
   private static byte[] _domainCache;
   private static Object _userLock;
   private static boolean _isUntrusted;
   public static final String MIDLET_CERT_PREFIX;

   private MIDletSecurity() {
   }

   private static final void setAllConnectors(byte[] var0, byte var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte[] ensureDefaultUntrustedPolicyInstalled() {
      throw new RuntimeException("cod2jar: type check");
   }

   private static final void fillInUntrustedPolicy(byte[] var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static final int setDomainCache() {
      byte[] var0 = new byte[40];
      int var1 = MIDletSecurityCrypto.verifyMIDletTrailer(var0);
      switch (var1) {
         case -1:
         case 2:
            Arrays.fill(var0, (byte)0);
            break;
         case 0:
            byte[] var2 = new byte[40];

            for (int var3 = 0; var3 < 40; var3++) {
               byte var4 = 1;
               if (var3 < var0.length) {
                  byte var5 = var0[var3];
                  switch (var5) {
                     case -1:
                     case 5:
                        break;
                     case 0:
                     case 1:
                     case 2:
                     case 3:
                     case 4:
                     case 6:
                     default:
                        var4 = var5;
                  }
               }

               var2[var3] = var4;
            }

            var0 = var2;
            break;
         case 1:
         default:
            fillInUntrustedPolicy(var0);
            break;
         case 3:
            Arrays.fill(var0, (byte)6);
      }

      _domainCache = var0;
      return var1;
   }

   private static final int getDomainSetting(int var0) {
      if (_domainCache == null) {
         setDomainCache();
      }

      return var0 >= 0 && var0 < _domainCache.length ? _domainCache[var0] : 0;
   }

   private static final byte[] fetchStoredSettings() {
      byte[] var0 = MIDletSecurityCrypto.fetchStoredSettings();
      if (var0 == null) {
         var0 = new byte[40];
         Arrays.fill(var0, (byte)5);
      }

      _installedCache = var0;
      return var0;
   }

   private static final void updateStoredSettings() {
      MIDletSecurityCrypto.updateStoredSettings(_installedCache);
   }

   private static final synchronized byte[] getSessionCache() {
      if (_sessionCache == null) {
         byte[] var0 = fetchStoredSettings();
         _sessionCache = Arrays.copy(var0);
      }

      return _sessionCache;
   }

   private static final int askUser(int var0, int var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void updateForGroup(byte[] var0, int var1, int var2) {
      byte var3 = MIDletSecurityConstants.MIDletPermissionGroups[var1];

      for (int var4 = var0.length - 1; var4 >= 0; var4--) {
         byte var5 = MIDletSecurityConstants.MIDletPermissionGroups[var4];
         if (var5 == var3) {
            var0[var4] = (byte)var2;
         }
      }
   }

   public static final void checkMIDletCreation() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean checkUntrustedMIDlet() {
      if (CodeModuleManager.isMidlet()) {
         checkPermission(0, false, false, false, null);
         if (_isUntrusted) {
            return true;
         }
      }

      return false;
   }

   public static final void checkPermission(int var0) {
      checkPermission(var0, true, true, false, null);
   }

   public static final void checkPermission(int var0, String var1) {
      checkPermission(var0, true, true, false, var1);
   }

   public static final int checkPermissionNoPrompt(int var0) {
      int var1 = checkPermission(var0, false, false, false, null);
      switch (var1) {
         case 0:
            return 0;
         case 6:
            return 1;
         default:
            return -1;
      }
   }

   public static final int checkRealPermissionNoPrompt(int var0) {
      return checkPermission(var0, false, false, true, null);
   }

   private static final int checkPermission(int var0, boolean var1, boolean var2, boolean var3, String var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void setPermission(int var0, int var1, boolean var2) {
      byte[] var3 = getSessionCache();
      switch (var1) {
         case 3:
         default:
            updateForGroup(var3, var0, var2 ? 6 : 0);
            return;
         case 4:
            updateForGroup(var3, var0, var2 ? 6 : 0);
            if (var1 == 6) {
               updateForGroup(_installedCache, var0, var2 ? 6 : 0);
               updateStoredSettings();
            }
         case 2:
      }
   }

   public static final int findPermission(String var0) {
      String[] var1 = MIDletSecurityConstants.MIDletPermissions;

      for (int var2 = var1.length - 1; var2 >= 0; var2--) {
         if (var0.equals(var1[var2])) {
            return var2;
         }
      }

      return 40;
   }

   public static final int checkSymbolicPermission(String var0) {
      int var1 = findPermission(var0);
      return var1 >= 0 && var1 < 40 ? checkPermissionNoPrompt(var1) : 0;
   }

   public static final String getMIDletCertificateTag(int var0, int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final int checkJADCertChain(String[] var0) {
      return MIDletSecurityCrypto.checkJADCertChain(var0);
   }

   public static final Digest getMIDletSignatureDigest() {
      return MIDletSecurityCrypto.getMIDletSignatureDigest();
   }

   public static final int checkMIDletSignature(Digest var0, String var1, String[] var2, byte[] var3, byte[] var4) {
      return MIDletSecurityCrypto.checkMIDletSignature(var0, var1, var2, var3, var4);
   }

   public static final byte[] genMIDletTrailer(byte[] var0, byte[] var1) {
      return MIDletSecurityCrypto.signMIDletTrailer(var0, var1);
   }

   private static final boolean checkValidSettings(byte[] var0, int var1, int var2) {
      if (var2 != 40) {
         return false;
      }

      while (var2 > 0) {
         int var3 = var0[var1] & 255;
         if (var3 < 0 || var3 >= 7) {
            return false;
         }

         var1++;
         var2--;
      }

      return true;
   }

   public static final boolean checkDomainPolicy(byte[] var0) {
      int var1 = var0.length;
      return var1 != 61 ? false : checkValidSettings(var0, 21, var0[20] & 255);
   }

   public static final void createTrailerFromNvStoreDomain(int var0, byte[] var1, byte[] var2) {
      int var3 = var1[20] & 255;
      if ((var3 & 3) != 0) {
         throw new Object();
      }

      Array.resize(var2, var3 + 4 + 20);
      System.arraycopy(var1, 0, var2, 0, 20);
      var2[20] = (byte)var0;
      var2[21] = var1[20];
      var2[22] = 0;
      var2[23] = 0;
      System.arraycopy(var1, 21, var2, 24, var3);
   }

   public static final void copyPolicyFromMIDletTrailer(byte[] var0, byte[] var1) {
      int var2 = var0[21] & 255;
      System.arraycopy(var0, 24, var1, 0, Math.min(var2, var1.length));
   }

   private static final int calcBaseDomain(byte[] var0) {
      byte var1 = 0;
      switch (var0[20]) {
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 33:
         default:
            var1 = 24;
         case 24:
         case 31:
         case 32:
            return var1;
      }
   }

   public static final byte[] updateDesiredPolicy(byte[] var0, byte[] var1) {
      int var2 = var0.length;
      if (var2 > 40) {
         return null;
      }

      int var3 = calcBaseDomain(var1);
      if (var3 == 0) {
         return null;
      }

      int var4 = var1.length;
      if (var4 > var3 && var4 <= var3 + 40) {
         byte[] var5 = new byte[var3 + 40];
         Arrays.fill(var5, (byte)0);
         System.arraycopy(var1, 0, var5, 0, var3);

         for (int var6 = 0; var6 < 40; var6++) {
            byte var7 = var6 < var2 ? var0[var6] : 0;
            if (var7 != 0) {
               int var8 = var6 + var3;
               byte var9 = var8 < var4 ? var1[var8] : 0;
               byte var10;
               switch (var9) {
                  case 0:
                     if (var7 != 5) {
                        return new byte[]{(byte)var6};
                     }

                     var10 = 0;
                     break;
                  default:
                     var10 = var9;
               }

               var5[var3 + var6] = var10;
            }
         }

         return var5;
      } else {
         return null;
      }
   }

   private static final byte[] cleansePolicy(byte[] var0) {
      int var1 = var0.length;
      if (var1 > 0 && var1 <= 40 && (var1 & 3) == 0) {
         if (var1 < 40) {
            byte[] var2 = new byte[40];
            Arrays.fill(var2, (byte)0);
            System.arraycopy(var0, 0, var2, 0, var1);
            var0 = var2;
         }

         return var0;
      } else {
         throw new Object();
      }
   }

   public static final void installRootDomain(byte[] var0, byte[] var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean prepareForRootDomainInstallation() {
      throw new RuntimeException("cod2jar: array creation");
   }

   public static final byte[] createPolicy() {
      byte[] var0 = new byte[40];
      Arrays.fill(var0, (byte)0);
      var0[0] = 6;
      return var0;
   }

   public static final int adjustPolicy(byte[] var0, String var1, boolean var2) {
      int var3 = findPermission(var1);
      if (var3 != 40) {
         if (var2) {
            if (var0[var3] != 6) {
               var0[var3] = 5;
               return var3;
            }
         } else {
            var0[var3] = 6;
         }
      }

      return var3;
   }
}
