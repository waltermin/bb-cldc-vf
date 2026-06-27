package net.rim.device.internal.ui.security.component;

import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.proxy.Proxy;
import net.rim.device.internal.ui.component.BackgroundDialog;
import net.rim.device.internal.ui.container.VerticalIndentFieldManager;

public class PermissionDialog extends VendorModuleStackDialog implements GlobalEventListener, FieldChangeListener {
   private boolean _isClosed;
   private CheckboxField _userOptionCheckbox;
   private ButtonField _allow;
   private ButtonField _deny;

   public boolean getUserOptionCheckBoxValue() {
      return this._userOptionCheckbox == null ? false : this._userOptionCheckbox.getChecked();
   }

   public boolean getPermission() {
      BackgroundDialog.showOnProxy(this);
      return this.getCloseReason() != -1;
   }

   @Override
   public void fieldChanged(Field var1, int var2) {
      if (var1 == this._allow) {
         this.allow();
      } else {
         if (var1 == this._deny) {
            this.deny();
         }
      }
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == -7131874474196788121L) {
         Proxy.getInstance().invokeLater(new PermissionDialog$1(this));
      }
   }

   private synchronized boolean deny() {
      if (!this._isClosed) {
         this._isClosed = true;
         this.close(-1);
      }

      return true;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 == 27) {
         return this._deny != null ? this.deny() : true;
      }

      if (var1 == '\n') {
         Field var4 = this.getLeafFieldWithFocus();
         if (var4 == this._deny) {
            return this.deny();
         }

         if (var4 == this._allow) {
            return this.allow();
         }
      }

      return super.keyChar(var1, var2, var3);
   }

   public PermissionDialog(String var1, String var2, int[] var3, int[] var4) {
      super(new VerticalIndentFieldManager(1153220571769602048L), 134217728);
      VerticalIndentFieldManager var5 = (VerticalIndentFieldManager)this.getDelegate();
      if (var1 != null) {
         Object var6 = new Object(var1);
         var5.add((Field)var6);
      }

      if (var2 != null) {
         this._userOptionCheckbox = (CheckboxField)(new Object(var2, true, 1073741824));
         var5.add(this._userOptionCheckbox);
      }

      Object var7 = new Object(12884901888L);
      this._allow = (ButtonField)(new Object(CommonResource.getStringArray(10170)[0]));
      this._allow.setChangeListener(this);
      ((HorizontalFieldManager)var7).add(this._allow);
      this._deny = (ButtonField)(new Object(CommonResource.getStringArray(10170)[1]));
      this._deny.setChangeListener(this);
      ((HorizontalFieldManager)var7).add(this._deny);
      var5.add((Field)var7);
      VendorModuleStackDialog.populateVendorApplicationModulesStack(var5, var3, var4);
      this.setCancelAllowed(true);
   }

   @Override
   public void onUiEngineAttached(boolean var1) {
      if (var1) {
         super.onUiEngineAttached(var1);
         if (this._userOptionCheckbox != null) {
            this._userOptionCheckbox.setFocus();
         } else {
            this._allow.setFocus();
         }

         Proxy.getInstance().addGlobalEventListener(this);
      } else {
         Proxy.getInstance().removeGlobalEventListener(this);
         super.onUiEngineAttached(var1);
      }
   }

   private synchronized boolean allow() {
      if (!this._isClosed) {
         this._isClosed = true;
         this.close(0);
      }

      return true;
   }

   static void access$000(PermissionDialog var0, int var1) {
      var0.close(var1);
   }
}
