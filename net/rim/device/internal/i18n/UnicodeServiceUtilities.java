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

   public static final byte resolveEncoding(byte[] var0, byte[] var1) {
      return _defaultUnicodeServiceProvider.resolveEncoding(var0, var1);
   }

   public static final byte resolveEncoding(byte[] var0) {
      return _defaultUnicodeServiceProvider.resolveEncoding(getSupportedEncodings(), var0);
   }

   public static final String getEncoding(byte var0) {
      if ((var0 & 128) == 128) {
         var0 = (byte)(var0 & -129);
      }

      switch ((byte)(var0 & 112)) {
         case 16:
         case 32:
         case 48:
         case 64:
            var0 = (byte)(var0 & -113);
         default:
            return _defaultUnicodeServiceProvider.getEncoding(var0);
      }
   }

   public static final byte getEncoding(String var0) {
      return _defaultUnicodeServiceProvider.getEncoding(var0);
   }

   public static final byte getPreferredEncoding() {
      Locale var0 = Locale.getDefaultInputForSystem();
      return (byte)(Locale.isLatinOneCharacterSetLocale(var0) ? 0 : 1);
   }

   public static final String readString(byte[] var0, int var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final int detectFutureData(byte[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
