package net.rim.device.api.crypto;

final class RIMDigestFactoryCrypto10 extends DigestFactory {
   @Override
   protected final String[] getFactoryAlgorithms() {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected final Digest create(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
