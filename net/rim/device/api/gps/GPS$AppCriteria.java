package net.rim.device.api.gps;

public class GPS$AppCriteria {
   int _prefRespTime;
   int _verticalAccuracy;
   int _horizontalAccuracy;
   boolean _costAllowed;
   int _powerUsage;

   public GPS$AppCriteria() {
   }

   public GPS$AppCriteria(int var1, int var2, int var3, boolean var4, int var5) {
      this._prefRespTime = var1;
      this._verticalAccuracy = var2;
      this._horizontalAccuracy = var3;
      this._costAllowed = var4;
      this._powerUsage = var5;
   }
}
