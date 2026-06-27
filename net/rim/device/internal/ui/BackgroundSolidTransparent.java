package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;

class BackgroundSolidTransparent extends Background {
   private int _color;
   private int _alpha;

   BackgroundSolidTransparent(int var1) {
      this._alpha = var1 >>> 24;
      this._color = var1 & 16777215;
   }

   @Override
   public void draw(Graphics var1, XYRect var2) {
      int var3 = var1.getGlobalAlpha();
      int var4 = var1.getColor();
      var1.setGlobalAlpha(this._alpha);
      var1.setColor(this._color);
      var1.fillRect(var2.x, var2.y, var2.width, var2.height);
      var1.setColor(var4);
      var1.setGlobalAlpha(var3);
   }

   @Override
   public boolean isTransparent() {
      return this._alpha < 255;
   }
}
