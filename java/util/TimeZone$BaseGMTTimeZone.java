package java.util;

final class TimeZone$BaseGMTTimeZone extends TimeZone {
   private static String GMT_DESC;

   private TimeZone$BaseGMTTimeZone() {
   }

   @Override
   public final int getOffset(int var1, int var2, int var3, int var4, int var5, int var6) {
      return 0;
   }

   public final int getOffset(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      return 0;
   }

   @Override
   public final int getRawOffset() {
      return 0;
   }

   @Override
   public final boolean useDaylightTime() {
      return false;
   }

   @Override
   public final String getID() {
      return TimeZone.GMT_STRING;
   }

   @Override
   public final String toString() {
      return GMT_DESC;
   }

   @Override
   public final boolean equals(Object var1) {
      return var1 instanceof TimeZone$BaseGMTTimeZone;
   }

   TimeZone$BaseGMTTimeZone(TimeZone$1 var1) {
      this();
   }
}
