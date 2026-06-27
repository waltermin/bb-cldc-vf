package net.rim.device.api.i18n;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.api.util.Persistable;
import net.rim.device.internal.i18n.DateTimeFormatOptions;

public final class DateFormatSymbols implements Persistable {
   private int _localecode;
   private String[] _ampm;
   private String[] _ampm_short;
   private String[] _dateFormats;
   private String _datetimeFormat;
   private String[] _months;
   private String[] _months_short;
   private String[] _timeFormats;
   private String[] _weekdays;
   private String[] _weekdays_short;
   private char _undefinedSymbol;
   private static final long SYMBOLS;
   private static DateFormatSymbols _default;

   private DateFormatSymbols() {
   }

   private DateFormatSymbols(Locale var1) {
   }

   private final void init(ResourceBundle var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String[] getAmPmStrings() {
      return this._ampm;
   }

   public static final synchronized DateFormatSymbols getInstance() {
      settingChanged();
      return _default;
   }

   public static final synchronized DateFormatSymbols getInstance(Locale var0) {
      return getSymbols(var0.getCode());
   }

   public final String[] getMonths() {
      return this._months;
   }

   public final String getPattern(int var1) {
      int var2 = 4 * DateTimeFormatOptions.getDateFormat() + (var1 >> 3 & 3);
      var2 = MathUtilities.clamp(0, var2, this._dateFormats.length - 1);
      String var3 = this._dateFormats[var2];
      int var4 = 4 * DateTimeFormatOptions.getTimeFormat() + (var1 >> 0 & 3);
      var4 = MathUtilities.clamp(0, var4, this._timeFormats.length - 1);
      String var5 = this._timeFormats[var4];
      if ((var1 & 32) != 0 && (var1 & 4) != 0) {
         return MessageFormat.format(this._datetimeFormat, new String[]{var3, var5});
      } else if ((var1 & 32) != 0) {
         return var3;
      } else if ((var1 & 4) != 0) {
         return var5;
      } else {
         throw new Object();
      }
   }

   public final String[] getShortAmPmStrings() {
      return this._ampm_short;
   }

   public final String[] getShortMonths() {
      return this._months_short;
   }

   public final String[] getShortWeekdays() {
      return this._weekdays_short;
   }

   private static final DateFormatSymbols getSymbols(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final String[] getWeekdays() {
      return this._weekdays;
   }

   public final char getUndefinedSymbol() {
      return this._undefinedSymbol;
   }

   public final void setSymbols(
      Locale var1,
      String[] var2,
      String[] var3,
      String[] var4,
      String var5,
      String[] var6,
      String[] var7,
      String[] var8,
      String[] var9,
      String[] var10,
      char var11
   ) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void settingChanged() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      int var1 = Locale.getDefaultForSystem().getCode();
      DateFormatSymbols var2 = (DateFormatSymbols)var0.get(1125501472565950566L);
      if (var2 == null || var2._localecode != var1) {
         DateFormatSymbols var3 = getSymbols(var1);
         var2 = (DateFormatSymbols)var0.getOrWaitFor(1125501472565950566L);
         if (var2 == null || var2._localecode != var1) {
            var2 = var3;
            var0.replace(1125501472565950566L, var2);
         }
      }

      int var4 = Locale.getDefault().getCode();
      if (var4 != var1) {
         if (_default != null && _default._localecode == var4) {
            var2 = _default;
         } else {
            var2 = getSymbols(var4);
         }
      }

      _default = var2;
   }
}
