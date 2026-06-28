package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.RadioButtonField;
import net.rim.device.api.ui.component.RadioButtonGroup;

final class ListChoice extends BasicChoice {
   private RadioButtonGroup _radioGroup;
   private ChoiceGroup _choiceGroupFacade;

   ListChoice(ChoiceGroup facade, int choiceType) {
      super._type = choiceType;
      this._choiceGroupFacade = facade;
      if (choiceType == 1) {
         this._radioGroup = (RadioButtonGroup)(new Object());
      }
   }

   @Override
   final Field addToForm(FieldChangeListener changeListener) {
      super._changeListener = changeListener;
      switch (super._type) {
         case 1:
            this._radioGroup.setChangeListener(null);
            this._radioGroup.setChangeListener(changeListener);
            break;
         default:
            for (int i = 0; i < super._container.getFieldCount(); i++) {
               Field field = super._container.getField(i);
               field.setChangeListener(null);
               field.setChangeListener(changeListener);
            }
      }

      return super._container;
   }

   @Override
   public final int getSelectedIndex() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   protected final String doGetString(int elementNum) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   protected final Image doGetImage(int elementNum) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   protected final void doInsert(int elementNum, String stringElement, Image imageElement) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected final void doDelete(int elementNum) {
      boolean mustAdjustSelectedIndex = elementNum < this.getSelectedIndex();
      Field field = this.getField(elementNum);
      field.setChangeListener(null);
      super._container.delete(field);
      if (super._type == 1) {
         RadioButtonField radio = (RadioButtonField)field;
         boolean selected = radio.isSelected();
         this._radioGroup.remove(radio);
         if (selected && this.size() - 1 > 0) {
            this._radioGroup.setSelectedIndex(0);
            return;
         }
      } else if (super._type == 3 && !super._onScreen && mustAdjustSelectedIndex) {
         this.doSetSelectedIndex(this.getSelectedIndex() - 1, true);
      }
   }

   @Override
   protected final void doSet(int elementNum, String stringPart, Image imagePart) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected final boolean doIsSelected(int elementNum) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   protected final void doSetSelectedIndex(int elementNum, boolean selected) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   protected final void doSetSelectedFlags(boolean[] selectedArray) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }
}
