package java.io;

public class ByteArrayInputStream extends InputStream {
   protected byte[] buf;
   protected int pos;
   protected int mark = 0;
   protected int count;

   public ByteArrayInputStream(byte[] var1) {
      this.buf = var1;
      this.pos = 0;
      this.count = var1.length;
   }

   public ByteArrayInputStream(byte[] var1, int var2, int var3) {
      this.buf = var1;
      this.pos = var2;
      this.count = Math.min(var2 + var3, var1.length);
      this.mark = var2;
   }

   @Override
   public synchronized int read() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public synchronized int read(byte[] var1, int var2, int var3) {
      if (var1 == null) {
         throw new NullPointerException();
      }

      if (var2 < 0 || var2 > var1.length || var3 < 0 || var2 + var3 > var1.length || var2 + var3 < 0) {
         throw new IndexOutOfBoundsException();
      }

      if (this.pos >= this.count) {
         return -1;
      }

      if (this.pos + var3 > this.count) {
         var3 = this.count - this.pos;
      }

      if (var3 <= 0) {
         return 0;
      }

      System.arraycopy(this.buf, this.pos, var1, var2, var3);
      this.pos += var3;
      return var3;
   }

   @Override
   public synchronized long skip(long var1) {
      if (this.pos + var1 > this.count) {
         var1 = this.count - this.pos;
      }

      if (var1 < 0) {
         return 0;
      }

      this.pos = (int)(this.pos + var1);
      return var1;
   }

   @Override
   public synchronized int available() {
      return this.count - this.pos;
   }

   @Override
   public boolean markSupported() {
      return true;
   }

   @Override
   public void mark(int var1) {
      this.mark = this.pos;
   }

   @Override
   public synchronized void reset() {
      this.pos = this.mark;
   }

   @Override
   public synchronized void close() {
   }
}
