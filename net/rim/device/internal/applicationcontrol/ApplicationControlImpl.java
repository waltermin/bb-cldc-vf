package net.rim.device.internal.applicationcontrol;

import net.rim.device.api.applicationcontrol.ApplicationPermissions;
import net.rim.device.api.i18n.MessageFormat;
import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.IntVector;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.security.component.PermissionDialog;
import net.rim.vm.Array;
import net.rim.vm.Process;
import net.rim.vm.ThreadSpecificData;
import net.rim.vm.TraceBack;

final class ApplicationControlImpl {
   private static final long FLAG;
   private static final long GUID;
   private static final long PERM_KEY;
   private static final long USER_PERMS_KEY;
   private static final long STACK_HASHES_KEY;
   private static final int ALLOW;
   private static final int PROMPT;
   private static final int DENY;
   private static ApplicationRegistry _ar;
   private static int _processModuleHandle;
   private static byte[] _processModuleHash;
   private static UserPermissions _userPermissions;
   private static StackTracePermissions _responseStackPermissions;
   private static SystemPermissions _permissions;
   private static IntHashtable _transactions;
   private static boolean _isDesktopVM;

   static final void removeModule(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final boolean reloadModulePermissions() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final boolean reloadDefaultModulePermissions() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final boolean applyDefaultPermissions() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void checkInitialization() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final boolean addModule(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void addTransactionModule(int var0, int var1) {
      if (_transactions == null) {
         _transactions = (IntHashtable)(new Object());
      }

      Object var2 = _transactions.get(var1);
      if (var2 == null) {
         var2 = new Object();
      }

      ((IntVector)var2).addElement(var0);
      _transactions.put(var1, var2);
   }

   static final void endTransactionAddModules(int var0) {
      if (_transactions != null) {
         Object var1 = _transactions.get(var0);
         if (var1 == null) {
            throw new Object();
         }

         ((IntVector)var1).trimToSize();
         int[] var2 = ((IntVector)var1).getArray();

         for (int var3 = var2.length - 1; var3 >= 0; var3--) {
            addModule(var2[var3]);
         }
      }
   }

   static final void removeUserSetting(int var0) {
      UserSetting var1 = _userPermissions.getSetting(var0);
      if (var0 != 0 && var1 != null) {
         if (isModuleSettingPresent(var1.getHash())) {
            doSetModulePermissions(var0, getModulePermissions(var0, var1.getHash()));
         } else {
            doSetModulePermissions(var0, _userPermissions.getDefaultSetting().getPermissions());
         }

         _userPermissions.removeSetting(var0, var1);
      }
   }

   static final void removeUserSettings(boolean var0) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   static final int getPolicyPermission(int var0, boolean var1) {
      return getStackPolicyPermission(var0, var1);
   }

   static final int getPolicyPermissionTernary(int var0, int var1, boolean var2) {
      int var3 = getStackPolicyPermission(var0, var2);
      if (var3 == 0) {
         return isStackPromptPolicyPermission(var1, var2) ? 2 : 0;
      } else {
         return 1;
      }
   }

   static final int getPolicyPermission(String var0, int var1, boolean var2) {
      if (getPolicyPermission(var1, true) == 0) {
         return 0;
      }

      switch (var1) {
         case 12:
            byte var3 = 3;
            return isConnectionAllowed(var0, var3, true);
         default:
            throw new Object();
      }
   }

   static final int getPolicyPermissionTernary(String var0, int var1, int var2, boolean var3) {
      int var4 = getPolicyPermissionTernary(var1, var2, var3);
      if (var4 != 1) {
         return var4 == 0 ? var4 : 2;
      }

      byte var5;
      switch (var1) {
         case 3:
            var5 = 1;
            break;
         case 5:
            var5 = 2;
            break;
         default:
            throw new Object();
      }

      return isConnectionAllowed(var0, var5, true);
   }

   private static final long getPolicyPermissionIgnoringDefaults(int var0, byte[] var1, int var2) {
      boolean var5 = isModuleSettingPresent(var1);
      return var5 ? getModulePermission(var0, var1, var2) : Long.MIN_VALUE >>> var2 & _permissions.getPermittedPermissions();
   }

   private static final int getStackPolicyPermission(int var0, boolean var1) {
      if (var1 && _processModuleHandle != 0 && !isSignedWithRRI(_processModuleHandle) && isSettingPresent(_processModuleHash)) {
         long var2 = getPolicyPermissionIgnoringDefaults(_processModuleHandle, _processModuleHash, var0);
         if (var2 == 0) {
            return 1;
         }
      }

      int[] var8 = TraceBack.getCallingModules();
      byte[] var3 = new byte[20];

      for (int var4 = var8.length - 1; var4 >= 0; var4--) {
         int var5 = var8[var4];
         if (!isSignedWithRRI(var5)) {
            if (!CodeModuleManager.getModuleHash(var5, var3)) {
               return 1;
            }

            long var6 = getPolicyPermissionIgnoringDefaults(var5, var3, var0);
            if (var6 == 0) {
               return 1;
            }
         }
      }

      return 0;
   }

   private static final boolean isStackPromptPolicyPermission(int var0, boolean var1) {
      if (var1 && _processModuleHandle != 0 && !isSignedWithRRI(_processModuleHandle) && isSettingPresent(_processModuleHash)) {
         long var2 = getPolicyPermissionIgnoringDefaults(_processModuleHandle, _processModuleHash, var0);
         if (var2 != 0) {
            return true;
         }
      }

      int[] var8 = TraceBack.getCallingModules();
      byte[] var3 = new byte[20];

      for (int var4 = var8.length - 1; var4 >= 0; var4--) {
         int var5 = var8[var4];
         if (!isSignedWithRRI(var5)) {
            if (!CodeModuleManager.getModuleHash(var5, var3)) {
               return true;
            }

            long var6 = getPolicyPermissionIgnoringDefaults(var5, var3, var0);
            if (var6 != 0) {
               return true;
            }
         }
      }

      return false;
   }

   static final long getPermittedPermission(int var0, byte[] var1, int var2) {
      if (var0 == 0 || !isModuleSettingPresent(var1)) {
         return _permissions.getPermittedPermissions() & Long.MIN_VALUE >>> var2;
      } else {
         return isPermitted(var1, var2) ? Long.MIN_VALUE >>> var2 : 0;
      }
   }

   static final long getUserPermission(int var0, int var1) {
      UserSetting var2 = _userPermissions.getSetting(var0);
      return getUserPermission(var2, var1);
   }

   public static final long getDefaultUserPermission(int var0) {
      UserSetting var1 = _userPermissions.getDefaultSetting();
      return getUserPermission(var1, var0);
   }

   private static final long getUserPermission(UserSetting var0, int var1) {
      if (var0 == null) {
         var0 = _userPermissions.getDefaultSetting();
         if (var0 == null) {
            return _permissions.getDefaultPermissions() & Long.MIN_VALUE >>> var1;
         }
      }

      return var0.getPermissions() & Long.MIN_VALUE >>> var1;
   }

   static final boolean isUserSettingPresent(int var0) {
      return _userPermissions.getSetting(var0) != null;
   }

   static final boolean isUserPermissionSet(int var0, int var1) {
      UserSetting var2 = _userPermissions.getSetting(var0);
      return isUserPermissionSet(var2, var1);
   }

   private static final boolean isUserPermissionSet(UserSetting var0, int var1) {
      return var0 == null ? false : (var0.getIsSet() & Long.MIN_VALUE >>> var1) != 0;
   }

   static final boolean differsFromUserDefaults(int var0) {
      UserSetting var1 = _userPermissions.getSetting(var0);
      if (var1 != null) {
         UserSetting var2 = _userPermissions.getDefaultSetting();
         return (var2.getPermissions() & var1.getIsSet()) != (var1.getPermissions() & var1.getIsSet());
      } else {
         return false;
      }
   }

   static final boolean isSignedWithRRI(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final boolean isAllowed(int var0, boolean var1) {
      return isAllowed(var0, var1, null);
   }

   static final boolean isAllowed(int var0, boolean var1, int[] var2) {
      checkInitialization();
      boolean var3 = false;
      int[] var4 = TraceBack.getCallingModules();
      Thread var5 = Thread.currentThread();
      ApplicationControlImpl$CachedPermissions var6 = (ApplicationControlImpl$CachedPermissions)ThreadSpecificData.get(var5, 1);
      if (var6 != null && var6._check == _permissions.getCheck() && var6.equals(var4)) {
         if ((var6._perms & Long.MIN_VALUE >>> var0) != 0) {
            return true;
         }

         var3 = true;
      }

      boolean var7 = doGetCallingModulesPermission(Long.MIN_VALUE >>> var0, 0, 1, _processModuleHandle == 0 ? false : var1, _processModuleHandle, var2) == 2;
      if (!var7) {
         logDenial(_processModuleHandle, var0);
      }

      if (var6 == null || !var3) {
         var6 = new ApplicationControlImpl$CachedPermissions(var4);
         ThreadSpecificData.set(var5, 1, var6);
      }

      long var8 = var7 ? Long.MIN_VALUE >>> var0 | var6._perms : (Long.MIN_VALUE >>> var0 ^ -1) & var6._perms;
      var6.setCachedPerms(var8, _permissions.getCheck());
      return var7;
   }

   static final int isAllowedTernary(int var0, int var1, boolean var2) {
      return isAllowedTernary(var0, var1, var2, null);
   }

   static final int isAllowedTernary(int var0, int var1, boolean var2, int[] var3) {
      checkInitialization();
      int var4 = doGetCallingModulesPermission(
         Long.MIN_VALUE >>> var0, Long.MIN_VALUE >>> var1, 1, _processModuleHandle == 0 ? false : var2, _processModuleHandle, var3
      );
      if (var4 != 2) {
         if (var4 == 1) {
            return 2;
         }

         logDenial(_processModuleHandle, var0);
         return 1;
      } else {
         return 0;
      }
   }

   static final int permissionMaskToTriState(long var0, int var2, int var3) {
      if ((var0 & Long.MIN_VALUE >>> var2) != 0) {
         return (var0 & Long.MIN_VALUE >>> var3) != 0 ? 2 : 0;
      } else {
         return 1;
      }
   }

   static final int isConnectionAllowed(String var0, byte var1, boolean var2) {
      return isConnectionAllowed(var0, var1, var2, null);
   }

   static final int isConnectionAllowed(String var0, byte var1, boolean var2, int[] var3) {
      boolean var4 = false;
      if (var2 && _processModuleHandle != 0 && !isSignedWithRRI(_processModuleHandle)) {
         int var5 = 1;
         if (isSettingPresent(_processModuleHash)) {
            var5 = isDomainAllowed(_processModuleHash, var0, var1);
         }

         if (var5 == 1) {
            return 1;
         }

         var4 |= var5 == 2;
      }

      int[] var11 = TraceBack.getCallingModules();
      byte[] var6 = new byte[20];

      for (int var7 = var11.length - 1; var7 >= 0; var7--) {
         int var8 = var11[var7];
         if (!isSignedWithRRI(var8)) {
            if (!CodeModuleManager.getModuleHash(var8, var6)) {
               return 1;
            }

            int var9 = 1;
            if (isSettingPresent(var6)) {
               var9 = isDomainAllowed(var6, var0, var1);
            }

            if (var9 == 1) {
               return 1;
            }

            var4 |= var9 == 2;
         }
      }

      int var12 = var3 == null ? 0 : var3.length;

      for (int var13 = var12 - 1; var13 >= 0; var13--) {
         int var14 = var3[var13];
         if (!isSignedWithRRI(var14)) {
            if (!CodeModuleManager.getModuleHash(var14, var6)) {
               return 1;
            }

            int var10 = 1;
            if (isSettingPresent(var6)) {
               var10 = isDomainAllowed(var6, var0, var1);
            }

            if (var10 == 1) {
               return 1;
            }

            var4 |= var10 == 2;
         }
      }

      return var4 ? 2 : 0;
   }

   static final boolean isFlagSetBoolean(byte[] var0, int var1) {
      return isFlagSet(var0, var1);
   }

   static final int isFlagSetTernary(byte[] var0, int var1, int var2) {
      if (isFlagSet(var0, var1)) {
         return isFlagSet(var0, var2) ? 2 : 0;
      } else {
         return 1;
      }
   }

   static final void assertIPCAllowed(boolean var0) {
      if (!_isDesktopVM) {
         if (!ApplicationControl.isIPCAllowed(var0)) {
            throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[2]);
         }
      }
   }

   static final boolean isThemeDataAllowed(int var0) {
      boolean var1 = checkModulePermissions(var0, 19) == 0;
      if (var1 && !isSignedWithRRI(var0)) {
         setGrantedPermissions(17592186044416L);
      }

      return var1;
   }

   static final boolean isAuthenticatorApiAllowed(int var0) {
      boolean var1 = checkModulePermissions(var0, 20, -1) == 0;
      if (var1) {
         setGrantedPermissions(8796093022208L);
      }

      return var1;
   }

   static final int checkModulePermissions(int var0, int var1) {
      return checkModulePermissions(var0, var1, -1, 63);
   }

   static final int checkModulePermissionsIgnoringBypass(int var0, int var1) {
      return checkModulePermissions(var0, var1, -1, 0);
   }

   static final int checkModulePermissions(int var0, int var1, int var2) {
      return checkModulePermissions(var0, var1, var2, 63);
   }

   private static final int checkModulePermissions(int var0, int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final boolean setRestrictiveDefaultPermission() {
      return setModuleUserPermission(ApplicationControlConstants.EMPTY_HASH, 0, 2229815737965346816L);
   }

   static final boolean setDefaultPermission() {
      return setModuleUserPermission(ApplicationControlConstants.EMPTY_HASH, 0, _userPermissions.getDefaultSetting().getPermissions());
   }

   static final boolean setPermissiveDefaultPermission() {
      return setModuleUserPermission(ApplicationControlConstants.EMPTY_HASH, 0, 3869672972855803904L);
   }

   static final boolean setModuleUserPermission(byte[] var0, int var1, ApplicationPermissions var2) {
      if (var2 != null && var0 != null) {
         int[] var3 = var2.getPermissionKeys();
         long var4 = 0;

         for (int var9 = 0; var9 < var3.length; var9++) {
            int var6 = var3[var9];
            int var7 = var2.getPermissionInternal(var6);
            int var8 = ApplicationControlConstants.APP_PERM_TO_APP_CONTROL_MAP[var6];
            if (var7 != -1) {
               var4 |= getPermissionFlags(var8);
               var4 |= correspondingPromptFlag(var8);
            }
         }

         UserSetting var12 = _userPermissions.getDefaultSetting();
         UserSetting var10 = getOrCreateUserSetting(var0, var1);
         return applyModulePermissions(
            var0, var1, var10, var4 & convertApplicationPermissions(var2) | (var4 ^ -1) & var12.getPermissions(), var10.getDontPrompt() & (var4 ^ -1), var4
         );
      } else {
         throw new Object();
      }
   }

   static final boolean setModuleUserPermission(byte[] var0, int var1, long var2) {
      UserSetting var4 = getOrCreateUserSetting(var0, var1);
      return applyModulePermissions(var0, var1, var4, var2, var4.getDontPrompt(), var4.getIsSet());
   }

   private static final boolean applyModulePermissions(byte[] var0, int var1, UserSetting var2, long var3, long var5, long var7) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final boolean setModulePermission(byte[] var0, int var1, long var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final long getPolicyPermissions(int var0, byte[] var1) {
      long var2 = 0;
      if (var0 == 0) {
         return _permissions.getPermittedPermissions();
      } else if (var1 == null) {
         throw new Object();
      } else {
         return getModulePolicyPermittedPermissions(var0, var1);
      }
   }

   private static final UserSetting getOrCreateUserSetting(byte[] var0, int var1) {
      UserSetting var2 = _userPermissions.getSetting(var1);
      if (var2 == null) {
         var2 = (UserSetting)(new Object(var0, _userPermissions.getDefaultSetting().getPermissions()));
         _userPermissions.putSetting(var1, var2, false);
         return var2;
      }

      if (var1 == 0) {
         _userPermissions.removeBackedUpDefaultSetting();
      }

      return var2;
   }

   static final boolean isPromptResponseSaved(int var0) {
      boolean var1 = false;
      long var2 = Long.MIN_VALUE >>> var0;
      UserSetting var4 = _userPermissions.getSetting(_processModuleHandle);
      if (var4 != null) {
         var1 |= (var4.getDontPrompt() & var2) != 0;
      } else if (!isSignedWithRRI(_processModuleHandle)) {
         var1 = false;
      }

      int[] var5 = TraceBack.getCallingModules();

      for (int var7 = var5.length - 1; var7 >= 0 && var1; var7--) {
         int var6 = var5[var7];
         var4 = _userPermissions.getSetting(var6);
         if (var4 != null) {
            var1 |= (var4.getDontPrompt() & var2) != 0;
         } else if (!isSignedWithRRI(var6)) {
            var1 = false;
         }
      }

      return var1;
   }

   private static final void saveResponsePermissions(int[] var0, int var1, int var2, boolean var3) {
      byte[] var6 = new byte[20];
      if (_processModuleHandle != 0 && Arrays.getIndex(var0, _processModuleHandle) == -1) {
         Arrays.add(var0, _processModuleHandle);
      }

      long var8 = Long.MIN_VALUE >>> var1;
      long var10 = Long.MIN_VALUE >>> var2;

      for (int var12 = 0; var12 < var0.length; var12++) {
         int var4 = var0[var12];
         if (!isSignedWithRRI(var4)) {
            if (!CodeModuleManager.getModuleHash(var4, var6)) {
               throw new Object();
            }

            Object var7 = _userPermissions.getSetting(var4);
            if (var7 == null) {
               var7 = new Object(var6, doGetModulePermissions(var4));
               _userPermissions.putSetting(var4, (UserSetting)var7, false);
            } else if (!((UserSetting)var7).hashEquals(var6)) {
               throw new Object();
            }

            Object var13 = new Object((UserSetting)var7);
            long var14 = ((UserSetting)var7).getPermissions();
            if (!var3) {
               var14 &= var8 ^ -1;
            }

            _userPermissions.setPermissions(
               (UserSetting)var13,
               (UserSetting)var7,
               var14 & (var10 ^ -1),
               ((UserSetting)var7).getDontPrompt() | var10 | var8,
               ((UserSetting)var7).getIsSet() | var10 | var8,
               false
            );
            long var16 = getModulePolicyPermittedPermissions(var4, var6);
            doSetModulePermissions(var4, combinePermissions(var16, ((UserSetting)var7).getPermissions(), ((UserSetting)var7).getDontPrompt()));
         }
      }

      _userPermissions.commit();
   }

   static final void resetPrompts(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void resetPrompt(int var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void resetAllPrompts() {
      _responseStackPermissions.removeAllResponses();
      int[] var0 = _userPermissions.getLoadedHandles();

      for (int var4 = 0; var4 < var0.length; var4++) {
         int var1 = var0[var4];
         UserSetting var2 = _userPermissions.getSetting(var1);
         if (var2 != null) {
            byte[] var3 = var2.getHash();
            Object var5 = new Object(var2);
            _userPermissions.resetPrompts((UserSetting)var5, var2, false);
            long var6 = getModulePolicyPermittedPermissions(var1, var3);
            doSetModulePermissions(var1, combinePermissions(var6, var2.getPermissions(), var2.getDontPrompt()));
         }
      }

      _userPermissions.commit();
   }

   static final void resetAllPrompts(int var0, int var1) {
      _responseStackPermissions.removeAllResponses(var0, var1);
      int[] var2 = _userPermissions.getLoadedHandles();
      long var6 = Long.MIN_VALUE >>> var0 | Long.MIN_VALUE >>> var1;

      for (int var8 = 0; var8 < var2.length; var8++) {
         int var3 = var2[var8];
         UserSetting var4 = _userPermissions.getSetting(var3);
         if (var4 != null) {
            byte[] var5 = var4.getHash();
            Object var9 = new Object(var4);
            _userPermissions.resetPrompt((UserSetting)var9, var4, var6, false);
            long var10 = getModulePolicyPermittedPermissions(var3, var5);
            doSetModulePermissions(var3, combinePermissions(var10, var4.getPermissions(), var4.getDontPrompt()));
         }
      }

      _userPermissions.commit();
   }

   private static final long getModulePermission(int var0, byte[] var1, int var2) {
      return isFlagSet(var1, var2) ? Long.MIN_VALUE >>> var2 : 0;
   }

   private static final long getModuleControlPermissions(int var0, byte[] var1) {
      long var2 = 0;
      var2 |= ControlledAccess.verifyCodeModuleSignature(var0, 51) ? 1 : 0;
      var2 |= isFlagSet(var1, 0) ? Long.MIN_VALUE : 0;
      var2 |= isFlagSet(var1, 1) ? 4611686018427387904L : 0;
      return var2 | (isFlagSet(var1, 20) ? 8796093022208L : 0);
   }

   private static final long getModulePolicyPermittedPermissions(int var0, byte[] var1) {
      boolean var4 = isModuleSettingPresent(var1);
      long var2;
      if (var4) {
         var2 = getModuleAppControlPermittedPermissions(var0, var1);
      } else {
         var2 = _permissions.getPermittedPermissions();
      }

      return var2 | getModuleControlPermissions(var0, var1);
   }

   private static final long getModulePermissions(int var0, byte[] var1) {
      long var2 = getModuleControlPermissions(var0, var1);
      if ((var2 & 1) != 0) {
         return var2;
      }

      long var4 = Long.MIN_VALUE;
      var2 |= isFlagSet(var1, 2) ? var4 >>> 2 : 0;
      var2 |= isFlagSet(var1, 3) ? var4 >>> 3 : 0;
      var2 |= isFlagSet(var1, 4) ? var4 >>> 4 : 0;
      var2 |= isFlagSet(var1, 5) ? var4 >>> 5 : 0;
      var2 |= isFlagSet(var1, 6) ? var4 >>> 6 : 0;
      var2 |= isFlagSet(var1, 7) ? var4 >>> 7 : 0;
      var2 |= isFlagSet(var1, 8) ? var4 >>> 8 : 0;
      var2 |= isFlagSet(var1, 9) ? var4 >>> 9 : 0;
      var2 |= isFlagSet(var1, 10) ? var4 >>> 10 : 0;
      var2 |= isFlagSet(var1, 11) ? var4 >>> 11 : 0;
      var2 |= isFlagSet(var1, 12) ? var4 >>> 12 : 0;
      var2 |= isFlagSet(var1, 13) ? var4 >>> 13 : 0;
      var2 |= isFlagSet(var1, 14) ? var4 >>> 14 : 0;
      var2 |= isFlagSet(var1, 15) ? var4 >>> 15 : 0;
      var2 |= isFlagSet(var1, 16) ? var4 >>> 16 : 0;
      var2 |= isFlagSet(var1, 17) ? var4 >>> 17 : 0;
      var2 |= isFlagSet(var1, 18) ? var4 >>> 18 : 0;
      var2 |= isFlagSet(var1, 19) ? var4 >>> 19 : 0;
      var2 |= isFlagSet(var1, 21) ? var4 >>> 21 : 0;
      var2 |= isFlagSet(var1, 22) ? var4 >>> 22 : 0;
      var2 |= isFlagSet(var1, 23) ? var4 >>> 23 : 0;
      var2 |= isFlagSet(var1, 24) ? var4 >>> 24 : 0;
      var2 |= isFlagSet(var1, 25) ? var4 >>> 25 : 0;
      var2 |= isFlagSet(var1, 26) ? var4 >>> 26 : 0;
      var2 |= isFlagSet(var1, 27) ? var4 >>> 27 : 0;
      var2 |= isFlagSet(var1, 28) ? var4 >>> 28 : 0;
      var2 |= isFlagSet(var1, 29) ? var4 >>> 29 : 0;
      var2 |= isFlagSet(var1, 30) ? var4 >>> 30 : 0;
      var2 |= isFlagSet(var1, 31) ? var4 >>> 31 : 0;
      return var2 | (isFlagSet(var1, 32) ? var4 >>> 32 : 0);
   }

   private static final long getModuleAppControlPermittedPermissions(int var0, byte[] var1) {
      long var2 = getModuleControlPermissions(var0, var1);
      if ((var2 & 1) != 0) {
         return var2;
      }

      long var4 = Long.MIN_VALUE;
      var2 |= isPermitted(var1, 2) ? var4 >>> 2 : 0;
      var2 |= isPermitted(var1, 3) ? var4 >>> 3 : 0;
      var2 |= isPermitted(var1, 4) ? var4 >>> 4 : 0;
      var2 |= isPermitted(var1, 5) ? var4 >>> 5 : 0;
      var2 |= isPermitted(var1, 6) ? var4 >>> 6 : 0;
      var2 |= isPermitted(var1, 7) ? var4 >>> 7 : 0;
      var2 |= isPermitted(var1, 8) ? var4 >>> 8 : 0;
      var2 |= isPermitted(var1, 9) ? var4 >>> 9 : 0;
      var2 |= isPermitted(var1, 10) ? var4 >>> 10 : 0;
      var2 |= isPermitted(var1, 11) ? var4 >>> 11 : 0;
      var2 |= isPermitted(var1, 12) ? var4 >>> 12 : 0;
      var2 |= isPermitted(var1, 13) ? var4 >>> 13 : 0;
      var2 |= isPermitted(var1, 14) ? var4 >>> 14 : 0;
      var2 |= isPermitted(var1, 15) ? var4 >>> 15 : 0;
      var2 |= isPermitted(var1, 16) ? var4 >>> 16 : 0;
      var2 |= isPermitted(var1, 17) ? var4 >>> 17 : 0;
      var2 |= isPermitted(var1, 18) ? var4 >>> 18 : 0;
      var2 |= isPermitted(var1, 19) ? var4 >>> 19 : 0;
      var2 |= isPermitted(var1, 21) ? var4 >>> 21 : 0;
      var2 |= isPermitted(var1, 22) ? var4 >>> 22 : 0;
      var2 |= isPermitted(var1, 23) ? var4 >>> 23 : 0;
      var2 |= isPermitted(var1, 24) ? var4 >>> 24 : 0;
      var2 |= isPermitted(var1, 25) ? var4 >>> 25 : 0;
      var2 |= isPermitted(var1, 26) ? var4 >>> 26 : 0;
      var2 |= isPermitted(var1, 27) ? var4 >>> 27 : 0;
      var2 |= isPermitted(var1, 28) ? var4 >>> 28 : 0;
      var2 |= isPermitted(var1, 29) ? var4 >>> 29 : 0;
      var2 |= isPermitted(var1, 30) ? var4 >>> 30 : 0;
      var2 |= isPermitted(var1, 31) ? var4 >>> 31 : 0;
      return var2 | (isPermitted(var1, 32) ? var4 >>> 32 : 0);
   }

   static final long getRequestedPermissions(ApplicationPermissions var0) {
      long var1 = 0;
      long var3 = Long.MIN_VALUE;
      var1 |= var0.getPermissionInternal(0) != 997 ? var3 >>> 20 : 0;
      var1 |= var0.getPermissionInternal(11) != 997 ? var3 >>> 2 : 0;
      var1 |= var0.getPermissionInternal(10) != 997 ? var3 >>> 3 : 0;
      var1 |= var0.getPermissionInternal(7) != 997 ? var3 >>> 5 : 0;
      var1 |= var0.getPermissionInternal(13) != 997 ? var3 >>> 7 : 0;
      var1 |= var0.getPermissionInternal(15) != 997 ? var3 >>> 8 : 0;
      var1 |= var0.getPermissionInternal(5) != 997 ? var3 >>> 10 : 0;
      var1 |= var0.getPermissionInternal(16) != 997 ? var3 >>> 11 : 0;
      var1 |= var0.getPermissionInternal(2) != 997 ? var3 >>> 12 : 0;
      var1 |= var0.getPermissionInternal(6) != 997 ? var3 >>> 13 : 0;
      var1 |= var0.getPermissionInternal(1) != 997 ? var3 >>> 14 : 0;
      var1 |= var0.getPermissionInternal(9) != 997 ? var3 >>> 15 : 0;
      var1 |= var0.getPermissionInternal(12) != 997 ? var3 >>> 16 : 0;
      var1 |= var0.getPermissionInternal(14) != 997 ? var3 >>> 17 : 0;
      var1 |= var0.getPermissionInternal(18) != 997 ? var3 >>> 19 : 0;
      var1 |= var0.getPermissionInternal(8) != 997 ? var3 >>> 21 : 0;
      var1 |= var0.getPermissionInternal(4) != 997 ? var3 >>> 22 : 0;
      var1 |= var0.getPermissionInternal(3) != 997 ? var3 >>> 23 : 0;
      var1 |= var0.getPermissionInternal(17) != 997 ? var3 >>> 25 : 0;
      var1 |= var0.getPermissionInternal(19) != 997 ? var3 >>> 27 : 0;
      var1 |= var0.getPermissionInternal(20) != 997 ? var3 >>> 29 : 0;
      return var1 | (var0.getPermissionInternal(21) != 997 ? var3 >>> 31 : 0);
   }

   static final long convertApplicationPermissions(ApplicationPermissions var0) {
      long var1 = 0;
      var1 |= convertPermToFlag(var0, 0, 20);
      var1 |= convertPermToFlag(var0, 11, 2);
      var1 |= convertPermToFlagTernary(var0, 10, 3, 4);
      var1 |= convertPermToFlagTernary(var0, 7, 5, 6);
      var1 |= convertPermToFlag(var0, 13, 7);
      var1 |= convertPermToFlagTernary(var0, 15, 8, 9);
      var1 |= convertPermToFlag(var0, 5, 10);
      var1 |= convertPermToFlag(var0, 16, 11);
      var1 |= convertPermToFlag(var0, 2, 12);
      var1 |= convertPermToFlag(var0, 6, 13);
      var1 |= convertPermToFlag(var0, 1, 14);
      var1 |= convertPermToFlag(var0, 9, 15);
      var1 |= convertPermToFlag(var0, 12, 16);
      var1 |= convertPermToFlagTernary(var0, 14, 17, 18);
      var1 |= convertPermToFlag(var0, 18, 19);
      var1 |= convertPermToFlag(var0, 8, 21);
      var1 |= convertPermToFlag(var0, 4, 22);
      var1 |= convertPermToFlagTernary(var0, 3, 23, 24);
      var1 |= convertPermToFlagTernary(var0, 17, 25, 26);
      var1 |= convertPermToFlagTernary(var0, 19, 27, 28);
      var1 |= convertPermToFlagTernary(var0, 20, 29, 30);
      return var1 | convertPermToFlagTernary(var0, 21, 31, 32);
   }

   private static final long convertPermToFlag(ApplicationPermissions var0, int var1, int var2) {
      return var0.getPermissionInternal(var1) == 999 ? getPermissionFlags(var2) : 0;
   }

   private static final long convertPermToFlagTernary(ApplicationPermissions var0, int var1, int var2, int var3) {
      int var4 = var0.getPermissionInternal(var1);
      if (var4 == 999) {
         return getPermissionFlags(var2);
      } else {
         return var4 == 998 ? getPermissionFlags(var2, var3) : 0;
      }
   }

   static final ApplicationPermissions buildPermissions(long var0) {
      return buildPermissions(var0, -1);
   }

   static final ApplicationPermissions buildPermissions(long var0, long var2) {
      Object var4 = new Object();
      addAppPermission((ApplicationPermissions)var4, 0, var0, var2, 20);
      addAppPermission((ApplicationPermissions)var4, 11, var0, var2, 2);
      addAppPermissionTernary((ApplicationPermissions)var4, 10, var0, var2, 3, 4);
      addAppPermissionTernary((ApplicationPermissions)var4, 7, var0, var2, 5, 6);
      addAppPermission((ApplicationPermissions)var4, 13, var0, var2, 7);
      addAppPermissionTernary((ApplicationPermissions)var4, 15, var0, var2, 8, 9);
      addAppPermission((ApplicationPermissions)var4, 5, var0, var2, 10);
      addAppPermission((ApplicationPermissions)var4, 16, var0, var2, 11);
      addAppPermission((ApplicationPermissions)var4, 2, var0, var2, 12);
      addAppPermission((ApplicationPermissions)var4, 6, var0, var2, 13);
      addAppPermission((ApplicationPermissions)var4, 1, var0, var2, 14);
      addAppPermission((ApplicationPermissions)var4, 9, var0, var2, 15);
      addAppPermission((ApplicationPermissions)var4, 12, var0, var2, 16);
      addAppPermissionTernary((ApplicationPermissions)var4, 14, var0, var2, 17, 18);
      addAppPermission((ApplicationPermissions)var4, 18, var0, var2, 19);
      addAppPermission((ApplicationPermissions)var4, 8, var0, var2, 21);
      addAppPermission((ApplicationPermissions)var4, 4, var0, var2, 22);
      addAppPermissionTernary((ApplicationPermissions)var4, 3, var0, var2, 23, 24);
      addAppPermissionTernary((ApplicationPermissions)var4, 17, var0, var2, 25, 26);
      addAppPermissionTernary((ApplicationPermissions)var4, 19, var0, var2, 27, 28);
      addAppPermissionTernary((ApplicationPermissions)var4, 20, var0, var2, 29, 30);
      addAppPermissionTernary((ApplicationPermissions)var4, 21, var0, var2, 31, 32);
      return (ApplicationPermissions)var4;
   }

   private static final void addAppPermission(ApplicationPermissions var0, int var1, long var2, long var4, int var6) {
      if (isBitSet(var4, var6)) {
         if (isBitSet(var2, var6)) {
            var0.addPermission(var1, 999);
            return;
         }

         var0.addPermission(var1, 997);
      }
   }

   private static final void addAppPermissionTernary(ApplicationPermissions var0, int var1, long var2, long var4, int var6, int var7) {
      if (isBitSet(var4, var6)) {
         if (isBitSet(var2, var7)) {
            var0.addPermission(var1, 998);
            return;
         }

         if (isBitSet(var2, var6)) {
            var0.addPermission(var1, 999);
            return;
         }

         var0.addPermission(var1, 997);
      }
   }

   static final long getPermissionFlags(int var0) {
      return Long.MIN_VALUE >>> var0;
   }

   static final long getPermissionFlags(int var0, int var1) {
      return Long.MIN_VALUE >>> var0 | Long.MIN_VALUE >>> var1;
   }

   static final boolean isBitSet(long var0, int var2) {
      return (var0 & Long.MIN_VALUE >>> var2) != 0;
   }

   private static final long correspondingPromptFlag(int var0) {
      return (1477252511105548288L & getPermissionFlags(var0)) >>> 1;
   }

   private static final long combinePermissions(long var0, long var2) {
      return combinePermissions(var0, var2, 0);
   }

   private static final long combinePermissions(long var0, long var2, long var4) {
      long var6 = 0;
      long var8 = 1152921504606846976L;
      long var10 = 576460752303423488L;
      long var12 = 288230376151711744L;
      long var14 = 144115188075855872L;
      long var16 = 36028797018963968L;
      long var18 = 18014398509481984L;
      long var20 = 70368744177664L;
      long var22 = 35184372088832L;
      long var24 = 1099511627776L;
      long var26 = 549755813888L;
      long var28 = 274877906944L;
      long var30 = 137438953472L;
      long var32 = 68719476736L;
      long var34 = 34359738368L;
      long var36 = 17179869184L;
      long var38 = 8589934592L;
      long var40 = 4294967296L;
      long var42 = 2147483648L;
      if ((var0 & 1) != 0) {
         var6 = var2 | 1;
      } else {
         long var44 = var0 | var2;
         var6 = var0 & var2;
         if ((var6 & var8) != 0) {
            var6 |= var44 & var10 & (var4 & var10 ^ -1);
         }

         if ((var6 & var12) != 0) {
            var6 |= var44 & var14 & (var4 & var14 ^ -1);
         }

         if ((var6 & var16) != 0) {
            var6 |= var44 & var18 & (var4 & var18 ^ -1);
         }

         if ((var6 & var20) != 0) {
            var6 |= var44 & var22 & (var4 & var22 ^ -1);
         }

         if ((var6 & var24) != 0) {
            var6 |= var44 & var26 & (var4 & var26 ^ -1);
         }

         if ((var6 & var28) != 0) {
            var6 |= var44 & var30 & (var4 & var30 ^ -1);
         }

         if ((var6 & var32) != 0) {
            var6 |= var44 & var34 & (var4 & var34 ^ -1);
         }

         if ((var6 & var36) != 0) {
            var6 |= var44 & var38 & (var4 & var38 ^ -1);
         }

         if ((var6 & var40) != 0) {
            var6 |= var44 & var42 & (var4 & var42 ^ -1);
         }
      }

      return var6 | var0 & -4611677222334365695L;
   }

   private static final boolean isMoreRestrictive(long var0, long var2) {
      long var4 = getGrantedPermissions();
      var0 &= Integer.MIN_VALUE;
      var2 &= Integer.MIN_VALUE;
      var4 |= (var4 & 1477252511105548288L) >> 1;
      long var6 = var0 ^ var2;
      long var8 = var6 & var0 & 3873059760727130112L & var4;
      long var10 = var0 & var2 & 1477252511105548288L & var4;
      long var12 = var6 & var2;
      return var8 != 0 || (var10 >>> 1 & var12) != 0;
   }

   static final boolean isPolicyDataPresent() {
      return _permissions.isAppControlPolicyDataPresent();
   }

   static final void disableXmit() {
      _permissions.disableXmit();
   }

   static final boolean isXmitDisabled() {
      return _permissions.isXmitDisabled();
   }

   static final void logDenial(int var0, int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final void logReset(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final boolean doSetModulePermissions(int var0, long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final long doGetModulePermissions(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final int doGetCallingModulesPermission(long var0, long var2, long var4, boolean var6, int var7, int[] var8) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final boolean isModuleSettingPresent(byte[] var0) {
      return var0 != null && _permissions.isAppControlPolicyDataPresent() ? isSettingPresent(var0) : false;
   }

   static final String getInternalConnectionDomains(byte[] var0) {
      return getConnectionDomains(var0, (byte)1);
   }

   static final String getExternalConnectionDomains(byte[] var0) {
      return getConnectionDomains(var0, (byte)2);
   }

   static final String getBrowserFilterConnectionDomains(byte[] var0) {
      return getConnectionDomains(var0, (byte)3);
   }

   static final String getConnectionDomains(byte[] var0, byte var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void scheduleDeviceReset(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void scheduleDeviceReset(String var0, long var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void scheduleDeviceReset(String var0, int var1, long var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void doPromptWork(int var0, ResourceBundleFamily var1, int var2, int var3, int var4) {
      if (var1 == null) {
         throw new Object();
      }

      switch (var0) {
         case -1:
            return;
         case 0:
            return;
         case 1:
            throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[var3]);
         case 2:
         default:
            Process var5 = Process.currentProcess();
            int var6 = var5.getModuleHandle();
            boolean var7 = false;
            int[] var8 = TraceBack.getCallingModules();
            int var9 = 0;
            int[] var10 = new int[var8.length];

            for (int var12 = 0; var12 < var8.length; var12++) {
               int var13 = var8[var12];
               UserSetting var11 = _userPermissions.getSetting(var8[var12]);
               if (!isSignedWithRRI(var13) && (getUserPermission(var11, var3) == 0 || getUserPermission(var11, var4) != 0)) {
                  byte[] var14 = CodeModuleManager.getModuleHash(var13);
                  if (var14 != null) {
                     if (getPolicyPermissionIgnoringDefaults(var13, var14, var3) == 0 || getPolicyPermissionIgnoringDefaults(var13, var14, var4) != 0) {
                        var7 = true;
                        break;
                     }

                     var10[var9++] = var13;
                  }
               }
            }

            Array.resize(var10, var9);
            if (var10.length == 0) {
               var7 = true;
            }

            if (var7) {
               int var15 = _responseStackPermissions.getResponsePermission(var6, var8, var3, var4);
               if (var15 == 0) {
                  return;
               }

               if (var15 == 1) {
                  throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[var3]);
               }

               var10 = new int[0];
            }

            String var16 = MessageFormat.format(var1.getString(var2), new String[]{var5.getModuleName()});
            Object var17 = new Object(var16, CommonResource.getString(10094), var8, var10);
            if (((PermissionDialog)var17).getPermission()) {
               if (((PermissionDialog)var17).getUserOptionCheckBoxValue()) {
                  if (var7) {
                     _responseStackPermissions.setResponse(var6, var8, var3, var4, true);
                  } else {
                     saveResponsePermissions(var10, var3, var4, true);
                  }
               }

               setGrantedPermissions(Long.MIN_VALUE >>> var3 | Long.MIN_VALUE >>> var4);
            } else {
               if (((PermissionDialog)var17).getUserOptionCheckBoxValue()) {
                  if (var7) {
                     _responseStackPermissions.setResponse(var6, var8, var3, var4, false);
                  } else {
                     saveResponsePermissions(var10, var3, var4, false);
                  }
               }

               throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[var3]);
            }
      }
   }

   static final native int isDomainAllowed(byte[] var0, String var1, byte var2);

   private static final native boolean isFlagSet(byte[] var0, int var1);

   private static final native boolean isPermitted(byte[] var0, int var1);

   private static final native byte[] getAllowedDomainsUTF8(byte[] var0, byte var1);

   private static final native boolean isSettingPresent(byte[] var0);

   private static final native void setModulePermissions(int var0, long var1);

   private static final native long getModulePermissions(int var0);

   private static final native long getGrantedPermissions();

   private static final native void setGrantedPermissions(long var0);

   private static final native int getCallingModulesPermission(long var0, long var2, long var4, boolean var6, int var7, int[] var8);
}
