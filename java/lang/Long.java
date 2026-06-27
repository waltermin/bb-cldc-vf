package java.lang;

public final class Long {
   private long value;
   public static final long MIN_VALUE;
   public static final long MAX_VALUE;

   public static final String toString(long var0, int var2) {
      byte[] var3 = new byte[10];
      return new String(var3, 0, toString(var0, var2, var3, 0));
   }

   static final int toString(long var0, int var2, Object var3, int var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final String toString(long var0) {
      return toString(var0, 10);
   }

   public static final long parseLong(String var0, int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long parseLong(String var0) {
      return parseLong(var0, 10);
   }

   public Long(long var1) {
      this.value = var1;
   }

   public final long longValue() {
      return this.value;
   }

   public final float floatValue() {
      return (float)this.value;
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
      return (int)(this.value ^ this.value >> 32);
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
