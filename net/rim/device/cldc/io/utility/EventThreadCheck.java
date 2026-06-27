package net.rim.device.cldc.io.utility;

public final class EventThreadCheck {
   private static boolean _isMidlet;

   private EventThreadCheck() {
   }

   public static final void throwException() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
