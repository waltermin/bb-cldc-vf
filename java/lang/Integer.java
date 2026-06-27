package java.lang;

import net.rim.device.api.util.NumberUtilities;

public final class Integer {
   private int value;
   public static final int MIN_VALUE;
   public static final int MAX_VALUE;
   static final byte[] digits;

   public static final String toString(int var0, int var1) {
      return Long.toString(var0, var1);
   }

   public static final String toHexString(int var0) {
      return toUnsignedString(var0, 4);
   }

   public static final String toOctalString(int var0) {
      return toUnsignedString(var0, 3);
   }

   public static final String toBinaryString(int var0) {
      return toUnsignedString(var0, 1);
   }

   private static final String toUnsignedString(int var0, int var1) {
      byte[] var2 = new byte[32];
      int var3 = 32;
      int var4 = 1 << var1;
      int var5 = var4 - 1;

      do {
         var2[--var3] = digits[var0 & var5];
         var0 >>>= var1;
      } while (var0 != 0);

      return new String(var2, var3, 32 - var3);
   }

   public static final String toString(int var0) {
      return toString(var0, 10);
   }

   public static final int parseInt(String var0, int var1) {
      return NumberUtilities.parseInt(var0, 0, MAX_VALUE, var1);
   }

   public static final int parseInt(String var0) {
      return parseInt(var0, 10);
   }

   public static final Integer valueOf(String var0, int var1) {
      return new Integer(parseInt(var0, var1));
   }

   public static final Integer valueOf(String var0) {
      return new Integer(parseInt(var0, 10));
   }

   public Integer(int var1) {
      this.value = var1;
   }

   public final byte byteValue() {
      return (byte)this.value;
   }

   public final short shortValue() {
      return (short)this.value;
   }

   public final int intValue() {
      return this.value;
   }

   public final long longValue() {
      return this.value;
   }

   public final float floatValue() {
      return this.value;
   }

   public final double doubleValue() {
      return this.value;
   }

   @Override
   public final String toString() {
      return toString(this.value);
   }

   @Override
   public final int hashCode() {
      return this.value;
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
