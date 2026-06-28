package com.sun.cldc.i18n.j2me;

import com.sun.cldc.i18n.StreamReader;

public final class SMS_Reader extends StreamReader {
   private static char[][][] TABLES;

   @Override
   public final int read() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final int read(char[] cbuf, int off, int len) {
      for (int count = 0; count < len; count++) {
         int ch = this.read();
         if (ch == -1) {
            if (count == 0) {
               return -1;
            }

            return count;
         }

         cbuf[off++] = (char)ch;
      }

      return len;
   }

   @Override
   public final int sizeOf(byte[] array, int offset, int length) {
      int count = 0;
      int end = offset + length;

      while (offset < end) {
         int ch = array[offset] & 255;
         offset++;
         if (ch != 27 && ch != 13) {
            if (ch == 255) {
               break;
            }

            count++;
         }
      }

      return count;
   }
}
