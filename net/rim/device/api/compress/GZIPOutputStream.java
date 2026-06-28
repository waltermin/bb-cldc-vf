package net.rim.device.api.compress;

import java.io.IOException;
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

   public GZIPOutputStream(OutputStream outputStream) {
      this(outputStream, 0);
   }

   public GZIPOutputStream(OutputStream outputStream, int compressionValue) {
      this(outputStream, compressionValue, 8);
   }

   public GZIPOutputStream(OutputStream outputStream, int compressionValue, int windowLength) {
      if (outputStream != null && compressionValue >= 0 && compressionValue <= 9 && windowLength >= 8 && windowLength <= 15) {
         this._outputStream = outputStream;
         this._buffer = new byte[1024];
         this._deflater = new Deflater(compressionValue, 0, windowLength + 16);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public synchronized void write(int data) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public synchronized void write(byte[] data, int dataOffset, int dataLength) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public synchronized void flush() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void close() {
      if (this._outputStream == null) {
         throw new IOException();
      }

      this._outputStream.write(this._deflater.compress(this._buffer, 0, this._bufferOffset, 4));
      this._bufferOffset = 0;
      this._outputStream.close();
      this._outputStream = null;
   }
}
