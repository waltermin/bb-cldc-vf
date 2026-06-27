package net.rim.device.internal.media;

import java.io.InputStream;
import javax.microedition.media.protocol.SourceStream;

class DataSourceInputStream extends InputStream {
   private SourceStream _source;
   private static byte[] ONE_BYTE;

   public DataSourceInputStream(SourceStream var1) {
      this._source = var1;
   }

   @Override
   public int read() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      return this._source.read(var1, var2, var3);
   }

   @Override
   public long skip(long var1) {
      if (var1 <= 0) {
         return 0;
      }

      if (this._source.getSeekType() == 2) {
         long var3 = this._source.tell();
         if (var3 >= 0) {
            return this._source.seek(var3 + var1) - var3;
         }
      }

      return super.skip(var1);
   }
}
