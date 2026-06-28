package net.rim.device.api.crypto;

public final class ITPolicyAuthentication {
   public static final String EC_CURVE;
   private static final long ID_SELF_TEST;

   private ITPolicyAuthentication() {
   }

   public static final boolean verifyITPolicy(byte[] publicKey, byte[] signature, byte[] itPolicy) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final void selfTest() {
      throw new RuntimeException("cod2jar: array init");
   }
}
