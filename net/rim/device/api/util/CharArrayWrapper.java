package net.rim.device.api.util;

final class CharArrayWrapper extends AbstractStringWrapper {
   private char[] _charArray;

   public CharArrayWrapper(char[] var1) {
      this._charArray = var1;
   }

   @Override
   public final String toString() {
      return (String)(new Object(this._charArray));
   }

   @Override
   public final int length() {
      return this._charArray.length;
   }

   @Override
   public final void getChars(int var1, int var2, char[] var3, int var4) {
      System.arraycopy(this._charArray, var1, var3, var4, var2 - var1);
   }

   @Override
   public final int indexOf(char var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         if (this._charArray[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   @Override
   public final char charAt(int var1) {
      return this._charArray[var1];
   }

   @Override
   public final void reset(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
