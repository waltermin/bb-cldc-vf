package net.rim.tid.im;

import net.rim.device.api.i18n.Locale;
import net.rim.tid.awt.im.repository.CustomWordsRepository;
import net.rim.tid.awt.im.spi.InputMethod;
import net.rim.tid.awt.im.spi.InputMethodDescriptor;

public class MinimalIMDescriptor implements InputMethodDescriptor {
   private Locale[] _locales;
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
   public String getInputMethodDisplayName(Locale var1, Locale var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public InputMethod createInputMethod() {
      return new MinimalInputMethod(this.getAvailableLocales());
   }

   protected static boolean verifySystemModulePresent(Locale var0) {
      throw new RuntimeException("cod2jar: exception table");
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
   public CustomWordsRepository getRepository(int var1) {
      return null;
   }

   @Override
   public void disposeCache() {
   }
}
