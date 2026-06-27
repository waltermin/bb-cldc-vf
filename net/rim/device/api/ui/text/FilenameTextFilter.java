package net.rim.device.api.ui.text;

public class FilenameTextFilter extends TextFilter {
   @Override
   public char convert(char var1, int var2) {
      return var1;
   }

   @Override
   public boolean validate(char var1) {
      switch (var1) {
         case '"':
         case '*':
         case '/':
         case ':':
         case '<':
         case '>':
         case '?':
         case '\\':
         case '|':
            return false;
         default:
            return var1 >= ' ';
      }
   }

   @Override
   public long getPreferredInputStyle() {
      return 268470272;
   }
}
