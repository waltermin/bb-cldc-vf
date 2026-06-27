package net.rim.device.cldc.io.dns;

import java.util.Vector;
import net.rim.device.api.util.DataBuffer;

public final class DNSMessageIPv4 {
   protected int _id;
   protected int _flags;
   protected Vector _questionSection = (Vector)(new Object());
   protected Vector _answerSection = (Vector)(new Object());
   protected Vector _authoritySection = (Vector)(new Object());
   protected Vector _additionalSection = (Vector)(new Object());
   public static final int QR_QUERY;
   public static final int QR_RESPONSE;
   public static final int OPCODE_QUERY;
   public static final int OPCODE_IQUERY;
   public static final int OPCODE_STATUS;
   public static final int AA_NOTAUTHORITY;
   public static final int AA_AUTHORITY;
   public static final int TC_NOTTRUNCATED;
   public static final int TC_TRUNCATED;
   public static final int RD_RECURSIONNOTDESIRED;
   public static final int RD_RECURSIONDESIRED;
   public static final int RA_RECURSIONNOTAVAILABLE;
   public static final int RA_RECURSIONAVAILABLE;
   public static final int RCODE_NOERROR;
   public static final int RCODE_FORMATERROR;
   public static final int RCODE_SERVERFAILURE;
   public static final int RCODE_NAMEERROR;
   public static final int RCODE_NOTIMPLEMENTED;
   public static final int RCODE_REFUSED;
   public static final int TYPE_A;
   public static final int TYPE_NS;
   public static final int TYPE_MD;
   public static final int TYPE_MF;
   public static final int TYPE_CNAME;
   public static final int TYPE_SOA;
   public static final int TYPE_MB;
   public static final int TYPE_MG;
   public static final int TYPE_MR;
   public static final int TYPE_NULL;
   public static final int TYPE_WKS;
   public static final int TYPE_PTR;
   public static final int TYPE_HINFO;
   public static final int TYPE_MINFO;
   public static final int TYPE_MX;
   public static final int TYPE_TXT;
   public static final int QTYPE_AXFR;
   public static final int QTYPE_MAILB;
   public static final int QTYPE_MAILA;
   public static final int QTYPE_ALL;
   public static final int CLASS_IN;
   public static final int CLASS_CS;
   public static final int CLASS_CH;
   public static final int CLASS_HS;
   public static final int QCLASS_ALL;
   protected static final int RCODE_MASK;
   protected static final int RA_MASK;
   protected static final int RD_MASK;
   protected static final int TC_MASK;
   protected static final int AA_MASK;
   protected static final int OPCODE_MASK;
   protected static final int QR_MASK;
   static final int MAX_CNAME_ATTEMPTS;
   static final int MAX_REFERRAL_ATTEMPTS;

   public final int getID() {
      return this._id;
   }

