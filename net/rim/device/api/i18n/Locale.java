package net.rim.device.api.i18n;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.Branding;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.system.SIMCard;
import net.rim.device.api.system.SIMCardException;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.i18n.DateTimeFormatOptions;
import net.rim.device.internal.i18n.LocaleInternal;
import net.rim.device.internal.i18n.ResourceBundleFetcher;
import net.rim.device.internal.util.StringUtilitiesInternal;
import net.rim.tid.awt.im.InputContext;
import net.rim.vm.Array;
import net.rim.vm.TraceBack;

public final class Locale {
   private int _code;
   private String _variant;
   private int _keyboardID;
   private static final int A;
   private static final int B;
   private static final int C;
   private static final int D;
   private static final int E;
   private static final int F;
   private static final int G;
   private static final int H;
   private static final int I;
   private static final int J;
   private static final int K;
   private static final int L;
   private static final int M;
   private static final int N;
   private static final int O;
   private static final int P;
   private static final int Q;
   private static final int R;
   private static final int S;
   private static final int T;
   private static final int U;
   private static final int V;
   private static final int W;
   private static final int X;
   private static final int Y;
   private static final int Z;
   private static final int[] _languagesISO639;
   private static final int _languagesISO639Count;
   private static final int[] _countriesISO3166;
   private static final int _countriesISO3166Count;
   private static final short[] _latin1CharacterSetsLocales;
   private static final int[] US_VENDORS;
   private static final long LOCALE;
   private static String EMPTY;
   public static final long GUID_LOCALE_CHANGED;
   public static final long GUID_INPUT_LOCALE_CHANGED;
   public static final long GUID_NAME_ORDER_CHANGED;
   public static final int APPLICATION;
   public static final int NO_KEYBOARD_ID;
   public static final int LOCALE_SET_DECLINED;
   public static final int LOCALE_SET_OK;
   private static PersistentObject _persist;
   private static Locale$LocalePersist _localePersist;
   private static final long REGISTRY_NAME;
   private static Locale$Locales _locales;
   private static Locale _defaultLocale;
   private static Locale _defaultInputLocale;
   public static final int LOCALE_ROOT;
   public static final int LOCALE_ar;
   public static final int LOCALE_ca;
   public static final int LOCALE_cs;
   public static final int LOCALE_de;
   public static final int LOCALE_en;
   public static final int LOCALE_en_GB;
   public static final int LOCALE_en_US;
   public static final int LOCALE_es;
   public static final int LOCALE_es_MX;
   public static final int LOCALE_fr;
   public static final int LOCALE_fr_CA;
   public static final int LOCALE_he;
   public static final int LOCALE_hu;
   public static final int LOCALE_it;
   public static final int LOCALE_ko;
   public static final int LOCALE_nl;
   public static final int LOCALE_pt;
   public static final int LOCALE_pt_BR;
   public static final int LOCALE_zh;
   public static final int LOCALE_zh_CN;
   public static final int LOCALE_zh_HK;
   public static final int LOCALE_el;
   public static final int LOCALE_tr;
   public static final int LOCALE_ru;
   public static final int LOCALE_pl;
   public static final int LOCALE_ja;
   public static final int KEYBOARD_ID_QWERTY;
   public static final int KEYBOARD_ID_QWERTY_PHONE;
   public static final int KEYBOARD_ID_QWERTY_REDUCED;
   public static final int KEYBOARD_ID_QWERTY_LEGACY;
   public static final int FIRST_NAME_ORDER;
   public static final int LAST_NAME_ORDER;

   private Locale(int code, String variant) {
      if (!verifyCode(code)) {
         throw new IllegalArgumentException("Invalid locale code: " + code);
      }

      if (variant == null || variant.length() == 0) {
         variant = EMPTY;
      }

      if (variant != EMPTY && code == 0) {
         throw new IllegalArgumentException("Invalid locale code: " + code);
      }

      this._code = code;
      this._variant = variant;
      this._keyboardID = -1;
   }

