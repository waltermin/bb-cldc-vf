package net.rim.device.api.ui.text;

import net.rim.device.api.util.AbstractString;

public class EmailAddressTextFilter extends TextFilter {
   @Override
   public char convert(char var1, int var2) {
      return var1;
   }

   @Override
   public boolean validate(char var1) {
      if (var1 <= 31) {
         return false;
      }

      switch (var1) {
         case '"':
         case '<':
         case '>':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '`':
         case '{':
         case '|':
         case '}':
         case '\u007f':
            return false;
         default:
            return true;
      }
   }

   @Override
   public boolean validate(AbstractString var1) {
      if (var1 != null) {
         int var2 = var1.length();

         for (int var3 = 0; var3 < var2; var3++) {
            char var4 = var1.charAt(var3);
            if (!this.validate(var4)) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public long getPreferredInputStyle() {
      return 134266880;
   }
}
