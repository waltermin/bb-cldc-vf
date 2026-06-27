package net.rim.device.api.crypto;

public final class SHA1Digest extends AbstractDigest implements Digest {
   private NativeDigest _context = NativeDigest.initializeSHA1();
   private static final int MAX_UPDATE;
   public static final int DIGEST_LENGTH;
   public static final int BLOCK_LENGTH;

   @Override
   public final String getAlgorithm() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void reset() {
      this._context.reset();
   }

   @Override
   public final void update(int var1) {
      this._context.update(var1);
   }

   @Override
   public final void update(byte[] var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var3 >= 0 && var1.length - var3 >= var2) {
         while (var3 > 0) {
            int var4 = Math.min(var3, 1024);
            this._context.update(var1, var2, var4);
            var2 += var4;
            var3 -= var4;
         }
      } else {
         throw new Object();
      }
   }

   @Override
   public final int getDigestLength() {
      return 20;
   }

   @Override
   public final int getBlockLength() {
      return 64;
   }

   @Override
   public final int getDigest(byte[] var1, int var2, boolean var3) {
      this._context.getDigest(var1, var2, var3);
      return 20;
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: array init");
   }

   public static final void hmacSelfTest() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
