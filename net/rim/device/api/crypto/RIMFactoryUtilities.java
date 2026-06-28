package net.rim.device.api.crypto;

public final class RIMFactoryUtilities {
   private RIMFactoryUtilities() {
   }

   public static final String getBaseAlgorithm(String algorithm) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String stripBaseAlgorithm(String algorithm) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getKeyBitLength(String algorithm, int defaultBitLength) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getBlockBitLength(String algorithm, int defaultLength) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getNumRounds(String algorithm, int defaultRounds) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String getRightMostSubAlgorithm(String algorithm) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String getLeftMostSubAlgorithm(String algorithm) {
      int end = algorithm.indexOf(47, 0);
      return end < 0 ? algorithm : algorithm.substring(0, end);
   }

   public static final String stripLeftMostSubAlgorithm(String algorithm) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String stripRightMostSubAlgorithm(String algorithm) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
