package javax.microedition.lcdui;

import net.rim.device.api.ui.component.LabelField;

class CustomLabelField extends LabelField {
   private CustomField _customField;

   public CustomLabelField(String var1, CustomField var2) {
      super(var1, 18014398509481984L);
      this._customField = var2;
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      return this._customField.keyDown(var1, var2);
   }

   @Override
   protected boolean keyUp(int var1, int var2) {
      return this._customField.keyUp(var1, var2);
   }

   @Override
   protected void drawFocus(net.rim.device.api.ui.Graphics var1, boolean var2) {
   }
}
