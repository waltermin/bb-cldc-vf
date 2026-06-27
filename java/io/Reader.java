package java.io;

public class Reader {
   protected Object lock;
   private char[] skipBuffer;
   private static final int maxSkipBufferSize;

   protected Reader() {
      this.lock = this;
   }

   protected Reader(Object var1) {
      if (var1 == null) {
         throw new NullPointerException();
      }

      this.lock = var1;
   }

   public int read() {
      char[] var1 = new char[1];
      return this.read(var1, 0, 1) == -1 ? -1 : var1[0];
   }

   public int read(char[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public int read(char[] var1, int var2, int var3) {
      throw null;
   }

   public long skip(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean ready() {
      return false;
   }

   public boolean markSupported() {
      return false;
   }

   public void mark(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void reset() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void close() {
      throw null;
   }
}
