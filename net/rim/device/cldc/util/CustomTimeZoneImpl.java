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

   CustomTimeZoneImpl(char var1, int var2, int var3) {
   }

   @Override
   public final int getOffset(int var1, int var2, int var3, int var4, int var5, int var6) {
      throw new RuntimeException("cod2jar: exception table");
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
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
