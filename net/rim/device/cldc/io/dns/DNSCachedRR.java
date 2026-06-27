package net.rim.device.cldc.io.dns;

public final class DNSCachedRR {
   private int _type;
   private int _expiryTime;
   private Object _data;

   public DNSCachedRR(DNSMessageIPv4Resource var1, int var2) {
      this._type = var1.getType();
      this._expiryTime = var2 + var1.getTTL();
      switch (this._type) {
         case 6:
            this._data = ((DNSMessageIPv4Resource$SOAData)var1.getData()).mName;
            return;
         case 11:
            this._data = ((DNSMessageIPv4Resource$WKSData)var1.getData()).address;
            return;
         case 14:
            this._data = ((DNSMessageIPv4Resource$MINFOData)var1.getData()).rMailBox;
            return;
         case 15:
            this._data = ((DNSMessageIPv4Resource$MXData)var1.getData()).domainName;
            return;
         default:
            this._data = var1.getData();
      }
   }

   public final int getType() {
      return this._type;
   }

   public final int getExpiryTime() {
      return this._expiryTime;
   }

   public final Object getData() {
      return this._data;
   }
}
