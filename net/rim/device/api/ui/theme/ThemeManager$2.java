package net.rim.device.api.ui.theme;

import net.rim.device.api.util.Comparator;
import net.rim.device.api.util.StringUtilities;

class ThemeManager$2 implements Comparator {
   private final ThemeManager this$0;

   ThemeManager$2(ThemeManager var1) {
      this.this$0 = var1;
   }

   @Override
   public int compare(Object var1, Object var2) {
      Object var3 = var1;
      Object var4 = var2;
      return StringUtilities.compareToIgnoreCase((String)var3, ((Theme$Factory)var4).getName(), 1701707776);
   }
}
