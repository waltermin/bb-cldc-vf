package net.rim.device.api.ui.text;

public class UppercaseTextFilter extends TextFilter {
   @Override
   public char convert(char var1, int var2) {
      return Character.toUpperCase(var1);
   }

   @Override
   public boolean validate(char var1) {
      return Character.isUpperCase(var1);
   }

   @Override
   public long getPreferredInputStyle() {
      return 1073741824;
   }
}
