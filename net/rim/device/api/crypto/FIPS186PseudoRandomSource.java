package net.rim.device.api.crypto;

public final class FIPS186PseudoRandomSource extends AbstractPseudoRandomSource implements PseudoRandomSource {
   private NativeFIPSPRNG _context;

   public FIPS186PseudoRandomSource(byte[] var1) {
      this(var1, 0, var1 == null ? 0 : var1.length);
   }

   public FIPS186PseudoRandomSource(byte[] var1, int var2, int var3) {
      this(var1, var2, var3, null, 0, 0, true);
   }

   public FIPS186PseudoRandomSource(byte[] var1, byte[] var2) {
      this(var1, 0, var1 == null ? 0 : var1.length, var2, 0, var2 == null ? 0 : var2.length, true);
   }

   public FIPS186PseudoRandomSource(byte[] var1, int var2, int var3, byte[] var4, int var5, int var6, boolean var7) {
      if (var1 != null && var2 >= 0 && var3 >= 0 && var1.length - var3 >= var2 && (var4 == null || var5 >= 0 && var6 >= 0 && var4.length - var6 >= var5)) {
         this._context = NativeFIPSPRNG.initialize(var1, var2, var3, var4, var5, var6, var7);
      } else {
         throw new Object();
      }
   }

   @Override
   public final String getAlgorithm() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void xorBytes(byte[] var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var3 >= 0 && var1.length - var3 >= var2) {
         this._context.xorBytes(var1, var2, var3);
      } else {
         throw new Object();
      }
   }

   @Override
   public final int getAvailable() {
      return Integer.MAX_VALUE;
   }

   @Override
   public final int getMaxAvailable() {
      return Integer.MAX_VALUE;
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
