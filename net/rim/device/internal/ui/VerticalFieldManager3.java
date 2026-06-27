package net.rim.device.internal.ui;

import net.rim.device.api.ui.container.VerticalFieldManager;

public class VerticalFieldManager3 extends VerticalFieldManager {
   private static final int MAX_EXTENT;

   public VerticalFieldManager3() {
      super(0);
   }

   public VerticalFieldManager3(long var1) {
      super(var1);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      super.sublayout(Math.min(var1, this.getPreferredWidth()), var2);
   }
}
