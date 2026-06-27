package net.rim.device.internal.crypto;

public final class EncryptionUtilities {
   public static final int ECC_160_R1;
   public static final int ECC_256_R1;
   public static final int ECC_521_R1;
   public static final int ECC_283_K1;
   public static final int ECC_571_K1;
   private static final String ECC_160_R1_NAME;
   private static final int ECC_160_R1_PUBLIC_KEY_LENGTH;
   private static final int ECC_160_R1_PRIVATE_KEY_LENGTH;
   private static final int ECC_160_R1_SHARED_SECRET_LENGTH;
   private static final String ECC_256_R1_NAME;
   private static final int ECC_256_R1_PUBLIC_KEY_LENGTH;
   private static final int ECC_256_R1_PRIVATE_KEY_LENGTH;
   private static final int ECC_256_R1_SHARED_SECRET_LENGTH;
   private static final String ECC_521_R1_NAME;
   private static final int ECC_521_R1_PUBLIC_KEY_LENGTH;
   private static final int ECC_521_R1_PRIVATE_KEY_LENGTH;
   private static final int ECC_521_R1_SHARED_SECRET_LENGTH;
   private static final String ECC_283_K1_NAME;
   private static final int ECC_283_K1_PUBLIC_KEY_LENGTH;
   private static final int ECC_283_K1_PRIVATE_KEY_LENGTH;
   private static final int ECC_283_K1_SHARED_SECRET_LENGTH;
   private static final String ECC_571_K1_NAME;
   private static final int ECC_571_K1_PUBLIC_KEY_LENGTH;
   private static final int ECC_571_K1_PRIVATE_KEY_LENGTH;
   private static final int ECC_571_K1_SHARED_SECRET_LENGTH;

   private EncryptionUtilities() {
   }

   private static final String getCurveName(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean isSupported(int var0) {
      return isSupported(getCurveName(var0));
   }

   private static final native boolean isSupported(String var0);

   public static final int getPublicKeyLength(int var0) {
      switch (var0) {
         case -1:
            throw new Object();
         case 0:
         default:
            return 21;
         case 1:
            return 33;
         case 2:
            return 67;
         case 3:
            return 37;
         case 4:
            return 73;
      }
   }

   public static final int getPrivateKeyLength(int var0) {
      switch (var0) {
         case -1:
            throw new Object();
         case 0:
         default:
            return 21;
         case 1:
            return 32;
         case 2:
            return 66;
         case 3:
            return 36;
         case 4:
            return 72;
      }
   }

   public static final int getDerivedKeyLength(int var0) {
      switch (var0) {
         case -1:
            throw new Object();
         case 0:
         default:
            return 20;
         case 1:
            return 32;
         case 2:
            return 66;
         case 3:
            return 36;
         case 4:
            return 72;
      }
   }

   public static final void getGroupOrder(int var0, byte[] var1) {
      getGroupOrder(getCurveName(var0), var1);
   }

   private static final native void getGroupOrder(String var0, byte[] var1);

   public static final void scalarMultiply(int var0, byte[] var1, byte[] var2, byte[] var3) {
      scalarMultiply(getCurveName(var0), var1, var2, var3);
   }

   private static final native void scalarMultiply(String var0, byte[] var1, byte[] var2, byte[] var3);

   public static final void createKeyPair(int var0, byte[] var1, byte[] var2) {
      createKeyPair(getCurveName(var0), var1, var2);
   }

   private static final native void createKeyPair(String var0, byte[] var1, byte[] var2);

   public static final byte[] calculateKey(int var0, byte[] var1, byte[] var2) {
      byte[] var3 = new byte[getDerivedKeyLength(var0)];
      calculateKey(var0, var1, var2, var3);
      return var3;
   }

   public static final void calculateKey(int var0, byte[] var1, byte[] var2, byte[] var3) {
      calculateKey(getCurveName(var0), var1, var2, var3);
   }

   private static final native void calculateKey(String var0, byte[] var1, byte[] var2, byte[] var3);

   public static final byte[] generateCurvePointFromByteArray(int var0, byte[] var1) {
      byte[] var2 = new byte[getPublicKeyLength(var0)];
      generateCurvePointFromByteArray(var0, var1, var2);
      return var2;
   }

   public static final void generateCurvePointFromByteArray(int var0, byte[] var1, byte[] var2) {
      generateCurvePointFromByteArray(getCurveName(var0), var1, var2);
   }

   private static final native void generateCurvePointFromByteArray(String var0, byte[] var1, byte[] var2);

   public static final byte[] generateECMQVSharedSecret(int var0, byte[] var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5) {
      byte[] var6 = new byte[getDerivedKeyLength(var0)];
      generateECMQVSharedSecret(var0, var1, var2, var3, var4, var5, var6);
      return var6;
   }

   public static final void generateECMQVSharedSecret(int var0, byte[] var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6) {
      generateECMQVSharedSecret(getCurveName(var0), var1, var2, var3, var4, var5, var6);
   }

   private static final native void generateECMQVSharedSecret(String var0, byte[] var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6);

   public static final native int getCiphertextLength(int var0);

   public static final native int encrypt(byte[] var0, Object var1, int var2, int var3, Object var4, int var5);

   public static final int decrypt(byte[] var0, Object var1, int var2, int var3, Object var4, int var5) {
      return decrypt(var0, var1, var2, var3, var4, var5, false);
   }

   public static final native int decrypt(byte[] var0, Object var1, int var2, int var3, Object var4, int var5, boolean var6);
}
