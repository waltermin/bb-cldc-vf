package net.rim.device.api.util;

public class AbstractStringWrapper implements AbstractString {
   protected AbstractStringWrapper() {
   }

   public static AbstractStringWrapper createInstance(Object string) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void reset(Object _1) {
      throw null;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      int idxMax = this.length();

      for (int idx = 0; idx < idxMax; idx++) {
         hash = hash * 31 + this.charAt(idx);
      }

      return hash;
   }

   @Override
   public void getChars(int _1, int _2, char[] _3, int _4) {
      throw null;
   }

   @Override
   public char charAt(int _1) {
      throw null;
   }

   @Override
   public int indexOf(char _1, int _2, int _3) {
      throw null;
   }

   @Override
   public int length() {
      throw null;
   }
}
