package net.rim.device.internal.system;

import java.util.Vector;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentContentInternal;
import net.rim.device.api.system.Phone;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.system.UserAuthenticator;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.api.util.TLEUtilities;

public final class Security {
   private Security$SecurityCache _securityCache;
   private UserAuthenticator _userAuthenticator;
   private Vector _registeredUserAuthenticators;
   private boolean _pendingContentProtectionChange;
   private boolean _pendingContentProtectionEncryptionSetting;
   private int _pendingContentProtectionEncryptionStrength;
   private boolean _lockOnIdle;
   private DevicePasswordListener _keyStoreListener;
   private DevicePasswordListener _fileSystemEncryptionListener;
   private SecurityCallHandler _callHandler;
   private boolean _isAutoOnRequired;
   private int _unlockCounter;
   private int _lockCounter;
   private static final long REGISTRY_NAME;
   private static final long PASSWORD_KEY;
   private static final int DAY_IN_MILLISECONDS;
   private static final int PASSWORD_ENABLED_TIMESTAMP;
   private static Security _instance;
   public static final int MAX_PASSWORD_LENGTH;
   public static final int PASSWORD_OK;
   public static final int PASSWORD_TOO_SHORT;
   public static final int PASSWORD_TOO_LONG;
   public static final int PASSWORD_IS_SEQUENCE;
   public static final int PASSWORD_REQ_ALPHA_NUMERIC;
   public static final int PASSWORD_REQ_ALPHA_NUMERIC_SPECIAL;
   public static final int PASSWORD_REQ_ALPHA_CASE_NUMERIC_SPECIAL;
   public static final int PASSWORD_FORBIDDEN;
   public static final int PASSWORD_PATTERN_MASK_ERROR;
   public static final long GUID_PASSWORD_STATE_CHANGED;
   public static final long GUID_SECURITY_OPTIONS_CHANGED;
   public static final long SECURITY_SETTING_PASSWORD_GUID;
   public static final long SECURITY_SETTING_CONTENT_PROTECTION_GUID;
   public static final long SECURITY_SETTING_FILE_SYSTEM_ENCRYPTION_UID;
   public static final long GUID_PASSWORD_ENTRY_CHANGED;

   public static final Security getInstance() {
      if (_instance == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _instance = (Security)var0.getOrWaitFor(9159244075162769423L);
         if (_instance == null) {
            _instance = new Security();
            var0.put(9159244075162769423L, _instance);
         }
      }

      return _instance;
   }

   private static final native boolean lockOnIdleTimeout();

   private Security() {
   }

   public final boolean setUserAuthenticatorPassword(UserAuthenticator var1, String var2) {
      if (var1 == null || this._userAuthenticator != null || var2 == null || !this._registeredUserAuthenticators.contains(var1)) {
         throw new Object();
      }

      if (var1.initialize(var2)) {
         this._userAuthenticator = var1;
         this.setNumericPasswords(null, var2);
         Class var3 = var1.getClass();
         NvStore.writeData(15, var3.getName().getBytes());
         byte[] var4 = var1.getStateData();
         if (var4 != null) {
            NvStore.writeData(16, var4);
         }

         return true;
      } else {
         return false;
      }
   }

   public final void reinitializeUserAuthenticatorStateData() {
      if (this._userAuthenticator == null) {
         throw new Object();
      }

      byte[] var1 = this._userAuthenticator.getStateData();
      if (var1 != null) {
         NvStore.writeData(16, var1);
      }
   }

   public final void uninitializeUserAuthenticator() {
      NvStore.deleteData(15);
      NvStore.deleteData(16);
      this._securityCache.setAutoFillUserAuthenticatorPasswordField(false);
      if (this._userAuthenticator != null) {
         this._userAuthenticator.uninitialize();
         this._userAuthenticator = null;
      }
   }

