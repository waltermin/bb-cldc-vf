package com.sun.cldc.i18n.j2me;

import com.sun.cldc.i18n.StreamReader;
import java.io.IOException;

public final class SMS_Reader extends StreamReader {
   private static char[][][] TABLES;

   @Override
   public final int read() {
      try {
         int table = 0;
         char ch = 0;

         while (true) {
            int index = super.in.read();
            if (index == -1) {
               break;
            }

            index &= 255;
            if (index == 255) {
               return -1;
            }

            if (index != 13) {
               if (index == 27) {
                  if (++table < TABLES.length) {
                     continue;
                  }

                  table--;
               }

               ch = (char)TABLES[table][index];
               if (ch == false) {
                  ch = (char)TABLES[0][index];
               }

               int var5 = false;
               break;
            }
         }

         return ch;
      } catch (ArrayIndexOutOfBoundsException e) {
         throw new IOException("Bad data format");
      }
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
