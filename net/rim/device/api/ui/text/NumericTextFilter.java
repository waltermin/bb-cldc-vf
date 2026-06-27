package net.rim.device.api.ui.text;

import net.rim.device.api.ui.Keypad;
import net.rim.device.api.util.AbstractString;

public class NumericTextFilter extends TextFilter {
   private final int _style;
   public static final int ALLOW_NEGATIVE;
   public static final int ALLOW_DECIMAL;

   public NumericTextFilter() {
      this(0);
   }

   public NumericTextFilter(int var1) {
      this._style = var1;
   }

   @Override
   public char convert(char var1, int var2) {
      if (!this.validate(var1)) {
         var1 = Keypad.getAltedChar(var1);
      }

      return var1;
   }

   @Override
   public char convert(char var1, AbstractString var2, int var3, int var4) {
      if (!this.validate(var1, var2, var3)) {
         var1 = Keypad.getAltedChar(var1);
      }

      return var1;
   }

   @Override
   public boolean validate(char var1) {
      return var1 >= '0' && var1 <= '9' || var1 == 128 || var1 == '-' && (this._style & 1) != 0 || var1 == '.' && (this._style & 2) != 0;
   }

   @Override
   public boolean validate(char var1, AbstractString var2, int var3) {
      return var2 == null
         || var1 >= '0' && var1 <= '9'
         || var1 == 128
         || var1 == '-' && (this._style & 1) != 0 && var3 == 0 && (var2.length() == 0 || var2.charAt(0) != '-')
         || var1 == '.' && (this._style & 2) != 0;
   }

   @Override
   public boolean validate(AbstractString var1) {
      if (var1 != null) {
         int var2 = var1.length();

         for (int var3 = 0; var3 < var2; var3++) {
            char var4 = var1.charAt(var3);
            if (var4 == '-' && (var3 != 0 || (this._style & 1) == 0)) {
               return false;
            }

            if (!this.validate(var4)) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public long getPreferredInputStyle() {
      return 1090519040;
   }
}
