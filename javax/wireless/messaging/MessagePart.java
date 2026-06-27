package javax.wireless.messaging;

import java.io.InputStream;

public class MessagePart {
   private byte[] _contents;
   private String _mimeType;
   private String _contentId;
   private String _contentLocation;
   private String _enc;

   public MessagePart(byte[] var1, int var2, int var3, String var4, String var5, String var6, String var7) {
      if (var4 != null && var5 != null && var3 >= 0 && var2 >= 0) {
         if (var1 != null) {
            if (var2 + var3 > var1.length) {
               throw new Object();
            }

            this._contents = new byte[var3];
            System.arraycopy(var1, var2, this._contents, 0, var3);
         }

         this._mimeType = var4;
         if (var5 != null && !this.isASCII(var5)) {
            throw new Object();
         }

         if (var6 != null && !this.isASCII(var6)) {
            throw new Object();
         }

         this._contentId = var5;
         this._contentLocation = var6;
         this._enc = var7;
      } else {
         throw new Object();
      }
   }

   public MessagePart(byte[] var1, String var2, String var3, String var4, String var5) {
      if (var2 == null || var3 == null) {
         throw new Object();
      }

      if (var3 != null && !this.isASCII(var3)) {
         throw new Object();
      }

      if (var4 != null && !this.isASCII(var4)) {
         throw new Object();
      }

      if (var1 != null) {
         this._contents = new byte[var1.length];
         System.arraycopy(var1, 0, this._contents, 0, var1.length);
      }

      this._mimeType = var2;
      this._contentId = var3;
      this._contentLocation = var4;
      this._enc = var5;
   }

   public MessagePart(InputStream var1, String var2, String var3, String var4, String var5) {
      if (var2 == null || var3 == null) {
         throw new Object();
      }

      if (var3 != null && !this.isASCII(var3)) {
         throw new Object();
      }

      if (var4 != null && !this.isASCII(var4)) {
         throw new Object();
      }

      if (var1 != null) {
         this._contents = new byte[var1.available()];
         var1.read(this._contents);
      }

      this._mimeType = var2;
      this._contentId = var3;
      this._contentLocation = var4;
      this._enc = var5;
   }

   public byte[] getContent() {
      return this._contents;
   }

   public InputStream getContentAsStream() {
      return (InputStream)(this._contents == null ? new Object(new byte[0]) : new Object(this._contents));
   }

   public String getContentID() {
      return this._contentId;
   }

   public String getContentLocation() {
      return this._contentLocation;
   }

   public String getEncoding() {
      return this._enc;
   }

   public int getLength() {
      return this._contents != null ? this._contents.length : 0;
   }

   public String getMIMEType() {
      return this._mimeType;
   }

   private boolean isASCII(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
