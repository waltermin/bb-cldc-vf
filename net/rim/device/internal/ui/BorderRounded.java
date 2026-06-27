package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;

public class BorderRounded extends Border {
   private boolean _filled;
   private boolean _dashed;
   private int _arcWidth;
   private int _arcHeight;
   private Border _outerBackground;
   public static final int FILLED;
   public static final int DASHED;
   public static final int TRANSPARENT;

   public BorderRounded(int var1, int var2, int var3, int var4, int var5) {
   }

   @Override
   public void paint(Graphics var1, XYRect var2) {
      int var3 = var1.getColor();
      var1.setColor(var1.getBackgroundColor());
      this._outerBackground.paint(var1, var2);
      var1.setColor(var3);
      if (this._filled) {
         var1.fillRoundRect(var2.x + 1, var2.y + 1, var2.width - 2, var2.height - 2, this._arcWidth, this._arcHeight);
      }

      if (this._dashed) {
         var1.setStipple(-858993460);
      }

      var1.drawRoundRect(var2.x + 1, var2.y + 1, var2.width - 2, var2.height - 2, this._arcWidth, this._arcHeight);
      if (this._dashed) {
         var1.setStipple(-1);
      }
   }
}
