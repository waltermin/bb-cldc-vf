package java.io;

import com.sun.cldc.i18n.Helper;

public class InputStreamReader extends Reader {
   private Reader in;

   public InputStreamReader(InputStream var1) {
      this.in = Helper.getStreamReader(var1);
   }

   public InputStreamReader(InputStream var1, String var2) {
      this.in = Helper.getStreamReader(var1, var2);
   }

   private void ensureOpen() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int read() {
      this.ensureOpen();
      return this.in.read();
   }

   @Override
   public int read(char[] var1, int var2, int var3) {
      this.ensureOpen();
      if (var2 < 0 || var2 > var1.length || var3 < 0 || var2 + var3 > var1.length || var2 + var3 < 0) {
         throw new IndexOutOfBoundsException();
      } else {
         return var3 == 0 ? 0 : this.in.read(var1, var2, var3);
      }
   }

   @Override
   public long skip(long var1) {
      this.ensureOpen();
      return this.in.skip(var1);
   }

   @Override
   public boolean ready() {
      this.ensureOpen();
      return this.in.ready();
   }

   @Override
   public boolean markSupported() {
      return this.in == null ? false : this.in.markSupported();
   }

   @Override
   public void mark(int var1) {
      this.ensureOpen();
      this.in.mark(var1);
   }

   @Override
   public void reset() {
      this.ensureOpen();
      this.in.reset();
   }

   @Override
   public void close() {
      if (this.in != null) {
         this.in.close();
         this.in = null;
      }
   }
}
