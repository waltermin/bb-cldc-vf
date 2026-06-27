package net.rim.device.api.util;

import java.util.Calendar;
import java.util.TimeZone;
import net.rim.device.cldc.util.CalendarExtensions;
import net.rim.vm.Array;

public final class DateTimeUtilities {
   public static final long GUID_DATE_CHANGED;
   public static final long GUID_TIMEZONE_CHANGED;
   public static final int ONESECOND;
   public static final int ONEMINUTE;
   public static final int ONEHOUR;
   public static final int ONEDAY;
   public static String GMT;
   private static final int[] DAYS_IN_MONTH;

   private DateTimeUtilities() {
   }

   public static final boolean isWeekend() {
      return isWeekend(System.currentTimeMillis());
   }

   public static final boolean isWeekend(long var0) {
      if (var0 < 0) {
         throw new Object();
      }

      Calendar var2 = Calendar.getInstance();
      ((CalendarExtensions)var2).setTimeLong(var0);
      return isWeekend(var2);
   }

   public static final boolean isWeekend(Calendar var0) {
      int var1 = var0.get(7);
      return var1 == 7 || var1 == 1;
   }

   public static final Calendar getNextDate(int var0) {
      if (var0 < 0) {
         throw new Object();
      }

      Calendar var1 = getDate(var0);
      long var2 = ((CalendarExtensions)var1).getTimeLong();
      if (var2 < System.currentTimeMillis()) {
         ((CalendarExtensions)var1).add(5, 1);
      }

      return var1;
   }

   public static final Calendar getDate(int var0) {
      if (var0 < 0) {
         throw new Object();
      }

      int var1 = var0 / 3600000;
      int var2 = (var0 - var1 * 3600000) / 60000;
      Calendar var3 = Calendar.getInstance();
      var3.set(11, var1);
      var3.set(12, var2);
      var3.set(13, 0);
      var3.set(14, 0);
      return var3;
   }

   public static final void formatElapsedTime(long var0, StringBuffer var2, boolean var3) {
      if (var0 < 0) {
         throw new Object();
      }

      if (var3) {
         var2.setLength(0);
      }

      int var4 = (int)(var0 % 60);
      var0 /= 60;
      int var5 = (int)(var0 % 60);
      var0 /= 60;
      int var6 = (int)(var0 % 24);
      var0 /= 24;
      int var7 = (int)var0;
      if (var7 != 0) {
         var2.append(var7);
         var2.append(':');
         if (var6 < 10) {
            var2.append('0');
         }
      }

      if (var7 != 0 || var6 != 0) {
         var2.append(var6);
         var2.append(':');
         if (var5 < 10) {
            var2.append('0');
         }
      }

      var2.append(var5);
      var2.append(':');
      if (var4 < 10) {
         var2.append('0');
      }

      var2.append(var4);
   }

   public static final int[] getCalendarFields(Calendar var0, int[] var1) {
      if (var1 == null) {
         var1 = new int[7];
      }

      if (var1.length < 7) {
         Array.resize(var1, 7);
      }

      var1[0] = var0.get(1);
      var1[1] = var0.get(2);
      var1[2] = var0.get(5);
      var1[3] = var0.get(11);
      var1[4] = var0.get(12);
      var1[5] = var0.get(13);
      var1[6] = var0.get(14);
      return var1;
   }

   public static final void setCalendarFields(Calendar var0, int[] var1) {
      if (var1 != null && var1.length >= 7) {
         var0.set(1, var1[0]);
         var0.set(2, var1[1]);
         var0.set(5, var1[2]);
         var0.set(11, var1[3]);
         var0.set(12, var1[4]);
         var0.set(13, var1[5]);
         var0.set(14, var1[6]);
      }
   }

   public static final boolean isSameDate(long var0, long var2, TimeZone var4, TimeZone var5) {
      return isSameDate(var0, var2, var4, var5, null);
   }

   public static final boolean isSameDate(long var0, long var2) {
      TimeZone var4 = TimeZone.getDefault();
      return isSameDate(var0, var2, var4, var4, null);
   }

   public static final boolean isSameDate(long var0, long var2, TimeZone var4, TimeZone var5, Calendar var6) {
      boolean var7 = var4 == null || var5 == null;
      if (var4 != null && var5 != null) {
         var7 = var4 == var5 || var4.getID().equals(var5.getID());
      }

      if (var7) {
         long var8 = var0 - var2;
         if (var8 < 0) {
            var8 *= -1;
         }

         if (var8 > 90000000) {
            return false;
         }
      }

      if (var4 == null) {
         var4 = TimeZone.getDefault();
      }

      Calendar var12 = var6;
      if (var12 == null) {
         var12 = Calendar.getInstance();
      }

      var12.setTimeZone(var4);
      ((CalendarExtensions)var12).setTimeLong(var0);
      int var9 = var12.get(5);
      int var10 = var12.get(2);
      int var11 = var12.get(1);
      if (var5 == null) {
         var5 = var4;
      }

      var12.setTimeZone(var5);
      ((CalendarExtensions)var12).setTimeLong(var2);
      return var12.get(5) == var9 && var12.get(2) == var10 && var12.get(1) == var11;
   }

