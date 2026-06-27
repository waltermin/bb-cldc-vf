package java.util;

import net.rim.device.cldc.util.TimeService;

public class TimeZone {
   private static String GMT_STRING;
   private static TimeZone$BaseGMTTimeZone _gmtZone;

   public int getOffset(int var1, int var2, int var3, int var4, int var5, int var6) {
      throw null;
   }

   public int getRawOffset() {
      throw null;
   }

   public boolean useDaylightTime() {
      throw null;
   }

   public String getID() {
      return null;
   }

   public static TimeZone getTimeZone(String var0) {
      if (var0.equals(GMT_STRING)) {
         return _gmtZone;
      }

      TimeZone var1 = null;
      TimeService var2 = TimeService.getTimeService();
      if (var2 != null) {
         var1 = var2.getTimeZone(var0);
      }

      if (var1 == null) {
         var1 = _gmtZone;
      }

      return var1;
   }

   public static TimeZone getDefault() {
      TimeService var0 = TimeService.getTimeService();
      if (var0 == null) {
         return _gmtZone;
      }

      TimeZone var1 = var0.getTimeZone(var0.getDefaultTimeZoneID());
      return var1 == null ? _gmtZone : var1;
   }

   public static String[] getAvailableIDs() {
      TimeService var0 = TimeService.getTimeService();
      return var0 == null ? new String[]{GMT_STRING} : var0.getTimeZoneIDs();
   }
}
