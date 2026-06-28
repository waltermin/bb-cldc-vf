package java.util;

class TimerThread extends Thread {
   boolean newTasksMayBeScheduled = true;
   private TaskQueue queue;
   private static final long THREAD_TIMEOUT;

   TimerThread(TaskQueue queue) {
      this.queue = queue;
   }

   @Override
   public void run() {
      try {
         this.mainLoop();
      } catch (Throwable t) {
         synchronized (this.queue) {
            this.newTasksMayBeScheduled = false;
            this.queue.clear();
         }
      }
   }

   private void mainLoop() {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }
}
