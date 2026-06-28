package net.rim.device.cldc.io.dns;

import java.util.Vector;
import net.rim.device.api.util.DataBuffer;

public final class DNSMessageIPv4Resource {
   private String _name = null;
   private int _type = 1;
   private int _class = 1;
   private int _ttl;
   private int _rdlength = 4;
   private byte[] _rdata;
   private Object _data;

   public final void setName(String name) {
      if (name != null) {
         if (name.length() == 0) {
            name = null;
         } else if (name.length() > 255) {
            throw new IllegalArgumentException("Domain longer than 255 characters");
         }
      }

      this._name = name;
   }

   public final String getName() {
      return this._name;
   }

   public final void setType(int type) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getType() {
      return this._type;
   }

   public final void setClazz(int clazz) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getClazz() {
      return this._class;
   }

   public final void setTTL(int ttl) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getTTL() {
      return this._ttl;
   }

   public final void setRDLength(int rdlength) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getRDLength() {
      return this._rdlength;
   }

   public final void setRData(byte[] rdata) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final byte[] getRData() {
      return this._rdata;
   }

   final void writeResource(DataBuffer db) {
      DNSMessageIPv4.writeDomainName(db, this._name);
      db.writeShort(this._type);
      db.writeShort(this._class);
      db.writeInt(this._ttl);
      db.writeShort(this._rdlength);
      db.write(this._rdata, 0, this._rdlength);
   }

   final void readResource(DataBuffer db) {
      this._name = DNSMessageIPv4.readDomainName(db);
      this._type = db.readShort();
      this._class = db.readShort();
      this._ttl = db.readInt();
      this._rdlength = db.readShort();
      int rdataPos = db.getPosition();
      this._rdata = new byte[this._rdlength];
      db.readFully(this._rdata);
      db.setPosition(rdataPos);
      switch (this._type) {
         case 1:
         case 3:
         case 4:
         case 7:
         case 8:
         case 9:
         case 10:
            db.skipBytes(this._rdlength);
            this._data = this._rdata;
            return;
         case 2:
         case 5:
         case 12:
         default:
            this._data = DNSMessageIPv4.readDomainName(db);
            return;
         case 6:
            this._data = new DNSMessageIPv4Resource$SOAData(db);
            return;
         case 11:
            this._data = new DNSMessageIPv4Resource$WKSData(db, this._rdlength);
            return;
         case 13:
         case 16:
            int length = this._rdlength;
            Vector v = new Vector();

            while (length > 0) {
               byte[] array = new byte[db.readUnsignedByte()];
               db.readFully(array);
               v.addElement(array);
               length -= 1 + array.length;
            }

            this._data = v;
            return;
         case 14:
            this._data = new DNSMessageIPv4Resource$MINFOData(db);
            return;
         case 15:
            this._data = new DNSMessageIPv4Resource$MXData(db);
      }
   }

   public final Object getData() {
      return this._data;
   }
}
