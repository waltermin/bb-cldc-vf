package java.io;

public class Writer {
   private char[] writeBuffer;
   protected Object lock;
   private static final int writeBufferSize;

   protected Writer() {
      this.lock = this;
   }

   protected Writer(Object var1) {
      if (var1 == null) {
         throw new NullPointerException();
      }

      this.lock = var1;
   }

   public void write(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void write(char[] var1) {
      this.write(var1, 0, var1.length);
   }

   public void write(char[] var1, int var2, int var3) {
      throw null;
   }

   public void write(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void write(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void flush() {
      throw null;
   }

   public void close() {
      throw null;
   }
}
