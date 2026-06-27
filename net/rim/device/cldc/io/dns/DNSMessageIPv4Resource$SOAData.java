package net.rim.device.cldc.io.dns;

import net.rim.device.api.util.DataBuffer;

public class DNSMessageIPv4Resource$SOAData {
   public String mName;
   public String rName;
   public int serial;
   public int refresh;
   public int retry;
   public int expire;
   public int minimum;

   public DNSMessageIPv4Resource$SOAData(DataBuffer var1) {
      this.mName = DNSMessageIPv4.readDomainName(var1);
      this.rName = DNSMessageIPv4.readDomainName(var1);
      this.serial = var1.readInt();
      this.refresh = var1.readInt();
      this.retry = var1.readInt();
      this.expire = var1.readInt();
      this.minimum = var1.readInt();
   }
}
