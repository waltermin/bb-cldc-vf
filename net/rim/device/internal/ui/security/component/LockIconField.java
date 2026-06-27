package net.rim.device.internal.ui.security.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

public class LockIconField extends Field {
   private int _x;
   private int _y;

   public LockIconField(int var1, int var2) {
      super(36028797018963968L);
      this._x = var1;
      this._y = var2;
   }

   @Override
   protected void layout(int var1, int var2) {
      this.setExtent(this._x + 5, this._y + 5);
   }

   @Override
   protected void paint(Graphics var1) {
      drawLock(var1, this._x, this._y);
   }

   public static void drawLock(Graphics var0, int var1, int var2) {
      int var3 = var0.getColor();
      var0.setColor(16711680);
      var0.drawRect(var1, var2 + 2, 5, 3);
      var0.drawPoint(var1 + 1, var2 + 1);
      var0.drawPoint(var1 + 2, var2);
      var0.drawPoint(var1 + 3, var2 + 1);
      var0.drawPoint(var1 + 1, var2 + 3);
      var0.drawPoint(var1 + 3, var2 + 3);
      var0.setColor(var3);
   }
}
