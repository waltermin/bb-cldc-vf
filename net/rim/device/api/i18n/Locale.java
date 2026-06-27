package net.rim.device.api.i18n;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.Branding;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.i18n.DateTimeFormatOptions;
import net.rim.device.internal.i18n.LocaleInternal;
import net.rim.tid.awt.im.InputContext;
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

   private Locale(int var1, String var2) {
   }

   public static final boolean isLatinOneCharacterSetLocale(Locale var0) {
      if (_latin1CharacterSetsLocales != null && var0 != null) {
         short var1 = (short)(var0.getCode() >> 16);

         for (int var2 = 0; var2 < _latin1CharacterSetsLocales.length; var2++) {
            if (var1 == _latin1CharacterSetsLocales[var2]) {
               return true;
            }
         }
      }

      return false;
   }

   public static final void addLocaleInternal(Locale var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void addInputLocaleInternal(Locale var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String convertCodeToVariant(int var0) {
      return convertKeyboardIDToString(var0);
   }

   public static final int convertVariantToCcode(String var0) {
      return convertStringToKeyboardID(var0);
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Locale get(int var0) {
      return get(var0, null);
   }

   public static final Locale get(int var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Locale get(String var0) {
      return get(var0, null, null);
   }

   public static final Locale get(String var0, String var1) {
      return get(var0, var1, null);
   }

   public static final Locale get(String var0, String var1, String var2) {
      int var3 = pack(var0, var1);
      return get(var3, var2);
   }

   public static final Locale get(int var0, String var1, int var2) {
      if (var2 == -1) {
         return get(var0, var1);
      }

      Locale var3 = new Locale(var0, var1);
      var3._keyboardID = var2;
      return var3;
   }

   public static final Locale[] getAvailableLocales() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Locale[] getAvailableInputLocales() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int getCode() {
      return this._code;
   }

   public final String getCountry() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public static final String getDefaultCountry() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
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
      int var0 = checkDefaultCode(Keypad.getHardwareMap());
      return get(var0);
   }

   private static final Locale getDefaultFromOS() {
      int var0 = checkDefaultCode(getDefaultLanguageLocale());
      return get(var0, null);
   }

   private static final int checkDefaultCode(int var0) {
      if (verifyCode(var0) && var0 != 0) {
         return var0;
      }

      int var1 = Branding.getVendorId();

      for (int var2 = US_VENDORS.length - 1; var2 >= 0; var2--) {
         if (US_VENDORS[var2] == var1) {
            return 1701729619;
         }
      }

      return 1701726018;
   }

   public static final int getDefaultNameOrder(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String getDisplayCountry() {
      int var1 = this._code & 65535;
      return var1 == 0 ? EMPTY : LocaleInternal.getString(65536 + var1);
   }

   public final String getDisplayLanguage() {
      int var1 = this._code >> 16 & 65535;
      if (var1 == 0) {
         return EMPTY;
      }

      Object var2 = null;
      ResourceBundle var3 = LocaleInternal.getBundle().getBundle(this);
      if (var3.getLocale().getCode() == this._code) {
         var2 = var3.getObject(2, false);
      }

      if (var2 == null) {
         var2 = LocaleInternal.getString(65536 + var1);
      }

      return (String)var2;
   }

   public final String getDisplayName() {
      throw new RuntimeException("cod2jar: string-special");
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

   public static final String convertKeyboardIDToString(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String getPersonalNamesSeparator() {
      return getPersonalNamesSeparator(getSystemNameOrder());
   }

   public static final String getPersonalNamesSeparator(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final int convertStringToKeyboardID(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String[] getISOCountries() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String[] getISOLanguages() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final String getLanguage() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   final Locale getParent() {
      int var1 = this._code;
      if (this._variant == EMPTY) {
         if ((this._code & 65535) != 0) {
            var1 &= -65536;
         } else {
            if ((this._code & -65536) == 0) {
               return null;
            }

            var1 = 0;
         }
      }

      return get(var1);
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
      int var0 = Keypad.getHardwareMap();
      return verifyCode(var0) && var0 != 0;
   }

   private static final int pack(String var0, String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Locale parse(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isLocaleEligbleForRemovable(Locale var0, boolean var1) {
      if (var0 == null) {
         return false;
      }

      int var2 = var0.getCode();
      return var2 != 0 && var2 != 1701707776 && var2 != getDefaultLanguageLocale() ? !var1 || checkDefaultCode(0) != var2 : false;
   }

   public static final void removeLocaleInternal(Locale var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      int var1 = Arrays.binarySearch(_locales.available, var0, _locales.comparator, 0, _locales.available.length);
      if (var1 > 0 && var0.getCode() == _locales.available[var1].getCode() && isLocaleEligbleForRemovable(var0, false)) {
         String var2 = var0.getVariant();
         String var3 = _locales.available[var1].getVariant();
         if (var2 != null && var3 != null && var2.equals(var3)) {
            Arrays.remove(_locales.available, _locales.available[var1]);
         }
      }

      var1 = Arrays.binarySearch(_locales.inputAvailable, var0, _locales.comparator, 0, _locales.inputAvailable.length);
      if (var1 >= 0 && var0.getCode() == _locales.inputAvailable[var1].getCode() && isLocaleEligbleForRemovable(var0, true)) {
         String var5 = var0.getVariant();
         String var6 = _locales.inputAvailable[var1].getVariant();
         if (var5 != null && var6 != null && var5.equals(var6)) {
            Arrays.remove(_locales.inputAvailable, _locales.inputAvailable[var1]);
         }
      }
   }

   public static final void setDefault(Locale var0) {
      _defaultLocale = var0;
      int var1 = Application.getApplication().getProcessId();
      RIMGlobalMessagePoster.postGlobalEvent(var1, -7464003439710973532L, 1, 0, null, null);
      RIMGlobalMessagePoster.postGlobalEvent(var1, -8040378802380461050L, 1, 0, null, null);
      RIMGlobalMessagePoster.postGlobalEvent(var1, -1438311245835636745L, 1, 0, null, null);
      DateTimeFormatOptions.onAppLocaleChange();
      RIMGlobalMessagePoster.postGlobalEvent(var1, 7207871974803693937L, 1, 0, null, null);
   }

   public static final void setDefaultInput(Locale var0) {
      if (InputContext.getInstance().selectInputMethod(var0)) {
         _defaultInputLocale = var0;
         int var1 = Application.getApplication().getProcessId();
         RIMGlobalMessagePoster.postGlobalEvent(var1, -8040378802380461050L, 1, 0, null, null);
      }
   }

   public static final void setDefaultForSystem(Locale var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final native void setDisplayLocale(int var1);

   private final native void setInputLocale(int var1);

   public static final int setDefaultInputForSystem(Locale var0) {
      int var1 = TraceBack.getCallingModule(0);
      return setDefaultInputForSystemImpl(var0, true, var1);
   }

   public static final int setDefaultInputForSystem(Locale var0, boolean var1) {
      int var2 = TraceBack.getCallingModule(0);
      return setDefaultInputForSystemImpl(var0, var1, var2);
   }

   private static final int setDefaultInputForSystemImpl(Locale var0, boolean var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String getCLDCLocaleString() {
      return _locales.current.buildToString(false).replace('_', '-');
   }

   public static final void setNameOrder(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final String toString() {
      return this._code == 0 ? EMPTY : this.buildToString(true);
   }

   private final String buildToString(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean verifyCode(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final boolean verifyCountry(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final boolean verifyLanguage(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final Locale verifySystemModulePresent(Locale var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final native int getDefaultLanguageLocale();
}
