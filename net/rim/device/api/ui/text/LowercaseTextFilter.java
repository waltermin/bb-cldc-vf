package net.rim.device.api.ui.text;

public class LowercaseTextFilter extends TextFilter {
   @Override
   public char convert(char var1, int var2) {
      return Character.toLowerCase(var1);
   }

   @Override
   public boolean validate(char var1) {
      return Character.isLowerCase(var1);
   }
}
