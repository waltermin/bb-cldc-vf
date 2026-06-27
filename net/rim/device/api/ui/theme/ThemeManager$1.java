package net.rim.device.api.ui.theme;

import net.rim.device.api.util.Comparator;
import net.rim.device.api.util.StringUtilities;

class ThemeManager$1 implements Comparator {
   private final ThemeManager this$0;

   ThemeManager$1(ThemeManager var1) {
      this.this$0 = var1;
   }

   @Override
   public int compare(Object var1, Object var2) {
      Theme$Factory var3 = (Theme$Factory)var1;
      Theme$Factory var4 = (Theme$Factory)var2;
      return StringUtilities.compareToIgnoreCase(var3.getName(), var4.getName(), 1701707776);
   }
}
