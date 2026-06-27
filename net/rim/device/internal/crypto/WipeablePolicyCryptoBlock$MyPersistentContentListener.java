package net.rim.device.internal.crypto;

import net.rim.device.api.system.PersistentContentListener;
import net.rim.device.internal.system.NvStore;
import net.rim.device.internal.system.Security;

class WipeablePolicyCryptoBlock$MyPersistentContentListener implements PersistentContentListener {
   private WipeablePolicyCryptoBlock$MyPersistentContentListener() {
   }

   @Override
   public void persistentContentStateChanged(int var1) {
      if (var1 == 1 && !WipeablePolicyCryptoBlock.isWLANKeyAvailable() && NvStore.readData(42) != null) {
         Security.getInstance().deviceUnderAttack();
      }
   }

   @Override
   public void persistentContentModeChanged(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   WipeablePolicyCryptoBlock$MyPersistentContentListener(WipeablePolicyCryptoBlock$1 var1) {
      this();
   }
}
