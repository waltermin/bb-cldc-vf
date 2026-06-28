package net.rim.device.api.crypto;

public final class ITPolicyAuthentication {
   public static final String EC_CURVE;
   private static final long ID_SELF_TEST;

   private ITPolicyAuthentication() {
   }

   public static final boolean verifyITPolicy(byte[] publicKey, byte[] signature, byte[] itPolicy) {
      SHA512Digest hasher = new SHA512Digest();
      hasher.update(itPolicy);
      byte[] digest = hasher.getDigest();
      byte[] r = new byte[signature.length >> 1];
      byte[] s = new byte[signature.length >> 1];
      System.arraycopy(signature, 0, r, 0, r.length);
      System.arraycopy(signature, r.length, s, 0, s.length);
      return NativeEC.verifyDSA("EC571K1", publicKey, digest, r, s);
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: array init");
   }
}