   public static final long convertEpochToMilliseconds(int var0) {
      return ((long)var0 - 36816480) * 60000;
   }

   public static final int convertMillisecondsToEpoch(long var0) {
      return (int)(var0 / 60000 + 36816480);
   }

   public static final long copyCalendar(Calendar var0, Calendar var1) {
      var1.set(1, var0.get(1));
      var1.set(2, var0.get(2));
      var1.set(5, var0.get(5));
      var1.set(11, var0.get(11));
      var1.set(12, var0.get(12));
      var1.set(13, var0.get(13));
      var1.set(14, var0.get(14));
      return ((CalendarExtensions)var1).getTimeLong();
   }

   public static final void zeroCalendarTime(Calendar var0) {
      var0.set(11, 0);
      var0.set(12, 0);
      var0.set(13, 0);
      var0.set(14, 0);
   }

   public static final int getWeekOfYear(Calendar var0, long var1, int var3) {
      ((CalendarExtensions)var0).setTimeLong(var1);
      int var4 = var0.get(6);
      int var5 = var0.get(1);
      boolean var6 = var5 % 4 == 0 && var5 % 100 != 0 || var5 % 400 == 0;
      boolean var7 = (var5 - 1) % 4 == 0 && (var5 - 1) % 100 != 0 || (var5 - 1) % 400 == 0;
      int var8 = (var5 - 1) % 100;
      int var9 = var5 - 1 - var8;
      int var10 = var8 + var8 / 4;
      int var11 = 1 + (var9 / 100 % 4 * 5 + var10) % 7;
      int var12 = var4 + (var11 - 1);
      int var13 = 1 + (var12 - 1) % 7;
      int var14 = var5;
      int var15 = 0;
      if (var4 <= 8 - var11 && var11 > 4) {
         var14 = var5 - 1;
         if (var11 != 5 && (var11 != 6 || !var7)) {
            var15 = 52;
         } else {
            var15 = 53;
         }
      }

      if (var14 == var5) {
         short var16 = 365;
         if (var6) {
            var16 = 366;
         }

         if (var16 - var4 < 4 - var13) {
            var14 = var5 + 1;
            var15 = 1;
         }
      }

      if (var14 == var5) {
         int var17 = var4 + (7 - var13) + (var11 - 1);
         var15 = var17 / 7;
         if (var11 > 4) {
            var15--;
         }
      }

      return var15;
   }

   public static final int getNumberOfDaysInMonth(int var0, int var1) {
      int var2 = -1;
      boolean var3 = var1 % 4 == 0 && var1 % 100 != 0 || var1 % 400 == 0;
      if (var0 == 1) {
         return var3 ? 29 : 28;
      }

      if (var0 >= 0 && var0 < DAYS_IN_MONTH.length) {
         var2 = DAYS_IN_MONTH[var0];
      }

      return var2;
   }

   public static final boolean verifyStartOfDay(Calendar var0, CalendarExtensions var1) {
      if (var0.get(11) == 23) {
         var1.add(10, 1);
         return true;
      } else if (var0.get(11) == 1) {
         zeroCalendarTime(var0);
         return true;
      } else {
         return false;
      }
   }

   public static final boolean calendarAddWithDst(Calendar var0, CalendarExtensions var1, int var2, int var3) {
      boolean var4 = false;
      switch (var2) {
         case 1:
         case 3:
         case 4:
            var1.add(var2, var3);
            break;
         case 2:
         case 5:
         case 6:
         case 7:
         default:
            int var5 = var0.get(11);
            var1.add(var2, var3);
            if (var5 == 0 && var0.get(11) == 23 && var3 > 0) {
               var1.add(10, 1);
               var4 = true;
            }
      }

      return var4;
   }

   public static final boolean calendarSetWithDst(Calendar var0, CalendarExtensions var1, int var2, int var3) {
      boolean var4 = false;
      switch (var2) {
         case 2:
         case 5:
         case 6:
         case 7:
            int var6 = var0.get(11);
            var0.set(var2, var3);
            if (var6 == 0 && var0.get(11) == 23) {
               var1.add(11, 1);
               var4 = true;
            }
            break;
         case 11:
            var0.set(var2, var3);
            int var5 = var0.get(11);
            if (var3 % 24 == 0 && var5 == 23) {
               var1.add(11, 1);
               var4 = true;
            }
            break;
         default:
            var0.set(var2, var3);
      }

      return var4;
   }
}
