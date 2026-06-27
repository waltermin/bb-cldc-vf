package net.rim.device.internal.i18n;

public final class InternalCalendarUtilities {
   public static final int getFirstDayOfWeek() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final boolean is24Hour() {
      return DateTimeFormatOptions.getTimeFormat() == 1;
   }
}
