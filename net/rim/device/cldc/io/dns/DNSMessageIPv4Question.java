package net.rim.device.cldc.io.dns;

import net.rim.device.api.util.DataBuffer;

public final class DNSMessageIPv4Question {
   private String _qname;
   private int _qtype = 1;
   private int _qclass = 1;

   public final void setQname(String qname) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String getQname() {
      return this._qname;
   }

   public final void setQtype(int qtype) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getQtype() {
      return this._qtype;
   }

   public final void setQclass(int qclass) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getQclass() {
      return this._qclass;
   }

   final void writeQuestion(DataBuffer db) {
      DNSMessageIPv4.writeDomainName(db, this._qname);
      db.writeShort(this._qtype);
      db.writeShort(this._qclass);
   }

   final void readQuestion(DataBuffer db) {
      this._qname = DNSMessageIPv4.readDomainName(db);
      this._qtype = db.readShort();
      this._qclass = db.readShort();
   }
}
