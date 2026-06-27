package net.rim.device.api.ui.text;

import net.rim.device.api.ui.Keypad;
import net.rim.device.api.util.CharacterUtilities;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.im.layout.SLKeyLayout;

public class HexadecimalTextFilter extends TextFilter {
   @Override
   public char convert(char var1, int var2) {
      if (InputContext.getInstance().isSureType()) {
         if (Character.isUpperCase(var1) && this.validate(var1)) {
            return var1;
         }

         if ((var2 & 1) != 0) {
            SLKeyLayout var3 = Keypad.getLayout();
            int var4 = var3.getOriginalKeyCode(var1, SLKeyLayout.convertStatusToModifiers(var2));
            var1 = Keypad.map(var4, 2);
            var2 = 0;
         }
      }

      if ((var2 & 1) != 0) {
         char var5 = CharacterUtilities.toUpperCase(Keypad.getUnaltedChar(var1), 1701707776);
         if (this.validate(var5)) {
            return var5;
         }
      }

      if ((var2 & 32768) != 0) {
         return CharacterUtilities.toUpperCase(var1, 1701707776);
      }

      if (this.validate(var1)) {
         return var1;
      }

      char var6 = Keypad.getAltedChar(var1);
      return this.validate(var6) ? var6 : CharacterUtilities.toUpperCase(var1, 1701707776);
   }

   @Override
   public boolean validate(char var1) {
      return var1 >= '0' && var1 <= '9' || var1 >= 'A' && var1 <= 'F';
   }

   @Override
   public long getPreferredInputStyle() {
      return 1140850688;
   }
}