   public static final boolean isLatinOneCharacterSetLocale(Locale locale) {
      if (_latin1CharacterSetsLocales != null && locale != null) {
         short code = (short)(locale.getCode() >> 16);

         for (int i = 0; i < _latin1CharacterSetsLocales.length; i++) {
            if (code == _latin1CharacterSetsLocales[i]) {
               return true;
            }
         }
      }

      return false;
   }

   public static final void addLocaleInternal(Locale locale) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      synchronized (_locales.available) {
         Array.resize(_locales.available, _locales.available.length + 1);
         _locales.available[_locales.available.length - 1] = locale;
         Arrays.sort(_locales.available, _locales.comparator);
      }
   }

   public static final void addInputLocaleInternal(Locale locale) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      synchronized (_locales.inputAvailable) {
         if (Arrays.getIndex(_locales.inputAvailable, locale) == -1) {
            Array.resize(_locales.inputAvailable, _locales.inputAvailable.length + 1);
            _locales.inputAvailable[_locales.inputAvailable.length - 1] = locale;
            Arrays.sort(_locales.inputAvailable, _locales.comparator);
         }
      }
   }

   public static final String convertCodeToVariant(int code) {
      return convertKeyboardIDToString(code);
   }

   public static final int convertVariantToCcode(String variant) {
      return convertStringToKeyboardID(variant);
   }

   @Override
   public final boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }

      if (!(obj instanceof Locale)) {
         return false;
      }

      Locale l = (Locale)obj;
      return this._code == l._code && this._variant.equals(l._variant);
   }

   public static final Locale get(int code) {
      return get(code, null);
   }

   public static final Locale get(int code, String variant) {
      if (variant == null) {
         variant = EMPTY;
      }

      synchronized (_locales.used) {
         Locale locale = null;
         if (variant == EMPTY) {
            locale = (Locale)_locales.used.get(code);
         }

         if (locale == null) {
            locale = new Locale(code, variant);
            if (variant == EMPTY) {
               _locales.used.put(code, locale);
            }
         }

         return locale;
      }
   }

   public static final Locale get(String language) {
      return get(language, null, null);
   }

   public static final Locale get(String language, String country) {
      return get(language, country, null);
   }

   public static final Locale get(String language, String country, String variant) {
      int code = pack(language, country);
      return get(code, variant);
   }

   public static final Locale get(int code, String variant, int keyboardID) {
      if (keyboardID == -1) {
         return get(code, variant);
      }

      Locale result = new Locale(code, variant);
      result._keyboardID = keyboardID;
      return result;
   }

   public static final Locale[] getAvailableLocales() {
      synchronized (_locales.available) {
         Locale[] result = new Locale[_locales.available.length];
         System.arraycopy(_locales.available, 0, result, 0, result.length);
         return result;
      }
   }

   public static final Locale[] getAvailableInputLocales() {
      synchronized (_locales.inputAvailable) {
         Locale[] result = new Locale[_locales.inputAvailable.length];
         System.arraycopy(_locales.inputAvailable, 0, result, 0, result.length);
         return result;
      }
   }

   public final int getCode() {
      return this._code;
   }

   public final String getCountry() {
      String country = EMPTY;
      if ((this._code & 65535) != 0) {
         country = EMPTY + (char)(this._code >> 8 & 0xFF) + (char)(this._code >> 0 & 0xFF);
      }

      return country;
   }

   public static final String getDefaultCountry() {
      String country = EMPTY;
      int code = getDefaultLanguageLocale();
      if ((code & 65535) != 0) {
         country = EMPTY + (char)(code >> 8 & 0xFF) + (char)(code >> 0 & 0xFF);
      }

      return country;
   }

   public static final Locale getDefault() {
      return _defaultLocale != null ? _defaultLocale : _locales.current;
   }

   public static final Locale getDefaultInput() {
      if (_defaultInputLocale != null) {
         return _defaultInputLocale;
      } else {
         return _locales.currentInput != null ? _locales.currentInput : getDefault();
      }
   }

   public static final Locale getDefaultForSystem() {
      return _locales.current;
   }

   public static final Locale getDefaultInputForSystem() {
      return _locales.currentInput;
   }

   public static final Locale getDefaultForKeyboard() {
      int code = checkDefaultCode(Keypad.getHardwareMap());
      return get(code);
   }

   private static final Locale getDefaultFromOS() {
      int code = checkDefaultCode(getDefaultLanguageLocale());
      return get(code, null);
   }

   private static final int checkDefaultCode(int code) {
      if (verifyCode(code) && code != 0) {
         return code;
      }

      int vendorId = Branding.getVendorId();

      for (int i = US_VENDORS.length - 1; i >= 0; i--) {
         if (US_VENDORS[i] == vendorId) {
            return 1701729619;
         }
      }

      return 1701726018;
   }

   public static final int getDefaultNameOrder(int _localeCode) {
      String str = get(_localeCode).getLanguage();
      return !str.equals("zh") && !str.equals("ja") ? 0 : 1;
   }

   public final String getDisplayCountry() {
      int country = this._code & 65535;
      return country == 0 ? EMPTY : LocaleInternal.getString(65536 + country);
   }

   public final String getDisplayLanguage() {
      int language = this._code >> 16 & 65535;
      if (language == 0) {
         return EMPTY;
      }

      String result = null;
      ResourceBundle bundle = LocaleInternal.getBundle().getBundle(this);
      if (bundle.getLocale().getCode() == this._code) {
         result = (String)bundle.getObject(2, false);
      }

      if (result == null) {
         result = LocaleInternal.getString(65536 + language);
      }

      return result;
   }

   public final String getDisplayName() {
      String language = this.getDisplayLanguage();
      String country = this.getDisplayCountry();
      String variant = this.getDisplayVariant();
      if (country.length() == 0 && variant.length() != 0) {
         country = variant;
         variant = EMPTY;
      }

      String result = null;
      if (country.length() != 0) {
         result = MessageFormat.format(LocaleInternal.getString(100), new Object[]{language, country, variant != EMPTY ? "," : EMPTY, variant});
      } else {
         result = language;
      }

      return result;
   }

   public final String getDisplayVariant() {
      return this._variant;
   }

   public static final int getSystemKeyboardID() {
      return Keypad.getHardwareLayout();
   }

   public final boolean isKeyboardIDSet() {
      return this._keyboardID != -1;
   }

   public final int getKeyboardID() {
      return this._keyboardID;
   }

   public static final String convertKeyboardIDToString(int aID) {
      if (aID != 0 && aID != -1) {
         StringBuffer buffer = _locales.buffer;
         synchronized (buffer) {
            buffer.setLength(0);

            for (int lv = 24; lv >= 0; lv -= 8) {
               char ch = (char)(aID >> lv & 0xFF);
               if (ch == 0) {
                  break;
               }

               buffer.append(ch);
            }

            return buffer.toString();
         }
      } else {
         return "";
      }
   }

   public static final String getPersonalNamesSeparator() {
      return getPersonalNamesSeparator(getSystemNameOrder());
   }

   public static final String getPersonalNamesSeparator(int order) {
      String nameSeparator;
      switch (order) {
         case -1:
            throw new IllegalArgumentException("Illegal order ID " + order);
         case 0:
         default:
            nameSeparator = LocaleInternal.getBundle().getString(83539);
            break;
         case 1:
            nameSeparator = LocaleInternal.getBundle().getString(83540);
      }

      return nameSeparator != null && nameSeparator.length() >= 1 ? nameSeparator : "";
   }

   public static final int convertStringToKeyboardID(String variant) {
      if (variant == null) {
         return -1;
      }

      if (variant.length() == 0) {
         return 0;
      }

      int result = 0;
      int shift = 24;
      int length = variant.length();

      for (int lv = 0; lv < length; lv++) {
         result |= variant.charAt(lv) << shift;
         shift -= 8;
      }

      return result;
   }

   public static final String[] getISOCountries() {
      String[] countries = new String[239];
      StringBuffer buffer = _locales.buffer;
      synchronized (buffer) {
         buffer.setLength(0);
         int count = 0;

         for (int first = 0; first < 26; first++) {
            for (int second = 0; second < 26; second++) {
               if ((1 << second & _countriesISO3166[first]) != 0) {
                  buffer.setLength(0);
                  buffer.append((char)(65 + first));
                  buffer.append((char)(65 + second));
                  countries[count++] = buffer.toString();
               }
            }
         }

         return countries;
      }
   }

   public static final String[] getISOLanguages() {
      String[] languages = new String[139];
      StringBuffer buffer = _locales.buffer;
      synchronized (buffer) {
         buffer.setLength(0);
         int count = 0;

         for (int first = 0; first < 26; first++) {
            for (int second = 0; second < 26; second++) {
               if ((1 << second & _languagesISO639[first]) != 0) {
                  buffer.setLength(0);
                  buffer.append((char)(97 + first));
                  buffer.append((char)(97 + second));
                  languages[count++] = buffer.toString();
               }
            }
         }

         return languages;
      }
   }

   public final String getLanguage() {
      String language = EMPTY;
      if ((this._code & -65536) != 0) {
         language = EMPTY + (char)(this._code >> 24 & 0xFF) + (char)(this._code >> 16 & 0xFF);
      }

      return language;
   }

   final Locale getParent() {
      int code = this._code;
      if (this._variant == EMPTY) {
         if ((this._code & 65535) != 0) {
            code &= -65536;
         } else {
            if ((this._code & -65536) == 0) {
               return null;
            }

            code = 0;
         }
      }

      return get(code);
   }

   public static final int getSystemNameOrder() {
      return _localePersist.nameOrder;
   }

   public final String getVariant() {
      return this._variant;
   }

   @Override
   public final int hashCode() {
      return this._variant == EMPTY ? this._code : this._code ^ this._variant.hashCode();
   }

   public static final boolean isDefaultForKeyboardSet() {
      int code = Keypad.getHardwareMap();
      return verifyCode(code) && code != 0;
   }

   private static final int pack(String language, String country) {
      if (!verifyLanguage(language)) {
         throw new IllegalArgumentException("Invalid language: " + language);
      }

      if (!verifyCountry(country)) {
         throw new IllegalArgumentException("Invalid country: " + country);
      }

      int code = 0;
      if (language != null && !language.equals(EMPTY)) {
         code |= language.charAt(0) << 24 | language.charAt(1) << 16;
      }

      if (country != null && !country.equals(EMPTY)) {
         code |= country.charAt(0) << '\b' | country.charAt(1) << 0;
      }

      return code;
   }

   public static final Locale parse(String id) {
      if (id == null) {
         return null;
      }

      int code = 0;
      String variant = null;

      for (int lv = id.length() - 1; lv >= 0; lv--) {
         char ch = id.charAt(lv);
         if (ch > 127) {
            throw new IllegalArgumentException();
         }
      }

      int length = id.length();
      int pos = 0;

      try {
         if (pos < length) {
            if (id.charAt(pos) != '_') {
               char l0 = id.charAt(pos + 0);
               char l1 = id.charAt(pos + 1);
               code |= l0 << 24 | l1 << 16;
               pos += 2;
            }

            if (pos < length) {
               if (id.charAt(pos) != '_') {
                  throw new IllegalArgumentException();
               }

               if (id.charAt(++pos) != '_') {
                  char c0 = id.charAt(pos + 0);
                  char c1 = id.charAt(pos + 1);
                  code |= c0 << '\b' | c1 << 0;
                  pos += 2;
               }

               if (pos < length) {
                  if (id.charAt(pos) != '_') {
                     throw new IllegalArgumentException();
                  }

                  if (++pos == length) {
                     throw new IllegalArgumentException();
                  }

                  variant = id.substring(pos);
               }
            }
         }
      } catch (IndexOutOfBoundsException e) {
         throw new IllegalArgumentException();
      }

      return get(code, variant);
   }

   public static final boolean isLocaleEligbleForRemovable(Locale locale, boolean inputLocale) {
      if (locale == null) {
         return false;
      }

      int localeCode = locale.getCode();
      return localeCode != 0 && localeCode != 1701707776 && localeCode != getDefaultLanguageLocale()
         ? !inputLocale || checkDefaultCode(0) != localeCode
         : false;
   }

   public static final void removeLocaleInternal(Locale locale) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      int indexToRemove = Arrays.binarySearch(_locales.available, locale, _locales.comparator, 0, _locales.available.length);
      if (indexToRemove > 0 && locale.getCode() == _locales.available[indexToRemove].getCode() && isLocaleEligbleForRemovable(locale, false)) {
         String paramVariant = locale.getVariant();
         String localeVariant = _locales.available[indexToRemove].getVariant();
         if (paramVariant != null && localeVariant != null && paramVariant.equals(localeVariant)) {
            Arrays.remove(_locales.available, _locales.available[indexToRemove]);
         }
      }

      indexToRemove = Arrays.binarySearch(_locales.inputAvailable, locale, _locales.comparator, 0, _locales.inputAvailable.length);
      if (indexToRemove >= 0 && locale.getCode() == _locales.inputAvailable[indexToRemove].getCode() && isLocaleEligbleForRemovable(locale, true)) {
         String paramVariant = locale.getVariant();
         String localeVariant = _locales.inputAvailable[indexToRemove].getVariant();
         if (paramVariant != null && localeVariant != null && paramVariant.equals(localeVariant)) {
            Arrays.remove(_locales.inputAvailable, _locales.inputAvailable[indexToRemove]);
         }
      }
   }

   public static final void setDefault(Locale defaultLocale) {
      _defaultLocale = defaultLocale;
      int pid = Application.getApplication().getProcessId();
      RIMGlobalMessagePoster.postGlobalEvent(pid, -7464003439710973532L, 1, 0, null, null);
      RIMGlobalMessagePoster.postGlobalEvent(pid, -8040378802380461050L, 1, 0, null, null);
      RIMGlobalMessagePoster.postGlobalEvent(pid, -1438311245835636745L, 1, 0, null, null);
      DateTimeFormatOptions.onAppLocaleChange();
      RIMGlobalMessagePoster.postGlobalEvent(pid, 7207871974803693937L, 1, 0, null, null);
   }

   public static final void setDefaultInput(Locale defaultLocale) {
      if (InputContext.getInstance().selectInputMethod(defaultLocale)) {
         _defaultInputLocale = defaultLocale;
         int pid = Application.getApplication().getProcessId();
         RIMGlobalMessagePoster.postGlobalEvent(pid, -8040378802380461050L, 1, 0, null, null);
      }
   }

   public static final void setDefaultForSystem(Locale locale) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      synchronized (_locales.available) {
         int index = Arrays.binarySearch(_locales.available, locale, _locales.comparator, 0, _locales.available.length);
         if (index < 0 || locale.getCode() == 0) {
            Locale searchLocale = get(locale.getCode() & -65536);
            index = Arrays.binarySearch(_locales.available, searchLocale, _locales.comparator, 0, _locales.available.length);
         }

         if (index < 0 || locale.getCode() == 0) {
            throw new IllegalArgumentException("Unsupported locale: " + locale.toString());
         }
      }

      synchronized (_persist) {
         _localePersist.code = locale.getCode();
         _localePersist.variant = locale.getVariant();
         _persist.commit();
         _locales.current = locale;
      }

      RIMGlobalMessagePoster.postGlobalEvent(-7464003439710973532L);
      RIMGlobalMessagePoster.postGlobalEvent(-8040378802380461050L);
      RIMGlobalMessagePoster.postGlobalEvent(-1438311245835636745L);
      DateTimeFormatOptions.onSystemLocaleChange();
      RIMGlobalMessagePoster.postGlobalEvent(7207871974803693937L);
      InputContext.getInstance().selectInputMethod(getDefaultInputForSystem());
      locale.setDisplayLocale(locale.getCode());
      if (SIMCard.isSupported()) {
         try {
            SIMCard.atSetLocale(locale.getCode());
            return;
         } catch (SIMCardException var7) {
         }
      }
   }

   private final native void setDisplayLocale(int var1);

   private final native void setInputLocale(int var1);

   public static final int setDefaultInputForSystem(Locale locale) {
      int callingModule = TraceBack.getCallingModule(0);
      return setDefaultInputForSystemImpl(locale, true, callingModule);
   }

   public static final int setDefaultInputForSystem(Locale locale, boolean updateInput) {
      int callingModule = TraceBack.getCallingModule(0);
      return setDefaultInputForSystemImpl(locale, updateInput, callingModule);
   }

   private static final int setDefaultInputForSystemImpl(Locale locale, boolean updateInput, int callingModule) {
      if (!ControlledAccess.verifyRRISignature(callingModule)) {
         ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
      }

      if (updateInput && !InputContext.getInstance().selectInputMethod(locale)) {
         return 0;
      }

      synchronized (_persist) {
         if (locale == null) {
            _localePersist.inputCode = -1;
            _localePersist.inputVariant = null;
         } else {
            _localePersist.inputCode = locale.getCode();
            _localePersist.inputVariant = locale.getVariant();
         }

         _persist.commit();
         _locales.currentInput = locale;
      }

      if (locale != null) {
         locale.setInputLocale(locale.getCode());
      }

      RIMGlobalMessagePoster.postGlobalEvent(-8040378802380461050L);
      return 1;
   }

   public static final String getCLDCLocaleString() {
      return _locales.current.buildToString(false).replace('_', '-');
   }

   public static final void setNameOrder(int order) {
      synchronized (_persist) {
         _localePersist.nameOrder = order;
         _persist.commit();
      }

      RIMGlobalMessagePoster.postGlobalEvent(-1438311245835636745L);
   }

   @Override
   public final String toString() {
      return this._code == 0 ? EMPTY : this.buildToString(true);
   }

   private final String buildToString(boolean withVariant) {
      StringBuffer buffer = _locales.buffer;
      synchronized (buffer) {
         buffer.setLength(0);
         if ((this._code & -65536) != 0) {
            buffer.append((char)(this._code >> 24 & 0xFF));
            buffer.append((char)(this._code >> 16 & 0xFF));
         }

         if ((this._code & 65535) != 0) {
            buffer.append('_');
            buffer.append((char)(this._code >> 8 & 0xFF));
            buffer.append((char)(this._code >> 0 & 0xFF));
         }

         if (withVariant && this._variant != EMPTY) {
            if ((this._code & 65535) == 0) {
               buffer.append('_');
            }

            buffer.append('_');
            buffer.append(this._variant);
         }

         return buffer.toString();
      }
   }

   public static final boolean verifyCode(int code) {
      try {
         return ((code & -65536) == 0 || (_languagesISO639[(code >>> 24) - 97] & 1 << (code >> 16 & 0xFF) - 97) != 0)
            && ((code & 65535) == 0 || (_countriesISO3166[(code >> 8 & 0xFF) - 65] & 1 << (code & 0xFF) - 65) != 0);
      } catch (Exception e) {
         return false;
      }
   }

   private static final boolean verifyCountry(String country) {
      try {
         return country == null || country.equals(EMPTY) || (_countriesISO3166[country.charAt(0) - 'A'] & 1 << country.charAt(1) - 'A') != 0;
      } catch (Exception e) {
         return false;
      }
   }

   private static final boolean verifyLanguage(String language) {
      try {
         return language == null || language.equals(EMPTY) || (_languagesISO639[language.charAt(0) - 'a'] & 1 << language.charAt(1) - 'a') != 0;
      } catch (Exception e) {
         return false;
      }
   }

   private static final Locale verifySystemModulePresent(Locale locale) {
      boolean giveUp = false;

      while (true) {
         try {
            StringBuffer scratch = StringUtilitiesInternal.getScratchBuffer();
            String name;
            synchronized (scratch) {
               scratch.append("net.rim.device.internal.resource.Locale");
               scratch.append('£');
               scratch.append(locale);
               name = scratch.toString();
               scratch.setLength(0);
            }

            boolean exists = ResourceBundleFetcher.verifyCompressedResourcePresent(name + ".crb");
            if (exists) {
               return locale;
            }

            Class.forName(name);
            return locale;
         } catch (ClassNotFoundException e) {
            if (giveUp) {
               throw new IllegalArgumentException();
            }

            int code = locale.getCode();
            if ((code & 65535) != 0) {
               locale = get(code & -65536);
            } else {
               locale = get(1701707776);
               giveUp = true;
            }
         }
      }
   }

   private static final native int getDefaultLanguageLocale();
}
