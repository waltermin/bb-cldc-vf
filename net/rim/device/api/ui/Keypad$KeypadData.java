package net.rim.device.api.ui;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.vm.Persistable;

final class Keypad$KeypadData implements Persistable {
   public int _localeCode;
   private int _osLocale;
   private int _osLocaleVariant;
   private int _keypadID;

   public Keypad$KeypadData() {
      this.setLocale(Locale.getDefaultForKeyboard());
   }

   private final Locale getLocale() {
      return Locale.get(this._localeCode, Locale.convertKeyboardIDToString(0), this._keypadID);
   }

   private final Locale getOSLocale() {
      return Locale.get(this._osLocale, Locale.convertKeyboardIDToString(this._osLocaleVariant));
   }

   public final void setLocale(Locale locale) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void setKeymap(Locale locale, ResourceBundle bundle) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
