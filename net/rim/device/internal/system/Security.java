package net.rim.device.internal.system;

import java.io.EOFException;
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
import net.rim.device.api.util.StringUtilities;
import net.rim.device.api.util.TLEUtilities;
import net.rim.device.internal.applicationcontrol.ApplicationControl;

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
         ApplicationRegistry appRegistry = ApplicationRegistry.getApplicationRegistry();
         _instance = (Security)appRegistry.getOrWaitFor(9159244075162769423L);
         if (_instance == null) {
            _instance = new Security();
            appRegistry.put(9159244075162769423L, _instance);
         }
      }

      return _instance;
   }

   private static final native boolean lockOnIdleTimeout();

   private Security() {
   }

   public final boolean setUserAuthenticatorPassword(UserAuthenticator userAuthenticator, String userAuthenticatorPassword) {
      if (userAuthenticator == null
         || this._userAuthenticator != null
         || userAuthenticatorPassword == null
         || !this._registeredUserAuthenticators.contains(userAuthenticator)) {
         throw new IllegalArgumentException();
      }

      if (userAuthenticator.initialize(userAuthenticatorPassword)) {
         this._userAuthenticator = userAuthenticator;
         this.setNumericPasswords(null, userAuthenticatorPassword);
         Class authenticatorClass = userAuthenticator.getClass();
         NvStore.writeData(15, authenticatorClass.getName().getBytes());
         byte[] state = userAuthenticator.getStateData();
         if (state != null) {
            NvStore.writeData(16, state);
         }

         return true;
      } else {
         return false;
      }
   }

   public final void reinitializeUserAuthenticatorStateData() {
      if (this._userAuthenticator == null) {
         throw new IllegalArgumentException();
      }

      byte[] state = this._userAuthenticator.getStateData();
      if (state != null) {
         NvStore.writeData(16, state);
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

   public final boolean setPassword(String oldPassword, String newPassword, String userAuthenticatorPassword) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void setTimeoutIfRequired() {
      int setTimeout = 60 * ITPolicy.getInteger(22, 1, -1);
      boolean allowUserToChangeTimeout = ITPolicy.getBoolean(12, true);
      if (setTimeout >= 0) {
         if (allowUserToChangeTimeout) {
            if (!this.isPasswordEnabled()) {
               this.setTimeout(setTimeout);
               return;
            }
         } else {
            this.setTimeout(setTimeout);
         }
      }
   }

   public final void setContentProtection(boolean newEncryptionSetting, int newEncryptionStrength) {
      this._pendingContentProtectionChange = true;
      this._pendingContentProtectionEncryptionSetting = newEncryptionSetting;
      this._pendingContentProtectionEncryptionStrength = newEncryptionStrength;
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

   public final int verifyPasswordPattern(String password) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final native boolean verifyPasswordChallenge(byte[] var1, byte[] var2);

   private static final boolean isLetter(char ch) {
      return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z';
   }

   private static final boolean isUppercase(char ch) {
      return 'A' <= ch && ch <= 'Z';
   }

   private static final boolean isVowel(char ch) {
      return ch == 'a'
         || ch == 'e'
         || ch == 'i'
         || ch == 'o'
         || ch == 'u'
         || ch == 'y'
         || ch == 'A'
         || ch == 'E'
         || ch == 'I'
         || ch == 'O'
         || ch == 'U'
         || ch == 'Y';
   }

   private static final boolean isDigit(char ch) {
      return '0' <= ch && ch <= '9';
   }

   public final boolean verifyPassword(String password, String userAuthenticatorPassword, int[] uSBChannels) {
      boolean result = false;
      if (this.verifyPassword(password, userAuthenticatorPassword)) {
         result = true;
      }

      for (int i = 0; i < uSBChannels.length; i++) {
         USBPasswordRedirectManager.getInstance().allowChannel(uSBChannels[i], result);
      }

      return result;
   }

   public final boolean verifyPassword(String password, String userAuthenticatorPassword) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final boolean processVerifyPassword(String password, String userAuthenticatorPassword) {
      try {
         if (this._userAuthenticator != null && this._userAuthenticator.isInitialized()) {
            if (!this._userAuthenticator.authenticate(userAuthenticatorPassword)) {
               return false;
            }

            this.setNumericPasswords(password, userAuthenticatorPassword);
         }
      } finally {
         ;
      }

      this._securityCache.markLongTermTimeOutTimeStamp();
      PersistentContentInternal.unlock(password);
      if (this._pendingContentProtectionChange) {
         this._pendingContentProtectionChange = false;
         PersistentContentInternal.setContentProtection(
            password, this._pendingContentProtectionEncryptionSetting, this._pendingContentProtectionEncryptionStrength
         );
      }

      if (this._keyStoreListener != null) {
         this._keyStoreListener.unlock(password);
      }

      if (this._fileSystemEncryptionListener != null) {
         this._fileSystemEncryptionListener.unlock(password);
      }

      this._securityCache.setAutoFillUserAuthenticatorPasswordField(StringUtilities.strEqual(password, userAuthenticatorPassword));
      return true;
   }

   public final boolean setKeyStoreListener(DevicePasswordListener listener) {
      if (this._keyStoreListener != null) {
         return false;
      }

      this._keyStoreListener = listener;
      return true;
   }

   public final boolean setFileSystemEncryptionListener(DevicePasswordListener listener) {
      if (this._fileSystemEncryptionListener != null) {
         return false;
      }

      this._fileSystemEncryptionListener = listener;
      return true;
   }

   public final boolean verifyStoredPasswordOnly(String password) {
      if (this.verifyPassword(password)) {
         USBPasswordRedirectManager.getInstance().clearChannels(true);
         this._securityCache.markLongTermTimeOutTimeStamp();
         PersistentContentInternal.unlock(password);
         if (this._keyStoreListener != null) {
            this._keyStoreListener.unlock(password);
         }

         if (this._fileSystemEncryptionListener != null) {
            this._fileSystemEncryptionListener.unlock(password);
         }

         return true;
      } else {
         if (this.getPasswordFailureCount() >= this.getMaxPasswordAttempts()) {
            this.deviceUnderAttack();
         }

         return false;
      }
   }

   public final int isPasswordValid(String password) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final int checkCyclic(String password, boolean altedChar) {
      throw new RuntimeException("cod2jar: string-special");
   }

   static final boolean containsForbiddenPassword(String password, String forbiddenPasswords) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void setCallHandler(SecurityCallHandler handler) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final SecurityCallHandler getCallHandler() {
      return this._callHandler;
   }

   public final native int getMaxPasswordAttempts();

   public final void setMaxPasswordAttempts(int maxAttempts) {
      int itPolicyMaxPasswordAttempts = ITPolicy.getInteger(22, 2, 10);
      int clampedValue = MathUtilities.clamp(3, maxAttempts, itPolicyMaxPasswordAttempts);
      NvStore.writeInt(9, clampedValue);
      this._securityCache.setMaxPasswordAttempts(clampedValue);
      this.setMaxPasswordAttemptsInternal(clampedValue);
   }

   private final native boolean setMaxPasswordAttemptsInternal(int var1);

   public final boolean isLastAttempt() {
      return this.getPasswordFailureCount() + 1 >= this.getMaxPasswordAttempts();
   }

   public final int getRevealPasswordAttempts(int maxAttempts) {
      return FIPSPolicy.getBoolean(22, 3, false, true) ? maxAttempts + 1 : maxAttempts / 2;
   }

   public final boolean isPasswordEnabled() {
      return !this.verifyPassword(null);
   }

   public final boolean setTimeout(int seconds) {
      boolean timeoutSet = this._securityCache.setCurrentTimeOut(seconds);
      if (timeoutSet) {
         RIMGlobalMessagePoster.postGlobalEvent(9206737719270818227L, 22, 1, null, null);
      }

      return timeoutSet;
   }

   public final int getTimeout() {
      return this._securityCache.getCurrentTimeOut();
   }

   public final void setLockWhenHolstered(boolean lockWhenHolstered) {
      this._securityCache.setLockWhenHolstered(lockWhenHolstered);
      RIMGlobalMessagePoster.postGlobalEvent(9206737719270818227L, 24, 12, null, null);
   }

   public final boolean getLockWhenHolstered() {
      return this._securityCache.getLockWhenHolstered();
   }

   public final void setPasswordRequiredForAppInstall(boolean passwordRequiredForInstall) {
      this._securityCache.setPasswordRequiredForAppInstall(passwordRequiredForInstall);
   }

   public final boolean getPasswordRequiredForAppInstall() {
      return this._securityCache.getPasswordRequiredForAppInstall();
   }

   public final void setAllowOutgoingCallWhileLocked(boolean allowOutgoingCallWhileLocked) {
      this._securityCache.setAllowOutgoingCallWhileLocked(allowOutgoingCallWhileLocked);
      RIMGlobalMessagePoster.postGlobalEvent(9206737719270818227L, 24, 40, null, null);
   }

   public final boolean getAllowOutgoingCallWhileLocked() {
      return ITPolicy.getBoolean(24, 40, true) && this._securityCache.getAllowOutgoingCallWhileLocked();
   }

   public final boolean getAutoFillUserAuthenticatorPasswordField() {
      return this._securityCache._autoFillUserAuthenticatorPasswordField;
   }

   public final void setSecurityServiceColours(int ITPolicyServiceColour, int otherServiceColour) {
      this._securityCache.setSecurityServiceColours(ITPolicyServiceColour, otherServiceColour);
      RIMGlobalMessagePoster.postGlobalEvent(9206737719270818227L, 24, 42, null, null);
   }

   private final int parseSecurityServiceColour(boolean ITPolicyServiceColour, int userColour) {
      throw new RuntimeException("cod2jar: string-special");
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

   public final void setExcludeAddressBookFromContentProtection(boolean excludeAddressBookFromContentProtection) {
      this._securityCache.setExcludeAddressBookFromContentProtection(excludeAddressBookFromContentProtection);
   }

   public final boolean isSmartPasswordEntryEnabledOnUserAuthenticatorPassword() {
      return this.getSmartPasswordEntry() && this._securityCache._numericUserAuthenticatorPassword;
   }

   public final boolean isSmartPasswordEntryEnabledOnHandheldPassword() {
      return this.getSmartPasswordEntry() && this._securityCache._numericHandheldPassword;
   }

   public final void setNumericPasswords(String handheldPassword, String userAuthenticatorPassword) {
      if (this.getSmartPasswordEntry()) {
         if (handheldPassword != null) {
            this._securityCache.setNumericHandheldPassword(this.isNumeric(handheldPassword));
         }

         if (userAuthenticatorPassword != null) {
            this._securityCache.setNumericUserAuthenticatorPassword(this.isNumeric(userAuthenticatorPassword));
         }
      }
   }

   private final boolean isNumeric(String password) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final boolean getSmartPasswordEntry() {
      return ITPolicy.getBoolean(24, 62, false) ? false : this._securityCache._smartPasswordEntry;
   }

   public final void setSmartPasswordEntry(boolean smartPasswordEntry) {
      this._securityCache.setSmartPasswordEntry(smartPasswordEntry);
   }

   public final boolean cleanNow(int event) {
      ApplicationManager manager = ApplicationManager.getApplicationManager();
      if (event != 6 && (event != 3 || !manager.isSystemLocked())) {
         return false;
      }

      PersistentContentInternal.lock();
      return PersistentContentInternal.doesEncryptionKeyExist();
   }

   public final boolean activateLongTermTimeOut() {
      return !ITPolicy.getBoolean(14, false) ? false : System.currentTimeMillis() > this._securityCache._longTermSecurityTimeStamp;
   }

   public final boolean activatePasswordAging() {
      int maxPasswordAge = ITPolicy.getInteger(11, 0);
      long passwordEnableTimeStamp = this.getPasswordEnableTimeStamp();
      if (maxPasswordAge != 0 && passwordEnableTimeStamp != 0) {
         long days = (System.currentTimeMillis() - passwordEnableTimeStamp) / 86400000;
         return days >= maxPasswordAge;
      } else {
         return false;
      }
   }

   public final synchronized boolean registerUserAuthenticator(UserAuthenticator authenticator) {
      if (authenticator == null) {
         throw new IllegalArgumentException();
      }

      ApplicationControl.assertAuthenticatorApiAllowed(true);
      Class newAuthenticatorClass = authenticator.getClass();

      try {
         newAuthenticatorClass.newInstance();
         this._registeredUserAuthenticators.addElement(authenticator);
         return true;
      } catch (InstantiationException var4) {
      } catch (IllegalAccessException var5) {
      }

      throw new IllegalArgumentException();
   }

   public final UserAuthenticator getUserAuthenticator() {
      return this._userAuthenticator;
   }

   public final UserAuthenticator[] getRegisteredUserAuthenticators() {
      int size = this._registeredUserAuthenticators.size();
      UserAuthenticator[] result = new UserAuthenticator[size];
      this._registeredUserAuthenticators.copyInto(result);
      return result;
   }

   private final boolean isPhoneOff() {
      return !Phone.isSupported() || !Phone.getInstance().isActive();
   }

   public final boolean isLockRequired() {
      boolean passwordEnabled = this.isPasswordEnabled();
      if (passwordEnabled && this._lockOnIdle && DeviceInfo.getIdleTime() >= this.getTimeout() && this.isPhoneOff()) {
         return true;
      } else if (this._securityCache.getLockWhenHolstered() && DeviceInfo.isInHolster() && this.isPhoneOff()) {
         return true;
      } else {
         return passwordEnabled ? this.activateLongTermTimeOut() : false;
      }
   }

   public final boolean isAutoOnRequired() {
      return this._isAutoOnRequired;
   }

   public final void setAutoOnRequired(boolean required) {
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
      DataBuffer buff = new DataBuffer(true);
      DataBuffer timeStampBuffer = new DataBuffer(true);
      buff.writeLong(System.currentTimeMillis());
      TLEUtilities.writeDataField(timeStampBuffer, 1, buff.toArray());
      NvStore.writeData(39, timeStampBuffer.getArray());
   }

   private final long getPasswordEnableTimeStamp() {
      long result = 0;
      DataBuffer timestampBuffer = null;
      byte[] buffer = NvStore.readData(39);
      if (buffer != null) {
         timestampBuffer = new DataBuffer(true);
         timestampBuffer.setData(buffer, 0, buffer.length, true);
      }

      if (timestampBuffer != null) {
         try {
            while (!timestampBuffer.eof()) {
               int tag = TLEUtilities.getType(timestampBuffer);
               switch (tag) {
                  case 1:
                     timestampBuffer.readCompressedInt();
                     timestampBuffer.readCompressedInt();
                     result = timestampBuffer.readLong();
                     break;
                  default:
                     TLEUtilities.skipField(timestampBuffer);
               }
            }
         } catch (EOFException var6) {
         }
      }

      return result;
   }

   public final boolean verifyPassword(String password) {
      return this.verifyPasswordInternal(password == null ? null : password.getBytes());
   }

   private final native boolean verifyPasswordInternal(byte[] var1);

   public final boolean setPassword(String oldPassword, String newPassword) {
      return this.setPasswordInternal(oldPassword == null ? null : oldPassword.getBytes(), newPassword == null ? null : newPassword.getBytes());
   }

   private final native boolean setPasswordInternal(byte[] var1, byte[] var2);

   public final native int getPasswordFailureCount();

   public final native void deviceUnderAttack();

   public final native boolean resetPassword();
}
