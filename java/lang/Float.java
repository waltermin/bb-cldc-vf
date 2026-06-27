package java.lang;

public final class Float {
   private float value;
   public static final float POSITIVE_INFINITY;
   public static final float NEGATIVE_INFINITY;
   public static final float NaN;
   public static final float MAX_VALUE;
   public static final float MIN_VALUE;

   public static final String toString(float var0) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public static final Float valueOf(String var0) {
      return new Float(FloatingDecimal.readJavaFormatString(var0).floatValue());
   }

   public static final float parseFloat(String var0) {
      return FloatingDecimal.readJavaFormatString(var0).floatValue();
   }

   public static final boolean isNaN(float var0) {
      return var0 != var0;
   }

   public static final boolean isInfinite(float var0) {
      return var0 == 2139095040 || var0 == -8388608;
   }

   public Float(float var1) {
      this.value = var1;
   }

   public Float(double var1) {
      this.value = (float)var1;
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
      return this.value;
   }

   public final double doubleValue() {
      return this.value;
   }

   @Override
   public final int hashCode() {
      return floatToIntBits(this.value);
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final native int floatToIntBits(float var0);

   public static final native float intBitsToFloat(int var0);
}
