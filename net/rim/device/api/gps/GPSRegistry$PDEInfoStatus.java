package net.rim.device.api.gps;

final class GPSRegistry$PDEInfoStatus {
   GPS$GPSPDEInfo _pdeInfo;
   boolean _pdeStatus;
   boolean _credentialStatus;

   private GPSRegistry$PDEInfoStatus(GPS$GPSPDEInfo var1, boolean var2, boolean var3) {
      this._pdeInfo = var1;
      this._pdeStatus = var2;
      this._credentialStatus = var3;
   }

   public final void setCredStatus(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final boolean getCredStatus() {
      return this._credentialStatus;
   }

   public final boolean getPDEStatus() {
      return this._pdeStatus;
   }

   public final GPS$GPSPDEInfo getPDEInfo() {
      return this._pdeInfo;
   }

   GPSRegistry$PDEInfoStatus(GPS$GPSPDEInfo var1, boolean var2, boolean var3, GPSRegistry$1 var4) {
      this(var1, var2, var3);
   }
}
