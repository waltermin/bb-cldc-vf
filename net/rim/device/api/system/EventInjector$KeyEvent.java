package net.rim.device.api.system;

public class EventInjector$KeyEvent extends EventInjector$Event {
   public static final int KEY_DOWN;
   public static final int KEY_REPEAT;
   public static final int KEY_UP;

   public EventInjector$KeyEvent(int var1, char var2, int var3, int var4) {
      super(2, var1, var2, var3, var4, null, null);
   }

   public void setChar(char var1) {
      super._msg.setSubMessage(var1);
   }

   public char getChar() {
      return (char)super._msg.getSubMessage();
   }

   public void setTime(int var1) {
      super._msg.setData1(var1);
   }

   public int getTime() {
      return super._msg.getData1();
   }
}
