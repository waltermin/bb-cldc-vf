package com.sun.cldc.i18n.j2me;

final class BreakingDataRegistryHelper {
   private BreakingDataRegistryHelper$BreakingData[] _breakingDataTable = new BreakingDataRegistryHelper$BreakingData[0];
   private int _supportedLocalesNum;
   private static final int BREAKING_INCREMENT_NUMBER;

   final synchronized boolean loadBreakingData(int locale, int dataType, byte[][][] data) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   final byte[][][] getBreakingData(int locale, int dataType) {
      throw new RuntimeException("cod2jar: type check");
   }

   final int getTextProcessingDataID(int locale, int dataType) {
      BreakingDataRegistryHelper$BreakingData entry = this.runOverTheData(locale, dataType);
      return entry != null ? entry._locale : -1;
   }

   private final synchronized BreakingDataRegistryHelper$BreakingData runOverTheData(int locale, int dataType) {
      int length = this._breakingDataTable.length;

      for (int i = 0; i < length; i++) {
         BreakingDataRegistryHelper$BreakingData entry = this._breakingDataTable[i];
         if (entry != null && entry._locale == locale && entry._dataType == dataType) {
            return entry;
         }
      }

      return null;
   }
}
