package net.rim.device.internal.system;

public class DebugUtilities {
   public static void printArrayContents(byte[] var0) {
      printArrayContents(var0, 0, var0 == null ? 0 : var0.length);
   }

   public static void printArrayContents(byte[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static long logStart(String var0, String var1) {
      long var2 = System.currentTimeMillis();
      Object var4 = new Object(var0);
      ((StringBuffer)var4).append(' ');
      ((StringBuffer)var4).append(var1);
      ((StringBuffer)var4).append(' ');
      ((StringBuffer)var4).append('S');
      ((StringBuffer)var4).append(' ');
      ((StringBuffer)var4).append(var2);
      ((StringBuffer)var4).append(' ');
      System.out.println(((StringBuffer)var4).toString());
      return var2;
   }

   public static void logFinish(String var0, String var1, long var2) {
      long var4 = System.currentTimeMillis();
      Object var6 = new Object(var0);
      ((StringBuffer)var6).append(' ');
      ((StringBuffer)var6).append(var1);
      ((StringBuffer)var6).append(' ');
      ((StringBuffer)var6).append('F');
      ((StringBuffer)var6).append(' ');
      ((StringBuffer)var6).append(var4);
      ((StringBuffer)var6).append(' ');
      System.out.println(((StringBuffer)var6).toString());
      ((StringBuffer)var6).setLength(0);
      ((StringBuffer)var6).append(' ');
      ((StringBuffer)var6).append(var1);
      ((StringBuffer)var6).append(' ');
      ((StringBuffer)var6).append('D');
      ((StringBuffer)var6).append(' ');
      ((StringBuffer)var6).append(var4 - var2);
      ((StringBuffer)var6).append(' ');
      System.out.println(((StringBuffer)var6).toString());
   }
}
