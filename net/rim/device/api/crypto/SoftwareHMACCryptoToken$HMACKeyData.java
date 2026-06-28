package net.rim.device.api.crypto;

import net.rim.device.api.memorycleaner.MemoryCleanerManager;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Persistable;

final class SoftwareHMACCryptoToken$HMACKeyData implements CryptoTokenMACKeyData, Persistable {
   private byte[] _data;
   private int _hashCode;

   public SoftwareHMACCryptoToken$HMACKeyData(int length) {
      if (length < 0) {
         throw new Object();
      }

      this._data = RandomSource.getBytes(length);
      MemoryCleanerManager.getInstance().setCryptoAPISecureOldObjects(true);
      PersistentContent.markAsPlaintext(this._data);
      this.setHashCode();
   }

   public SoftwareHMACCryptoToken$HMACKeyData(byte[] data, int offset, int length) {
      if (data != null && offset >= 0 && length >= 0 && data.length - length >= offset) {
         this._data = Arrays.copy(data, offset, length);
         MemoryCleanerManager.getInstance().setCryptoAPISecureOldObjects(true);
         PersistentContent.markAsPlaintext(this._data);
         PersistentContent.markAsPlaintext(data);
         this.setHashCode();
      } else {
         throw new Object();
      }
   }

   public final byte[] getData() {
      byte[] data = Arrays.copy(this._data);
      PersistentContent.markAsPlaintext(data);
      return data;
   }

   public final int getLength() {
      return this._data.length;
   }

   private final void setHashCode() {
      this._hashCode = HashCodeCalculator.getCRC32(this._data);
      if (this._hashCode == 0) {
         this._hashCode = 1;
      }
   }

   @Override
   public final int hashCode() {
      return this._hashCode;
   }

   @Override
   public final boolean equals(Object obj) {
      throw new RuntimeException("cod2jar: type check");
   }
}
