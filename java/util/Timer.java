package java.util;

public class Timer {
   private TaskQueue queue = new TaskQueue();
   private TimerThread thread = new TimerThread(this.queue);

   public Timer() {
      this.thread.start();
   }

   public void schedule(TimerTask task, long delay) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void schedule(TimerTask task, Date time) {
      this.sched(task, time.getTime(), 0);
   }

   public void schedule(TimerTask task, long delay, long period) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void schedule(TimerTask task, Date firstTime, long period) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void sched(TimerTask task, long time, long period) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void cancel() {
      synchronized (this.queue) {
         this.thread.newTasksMayBeScheduled = false;
         this.queue.clear();
         this.queue.notify();
      }
   }
}
