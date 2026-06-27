package java.lang;

public class Throwable {
   private String detailMessage;
   private Object backtrace;

   public Throwable() {
   }

   public Throwable(String var1) {
      this.detailMessage = var1;
   }

   public String getMessage() {
      return this.detailMessage;
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void printStackTrace() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
