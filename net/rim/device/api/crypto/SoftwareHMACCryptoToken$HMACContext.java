package net.rim.device.api.crypto;

final class SoftwareHMACCryptoToken$HMACContext implements CryptoTokenMACContext {
   private Digest _digest;
   private Digest _digest2;
   private byte[] _ipad;
   private byte[] _opad;
   private static final byte IPAD_BYTE;
   private static final byte OPAD_BYTE;

   public SoftwareHMACCryptoToken$HMACContext(SoftwareHMACCryptoToken$HMACKeyData var1, Digest var2) {
   }

   public final void reset() {
      this._digest.reset();
      this._digest.update(this._ipad);
   }

   public final int getMAC(byte[] var1, int var2, boolean var3) {
      if (var1 != null && var2 >= 0 && var1.length - this._digest.getDigestLength() >= var2) {
         byte[] var4 = this._digest.getDigest(var3);
         this._digest2.update(this._opad);
         this._digest2.update(var4);
         int var5 = this._digest2.getDigest(var1, var2);
         if (var3) {
            this.reset();
         }

         return var5;
      } else {
         throw new Object();
      }
   }
}
