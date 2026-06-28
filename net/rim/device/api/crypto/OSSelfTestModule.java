package net.rim.device.api.crypto;

import net.rim.device.internal.i18n.CommonResource;

public class OSSelfTestModule implements SelfTestModule {
   int _prngResult = 0;
   boolean _prngTestsDone = false;

   @Override
   public int getNumTests(boolean startupTests) {
      return 8;
   }

   @Override
   public void test(int testIndex) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String[] getTestNames(boolean startupTests) {
      String[] testStrings = CommonResource.getStringArray(10020);
      String[] temp = new String[this.getNumTests(startupTests)];
      System.arraycopy(testStrings, 0, temp, 0, temp.length);
      return temp;
   }
}
