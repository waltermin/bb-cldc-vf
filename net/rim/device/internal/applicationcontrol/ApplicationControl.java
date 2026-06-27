package net.rim.device.internal.applicationcontrol;

import net.rim.device.api.applicationcontrol.ApplicationPermissions;
import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.ControlledAccess;

public final class ApplicationControl {
   public static final int LAPI_ALLOWED_FLAG;
   public static final int LAPI_PROMPT_FLAG;
   public static final int SCREEN_CAPTURE_ALLOWED_FLAG;
   public static final int SCREEN_CAPTURE_PROMPT_FLAG;

   private ApplicationControl() {
   }

   public static final boolean reloadModulePermissions() {
      return ApplicationControlImpl.reloadModulePermissions();
   }

   public static final boolean reloadDefaultModulePermissions() {
      return ApplicationControlImpl.reloadDefaultModulePermissions();
   }

   public static final void disableXmit() {
      ApplicationControlImpl.disableXmit();
   }

   public static final boolean isXmitDisabled() {
      return ApplicationControlImpl.isXmitDisabled();
   }

   public static final void scheduleDeviceReset(String var0) {
      ApplicationControlImpl.scheduleDeviceReset(var0);
   }

   public static final void scheduleDeviceReset(String var0, long var1) {
      ApplicationControlImpl.scheduleDeviceReset(var0, var1);
   }

   public static final void scheduleDeviceReset(String var0, int var1, long var2) {
      ApplicationControlImpl.scheduleDeviceReset(var0, var1, var2);
   }

   public static final void resetPrompts(int var0) {
      ApplicationControlImpl.resetPrompts(var0);
   }

   public static final void resetAllPrompts() {
      ApplicationControlImpl.resetAllPrompts();
   }

   public static final void resetAllPrompts(int var0, int var1) {
      ApplicationControlImpl.resetAllPrompts(var0, var1);
   }

   public static final int isInternalConnectionAllowed(byte[] var0, String var1) {
      return ApplicationControlImpl.isDomainAllowed(var0, var1, (byte)1);
   }

   public static final int isInternalConnectionAllowed(String var0, boolean var1) {
      return isInternalConnectionAllowed(var0, var1, null);
   }

   public static final int isInternalConnectionAllowed(String var0, boolean var1, int[] var2) {
      int var3;
      if ((var3 = ApplicationControlImpl.isAllowedTernary(3, 4, var1, var2)) != 1) {
         return var3 == 0 ? var3 : 2;
      } else {
         return ApplicationControlImpl.isConnectionAllowed(var0, (byte)1, var1, var2);
      }
   }

