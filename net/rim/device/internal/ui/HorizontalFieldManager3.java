package net.rim.device.internal.ui;

import net.rim.device.api.ui.container.HorizontalFieldManager;

public class HorizontalFieldManager3 extends HorizontalFieldManager {
   private static final int MAX_EXTENT;

   public HorizontalFieldManager3() {
      super(0);
   }

   public HorizontalFieldManager3(long var1) {
      super(var1);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      super.sublayout(var1, Math.min(var2, this.getPreferredHeight()));
   }
}
