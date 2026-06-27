package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

public class HorizontalSpacerField extends Field {
   private int _width;

   public HorizontalSpacerField(int var1) {
      super(36028797018963968L);
      this._width = var1;
   }

   @Override
   protected void layout(int var1, int var2) {
      this.setExtent(this._width, 0);
   }

   @Override
   protected void paint(Graphics var1) {
   }
}
