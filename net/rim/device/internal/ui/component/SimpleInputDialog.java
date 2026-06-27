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
   private byte _isUnicodeInputAllowed;
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

   public SimpleInputDialog(int var1, String var2) {
      this(var1, var2, 0, 1000000, 0);
   }

   public SimpleInputDialog(int var1, String var2, int var3, int var4, long var5) {
   }

   public void setIcon(Image var1) {
      ImageField var2 = null;
      if (var1 != null) {
         var2 = new ImageField();
         var2.setImage(var1);
      }

      this._dfm.setIcon(var2);
   }

   public BasicEditField getEditField() {
      return this._editField;
   }

   public void setEditField(BasicEditField var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public void setPrompt(String var1) {
      this._promptField.setText(var1);
   }

   public void setType(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public void add(Field var1) {
      this._dfm.addCustomField(var1);
   }

   public String getText() {
      return this._editField.getText();
   }

   public void setText(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void show(String var1) {
      this.setPrompt(var1);
      this.show();
   }

   @Override
   protected void onUiEngineAttached(boolean var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   protected boolean cancel() {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected boolean accept() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void setMinLength(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getMinLength() {
      return this._minLength;
   }

   public void setMaxLength(int var1) {
      this._maxLength = var1;
      this._editField.setMaxSize(this._maxLength);
   }

   public int getMaxLength() {
      return this._maxLength;
   }

   @Override
   protected boolean navigationClick(int var1, int var2) {
      return super.navigationClick(var1, var2) ? true : this.accept();
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 == 27) {
         return this.cancel();
      } else {
         return var1 == '\n' ? this.accept() : super.keyChar(var1, var2, var3);
      }
   }

   public void setAllowUnicodeInput(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean isUnicodeInputAllowed() {
      return this._editField != null ? this._editField.isUnicodeInputAllowed() : this._isUnicodeInputAllowed != 2;
   }
}
