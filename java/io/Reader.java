package java.io;

public class Reader {
   protected Object lock;
   private char[] skipBuffer;
   private static final int maxSkipBufferSize;

   protected Reader() {
      this.lock = this;
   }

   protected Reader(Object lock) {
      if (lock == null) {
         throw new NullPointerException();
      }

      this.lock = lock;
   }

   public int read() {
      char[] cb = new char[1];
      return this.read(cb, 0, 1) == -1 ? -1 : cb[0];
   }

   public int read(char[] cbuf) {
      return this.read(cbuf, 0, cbuf.length);
   }

   public int read(char[] _1, int _2, int _3) {
      throw null;
   }

   public long skip(long n) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean ready() {
      return false;
   }

   public boolean markSupported() {
      return false;
   }

   public void mark(int readAheadLimit) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void reset() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void close() {
      throw null;
   }
}
