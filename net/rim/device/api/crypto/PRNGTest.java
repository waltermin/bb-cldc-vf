package net.rim.device.api.crypto;

public final class PRNGTest {
   public static final int MONO_BIT_TEST_PASSED;
   public static final int POKER_TEST_PASSED;
   public static final int RUNS_TEST_PASSED;
   public static final int LONG_RUNS_TEST_PASSED;
   public static final int ALL_TESTS_PASSED;
   public static final int ALL_FIPS_TESTS_PASSED;

   private PRNGTest() {
   }

   public static final int testPRNG(PseudoRandomSource var0) {
      if (var0 == null) {
         throw new Object();
      } else {
         return testForRandomness(var0.getBytes(2500));
      }
   }

   public static final int testRandomSource() {
      int var0 = 0;
      byte[] var1 = new byte[2500];

      for (int var2 = 0; var2 < 10; var2++) {
         RandomSource.getBytes(var1);
         var0 = testForRandomness(var1);
         if (var0 == 15) {
            return var0;
         }

         RandomSource.add(SelfTestData.RANDOM_DATA);
         RandomSource.add(var1);
      }

      return var0;
   }

   static final native int testForRandomness(byte[] var0);
}
