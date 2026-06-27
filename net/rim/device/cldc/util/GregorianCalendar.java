package net.rim.device.cldc.util;

import java.util.Calendar;
import java.util.TimeZone;

public final class GregorianCalendar extends Calendar implements CalendarExtensions {
   private int _eraVal;
   private int _eraStamp;
   private int _yearVal;
   private int _yearStamp;
   private int _monthVal;
   private int _monthStamp;
   private int _dateVal;
   private int _dateStamp;
   private int _doyVal;
   private int _doyStamp;
   private int _dowVal;
   private int _dowStamp;
   private int _ampmVal;
   private int _ampmStamp;
   private int _hourVal;
   private int _hourStamp;
   private int _hourOfDayVal;
   private int _hourOfDayStamp;
   private int _minVal;
   private int _minStamp;
   private int _secVal;
   private int _secStamp;
   private int _milliVal;
   private int _milliStamp;
   private boolean _isTimeSet;
   private boolean _areFieldsSet;
   private int _zoneOffset;
   private int _dstOffset;
   private int _nextStamp = 2;
   private static final int ACTUAL_MINIMUM_MASK;
   private static final int UNSET;
   private static final int INTERNALLY_SET;
   private static final int MINIMUM_USER_STAMP;
   private static final int GREGORIAN_CUTOVER_YEAR;
   private static final int[] MONTH_LENGTH;
   private static final int[] LEAP_MONTH_LENGTH;
   private static final int ONE_SECOND;
   private static final int ONE_MINUTE;
   private static final int ONE_HOUR;
   private static final long ONE_DAY;
   private static final long ONE_WEEK;
   private static final int[] MIN_VALUES;
   private static final int[] MAX_VALUES;

   public final int internalGet(int var1, boolean var2) {
      if (var2) {
         this.complete();
      }

      switch (var1) {
         case -1:
         case 3:
         case 4:
         case 8:
         case 15:
            if (var2) {
               throw new Object(var1);
            }

            return 0;
         case 0:
         default:
            return this._eraVal;
         case 1:
            return this._yearVal;
         case 2:
            return this._monthVal;
         case 5:
            return this._dateVal;
         case 6:
            return this._doyVal;
         case 7:
            return this._dowVal;
         case 9:
            return this._ampmVal;
         case 10:
            return this._hourVal;
         case 11:
            return this._hourOfDayVal;
         case 12:
            return this._minVal;
         case 13:
            return this._secVal;
         case 14:
            return this._milliVal;
         case 16:
            return this._dstOffset;
      }
   }

   public final void internalSet(int var1, int var2) {
      this._isTimeSet = false;
      this._areFieldsSet = false;
      int var3 = this._nextStamp;
      switch (var1) {
         case -1:
         case 3:
         case 4:
         case 8:
            throw new Object(var1);
         case 0:
         default:
            this._eraVal = var2;
            this._eraStamp = var3;
            break;
         case 1:
            this._yearVal = var2;
            this._yearStamp = var3;
            break;
         case 2:
            this._monthVal = var2;
            this._monthStamp = var3;
            break;
         case 5:
            this._dateVal = var2;
            this._dateStamp = var3;
            break;
         case 6:
            this._doyVal = var2;
            this._doyStamp = var3;
            break;
         case 7:
            this._dowVal = var2;
            this._dowStamp = var3;
            break;
         case 9:
            this._ampmVal = var2;
            this._ampmStamp = var3;
            break;
         case 10:
            this._hourVal = var2;
            this._hourStamp = var3;
            break;
         case 11:
            this._hourOfDayVal = var2;
            this._hourOfDayStamp = var3;
            break;
         case 12:
            this._minVal = var2;
            this._minStamp = var3;
            break;
         case 13:
            this._secVal = var2;
            this._secStamp = var3;
            break;
         case 14:
            this._milliVal = var2;
            this._milliStamp = var3;
      }

      this._nextStamp = var3 + 1;
   }

   @Override
   public final int getActualMaximum(int var1) {
      short var2 = 0;
      switch (var1) {
         case 1:
         default:
            return 9999;
         case 2:
            return 11;
         case 5:
            if (this.isLeapYear(this._yearVal)) {
               return LEAP_MONTH_LENGTH[this._monthVal];
            }

            return MONTH_LENGTH[this._monthVal];
         case 7:
            return 6;
         case 9:
            return 1;
         case 10:
            return 11;
         case 11:
            return 23;
         case 12:
         case 13:
            return 59;
         case 14:
            var2 = 1000;
         case 0:
         case 3:
         case 4:
         case 6:
         case 8:
            return var2;
      }
   }

