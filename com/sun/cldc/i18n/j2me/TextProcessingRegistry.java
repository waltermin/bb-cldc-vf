package com.sun.cldc.i18n.j2me;

public final class TextProcessingRegistry {
   private ConversionDataRegistryHelper _conversionRegistryHelper = new ConversionDataRegistryHelper();
   private BreakingDataRegistryHelper _breakingDataRegistryHelper = new BreakingDataRegistryHelper();
   public static final int CONVERSION_DATA_TYPE;
   public static final int COLLATION_DATA_TYPE_TAILORING;
   public static final int BREAKING_DATA_TYPE_LINE;
   public static final int BREAKING_DATA_TYPE_CHAR;
   public static final int BREAKING_DATA_TYPE_WORD;
   private static final long REGISTRY_NAME;
   private static TextProcessingRegistry _registry;

   public static final TextProcessingRegistry getInstance() {
      return _registry;
   }

   public static final boolean loadTextProcessingData(String var0, String var1, int var2, int var3, String var4) {
      return loadTextProcessingData(var0, var1, var2, null, -1, var3, var4);
   }

   public static final boolean loadTextProcessingData(String var0, String var1, int var2, String var3, int var4, int var5, String var6) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public static final boolean loadTextProcessingData(byte[][][] var0, int var1, String var2, int var3, int var4, String var5) {
      switch (var1) {
         case 0:
            return getInstance()._conversionRegistryHelper.loadConversionData(var2, var3, var4, var5, var0);
         case 2:
            return getInstance()._breakingDataRegistryHelper.loadBreakingData(var4, var1, (byte[][])var0);
         default:
            return false;
      }
   }

   public final byte[][][] getTextProcessingData(int var1, int var2, int[] var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final String[] getSupported(int var1) {
      switch (var1) {
         case 0:
            return this._conversionRegistryHelper.getSupportedEncodings();
         default:
            return null;
      }
   }

   public final boolean isSupported(String var1, int var2) {
      switch (var2) {
         case 0:
            return this._conversionRegistryHelper.isSupported(var1);
         default:
            return false;
      }
   }

   public final int getSuggestedLocale(String var1) {
      return this._conversionRegistryHelper.getSuggestedLocale(var1);
   }

   public final String getSuggestedTypeface(String var1) {
      return this._conversionRegistryHelper.getSuggestedTypeface(var1);
   }

   public final String getSuggestedTypeface(int var1) {
      return this._conversionRegistryHelper.getSuggestedTypeface(var1);
   }

   public final String getSuggestedEncoding(int var1) {
      return this._conversionRegistryHelper.getSuggestedEncoding(var1);
   }

   public final int getTextProcessingDataID(String var1, int var2) {
      switch (var2) {
         case 0:
            return this._conversionRegistryHelper.getEncodingID(var1);
         default:
            return -1;
      }
   }

   public final int getTextProcessingDataID(int var1, int var2) {
      switch (var2) {
         case 2:
            return this._breakingDataRegistryHelper.getTextProcessingDataID(var1, var2);
         default:
            return -1;
      }
   }
}
