package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.DialogFieldManager;

public class UsernamePasswordDialog extends PopupDialog implements FieldChangeListener {
   private DialogFieldManager _dfm = (DialogFieldManager)this.getDelegate();
   private EditField _usernameField;
   private EditField _domainField;
   private PasswordEditField _passwordField;
   private ButtonField _okButton;
   private ButtonField _cancelButton;
   private CheckboxField _checkBoxField;
   private boolean _dialogCancelled;
   private boolean _isClosed;
   private String _username;
   private String _domain;
   private String _password;
   private boolean _remember;
   public static final int LDAP_MODE;
   public static final int BROWSER_MODE;
   public static final int JUST_USERNAME_AND_PASSWORD_MODE;

   public UsernamePasswordDialog(int var1) {
      this(null, var1);
   }

   public UsernamePasswordDialog(String var1, int var2) {
      this(var1, null, null, null, var2, 0);
   }

   public UsernamePasswordDialog(String var1, String var2, String var3, String var4, int var5, long var6) {
      super((Manager)(new Object()), var6);
      this._username = var2;
      this._domain = var3;
      this._password = var4;
      this.populateDialog(var1, var5);
   }

   private void populateDialog(String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void setAllowUnicodePassword(boolean var1) {
      this._passwordField.setAllowUnicodeInput(var1);
   }

   public boolean isUnicodePasswordAllowed() {
      return this._passwordField.isUnicodeInputAllowed();
   }

   private synchronized boolean accept() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private synchronized boolean cancel() {
      if (!this.isCancelAllowed()) {
         return false;
      } else if (!this._isClosed) {
         this._isClosed = true;
         this._dialogCancelled = true;
         this.close(-1);
         return true;
      } else {
         return true;
      }
   }

   public String getUsername() {
      return this._dialogCancelled ? null : this._username;
   }

   public String getDomain() {
      return this._dialogCancelled ? null : this._domain;
   }

   public String getPassword() {
      return this._dialogCancelled ? null : this._password;
   }

   public boolean rememberCredentials() {
      return this._remember;
   }

   @Override
   public void fieldChanged(Field var1, int var2) {
      if (var1 == this._okButton) {
         this.accept();
      } else {
         if (var1 == this._cancelButton) {
            this.cancel();
         }
      }
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 == '\n') {
         Field var4 = this.getDelegate().getLeafFieldWithFocus();
         if (var4 == this._cancelButton) {
            this.cancel();
            return true;
         }

         if (var4 == this._usernameField) {
            if (this._domainField != null) {
               this._domainField.setFocus();
            } else {
               this._passwordField.setFocus();
            }
         } else if (var4 == this._domainField) {
            this._passwordField.setFocus();
         } else if (var4 == this._passwordField || var4 == this._okButton) {
            this.accept();
         }
      } else if (var1 == 27) {
         this.cancel();
      }

      return super.keyChar(var1, var2, var3);
   }
}
