package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.RichTextFieldUtilities;
import net.rim.device.internal.ui.container.FrameLayout;
import net.rim.device.internal.ui.container.VerticalIndentFieldManager;

public class PasswordDialog extends PopupDialog implements FieldChangeListener {
   private byte[] _passwordBytes;
   protected ButtonField _ok;
   protected ButtonField _cancel;
   protected BasicEditField _passwordField;
   protected BasicEditField _confirmField;
   private boolean _mismatchIndicationDisplayed;

   protected boolean accept() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public byte[] getPassword() {
      return this._passwordBytes;
   }

   protected boolean cancel() {
      if (!this.isCancelAllowed()) {
         return false;
      }

      this._passwordBytes = null;
      this.close(-1);
      return true;
   }

   @Override
   public void fieldChanged(Field var1, int var2) {
      if (var1 == this._ok) {
         this.accept();
      } else {
         if (var1 == this._cancel) {
            this.cancel();
         }
      }
   }

   public PasswordDialog(String var1, String var2) {
      this(var1, var2, 32);
   }

   public PasswordDialog(String var1, String var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public PasswordDialog(String var1, String var2, int var3, int var4) {
      this(var1, var2, false, var3, var4);
   }

   public PasswordDialog(String var1, String var2, boolean var3, String var4, boolean var5, int var6) {
      this(var1, var4, var5, 32, var6);
   }

   private PasswordDialog(String var1, String var2, boolean var3, int var4, int var5) {
      super(new VerticalIndentFieldManager(1153220571769602048L), var5);
      VerticalIndentFieldManager var6 = (VerticalIndentFieldManager)this.getDelegate();
      RichTextField var7 = RichTextFieldUtilities.getBoldFormattedRichTextField(var1, 45035996273704960L);
      var6.add(var7);
      this._passwordField = this.createPasswordEditField(var3, var4);
      FrameLayout var8 = new FrameLayout(1);
      var8.add(this._passwordField);
      var6.add(var8);
      if (var2 != null) {
         RichTextField var9 = RichTextFieldUtilities.getBoldFormattedRichTextField(var2, 45035996273704960L);
         var6.add(var9);
         this._confirmField = this.createPasswordEditField(var3, var4);
         var8 = new FrameLayout(1);
         var8.add(this._confirmField);
         var6.add(var8);
      }

      Object var11 = new Object(12884901888L);
      this._ok = (ButtonField)(new Object(CommonResource.getString(100)));
      this._ok.setChangeListener(this);
      ((HorizontalFieldManager)var11).add(this._ok);
      this._cancel = (ButtonField)(new Object(CommonResource.getString(10005)));
      this._cancel.setChangeListener(this);
      ((HorizontalFieldManager)var11).add(this._cancel);
      var6.add((Field)var11);
   }

   private BasicEditField createPasswordEditField(boolean var1, int var2) {
      return (BasicEditField)(var1 ? new Object(null, null, var2, 1073741824) : new Object(null, null, var2, 1073741824));
   }

   public PasswordDialog(String var1) {
      this(var1, false);
   }

   public PasswordDialog(String var1, boolean var2, int var3, int var4) {
      this(var1, null, var2, var3, var4);
   }

   public PasswordDialog(String var1, boolean var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public PasswordDialog(String var1, boolean var2) {
      this(var1, var2, 32);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 == 27) {
         return this.cancel();
      }

      if (var1 == '\n') {
         Field var4 = this.getLeafFieldWithFocus();
         if (var4 == this._cancel) {
            return this.cancel();
         } else if (var4 == this._passwordField && this._confirmField != null) {
            this._confirmField.setFocus();
            return true;
         } else {
            this.accept();
            return true;
         }
      } else {
         return super.keyChar(var1, var2, var3);
      }
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      if (!super.trackwheelClick(var1, var2)) {
         Field var3 = this.getLeafFieldWithFocus();
         if (var3 == this._cancel) {
            return this.cancel();
         } else if (var3 == this._passwordField && this._confirmField != null) {
            this._confirmField.setFocus();
            return true;
         } else {
            this.accept();
            return true;
         }
      } else {
         return true;
      }
   }
}
