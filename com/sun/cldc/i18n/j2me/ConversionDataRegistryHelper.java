package com.sun.cldc.i18n.j2me;

import java.io.DataInputStream;
import java.io.IOException;
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

   final synchronized boolean loadConversionData(String encodingName, int encodingID, int locale, String typeface, byte[][][] bData) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   final boolean isSupported(String encoding) {
      ConversionDataRegistryHelper$EncodingMappingData eData = this.runOverTheData(encoding);
      return eData != null && (this.isAlgorithmicallySupported(eData._id) || eData._binaryData != null);
   }

   final int getSuggestedLocale(String encoding) {
      ConversionDataRegistryHelper$EncodingMappingData eData = this.runOverTheData(encoding);
      return eData != null ? eData._locale : -1;
   }

   final String getSuggestedTypeface(String encoding) {
      ConversionDataRegistryHelper$EncodingMappingData eData = this.runOverTheData(encoding);
      if (eData != null) {
         return eData._typeface;
      }

      Font f = FontRegistry.getDefaultFont();
      return f != null ? f.getFontFamily().getName() : FontRegistry.DEFAULT_FAMILY;
   }

   final String getSuggestedTypeface(int localeCode) {
      ConversionDataRegistryHelper$EncodingMappingData eData = this.runOverTheData(localeCode);
      if (eData != null) {
         return eData._typeface;
      }

      Font f = FontRegistry.getDefaultFont();
      return f != null ? f.getFontFamily().getName() : FontRegistry.DEFAULT_FAMILY;
   }

   final String getSuggestedEncoding(int localeCode) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final synchronized byte[][][] getConversionData(int id, int[] dataOffset) {
      throw new RuntimeException("cod2jar: type check");
   }

   final int getEncodingIDLocal(String encoding) {
      throw new RuntimeException("cod2jar: string-special");
   }

   final int getEncodingID(String encoding) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final synchronized ConversionDataRegistryHelper$EncodingMappingData runOverTheData(String encoding) {
      int length = this._encodingMappingTable.length;

      for (int i = 0; i < length; i++) {
         ConversionDataRegistryHelper$EncodingMappingData eData = this._encodingMappingTable[i];
         if (eData != null && StringUtilities.startsWithIgnoreCase(encoding, eData._encoding, 1701707776)) {
            return eData;
         }
      }

      return null;
   }

   private final synchronized ConversionDataRegistryHelper$EncodingMappingData runOverTheData(int locale) {
      int length = this._encodingMappingTable.length;

      for (int i = 0; i < length; i++) {
         ConversionDataRegistryHelper$EncodingMappingData eData = this._encodingMappingTable[i];
         if (eData != null && eData._locale == locale) {
            return eData;
         }
      }

      return null;
   }

   private final int readHeader(DataInputStream stream) {
      try {
         int signature = this.readLEValue(stream, 4);
         stream.skip(2);
         int versionMajor = this.readLEValue(stream, 2);
         int versionMinor = this.readLEValue(stream, 2);
         if (signature == 1717724259 && versionMajor <= 1 && (versionMajor != 1 || versionMinor <= 0)) {
            stream.skip(2);
            return 12;
         } else {
            return 0;
         }
      } catch (IOException ioe) {
         return 0;
      }
   }

   private final int readLEValue(DataInputStream stream, int length) {
      int value = 0;

      for (int i = 0; i < length; i++) {
         value |= stream.readUnsignedByte() << i * 8;
      }

      return value;
   }

   private final void updateAvailableEncodings() {
      String[] swap = new String[this._supportedEncodingsNum];
      int length = this._encodingMappingTable.length;
      int i = 0;

      for (int j = 0; i < swap.length && j < length; j++) {
         ConversionDataRegistryHelper$EncodingMappingData eData = this._encodingMappingTable[i];
         if (eData != null && (this.isAlgorithmicallySupported(eData._id) || eData._binaryData != null)) {
            swap[i++] = eData._encoding;
         }
      }

      this._availableEncodings = swap;
   }

   private final boolean isAlgorithmicallySupported(int id) {
      switch (id) {
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
