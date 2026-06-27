package java.lang;

public final class Short {
   private short value;
   public static final short MIN_VALUE;
   public static final short MAX_VALUE;

   public static final short parseShort(String var0) {
      return parseShort(var0, 10);
   }

   public static final short parseShort(String var0, int var1) {
      int var2 = Integer.parseInt(var0, var1);
      if (var2 >= -32768 && var2 <= 32767) {
         return (short)var2;
      } else {
         throw new Object();
      }
   }

   public Short(short var1) {
      this.value = var1;
   }

   public final short shortValue() {
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
