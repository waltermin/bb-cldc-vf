package net.rim.device.api.bluetooth;

public class BluetoothSerialPortInfo {
   private byte[] _address;
   private int _pageScanInfo;
   private String _name;
   private int _serverID;
   private String _serviceName;

   public BluetoothSerialPortInfo(byte[] var1, int var2, String var3) {
      this(var1, 0, null, var2, var3);
   }

   public BluetoothSerialPortInfo(byte[] var1, String var2, int var3, String var4) {
      this(var1, 0, var2, var3, var4);
   }

   public BluetoothSerialPortInfo(byte[] var1, int var2, String var3, int var4, String var5) {
      this._address = var1;
      this._pageScanInfo = var2;
      this._name = var3;
      this._serverID = var4;
      this._serviceName = var5;
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public byte[] getDeviceAddress() {
      return this._address;
   }

   public int getDevicePageScanInfo() {
      return this._pageScanInfo;
   }

   public String getDeviceName() {
      return this._name;
   }

   public int getServerID() {
      return this._serverID;
   }

   public String getServiceName() {
      return this._serviceName;
   }
}
