package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.text.TextFilter;
import net.rim.vm.WeakReference;

public class PhoneNumberEditField extends EditField {
   private boolean _performValidation;
   private static WeakReference _validationBufferWR;

   public PhoneNumberEditField(String var1, String var2) {
      this(var1, var2, Integer.MAX_VALUE, 6);
   }

   public PhoneNumberEditField(String var1, String var2, int var3, int var4) {
      super(var1, var2, var3, 2202244481024L);
      this.setFilter(TextFilter.get(var4));
      if (var2 != null) {
         this._performValidation = super.validate(var2);
      } else {
         this._performValidation = true;
      }
   }

   @Override
   protected boolean keyRepeat(int var1, int var2) {
      boolean var3 = super.keyRepeat(var1, var2);
      if (Keypad.key(var1) == 261) {
         var3 = true;
      }

      return var3;
   }

   @Override
   protected boolean insert(char var1, int var2) {
      boolean var3 = (var2 & 32768) == 0;
      boolean var4 = (var2 & 1) != 0;
      if (!this.validate(var1) && var3 && !var4) {
         var1 = Keypad.getAltedChar(var1);
      }

      return super.insert(var1, var2);
   }

   @Override
   public boolean validate(char var1) {
      if (var1 == 149) {
         return false;
      } else {
         return !this._performValidation ? true : super.validate(var1);
      }
   }

   @Override
   public boolean validate(String var1) {
      return !this._performValidation ? true : super.validate(var1);
   }

   public static String cleanPhoneNumber(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static boolean validCharacter(char var0, int var1) {
      if (Character.isDigit(var0)) {
         return true;
      } else {
         return var0 == '+' ? var1 == 0 : var0 == '#' || var0 == '*';
      }
   }

   private static boolean cleanPhoneNumber(String var0, StringBuffer var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
