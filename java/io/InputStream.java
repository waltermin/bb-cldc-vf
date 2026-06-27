package java.io;

public class InputStream {
   public int read() {
      throw null;
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public int read(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public long skip(long var1) {
      long var3 = var1;

      while (var3 > 0 && this.read() >= 0) {
         var3 -= 1;
      }

      return var1 - var3;
   }

   public int available() {
      return 0;
   }

   public void close() {
   }

   public synchronized void mark(int var1) {
   }

   public synchronized void reset() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean markSupported() {
      return false;
   }
}
