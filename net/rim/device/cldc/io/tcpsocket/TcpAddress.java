package net.rim.device.cldc.io.tcpsocket;

import java.io.IOException;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.system.RadioException;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.internal.io.tunnel.TunnelCredentialsProvider;
import net.rim.device.internal.system.TCPPacketHeader;

final class TcpAddress extends DatagramAddressBase {
   protected int _ipAddress;
   protected int _port;
   protected int _localPort;
   protected String _apnName;
   protected String _apnUsername;
   protected String _apnPassword;
   protected boolean _isListenAddress;
   public static int TCP_PORT_NONE;
   public static int TCP_IP_ADDRESS_NONE;
   protected static String SLASH_SLASH;
   protected static String COMPARISON_STRING;

   public TcpAddress() {
   }

   public TcpAddress(byte[] ipAddress, int destPort) {
      this(ipAddress, destPort, TCP_PORT_NONE, null);
   }

   public TcpAddress(byte[] ipAddress, int destPort, int srcPort) {
      this(ipAddress, destPort, srcPort, null);
   }

   public TcpAddress(byte[] ipAddress, int destPort, String apn) {
      this(ipAddress, destPort, TCP_PORT_NONE, apn, 0, apn != null ? apn.length() : 0);
   }

   public TcpAddress(byte[] ipAddress, int destPort, int srcPort, String apn) {
      this(ipAddress, destPort, srcPort, apn, 0, apn != null ? apn.length() : 0);
   }

   public TcpAddress(byte[] ipAddress, int destPort, int srcPort, String apn, int apnOffset, int apnLength) {
   }

   public TcpAddress(String address) {
      this(address, null);
   }

   public TcpAddress(String address, String apn) {
      if (address.length() == 0) {
         this._ipAddress = TCP_IP_ADDRESS_NONE;
         this._port = TCP_PORT_NONE;
         this._localPort = TCP_PORT_NONE;
         this._apnName = null;
         super._address = "";
      } else {
         this.processApnInfo(apn);
         this.parseAddress(address);
      }
   }

   private final void processApnInfo(String apn) {
      if (apn != null) {
         this._apnName = apn;
      } else {
         this._apnName = TunnelCredentialsProvider.getInstance().getApn();
         this._apnUsername = TunnelCredentialsProvider.getInstance().getApnUsername();
         this._apnPassword = TunnelCredentialsProvider.getInstance().getApnPassword();
      }
   }

   public final int getConnectionIpAddress() {
      return this.getIpAddress();
   }

   public final int getConnectionLocalPort() {
      return this.getLocalPort();
   }

   public final void setConnectionLocalPort(int port) {
      this.setLocalPort(port);
   }

   public final int getConnectionDestinationPort() {
      return this.getDestPort();
   }

   public final String getConnectionApn() {
      return this.getApnName();
   }

   protected final void parseAddress(String address) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void setLocalPort(int port) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final String getConnectionAddress() {
      return makeAddress(true, this._ipAddress != 0 ? TCPPacketHeader.IPv4IntToByteArray(this._ipAddress) : null, this._port, this._localPort);
   }

   public final int getIpAddress() {
      return this._ipAddress;
   }

   @Override
   public final boolean equals(Object dgramAddress) {
      if (dgramAddress == this) {
         return true;
      }

      if (!(dgramAddress instanceof TcpAddress)) {
         return false;
      }

      TcpAddress address = (TcpAddress)dgramAddress;
      return (this._ipAddress == TCP_IP_ADDRESS_NONE || address._ipAddress == TCP_IP_ADDRESS_NONE || this._ipAddress == address._ipAddress)
         && (this._port == TCP_PORT_NONE || address._port == TCP_PORT_NONE || this._port == address._port)
         && (this._localPort == TCP_PORT_NONE || address._localPort == TCP_PORT_NONE || this._localPort == address._localPort)
         && (this._apnName == null || this.compareApn(address._apnName));
   }

   @Override
   public final int hashCode() {
      int hash = 7;
      hash = 31 * hash + this._ipAddress;
      hash = 31 * hash + this._port;
      hash = 31 * hash + this._localPort;
      if (this._apnName != null) {
         hash = 31 * hash + this._apnName.hashCode();
      }

      return hash;
   }

   public final String getApnName() {
      return this._apnName;
   }

   public final void setApnName(String apn) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getApnUsername() {
      return this._apnUsername;
   }

   public final void setApnUsername(String apnUsername) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getApnPassword() {
      return this._apnPassword;
   }

   public final void setApnPassword(String apnPassword) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getLocalPort() {
      return this._localPort;
   }

   public final int getDestPort() {
      return this._port;
   }

   public final boolean isListenAddress() {
      return this._isListenAddress;
   }

   public final boolean compareApn(String apn) {
      return this._apnName.equalsIgnoreCase(apn);
   }

   public static final String makeAddress(boolean open, byte[] ipAddress, int destPort, int srcPort) {
      return makeAddress(open, ipAddress, destPort, srcPort, null, 0, 0);
   }

   public static final String makeAddress(boolean open, byte[] ipAddress, int destPort, int srcPort, String apn) {
      return makeAddress(open, ipAddress, destPort, srcPort, apn, 0, apn != null ? apn.length() : 0);
   }

   public static final String makeAddress(boolean open, byte[] ipAddress, int destPort, int srcPort, String apn, int apnOffset, int apnLength) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public static final void convertIpAddressBytesToStringBuffer(byte[] ipAddress, StringBuffer buf) {
      if (ipAddress == null) {
         buf.append("255.255.255.255");
      } else {
         buf.append(ipAddress[0] & 255);
         buf.append('.');
         buf.append(ipAddress[1] & 255);
         buf.append('.');
         buf.append(ipAddress[2] & 255);
         buf.append('.');
         buf.append(ipAddress[3] & 255);
      }
   }

   public final String getLocalAddress() {
      if (this._apnName == null) {
         throw new IOException();
      }

      StringBuffer buf = new StringBuffer(20);

      try {
         convertIpAddressBytesToStringBuffer(RadioInfo.getIPAddress(RadioInfo.getAccessPointNumber(this._apnName)), buf);
      } catch (RadioException e) {
         throw new IOException("Can't get local ip address");
      }

      return buf.toString();
   }
}
