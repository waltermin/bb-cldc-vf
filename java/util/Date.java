package java.util;

public class Date {
   private long millis;

   public Date() {
      this(System.currentTimeMillis());
   }

   public Date(long var1) {
      this.millis = var1;
   }

   public long getTime() {
      return this.millis;
   }

   public void setTime(long var1) {
      this.millis = var1;
   }

   @Override
   public boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int hashCode() {
      long var1 = this.getTime();
      return (int)var1 ^ (int)(var1 >> 32);
   }

   @Override
   public String toString() {
      Calendar var1 = Calendar.getInstance();
      var1.setTime(this);
      return net.rim.device.cldc.util.GregorianCalendar.toString(var1);
   }
}
