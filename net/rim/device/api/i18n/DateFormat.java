package net.rim.device.api.i18n;

import java.util.Calendar;
import net.rim.device.cldc.util.CalendarExtensions;

public class DateFormat extends Format {
   public static final long GUID_DATE_FORMAT_CHANGED;
   private static Calendar _cal;
   private static CalendarExtensions _calEx;
   static final int DATE;
   static final int TIME;
   public static final int DATE_FULL;
   public static final int DATE_LONG;
   public static final int DATE_MEDIUM;
   public static final int DATE_SHORT;
   public static final int DATE_DEFAULT;
   public static final int TIME_FULL;
   public static final int TIME_LONG;
   public static final int TIME_MEDIUM;
   public static final int TIME_SHORT;
   public static final int TIME_DEFAULT;
   public static final int DATETIME_DEFAULT;
   public static final int MILLISECOND_FIELD;
   public static final int SECOND_FIELD;
   public static final int MINUTE_FIELD;
   public static final int HOUR_FIELD;
   public static final int HOUR_OF_DAY_FIELD;
   public static final int AM_PM_FIELD;
   public static final int DATE_FIELD;
   public static final int MONTH_FIELD;
   public static final int YEAR_FIELD;
   public static final int ERA_FIELD;
   public static final int DAY_OF_WEEK_FIELD;
   public static final int TIMEZONE_FIELD;

   protected DateFormat() {
   }

   public StringBuffer format(Calendar var1, StringBuffer var2, FieldPosition var3) {
      throw null;
   }

   @Override
   public StringBuffer format(Object var1, StringBuffer var2, FieldPosition var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final DateFormat getInstance(int var0) {
      return new SimpleDateFormat(var0);
   }

   public static long alignToMidnight(long var0) {
      return alignToHourMinute(var0, 23, 59);
   }

   public static long alignToHourMinute(long var0, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final StringBuffer formatLocal(StringBuffer var1, long var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final String formatLocal(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
