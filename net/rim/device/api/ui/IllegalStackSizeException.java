package net.rim.device.api.ui;

public class IllegalStackSizeException extends RuntimeException {
   public IllegalStackSizeException() {
   }

   public IllegalStackSizeException(String var1) {
   }

   public IllegalStackSizeException(String var1, Class var2, Graphics var3, int var4) {
      super(unpairedMessage(var1, var2, var3, var4));
   }

   private static String unpairedMessage(String var0, Class var1, Graphics var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
