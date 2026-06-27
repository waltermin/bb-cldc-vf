package com.sun.cldc.i18n.j2me;

import com.sun.cldc.i18n.StreamWriter;
import java.io.OutputStream;
import java.io.Writer;

public final class Universal_Writer extends StreamWriter {
   private int _enc;
   private byte[] _buf;
   private char[] _cbuf;
   private int[] _charsUsed;
   private char[] _char;
   private byte[] _conversionData;
   private int[] _conversionDataOffset;
   private boolean _noConversionData;
   private int _boundaryChar;
   private static final int BUF_SIZE;

   @Override
   public final Writer open(OutputStream var1, String var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void write(int var1) {
      if (this._noConversionData) {
         this.writeISO(var1);
      } else {
         this._char[0] = (char)var1;
         this.write(this._char, 0, 1);
      }
   }

   @Override
   public final void write(char[] var1, int var2, int var3) {
      if (var2 < 0 || var3 < 0 || var2 + var3 > var1.length) {
         throw new IndexOutOfBoundsException();
      }

      if (this._noConversionData) {
         while (var3-- > 0) {
            this.writeISO(var1[var2++]);
         }
      } else {
         int var4 = 0;
         var3 += var2;

         while (var2 < var3) {
            var4 = Math.min(this._cbuf.length, var3 - var2);
            System.arraycopy(var1, var2, this._cbuf, 0, var4);
            this.writeOut(this._cbuf, 0, var4, this._buf, 0, this._buf.length, true);
            var2 += var4;
         }
      }
   }

   @Override
   public final void write(String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final int sizeOf(char[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void writeOut(char[] var1, int var2, int var3, byte[] var4, int var5, int var6, boolean var7) {
      int var8 = 0;
      int var9 = 0;
      var3 += var2;

      while (var2 < var3) {
         var8 = TranscodingGateway.U2L(
            this._enc, var1, var2, var3 - var2, var4, var5, var6, this._charsUsed, this._conversionData, this._conversionDataOffset[0]
         );
         if (var8 <= 0) {
            break;
         }

         var2 += this._charsUsed[0];
         var9 += var8;
         if (var7) {
            super.out.write(var4, 0, var8);
         }
      }

      this._charsUsed[0] = var9;
   }

   private final void writeISO(int var1) {
      if (var1 > this._boundaryChar) {
         var1 = 63;
      }

      super.out.write(var1);
   }
}
