package com.sun.cldc.i18n.j2me;

import java.io.DataInputStream;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontRegistry;
import net.rim.device.api.util.StringUtilities;

final class ConversionDataRegistryHelper {
   private final String DEFAULT_ENCODING;
   private String[] _availableEncodings;
   private int _supportedEncodingsNum;
   private ConversionDataRegistryHelper$EncodingMappingData[] _encodingMappingTable;
   private static final int CDBF_CONVERSION_SIGNATURE;
   private static final int CDBF_CURRENT_VERSION_MAJOR;
   private static final int CDBF_CURRENT_VERSION_MINOR;
   private static final int ENC_INCREMENT_NUMBER;
   private static final int STRIP_FLAG;

   final synchronized String[] getSupportedEncodings() {
      return this._availableEncodings;
   }

   final synchronized boolean loadConversionData(String var1, int var2, int var3, String var4, byte[][][] var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final boolean isSupported(String var1) {
      ConversionDataRegistryHelper$EncodingMappingData var2 = this.runOverTheData(var1);
      return var2 != null && (this.isAlgorithmicallySupported(var2._id) || var2._binaryData != null);
   }

   final int getSuggestedLocale(String var1) {
      ConversionDataRegistryHelper$EncodingMappingData var2 = this.runOverTheData(var1);
      return var2 != null ? var2._locale : -1;
   }

   final String getSuggestedTypeface(String var1) {
      ConversionDataRegistryHelper$EncodingMappingData var2 = this.runOverTheData(var1);
      if (var2 != null) {
         return var2._typeface;
      }

      Font var3 = FontRegistry.getDefaultFont();
      return var3 != null ? var3.getFontFamily().getName() : FontRegistry.DEFAULT_FAMILY;
   }

   final String getSuggestedTypeface(int var1) {
      ConversionDataRegistryHelper$EncodingMappingData var2 = this.runOverTheData(var1);
      if (var2 != null) {
         return var2._typeface;
      }

      Font var3 = FontRegistry.getDefaultFont();
      return var3 != null ? var3.getFontFamily().getName() : FontRegistry.DEFAULT_FAMILY;
   }

   final String getSuggestedEncoding(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final synchronized byte[][][] getConversionData(int var1, int[] var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   final int getEncodingIDLocal(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   final int getEncodingID(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final synchronized ConversionDataRegistryHelper$EncodingMappingData runOverTheData(String var1) {
      int var3 = this._encodingMappingTable.length;

      for (int var4 = 0; var4 < var3; var4++) {
         ConversionDataRegistryHelper$EncodingMappingData var2 = this._encodingMappingTable[var4];
         if (var2 != null && StringUtilities.startsWithIgnoreCase(var1, var2._encoding, 1701707776)) {
            return var2;
         }
      }

      return null;
   }

   private final synchronized ConversionDataRegistryHelper$EncodingMappingData runOverTheData(int var1) {
      int var3 = this._encodingMappingTable.length;

      for (int var4 = 0; var4 < var3; var4++) {
         ConversionDataRegistryHelper$EncodingMappingData var2 = this._encodingMappingTable[var4];
         if (var2 != null && var2._locale == var1) {
            return var2;
         }
      }

      return null;
   }

   private final int readHeader(DataInputStream var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final int readLEValue(DataInputStream var1, int var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var2; var4++) {
         var3 |= var1.readUnsignedByte() << var4 * 8;
      }

      return var3;
   }

   private final void updateAvailableEncodings() {
      String[] var1 = new String[this._supportedEncodingsNum];
      int var3 = this._encodingMappingTable.length;
      int var4 = 0;

      for (int var5 = 0; var4 < var1.length && var5 < var3; var5++) {
         ConversionDataRegistryHelper$EncodingMappingData var2 = this._encodingMappingTable[var4];
         if (var2 != null && (this.isAlgorithmicallySupported(var2._id) || var2._binaryData != null)) {
            var1[var4++] = var2._encoding;
         }
      }

      this._availableEncodings = var1;
   }

   private final boolean isAlgorithmicallySupported(int var1) {
      switch (var1) {
         case 0:
         case 1:
         case 18:
         case 27:
         case 28:
         case 39:
            return true;
         default:
            return false;
      }
   }
}
