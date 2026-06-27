package net.rim.device.api.util;

public class StringPattern {
   public boolean findMatch(AbstractString var1, int var2, StringPattern$Match var3) {
      return var1 == null ? false : this.findMatch(var1, var2, var1.length(), var3);
   }

   public boolean findMatch(AbstractString var1, int var2, int var3, StringPattern$Match var4) {
      throw null;
   }

   public boolean findMatch(AbstractString var1, int var2, int var3, int var4, StringPattern$Match var5) {
      return false;
   }

   public long getPatternTypeIdentifier() {
      return -1;
   }

   protected static final boolean isWhitespace(char var0) {
      switch (var0) {
         case '\t':
         case '\n':
         case '\r':
         case ' ':
         case ' ':
         case '\u200b':
            return true;
         default:
            return false;
      }
   }
}
