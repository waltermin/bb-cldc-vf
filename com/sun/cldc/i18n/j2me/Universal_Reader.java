package com.sun.cldc.i18n.j2me;

import com.sun.cldc.i18n.StreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import net.rim.device.api.util.StringUtilities;

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
   public final Reader open(InputStream in, String enc) {
      if (enc == null) {
         return null;
      }

      if (!StringUtilities.strEqualIgnoreCase(enc, TranscodingGateway.ISO8859_1, 1701707776)
         && !StringUtilities.strEqualIgnoreCase(enc, TranscodingGateway.ASCII, 1701707776)) {
         TextProcessingRegistry txtRg = TextProcessingRegistry.getInstance();
         this._enc = txtRg.getTextProcessingDataID(enc, 0);
         if (this._enc == -1) {
            throw new UnsupportedEncodingException("Encoding " + enc + " is not suported!");
         }

         byte[][][] bData = (byte[][][])txtRg.getTextProcessingData(this._enc, 0, this._conversionDataOffset);
         if (bData != null && bData.length > 0) {
            this._conversionData = (byte[])bData[0];
         }
      } else {
         this._noConversionData = true;
      }

      super.in = in;
      int len = this._noConversionData ? 1 : 1024;
      if (this._buf == null || this._buf.length < len) {
         this._buf = new byte[len];
      }

      this._bytesUsed[0] = 1048574;
      return this;
   }

   @Override
   public final int read() {
      return this.read(this._char, 0, 1) > 0 ? this._char[0] : -1;
   }

   @Override
   public final int read(char[] cbuf, int offset, int length) {
      if (offset >= 0 && length >= 0 && offset + length <= cbuf.length) {
         int count = 0;
         if (this._noConversionData) {
            while (count < length) {
               int ch = super.in.read();
               if (ch == -1) {
                  if (count == 0) {
                     return -1;
                  }

                  return count;
               }

               cbuf[offset++] = (char)ch;
               count++;
               this._streamPos += 1;
            }

            return length;
         } else {
            return this.read(super.in, 0, Integer.MAX_VALUE, cbuf, offset, length);
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public final boolean ready() {
      if (super.in == null) {
         return false;
      }

      try {
         return super.in.available() <= 0 ? this._size - this._pos > 0 : true;
      } catch (IOException x) {
         return this._size - this._pos > 0;
      }
   }

   @Override
   public final void reset() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final boolean markSupported() {
      return super.in != null ? super.in.markSupported() : false;
   }

   @Override
   public final void mark(int readAheadLimit) {
      if (!this.markSupported()) {
         throw new IOException("mark() not supported");
      }

      this._markPos = this._streamPos;
   }

   @Override
   public final void close() {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   @Override
   public final int sizeOf(byte[] cbuf, int offset, int length) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final int read(Object in, int inOffset, int inLength, Object cbuf, int offset, int length) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final Object byteToCharArray(int encId, byte[] buffer, int offset, int length, String enc) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
