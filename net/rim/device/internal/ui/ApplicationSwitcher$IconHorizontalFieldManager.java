package net.rim.device.internal.ui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.container.HorizontalFieldManager;

class ApplicationSwitcher$IconHorizontalFieldManager extends HorizontalFieldManager {
   ApplicationSwitcher$IconHorizontalFieldManager(long var1) {
      super(var1);
   }

   @Override
   protected void subpaint(Graphics var1) {
      XYRect var2 = var1.getClippingRect();
      int var3 = var2.x;
      int var4 = var2.x + var2.width;
      int var5 = this.getFieldCount();
      int var6 = this.getFieldAtLocation(0, var3);
      if (var6 != -1) {
         for (; var6 < var5; var6++) {
            Field var7 = this.getField(var6);
            if (var7.getLeft() >= var4) {
               return;
            }

            boolean var8 = var6 == this.getFieldWithFocusIndex();
            if (var8) {
               boolean var9 = var1.isDrawingStyleSet(8);
               var1.setDrawingStyle(8, var7 == this.getFieldWithFocus());
               this.paintChild(var1, var7);
               var1.setDrawingStyle(8, var9);
            } else {
               this.paintChild(var1, var7);
            }
         }
      }
   }
}
