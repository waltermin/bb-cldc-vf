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

   public int extractKeyLength(CryptoTokenMACKeyData var1) {
      throw new Object();
   }

   public byte[] extractKeyData(CryptoTokenMACKeyData var1) {
      throw new Object();
   }

   public CryptoTokenMACContext initialize(CryptoTokenMACKeyData var1, Digest var2) {
      throw new Object();
   }

   public void reset(CryptoTokenMACContext var1) {
      throw new Object();
   }

   public int getMAC(CryptoTokenMACContext var1, byte[] var2, int var3, boolean var4) {
      throw new Object();
   }

   public CryptoTokenMACKeyData createKey(int var1) {
      throw new Object();
   }

   public CryptoTokenMACKeyData injectKey(byte[] var1, int var2, int var3) {
      throw new Object();
   }

   public void deleteKey(CryptoTokenMACKeyData var1) {
      throw new Object();
   }
}
