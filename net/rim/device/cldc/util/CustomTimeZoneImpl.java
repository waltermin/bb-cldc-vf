package net.rim.device.cldc.util;

import java.util.TimeZone;

final class CustomTimeZoneImpl extends TimeZone {
   private char _sign;
   private int _hours;
   private int _minutes;
   private String _ID;
   private int _rawOffset;
   private static final int ONE_MINUTE;
   private static final int ONE_HOUR;
   private static final int millisPerDay;
   private static final byte[] staticMonthLength;

   CustomTimeZoneImpl(char sign, int hours, int minutes) {
   }

   @Override
   public final int getOffset(int era, int year, int month, int day, int dayOfWeek, int millis) {
      int monthLength;
      try {
         monthLength = staticMonthLength[month];
      } catch (ArrayIndexOutOfBoundsException e) {
         throw new IllegalArgumentException("Illegal Month");
      }

      if ((era == 0 || era == 1) && day >= 1 && day <= monthLength && dayOfWeek >= 1 && dayOfWeek <= 7 && millis >= 0 && millis < 86400000) {
         return this._rawOffset;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public final int getRawOffset() {
      return this._rawOffset;
   }

   @Override
   public final boolean useDaylightTime() {
      return false;
   }

   @Override
   public final String getID() {
      return this._ID;
   }

   @Override
   public final String toString() {
      return this._ID;
   }

   @Override
   public final boolean equals(Object o) {
      throw new RuntimeException("cod2jar: type check");
   }
}
