package net.rim.device.cldc.util;

import java.util.TimeZone;

final class TimeZoneImpl extends TimeZone {
   private String ID;
   private int startMonth;
   private int startDay;
   private int startDayOfWeek;
   private int startTime;
   private int endMonth;
   private int endDay;
   private int endDayOfWeek;
   private int endTime;
   private int rawOffset;
   private boolean useDaylight;
   private int startMode;
   private int endMode;
   private int dstSavings;
   private int _c_year;
   private int _c_ruleDayStart;
   private int _c_ruleDayEnd;
   private int serialSyncID;
   static final short NO_MODE;
   static final short DOW_WIM_MODE;
   static final short DOM_MODE;
   static final short DOW_GE_DOM_MODE;
   static final short DOW_LE_DOM_MODE;
   private static final int MILLIS_IN_MINUTE;
   private static final int ONE_MINUTE;
   private static final int ONE_HOUR;
   private static final int ONE_DAY;
   private static final int millisPerHour;
   private static final int millisPerDay;
   private static final byte[] staticMonthLength;

   TimeZoneImpl(
      String var1,
      int var2,
      int var3,
      int var4,
      int var5,
      int var6,
      int var7,
      int var8,
      int var9,
      int var10,
      int var11,
      int var12,
      int var13,
      int var14,
      boolean var15
   ) {
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final String toString() {
      return this.ID;
   }

   @Override
   public final synchronized int getOffset(int var1, int var2, int var3, int var4, int var5, int var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final int compareToRule(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, boolean var11) {
      if (var1 < var7) {
         return -1;
      }

      if (var1 > var7) {
         return 1;
      }

      int var12;
      if (var11) {
         var12 = this._c_ruleDayStart;
      } else {
         var12 = this._c_ruleDayEnd;
      }

      if (var12 == -1) {
         switch (var6) {
            case 0:
               break;
            case 1:
               if (var9 > 0) {
                  var12 = 1 + (var9 - 1) * 7 + (7 + var8 - (var4 - var3 + 1)) % 7;
               } else {
                  var12 = var2 + (var9 + 1) * 7 - (7 + (var4 + var2 - var3) - var8) % 7;
               }
               break;
            case 2:
            default:
               var12 = var9;
               break;
            case 3:
               var12 = var9 + (49 + var8 - var9 - var4 + var3) % 7;
               break;
            case 4:
               var12 = var9 - (49 - var8 + var9 + var4 - var3) % 7;
         }

         if (var11) {
            this._c_ruleDayStart = var12;
         } else {
            this._c_ruleDayEnd = var12;
         }
      }

      if (var3 < var12) {
         return -1;
      } else if (var3 > var12) {
         return 1;
      } else if (var5 < var10) {
         return -1;
      } else {
         return var5 > var10 ? 1 : 0;
      }
   }

   @Override
   public final int getRawOffset() {
      return this.rawOffset;
   }

   @Override
   public final boolean useDaylightTime() {
      return this.useDaylight;
   }

   @Override
   public final String getID() {
      return this.ID;
   }

   public final int getSerialSyncID() {
      return this.serialSyncID;
   }
}
