package net.rim.device.api.ui.component;

public class ObjectComboField$Controller extends ComboFieldController {
   private final ObjectComboField this$0;

   public ObjectComboField$Controller(ObjectComboField var1) {
      this.this$0 = var1;
   }

   @Override
   protected void textChanged(String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void select(Object var1, int var2) {
      if (var1 != null) {
         super._comboField.setText(var1.toString());
      }

      super._comboField.hideDropList();
   }

   @Override
   protected void escape() {
      super._comboField.hideDropList();
   }
}
