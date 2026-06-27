package net.rim.tid.itie;

import net.rim.device.api.memorycleaner.MemoryCleanerListener;
import net.rim.device.api.memorycleaner.MemoryCleanerManager;
import net.rim.vm.WeakReference;

class SecureBufferRegistry implements MemoryCleanerListener {
   private WeakReference[] _buffer = new WeakReference[20];
   private int _firstNullCell = 0;
   private int _lastEmptyIndex = -1;
   private static final int DEFAULT_INC;
   private static final int MAX_RETRACE_COUNT;
   private static final int MAX_BUFFER_SIZE;
   private static final int GC_TRIGGER;

   SecureBufferRegistry() {
      MemoryCleanerManager.getInstance().addListener(this, true, false);
   }

   synchronized void registerBuffer(ISecureInputMethodBuffer var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public boolean cleanNow(int var1) {
      return var1 == 10 ? this.runSecureClean() : false;
   }

   @Override
   public String getDescription() {
      return null;
   }

   private synchronized boolean runSecureClean() {
      boolean var1 = false;

      for (int var2 = 0; var2 < this._firstNullCell; var2++) {
         Object var3 = this._buffer[var2].get();
         if (var3 != null) {
            var1 |= ((ISecureInputMethodBuffer)var3).runSecureClean();
         }
      }

      return var1;
   }
}
