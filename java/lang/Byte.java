package java.lang;

public final class Byte {
   private byte value;
   public static final byte MIN_VALUE;
   public static final byte MAX_VALUE;

   public static final byte parseByte(String var0) {
      return parseByte(var0, 10);
   }

   public static final byte parseByte(String var0, int var1) {
      int var2 = Integer.parseInt(var0, var1);
      if (var2 >= -128 && var2 <= 127) {
         return (byte)var2;
      } else {
         throw new Object();
      }
   }

   public Byte(byte var1) {
      this.value = var1;
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
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
