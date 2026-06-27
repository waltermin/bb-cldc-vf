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

   public final void setName(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String getName() {
      return this._name;
   }

   public final void setType(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getType() {
      return this._type;
   }

   public final void setClazz(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getClazz() {
      return this._class;
   }

   public final void setTTL(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getTTL() {
      return this._ttl;
   }

   public final void setRDLength(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getRDLength() {
      return this._rdlength;
   }

   public final void setRData(byte[] var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final byte[] getRData() {
      return this._rdata;
   }

   final void writeResource(DataBuffer var1) {
      DNSMessageIPv4.writeDomainName(var1, this._name);
      var1.writeShort(this._type);
      var1.writeShort(this._class);
      var1.writeInt(this._ttl);
      var1.writeShort(this._rdlength);
      var1.write(this._rdata, 0, this._rdlength);
   }

   final void readResource(DataBuffer var1) {
      this._name = DNSMessageIPv4.readDomainName(var1);
      this._type = var1.readShort();
      this._class = var1.readShort();
      this._ttl = var1.readInt();
      this._rdlength = var1.readShort();
      int var2 = var1.getPosition();
      this._rdata = new byte[this._rdlength];
      var1.readFully(this._rdata);
      var1.setPosition(var2);
      switch (this._type) {
         case 1:
         case 3:
         case 4:
         case 7:
         case 8:
         case 9:
         case 10:
            var1.skipBytes(this._rdlength);
            this._data = this._rdata;
            return;
         case 2:
         case 5:
         case 12:
         default:
            this._data = DNSMessageIPv4.readDomainName(var1);
            return;
         case 6:
            this._data = new DNSMessageIPv4Resource$SOAData(var1);
            return;
         case 11:
            this._data = new DNSMessageIPv4Resource$WKSData(var1, this._rdlength);
            return;
         case 13:
         case 16:
            int var3 = this._rdlength;
            Object var4 = new Object();

            while (var3 > 0) {
               byte[] var5 = new byte[var1.readUnsignedByte()];
               var1.readFully(var5);
               ((Vector)var4).addElement(var5);
               var3 -= 1 + var5.length;
            }

            this._data = var4;
            return;
         case 14:
            this._data = new DNSMessageIPv4Resource$MINFOData(var1);
            return;
         case 15:
            this._data = new DNSMessageIPv4Resource$MXData(var1);
      }
   }

   public final Object getData() {
      return this._data;
   }
}
