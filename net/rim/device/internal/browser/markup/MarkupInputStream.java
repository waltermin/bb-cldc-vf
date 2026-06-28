package net.rim.device.internal.browser.markup;

import com.sun.cldc.i18n.Helper;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import net.rim.device.api.util.StringUtilities;
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
      int intType = 0;
      if (StringUtilities.strEqualIgnoreCase(type, "text/html", 1701707776)
         || StringUtilities.strEqualIgnoreCase(type, "application/vnd.wap.xhtml+xml", 1701707776)
         || StringUtilities.strEqualIgnoreCase(type, "application/xhtml+xml", 1701707776)
         || StringUtilities.strEqualIgnoreCase(type, "application/vnd.wap.wml+xml", 1701707776)) {
         intType = 1;
      } else if (StringUtilities.strEqualIgnoreCase(type, "text/plain", 1701707776)) {
         intType = 2;
      } else if (StringUtilities.strEqualIgnoreCase(type, "text/vnd.wap.wml", 1701707776)) {
         intType = 3;
      } else if (StringUtilities.strEqualIgnoreCase(type, "text/vnd.wap.si", 1701707776)
         || StringUtilities.strEqualIgnoreCase(type, "text/vnd.wap.sl", 1701707776)
         || StringUtilities.strEqualIgnoreCase(type, "text/vnd.wap.co", 1701707776)) {
         intType = 4;
      }

      boolean guessEncoding = false;
      int intEncoding = 1;
      Reader reader = null;
      if (encoding != null) {
         if (StringUtilities.compareToIgnoreCase(encoding, "iso-8859-1", 1701707776) == 0
            || StringUtilities.compareToIgnoreCase(encoding, "us-ascii", 1701707776) == 0) {
            intEncoding = 1;
         } else if (StringUtilities.compareToIgnoreCase(encoding, "utf-8", 1701707776) == 0) {
            intEncoding = 2;
         } else {
            try {
               reader = Helper.getStreamReader(bytes, encoding);
            } catch (UnsupportedEncodingException e) {
               guessEncoding = true;
            }
         }
      } else {
         guessEncoding = true;
         if (defaultEncoding != null) {
            if (StringUtilities.compareToIgnoreCase(defaultEncoding, "iso-8859-1", 1701707776) == 0
               || StringUtilities.compareToIgnoreCase(defaultEncoding, "us-ascii", 1701707776) == 0) {
               intEncoding = 1;
            } else if (StringUtilities.compareToIgnoreCase(defaultEncoding, "utf-8", 1701707776) == 0) {
               intEncoding = 2;
            } else {
               try {
                  reader = Helper.getStreamReader(bytes, defaultEncoding);
               } catch (UnsupportedEncodingException var23) {
               }
            }
         }
      }

      if (!bytes.markSupported()) {
         guessEncoding = false;
      } else {
         bytes.mark(Integer.MAX_VALUE);
      }

      MarkupContext context = new MarkupContext();
      context._actionStateTable = actionStateTable;
      context._attributeIds = attributeIds;
      context._attributeIndicies = attributeIndicies;
      context._attributes = attributes;
      context._attributeValueIds = attributeValueIds;
      context._attributeValueIndicies = attributeValueIndicies;
      context._attributeValues = attributeValues;
      context._configurationData = configurationData;
      context._entities = entities;
      context._entityIds = entityIds;
      context._entityIndicies = entityIndicies;
      context._tagIds = tagIds;
      context._tagIndicies = tagIndicies;
      context._tags = tags;
      context._inputEncoding = intEncoding;
      context._guessEncoding = guessEncoding;
      return new MarkupInputStream(intType, bytes, context, reader, encoding);
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
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private void setNewEncoding(byte[] encoding) {
      this._primaryInput.reset();
      this._encoding = new String(encoding);
      this._context.reset();
      this._context._firstBlock = true;
      this._context._lastBlock = false;
      this._context._guessEncoding = false;
      this._context._newEncodingType = encoding;
      this._unconsumedData = null;
      this._setReader = false;
      if (!StringUtilities.strEqualIgnoreCase(this._encoding, "iso-8859-1", 1701707776)) {
         if (!StringUtilities.strEqualIgnoreCase(this._encoding, "us-ascii", 1701707776)) {
            if (StringUtilities.strEqualIgnoreCase(this._encoding, "utf-8", 1701707776)) {
               this._context._inputEncoding = 2;
            } else {
               try {
                  this._reader = Helper.getStreamReader(this._primaryInput, this._encoding);
               } catch (UnsupportedEncodingException var3) {
               }
            }
         }
      }
   }

   private static native int markupDataChunk(int var0, Object var1, int var2, byte[] var3, MarkupContext var4);
}
