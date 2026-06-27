package net.rim.device.internal.media;

import java.io.InputStream;

class HTTPBufferingManager$HTTPBufferedInputStream extends InputStream {
   private final byte[] readBuffer;
   private boolean _closed;
   private int _markingPos;
   private boolean _skipping;
   private final HTTPBufferingManager this$0;

   HTTPBufferingManager$HTTPBufferedInputStream(HTTPBufferingManager var1) {
      this.this$0 = var1;
      this.readBuffer = new byte[1];
   }

   @Override
   public void mark(int var1) {
      this._markingPos = this.this$0._readOffset;
   }

   @Override
   public int available() {
      return this.this$0._dataLength;
   }

   @Override
   public void reset() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean markSupported() {
      return this.this$0._totalInputLength <= 1048576;
   }

   @Override
   public long skip(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int read() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void arrayCopy(byte[] var1, int var2, byte[] var3, int var4, int var5) {
      if (var5 == 1) {
         var3[var4] = var1[var2];
      } else {
         System.arraycopy(var1, var2, var3, var4, var5);
      }
   }

   @Override
   public void close() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
