package net.rim.device.api.util;

final class StringBufferWrapper extends AbstractStringWrapper {
   private StringBuffer _stringBuffer;
   private char[] _buffer = new char[64];

   public StringBufferWrapper(StringBuffer var1) {
      this._stringBuffer = var1;
   }

   @Override
   public final String toString() {
      return this._stringBuffer.toString();
   }

   @Override
   public final int length() {
      return this._stringBuffer.length();
   }

   @Override
   public final char charAt(int var1) {
      return this._stringBuffer.charAt(var1);
   }

   @Override
   public final void getChars(int var1, int var2, char[] var3, int var4) {
      this._stringBuffer.getChars(var1, var2, var3, var4);
   }

   @Override
   public final int indexOf(char var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4 += 64) {
         int var5 = var3 - var4;
         if (var5 > 64) {
            var5 = 64;
         }

         this._stringBuffer.getChars(var4, var4 + var5, this._buffer, 0);

         for (int var6 = 0; var6 < var5; var6++) {
            if (this._buffer[var6] == var1) {
               return var4 + var6;
            }
         }
      }

      return -1;
   }

   @Override
   public final void reset(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
