package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.DialogFieldManager;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.component.PopupDialog;

class MIDletSecurity$PermDialog extends PopupDialog {
   private DialogFieldManager _dfm;
   private Field _yesButton;
   private Field _noButton;
   int _setting;

   MIDletSecurity$PermDialog(int var1, int var2, ApplicationDescriptor var3, String var4) {
      super((Manager)(new Object()), 33554432);
      this.setStatusPriority(-2147483643);
      String var5 = this.getMessage(var1, var3, var4);
      String[] var6 = CommonResource.getStringArray(10012);
      this._dfm = (DialogFieldManager)this.getDelegate();
      Object var7 = new Object(var5, 45035996273704960L);
      this._dfm.setMessage((RichTextField)var7);
      this._yesButton = (Field)(new Object(var6[0]));
      this._dfm.addCustomField(this._yesButton);
      this._noButton = (Field)(new Object(var6[1]));
      this._dfm.addCustomField(this._noButton);
      switch (var2) {
         case 2:
            this._yesButton.setFocus();
            break;
         default:
            this._noButton.setFocus();
      }

      this._setting = 0;
   }

   private String getMessage(int var1, ApplicationDescriptor var2, String var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      return this.doAction() ? true : super.trackwheelClick(var1, var2);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      return var1 == '\n' && this.doAction() ? true : super.keyChar(var1, var2, var3);
   }

   private boolean doAction() {
      Field var1 = this._dfm.getLeafFieldWithFocus();
      if (var1 == this._yesButton) {
         this._setting = 6;
         this.close(0);
         return true;
      } else if (var1 == this._noButton) {
         this._setting = 0;
         this.close(0);
         return true;
      } else {
         return false;
      }
   }
}
