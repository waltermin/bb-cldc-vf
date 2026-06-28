package net.rim.device.internal.util;

import net.rim.device.api.crypto.HashCodeCalculator;
import net.rim.device.api.util.Persistable;

public final class ByteArray implements Persistable {
   private byte[] _array;

   public ByteArray(byte[] array) {
      this._array = array;
   }

   public final byte[] getArray() {
      return this._array;
   }

   @Override
   public final int hashCode() {
      return HashCodeCalculator.getCRC32(this._array);
   }

   @Override
   public final boolean equals(Object obj) {
      throw new RuntimeException("cod2jar: type check");
   }
}
