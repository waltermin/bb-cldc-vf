package java.lang;

public final class Long {
   private long value;
   public static final long MIN_VALUE;
   public static final long MAX_VALUE;

   public static final String toString(long i, int radix) {
      byte[] buf = new byte[10];
      return new String(buf, 0, toString(i, radix, buf, 0));
   }

   static final int toString(long i, int radix, Object obuf, int offset) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final String toString(long i) {
      return toString(i, 10);
   }

   public static final long parseLong(String s, int radix) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final long parseLong(String s) {
      return parseLong(s, 10);
   }

   public Long(long value) {
      this.value = value;
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
   public final boolean equals(Object obj) {
      throw new RuntimeException("cod2jar: type check");
   }
}
