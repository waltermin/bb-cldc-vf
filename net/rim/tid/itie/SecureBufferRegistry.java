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

   synchronized void registerBuffer(ISecureInputMethodBuffer buffer) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public boolean cleanNow(int event) {
      return event == 10 ? this.runSecureClean() : false;
   }

   @Override
   public String getDescription() {
      return null;
   }

   private synchronized boolean runSecureClean() {
      boolean result = false;

      for (int i = 0; i < this._firstNullCell; i++) {
         ISecureInputMethodBuffer buffer = (ISecureInputMethodBuffer)this._buffer[i].get();
         if (buffer != null) {
            result |= buffer.runSecureClean();
         }
      }

      return result;
   }
}
