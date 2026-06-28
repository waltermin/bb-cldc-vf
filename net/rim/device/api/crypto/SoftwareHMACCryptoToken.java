package net.rim.device.api.crypto;

import net.rim.device.api.util.Persistable;

final class SoftwareHMACCryptoToken extends HMACCryptoToken implements Persistable {
   private static SoftwareHMACCryptoToken _instance;
   private static final long ID_TEST_HMAC;

   public static final SoftwareHMACCryptoToken getInstance() {
      return _instance;
   }

   private SoftwareHMACCryptoToken() {
   }

   @Override
   public final CryptoTokenMACKeyData createKey(int length) {
      return new SoftwareHMACCryptoToken$HMACKeyData(length);
   }

   @Override
   public final CryptoTokenMACKeyData injectKey(byte[] data, int offset, int length) {
      return new SoftwareHMACCryptoToken$HMACKeyData(data, offset, length);
   }

   @Override
   public final void deleteKey(CryptoTokenMACKeyData data) {
   }

   @Override
   public final int extractKeyLength(CryptoTokenMACKeyData cryptoTokenData) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final byte[] extractKeyData(CryptoTokenMACKeyData cryptoTokenData) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final CryptoTokenMACContext initialize(CryptoTokenMACKeyData keyData, Digest digest) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void reset(CryptoTokenMACContext context) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final int getMAC(CryptoTokenMACContext context, byte[] buffer, int offset, boolean reset) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: array init");
   }
}
