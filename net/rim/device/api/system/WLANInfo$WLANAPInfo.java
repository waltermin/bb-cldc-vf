package net.rim.device.api.system;

public class WLANInfo$WLANAPInfo {
   private String _profileName;
   private String _ssid;
   private String _bssid;
   private int _radioBand;
   private int _dataRate;
   private int _signalLevel;

   WLANInfo$WLANAPInfo(String var1, String var2, String var3, int var4, int var5, int var6) {
      this._profileName = var1;
      this._ssid = var2;
      this._bssid = var3;
      this._radioBand = var4;
      this._dataRate = var5;
      this._signalLevel = var6;
   }

   public String getProfileName() {
      return this._profileName;
   }

   public String getSSID() {
      return this._ssid;
   }

   public String getBSSID() {
      return this._bssid;
   }

   public int getRadioBand() {
      return this._radioBand;
   }

   public int getDataRate() {
      return this._dataRate;
   }

   public int getSignalLevel() {
      return this._signalLevel;
   }
}
