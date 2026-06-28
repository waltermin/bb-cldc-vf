package com.sun.cldc.i18n.j2me;

import com.sun.cldc.i18n.StreamWriter;
import java.io.IOException;
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
   public final Writer open(OutputStream out, String enc) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void write(int c) {
      if (this._noConversionData) {
         this.writeISO(c);
      } else {
         this._char[0] = (char)c;
         this.write(this._char, 0, 1);
      }
   }

   @Override
   public final void write(char[] cbuf, int offset, int length) {
      if (offset < 0 || length < 0 || offset + length > cbuf.length) {
         throw new IndexOutOfBoundsException();
      }

      if (this._noConversionData) {
         while (length-- > 0) {
            this.writeISO(cbuf[offset++]);
         }
      } else {
         int size = 0;
         length += offset;

         while (offset < length) {
            size = Math.min(this._cbuf.length, length - offset);
            System.arraycopy(cbuf, offset, this._cbuf, 0, size);
            this.writeOut(this._cbuf, 0, size, this._buf, 0, this._buf.length, true);
            offset += size;
         }
      }
   }

   @Override
   public final void write(String str, int offset, int length) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final int sizeOf(char[] cbuf, int offset, int length) {
      if (offset < 0 || length < 0 || offset + length > cbuf.length) {
         throw new IndexOutOfBoundsException();
      }

      if (this._noConversionData) {
         return length;
      }

      int ret = TranscodingGateway.sizeOf(this._enc, length, Integer.MAX_VALUE, false);
      if (ret < 0) {
         ret = 0;
         int size = 0;
         length += offset;

         while (offset < length) {
            size = Math.min(this._cbuf.length, length - offset);
            System.arraycopy(cbuf, offset, this._cbuf, 0, size);

            try {
               this.writeOut(this._cbuf, 0, size, null, 0, Integer.MAX_VALUE, false);
            } catch (IOException ioe) {
               return -1;
            }

            ret += this._charsUsed[0];
            offset += size;
         }
      }

      return ret;
   }

   private final void writeOut(char[] cbuf, int offset, int length, byte[] buf, int bufOffset, int bufLength, boolean writeOut) {
      int converted = 0;
      int convertedTotal = 0;
      length += offset;

      while (offset < length) {
         converted = TranscodingGateway.U2L(
            this._enc, cbuf, offset, length - offset, buf, bufOffset, bufLength, this._charsUsed, this._conversionData, this._conversionDataOffset[0]
         );
         if (converted <= 0) {
            break;
         }

         offset += this._charsUsed[0];
         convertedTotal += converted;
         if (writeOut) {
            super.out.write(buf, 0, converted);
         }
      }

      this._charsUsed[0] = convertedTotal;
   }

   private final void writeISO(int c) {
      if (c > this._boundaryChar) {
         c = 63;
      }

      super.out.write(c);
   }
}