   public final void setID(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getQR() {
      return this._flags & 32768;
   }

   public final void setQR(int var1) {
      if (var1 != 0 && var1 != 32768) {
         throw new Object();
      }

      this._flags = this._flags & -32769 | var1;
   }

   public final int getOpcode() {
      return this._flags & 30720;
   }

   public final void setOpcode(int var1) {
      if (var1 != 0 && var1 != 2048 && var1 != 4096) {
         throw new Object();
      }

      this._flags = this._flags & -30721 | var1;
   }

   public final int getAA() {
      return this._flags & 1024;
   }

   public final void setAA(int var1) {
      if (var1 != 0 && var1 != 1024) {
         throw new Object();
      }

      this._flags = this._flags & -1025 | var1;
   }

   public final int getTC() {
      return this._flags & 512;
   }

   public final void setTC(int var1) {
      if (var1 != 0 && var1 != 512) {
         throw new Object();
      }

      this._flags = this._flags & -513 | var1;
   }

   public final int getRD() {
      return this._flags & 256;
   }

   public final void setRD(int var1) {
      if (var1 != 0 && var1 != 256) {
         throw new Object();
      }

      this._flags = this._flags & -257 | var1;
   }

   public final int getRA() {
      return this._flags & 128;
   }

   public final void setRA(int var1) {
      if (var1 != 0 && var1 != 128) {
         throw new Object();
      }

      this._flags = this._flags & -129 | var1;
   }

   public final int getRcode() {
      return this._flags & 15;
   }

   public final void setRcode(int var1) {
      if (var1 != 0 && var1 != 1 && var1 != 2 && var1 != 3 && var1 != 4 && var1 != 5) {
         throw new Object();
      }

      this._flags = this._flags & -16 | var1;
   }

   final void setFlags(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   final int getFlags() {
      return this._flags;
   }

   public final void addQuestion(DNSMessageIPv4Question var1) {
      this._questionSection.addElement(var1);
   }

   public final Vector getQuestions() {
      return this._questionSection;
   }

   public final void addAnswer(DNSMessageIPv4Resource var1) {
      this._answerSection.addElement(var1);
   }

   public final Vector getAnswers() {
      return this._answerSection;
   }

   public final void addAuthority(DNSMessageIPv4Resource var1) {
      this._authoritySection.addElement(var1);
   }

   public final Vector getAuthorities() {
      return this._authoritySection;
   }

   public final void addAdditional(DNSMessageIPv4Resource var1) {
      this._additionalSection.addElement(var1);
   }

   public final Vector getAdditional() {
      return this._additionalSection;
   }

   final void writeMessage(DataBuffer var1) {
      int var2 = this._questionSection.size();
      int var3 = this._answerSection.size();
      int var4 = this._authoritySection.size();
      int var5 = this._additionalSection.size();
      var1.writeShort(this.getID());
      var1.writeShort(this.getFlags());
      var1.writeShort(var2);
      var1.writeShort(var3);
      var1.writeShort(var4);
      var1.writeShort(var5);

      for (int var6 = 0; var6 < var2; var6++) {
         ((DNSMessageIPv4Question)this._questionSection.elementAt(var6)).writeQuestion(var1);
      }

      for (int var7 = 0; var7 < var3; var7++) {
         ((DNSMessageIPv4Resource)this._answerSection.elementAt(var7)).writeResource(var1);
      }

      for (int var8 = 0; var8 < var4; var8++) {
         ((DNSMessageIPv4Resource)this._authoritySection.elementAt(var8)).writeResource(var1);
      }

      for (int var9 = 0; var9 < var5; var9++) {
         ((DNSMessageIPv4Resource)this._additionalSection.elementAt(var9)).writeResource(var1);
      }
   }

   final void readMessage(DataBuffer var1) {
      this.setID(var1.readShort());
      this.setFlags(var1.readShort());
      int var2 = var1.readUnsignedShort();
      int var3 = var1.readUnsignedShort();
      int var4 = var1.readUnsignedShort();
      int var5 = var1.readUnsignedShort();

      for (int var6 = 0; var6 < var2; var6++) {
         DNSMessageIPv4Question var7 = new DNSMessageIPv4Question();
         var7.readQuestion(var1);
         this.addQuestion(var7);
      }

      for (int var9 = 0; var9 < var3; var9++) {
         DNSMessageIPv4Resource var8 = new DNSMessageIPv4Resource();
         var8.readResource(var1);
         this.addAnswer(var8);
      }

      for (int var10 = 0; var10 < var4; var10++) {
         DNSMessageIPv4Resource var12 = new DNSMessageIPv4Resource();
         var12.readResource(var1);
         this.addAuthority(var12);
      }

      for (int var11 = 0; var11 < var5; var11++) {
         DNSMessageIPv4Resource var13 = new DNSMessageIPv4Resource();
         var13.readResource(var1);
         this.addAdditional(var13);
      }
   }

   static final String readDomainName(DataBuffer var0) {
      Object var1 = new Object(32);
      readDomainName(var0, (StringBuffer)var1);
      return ((StringBuffer)var1).toString();
   }

   static final void readDomainName(DataBuffer var0, StringBuffer var1) {
      int var2 = var0.readUnsignedByte();

      while (var2 > 0) {
         if ((var2 & 192) != 0) {
            int var4 = (var2 & 63) << 8;
            var4 |= var0.readUnsignedByte();
            int var3 = var0.getPosition();
            var0.setPosition(var4);
            readDomainName(var0, var1);
            var0.setPosition(var3);
            return;
         }

         while (--var2 >= 0) {
            var1.append((char)var0.readUnsignedByte());
         }

         var2 = var0.readUnsignedByte();
         if (var2 > 0) {
            var1.append('.');
         }
      }
   }

   static final void writeDomainName(DataBuffer var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
