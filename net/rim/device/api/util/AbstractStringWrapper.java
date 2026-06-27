package net.rim.device.api.util;

public class AbstractStringWrapper implements AbstractString {
   protected AbstractStringWrapper() {
   }

   public static AbstractStringWrapper createInstance(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void reset(Object var1) {
      throw null;
   }

   @Override
   public int hashCode() {
      int var1 = 0;
      int var2 = this.length();

      for (int var3 = 0; var3 < var2; var3++) {
         var1 = var1 * 31 + this.charAt(var3);
      }

      return var1;
   }

   @Override
   public void getChars(int var1, int var2, char[] var3, int var4) {
      throw null;
   }

   @Override
   public char charAt(int var1) {
      throw null;
   }

   @Override
   public int indexOf(char var1, int var2, int var3) {
      throw null;
   }

   @Override
   public int length() {
      throw null;
   }
}
