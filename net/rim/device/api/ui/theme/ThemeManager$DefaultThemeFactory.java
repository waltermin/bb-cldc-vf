package net.rim.device.api.ui.theme;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Graphics;

class ThemeManager$DefaultThemeFactory extends Theme$Factory {
   ThemeManager$DefaultThemeFactory() {
      int displayDepth = ThemeManager.log2(Graphics.getNumColors());
      this.setTargetDisplay(Display.getWidth(), Display.getHeight(), displayDepth);
      this.setRemovable(false);
   }

   @Override
   public String getName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void populate(Theme$Writer theme) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
