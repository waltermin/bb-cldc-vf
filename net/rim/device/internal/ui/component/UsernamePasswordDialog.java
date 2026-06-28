package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
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

   public UsernamePasswordDialog(int mode) {
      this(null, mode);
   }

   public UsernamePasswordDialog(String prompt, int mode) {
      this(prompt, null, null, null, mode, 0);
   }

   public UsernamePasswordDialog(String prompt, String username, String domain, String password, int mode, long style) {
      super(new DialogFieldManager(), style);
      this._username = username;
      this._domain = domain;
      this._password = password;
      this.populateDialog(prompt, mode);
   }

   private void populateDialog(String prompt, int mode) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void setAllowUnicodePassword(boolean allow) {
      this._passwordField.setAllowUnicodeInput(allow);
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
   public void fieldChanged(Field field, int context) {
      if (field == this._okButton) {
         this.accept();
      } else {
         if (field == this._cancelButton) {
            this.cancel();
         }
      }
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      if (key == '\n') {
         Field field = this.getDelegate().getLeafFieldWithFocus();
         if (field == this._cancelButton) {
            this.cancel();
            return true;
         }

         if (field == this._usernameField) {
            if (this._domainField != null) {
               this._domainField.setFocus();
            } else {
               this._passwordField.setFocus();
            }
         } else if (field == this._domainField) {
            this._passwordField.setFocus();
         } else if (field == this._passwordField || field == this._okButton) {
            this.accept();
         }
      } else if (key == 27) {
         this.cancel();
      }

      return super.keyChar(key, status, time);
   }
}
