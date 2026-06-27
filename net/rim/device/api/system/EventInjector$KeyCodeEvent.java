package net.rim.device.api.system;

import net.rim.device.api.ui.KeypadUtil;

public class EventInjector$KeyCodeEvent extends EventInjector$Event {
   public static final int KEY_DOWN;
   public static final int KEY_REPEAT;
   public static final int KEY_UP;

   public EventInjector$KeyCodeEvent(int var1, char var2, int var3, int var4) {
      super(2, var1, 0, charToScancode(var2, var3), var4, null, null);
      if (!this.isVaildStatus(var3)) {
         throw new Object();
      }
   }

   public int getKeyCode() {
      int var1 = super._msg.getData0();
      return this.extractKeyCode(var1);
   }

   public void setKeyCode(char var1, int var2) {
      if (!this.isVaildStatus(var2)) {
         throw new Object();
      }

      super._msg.setData0(charToScancode(var1, var2));
   }

   @Override
   public void setStatus(int var1) {
      if (!this.isVaildStatus(var1)) {
         throw new Object();
      }

      int var2 = this.getKeyCode();
      int var3 = var2 << 16;
      var3 |= var1;
      super._msg.setData0(var3);
   }

   @Override
   public int getStatus() {
      int var1 = super._msg.getData0();
      return this.extractStatus(var1);
   }

   public int getTime() {
      return super._msg.getData1();
   }

   public void setTime(int var1) {
      super._msg.setData1(var1);
   }

   private static int charToScancode(char var0, int var1) {
      if (var0 >= 128 && var0 <= 159) {
         return KeypadUtil.getKeyCode(var0, 0, 0);
      }

      if (var0 >= 'a' && var0 <= 'z') {
         var0 = (char)(var0 - ' ');
      }

      int var2 = var0 << 16;
      return var2 | var1;
   }

   private int extractKeyCode(int var1) {
      return var1 >> 16;
   }

   private int extractStatus(int var1) {
      char var2 = '\uffff';
      return var2 & var1;
   }

   private boolean isVaildStatus(int var1) {
      return (var1 & -65536) == 0;
   }
}
