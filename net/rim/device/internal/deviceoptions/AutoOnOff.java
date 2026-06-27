package net.rim.device.internal.deviceoptions;

import java.util.Calendar;
import java.util.Date;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.util.DateTimeUtilities;
import net.rim.device.cldc.util.CalendarExtensions;

public final class AutoOnOff {
   public static final long GUID_AUTOONOFF_OPTIONS_CHANGED;
   public static final int IMMEDIATE_AUTO_OFF;
   public static final int AUTO_OFF_WHEN_IDLE;
   public static final int ABORT_AUTO_OFF;
   private static final long AUTOONOFF_DATA_KEY;
   private static PersistentObject _persistentAutoOnOffData;
   private static AutoOnOff$AutoOnOffData _autoOnOffData;

   private AutoOnOff() {
   }

   public static final void init() {
   }

   private static final void commit(boolean var0) {
      _persistentAutoOnOffData.commit();
      if (var0) {
         RIMGlobalMessagePoster.postGlobalEvent(-2918606221006897090L);
      }

      setApplicationManagerPowerOnBehaviour();
   }

   private static final void setApplicationManagerPowerOnBehaviour() {
      ApplicationManager var0 = ApplicationManager.getApplicationManager();
      if (!isWeekendAutoOnOffEnabled() && !isWeekdayAutoOnOffEnabled()) {
         var0.setCurrentPowerOnBehavior(1);
      } else {
         var0.setCurrentPowerOnBehavior(2);
      }
   }

   public static final int getWeekdayOnTime() {
      return _autoOnOffData._weekdayOn;
   }

   public static final void setWeekdayOnTime(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getWeekdayOffTime() {
      return _autoOnOffData._weekdayOff;
   }

   public static final void setWeekdayOffTime(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isWeekdayAutoOnOffEnabled() {
      return _autoOnOffData._weekdayAutoOnOffEnabled;
   }

   public static final void enableWeekdayAutoOnOff(boolean var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getWeekendOnTime() {
      return _autoOnOffData._weekendOn;
   }

   public static final void setWeekendOnTime(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getWeekendOffTime() {
      return _autoOnOffData._weekendOff;
   }

   public static final void setWeekendOffTime(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isWeekendAutoOnOffEnabled() {
      return _autoOnOffData._weekendAutoOnOffEnabled;
   }

   public static final void enableWeekendAutoOnOff(boolean var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void resetToDefaults() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long getNextAutoOnTime() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long getNextAutoOffTime(boolean var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final long constructAutoOffTime(long var0, int var2) {
      Calendar var3 = Calendar.getInstance();
      var3.setTime((Date)(new Object(var0)));
      int var4 = var2 / 3600000;
      int var5 = (var2 - var4 * 3600000) / 60000;
      var3.set(11, var4);
      var3.set(12, var5);
      var3.set(13, 0);
      var3.set(14, 0);
      long var6 = ((CalendarExtensions)var3).getTimeLong();
      if (var6 < var0) {
         var6 += 86400000;
      }

      return var6 < System.currentTimeMillis() ? Long.MIN_VALUE : var6;
   }

   public static final int determineAutoOffBehavior() {
      byte var0 = 2;
      Calendar var1 = Calendar.getInstance();
      int var2 = var1.get(11) * 3600000 + var1.get(12) * 60000 + var1.get(13) * 1000 + var1.get(14);
      if (DateTimeUtilities.isWeekend(var1) && _autoOnOffData._weekendAutoOnOffEnabled) {
         if (!insideTimeFrame(_autoOnOffData._weekendOn, _autoOnOffData._weekendOff, var2, var1.get(7) == 7)) {
            var0 = 1;
            if (turnOffImmediately(_autoOnOffData._weekendOff, var2)) {
               var0 = 0;
            }
         }

         if (var1.get(7) == 7
            && var0 != 0
            && _autoOnOffData._weekdayAutoOnOffEnabled
            && _autoOnOffData._weekdayOn > _autoOnOffData._weekdayOff
            && var0 == 1
            && var2 < _autoOnOffData._weekendOn) {
            if (insideTimeFrame(0, _autoOnOffData._weekdayOff, var2)) {
               return 2;
            }

            if (turnOffImmediately(_autoOnOffData._weekdayOff, var2)) {
               return 0;
            }
         }
      } else if (!DateTimeUtilities.isWeekend(var1) && _autoOnOffData._weekdayAutoOnOffEnabled) {
         if (!insideTimeFrame(_autoOnOffData._weekdayOn, _autoOnOffData._weekdayOff, var2, var1.get(7) == 2)) {
            var0 = 1;
            if (turnOffImmediately(_autoOnOffData._weekdayOff, var2)) {
               var0 = 0;
            }
         }

         if (var1.get(7) == 2
            && var0 != 0
            && _autoOnOffData._weekendAutoOnOffEnabled
            && _autoOnOffData._weekendOn > _autoOnOffData._weekendOff
            && var0 == 1
            && var2 < _autoOnOffData._weekdayOn) {
            if (insideTimeFrame(0, _autoOnOffData._weekendOff, var2)) {
               return 2;
            }

            if (turnOffImmediately(_autoOnOffData._weekendOff, var2)) {
               var0 = 0;
            }
         }
      }

      return var0;
   }

   private static final boolean insideTimeFrame(int var0, int var1, int var2) {
      return insideTimeFrame(var0, var1, var2, false);
   }

   private static final boolean insideTimeFrame(int var0, int var1, int var2, boolean var3) {
      boolean var4;
      if (var1 < var0) {
         var4 = !insideTimeFrame(var1, var0, var2);
         if (var3) {
            var4 = var4 && var2 >= var0;
         }
      } else {
         var4 = var2 >= var0 - 60000 && var2 <= var1 - 60000;
      }

      return var4;
   }

   private static final boolean turnOffImmediately(int var0, int var1) {
      int var2 = var1 - var0;
      return var2 >= 0 && var2 <= 60000;
   }
}
