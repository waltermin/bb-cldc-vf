package net.rim.device.internal.system;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.util.Persistable;

class Security$SecurityCache implements Persistable {
   private int _maxPasswordAttempts;
   private int _currentTimeOut;
   private long _longTermSecurityTimeStamp;
   private boolean _passwordRequiredForAppInstall;
   private boolean _lockWhenHolstered;
   private boolean _autoFillUserAuthenticatorPasswordField;
   private boolean _allowOutgoingCallWhileLocked;
   private boolean _numericUserAuthenticatorPassword;
   private boolean _numericHandheldPassword;
   private boolean _smartPasswordEntry;
   private int _securityITPolicyServiceColour;
   private int _securityOtherServiceColour;
   private boolean _excludeAddressBookFromContentProtection;

   private Security$SecurityCache() {
   }

   private void setMaxPasswordAttempts(int var1) {
      this._maxPasswordAttempts = var1;
      this.commit();
   }

   private void markLongTermTimeOutTimeStamp() {
      if (ITPolicy.getBoolean(14, false)) {
         int var1 = ITPolicy.getInteger(22, 8, 60) * 60 * 1000;
         this._longTermSecurityTimeStamp = System.currentTimeMillis() + var1;
         this.commit();
      }
   }

   private int getCurrentTimeOut() {
      return Math.min(this._currentTimeOut, ITPolicy.getInteger(10, 60) * 60);
   }

   private boolean setCurrentTimeOut(int var1) {
      int var2 = ITPolicy.getInteger(10, 60) * 60;
      if (var1 > 0 && var1 <= var2) {
         this._currentTimeOut = var1;
         this.commit();
         return true;
      } else {
         return false;
      }
   }

   private boolean getLockWhenHolstered() {
      return this._lockWhenHolstered || ITPolicy.getBoolean(24, 12, false);
   }

   private void setLockWhenHolstered(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private boolean getPasswordRequiredForAppInstall() {
      return this._passwordRequiredForAppInstall || ITPolicy.getBoolean(24, 75, false);
   }

   private void setPasswordRequiredForAppInstall(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private boolean getAllowOutgoingCallWhileLocked() {
      return this._allowOutgoingCallWhileLocked;
   }

   private void setAllowOutgoingCallWhileLocked(boolean var1) {
      this._allowOutgoingCallWhileLocked = var1;
      this.commit();
   }

   private void setAutoFillUserAuthenticatorPasswordField(boolean var1) {
      this._autoFillUserAuthenticatorPasswordField = var1;
      this.commit();
   }

   private void setSecurityServiceColours(int var1, int var2) {
      this._securityITPolicyServiceColour = var1;
      this._securityOtherServiceColour = var2;
      this.commit();
   }

   private void setExcludeAddressBookFromContentProtection(boolean var1) {
      this._excludeAddressBookFromContentProtection = var1;
      this.commit();
   }

   private void setNumericUserAuthenticatorPassword(boolean var1) {
      this._numericUserAuthenticatorPassword = var1;
      this.commit();
   }

   private void setNumericHandheldPassword(boolean var1) {
      this._numericHandheldPassword = var1;
      this.commit();
   }

   private void setSmartPasswordEntry(boolean var1) {
      this._smartPasswordEntry = var1;
      if (!this._smartPasswordEntry) {
         this._numericUserAuthenticatorPassword = false;
         this._numericHandheldPassword = false;
      }

      this.commit();
   }

   private void commit() {
      throw new RuntimeException("cod2jar: exception table");
   }

   Security$SecurityCache(Security$1 var1) {
      this();
   }
}
