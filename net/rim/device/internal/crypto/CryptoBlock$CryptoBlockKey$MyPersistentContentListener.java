package net.rim.device.internal.crypto;

import net.rim.device.api.system.PersistentContentListener;
import net.rim.device.internal.system.Security;

class CryptoBlock$CryptoBlockKey$MyPersistentContentListener implements PersistentContentListener {
   private CryptoBlock$CryptoBlockKey$MyPersistentContentListener() {
   }

   @Override
   public void persistentContentStateChanged(int var1) {
      if (var1 == 1
         && (CryptoBlock$CryptoBlockKey._deviceKey == null || CryptoBlock$CryptoBlockKey._deviceKey[0] == null)
         && !CryptoBlock$CryptoBlockKey.areMasterKeysAvailable()) {
         Security.getInstance().deviceUnderAttack();
      }
   }

   @Override
   public void persistentContentModeChanged(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   CryptoBlock$CryptoBlockKey$MyPersistentContentListener(CryptoBlock$1 var1) {
      this();
   }
}
