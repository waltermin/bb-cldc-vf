package net.rim.device.internal.i18n;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.system.ApplicationDescriptor;

public final class LocaleInternal {
   public static final String LOCALE_SEPARATOR;
   public static final char LOCALE_SEPARATOR_CHAR;
   public static final String LOCALE_SEPARATOR_STRING;
   public static final String CRB_EXTENSION;
   private static String RUNTIME_RESOURCES;
   private static ResourceBundleFamily _resourceFamily;
   private static ResourceBundle _resources;

   public static final void addLocale() {
      String var0 = ApplicationDescriptor.currentApplicationDescriptor().getModuleName();
      if (var0.startsWith(RUNTIME_RESOURCES)) {
         throw new Object();
      }

      Locale.addLocaleInternal(getLocaleFromModule());
   }

   public static final Locale getLocaleFromModule() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Locale getLocaleFromModuleName(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final Locale getLocaleFromModuleName(String var0, String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final ResourceBundleFamily getBundle() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String getString(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
