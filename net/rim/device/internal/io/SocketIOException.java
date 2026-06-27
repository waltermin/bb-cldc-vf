package net.rim.device.internal.io;

import java.io.IOException;

public final class SocketIOException extends IOException {
   private int _exceptionCode;
   public static final int SOC_ERROR_BAD_SID;

   public SocketIOException(int var1) {
      this._exceptionCode = var1;
   }

   @Override
   public final String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
