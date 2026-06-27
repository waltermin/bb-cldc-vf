package net.rim.device.internal.crypto;

import net.rim.device.api.crypto.SelfTestModule;
import net.rim.device.internal.i18n.CommonResource;

public final class CryptoBlockSelfTestModule implements SelfTestModule {
   @Override
   public final int getNumTests(boolean var1) {
      return 1;
   }

   @Override
   public final void test(int var1) {
      switch (var1) {
         case 0:
            CryptoBlock.selfTest();
            return;
         default:
            throw new Object();
      }
   }

   @Override
   public final String[] getTestNames(boolean var1) {
      return CommonResource.getStringArray(10019);
   }
}