   public static final int isInternalConnectionAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 3, 4);
   }

   public static final void assertInternalConnectionAllowed(String var0, boolean var1) {
      if (isInternalConnectionAllowed(var0, var1) != 0) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[3]);
      }
   }

   public static final int isExternalConnectionAllowed(byte[] var0, String var1) {
      return ApplicationControlImpl.isDomainAllowed(var0, var1, (byte)2);
   }

   public static final int isExternalConnectionAllowed(String var0, boolean var1) {
      return isExternalConnectionAllowed(var0, var1, null);
   }

   public static final int isExternalConnectionAllowed(String var0, boolean var1, int[] var2) {
      int var3;
      if ((var3 = ApplicationControlImpl.isAllowedTernary(5, 6, var1, var2)) != 1) {
         return var3 == 0 ? var3 : 2;
      } else {
         return ApplicationControlImpl.isConnectionAllowed(var0, (byte)2, var1, var2);
      }
   }

   public static final int isExternalConnectionAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 5, 6);
   }

   public static final void assertExternalConnectionAllowed(String var0, boolean var1) {
      if (isExternalConnectionAllowed(var0, var1) != 0) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[5]);
      }
   }

   public static final boolean isBrowserFilterAllowed(byte[] var0, String var1) {
      return ApplicationControlImpl.isDomainAllowed(var0, var1, (byte)3) == 0;
   }

   public static final boolean isBrowserFilterAllowed(String var0, boolean var1) {
      return ApplicationControlImpl.isAllowed(12, var1) ? true : ApplicationControlImpl.isConnectionAllowed(var0, (byte)3, var1) == 0;
   }

   public static final boolean isBrowserFilterAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 12) == 0;
   }

   public static final void assertBrowserFilterAllowed(String var0, boolean var1) {
      if (!isBrowserFilterAllowed(var0, var1)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[12]);
      }
   }

   public static final boolean isRequiredApp(int var0) {
      return ApplicationControlImpl.checkModulePermissionsIgnoringBypass(var0, 0) == 0;
   }

   public static final boolean isExcludedApp(int var0) {
      return ApplicationControlImpl.checkModulePermissionsIgnoringBypass(var0, 1) == 0;
   }

   public static final boolean isIPCAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowed(2, var0);
   }

   public static final boolean isIPCAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 2) == 0;
   }

   public static final void assertIPCAllowed(boolean var0) {
      ApplicationControlImpl.assertIPCAllowed(var0);
   }

   public static final boolean isLocalConnectionAllowed(boolean var0) {
      return isLocalConnectionAllowed(var0, null);
   }

   public static final boolean isLocalConnectionAllowed(boolean var0, int[] var1) {
      return ApplicationControlImpl.isAllowed(7, var0, var1) && (ControlledAccess.verifyRRISignatures(var0) || ITPolicy.getBoolean(24, 16, true));
   }

   public static final boolean isLocalConnectionAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 7) == 0;
   }

   public static final void assertLocalConnectionAllowed(boolean var0) {
      if (!isLocalConnectionAllowed(var0)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[7]);
      }
   }

   public static final int isPhoneAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowedTernary(8, 9, var0);
   }

   public static final int isPhoneAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 8, 9);
   }

   public static final void assertPhoneAllowed(boolean var0) {
      if (isPhoneAllowed(var0) != 0) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[8]);
      }
   }

   public static final void assertPhonePermitted(boolean var0, ResourceBundleFamily var1, int var2) {
      int var3 = isPhoneAllowed(var0);
      ApplicationControlImpl.doPromptWork(var3, var1, var2, 8, 9);
   }

   public static final boolean isPIMAllowed(boolean var0) {
      return isPIMAllowed(var0, null);
   }

   public static final boolean isPIMAllowed(boolean var0, int[] var1) {
      return ApplicationControlImpl.isAllowed(11, var0, var1);
   }

   public static final boolean isPIMAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 11) == 0;
   }

   public static final void assertPIMAllowed(boolean var0) {
      if (!isPIMAllowed(var0)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[11]);
      }
   }

   public static final boolean isEventInjectorAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowed(13, var0);
   }

   public static final boolean isEventInjectorAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 13) == 0;
   }

   public static final void assertEventInjectorAllowed(boolean var0) {
      if (!isEventInjectorAllowed(var0)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[13]);
      }
   }

   public static final boolean isEmailAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowed(10, var0);
   }

   public static final boolean isEmailAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 10) == 0;
   }

   public static final void assertEmailAllowed(boolean var0) {
      if (!isEmailAllowed(var0)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[10]);
      }
   }

   public static final boolean isBluetoothSerialProfileAllowed(boolean var0) {
      return isBluetoothSerialProfileAllowed(var0, null);
   }

   public static final boolean isBluetoothSerialProfileAllowed(boolean var0, int[] var1) {
      return ApplicationControlImpl.isAllowed(14, var0, var1);
   }

   public static final boolean isBluetoothSerialProfileAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 14) == 0;
   }

   public static final void assertBluetoothSerialProfileAllowed(boolean var0) {
      if (!isBluetoothSerialProfileAllowed(var0)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[14]);
      }
   }

   public static final boolean isHandheldKeyStoreAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowed(15, var0);
   }

   public static final boolean isHandheldKeyStoreAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 15) == 0;
   }

   public static final void assertHandheldKeyStoreAllowed(boolean var0) {
      if (!isHandheldKeyStoreAllowed(var0)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[15]);
      }
   }

   public static final boolean isKeyStoreMediumSecurityAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowed(16, var0);
   }

   public static final boolean isKeyStoreMediumSecurityAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 16) == 0;
   }

   public static final void assertKeyStoreMediumSecurityAllowed(boolean var0) {
      if (!isKeyStoreMediumSecurityAllowed(var0)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[16]);
      }
   }

   public static final int isLocationApiAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowedTernary(17, 18, var0);
   }

   public static final int isLocationApiAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 17, 18);
   }

   public static final void assertLocationApiAllowed(boolean var0) {
      if (isLocationApiAllowed(var0) != 0) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[17]);
      }
   }

   public static final void assertLocationApiPermitted(boolean var0, ResourceBundleFamily var1, int var2) {
      int var3 = isLocationApiAllowed(var0);
      ApplicationControlImpl.doPromptWork(var3, var1, var2, 17, 18);
   }

   public static final boolean isThemeDataAllowed(int var0) {
      return ApplicationControlImpl.isThemeDataAllowed(var0);
   }

   public static final void assertThemeDataAllowed(int var0) {
      if (!isThemeDataAllowed(var0)) {
         ApplicationControlImpl.logDenial(var0, 19);
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[19]);
      }
   }

   public static final boolean isAuthenticatorApiAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowed(20, var0);
   }

   public static final boolean isAuthenticatorApiAllowed(int var0) {
      return ApplicationControlImpl.isAuthenticatorApiAllowed(var0);
   }

   public static final void assertAuthenticatorApiAllowed(boolean var0) {
      if (!isAuthenticatorApiAllowed(var0)) {
         throw new Object();
      }
   }

   public static final boolean isFileApiAllowed(boolean var0) {
      return isFileApiAllowed(var0, null);
   }

   public static final boolean isFileApiAllowed(boolean var0, int[] var1) {
      return ApplicationControlImpl.isAllowed(21, var0, var1);
   }

   public static final boolean isFileApiAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 21) == 0;
   }

   public static final void assertFileApiAllowed(boolean var0) {
      if (!isFileApiAllowed(var0)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[21]);
      }
   }

   public static final boolean isCMMApiAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowed(22, var0);
   }

   public static final boolean isCMMApiAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 22) == 0;
   }

   public static final void assertCMMApiAllowed(boolean var0) {
      if (!isCMMApiAllowed(var0)) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[22]);
      }
   }

   public static final int isChangeDeviceSettingsAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowedTernary(23, 24, var0);
   }

   public static final int isChangeDeviceSettingsAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 23, 24);
   }

   public static final void assertChangeDeviceSettingsAllowed(boolean var0) {
      if (isChangeDeviceSettingsAllowed(var0) != 0) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[23]);
      }
   }

   public static final void assertChangeDeviceSettingsPermitted(boolean var0, ResourceBundleFamily var1, int var2) {
      int var3 = isChangeDeviceSettingsAllowed(var0);
      ApplicationControlImpl.doPromptWork(var3, var1, var2, 23, 24);
   }

   public static final int isScreenCaptureAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowedTernary(25, 26, var0);
   }

   public static final int isScreenCaptureAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 25, 26);
   }

   public static final void assertScreenCaptureAllowed(boolean var0) {
      if (isScreenCaptureAllowed(var0) != 0) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[25]);
      }
   }

   public static final void assertScreenCapturePermitted(boolean var0, ResourceBundleFamily var1, int var2) {
      int var3 = isScreenCaptureAllowed(var0);
      ApplicationControlImpl.doPromptWork(var3, var1, var2, 25, 26);
   }

   public static final int isWiFiAllowed(boolean var0) {
      return isWiFiAllowed(var0, null);
   }

   public static final int isWiFiAllowed(boolean var0, int[] var1) {
      return ApplicationControlImpl.isAllowedTernary(27, 28, var0, var1);
   }

   public static final int isWiFiAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 27, 28);
   }

   public static final void assertWiFiAllowed(boolean var0) {
      if (isWiFiAllowed(var0) != 0) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[27]);
      }
   }

   public static final void assertWiFiPermitted(boolean var0, ResourceBundleFamily var1, int var2) {
      int var3 = isWiFiAllowed(var0);
      ApplicationControlImpl.doPromptWork(var3, var1, var2, 27, 28);
   }

   public static final int isIdleTimerAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowedTernary(29, 30, var0);
   }

   public static final int isIdleTimerAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 29, 30);
   }

   public static final void assertIdleTimerAllowed(boolean var0) {
      if (isIdleTimerAllowed(var0) != 0) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[29]);
      }
   }

   public static final void assertIdleTimerPermitted(boolean var0, ResourceBundleFamily var1, int var2) {
      int var3 = isIdleTimerAllowed(var0);
      ApplicationControlImpl.doPromptWork(var3, var1, var2, 29, 30);
   }

   public static final int isMediaAllowed(boolean var0) {
      return ApplicationControlImpl.isAllowedTernary(31, 32, var0);
   }

   public static final int isMediaAllowed(int var0) {
      return ApplicationControlImpl.checkModulePermissions(var0, 31, 32);
   }

   public static final void assertMediaAllowed(boolean var0) {
      if (isMediaAllowed(var0) != 0) {
         throw new Object(null, ApplicationControlResource.PERMISSIONS_STRINGS[31]);
      }
   }

   public static final void assertMediaPermitted(boolean var0, ResourceBundleFamily var1, int var2) {
      int var3 = isMediaAllowed(var0);
      ApplicationControlImpl.doPromptWork(var3, var1, var2, 31, 32);
   }

   public static final String getBrowserFilterConnectionDomains(byte[] var0) {
      return ApplicationControlImpl.getBrowserFilterConnectionDomains(var0);
   }

   public static final String getInternalConnectionDomains(byte[] var0) {
      return ApplicationControlImpl.getInternalConnectionDomains(var0);
   }

   public static final String getExternalConnectionDomains(byte[] var0) {
      return ApplicationControlImpl.getExternalConnectionDomains(var0);
   }

   public static final void removeModule(int var0) {
      ApplicationControlImpl.removeModule(var0);
   }

   public static final void doPromptWork(int var0, ResourceBundleFamily var1, int var2, int var3, int var4) {
      ApplicationControlImpl.doPromptWork(var0, var1, var2, var3, var4);
   }

   public static final ApplicationPermissions buildPermissions(long var0, long var2) {
      return ApplicationControlImpl.buildPermissions(var0, var2);
   }

   public static final long getRequestedPermissions(ApplicationPermissions var0) {
      return ApplicationControlImpl.getRequestedPermissions(var0);
   }

   public static final long getUserPermission(int var0, int var1) {
      return ApplicationControlImpl.getUserPermission(var0, var1);
   }

   public static final long getDefaultUserPermission(int var0) {
      return ApplicationControlImpl.getDefaultUserPermission(var0);
   }

   public static final boolean isUserSettingPresent(int var0) {
      return ApplicationControlImpl.isUserSettingPresent(var0);
   }

   public static final boolean isModuleSettingPresent(byte[] var0) {
      return ApplicationControlImpl.isModuleSettingPresent(var0);
   }

   public static final boolean isUserPermissionSet(int var0, int var1) {
      return ApplicationControlImpl.isUserPermissionSet(var0, var1);
   }

   public static final int getPolicyPermission(int var0, boolean var1) {
      return ApplicationControlImpl.getPolicyPermission(var0, var1);
   }

   public static final int getPolicyPermissionTernary(int var0, int var1, boolean var2) {
      return ApplicationControlImpl.getPolicyPermissionTernary(var0, var1, var2);
   }

   public static final int getPolicyPermission(String var0, int var1, boolean var2) {
      return ApplicationControlImpl.getPolicyPermission(var0, var1, var2);
   }

   public static final int getPolicyPermissionTernary(String var0, int var1, int var2, boolean var3) {
      return ApplicationControlImpl.getPolicyPermissionTernary(var0, var1, var2, var3);
   }

   public static final long getPermittedPermission(int var0, byte[] var1, int var2) {
      return ApplicationControlImpl.getPermittedPermission(var0, var1, var2);
   }

   public static final boolean setModuleUserPermission(byte[] var0, int var1, ApplicationPermissions var2) {
      return ApplicationControlImpl.setModuleUserPermission(var0, var1, var2);
   }

   public static final boolean isPromptResponseSaved(int var0) {
      return ApplicationControlImpl.isPromptResponseSaved(var0);
   }

   public static final boolean isFlagSetBoolean(byte[] var0, int var1) {
      return ApplicationControlImpl.isFlagSetBoolean(var0, var1);
   }

   public static final boolean isBitSet(long var0, int var2) {
      return ApplicationControlImpl.isBitSet(var0, var2);
   }

   public static final long getPermissionFlags(int var0) {
      return ApplicationControlImpl.getPermissionFlags(var0);
   }

   public static final long getPermissionFlags(int var0, int var1) {
      return ApplicationControlImpl.getPermissionFlags(var0, var1);
   }

   public static final void removeUserSetting(int var0) {
      ApplicationControlImpl.removeUserSetting(var0);
   }

   public static final void removeUserSettings(boolean var0) {
      ApplicationControlImpl.removeUserSettings(var0);
   }

   public static final boolean setRestrictiveDefaultPermission() {
      return ApplicationControlImpl.setRestrictiveDefaultPermission();
   }

   public static final boolean setDefaultPermission() {
      return ApplicationControlImpl.setDefaultPermission();
   }

   public static final boolean setPermissiveDefaultPermission() {
      return ApplicationControlImpl.setPermissiveDefaultPermission();
   }

   public static final boolean isSignedWithRRI(int var0) {
      return ApplicationControlImpl.isSignedWithRRI(var0);
   }

   public static final boolean differsFromUserDefaults(int var0) {
      return ApplicationControlImpl.differsFromUserDefaults(var0);
   }
}
