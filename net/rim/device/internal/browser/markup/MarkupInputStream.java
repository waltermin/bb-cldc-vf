package net.rim.device.internal.browser.markup;

import java.io.InputStream;
import java.io.Reader;
import net.rim.device.internal.browser.util.Pipe;

public class MarkupInputStream extends InputStream {
   private InputStream _primaryInput;
   private int _type;
   private MarkupContext _context;
   private Pipe _pipe = (Pipe)(new Object());
   private int _currentPacketNo;
   private String _encoding;
   private Reader _reader;
   private Object _unconsumedData;
   private boolean _setReader = true;
   private InputStream _pipeInput = this._pipe.getInputStream();
   private static final int READ_BUFFER_SIZE;
   private static final int MARKUP_BEFORE;
   private static final int MARKUP_AFTER;
   public static final int CONFIG_DATA_JAVASCRIPT_ENABLED_INDEX;
   public static final int CONFIG_DATA_CSS_ENABLED_INDEX;
   public static final int CONFIG_DATA_SIZE;
   private static final int UNKNOWN;
   private static final int HTML;
   private static final int TEXT;
   private static final int WML_1x_TEXT;
   private static final int WBXML;
   static final int SINGLE_UNIT;
   static final int UTF8;

   public static MarkupInputStream getConvertedInputStream(
      String var0,
      String var1,
      String var2,
      InputStream var3,
      int[] var4,
      byte[] var5,
      String[] var6,
      int[] var7,
      int[] var8,
      String[] var9,
      int[] var10,
      int[] var11,
      String[] var12,
      int[] var13,
      int[] var14,
      String[] var15,
      int[] var16,
      int[] var17
   ) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public MarkupInputStream(int var1, InputStream var2, MarkupContext var3, Reader var4, String var5) {
      this._type = var1;
      this._primaryInput = var2;
      this._context = var3;
      this._reader = var4;
      this._encoding = var5;
   }

   public Pipe getPipe() {
      return this._pipe;
   }

   public InputStream getPrimaryInput() {
      return this._primaryInput;
   }

   @Override
   public int read() {
      return this._pipeInput.available() <= 0 && !this.readNextChunk() ? -1 : this._pipeInput.read();
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      if (var3 == 0) {
         return 0;
      }

      int var4 = 0;
      int var5 = 0;

      while (var3 > 0) {
         var5 = this._pipeInput.available();
         if (var5 <= 0 && !this.readNextChunk()) {
            if (var4 > 0) {
               return var4;
            }

            return -1;
         }

         int var6 = Math.min(var5, var3);
         var6 = this._pipeInput.read(var1, var2, var6);
         var2 += var6;
         var3 -= var6;
         var4 += var6;
      }

      return var4 > 0 ? var4 : -1;
   }

   @Override
   public int available() {
      return this._primaryInput.available() + this._pipeInput.available();
   }

   @Override
   public boolean markSupported() {
      return this._pipeInput.markSupported();
   }

   @Override
   public void mark(int var1) {
      this._pipeInput.mark(var1);
   }

   @Override
   public void reset() {
      this._pipeInput.reset();
   }

   @Override
   public void close() {
      this._pipe.closeWrite();
      this._primaryInput.close();
   }

   private boolean readNextChunk() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void setNewEncoding(byte[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static native int markupDataChunk(int var0, Object var1, int var2, byte[] var3, MarkupContext var4);
}
