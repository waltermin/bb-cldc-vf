package net.rim.device.api.ui;

public class IllegalStackSizeException extends RuntimeException {
   public IllegalStackSizeException() {
   }

   public IllegalStackSizeException(String message) {
   }

   public IllegalStackSizeException(String type, Class clazz, Graphics graphics, int depthExpected) {
      super(unpairedMessage(type, clazz, graphics, depthExpected));
   }

   private static String unpairedMessage(String type, Class clazz, Graphics graphics, int depthExpected) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
