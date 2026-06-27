package java.io;

import com.sun.cldc.i18n.Helper;

public class OutputStreamWriter extends Writer {
   private Writer out;

   public OutputStreamWriter(OutputStream var1) {
      this.out = Helper.getStreamWriter(var1);
   }

   public OutputStreamWriter(OutputStream var1, String var2) {
      this.out = Helper.getStreamWriter(var1, var2);
   }

   private void ensureOpen() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void write(int var1) {
      this.ensureOpen();
      this.out.write(var1);
   }

   @Override
   public void write(char[] var1, int var2, int var3) {
      this.ensureOpen();
      if (var2 < 0 || var2 > var1.length || var3 < 0 || var2 + var3 > var1.length || var2 + var3 < 0) {
         throw new IndexOutOfBoundsException();
      }

      if (var3 != 0) {
         this.out.write(var1, var2, var3);
      }
   }

   @Override
   public void write(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public void flush() {
      this.ensureOpen();
      this.out.flush();
   }

   @Override
   public void close() {
      if (this.out != null) {
         this.out.close();
         this.out = null;
      }
   }
}
