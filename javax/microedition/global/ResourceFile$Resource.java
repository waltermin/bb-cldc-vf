package javax.microedition.global;

class ResourceFile$Resource {
   int id;
   int type;
   int offset;
   int length;
   Object data;

   public ResourceFile$Resource(byte[] var1, int var2) {
      this.id = (var1[var2] & 255) << 24 | (var1[var2 + 1] & 255) << 16 | (var1[var2 + 2] & 255) << 8 | var1[var2 + 3] & 255;
      this.type = var1[var2 + 4];
      this.offset = (var1[var2 + 5] & 255) << 16 | (var1[var2 + 6] & 255) << 8 | var1[var2 + 7] & 255;
      this.length = 0;
      this.data = null;
   }
}
