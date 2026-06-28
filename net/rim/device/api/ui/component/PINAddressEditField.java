package net.rim.device.api.ui.component;

import net.rim.device.api.ui.text.TextFilter;
import net.rim.device.internal.util.AddressUtilities;

public final class PINAddressEditField extends EditField {
   public PINAddressEditField(String label, String initialValue) {
      super(label, null, Integer.MAX_VALUE, 1073741824);
      if (this.validate(initialValue)) {
         super.setText(initialValue);
      }

      super.setFilter((TextFilter)(new Object()));
   }

   public PINAddressEditField(String label, String initialValue, int maxNumChars) {
      super(label, null, maxNumChars, 1073741824);
      if (this.validate(initialValue)) {
         super.setText(initialValue);
      }

      super.setFilter((TextFilter)(new Object()));
   }

   @Override
   public final boolean validate(String text) {
      return validateText(text);
   }

   public static final boolean validateCharacter(char character) {
      switch (character) {
         case '\n':
         case '\r':
            return false;
         default:
            return character >= '0' && character <= '9' || character >= 'A' && character <= 'F';
      }
   }

   public static final boolean validateText(String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected final int insert(String text, int context) {
      return super.insert(AddressUtilities.removePrefixes(text), context);
   }
}
