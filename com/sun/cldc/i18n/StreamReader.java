package com.sun.cldc.i18n;

import java.io.InputStream;
import java.io.Reader;

public class StreamReader extends Reader {
   public InputStream in;

   public Reader open(InputStream var1, String var2) {
      this.in = var1;
      return this;
   }

   @Override
   public boolean ready() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean markSupported() {
      return this.in.markSupported();
   }

   @Override
   public void mark(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void reset() {
      this.in.reset();
   }

   @Override
   public void close() {
      this.in.close();
      this.in = null;
   }

   public int sizeOf(byte[] var1, int var2, int var3) {
      throw null;
   }
}
