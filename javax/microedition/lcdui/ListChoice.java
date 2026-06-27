package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.RadioButtonField;
import net.rim.device.api.ui.component.RadioButtonGroup;

final class ListChoice extends BasicChoice {
   private RadioButtonGroup _radioGroup;
   private ChoiceGroup _choiceGroupFacade;

   ListChoice(ChoiceGroup var1, int var2) {
      super._type = var2;
      this._choiceGroupFacade = var1;
      if (var2 == 1) {
         this._radioGroup = (RadioButtonGroup)(new Object());
      }
   }

   @Override
   final Field addToForm(FieldChangeListener var1) {
      super._changeListener = var1;
      switch (super._type) {
         case 1:
            this._radioGroup.setChangeListener(null);
            this._radioGroup.setChangeListener(var1);
            break;
         default:
            for (int var2 = 0; var2 < super._container.getFieldCount(); var2++) {
               Field var3 = super._container.getField(var2);
               var3.setChangeListener(null);
               var3.setChangeListener(var1);
            }
      }

      return super._container;
   }

   @Override
   public final int getSelectedIndex() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected final String doGetString(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   protected final Image doGetImage(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   protected final void doInsert(int var1, String var2, Image var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected final void doDelete(int var1) {
      boolean var2 = var1 < this.getSelectedIndex();
      Field var3 = this.getField(var1);
      var3.setChangeListener(null);
      super._container.delete(var3);
      if (super._type == 1) {
         Object var4 = var3;
         boolean var5 = ((RadioButtonField)var4).isSelected();
         this._radioGroup.remove((RadioButtonField)var4);
         if (var5 && this.size() - 1 > 0) {
            this._radioGroup.setSelectedIndex(0);
            return;
         }
      } else if (super._type == 3 && !super._onScreen && var2) {
         this.doSetSelectedIndex(this.getSelectedIndex() - 1, true);
      }
   }

   @Override
   protected final void doSet(int var1, String var2, Image var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected final boolean doIsSelected(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected final void doSetSelectedIndex(int var1, boolean var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   protected final void doSetSelectedFlags(boolean[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
