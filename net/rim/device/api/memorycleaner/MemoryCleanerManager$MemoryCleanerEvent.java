package net.rim.device.api.memorycleaner;

import net.rim.device.api.listener.Event;
import net.rim.device.api.system.EventLogger;
import net.rim.device.internal.compress.CompressUtilities;
import net.rim.device.internal.system.BitmapCache;
import net.rim.device.internal.system.Security;
import net.rim.vm.Memory;

class MemoryCleanerManager$MemoryCleanerEvent implements Event {
   boolean _somethingCleaned;
   int _event;

   MemoryCleanerManager$MemoryCleanerEvent(int var1) {
      this._event = var1;
   }

   @Override
   public Thread preUpdateEventListener() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Thread updateEventListener(Object var1) {
      MemoryCleanerListener var2 = (MemoryCleanerListener)var1;
      this._somethingCleaned = this._somethingCleaned | var2.cleanNow(this._event);
      return null;
   }

   @Override
   public Thread postUpdateEventListener() {
      this._somethingCleaned = this._somethingCleaned | Security.getInstance().cleanNow(this._event);
      if ((this._somethingCleaned || Memory.anyPlaintext()) && this._event != 2 && this._event != 3 && this._event != 5) {
         int var1 = this._event < MemoryCleanerManager.EVENT_LOGGER_CODES.length
            ? MemoryCleanerManager.EVENT_LOGGER_CODES[this._event]
            : 808464432 + (this._event / 10 << 8) + this._event % 10;
         EventLogger.logEvent(-3818033069674138067L, var1);
         if (!Memory.anyPersistentPlaintext()) {
            Memory.fullGC();
         } else {
            Memory.persistentGC();
         }

         BitmapCache.flush();
         CompressUtilities.zeroBuffers();
         Memory.scrubUnusedMemory();
      }

      return null;
   }
}
