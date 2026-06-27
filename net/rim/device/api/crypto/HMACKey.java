package net.rim.device.api.crypto;

import net.rim.device.api.util.Persistable;

public final class HMACKey implements SymmetricKey, Persistable {
   private HMACCryptoToken _cryptoToken;
   private CryptoTokenMACKeyData _cryptoTokenData;
   private int _hashCode;

   public final CryptoTokenMACKeyData getCryptoTokenData() {
      return this._cryptoTokenData;
   }

   public final HMACCryptoToken getHMACCryptoToken() {
      return this._cryptoToken;
   }

   @Override
   public final int getLength() {
      return this._cryptoToken.extractKeyLength(this._cryptoTokenData);
   }

   @Override
   public final int getBitLength() {
      return this._cryptoToken.extractKeyLength(this._cryptoTokenData) * 8;
   }

   @Override
   public final SymmetricCryptoToken getSymmetricCryptoToken() {
      return this._cryptoToken;
   }

   @Override
   public final byte[] getData() {
      return this._cryptoToken.extractKeyData(this._cryptoTokenData);
   }

   @Override
   public final String getAlgorithm() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void initialize(HMACCryptoToken var1, int var2) {
      if (var1 != null && var2 >= 0) {
         this.initialize(var1, var1.createKey(var2));
      } else {
         throw new Object();
      }
   }

   private final void intialize(HMACCryptoToken var1, byte[] var2, int var3, int var4) {
      if (var1 != null && var2 != null && var3 >= 0 && var4 >= 0 && var2.length - var4 >= var3) {
         this.initialize(var1, var1.injectKey(var2, var3, var4));
      } else {
         throw new Object();
      }
   }

   private final void initialize(HMACCryptoToken var1, CryptoTokenMACKeyData var2) {
      if (var1 != null && var2 != null) {
         this._cryptoToken = var1;
         this._cryptoTokenData = var2;
         this.setHashCode();
      } else {
         throw new Object();
      }
   }

   public HMACKey(HMACCryptoToken var1, CryptoTokenMACKeyData var2) {
      this.initialize(var1, var2);
   }

   public HMACKey(HMACCryptoToken var1, byte[] var2, int var3, int var4) {
      this.intialize(var1, var2, var3, var4);
   }

   public HMACKey(HMACCryptoToken var1, int var2) {
      this.initialize(var1, var2);
   }

   public HMACKey(byte[] var1) {
      this(var1, 0, var1 == null ? 0 : var1.length);
   }

   public HMACKey(byte[] var1, int var2, int var3) {
   }

   public HMACKey(int var1) {
   }

   public HMACKey() {
      this(128);
   }

   private final void setHashCode() {
      this._hashCode = this._cryptoToken.hashCode() ^ this._cryptoTokenData.hashCode();
      if (this._hashCode == 0) {
         this._hashCode = 1;
      }
   }

   @Override
   public final int hashCode() {
      return this._hashCode;
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
