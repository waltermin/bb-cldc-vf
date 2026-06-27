package net.rim.device.internal.util;

import net.rim.device.api.crypto.HashCodeCalculator;
import net.rim.device.api.util.Persistable;

public final class ByteArray implements Persistable {
   private byte[] _array;

   public ByteArray(byte[] var1) {
      this._array = var1;
   }

   public final byte[] getArray() {
      return this._array;
   }

   @Override
   public final int hashCode() {
      return HashCodeCalculator.getCRC32(this._array);
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
