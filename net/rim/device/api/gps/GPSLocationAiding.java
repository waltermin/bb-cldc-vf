package net.rim.device.api.gps;

public class GPSLocationAiding {
   int _status;
   int _latitude;
   int _longitude;
   int _altitude;
   int _horUncertainty;
   int _altUncertainty;

   public void setStatus(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setLatitude(float var1) {
      int var2 = (int)var1 * 1000000;
      if (var2 >= 0) {
         this._latitude = var2;
      } else {
         var2 = 0 - var2;
         this._latitude = -2147483648 | var2;
      }
   }

   public void setLongitude(float var1) {
      int var2 = (int)var1 * 1000000;
      if (var2 >= 0) {
         this._longitude = var2;
      } else {
         var2 = 0 - var2;
         this._longitude = -2147483648 | var2;
      }
   }

   public void setAltitude(int var1) {
      int var2 = var1 & 32767;
      if (var1 >= 0) {
         this._altitude = 32768 | var2;
      } else {
         this._altitude = var2;
      }
   }

   public void setHorUncertainty(float var1) {
      this._horUncertainty = (int)(var1 * 1120403456);
   }

   public void setAltUncertainty(float var1) {
      this._altUncertainty = (int)(var1 * 1120403456);
   }
}
