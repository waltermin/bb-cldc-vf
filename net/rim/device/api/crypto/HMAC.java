package net.rim.device.api.crypto;

public final class HMAC extends AbstractMAC implements MAC {
   private Digest _digest;
   private HMACCryptoToken _cryptoToken;
   private CryptoTokenMACContext _context;

   public HMAC(HMACKey var1, Digest var2) {
      if (var1 != null && var2 != null) {
         this._digest = var2;
         this._cryptoToken = var1.getHMACCryptoToken();
         this._context = this._cryptoToken.initialize(var1.getCryptoTokenData(), this._digest);
      } else {
         throw new Object();
      }
   }

   @Override
   public final String getAlgorithm() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void reset() {
      this._cryptoToken.reset(this._context);
   }

   @Override
   public final int getLength() {
      return this._digest.getDigestLength();
   }

   @Override
   public final void update(int var1) {
      this._digest.update(var1);
   }

   @Override
   public final void update(byte[] var1, int var2, int var3) {
      this._digest.update(var1, var2, var3);
   }

   @Override
   public final int getMAC(byte[] var1, int var2, boolean var3) {
      return this._cryptoToken.getMAC(this._context, var1, var2, var3);
   }
}
