package net.rim.device.internal.system;

public class DebugUtilities {
   public static void printArrayContents(byte[] array) {
      printArrayContents(array, 0, array == null ? 0 : array.length);
   }

   public static void printArrayContents(byte[] array, int offset, int length) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static long logStart(String className, String m) {
      long start = System.currentTimeMillis();
      StringBuffer buffer = new StringBuffer(className);
      buffer.append(' ');
      buffer.append(m);
      buffer.append(' ');
      buffer.append('S');
      buffer.append(' ');
      buffer.append(start);
      buffer.append(' ');
      System.out.println(buffer.toString());
      return start;
   }

   public static void logFinish(String className, String m, long start) {
      long end = System.currentTimeMillis();
      StringBuffer buffer = new StringBuffer(className);
      buffer.append(' ');
      buffer.append(m);
      buffer.append(' ');
      buffer.append('F');
      buffer.append(' ');
      buffer.append(end);
      buffer.append(' ');
      System.out.println(buffer.toString());
      buffer.setLength(0);
      buffer.append(' ');
      buffer.append(m);
      buffer.append(' ');
      buffer.append('D');
      buffer.append(' ');
      buffer.append(end - start);
      buffer.append(' ');
      System.out.println(buffer.toString());
   }
}
