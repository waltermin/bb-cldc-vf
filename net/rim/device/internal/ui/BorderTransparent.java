package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;

public class BorderTransparent extends Border {
   public BorderTransparent(int var1, int var2, int var3, int var4) {
      super(var1, var2, var3, var4);
      this.setTransparent(true);
   }

   @Override
   public void paint(Graphics var1, XYRect var2) {
   }
}
