package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;

class SpacerField extends Field {
   private int _width;
   private int _height;

   SpacerField(int var1, int var2) {
      super(3458764513820540928L);
      this.setSize(var1, var2);
   }

   void setSize(int var1, int var2) {
      this._width = var1;
      this._height = var2;
      this.invalidate();
   }

   @Override
   protected void layout(int var1, int var2) {
      this.setExtent(this._width, this._height);
   }

   @Override
   public int getPreferredWidth() {
      return this._width;
   }

   @Override
   public int getPreferredHeight() {
      return this._height;
   }

   @Override
   public boolean isFocusable() {
      return false;
   }

   @Override
   protected void paint(net.rim.device.api.ui.Graphics var1) {
   }
}
