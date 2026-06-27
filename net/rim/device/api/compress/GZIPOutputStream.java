package net.rim.device.api.compress;

import java.io.OutputStream;
import net.rim.device.internal.compress.Deflater;

public class GZIPOutputStream extends OutputStream {
   private OutputStream _outputStream;
   private Deflater _deflater;
   private byte[] _buffer;
   private int _bufferOffset;
   public static final int MIN_LOG2_WINDOW_LENGTH;
   public static final int MAX_LOG2_WINDOW_LENGTH;
   public static final int COMPRESSION_NONE;
   public static final int COMPRESSION_BEST;
   private static final int BUFFER_LENGTH;

   public GZIPOutputStream(OutputStream var1) {
      this(var1, 0);
   }

   public GZIPOutputStream(OutputStream var1, int var2) {
      this(var1, var2, 8);
   }

   public GZIPOutputStream(OutputStream var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var2 <= 9 && var3 >= 8 && var3 <= 15) {
         this._outputStream = var1;
         this._buffer = new byte[1024];
         this._deflater = (Deflater)(new Object(var2, 0, var3 + 16));
      } else {
         throw new Object();
      }
   }

   @Override
   public synchronized void write(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public synchronized void write(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public synchronized void flush() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void close() {
      if (this._outputStream == null) {
         throw new Object();
      }

      this._outputStream.write(this._deflater.compress(this._buffer, 0, this._bufferOffset, 4));
      this._bufferOffset = 0;
      this._outputStream.close();
      this._outputStream = null;
   }
}
