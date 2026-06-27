package net.rim.device.api.crypto;

import net.rim.device.internal.i18n.CommonResource;

public class OSSelfTestModule implements SelfTestModule {
   int _prngResult = 0;
   boolean _prngTestsDone = false;

   @Override
   public int getNumTests(boolean var1) {
      return 8;
   }

   @Override
   public void test(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String[] getTestNames(boolean var1) {
      String[] var2 = CommonResource.getStringArray(10020);
      String[] var3 = new String[this.getNumTests(var1)];
      System.arraycopy(var2, 0, var3, 0, var3.length);
      return var3;
   }
}
