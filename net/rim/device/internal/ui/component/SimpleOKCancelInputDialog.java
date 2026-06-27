package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.DialogFieldManager;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.internal.i18n.CommonResource;

public class SimpleOKCancelInputDialog extends SimpleInputDialog implements FieldChangeListener {
   private ButtonField _okButton = (ButtonField)(new Object(CommonResource.getString(100), 65536));
   private ButtonField _cancelButton;
   private HorizontalFieldManager _buttonManager;

   public SimpleOKCancelInputDialog(int var1, String var2) {
      this(var1, var2, 0, 1000000, 0);
   }

   public SimpleOKCancelInputDialog(int var1, String var2, int var3, int var4, long var5) {
      super(var1, var2, var3, var4, var5);
      this._okButton.setChangeListener(this);
      this._cancelButton = (ButtonField)(new Object(CommonResource.getString(10005), 65536));
      this._cancelButton.setChangeListener(this);
      this._buttonManager = (HorizontalFieldManager)(new Object(12884901888L));
      this._buttonManager.add(this._okButton);
      this._buttonManager.add(this._cancelButton);
      Object var7 = this.getDelegate();
      ((DialogFieldManager)var7).addCustomField(this._buttonManager);
      this.setCancelAllowed(true);
   }

   protected Manager getButtonFieldManager() {
      return this._buttonManager;
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
   public boolean navigationClick(int var1, int var2) {
      Field var3 = this.getDelegate().getLeafFieldWithFocus();
      return var3 == this._cancelButton ? this.cancel() : super.navigationClick(var1, var2);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 == '\n') {
         Field var4 = this.getDelegate().getLeafFieldWithFocus();
         if (var4 == this._cancelButton) {
            return this.cancel();
         }
      }

      return super.keyChar(var1, var2, var3);
   }

   @Override
   public void setType(int var1) {
      super.setType(var1);
      if (this._buttonManager != null) {
         this.delete(this._buttonManager);
         this.add(this._buttonManager);
      }
   }
}
