package net.rim.device.api.io;

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

   public UdpAddress(byte[] var1, int var2, int var3, String var4, int var5) {
      this.init(var1 != null ? DatagramAddressBase.readInt(var1, 0) : -1, var2, var3, var4, var5);
   }

   public UdpAddress(byte[] var1, int var2, int var3, String var4, int var5, int var6, int var7) {
      this.init(var1 != null ? DatagramAddressBase.readInt(var1, 0) : -1, var2, var3, var4 != null ? var4.substring(var5, var5 + var6) : null, var7);
   }

   public UdpAddress(int var1, int var2, int var3, String var4, int var5) {
      this.init(var1, var2, var3, var4, var5);
   }

   public UdpAddress(int var1, int var2, int var3, String var4, int var5, int var6, int var7) {
      this.init(var1, var2, var3, var4 != null ? var4.substring(var5, var5 + var6) : null, var7);
   }

   public UdpAddress(DatagramAddressBase var1) {
   }

   public UdpAddress(String var1) {
      this.setAddress(var1);
   }

   private void init(int var1, int var2, int var3, String var4, int var5) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public byte[] getIpAddress() {
      byte[] var1 = null;
      if (this._ipAddress != -1) {
         var1 = new byte[4];
         DatagramAddressBase.writeInt(var1, 0, this._ipAddress);
      }

      return var1;
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

   public void setApn(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getApnUsername() {
      return this._apnUsername;
   }

   public void setApnUsername(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getApnPassword() {
      return this._apnPassword;
   }

   public void setApnPassword(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setSrcPort(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setDestPort(int var1) {
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
   public void setAddress(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String resolveAddress(String var0) {
      return setAddress(null, var0, true, false);
   }

   private static final String setAddress(UdpAddress var0, String var1, boolean var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getAddress() {
      if (super._address == null) {
         byte[] var1 = new byte[4];
         DatagramAddressBase.writeInt(var1, 0, this._ipAddress);
         super._address = makeAddress(false, var1, this._destPort, this._srcPort, this._apn, this._type, this._apnUsername, this._apnPassword);
      }

      return super._address;
   }

   @Override
   public void swap() {
      int var1 = this._destPort;
      this._destPort = this._srcPort;
      this._srcPort = var1;
      super._address = null;
   }

   @Override
   public boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int hashCode() {
      int var1 = 7;
      var1 = 31 * var1 + this._ipAddress;
      var1 = 31 * var1 + this._destPort;
      var1 = 31 * var1 + this._srcPort;
      if (this._apn != null) {
         var1 = 31 * var1 + this._apn.hashCode();
      }

      return 31 * var1 + this._type;
   }

   public boolean compareApn(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static String makeAddress(boolean var0, byte[] var1, int var2, int var3, String var4, int var5) {
      return makeAddress(var0, var1, var2, var3, var4, var5, null, null);
   }

   public static String makeAddress(boolean var0, byte[] var1, int var2, int var3, String var4, int var5, int var6, int var7) {
      return makeAddress(var0, var1, var2, var3, var4 != null ? var4.substring(var5, var5 + var6) : null, var7, null, null);
   }

   public static String makeAddress(boolean var0, byte[] var1, int var2, int var3, String var4, int var5, String var6, String var7) {
      Object var8 = new Object(128);
      appendAddress((StringBuffer)var8, var0, var1, var2, var3, var4, var5, var6, var7);
      return ((StringBuffer)var8).toString();
   }

   public static void appendAddress(StringBuffer var0, boolean var1, byte[] var2, int var3, int var4, String var5, int var6, int var7, int var8) {
      appendAddress(var0, var1, var2, var3, var4, var5 != null ? var5.substring(var6, var6 + var7) : null, var8, null, null);
   }

   public static void appendAddress(StringBuffer var0, boolean var1, byte[] var2, int var3, int var4, String var5, int var6) {
      appendAddress(var0, var1, var2, var3, var4, var5, var6, null, null);
   }

   private static void appendAddress(StringBuffer var0, boolean var1, byte[] var2, int var3, int var4, String var5, int var6, String var7, String var8) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static byte[] parseIpAddress(String var0, int var1) {
      byte[] var2 = new byte[4];
      DatagramAddressBase.writeInt(var2, 0, DatagramAddressBase.parseIpAddressInt(var0, var1));
      return var2;
   }

   public static String[] retrieveApnSettings(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   protected static String parseApn(String var0, int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   protected static int parseApnCredentials(String var0, int var1, String[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static byte[] resolveAddress(String var0, String var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