   @Override
   public final void add(int var1, int var2) {
      if (var2 != 0) {
         this.complete();
         if (var1 == 1) {
            int var11 = this._yearVal;
            if (this._eraVal == 1) {
               var11 += var2;
               if (var11 > 0) {
                  this.set(1, var11);
               } else {
                  this.set(1, 1 - var11);
                  this.set(0, 0);
               }
            } else {
               var11 -= var2;
               if (var11 > 0) {
                  this.set(1, var11);
               } else {
                  this.set(1, 1 - var11);
                  this.set(0, 1);
               }
            }

            this.pinDayOfMonth();
         } else if (var1 == 2) {
            int var9 = this._monthVal + var2;
            if (var9 >= 0) {
               this.set(1, this._yearVal + var9 / 12);
               this.set(2, var9 % 12);
            } else {
               this.set(1, this._yearVal + (var9 + 1) / 12 - 1);
               var9 %= 12;
               if (var9 < 0) {
                  var9 += 12;
               }

               this.set(2, 0 + var9);
            }

            this.pinDayOfMonth();
         } else if (var1 == 0) {
            int var8 = this._eraVal + var2;
            if (var8 < 0) {
               var8 = 0;
            }

            if (var8 > 1) {
               var8 = 1;
            }

            this.set(0, var8);
         } else {
            long var3 = var2;
            boolean var5 = true;
            switch (var1) {
               case 4:
               case 8:
                  throw new Object();
               case 5:
               case 6:
               case 7:
                  var3 *= 86400000;
                  break;
               case 9:
               default:
                  var3 *= 43200000;
                  break;
               case 10:
               case 11:
                  var3 *= 3600000;
                  var5 = false;
                  break;
               case 12:
                  var3 *= 60000;
                  var5 = false;
                  break;
               case 13:
                  var3 *= 1000;
                  var5 = false;
                  break;
               case 14:
                  var5 = false;
            }

            long var6 = 0;
            if (var5) {
               var6 = this._dstOffset;
            }

            this.setTimeInMillis(super.time + var3);
            if (var5) {
               var6 -= this._dstOffset;
               if (var6 != 0) {
                  this.setTimeInMillis(super.time + var6);
               }
            }
         }
      }
   }

   @Override
   public final void roll(int var1, int var2) {
      if (var2 != 0) {
         int var3 = 0;
         int var4 = 0;
         if (var1 >= 0) {
            this.complete();
            var3 = this.getMinimum(var1);
            var4 = this.getMaximum(var1);
         }

         switch (var1) {
            case -1:
            case 3:
            case 4:
            case 8:
               throw new Object();
            case 2:
               int var16 = (this._monthVal + var2) % 12;
               if (var16 < 0) {
                  var16 += 12;
               }

               this.set(2, var16);
               int var7 = this.monthLength(var16);
               int var19 = this._dateVal;
               if (var19 > var7) {
                  this.set(5, var7);
               }

               return;
            case 5:
               var4 = this.monthLength(this._monthVal);
            case 0:
            case 1:
            case 9:
            case 12:
            case 13:
            case 14:
               int var5 = var4 - var3 + 1;
               int var13 = this.internalGet(var1, false) + var2;
               var13 = (var13 - var3) % var5;
               if (var13 < 0) {
                  var13 += var5;
               }

               var13 += var3;
               this.set(var1, var13);
               return;
            case 6:
               long var12 = (long)var2 * 86400000;
               long var18 = super.time - (long)(this._doyVal - 1) * 86400000;
               int var10 = this.yearLength();
               super.time = (super.time + var12 - var18) % ((long)var10 * 86400000);
               if (super.time < 0) {
                  super.time += (long)var10 * 86400000;
               }

               this.setTimeInMillis(super.time + var18);
               return;
            case 7:
               long var11 = (long)var2 * 86400000;
               int var17 = this._dowVal - 1;
               if (var17 < 0) {
                  var17 += 7;
               }

               long var20 = super.time - (long)var17 * 86400000;
               super.time = (super.time + var11 - var20) % 604800000;
               if (super.time < 0) {
                  super.time += 604800000;
               }

               this.setTimeInMillis(super.time + var20);
               return;
            case 10:
            case 11:
            default:
               long var6 = this.getTimeInMillis();
               int var8 = this.internalGet(var1, false);
               int var9 = (var8 + var2) % (var4 + 1);
               if (var9 < 0) {
                  var9 += var4 + 1;
               }

               this.setTimeInMillis(var6 + 3600000 * (var9 - var8));
         }
      }
   }

   @Override
   public final int getActualMinimum(int var1) {
      return 226 >> var1 & 1;
   }

   @Override
   public final void setTimeLong(long var1) {
      this.setTimeInMillis(var1);
   }

   @Override
   public final long getTimeLong() {
      return this.getTimeInMillis();
   }

