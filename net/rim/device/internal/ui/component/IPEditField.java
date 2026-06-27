package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.text.TextFilter;

public class IPEditField extends EditField {
   private boolean keyRepeatProcessed;
   private int lastKeyPosition;

   public IPEditField(String var1, String var2) {
      super(var1, var2);
      this.setFilter((TextFilter)(new Object()));
   }

   public IPEditField(String var1, String var2, int var3) {
      super(var1, var2, var3, 0);
      this.setFilter((TextFilter)(new Object()));
   }

   public IPEditField(String var1, String var2, int var3, int var4) {
      super(var1, var2, var3, 0);
      this.setFilter((TextFilter)(new Object(var4)));
   }

   @Override
   protected boolean insert(char var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      this.keyRepeatProcessed = false;
      this.lastKeyPosition = this.getCursorPosition();
      return super.keyDown(var1, var2);
   }

   @Override
   protected boolean keyRepeat(int var1, int var2) {
      boolean var3 = false;
      if (!this.keyRepeatProcessed) {
         char var4 = Keypad.getAltedChar(Keypad.map(var1));
         if (Character.isDigit(var4)) {
            if (this.lastKeyPosition != this.getCursorPosition()) {
               this.backspace();
            }

            this.insert(var4, 0);
            this.keyRepeatProcessed = true;
            var3 = true;
         }
      }

      if (!var3) {
         var3 = super.keyRepeat(var1, var2);
      }

      return var3;
   }

   public static final int parseIpAddr(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void appendIpAddr(StringBuffer var0, int var1) {
      for (byte var2 = 24; var2 >= 0; var2 -= 8) {
         int var3 = var1 >>> var2 & 0xFF;
         var0.append(var3);
         if (var2 != 0) {
            var0.append('.');
         }
      }
   }

   public static final void appendIpAddr(StringBuffer var0, byte[] var1) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
