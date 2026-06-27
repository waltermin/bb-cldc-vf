package java.io;

import net.rim.vm.Array;

public class ByteArrayOutputStream extends OutputStream {
   protected byte[] buf;
   protected int count;
   private boolean isClosed;

   private void ensureOpen() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public ByteArrayOutputStream() {
      this(32);
   }

   public ByteArrayOutputStream(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      }

      this.buf = new byte[var1];
   }

   @Override
   public synchronized void write(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public synchronized void write(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized void reset() {
      this.ensureOpen();
      this.count = 0;
   }

   public synchronized byte[] toByteArray() {
      if (this.isClosed) {
         if (this.buf.length != this.count) {
            Array.resize(this.buf, this.count);
         }

         return this.buf;
      } else {
         byte[] var1 = new byte[this.count];
         System.arraycopy(this.buf, 0, var1, 0, this.count);
         return var1;
      }
   }

   public int size() {
      return this.count;
   }

   @Override
   public String toString() {
      return (String)(new Object(this.buf, 0, this.count));
   }

   @Override
   public synchronized void close() {
      this.isClosed = true;
   }
}
