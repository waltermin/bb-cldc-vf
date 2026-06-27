package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.container.FrameLayout;
import net.rim.device.internal.ui.container.VerticalIndentFieldManager;
import net.rim.device.internal.ui.security.component.VendorModuleStackDialog;

public class TicketDialog extends VendorModuleStackDialog implements FieldChangeListener {
   private boolean _isClosed;
   private byte[] _passwordBytes;
   private ButtonField _ok;
   private ButtonField _cancel;
   private BasicEditField _passwordField;
   private boolean _numericPassword;
   private boolean _revealPassword;
   private HorizontalFieldManager _passwordHFM;

   public byte[] getPassword() {
      return this.getCloseReason() == -1 ? null : this._passwordBytes;
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

   public TicketDialog(RichTextField var1, boolean var2, String var3, boolean var4, long var5, boolean var7) {
      super(new VerticalIndentFieldManager(1153220571769602048L), var5);
      VerticalIndentFieldManager var8 = (VerticalIndentFieldManager)this.getDelegate();
      if (var1 != null) {
         var8.add(var1);
      }

      this._revealPassword = var4;
      if (var2) {
         if (var3 != null) {
            var8.add(new VerticalSpacerField(4));
            Object var9 = new Object(var3, 9007199254740992L);
            var8.add((Field)var9);
         }

         if (var7) {
            this._passwordHFM = (HorizontalFieldManager)(new Object());
            var8.add(this._passwordHFM);
            this._numericPassword = true;
            this.resetPasswordField(false);
         } else {
            FrameLayout var10 = this.createPasswordField();
            var8.add(var10);
         }
      }

      Object var11 = new Object(12884901888L);
      this._ok = (ButtonField)(new Object(CommonResource.getString(100)));
      this._ok.setChangeListener(this);
      ((HorizontalFieldManager)var11).add(this._ok);
      this._cancel = (ButtonField)(new Object(CommonResource.getString(10005)));
      this._cancel.setChangeListener(this);
      ((HorizontalFieldManager)var11).add(this._cancel);
      var8.add((Field)var11);
      VendorModuleStackDialog.populateVendorApplicationModulesStack(var8);
   }

   private FrameLayout createPasswordField() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private void resetPasswordField(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void onUiEngineAttached(boolean var1) {
      super.onUiEngineAttached(var1);
      if (var1) {
         if (this._passwordField != null) {
            this._passwordField.setFocus();
            return;
         }

         if (this._ok != null) {
            this._ok.setFocus();
         }
      }
   }

   public TicketDialog(RichTextField var1, boolean var2, String var3, boolean var4, long var5) {
      this(var1, var2, var3, var4, var5, false);
   }

   private synchronized boolean accept() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private synchronized boolean cancel() {
      if (!this.isCancelAllowed()) {
         return false;
      } else if (!this._isClosed) {
         this._isClosed = true;
         this.close(-1);
         return true;
      } else {
         return true;
      }
   }

   public TicketDialog(RichTextField var1, boolean var2, String var3, boolean var4) {
      this(var1, var2, var3, var4, 0);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
