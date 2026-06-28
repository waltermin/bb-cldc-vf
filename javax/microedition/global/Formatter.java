package javax.microedition.global;

import java.util.Calendar;
import net.rim.device.api.i18n.DateFormat;
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
            throw new IllegalArgumentException();
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
      if (dateTime == null) {
         throw new NullPointerException();
      }

      if (this._locale == null) {
         switch (style) {
            case -1:
               throw new IllegalArgumentException();
            case 0:
            case 1:
            default:
               return this.createISO8601Date(dateTime);
            case 2:
            case 3:
               return this.createISO8601Time(dateTime);
            case 4:
            case 5:
               return this.createISO8601Date(dateTime) + 'T' + this.createISO8601Time(dateTime);
         }
      } else if (this._locale.equals(EN_LOCALE)) {
         int internalStyle;
         switch (style) {
            case -1:
               throw new IllegalArgumentException();
            case 0:
            default:
               internalStyle = 56;
               break;
            case 1:
               internalStyle = 40;
               break;
            case 2:
               internalStyle = 7;
               break;
            case 3:
               internalStyle = 5;
               break;
            case 4:
               internalStyle = 54;
               break;
            case 5:
               internalStyle = 54;
         }

         return DateFormat.getInstance(internalStyle).format(dateTime);
      } else {
         return EMPTY;
      }
   }

   private final String createISO8601Date(Calendar dateTime) {
      return Integer.toString(dateTime.get(1)) + '-' + this.forceTwoDigits(dateTime.get(2) + 1) + '-' + dateTime.get(5);
   }

   private final String createISO8601Time(Calendar dateTime) {
      return this.forceTwoDigits(dateTime.get(11))
         + ':'
         + this.forceTwoDigits(dateTime.get(12))
         + ':'
         + this.forceTwoDigits(dateTime.get(13))
         + '.'
         + dateTime.get(14)
         + 'Z';
   }

   private final String forceTwoDigits(int value) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String formatMessage(String template, String[] params) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String formatNumber(double number) {
      return this._locale == null ? Double.toString(number) : Double.toString(number);
   }

   public final String formatNumber(double number, int decimals) {
      if (decimals < 1 || decimals > 15) {
         throw new IllegalArgumentException();
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
      if (decimals < 1 || decimals > 15) {
         throw new IllegalArgumentException();
      } else {
         return this._locale == null
            ? this.correctDecimals(this.standardNotation(Float.toString(value * 1120403456)), decimals) + '%'
            : this.correctDecimals(this.standardNotation(Float.toString(value * 1120403456)), decimals) + '%';
      }
   }

   private final String addZeroes(String originalValue, int num) {
      String ret = originalValue;

      for (int i = 0; i < num; i++) {
         ret = ret + '0';
      }

      return ret;
   }

   private final String standardNotation(String originalValue) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final String correctDecimals(String originalValue, int decimals) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String formatPercentage(long value) {
      throw new RuntimeException("cod2jar: ldc");
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
