package net.rim.device.api.crypto;

public final class SHA512Digest extends AbstractDigest implements Digest {
   private NativeDigest _context = NativeDigest.initializeSHA512();
   private static final int MAX_UPDATE;
   public static final int DIGEST_LENGTH;
   public static final int BLOCK_LENGTH;

   @Override
   public final String getAlgorithm() {
      return "SHA512";
   }

   @Override
   public final void reset() {
      this._context.reset();
   }

   @Override
   public final void update(int data) {
      this._context.update(data);
   }

   @Override
   public final void update(byte[] data, int offset, int length) {
      if (data != null && offset >= 0 && length >= 0 && data.length - length >= offset) {
         while (length > 0) {
            int updated = Math.min(length, 1024);
            this._context.update(data, offset, updated);
            offset += updated;
            length -= updated;
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public final int getDigestLength() {
      return 64;
   }

   @Override
   public final int getBlockLength() {
      return 128;
   }

   @Override
   public final int getDigest(byte[] buffer, int offset, boolean resetDigest) {
      this._context.getDigest(buffer, offset, resetDigest);
      return 64;
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: array init");
   }

   public static final void hmacSelfTest() {
      throw new RuntimeException("cod2jar: array init");
   }
}
