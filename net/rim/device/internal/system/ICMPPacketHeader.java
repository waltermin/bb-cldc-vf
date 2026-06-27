package net.rim.device.internal.system;

import net.rim.device.api.system.RadioPacketHeader;

public final class ICMPPacketHeader implements RadioPacketHeader {
   private int _sourceAddress;
   private int _destinationAddress;
   private int _accessPointNumber;
   private byte _type;
   private byte _code;
   private short _checksum;
   private byte _ttl;
   public static final int TYPE_ECHO_REPLY;
   public static final int TYPE_DEST_UNREACHABLE;
   public static final int TYPE_SOURCE_QUENCH;
   public static final int TYPE_REDIRECT;
   public static final int TYPE_ECHO;
   public static final int TYPE_TIME_EXCEEDED;
   public static final int TYPE_PARAMETER_PROBLEM;
   public static final int TYPE_TIMESTAMP;
   public static final int TYPE_TIMESTAMP_REPLY;
   public static final int TYPE_INFO_REQUEST;
   public static final int TYPE_INFO_REPLY;
   public static final int MAX_ICMP_PACKET_SIZE;

   public ICMPPacketHeader() {
      this.reset();
   }

   @Override
   public final void reset() {
      this._sourceAddress = 0;
      this._destinationAddress = 0;
      this._accessPointNumber = 0;
      this._type = 0;
      this._code = 0;
      this._checksum = 0;
   }

   public final byte[] getSourceAddress() {
      return IPv4IntToByteArray(this._sourceAddress);
   }

   public final byte[] getDestinationAddress() {
      return IPv4IntToByteArray(this._destinationAddress);
   }

   public final int getAccessPointNumber() {
      return this._accessPointNumber;
   }

   public final int getType() {
      return this._type & 0xFF;
   }

   public final int getCode() {
      return this._code & 0xFF;
   }

   public final int getChecksum() {
      return this._checksum & 0xFF;
   }

   public final byte getTTL() {
      return this._ttl;
   }

   public final void setSourceAddress(byte[] var1) {
      this._sourceAddress = IPv4ByteArrayToInt(var1);
   }

   public final void setDestinationAddress(byte[] var1) {
      this._destinationAddress = IPv4ByteArrayToInt(var1);
   }

   public final void setAccessPointNumber(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setType(int var1) {
      this._type = (byte)var1;
   }

   public final void setCode(int var1) {
      this._code = (byte)var1;
   }

   public final void setChecksum(int var1) {
      this._checksum = (short)var1;
   }

   public final void setTTL(byte var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public static final byte[] IPv4IntToByteArray(int var0) {
      return new byte[]{(byte)(var0 >>> 24 & 0xFF), (byte)(var0 >>> 16 & 0xFF), (byte)(var0 >>> 8 & 0xFF), (byte)(var0 & 0xFF)};
   }

   public static final int IPv4ByteArrayToInt(byte[] var0) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
