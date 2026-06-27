package java.util;

public class Timer {
   private TaskQueue queue = new TaskQueue();
   private TimerThread thread = new TimerThread(this.queue);

   public Timer() {
      this.thread.start();
   }

   public void schedule(TimerTask var1, long var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void schedule(TimerTask var1, Date var2) {
      this.sched(var1, var2.getTime(), 0);
   }

   public void schedule(TimerTask var1, long var2, long var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void schedule(TimerTask var1, Date var2, long var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void scheduleAtFixedRate(TimerTask var1, long var2, long var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void scheduleAtFixedRate(TimerTask var1, Date var2, long var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void sched(TimerTask var1, long var2, long var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void cancel() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
