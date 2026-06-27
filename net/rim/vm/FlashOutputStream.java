package net.rim.vm;

import java.io.OutputStream;

public class FlashOutputStream extends OutputStream {
   private int _bufferSize;
   private byte[] _buffer;
   private int _headerSize;
   private int _index;
   private int _writeIndex;
   private long _guid;
   private boolean _startUserBlock;

   private static native int headerSize();

   private static native int bufferSize();

   private static native void writeBuffer(byte[] var0, int var1, int var2, long var3, boolean var5, boolean var6);

   public FlashOutputStream(long var1) {
      this(var1, false);
   }

   public FlashOutputStream(long var1, boolean var3) {
      FlashInputStream var4 = new FlashInputStream(var1);
      if (var3) {
         this._writeIndex = var4.getNextWriteIndex();
         var4.close();
      } else {
         var4.erase();
      }

      this._headerSize = headerSize();
      this._bufferSize = bufferSize();
      this._buffer = new byte[this._bufferSize];
      this._guid = var1;
      this._index = this._headerSize;
      this._startUserBlock = true;
   }

   @Override
   public void write(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void flush() {
      this.internalFlush(true);
      this._startUserBlock = true;
   }

   private void internalFlush(boolean var1) {
      if (this._index > this._headerSize) {
         writeBuffer(this._buffer, this._index, this._writeIndex, this._guid, this._startUserBlock, var1);
         this._startUserBlock = false;
         this._index = this._headerSize;
         this._writeIndex++;
      }
   }

   @Override
   public void close() {
      this.flush();
   }
}
