package net.rim.device.api.crypto;

import net.rim.device.api.memorycleaner.MemoryCleanerManager;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Persistable;

final class SoftwareHMACCryptoToken$HMACKeyData implements CryptoTokenMACKeyData, Persistable {
   private byte[] _data;
   private int _hashCode;

   public SoftwareHMACCryptoToken$HMACKeyData(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      this._data = RandomSource.getBytes(var1);
      MemoryCleanerManager.getInstance().setCryptoAPISecureOldObjects(true);
      PersistentContent.markAsPlaintext(this._data);
      this.setHashCode();
   }

   public SoftwareHMACCryptoToken$HMACKeyData(byte[] var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var3 >= 0 && var1.length - var3 >= var2) {
         this._data = Arrays.copy(var1, var2, var3);
         MemoryCleanerManager.getInstance().setCryptoAPISecureOldObjects(true);
         PersistentContent.markAsPlaintext(this._data);
         PersistentContent.markAsPlaintext(var1);
         this.setHashCode();
      } else {
         throw new Object();
      }
   }

   public final byte[] getData() {
      byte[] var1 = Arrays.copy(this._data);
      PersistentContent.markAsPlaintext(var1);
      return var1;
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
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
