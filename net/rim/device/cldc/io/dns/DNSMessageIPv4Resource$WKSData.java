package net.rim.device.cldc.io.dns;

import net.rim.device.api.util.DataBuffer;

public class DNSMessageIPv4Resource$WKSData {
   public byte[] address = new byte[4];
   public int protocol;
   public byte[] bitMap;

   public DNSMessageIPv4Resource$WKSData(DataBuffer var1, int var2) {
      var1.readFully(this.address);
      this.protocol = var1.readUnsignedByte();
      this.bitMap = new byte[var2 - 4 - 1];
      var1.readFully(this.bitMap);
   }
}
