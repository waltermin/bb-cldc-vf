package net.rim.device.internal.i18n;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.vm.PersistentInteger;

public class DateTimeFormatOptions {
   public static final int TIME_FORMAT_UNSET;
   public static final int TIME_FORMAT_12_HOUR;
   public static final int TIME_FORMAT_24_HOUR;
   private static final long TIME_FORMAT_GUID;
   private static int _timeFormatId;
   private static DateTimeFormatOptions$TimeFormatOptions _instance;
   private static int _timeFormatApplication;

   public static int getDateFormat() {
      return 0;
   }

   public static int getTimeFormat() {
      if (_instance.timeFormatUserSetting != -1) {
         return _instance.timeFormatUserSetting;
      } else {
         return Locale.getDefault() != Locale.getDefaultForSystem() ? _timeFormatApplication : _instance.timeFormatSystem;
      }
   }

   public static String[] getTimeFormats() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void onAppLocaleChange() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void onSystemLocaleChange() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void setTimeFormat(int timeFormat) {
      int curTimrFormat = getTimeFormat();
      if (timeFormat != curTimrFormat) {
         switch (timeFormat) {
            case -2:
            default:
               throw new Object();
            case -1:
            case 0:
            case 1:
               _instance.timeFormatUserSetting = timeFormat;
               PersistentInteger.set(_timeFormatId, timeFormat);
               onSystemLocaleChange();
               RIMGlobalMessagePoster.postGlobalEvent(7207871974803693937L);
         }
      }
   }
}
