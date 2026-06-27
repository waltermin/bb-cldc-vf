package net.rim.device.api.ui.component;

import net.rim.vm.Array;

class ThreadPool {
   private Runnable[] _list;
   private int _onePastLast;
   private int _first;
   private long _lastTimeUsed = System.currentTimeMillis();
   private int _threadCount;
   private static final long MAX_IDLE_TIME;
   private static final int LIST_INCR;
   private static ThreadPool _pool;

   private ThreadPool() {
      this._list = new Runnable[8];
      this._first = 0;
      this._onePastLast = 0;

      for (int var1 = 0; var1 < 2; var1++) {
         new ThreadPool$ThreadPoolRunner(this).start();
         this._threadCount++;
      }
   }

   public static void post(Runnable var0) {
      ThreadPool var1 = _pool;
      if (var1 == null || var1._threadCount <= 0) {
         var1 = new ThreadPool();
         _pool = var1;
      }

      var1.put(var0);
   }

   private synchronized void put(Runnable var1) {
      if (this._first == 0 && this._onePastLast == this._list.length - 1 || this._first == this._onePastLast + 1) {
         int var2 = this._list.length;
         Array.resize(this._list, var2 + 8);
         if (this._first > this._onePastLast) {
            System.arraycopy(this._list, this._first, this._list, this._first + 8, var2 - this._first);
            int var3 = this._first;
            this._first += 8;

            for (int var4 = var3; var4 < this._first; var4++) {
               this._list[var4] = null;
            }
         }
      }

      this._list[this._onePastLast] = var1;
      this._onePastLast++;
      if (this._onePastLast >= this._list.length) {
         this._onePastLast = 0;
      }

      this._lastTimeUsed = System.currentTimeMillis();
      super.notify();
   }

   private synchronized Runnable get() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static int access$110(ThreadPool var0) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   static ThreadPool access$202(ThreadPool var0) {
      _pool = var0;
      return var0;
   }
}
