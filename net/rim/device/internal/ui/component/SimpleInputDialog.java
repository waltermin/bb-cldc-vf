package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.DialogFieldManager;
import net.rim.device.internal.ui.Image;

public class SimpleInputDialog extends PopupDialog {
   private BasicEditField _editField;
   private String _initialText;
   private RichTextField _promptField;
   private int _minLength;
   private int _maxLength;
   private DialogFieldManager _dfm;
   private byte _isUnicodeInputAllowed = 0;
   public static final int NORMAL_INPUT;
   public static final int LOWER_CASE_INPUT;
   public static final int UPPER_CASE_INPUT;
   public static final int NUMERIC_INPUT;
   public static final int HEX_NUMERIC_INPUT;
   public static final int PASSWORD_INPUT;
   public static final int NUMERIC_PASSWORD_INPUT;
   public static final int PHONE_NUMBER_INPUT;
   public static final int EMAIL_ADDRESS_INPUT;
   public static final int URL_INPUT;
   public static final int NORMAL_INPUT_NO_COMPLEX;
   public static final int AUTOTEXT_INPUT;
   private static final byte UNICODE_INPUT_UNSET;
   private static final byte UNICODE_INPUT_ALLOWED;
   private static final byte UNICODE_INPUT_FORBIDDEN;

   public SimpleInputDialog(int type, String prompt) {
      this(type, prompt, 0, 1000000, 0);
   }

   public SimpleInputDialog(int type, String prompt, int minLength, int maxLength, long style) {
      super(new DialogFieldManager(), style);
      this._minLength = minLength;
      this._maxLength = maxLength;
      this._promptField = new RichTextField(prompt, 36028797018963968L);
      this._initialText = "";
      this._dfm = (DialogFieldManager)this.getDelegate();
      this._dfm.setMessage(this._promptField);
      this.setType(type);
   }

   public void setIcon(Image image) {
      ImageField field = null;
      if (image != null) {
         field = new ImageField();
         field.setImage(image);
      }

      this._dfm.setIcon(field);
   }

   public BasicEditField getEditField() {
      return this._editField;
   }

   public void setEditField(BasicEditField field) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public void setPrompt(String prompt) {
      this._promptField.setText(prompt);
   }

   public void setType(int type) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public void add(Field field) {
      this._dfm.addCustomField(field);
   }

   public String getText() {
      return this._editField.getText();
   }

   public void setText(String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void show(String prompt) {
      this.setPrompt(prompt);
      this.show();
   }

   @Override
   protected void onUiEngineAttached(boolean attached) {
      throw new RuntimeException("cod2jar: string-special");
   }

   protected boolean cancel() {
      if (!this.isCancelAllowed()) {
         return false;
      }

      this._editField.setText("");
      if (this.isGlobal() && !this.getApplication().isForeground()) {
         this.doPaint();
      }

      this.close(-1);
      return true;
   }

   protected boolean accept() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void setMinLength(int minLength) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getMinLength() {
      return this._minLength;
   }

   public void setMaxLength(int maxLength) {
      this._maxLength = maxLength;
      this._editField.setMaxSize(this._maxLength);
   }

   public int getMaxLength() {
      return this._maxLength;
   }

   @Override
   protected boolean navigationClick(int status, int time) {
      return super.navigationClick(status, time) ? true : this.accept();
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      if (key == 27) {
         return this.cancel();
      } else {
         return key == '\n' ? this.accept() : super.keyChar(key, status, time);
      }
   }

   public void setAllowUnicodeInput(boolean allow) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean isUnicodeInputAllowed() {
      return this._editField != null ? this._editField.isUnicodeInputAllowed() : this._isUnicodeInputAllowed != 2;
   }
}
