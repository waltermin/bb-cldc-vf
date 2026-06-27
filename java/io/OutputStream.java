package java.io;

public class OutputStream {
   public void write(int var1) {
      throw null;
   }

   public void write(byte[] var1) {
      this.write(var1, 0, var1.length);
   }

   public void write(byte[] var1, int var2, int var3) {
      if (var1 == null) {
         throw new NullPointerException();
      }

      if (var2 < 0 || var2 > var1.length || var3 < 0 || var2 + var3 > var1.length || var2 + var3 < 0) {
         throw new IndexOutOfBoundsException();
      }

      if (var3 != 0) {
         for (int var4 = 0; var4 < var3; var4++) {
            this.write(var1[var2 + var4]);
         }
      }
   }

   public void flush() {
   }

   public void close() {
   }
}
