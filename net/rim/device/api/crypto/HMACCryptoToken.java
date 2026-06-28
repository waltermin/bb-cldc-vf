package net.rim.device.api.crypto;

import net.rim.device.api.util.Persistable;

public class HMACCryptoToken implements SymmetricCryptoToken, Persistable {
   protected HMACCryptoToken() {
   }

   @Override
   public final String getAlgorithm() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean providesUserAuthentication() {
      return false;
   }

   public int extractKeyLength(CryptoTokenMACKeyData cryptoTokenData) {
      throw new Object();
   }

   public byte[] extractKeyData(CryptoTokenMACKeyData cryptoTokenData) {
      throw new Object();
   }

   public CryptoTokenMACContext initialize(CryptoTokenMACKeyData keyData, Digest digest) {
      throw new Object();
   }

   public void reset(CryptoTokenMACContext context) {
      throw new Object();
   }

   public int getMAC(CryptoTokenMACContext context, byte[] buffer, int offset, boolean reset) {
      throw new Object();
   }

   public CryptoTokenMACKeyData createKey(int length) {
      throw new Object();
   }

   public CryptoTokenMACKeyData injectKey(byte[] key, int offset, int length) {
      throw new Object();
   }

   public void deleteKey(CryptoTokenMACKeyData data) {
      throw new Object();
   }
}
