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

   public Formatter(String var1) {
      if (var1 != null && !var1.equals(EMPTY)) {
         this._locale = GlobalUtilities.convertUnderscoreToHyphens(var1);
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

   public final String formatCurrency(double var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String formatCurrency(double var1, String var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String formatDateTime(Calendar var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String createISO8601Date(Calendar var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String createISO8601Time(Calendar var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String forceTwoDigits(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String formatMessage(String var0, String[] var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public final String formatNumber(double var1) {
      return this._locale == null ? Double.toString(var1) : Double.toString(var1);
   }

   public final String formatNumber(double var1, int var3) {
      if (var3 < 1 || var3 > 15) {
         throw new Object();
      } else {
         return this._locale == null
            ? this.correctDecimals(this.standardNotation(Double.toString(var1)), var3)
            : this.correctDecimals(this.standardNotation(Double.toString(var1)), var3);
      }
   }

   public final String formatNumber(long var1) {
      return this._locale == null ? Long.toString(var1) : Long.toString(var1);
   }

   public final String formatPercentage(float var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String addZeroes(String var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String standardNotation(String var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String correctDecimals(String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String formatPercentage(long var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public final String getLocale() {
      return this._locale;
   }

   public static final String[] getSupportedLocales() {
      return new String[]{EN_LOCALE};
   }

   private final boolean isSupportedLocale(String var1) {
      return Arrays.contains(getSupportedLocales(), var1);
   }
}
