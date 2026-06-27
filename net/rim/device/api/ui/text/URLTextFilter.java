package net.rim.device.api.ui.text;

public class URLTextFilter extends TextFilter {
   @Override
   public char convert(char var1, int var2) {
      if (var1 == ' ') {
         if ((var2 & 2) == 0) {
            return '.';
         }

         var1 = '/';
      }

      return var1;
   }

   @Override
   public boolean validate(char var1) {
      if (var1 > ' ' && 127 > var1) {
         switch (var1) {
            case '"':
            case '<':
            case '>':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case '|':
               return false;
            default:
               return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public long getPreferredInputStyle() {
      return 117475328;
   }
}
