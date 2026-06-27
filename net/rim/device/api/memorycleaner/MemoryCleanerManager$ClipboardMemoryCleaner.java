package net.rim.device.api.memorycleaner;

import net.rim.device.api.system.Clipboard;
import net.rim.device.internal.i18n.CommonResource;

final class MemoryCleanerManager$ClipboardMemoryCleaner implements MemoryCleanerListener {
   @Override
   public final boolean cleanNow(int var1) {
      if (var1 != 7) {
         Clipboard var2 = Clipboard.getClipboard();
         if (var2.get() != null) {
            var2.put(null);
            return true;
         }
      }

      return false;
   }

   @Override
   public final String getDescription() {
      return CommonResource.getString(10093);
   }
}
