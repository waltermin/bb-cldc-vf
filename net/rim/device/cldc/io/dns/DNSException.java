package net.rim.device.cldc.io.dns;

import java.io.IOException;

public final class DNSException extends IOException {
   private int _errorCode;

   public DNSException() {
   }

   public DNSException(String var1) {
   }

   public DNSException(String var1, int var2) {
      super(var1);
      this._errorCode = var2;
   }

   public DNSException(int var1) {
   }

   public final int getErrorCode() {
      return this._errorCode;
   }
}
