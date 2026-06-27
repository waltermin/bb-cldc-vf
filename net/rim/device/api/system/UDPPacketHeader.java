package net.rim.device.api.system;

public final class UDPPacketHeader implements RadioPacketHeader {
   private int _sourceAddress;
   private int _destinationAddress;
   private int _sourcePort;
   private int _destinationPort;
   private int _accessPointNumber;

   public UDPPacketHeader() {
      this.reset();
   }

   @Override
   public final void reset() {
      this._sourceAddress = 0;
      this._destinationAddress = 0;
      this._sourcePort = 0;
      this._destinationPort = 0;
      this._accessPointNumber = 0;
   }

   public final byte[] getSourceAddress() {
      return IPv4IntToByteArray(this._sourceAddress);
   }

   public final byte[] getDestinationAddress() {
      return IPv4IntToByteArray(this._destinationAddress);
   }

   public final int getSourcePort() {
      return this._sourcePort;
   }

   public final int getDestinationPort() {
      return this._destinationPort;
   }

   public final int getAccessPointNumber() {
      return this._accessPointNumber;
   }

   public final void setSourceAddress(byte[] var1) {
      this._sourceAddress = IPv4ByteArrayToInt(var1);
   }

   public final void setDestinationAddress(byte[] var1) {
      this._destinationAddress = IPv4ByteArrayToInt(var1);
   }

   public final void setSourcePort(int var1) {
      this.checkPortRange(var1);
      this._sourcePort = var1;
   }

   public final void setDestinationPort(int var1) {
      this.checkPortRange(var1);
      this._destinationPort = var1;
   }

   public final void setAccessPointNumber(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private final void checkPortRange(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte[] IPv4IntToByteArray(int var0) {
      return new byte[]{(byte)(var0 >>> 24 & 0xFF), (byte)(var0 >>> 16 & 0xFF), (byte)(var0 >>> 8 & 0xFF), (byte)(var0 & 0xFF)};
   }

   public static final int IPv4ByteArrayToInt(byte[] var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final int getMaxPacketSize() {
      return 1492;
   }
}
