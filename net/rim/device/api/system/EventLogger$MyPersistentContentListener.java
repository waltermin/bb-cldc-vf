package net.rim.device.api.system;

class EventLogger$MyPersistentContentListener implements PersistentContentListener {
   private boolean _isEncryptionEnabled = false;

   private EventLogger$MyPersistentContentListener() {
   }

   boolean isEncryptionEnabled() {
      return this._isEncryptionEnabled;
   }

   @Override
   public void persistentContentModeChanged(int var1) {
      this._isEncryptionEnabled = PersistentContent.isEncryptionEnabled();
   }

   @Override
   public void persistentContentStateChanged(int var1) {
   }

   EventLogger$MyPersistentContentListener(EventLogger$1 var1) {
      this();
   }
}
