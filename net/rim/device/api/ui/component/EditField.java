package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Keypad;

public class EditField extends BasicEditField {
   private int _lastKeyPressed;
   private int _rollerCharacterIndex = -1;

   public EditField() {
      this(null, null, 1000000, validateStyle(0));
   }

   public EditField(long var1) {
      this(null, null, 1000000, validateStyle(var1));
   }

   public EditField(String var1, String var2) {
      this(var1, var2, 1000000, validateStyle(0));
   }

   public EditField(String var1, String var2, int var3, long var4) {
      super(var1, var2, var3, validateStyle(var4));
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      int var3 = Keypad.key(var1);
      int var4 = Keypad.status(var1);
      char var5 = Character.toUpperCase(Keypad.map(var3, var4));
      this.setLastKeyPressed(var5);
      this._rollerCharacterIndex = -1;
      return super.keyDown(var1, var2);
   }

   @Override
   protected boolean keyRepeat(int var1, int var2) {
      return !this.isEditable() ? false : super.keyRepeat(var1, var2);
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   protected boolean isEnteringRollerCharacter() {
      return this._rollerCharacterIndex != -1;
   }

   private static long validateStyle(long var0) {
      if ((var0 & 3298534883328L) == 0) {
         var0 |= 1099511627776L;
      }

      return var0;
   }

   protected int getLastKeyPressed() {
      return this._lastKeyPressed;
   }

   protected void setLastKeyPressed(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
