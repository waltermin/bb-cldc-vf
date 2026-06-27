package com.sun.cldc.i18n.j2me;

import com.sun.cldc.i18n.StreamReader;
import java.io.InputStream;
import java.io.Reader;

public final class Universal_Reader extends StreamReader {
   private int _enc = -1;
   private int _pos;
   private int _size;
   private byte[] _buf;
   private int[] _bytesUsed = new int[2];
   private char[] _char = new char[1];
   private boolean _tryToReadMore;
   private byte[] _conversionData;
   private int[] _conversionDataOffset = new int[1];
   private long _streamPos;
   private long _markPos;
   private boolean _noConversionData;
   private static final int BUF_SIZE;

   @Override
   public final Reader open(InputStream var1, String var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final int read() {
      return this.read(this._char, 0, 1) > 0 ? this._char[0] : -1;
   }

   @Override
   public final int read(char[] var1, int var2, int var3) {
      if (var2 >= 0 && var3 >= 0 && var2 + var3 <= var1.length) {
         int var4 = 0;
         if (this._noConversionData) {
            while (var4 < var3) {
               int var5 = super.in.read();
               if (var5 == -1) {
                  if (var4 == 0) {
                     return -1;
                  }

                  return var4;
               }

               var1[var2++] = (char)var5;
               var4++;
               this._streamPos += 1;
            }

            return var3;
         } else {
            return this.read(super.in, 0, Integer.MAX_VALUE, var1, var2, var3);
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public final boolean ready() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void reset() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final boolean markSupported() {
      return super.in != null ? super.in.markSupported() : false;
   }

   @Override
   public final void mark(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int sizeOf(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final int read(Object var1, int var2, int var3, Object var4, int var5, int var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final Object byteToCharArray(int var1, byte[] var2, int var3, int var4, String var5) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
