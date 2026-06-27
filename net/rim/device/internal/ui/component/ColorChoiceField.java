package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ObjectChoiceField;

public class ColorChoiceField extends ObjectChoiceField {
   public static ColorChoiceField$NamedColor[] _defaultColors;

   public ColorChoiceField() {
      super(null, null, 0, 268435456);
   }

   @Override
   protected void drawChoice(int var1, Graphics var2, int var3, int var4, int var5, int var6) {
      int var7 = this.getHeightOfChoices();
      int var8 = ((ColorChoiceField$NamedColor)this.getChoice(var1)).getColor();
      if (var8 >= 0) {
         int var9 = var2.getColor();
         var2.setColor(var8);
         var2.fillRect(var3 + 1, var4 + 1, var7 - 2, var7 - 2);
         var2.setColor(var9);
         var2.drawRect(var3 + 1, var4 + 1, var7 - 2, var7 - 2);
         var6 -= var7;
         var3 += var7;
      }

      super.drawChoice(var1, var2, var3, var4, var5, var6);
   }

   @Override
   protected int getWidthOfChoice(int var1) {
      int var2 = super.getWidthOfChoice(var1);
      if (((ColorChoiceField$NamedColor)this.getChoice(var1)).getColor() >= 0) {
         var2 += this.getHeightOfChoices();
      }

      return var2;
   }

   public int getSelectedColor() {
      return ((ColorChoiceField$NamedColor)this.getChoice(this.getSelectedIndex())).getColor();
   }
}
