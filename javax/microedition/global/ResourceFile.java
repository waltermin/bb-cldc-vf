package javax.microedition.global;

import java.io.ByteArrayOutputStream;
import net.rim.device.resources.Resource;

class ResourceFile {
   ResourceFile$Resource[] _resources;
   byte[] _filedata;
   public static final int RESOURCE_TYPE_STRING;
   public static final int RESOURCE_TYPE_BINARY;
   public static final int RESOURCE_TYPE_END;
   private static String EMPTY;

   private ResourceFile(byte[] var1, int var2) {
      int var3 = (var2 >> 3) - 1;
      this._resources = new ResourceFile$Resource[var3];

      for (int var4 = 0; var4 < var3; var4++) {
         this._resources[var4] = new ResourceFile$Resource(var1, (var4 + 1) * 8);
         if (var4 > 0) {
            this._resources[var4 - 1].length = this._resources[var4].offset - this._resources[var4 - 1].offset;
         }
      }

      this._resources[var3 - 1].length = var1.length - this._resources[var3 - 1].offset;
      this._filedata = var1;
   }

   public static ResourceFile getResourceFile(String var0) {
      byte[] var1 = Resource.getResourceClass().getResource(var0);
      if (var1 == null) {
         throw new ResourceException(3, var0);
      } else if (var1.length < 8) {
         throw new ResourceException(5, var0);
      } else if (var1[0] != 238 && var1[1] != 77 && var1[2] != 73 && var1[3] != 16) {
         throw new ResourceException(5, var0);
      } else {
         int var2 = (var1[4] & 255) << 24 | (var1[5] & 255) << 16 | (var1[6] & 255) << 8 | var1[7] & 255;
         if (var1.length < var2 + 8) {
            throw new ResourceException(5, var0);
         } else {
            return new ResourceFile(var1, var2);
         }
      }
   }

   private int getIndexForId(int var1) {
      for (int var2 = this._resources.length - 1; var2 >= 0; var2--) {
         if (this._resources[var2].id == var1) {
            return var2;
         }

         if (this._resources[var2].id < var1) {
            return -1;
         }
      }

      return -1;
   }

   private ResourceFile$Resource getResource(int var1) {
      int var2 = this.getIndexForId(var1);
      if (var2 == -1) {
         throw new ResourceException(1, EMPTY);
      } else {
         return this._resources[var2];
      }
   }

   public boolean isValidId(int var1) {
      return this.getIndexForId(var1) != -1;
   }

   public int getType(int var1) {
      return this.getResource(var1).type;
   }

   public Object getData(int var1) {
      ResourceFile$Resource var2 = this.getResource(var1);
      int var3 = var2.length;
      Object var4 = new Object(var3);

      for (int var5 = 0; var5 < var3; var5++) {
         ((ByteArrayOutputStream)var4).write(this._filedata[var5 + var2.offset]);
      }

      switch (var2.type) {
         case 1:
            return ((ByteArrayOutputStream)var4).toString();
         case 16:
            return ((ByteArrayOutputStream)var4).toByteArray();
         default:
            throw new ResourceException(6, EMPTY);
      }
   }
}
