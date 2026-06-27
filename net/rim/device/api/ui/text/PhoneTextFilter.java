package net.rim.device.api.ui.text;

import net.rim.device.api.ui.Keypad;
import net.rim.device.api.util.AbstractString;
import net.rim.device.api.util.CharacterUtilities;
import net.rim.device.internal.system.InternalServices;
import net.rim.tid.im.layout.SLKeyLayout;

public class PhoneTextFilter extends TextFilter {
   private boolean _isReducedKeyboard = InternalServices.isReducedFormFactor();
   private final int _style;
   public static final int ACCEPT_PAUSE;
   public static final int ACCEPT_WAIT;
   public static final int ACCEPT_CONTROL;
   public static final int ACCEPT_WILD_CARD;
   public static final int ACCEPT_ALPHA;
   public static final int ACCEPT_FORMATTING;
   public static final int ACCEPT_EXTENSION;
   public static final int ACCEPT_INTERNATIONAL;
   public static final int INTERNATIONAL_MUST_BE_FIRST;
   public static final int ACCEPT_EVERYTHING_EXCEPT_WILD_CARD;

   public PhoneTextFilter() {
      this(247);
   }

   public PhoneTextFilter(int var1) {
      this._style = var1;
   }

   @Override
   public char convert(char var1, int var2) {
      if (CharacterUtilities.isUpperCase(var1) && this.validate(var1)) {
         return var1;
      }

      if (!this._isReducedKeyboard || var1 != ' ' && var1 != '0') {
         if ((var2 & 1) == 0) {
            char var5 = Keypad.getAltedChar(var1);
            if (var2 == 0 && this.validate(var5)) {
               return var5;
            }

            if (!this.validate(var1) && (var2 & 32768) == 0) {
               if (var5 != 0) {
                  var1 = var5;
               }

               if (var1 == '!') {
                  var1 = '\uf402';
               }

               if (var1 == ',') {
                  var1 = '\uf3fe';
               }
            }

            return var1;
         } else {
            if (var1 == ' ') {
               return var1;
            }

            SLKeyLayout var3 = Keypad.getLayout();
            int var4 = var3.getOriginalKeyCode(var1, SLKeyLayout.convertStatusToModifiers(var2));
            return Keypad.map(var4, 2);
         }
      } else if ((var2 & 32768) != 0) {
         return var1;
      } else {
         return (char)((var2 & 1) != 0 ? ' ' : '0');
      }
   }

   private static char getNormalChar(char var0) {
      return (char)(var0 == Keypad.getAltedChar('x') ? 'x' : '\u0000');
   }

   @Override
   public boolean validate(char var1) {
      var1 = CharacterUtilities.foldFullWidth(var1);
      switch (var1) {
         case ' ':
         case '(':
         case ')':
         case '-':
         case '.':
            return this.flagSet(32);
         case '#':
         case '*':
            return this.flagSet(4);
         case '+':
            return this.flagSet(128);
         case '?':
            return this.flagSet(8);
         case 'e':
         case 't':
         case 'x':
            return this.flagSet(64);
         case '\uf3fe':
            return this.flagSet(1);
         case '\uf402':
            return this.flagSet(2);
         default:
            if ('0' <= var1 && var1 <= '9') {
               return true;
            } else if ('A' <= var1 && var1 <= 'Z') {
               return this.flagSet(16);
            } else {
               return 913 <= var1 && var1 <= 937 ? this.flagSet(16) : false;
            }
      }
   }

   private boolean flagSet(int var1) {
      return (this._style & var1) != 0;
   }

   @Override
   public boolean validate(AbstractString var1) {
      if (var1 != null) {
         int var2 = var1.length();

         for (int var3 = 0; var3 < var2; var3++) {
            char var4 = var1.charAt(var3);
            if (var4 == '+' && var3 != 0 && this.flagSet(256)) {
               return false;
            }

            if (!this.validate(var4)) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public long getPreferredInputStyle() {
      return 1174405120;
   }
}
