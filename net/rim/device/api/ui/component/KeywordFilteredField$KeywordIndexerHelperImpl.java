package net.rim.device.api.ui.component;

import net.rim.device.api.collection.util.KeywordIndexerHelper;
import net.rim.device.api.util.StringUtilities;
import net.rim.vm.Array;

class KeywordFilteredField$KeywordIndexerHelperImpl implements KeywordIndexerHelper {
   private final KeywordFilteredField this$0;

   public KeywordFilteredField$KeywordIndexerHelperImpl(KeywordFilteredField var1) {
      this.this$0 = var1;
   }

   @Override
   public boolean checkForMatch(Object var1, String[] var2) {
      String[] var3 = this.this$0._keywordProvider.getKeywords(var1);

      for (int var4 = 0; var4 < var2.length; var4++) {
         for (int var5 = 0; var5 < var3.length; var5++) {
            String[] var6 = StringUtilities.stringToWords(var3[var5]);

            for (int var7 = 0; var7 < var6.length; var7++) {
               if (StringUtilities.startsWithIgnoreCaseAndAccents(var6[var7], var2[var4])) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   @Override
   public int getKeywords(Object var1, String[] var2) {
      String[] var3 = this.this$0._keywordProvider.getKeywords(var1);
      System.arraycopy(var3, 0, var2, 0, var3.length);
      Array.resize(var2, var3.length);
      return var2.length;
   }
}
