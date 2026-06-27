package net.rim.device.cldc.io.dns;

import net.rim.device.api.util.DataBuffer;

public final class DNSMessageIPv4Question {
   private String _qname;
   private int _qtype = 1;
   private int _qclass = 1;

   public final void setQname(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String getQname() {
      return this._qname;
   }

   public final void setQtype(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getQtype() {
      return this._qtype;
   }

   public final void setQclass(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getQclass() {
      return this._qclass;
   }

   final void writeQuestion(DataBuffer var1) {
      DNSMessageIPv4.writeDomainName(var1, this._qname);
      var1.writeShort(this._qtype);
      var1.writeShort(this._qclass);
   }

   final void readQuestion(DataBuffer var1) {
      this._qname = DNSMessageIPv4.readDomainName(var1);
      this._qtype = var1.readShort();
      this._qclass = var1.readShort();
   }
}
