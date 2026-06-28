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

   public LineReader(InputStream stream) {
      if (stream == null) {
         throw new IllegalArgumentException();
      }

      this._stream = stream;
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

   public void setBufferOffset(int bufferOffset) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setBufferLength(int bufferLength) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int lengthUnreadData() {
      return this._bufferLength - this._bufferOffset;
   }

   public byte[] readLine() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private byte[] concatenate(byte[] original, byte[] newData, int offset, int length) {
      int originalLength = original.length;
      Array.resize(original, original.length + length);
      System.arraycopy(newData, offset, original, originalLength, length);
      return original;
   }
}