   public final boolean setPassword(String var1, String var2, String var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void setTimeoutIfRequired() {
      int var1 = 60 * ITPolicy.getInteger(22, 1, -1);
      boolean var2 = ITPolicy.getBoolean(12, true);
      if (var1 >= 0) {
         if (var2) {
            if (!this.isPasswordEnabled()) {
               this.setTimeout(var1);
               return;
            }
         } else {
            this.setTimeout(var1);
         }
      }
   }

   public final void setContentProtection(boolean var1, int var2) {
      this._pendingContentProtectionChange = true;
      this._pendingContentProtectionEncryptionSetting = var1;
      this._pendingContentProtectionEncryptionStrength = var2;
   }

   public final int getEncryptionStrength() {
      return this._pendingContentProtectionEncryptionStrength;
   }

   public final void clearContentProtection() {
      this._pendingContentProtectionChange = false;
   }

   public final boolean isContentProtectionPending() {
      return this._pendingContentProtectionChange;
   }

   public final int verifyPasswordPattern(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final native boolean verifyPasswordChallenge(byte[] var1, byte[] var2);

   private static final boolean isLetter(char var0) {
      return 'a' <= var0 && var0 <= 'z' || 'A' <= var0 && var0 <= 'Z';
   }

   private static final boolean isUppercase(char var0) {
      return 'A' <= var0 && var0 <= 'Z';
   }

   private static final boolean isVowel(char var0) {
      return var0 == 'a'
         || var0 == 'e'
         || var0 == 'i'
         || var0 == 'o'
         || var0 == 'u'
         || var0 == 'y'
         || var0 == 'A'
         || var0 == 'E'
         || var0 == 'I'
         || var0 == 'O'
         || var0 == 'U'
         || var0 == 'Y';
   }

   private static final boolean isDigit(char var0) {
      return '0' <= var0 && var0 <= '9';
   }

   public final boolean verifyPassword(String var1, String var2, int[] var3) {
      boolean var4 = false;
      if (this.verifyPassword(var1, var2)) {
         var4 = true;
      }

      for (int var5 = 0; var5 < var3.length; var5++) {
         USBPasswordRedirectManager.getInstance().allowChannel(var3[var5], var4);
      }

      return var4;
   }

   public final boolean verifyPassword(String var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final boolean processVerifyPassword(String var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean setKeyStoreListener(DevicePasswordListener var1) {
      if (this._keyStoreListener != null) {
         return false;
      }

      this._keyStoreListener = var1;
      return true;
   }

   public final boolean setFileSystemEncryptionListener(DevicePasswordListener var1) {
      if (this._fileSystemEncryptionListener != null) {
         return false;
      }

      this._fileSystemEncryptionListener = var1;
      return true;
   }

   public final boolean verifyStoredPasswordOnly(String var1) {
      if (this.verifyPassword(var1)) {
         USBPasswordRedirectManager.getInstance().clearChannels(true);
         this._securityCache.markLongTermTimeOutTimeStamp();
         PersistentContentInternal.unlock(var1);
         if (this._keyStoreListener != null) {
            this._keyStoreListener.unlock(var1);
         }

         if (this._fileSystemEncryptionListener != null) {
            this._fileSystemEncryptionListener.unlock(var1);
         }

         return true;
      } else {
         if (this.getPasswordFailureCount() >= this.getMaxPasswordAttempts()) {
            this.deviceUnderAttack();
         }

         return false;
      }
   }

   public final int isPasswordValid(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final int checkCyclic(String var1, boolean var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   static final boolean containsForbiddenPassword(String var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void setCallHandler(SecurityCallHandler var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final SecurityCallHandler getCallHandler() {
      return this._callHandler;
   }

   public final native int getMaxPasswordAttempts();

   public final void setMaxPasswordAttempts(int var1) {
      int var2 = ITPolicy.getInteger(22, 2, 10);
      int var3 = MathUtilities.clamp(3, var1, var2);
      NvStore.writeInt(9, var3);
      this._securityCache.setMaxPasswordAttempts(var3);
      this.setMaxPasswordAttemptsInternal(var3);
   }

   private final native boolean setMaxPasswordAttemptsInternal(int var1);

   public final boolean isLastAttempt() {
      return this.getPasswordFailureCount() + 1 >= this.getMaxPasswordAttempts();
   }

   public final int getRevealPasswordAttempts(int var1) {
      return FIPSPolicy.getBoolean(22, 3, false, true) ? var1 + 1 : var1 / 2;
   }

   public final boolean isPasswordEnabled() {
      return !this.verifyPassword(null);
   }

   public final boolean setTimeout(int var1) {
      boolean var2 = this._securityCache.setCurrentTimeOut(var1);
      if (var2) {
         RIMGlobalMessagePoster.postGlobalEvent(9206737719270818227L, 22, 1, null, null);
      }

      return var2;
   }

   public final int getTimeout() {
      return this._securityCache.getCurrentTimeOut();
   }

   public final void setLockWhenHolstered(boolean var1) {
      this._securityCache.setLockWhenHolstered(var1);
      RIMGlobalMessagePoster.postGlobalEvent(9206737719270818227L, 24, 12, null, null);
   }

   public final boolean getLockWhenHolstered() {
      return this._securityCache.getLockWhenHolstered();
   }

   public final void setPasswordRequiredForAppInstall(boolean var1) {
      this._securityCache.setPasswordRequiredForAppInstall(var1);
   }

   public final boolean getPasswordRequiredForAppInstall() {
      return this._securityCache.getPasswordRequiredForAppInstall();
   }

   public final void setAllowOutgoingCallWhileLocked(boolean var1) {
      this._securityCache.setAllowOutgoingCallWhileLocked(var1);
      RIMGlobalMessagePoster.postGlobalEvent(9206737719270818227L, 24, 40, null, null);
   }

   public final boolean getAllowOutgoingCallWhileLocked() {
      return ITPolicy.getBoolean(24, 40, true) && this._securityCache.getAllowOutgoingCallWhileLocked();
   }

   public final boolean getAutoFillUserAuthenticatorPasswordField() {
      return this._securityCache._autoFillUserAuthenticatorPasswordField;
   }

   public final void setSecurityServiceColours(int var1, int var2) {
      this._securityCache.setSecurityServiceColours(var1, var2);
      RIMGlobalMessagePoster.postGlobalEvent(9206737719270818227L, 24, 42, null, null);
   }

   private final int parseSecurityServiceColour(boolean var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int getSecurityITPolicyServiceColour() {
      return this.parseSecurityServiceColour(true, this._securityCache._securityITPolicyServiceColour);
   }

   public final int getSecurityOtherServiceColour() {
      return this.parseSecurityServiceColour(false, this._securityCache._securityOtherServiceColour);
   }

   public final boolean isAddressBookExcludedFromContentProtection() {
      if (!PersistentContent.isEncryptionEnabled()) {
         return true;
      } else {
         return ITPolicy.getBoolean(24, 55, false) ? false : this._securityCache._excludeAddressBookFromContentProtection;
      }
   }

   public final boolean getExcludeAddressBookFromContentProtection() {
      return this._securityCache._excludeAddressBookFromContentProtection;
   }

   public final void setExcludeAddressBookFromContentProtection(boolean var1) {
      this._securityCache.setExcludeAddressBookFromContentProtection(var1);
   }

   public final boolean isSmartPasswordEntryEnabledOnUserAuthenticatorPassword() {
      return this.getSmartPasswordEntry() && this._securityCache._numericUserAuthenticatorPassword;
   }

   public final boolean isSmartPasswordEntryEnabledOnHandheldPassword() {
      return this.getSmartPasswordEntry() && this._securityCache._numericHandheldPassword;
   }

   public final void setNumericPasswords(String var1, String var2) {
      if (this.getSmartPasswordEntry()) {
         if (var1 != null) {
            this._securityCache.setNumericHandheldPassword(this.isNumeric(var1));
         }

         if (var2 != null) {
            this._securityCache.setNumericUserAuthenticatorPassword(this.isNumeric(var2));
         }
      }
   }

   private final boolean isNumeric(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final boolean getSmartPasswordEntry() {
      return ITPolicy.getBoolean(24, 62, false) ? false : this._securityCache._smartPasswordEntry;
   }

   public final void setSmartPasswordEntry(boolean var1) {
      this._securityCache.setSmartPasswordEntry(var1);
   }

   public final boolean cleanNow(int var1) {
      ApplicationManager var2 = ApplicationManager.getApplicationManager();
      if (var1 != 6 && (var1 != 3 || !var2.isSystemLocked())) {
         return false;
      }

      PersistentContentInternal.lock();
      return PersistentContentInternal.doesEncryptionKeyExist();
   }

   public final boolean activateLongTermTimeOut() {
      return !ITPolicy.getBoolean(14, false) ? false : System.currentTimeMillis() > this._securityCache._longTermSecurityTimeStamp;
   }

   public final boolean activatePasswordAging() {
      int var1 = ITPolicy.getInteger(11, 0);
      long var2 = this.getPasswordEnableTimeStamp();
      if (var1 != 0 && var2 != 0) {
         long var4 = (System.currentTimeMillis() - var2) / 86400000;
         return var4 >= var1;
      } else {
         return false;
      }
   }

   public final synchronized boolean registerUserAuthenticator(UserAuthenticator var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final UserAuthenticator getUserAuthenticator() {
      return this._userAuthenticator;
   }

   public final UserAuthenticator[] getRegisteredUserAuthenticators() {
      int var1 = this._registeredUserAuthenticators.size();
      UserAuthenticator[] var2 = new UserAuthenticator[var1];
      this._registeredUserAuthenticators.copyInto(var2);
      return var2;
   }

   private final boolean isPhoneOff() {
      return !Phone.isSupported() || !Phone.getInstance().isActive();
   }

   public final boolean isLockRequired() {
      boolean var1 = this.isPasswordEnabled();
      if (var1 && this._lockOnIdle && DeviceInfo.getIdleTime() >= this.getTimeout() && this.isPhoneOff()) {
         return true;
      } else if (this._securityCache.getLockWhenHolstered() && DeviceInfo.isInHolster() && this.isPhoneOff()) {
         return true;
      } else {
         return var1 ? this.activateLongTermTimeOut() : false;
      }
   }

   public final boolean isAutoOnRequired() {
      return this._isAutoOnRequired;
   }

   public final void setAutoOnRequired(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getUnlockCounter() {
      return this._unlockCounter;
   }

   public final void incrementUnlockCounter() {
      this._unlockCounter++;
   }

   public final int getLockCounter() {
      return this._lockCounter;
   }

   public final void incrementLockCounter() {
      this._lockCounter++;
   }

   private final void setPasswordEnableTimeStamp() {
      Object var1 = new Object(true);
      Object var2 = new Object(true);
      ((DataBuffer)var1).writeLong(System.currentTimeMillis());
      TLEUtilities.writeDataField((DataBuffer)var2, 1, ((DataBuffer)var1).toArray());
      NvStore.writeData(39, ((DataBuffer)var2).getArray());
   }

   private final long getPasswordEnableTimeStamp() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean verifyPassword(String var1) {
      return this.verifyPasswordInternal(var1 == null ? null : var1.getBytes());
   }

   private final native boolean verifyPasswordInternal(byte[] var1);

   public final boolean setPassword(String var1, String var2) {
      return this.setPasswordInternal(var1 == null ? null : var1.getBytes(), var2 == null ? null : var2.getBytes());
   }

   private final native boolean setPasswordInternal(byte[] var1, byte[] var2);

   public final native int getPasswordFailureCount();

   public final native void deviceUnderAttack();

   public final native boolean resetPassword();
}
