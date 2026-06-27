package net.rim.device.api.i18n;

import net.rim.device.api.memorycleaner.MemoryCleanerDaemon;
import net.rim.device.api.memorycleaner.MemoryCleanerListener;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.util.IntHashtable;

final class ResourceBundleFamily$MyIntHashtable extends IntHashtable implements MemoryCleanerListener {
   private final ResourceBundleFamily this$0;

   ResourceBundleFamily$MyIntHashtable(ResourceBundleFamily var1, int var2) {
      super(var2);
      this.this$0 = var1;
      MemoryCleanerDaemon.addWeakListener(this, false);
   }

   @Override
   public final boolean cleanNow(int var1) {
      if (var1 == 6 && PersistentContent.isEncryptionEnabled()) {
         boolean var2 = this.this$0._cache.size() > 0;
         this.this$0._cache.clear();
         return var2;
      } else {
         return false;
      }
   }

   @Override
   public final String getDescription() {
      return null;
   }
}
