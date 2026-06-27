package net.rim.device.internal.system;

final class LEDEventProcessor$EventHolder {
   private long _sourceId;
   private long _expirationDate;

   public final void fill(long var1, long var3) {
      this._sourceId = var1;
      this._expirationDate = var3;
   }

   public final long getExpirationDate() {
      return this._expirationDate;
   }

   public final long getSourceId() {
      return this._sourceId;
   }
}
