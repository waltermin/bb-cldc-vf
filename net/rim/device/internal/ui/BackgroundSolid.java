package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;

class BackgroundSolid extends Background {
   private int _color;

   BackgroundSolid(int var1) {
      this._color = var1;
   }

   @Override
   public void draw(Graphics var1, XYRect var2) {
      int var3 = var1.getColor();
      var1.setColor(this._color);
      var1.fillRect(var2.x, var2.y, var2.width, var2.height);
      var1.setColor(var3);
   }

   @Override
   public boolean isTransparent() {
      return false;
   }
}
