package net.rim.device.internal.util;

import net.rim.device.api.util.Persistable;

public final class OptionsRegistry$ByteArrayParameter implements Persistable {
   private byte[] _value;

   public final byte[] getValue() {
      return this._value;
   }

   final void setValue(byte[] value) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
