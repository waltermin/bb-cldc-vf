package net.rim.device.api.gps;

public class GPS$GPSPDEInfo {
   private int _ip;
   private int _port;
   private GPS$AppCredential _credential;

   public GPS$GPSPDEInfo() {
   }

   public GPS$GPSPDEInfo(int var1, int var2, GPS$AppCredential var3) {
      this._ip = var1;
      this._port = var2;
      this._credential = var3;
   }

   public int getIP() {
      return this._ip;
   }

   public int getPort() {
      return this._port;
   }

   public GPS$AppCredential getCredential() {
      return this._credential;
   }
}
