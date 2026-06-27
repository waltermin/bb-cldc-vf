package net.rim.device.api.compress;

import java.io.OutputStream;
import net.rim.device.internal.compress.Deflater;

public class ZLibOutputStream extends OutputStream {
   private OutputStream _outputStream;
   private Deflater _deflater;
   private byte[] _buffer;
   private int _bufferOffset;
   public static final int MIN_LOG2_WINDOW_LENGTH;
   public static final int MAX_LOG2_WINDOW_LENGTH;
   public static final int COMPRESSION_NONE;
   public static final int COMPRESSION_BEST;
   private static final int BUFFER_LENGTH;

   public ZLibOutputStream(OutputStream var1) {
      this(var1, false);
   }

   public ZLibOutputStream(OutputStream var1, boolean var2) {
      this(var1, var2, 8);
   }

   public ZLibOutputStream(OutputStream var1, boolean var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public ZLibOutputStream(OutputStream var1, boolean var2, int var3, int var4) {
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
