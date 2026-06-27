package net.rim.device.api.ui.text;

import net.rim.device.api.ui.Keypad;
import net.rim.device.api.util.AbstractString;
import net.rim.device.api.util.CharacterUtilities;

public class IPTextFilter extends TextFilter {
   private int _flags;
   public static final int NO_PORTS;
   public static final int NUMERIC_IP_ONLY;

   public IPTextFilter() {
   }

   public IPTextFilter(int var1) {
      this._flags = var1;
   }

   @Override
   public char convert(char var1, int var2) {
      if (!this.validate(var1)) {
         if ((this._flags & 2) != 0) {
            return Keypad.getAltedChar(var1);
         }

         var1 = CharacterUtilities.toLowerCase(var1, 1701707776);
      }

      return var1;
   }

   @Override
   public long getPreferredInputStyle() {
      return 49152 | ((this._flags & 2) != 0 ? 5 : 1) << 24;
   }

   @Override
   public boolean validate(AbstractString var1) {
      if (var1 != null && var1.length() != 0) {
         int var2 = var1.length();
         int var3 = 0;
         int var4 = 0;
         boolean var5 = false;

         for (int var6 = 0; var6 < var2; var6++) {
            char var7 = var1.charAt(var6);
            if (Character.isLowerCase(var7) || Character.isUpperCase(var7)) {
               if ((this._flags & 2) != 0) {
                  return false;
               }

               var5 = true;
               if (var4 > 0) {
                  return false;
               }
            }

            if (!this.validate(var7)) {
               return false;
            }

            if (var7 == '.') {
               if (++var3 > 3 && !var5) {
                  return false;
               }
            } else if (var7 == ':') {
               if ((this._flags & 1) != 0) {
                  return false;
               }

               if (++var4 > 2) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   @Override
   public boolean validate(char var1) {
      if (var1 == ':') {
         return (this._flags & 1) == 0;
      }

      if (Character.isDigit(var1)) {
         return true;
      }

      if ((this._flags & 2) != 0) {
         if (var1 == '.') {
            return true;
         }
      } else if (Character.isLowerCase(var1) || var1 == '-' || var1 == '.') {
         return true;
      }

      return false;
   }
}
