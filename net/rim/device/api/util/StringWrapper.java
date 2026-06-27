package net.rim.device.api.util;

final class StringWrapper extends AbstractStringWrapper {
   private String _string;

   public StringWrapper(String var1) {
      this._string = var1;
   }

   @Override
   public final String toString() {
      return this._string.toString();
   }

   @Override
   public final int hashCode() {
      return this._string.hashCode();
   }

   @Override
   public final int length() {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final void getChars(int var1, int var2, char[] var3, int var4) {
      this._string.getChars(var1, var2, var3, var4);
   }

   @Override
   public final int indexOf(char var1, int var2, int var3) {
      return StringUtilities.indexOf(this._string, var1, var2, var3);
   }

   @Override
   public final char charAt(int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final void reset(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
