package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ObjectChoiceField;

public class ColorChoiceField extends ObjectChoiceField {
   public static ColorChoiceField$NamedColor[] _defaultColors;

   public ColorChoiceField() {
      super(null, null, 0, 268435456);
   }

   @Override
   protected void drawChoice(int index, Graphics graphics, int x, int y, int flags, int width) {
      int heightOfChoices = this.getHeightOfChoices();
      int color = ((ColorChoiceField$NamedColor)this.getChoice(index)).getColor();
      if (color >= 0) {
         int oldColor = graphics.getColor();
         graphics.setColor(color);
         graphics.fillRect(x + 1, y + 1, heightOfChoices - 2, heightOfChoices - 2);
         graphics.setColor(oldColor);
         graphics.drawRect(x + 1, y + 1, heightOfChoices - 2, heightOfChoices - 2);
         width -= heightOfChoices;
         x += heightOfChoices;
      }

      super.drawChoice(index, graphics, x, y, flags, width);
   }

   @Override
   protected int getWidthOfChoice(int index) {
      int width = super.getWidthOfChoice(index);
      if (((ColorChoiceField$NamedColor)this.getChoice(index)).getColor() >= 0) {
         width += this.getHeightOfChoices();
      }

      return width;
   }

   public int getSelectedColor() {
      return ((ColorChoiceField$NamedColor)this.getChoice(this.getSelectedIndex())).getColor();
   }
}
