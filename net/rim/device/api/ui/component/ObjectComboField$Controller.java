package net.rim.device.api.ui.component;

public class ObjectComboField$Controller extends ComboFieldController {
   private final ObjectComboField this$0;

   public ObjectComboField$Controller(ObjectComboField _1) {
      this.this$0 = _1;
   }

   @Override
   protected void textChanged(String newText, int context) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void select(Object selection, int type) {
      if (selection != null) {
         super._comboField.setText(selection.toString());
      }

      super._comboField.hideDropList();
   }

   @Override
   protected void escape() {
      super._comboField.hideDropList();
   }
}
