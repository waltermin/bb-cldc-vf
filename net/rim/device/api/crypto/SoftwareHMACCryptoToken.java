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
   public final CryptoTokenMACKeyData createKey(int var1) {
      return new SoftwareHMACCryptoToken$HMACKeyData(var1);
   }

   @Override
   public final CryptoTokenMACKeyData injectKey(byte[] var1, int var2, int var3) {
      return new SoftwareHMACCryptoToken$HMACKeyData(var1, var2, var3);
   }

   @Override
   public final void deleteKey(CryptoTokenMACKeyData var1) {
   }

   @Override
   public final int extractKeyLength(CryptoTokenMACKeyData var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final byte[] extractKeyData(CryptoTokenMACKeyData var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final CryptoTokenMACContext initialize(CryptoTokenMACKeyData var1, Digest var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void reset(CryptoTokenMACContext var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final int getMAC(CryptoTokenMACContext var1, byte[] var2, int var3, boolean var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