   @Override
   protected final void computeFields() {
      TimeZone var1 = this.getTimeZone();
      int var2 = var1.getRawOffset();
      long var3 = super.time + var2;
      if (super.time > 0 && var3 < 0 && var2 > 0) {
         var3 = Long.MAX_VALUE;
      } else if (super.time < 0 && var3 > 0 && var2 < 0) {
         var3 = Long.MIN_VALUE;
      }

      int var5 = this.timeToDateFields(var3);
      int var6 = var1.getOffset(this.internalGetEra(), this._yearVal, this._monthVal, this._dateVal, this._dowVal, var5) - var2;
      this._dstOffset = var6;
      this._zoneOffset = var2;
      var5 += var6;
      if (var5 >= 86400000) {
         long var7 = var3 + var6;
         if (var3 > 0 && var7 < 0 && var6 > 0) {
            var7 = Long.MAX_VALUE;
         } else if (var3 < 0 && var7 > 0 && var6 < 0) {
            var7 = Long.MIN_VALUE;
         }

         var5 = this.timeToDateFields(var7);
      }

      this.timeToRemainingFields(var5);
   }

   @Override
   protected final void computeTime() {
      TimeZone var2 = this.getTimeZone();
      int var3 = var2.getRawOffset();
      int var1 = this.fieldsToLocalTime();
      int var4 = 0;
      var4 = var2.getOffset(this._eraVal, this._yearVal, this._monthVal, this._dateVal, this._dowVal, var1) - var3;
      this._dstOffset = var4;
      this._zoneOffset = var3;
      super.time = super.time - var3 - var4;
   }

   @Override
   protected final long getTimeInMillis() {
      if (!this._isTimeSet) {
         this.updateTime();
      }

      return super.time;
   }

   @Override
   protected final void setTimeInMillis(long var1) {
      this._isTimeSet = true;
      super.time = var1;
      this.computeFields();
      this._areFieldsSet = true;
   }

   @Override
   public final void setTimeZone(TimeZone var1) {
      if (this.getTimeZone() != var1) {
         super.setTimeZone(var1);
         this._areFieldsSet = false;
      }
   }

   private final void updateTime() {
      this.computeTime();
      this._areFieldsSet = false;
      this._isTimeSet = true;
   }

   private final boolean isSet(int var1) {
      switch (var1) {
         case -1:
         case 3:
         case 4:
         case 8:
            throw new Object();
         case 0:
         default:
            if (this._eraStamp != 0) {
               return true;
            }

            return false;
         case 1:
            if (this._yearStamp != 0) {
               return true;
            }

            return false;
         case 2:
            if (this._monthStamp != 0) {
               return true;
            }

            return false;
         case 5:
            if (this._dateStamp != 0) {
               return true;
            }

            return false;
         case 6:
            if (this._doyStamp != 0) {
               return true;
            }

            return false;
         case 7:
            if (this._dowStamp != 0) {
               return true;
            }

            return false;
         case 9:
            if (this._ampmStamp != 0) {
               return true;
            }

            return false;
         case 10:
            if (this._hourStamp != 0) {
               return true;
            }

            return false;
         case 11:
            if (this._hourOfDayStamp != 0) {
               return true;
            }

            return false;
         case 12:
            if (this._minStamp != 0) {
               return true;
            }

            return false;
         case 13:
            if (this._secStamp != 0) {
               return true;
            }

            return false;
         case 14:
            return this._milliStamp != 0;
      }
   }

   private final void complete() {
      if (!this._isTimeSet) {
         this.updateTime();
      }

      if (!this._areFieldsSet) {
         this.computeFields();
         this._areFieldsSet = true;
      }
   }

   private final boolean isLeapYear(int var1) {
      return var1 < 1582 ? var1 % 4 == 0 : var1 % 4 == 0 && (var1 % 100 != 0 || var1 % 400 == 0);
   }

   private final int monthLength(int var1, int var2) {
      return this.isLeapYear(var2) ? LEAP_MONTH_LENGTH[var1] : MONTH_LENGTH[var1];
   }

   private final int monthLength(int var1) {
      int var2 = this._yearVal;
      if (this.internalGetEra() == 0) {
         var2 = 1 - var2;
      }

      return this.monthLength(var1, var2);
   }

   private final int yearLength() {
      return this.isLeapYear(this._yearVal) ? 366 : 365;
   }

   private final void pinDayOfMonth() {
      int var1 = this.monthLength(this._monthVal);
      int var2 = this._dateVal;
      if (var2 > var1) {
         this.set(5, var1);
      }
   }

   private final int internalGetEra() {
      return this.isSet(0) ? this._eraVal : 1;
   }

   private final int getMinimum(int var1) {
      return MIN_VALUES[var1];
   }

   private final int getMaximum(int var1) {
      return MAX_VALUES[var1];
   }

   private final native int fieldsToLocalTime();

   private final native int timeToDateFields(long var1);

   private final native void timeToRemainingFields(int var1);

   public static final String toString(Calendar var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final StringBuffer appendFourDigits(StringBuffer var0, int var1) {
      if (var1 >= 0 && var1 < 1000) {
         var0.append('0');
         if (var1 < 100) {
            var0.append('0');
         }

         if (var1 < 10) {
            var0.append('0');
         }
      }

      return var0.append(var1);
   }

   private static final StringBuffer appendTwoDigits(StringBuffer var0, int var1) {
      if (var1 < 10) {
         var0.append('0');
      }

      return var0.append(var1);
   }
}
