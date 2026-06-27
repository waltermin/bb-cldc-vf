package net.rim.device.api.ui.container;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.theme.Tag;

public class PopupScreen extends Screen {
   private static Tag TAG;
   private static final int SCALE_FACTOR;

   public PopupScreen(Manager var1) {
      this(var1, 0);
   }

   public PopupScreen(Manager var1, long var2) {
      super(var1, var2);
      this.setTag(TAG);
      int var4 = Display.getWidth() * 5 / 100;
      int var5 = Display.getHeight() * 5 / 100;
      this.setMargin(var5, var4, var5, var4);
   }

   @Override
   protected void applyTheme() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void sublayout(int var1, int var2) {
      var1 -= this.getMarginLeft() + this.getMarginRight();
      var2 -= this.getMarginTop() + this.getMarginBottom();
      this.setPositionDelegate(0, 0);
      this.layoutDelegate(var1, var2);
      XYRect var3 = this.getDelegate().getExtent();
      int var4 = var1 - var3.width >> 1;
      int var5 = var2 - var3.height >> 1;
      this.setPosition(var4 + this.getMarginLeft(), var5 + this.getMarginTop());
      this.setExtent(var3.width, var3.height);
   }

   @Override
   protected void paint(Graphics var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }
}
