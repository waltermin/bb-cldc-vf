package net.rim.device.api.io;

import java.io.IOException;

public class UdpAddress extends DatagramAddressBase {
   protected int _ipAddress;
   protected int _destPort;
   protected int _srcPort;
   protected String _apn;
   protected String _apnUsername;
   protected String _apnPassword;
   protected int _type;
   public static final int TYPE_UDP;
   public static final int TYPE_GPAK;
   public static final int TYPE_GCMP;
   private static String USERNAME;
   private static String PASSWORD;

   public UdpAddress() {
      this.init(-1, -1, -1, null, -1);
   }

   public UdpAddress(byte[] ipAddress, int destPort, int srcPort, String apn, int type) {
      this.init(ipAddress != null ? DatagramAddressBase.readInt(ipAddress, 0) : -1, destPort, srcPort, apn, type);
   }

   public UdpAddress(byte[] ipAddress, int destPort, int srcPort, String apn, int apnOffset, int apnLength, int type) {
      this.init(
         ipAddress != null ? DatagramAddressBase.readInt(ipAddress, 0) : -1,
         destPort,
         srcPort,
         apn != null ? apn.substring(apnOffset, apnOffset + apnLength) : null,
         type
      );
   }

   public UdpAddress(int ipAddress, int destPort, int srcPort, String apn, int type) {
      this.init(ipAddress, destPort, srcPort, apn, type);
   }

   public UdpAddress(int ipAddress, int destPort, int srcPort, String apn, int apnOffset, int apnLength, int type) {
      this.init(ipAddress, destPort, srcPort, apn != null ? apn.substring(apnOffset, apnOffset + apnLength) : null, type);
   }

   public UdpAddress(DatagramAddressBase addressBase) {
   }

   public UdpAddress(String address) {
      this.setAddress(address);
   }

   private void init(int ipAddress, int destPort, int srcPort, String apn, int type) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public byte[] getIpAddress() {
      byte[] ipAddress = null;
      if (this._ipAddress != -1) {
         ipAddress = new byte[4];
         DatagramAddressBase.writeInt(ipAddress, 0, this._ipAddress);
      }

      return ipAddress;
   }

   public int getIpAddressInt() {
      return this._ipAddress;
   }

   public int getDestPort() {
      return this._destPort;
   }

   public int getSrcPort() {
      return this._srcPort;
   }

   public String getApn() {
      return this._apn;
   }

   public void setApn(String apn) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getApnUsername() {
      return this._apnUsername;
   }

   public void setApnUsername(String apnUsername) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getApnPassword() {
      return this._apnPassword;
   }

   public void setApnPassword(String apnPassword) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setSrcPort(int srcPort) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setDestPort(int destPort) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getApnOffset() {
      return 0;
   }

   public int getApnLength() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public int getType() {
      return this._type;
   }

   @Override
   public void setAddress(String address) {
      try {
         setAddress(this, address, false, true);
      } catch (IOException var3) {
      }
   }

   public static final String resolveAddress(String address) {
      return setAddress(null, address, true, false);
   }

   private static final String setAddress(UdpAddress udpAddress, String address, boolean resolve, boolean parse) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public String getAddress() {
      if (super._address == null) {
         byte[] ipAddress = new byte[4];
         DatagramAddressBase.writeInt(ipAddress, 0, this._ipAddress);
         super._address = makeAddress(false, ipAddress, this._destPort, this._srcPort, this._apn, this._type, this._apnUsername, this._apnPassword);
      }

      return super._address;
   }

   @Override
   public void swap() {
      int port = this._destPort;
      this._destPort = this._srcPort;
      this._srcPort = port;
      super._address = null;
   }

   @Override
   public boolean equals(Object addressBase) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 31 * hash + this._ipAddress;
      hash = 31 * hash + this._destPort;
      hash = 31 * hash + this._srcPort;
      if (this._apn != null) {
         hash = 31 * hash + this._apn.hashCode();
      }

      return 31 * hash + this._type;
   }

   public boolean compareApn(String apn, int apnOffset, int apnLength) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static String makeAddress(boolean open, byte[] ipAddress, int destPort, int srcPort, String apn, int type) {
      return makeAddress(open, ipAddress, destPort, srcPort, apn, type, null, null);
   }

   public static String makeAddress(boolean open, byte[] ipAddress, int destPort, int srcPort, String apn, int apnOffset, int apnLength, int type) {
      return makeAddress(open, ipAddress, destPort, srcPort, apn != null ? apn.substring(apnOffset, apnOffset + apnLength) : null, type, null, null);
   }

   public static String makeAddress(boolean open, byte[] ipAddress, int destPort, int srcPort, String apn, int type, String apnUsername, String apnPassword) {
      StringBuffer buf = (StringBuffer)(new Object(128));
      appendAddress(buf, open, ipAddress, destPort, srcPort, apn, type, apnUsername, apnPassword);
      return buf.toString();
   }

   public static void appendAddress(
      StringBuffer buf, boolean open, byte[] ipAddress, int destPort, int srcPort, String apn, int apnOffset, int apnLength, int type
   ) {
      appendAddress(buf, open, ipAddress, destPort, srcPort, apn != null ? apn.substring(apnOffset, apnOffset + apnLength) : null, type, null, null);
   }

   public static void appendAddress(StringBuffer buf, boolean open, byte[] ipAddress, int destPort, int srcPort, String apn, int type) {
      appendAddress(buf, open, ipAddress, destPort, srcPort, apn, type, null, null);
   }

   private static void appendAddress(
      StringBuffer buf, boolean open, byte[] ipAddress, int destPort, int srcPort, String apn, int type, String apnUsername, String apnPassword
   ) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static byte[] parseIpAddress(String address, int offset) {
      byte[] ipAddress = new byte[4];
      DatagramAddressBase.writeInt(ipAddress, 0, DatagramAddressBase.parseIpAddressInt(address, offset));
      return ipAddress;
   }

   public static String[] retrieveApnSettings(String address) {
      throw new RuntimeException("cod2jar: string-special");
   }

   protected static String parseApn(String address, int offset) {
      throw new RuntimeException("cod2jar: string-special");
   }

   protected static int parseApnCredentials(String address, int offset, String[] types) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static byte[] resolveAddress(String address, String apn, boolean randomize) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
