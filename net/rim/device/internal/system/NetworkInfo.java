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

   public NetworkInfo(String name, int networkId) {
      this._name = name;
      this._networkId = networkId;
   }

   public NetworkInfo(int networkId, int category) {
      this._networkId = networkId;
      this._category = category;
   }

   public NetworkInfo(int networkId, int category, int accessTechnology) {
      this._networkId = networkId;
      this._category = category;
      this._accessTechnology = accessTechnology;
   }

   public String getName() {
      return this._name;
   }

   public void setName(String name) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getNetworkId() {
      return this._networkId;
   }

   public void setNetworkId(int id) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getLAC() {
      return this._lac;
   }

   public void setLAC(int lac) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getAccessTechnology() {
      return this._accessTechnology;
   }

   public int getCategory() {
      return this._category;
   }

   public void setCategory(int category) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getCountryCode() {
      return this._countryCode;
   }

   public void setCountryCode(String countryCode) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getMcc() {
      return this._networkId & 65535;
   }

   public int getMnc() {
      return (this._networkId & -65536) >> 16;
   }

   public void setMcc(int mcc) {
      this._networkId = (this._networkId & -65536) + (mcc & 65535);
   }

   public void setMnc(int mnc) {
      this._networkId = (this._networkId & 65535) + ((mnc & 65535) << 16);
   }
}
