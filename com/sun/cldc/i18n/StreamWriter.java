package com.sun.cldc.i18n;

import java.io.OutputStream;
import java.io.Writer;

public class StreamWriter extends Writer {
   public OutputStream out;

   public Writer open(OutputStream var1, String var2) {
      this.out = var1;
      return this;
   }

   @Override
   public void flush() {
      this.out.flush();
   }

   @Override
   public void close() {
      this.out.close();
   }

   public int sizeOf(char[] var1, int var2, int var3) {
      throw null;
   }
}
