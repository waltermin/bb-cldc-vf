package com.sun.cldc.i18n;

import net.rim.device.api.system.PersistentContent;

class Helper$LocalStrongReferences {
   private Object _universalReaderReference;
   private Object _lastReaderReference;
   private Object _lastWriterReference;
   private static Helper$ReferenceCleaner _referenceCleaner;

   public Helper$LocalStrongReferences() {
      _referenceCleaner.addLocalStrongReferences(this);
   }

   public void storeUniversalReaderStrongReference(Object var1) {
      if (_referenceCleaner.keepStrongReferences()) {
         this._universalReaderReference = var1;
      } else {
         PersistentContent.markAsPlaintext(var1);
      }
   }

   public void storeLastReaderStrongReference(Object var1) {
      if (_referenceCleaner.keepStrongReferences()) {
         this._lastReaderReference = var1;
      } else {
         PersistentContent.markAsPlaintext(var1);
      }
   }

   public void storeLastWriterStrongReference(Object var1) {
      if (_referenceCleaner.keepStrongReferences()) {
         this._lastWriterReference = var1;
      } else {
         PersistentContent.markAsPlaintext(var1);
      }
   }

   public void clearStrongReferences() {
      this._universalReaderReference = null;
      this._lastReaderReference = null;
      this._lastWriterReference = null;
   }

   @Override
   public int hashCode() {
      int var1 = 0;
      if (this._universalReaderReference != null) {
         var1 ^= this._universalReaderReference.hashCode();
      }

      if (this._lastReaderReference != null) {
         var1 ^= this._lastReaderReference.hashCode();
      }

      if (this._lastWriterReference != null) {
         var1 ^= this._lastWriterReference.hashCode();
      }

      return var1;
   }
}
