package net.rim.device.api.system;

import net.rim.device.internal.system.NvStore;

class PersistentContent$NvStorePersistentContentListener implements PersistentContentListener {
   private PersistentContent$NvStorePersistentContentListener() {
   }

   @Override
   public void persistentContentStateChanged(int var1) {
   }

   @Override
   public void persistentContentModeChanged(int var1) {
      NvStore.persistentContentModeChanged();
   }

   PersistentContent$NvStorePersistentContentListener(PersistentContent$1 var1) {
      this();
   }
}
