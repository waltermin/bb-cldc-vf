package java.lang;

public final class Double {
   private double value;
   public static final double POSITIVE_INFINITY;
   public static final double NEGATIVE_INFINITY;
   public static final double NaN;
   public static final double MAX_VALUE;
   public static final double MIN_VALUE;

   public static final String toString(double var0) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public static final Double valueOf(String var0) {
      return new Double(FloatingDecimal.readJavaFormatString(var0).doubleValue());
   }

   public static final double parseDouble(String var0) {
      return FloatingDecimal.readJavaFormatString(var0).doubleValue();
   }

   public static final boolean isNaN(double var0) {
      return var0 != var0;
   }

   public static final boolean isInfinite(double var0) {
      return var0 == 9218868437227405312L || var0 == -4503599627370496L;
   }

   public Double(double var1) {
      this.value = var1;
   }

   public final boolean isNaN() {
      return isNaN(this.value);
   }

   public final boolean isInfinite() {
      return isInfinite(this.value);
   }

   @Override
   public final String toString() {
      return String.valueOf(this.value);
   }

   public final byte byteValue() {
      return (byte)this.value;
   }

   public final short shortValue() {
      return (short)this.value;
   }

   public final int intValue() {
      return (int)this.value;
   }

   public final long longValue() {
      return (long)this.value;
   }

   public final float floatValue() {
      return (float)this.value;
   }

   public final double doubleValue() {
      return this.value;
   }

   @Override
   public final int hashCode() {
      long var1 = doubleToLongBits(this.value);
      return (int)(var1 ^ var1 >>> 32);
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final native long doubleToLongBits(double var0);

   public static final native double longBitsToDouble(long var0);
}
