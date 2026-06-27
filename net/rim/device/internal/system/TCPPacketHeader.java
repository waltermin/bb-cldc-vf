package net.rim.device.internal.system;

import net.rim.device.api.system.RadioPacketHeader;

public final class TCPPacketHeader implements RadioPacketHeader {
   public int _sourceAddress;
   public int _destinationAddress;
   private int _sourcePort;
   private int _destinationPort;
   public int _accessPointNumber;
   public int _sequenceNumber;
   public int _acknowledgementNumber;
   public int _flags;
   public int _window;
   public int _urgentPointer;
   public int _dataOffset;
   public int _controlCode;
   public int _controlDescription;
   public int _socketID;
   public boolean _isSimulTcpPacket;
   private static final int MIN_IP_HEADER_SIZE;
   private static final int MIN_TCP_HEADER_SIZE;
   private static int MAX_TCP_PAYLOAD_SIZE;

   @Override
   public final void reset() {
      this._sourceAddress = 0;
      this._destinationAddress = 0;
      this._sourcePort = 0;
      this._destinationPort = 0;
      this._accessPointNumber = 0;
      this._sequenceNumber = 0;
      this._acknowledgementNumber = 0;
      this._flags = 0;
      this._window = 0;
      this._urgentPointer = 0;
      this._dataOffset = 0;
      if (this._isSimulTcpPacket) {
         this._controlCode = 0;
         this._controlDescription = 0;
         this._socketID = 0;
      }
   }

   public final byte[] getSourceAddress() {
      return IPv4IntToByteArray(this._sourceAddress);
   }

   public final byte[] getDestinationAddress() {
      return IPv4IntToByteArray(this._destinationAddress);
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

   public final int getSourcePort() {
      return this._sourcePort;
   }

   public final void setDestinationPort(int var1) {
      this.checkPortRange(var1);
      this._destinationPort = var1;
   }

   public final int getDestinationPort() {
      return this._destinationPort;
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
      return MAX_TCP_PAYLOAD_SIZE;
   }

   public static final int getMaxPacketSize(int var0) {
      if (var0 >= 0) {
         byte[] var1 = RadioInternal.getNetworkParameter(var0, 102, 0);
         if (var1 != null && var1.length > 1) {
            int var2 = (var1[1] & 255) << 8 | var1[0] & 255;
            if (var2 > 0) {
               return Math.min(var2, getMaxPacketSize());
            }
         }
      }

      return getMaxPacketSize();
   }
}
