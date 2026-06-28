package net.rim.device.api.crypto;

public final class FIPS186PseudoRandomSource extends AbstractPseudoRandomSource implements PseudoRandomSource {
   private NativeFIPSPRNG _context;

   public FIPS186PseudoRandomSource(byte[] seed) {
      this(seed, 0, seed == null ? 0 : seed.length);
   }

   public FIPS186PseudoRandomSource(byte[] seed, int offset, int length) {
      this(seed, offset, length, null, 0, 0, true);
   }

   public FIPS186PseudoRandomSource(byte[] seed, byte[] additionalSeed) {
      this(seed, 0, seed == null ? 0 : seed.length, additionalSeed, 0, additionalSeed == null ? 0 : additionalSeed.length, true);
   }

   public FIPS186PseudoRandomSource(
      byte[] seed, int offset, int length, byte[] additionalSeed, int additionalSeedOffset, int additionalSeedLength, boolean useRevisedAlgorithm
   ) {
      if (seed != null
         && offset >= 0
         && length >= 0
         && seed.length - length >= offset
         && (
            additionalSeed == null
               || additionalSeedOffset >= 0 && additionalSeedLength >= 0 && additionalSeed.length - additionalSeedLength >= additionalSeedOffset
         )) {
         this._context = NativeFIPSPRNG.initialize(seed, offset, length, additionalSeed, additionalSeedOffset, additionalSeedLength, useRevisedAlgorithm);
      } else {
         throw new Object();
      }
   }

   @Override
   public final String getAlgorithm() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void xorBytes(byte[] buffer, int offset, int length) {
      if (buffer != null && offset >= 0 && length >= 0 && buffer.length - length >= offset) {
         this._context.xorBytes(buffer, offset, length);
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
      throw new RuntimeException("cod2jar: array init");
   }
}
