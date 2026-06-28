package net.rim.device.internal.browser.markup;

import java.io.InputStream;
import java.io.Reader;
import net.rim.device.internal.browser.util.Pipe;

public class MarkupInputStream extends InputStream {
   private InputStream _primaryInput;
   private int _type;
   private MarkupContext _context;
   private Pipe _pipe = new Pipe();
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
      String type,
      String encoding,
      String defaultEncoding,
      InputStream bytes,
      int[] actionStateTable,
      byte[] configurationData,
      String[] tags,
      int[] tagIds,
      int[] tagIndicies,
      String[] entities,
      int[] entityIds,
      int[] entityIndicies,
      String[] attributes,
      int[] attributeIds,
      int[] attributeIndicies,
      String[] attributeValues,
      int[] attributeValueIds,
      int[] attributeValueIndicies
   ) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public MarkupInputStream(int type, InputStream bytes, MarkupContext context, Reader reader, String encoding) {
      this._type = type;
      this._primaryInput = bytes;
      this._context = context;
      this._reader = reader;
      this._encoding = encoding;
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
   public int read(byte[] b, int offset, int length) {
      if (length == 0) {
         return 0;
      }

      int bytesRead = 0;
      int available = 0;

      while (length > 0) {
         available = this._pipeInput.available();
         if (available <= 0 && !this.readNextChunk()) {
            if (bytesRead > 0) {
               return bytesRead;
            }

            return -1;
         }

         int numRead = Math.min(available, length);
         numRead = this._pipeInput.read(b, offset, numRead);
         offset += numRead;
         length -= numRead;
         bytesRead += numRead;
      }

      return bytesRead > 0 ? bytesRead : -1;
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
   public void mark(int limit) {
      this._pipeInput.mark(limit);
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
      throw new RuntimeException("cod2jar: type check");
   }

   private void setNewEncoding(byte[] encoding) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static native int markupDataChunk(int var0, Object var1, int var2, byte[] var3, MarkupContext var4);
}
