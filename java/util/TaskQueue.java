package java.util;

class TaskQueue {
   private TimerTask[] queue = new TimerTask[4];
   private int size;

   void add(TimerTask task) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   TimerTask getMin() {
      return this.queue[1];
   }

   void removeMin() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   void rescheduleMin(long newTime) {
      this.queue[1].nextExecutionTime = newTime;
      this.fixDown(1);
   }

   boolean isEmpty() {
      return this.size == 0;
   }

   void clear() {
      for (int i = 1; i <= this.size; i++) {
         this.queue[i] = null;
      }

      this.size = 0;
   }

   private void fixUp(int k) {
      while (k > 1) {
         int j = k >> 1;
         if (this.queue[j].nextExecutionTime <= this.queue[k].nextExecutionTime) {
            return;
         }

         TimerTask tmp = this.queue[j];
         this.queue[j] = this.queue[k];
         this.queue[k] = tmp;
         k = j;
      }
   }

   private void fixDown(int k) {
      int j;
      while ((j = k << 1) <= this.size) {
         if (j < this.size && this.queue[j].nextExecutionTime > this.queue[j + 1].nextExecutionTime) {
            j++;
         }

         if (this.queue[k].nextExecutionTime <= this.queue[j].nextExecutionTime) {
            return;
         }

         TimerTask tmp = this.queue[j];
         this.queue[j] = this.queue[k];
         this.queue[k] = tmp;
         k = j;
      }
   }
}
