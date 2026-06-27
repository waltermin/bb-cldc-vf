package net.rim.device.api.ui.component;

import net.rim.device.api.ui.text.TextFilter;
import net.rim.device.internal.util.AddressUtilities;

public final class PINAddressEditField extends EditField {
   public PINAddressEditField(String var1, String var2) {
      super(var1, null, Integer.MAX_VALUE, 1073741824);
      if (this.validate(var2)) {
         super.setText(var2);
      }

      super.setFilter((TextFilter)(new Object()));
   }

   public PINAddressEditField(String var1, String var2, int var3) {
      super(var1, null, var3, 1073741824);
      if (this.validate(var2)) {
         super.setText(var2);
      }

      super.setFilter((TextFilter)(new Object()));
   }

   @Override
   public final boolean validate(String var1) {
      return validateText(var1);
   }

   public static final boolean validateCharacter(char var0) {
      switch (var0) {
         case '\n':
         case '\r':
            return false;
         default:
            return var0 >= '0' && var0 <= '9' || var0 >= 'A' && var0 <= 'F';
      }
   }

   public static final boolean validateText(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected final int insert(String var1, int var2) {
      return super.insert(AddressUtilities.removePrefixes(var1), var2);
   }
}
