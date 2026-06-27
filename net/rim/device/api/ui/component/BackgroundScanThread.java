package net.rim.device.api.ui.component;

import net.rim.vm.Array;

class BackgroundScanThread extends Thread {
   private Runnable[] _list = new Runnable[8];
   private int _headIndex;
   private int _tailIndex;
   private static final long MAX_IDLE_TIME;
   private static BackgroundScanThread _scanThread;

   private BackgroundScanThread() {
   }

   public static void post(Runnable var0) {
      BackgroundScanThread var1 = _scanThread;
      if (var1 == null) {
         var1 = new BackgroundScanThread();
         var1.start();
         _scanThread = var1;
      }

      var1.put(var0);
   }

   private synchronized void expandBuffer(int var1) {
      int var2 = this._list.length;
      Array.resize(this._list, var2 + var1);
      if (this._headIndex < this._tailIndex) {
         System.arraycopy(this._list, this._tailIndex, this._list, this._tailIndex + var1, var2 - this._tailIndex);
         this._tailIndex += var1;
      }
   }

   private synchronized void put(Runnable var1) {
      if (this._tailIndex - this._headIndex == 1 || this._tailIndex == 0 && this._headIndex == this._list.length - 1) {
         this.expandBuffer(8);
      }

      this._list[this._headIndex] = var1;
      this._headIndex++;
      if (this._headIndex >= this._list.length) {
         this._headIndex = 0;
      }

      super.notify();
   }

   private synchronized Runnable get() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void run() {
      while (true) {
         Runnable var1 = this.get();
         if (var1 == null) {
            _scanThread = null;
            return;
         }

         var1.run();
         Object var2 = null;
      }
   }
}
