package java.util;

class TaskQueue {
   private TimerTask[] queue = new TimerTask[4];
   private int size;

   void add(TimerTask var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   TimerTask getMin() {
      return this.queue[1];
   }

   void removeMin() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   void rescheduleMin(long var1) {
      this.queue[1].nextExecutionTime = var1;
      this.fixDown(1);
   }

   boolean isEmpty() {
      return this.size == 0;
   }

   void clear() {
      for (int var1 = 1; var1 <= this.size; var1++) {
         this.queue[var1] = null;
      }

      this.size = 0;
   }

   private void fixUp(int var1) {
      while (var1 > 1) {
         int var2 = var1 >> 1;
         if (this.queue[var2].nextExecutionTime <= this.queue[var1].nextExecutionTime) {
            return;
         }

         TimerTask var3 = this.queue[var2];
         this.queue[var2] = this.queue[var1];
         this.queue[var1] = var3;
         var1 = var2;
      }
   }

   private void fixDown(int var1) {
      int var2;
      while ((var2 = var1 << 1) <= this.size) {
         if (var2 < this.size && this.queue[var2].nextExecutionTime > this.queue[var2 + 1].nextExecutionTime) {
            var2++;
         }

         if (this.queue[var1].nextExecutionTime <= this.queue[var2].nextExecutionTime) {
            return;
         }

         TimerTask var3 = this.queue[var2];
         this.queue[var2] = this.queue[var1];
         this.queue[var1] = var3;
         var1 = var2;
      }
   }
}
