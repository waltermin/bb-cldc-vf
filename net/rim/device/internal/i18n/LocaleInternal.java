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
      String module = ApplicationDescriptor.currentApplicationDescriptor().getModuleName();
      if (module.startsWith(RUNTIME_RESOURCES)) {
         throw new IllegalStateException();
      }

      Locale.addLocaleInternal(getLocaleFromModule());
   }

   public static final Locale getLocaleFromModule() {
      String module = ApplicationDescriptor.currentApplicationDescriptor().getModuleName();
      return getLocaleFromModuleName(module, "__");
   }

   public static final Locale getLocaleFromModuleName(String module) {
      return getLocaleFromModuleName(module, "£");
   }

   private static final Locale getLocaleFromModuleName(String module, String separator) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final ResourceBundleFamily getBundle() {
      if (_resourceFamily == null) {
         _resourceFamily = ResourceBundle.getBundle(8736789735327653723L, "net.rim.device.internal.resource.Locale");
      }

      return _resourceFamily;
   }

   public static final String getString(int id) {
      if (_resources == null) {
         _resources = ResourceBundle.getBundle(8736789735327653723L, "net.rim.device.internal.resource.Locale").getBundle(Locale.get(0));
      }

      return _resources.getString(id);
   }
}
