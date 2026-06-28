package java.lang;

public final class Byte {
   private byte value;
   public static final byte MIN_VALUE;
   public static final byte MAX_VALUE;

   public static final byte parseByte(String s) {
      return parseByte(s, 10);
   }

   public static final byte parseByte(String s, int radix) {
      int i = Integer.parseInt(s, radix);
      if (i >= -128 && i <= 127) {
         return (byte)i;
      } else {
         throw new Object();
      }
   }

   public Byte(byte value) {
      this.value = value;
   }

   public final byte byteValue() {
      return this.value;
   }

   @Override
   public final String toString() {
      return String.valueOf(this.value);
   }

   @Override
   public final int hashCode() {
      return this.value;
   }

   @Override
   public final boolean equals(Object obj) {
      throw new RuntimeException("cod2jar: type check");
   }
}
