package net.rim.device.api.ui.container;

import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;

public class FullScreen extends Screen {
   public FullScreen() {
      this(0);
   }

   public FullScreen(long var1) {
      this(null, var1);
   }

   public FullScreen(Manager var1, long var2) {
      super(var1 != null ? var1 : new VerticalFieldManager(validateStyleVFM(var2)), validateStyle(var2));
   }

   @Override
   protected void sublayout(int var1, int var2) {
      this.setPosition(0, 0);
      this.setExtent(var1, var2);
      this.setPositionDelegate(0, 0);
      this.layoutDelegate(var1, var2);
   }

   private static long validateStyle(long var0) {
      return var0 & -289725711965487105L;
   }

   private static long validateStyleVFM(long var0) {
      var0 &= 289725711965487104L;
      return var0 | 3458764513820540928L;
   }
}
