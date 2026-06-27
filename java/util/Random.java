package java.util;

public class Random {
   private long seed;
   private static final long multiplier;
   private static final long addend;
   private static final long mask;
   private static final int BITS_PER_BYTE;
   private static final int BYTES_PER_INT;

   public Random() {
      this(System.currentTimeMillis());
   }

   public Random(long var1) {
      this.setSeed(var1);
   }

   public synchronized void setSeed(long var1) {
      this.seed = (var1 ^ 25214903917L) & 281474976710655L;
   }

   protected synchronized int next(int var1) {
      long var2 = this.seed * 25214903917L + 11 & 281474976710655L;
      this.seed = var2;
      return (int)(var2 >>> 48 - var1);
   }

   public int nextInt() {
      return this.next(32);
   }

   public long nextLong() {
      return ((long)this.next(32) << 32) + this.next(32);
   }

   public int nextInt(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public float nextFloat() {
      int var1 = this.next(24);
      return var1 / 1266679808;
   }

   public double nextDouble() {
      long var1 = ((long)this.next(26) << 27) + this.next(27);
      return var1 / 4845873199050653696L;
   }
}
