package javax.microedition.global;

import java.util.Calendar;
import net.rim.device.api.util.Arrays;

public final class Formatter {
   private String _locale;
   public static final int DATE_LONG;
   public static final int DATE_SHORT;
   public static final int DATETIME_LONG;
   public static final int DATETIME_SHORT;
   public static final int TIME_LONG;
   public static final int TIME_SHORT;
   private static String EMPTY;
   private static String EN_LOCALE;

   public Formatter() {
   }

   public Formatter(String locale) {
      if (locale != null && !locale.equals(EMPTY)) {
         this._locale = GlobalUtilities.convertUnderscoreToHyphens(locale);
         if (!GlobalUtilities.isValidLocale(this._locale)) {
            throw new Object();
         }

         if (!this.isSupportedLocale(this._locale)) {
            throw new UnsupportedLocaleException();
         }
      } else {
         this._locale = null;
      }
   }

   public final String formatCurrency(double number) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String formatCurrency(double number, String currencyCode) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String formatDateTime(Calendar dateTime, int style) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String createISO8601Date(Calendar dateTime) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String createISO8601Time(Calendar dateTime) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String forceTwoDigits(int value) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String formatMessage(String template, String[] params) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public final String formatNumber(double number) {
      return this._locale == null ? Double.toString(number) : Double.toString(number);
   }

   public final String formatNumber(double number, int decimals) {
      if (decimals < 1 || decimals > 15) {
         throw new Object();
      } else {
         return this._locale == null
            ? this.correctDecimals(this.standardNotation(Double.toString(number)), decimals)
            : this.correctDecimals(this.standardNotation(Double.toString(number)), decimals);
      }
   }

   public final String formatNumber(long number) {
      return this._locale == null ? Long.toString(number) : Long.toString(number);
   }

   public final String formatPercentage(float value, int decimals) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String addZeroes(String originalValue, int num) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String standardNotation(String originalValue) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String correctDecimals(String originalValue, int decimals) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String formatPercentage(long value) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public final String getLocale() {
      return this._locale;
   }

   public static final String[] getSupportedLocales() {
      return new String[]{EN_LOCALE};
   }

   private final boolean isSupportedLocale(String locale) {
      return Arrays.contains(getSupportedLocales(), locale);
   }
}
