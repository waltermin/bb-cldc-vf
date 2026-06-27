package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

public class VerticalSpacerField extends Field {
   private int _height;

   public VerticalSpacerField(int var1) {
      super(36028797018963968L);
      this._height = var1;
   }

   @Override
   protected void layout(int var1, int var2) {
      this.setExtent(0, this._height);
   }

   @Override
   protected void paint(Graphics var1) {
   }
}
