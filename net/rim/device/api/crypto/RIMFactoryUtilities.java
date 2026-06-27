package net.rim.device.api.crypto;

public final class RIMFactoryUtilities {
   private RIMFactoryUtilities() {
   }

   public static final String getBaseAlgorithm(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String stripBaseAlgorithm(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getKeyBitLength(String var0, int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getBlockBitLength(String var0, int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getNumRounds(String var0, int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String getRightMostSubAlgorithm(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String getLeftMostSubAlgorithm(String var0) {
      int var1 = var0.indexOf(47, 0);
      return var1 < 0 ? var0 : var0.substring(0, var1);
   }

   public static final String stripLeftMostSubAlgorithm(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String stripRightMostSubAlgorithm(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
