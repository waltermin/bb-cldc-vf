package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.internal.ui.IconCollection;

class VolumeControlDialog$VolumeGraphicsField extends Field {
   private int _index;
   private IconCollection _icons;
   private static final int NUM_ICONS;
   private static final int DEFAULT_WIDTH;
   private static final int DEFAULT_HEIGHT;

   public void changeIndex(int var1) {
      if (this._index != var1) {
         this._index = var1;
         this.invalidate();
      }
   }

   @Override
   protected void layout(int var1, int var2) {
      this.setExtent(this.getIconWidth(), this.getIconHeight());
   }

   private int getIconWidth() {
      return this._icons.getWidth(30, 30);
   }

   private int getIconHeight() {
      return this._icons.getHeight(30, 30);
   }

   @Override
   public void paint(Graphics var1) {
      this._icons.paint(var1, 0, 0, this.getIconWidth(), this.getIconHeight(), 0, this._index);
   }
}
