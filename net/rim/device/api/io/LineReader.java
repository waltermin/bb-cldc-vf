package net.rim.device.api.io;

import java.io.InputStream;
import net.rim.vm.Array;

public class LineReader {
   protected InputStream _stream;
   protected byte[] _buffer;
   protected int _bufferOffset;
   protected int _bufferLength;
   protected static final int BUFFER_LENGTH;
   private static final byte CR;
   private static final byte LF;

   public LineReader(InputStream var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._stream = var1;
      this._buffer = new byte[1024];
      this._bufferOffset = 0;
      this._bufferLength = 0;
   }

   public InputStream getStream() {
      return this._stream;
   }

   public byte[] getBuffer() {
      return this._buffer;
   }

   public int getBufferOffset() {
      return this._bufferOffset;
   }

   public int getBufferLength() {
      return this._bufferLength;
   }

   public void setBufferOffset(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setBufferLength(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int lengthUnreadData() {
      return this._bufferLength - this._bufferOffset;
   }

   public byte[] readLine() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private byte[] concatenate(byte[] var1, byte[] var2, int var3, int var4) {
      int var5 = var1.length;
      Array.resize(var1, var1.length + var4);
      System.arraycopy(var2, var3, var1, var5, var4);
      return var1;
   }
}
