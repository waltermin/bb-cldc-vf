package net.rim.tid.im;

import net.rim.device.api.i18n.Locale;
import net.rim.device.internal.i18n.ResourceBundleFetcher;
import net.rim.device.internal.util.StringUtilitiesInternal;
import net.rim.tid.awt.im.repository.CustomWordsRepository;
import net.rim.tid.awt.im.spi.InputMethod;
import net.rim.tid.awt.im.spi.InputMethodDescriptor;

public class MinimalIMDescriptor implements InputMethodDescriptor {
   private Locale[] _locales = new Locale[]{
      Locale.get("en", "US", "Multitap"),
      Locale.get("en"),
      Locale.get("en", "US"),
      Locale.get("en", "GB"),
      Locale.get("en", "NL"),
      Locale.get("de"),
      Locale.get("es"),
      Locale.get("fr"),
      Locale.get("it"),
      Locale.get("pt"),
      Locale.get("pt", "BR"),
      Locale.get("cs"),
      Locale.get("hu"),
      Locale.get("pl"),
      Locale.get("ca"),
      Locale.get("af"),
      Locale.get("nl"),
      Locale.get("tr"),
      Locale.get("el"),
      Locale.get("sv"),
      Locale.get("no"),
      Locale.get("he"),
      Locale.get("he", "US"),
      Locale.get("he", "IL")
   };
   private Locale[] _displayLocales;
   private Locale[] _availableLocales;

   @Override
   public Locale[] getAvailableLocales() {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public Locale[] getDisplayLocales() {
      if (this._displayLocales == null) {
         this.getAvailableLocales();
      }

      return this._displayLocales;
   }

   @Override
   public boolean hasDynamicLocaleList() {
      return true;
   }

   @Override
   public String getInputMethodDisplayName(Locale inputLocale, Locale displayLanguage) {
      return "";
   }

   @Override
   public InputMethod createInputMethod() {
      return new MinimalInputMethod(this.getAvailableLocales());
   }

   protected static boolean verifySystemModulePresent(Locale locale) {
      try {
         StringBuffer scratch = StringUtilitiesInternal.getScratchBuffer();
         String name;
         synchronized (scratch) {
            scratch.append("net.rim.device.internal.resource.Input");
            scratch.append('£');
            scratch.append(locale);
            name = scratch.toString();
            scratch.setLength(0);
         }

         boolean exists = ResourceBundleFetcher.verifyCompressedResourcePresent(name + ".crb");
         if (exists) {
            return true;
         }

         Class.forName(name);
         return true;
      } catch (ClassNotFoundException var6) {
         return false;
      }
   }

   @Override
   public long getInputMethodID() {
      return 1;
   }

   @Override
   public boolean forTestOnly() {
      return false;
   }

   @Override
   public CustomWordsRepository getRepository(int type) {
      return null;
   }

   @Override
   public void disposeCache() {
   }
}
