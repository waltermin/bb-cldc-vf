package net.rim.device.internal.i18n;

import net.rim.device.api.i18n.Locale;

public final class LocaleUtil {
   private static final int ENGLISH_LOCALE_NUMBER;
   private static final int FRENCH_LOCALE_NUMBER;
   private static final int SPANISH_LOCALE_NUMBER;
   private static final int GERMAN_LOCALE_NUMBER;
   private static final int ITALIAN_LOCALE_NUMBER;
   private static final int ALL_LOCALES;

   public static final int convertCppLocaleToJavaLocale(int var0) {
      switch (var0) {
         case -2:
            return var0;
         case -1:
            return 0;
         case 0:
         default:
            return 1701707776;
         case 1:
            return 1718747136;
         case 2:
            return 1702035456;
         case 3:
            return 1684340736;
         case 4:
            return 1769209856;
      }
   }

   public static final int convertJavaLocaleToCppLocale(int var0) {
      if (var0 == 0) {
         return -1;
      }

      Locale var1 = Locale.get(var0);
      String var2 = var1.getLanguage();
      Locale var3 = Locale.get(var2);
      var0 = var3.getCode();
      switch (var0) {
         case 1684340736:
            return 3;
         case 1701707776:
            return 0;
         case 1702035456:
            return 2;
         case 1718747136:
            return 1;
         case 1769209856:
            return 4;
         default:
            return var0;
      }
   }
}
