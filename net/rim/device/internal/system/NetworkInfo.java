package net.rim.device.internal.system;

public class NetworkInfo {
   private String _name;
   private int _networkId;
   private int _lac;
   private int _category;
   private int _accessTechnology;
   private String _countryCode;

   public NetworkInfo() {
   }

   public NetworkInfo(String var1, int var2) {
      this._name = var1;
      this._networkId = var2;
   }

   public NetworkInfo(int var1, int var2) {
      this._networkId = var1;
      this._category = var2;
   }

   public NetworkInfo(int var1, int var2, int var3) {
      this._networkId = var1;
      this._category = var2;
      this._accessTechnology = var3;
   }

   public String getName() {
      return this._name;
   }

   public void setName(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getNetworkId() {
      return this._networkId;
   }

   public void setNetworkId(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getLAC() {
      return this._lac;
   }

   public void setLAC(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getAccessTechnology() {
      return this._accessTechnology;
   }

   public int getCategory() {
      return this._category;
   }

   public void setCategory(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getCountryCode() {
      return this._countryCode;
   }

   public void setCountryCode(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getMcc() {
      return this._networkId & 65535;
   }

   public int getMnc() {
      return (this._networkId & -65536) >> 16;
   }

   public void setMcc(int var1) {
      this._networkId = (this._networkId & -65536) + (var1 & 65535);
   }

   public void setMnc(int var1) {
      this._networkId = (this._networkId & 65535) + ((var1 & 65535) << 16);
   }
}
