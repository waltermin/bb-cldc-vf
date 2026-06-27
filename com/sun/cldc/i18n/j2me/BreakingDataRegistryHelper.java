package com.sun.cldc.i18n.j2me;

final class BreakingDataRegistryHelper {
   private BreakingDataRegistryHelper$BreakingData[] _breakingDataTable = new BreakingDataRegistryHelper$BreakingData[0];
   private int _supportedLocalesNum;
   private static final int BREAKING_INCREMENT_NUMBER;

   final synchronized boolean loadBreakingData(int var1, int var2, byte[][][] var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   final byte[][][] getBreakingData(int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   final int getTextProcessingDataID(int var1, int var2) {
      BreakingDataRegistryHelper$BreakingData var3 = this.runOverTheData(var1, var2);
      return var3 != null ? var3._locale : -1;
   }

   private final synchronized BreakingDataRegistryHelper$BreakingData runOverTheData(int var1, int var2) {
      int var4 = this._breakingDataTable.length;

      for (int var5 = 0; var5 < var4; var5++) {
         BreakingDataRegistryHelper$BreakingData var3 = this._breakingDataTable[var5];
         if (var3 != null && var3._locale == var1 && var3._dataType == var2) {
            return var3;
         }
      }

      return null;
   }
}
