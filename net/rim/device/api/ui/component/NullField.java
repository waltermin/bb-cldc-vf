package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

public class NullField extends Field {
   public NullField() {
      this(0);
   }

   public NullField(long var1) {
      super(validateStyle(var1));
   }

   @Override
   public int getPreferredWidth() {
      return 0;
   }

   @Override
   public int getPreferredHeight() {
      return 0;
   }

   @Override
   protected void layout(int var1, int var2) {
      this.setExtent(0, 0);
   }

   @Override
   protected void paint(Graphics var1) {
   }

   private static long validateStyle(long var0) {
      if ((var0 & 54043195528445952L) == 0) {
         var0 |= 18014398509481984L;
      }

      return var0;
   }
}
