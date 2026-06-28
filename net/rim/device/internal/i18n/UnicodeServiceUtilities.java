package net.rim.device.internal.i18n;

import net.rim.device.api.i18n.Locale;

public final class UnicodeServiceUtilities implements UnicodeServiceConstants {
   private static UnicodeServiceProvider _defaultUnicodeServiceProvider;

   public static final UnicodeServiceProvider getUnicodeServiceProvider() {
      return _defaultUnicodeServiceProvider;
   }

   public static final synchronized byte[] getSupportedEncodings() {
      return _defaultUnicodeServiceProvider.getSupportedEncodings();
   }

   public static final byte resolveEncoding(byte[] supportedEncodings, byte[] encodingsToDecideAgainst) {
      return _defaultUnicodeServiceProvider.resolveEncoding(supportedEncodings, encodingsToDecideAgainst);
   }

   public static final byte resolveEncoding(byte[] encodingsToDecideAgainst) {
      return _defaultUnicodeServiceProvider.resolveEncoding(getSupportedEncodings(), encodingsToDecideAgainst);
   }

   public static final String getEncoding(byte encodingType) {
      if ((encodingType & 128) == 128) {
         encodingType = (byte)(encodingType & -129);
      }

      switch ((byte)(encodingType & 112)) {
         case 16:
         case 32:
         case 48:
         case 64:
            encodingType = (byte)(encodingType & -113);
         default:
            return _defaultUnicodeServiceProvider.getEncoding(encodingType);
      }
   }

   public static final byte getEncoding(String encodingType) {
      return _defaultUnicodeServiceProvider.getEncoding(encodingType);
   }

   public static final byte getPreferredEncoding() {
      Locale locale = Locale.getDefaultInputForSystem();
      return (byte)(Locale.isLatinOneCharacterSetLocale(locale) ? 0 : 1);
   }

   public static final String readString(byte[] dataBuffer, int readPosition, int lengthToRead, boolean encoded) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int detectFutureData(byte[] dataBuffer, int readPosition, int lengthToRead) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
