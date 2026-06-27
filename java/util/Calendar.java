package java.util;

public class Calendar {
   protected int[] fields;
   protected boolean[] isSet;
   protected long time;
   private TimeZone _zone;
   public static final int YEAR;
   public static final int MONTH;
   public static final int DATE;
   public static final int DAY_OF_MONTH;
   public static final int DAY_OF_WEEK;
   public static final int AM_PM;
   public static final int HOUR;
   public static final int HOUR_OF_DAY;
   public static final int MINUTE;
   public static final int SECOND;
   public static final int MILLISECOND;
   public static final int SUNDAY;
   public static final int MONDAY;
   public static final int TUESDAY;
   public static final int WEDNESDAY;
   public static final int THURSDAY;
   public static final int FRIDAY;
   public static final int SATURDAY;
   public static final int JANUARY;
   public static final int FEBRUARY;
   public static final int MARCH;
   public static final int APRIL;
   public static final int MAY;
   public static final int JUNE;
   public static final int JULY;
   public static final int AUGUST;
   public static final int SEPTEMBER;
   public static final int OCTOBER;
   public static final int NOVEMBER;
   public static final int DECEMBER;
   public static final int AM;
   public static final int PM;
   private static final int FIELD_COUNT;

   protected Calendar() {
      this.setTimeZone(TimeZone.getDefault());
      this.setTimeInMillis(System.currentTimeMillis());
      if (!(this instanceof Object)) {
         this.fields = new int[15];
         this.isSet = new boolean[15];
      }
   }

   public boolean before(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public boolean after(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static Calendar getInstance() {
      return (Calendar)(new Object());
   }

   public static Calendar getInstance(TimeZone var0) {
      Object var1 = new Object();
      ((Calendar)var1).setTimeZone(var0);
      return (Calendar)var1;
   }

   public final Date getTime() {
      return new Date(this.getTimeInMillis());
   }

   protected long getTimeInMillis() {
      return this.time;
   }

   public final int get(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void set(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void setTime(Date var1) {
      this.setTimeInMillis(var1.getTime());
   }

   protected void setTimeInMillis(long var1) {
      this.time = var1;
   }

   public void setTimeZone(TimeZone var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public TimeZone getTimeZone() {
      return this._zone;
   }

   @Override
   public boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int hashCode() {
      return this._zone.hashCode();
   }

   protected void computeFields() {
      throw null;
   }

   protected void computeTime() {
      throw null;
   }
}
