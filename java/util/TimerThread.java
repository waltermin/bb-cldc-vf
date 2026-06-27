package java.util;

class TimerThread extends Thread {
   boolean newTasksMayBeScheduled = true;
   private TaskQueue queue;
   private static final long THREAD_TIMEOUT;

   TimerThread(TaskQueue var1) {
      this.queue = var1;
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void mainLoop() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
