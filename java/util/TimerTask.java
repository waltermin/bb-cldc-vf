package java.util;

public class TimerTask implements Runnable {
   final Object lock = new Object();
   int state = 0;
   long nextExecutionTime;
   long period = 0;
   static final int VIRGIN;
   static final int SCHEDULED;
   static final int EXECUTED;
   static final int CANCELLED;

   public long scheduledExecutionTime() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean cancel() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void run() {
      throw null;
   }

   protected TimerTask() {
   }
}
