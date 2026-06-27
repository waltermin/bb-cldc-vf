package net.rim.device.cldc.io.tcpsocket;

import net.rim.device.api.io.DatagramAddressBase;
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

   public TcpAddress(byte[] var1, int var2) {
      this(var1, var2, TCP_PORT_NONE, null);
   }

   public TcpAddress(byte[] var1, int var2, int var3) {
      this(var1, var2, var3, null);
   }

   public TcpAddress(byte[] var1, int var2, String var3) {
   }

   public TcpAddress(byte[] var1, int var2, int var3, String var4) {
   }

   public TcpAddress(byte[] var1, int var2, int var3, String var4, int var5, int var6) {
   }

   public TcpAddress(String var1) {
      this(var1, null);
   }

   public TcpAddress(String var1, String var2) {
   }

   private final void processApnInfo(String var1) {
      if (var1 != null) {
         this._apnName = var1;
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

   public final void setConnectionLocalPort(int var1) {
      this.setLocalPort(var1);
   }

   public final int getConnectionDestinationPort() {
      return this.getDestPort();
   }

   public final String getConnectionApn() {
      return this.getApnName();
   }

   protected final void parseAddress(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void setLocalPort(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final String getConnectionAddress() {
      return makeAddress(true, this._ipAddress != 0 ? TCPPacketHeader.IPv4IntToByteArray(this._ipAddress) : null, this._port, this._localPort);
   }

   public final int getIpAddress() {
      return this._ipAddress;
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final int hashCode() {
      int var1 = 7;
      var1 = 31 * var1 + this._ipAddress;
      var1 = 31 * var1 + this._port;
      var1 = 31 * var1 + this._localPort;
      if (this._apnName != null) {
         var1 = 31 * var1 + this._apnName.hashCode();
      }

      return var1;
   }

   public final String getApnName() {
      return this._apnName;
   }

   public final void setApnName(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getApnUsername() {
      return this._apnUsername;
   }

   public final void setApnUsername(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getApnPassword() {
      return this._apnPassword;
   }

   public final void setApnPassword(String var1) {
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

   public final boolean compareApn(String var1) {
      return this._apnName.equalsIgnoreCase(var1);
   }

   public static final String makeAddress(boolean var0, byte[] var1, int var2, int var3) {
      return makeAddress(var0, var1, var2, var3, null, 0, 0);
   }

   public static final String makeAddress(boolean var0, byte[] var1, int var2, int var3, String var4) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String makeAddress(boolean var0, byte[] var1, int var2, int var3, String var4, int var5, int var6) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final void convertIpAddressBytesToStringBuffer(byte[] var0, StringBuffer var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final String getLocalAddress() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
