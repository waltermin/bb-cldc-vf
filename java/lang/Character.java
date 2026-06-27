package java.lang;

import net.rim.device.api.util.CharacterUtilities;

public final class Character {
   private char value;
   public static final int MIN_RADIX;
   public static final int MAX_RADIX;
   public static final char MIN_VALUE;
   public static final char MAX_VALUE;

   public Character(char var1) {
      this.value = var1;
   }

   public final char charValue() {
      return this.value;
   }

   @Override
   public final int hashCode() {
      return this.value;
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final String toString() {
      char[] var1 = new char[]{this.value};
      return String.valueOf(var1);
   }

   public static final boolean isLowerCase(char var0) {
      return CharacterUtilities.isLowerCase(var0);
   }

   public static final boolean isUpperCase(char var0) {
      return CharacterUtilities.isUpperCase(var0);
   }

   public static final boolean isDigit(char var0) {
      return CharacterUtilities.isDigit(var0);
   }

   public static final char toLowerCase(char var0) {
      return CharacterUtilities.isUpperCase(var0) ? CharacterUtilities.toLowerCase(var0) : var0;
   }

   public static final char toUpperCase(char var0) {
      return CharacterUtilities.isLowerCase(var0) ? CharacterUtilities.toUpperCase(var0) : var0;
   }

   public static final int digit(char var0, int var1) {
      int var2 = -1;
      if (var1 >= 2 && var1 <= 36) {
         if (isDigit(var0)) {
            var2 = var0 - '0';
         } else if (isUpperCase(var0) || isLowerCase(var0)) {
            var2 = (var0 & 31) + 9;
         }
      }

      return var2 < var1 ? var2 : -1;
   }
}
