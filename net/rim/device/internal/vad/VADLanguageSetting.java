package net.rim.device.internal.vad;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.ApplicationRegistry;

public class VADLanguageSetting {
   protected static final long GUID;

   public String[] getSupportedLanguages() {
      throw null;
   }

   public int getLanguageIndexForLocale(Locale var1) {
      throw null;
   }

   public void setLanguageIndex(int var1) {
      throw null;
   }

   public int getLanguageIndex() {
      throw null;
   }

   public String[] getVersionInfo() {
      throw null;
   }

   public static VADLanguageSetting getInstance() {
      return (VADLanguageSetting)ApplicationRegistry.getApplicationRegistry().get(7371110663783438069L);
   }
}
