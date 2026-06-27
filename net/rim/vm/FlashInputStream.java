package net.rim.vm;

import java.io.InputStream;

public class FlashInputStream extends InputStream {
   private Object _stream;
   private byte[] _buffer;
   private int _totalSize;
   private int _amountRead;
   private int _bytesInBuffer;
   private int _index;
   private int _handleIndex;

   private static native Object open(long var0);

   private static native int bufferSize();

   private static native int readBuffer(Object var0, byte[] var1, int var2);

   private static native int totalSize(Object var0);

   private static native int getNextWriteIndex(Object var0);

   private static native int getNumHandles(Object var0);

   private static native boolean purge(Object var0, int var1);

   private static native void erase(Object var0);

   public FlashInputStream(long var1) {
      int var3 = bufferSize();
      this._buffer = new byte[var3];
      this._stream = open(var1);
      this._totalSize = totalSize(this._stream);
      this._handleIndex = -1;
   }

   @Override
   public int read() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   int getNumHandles() {
      return getNumHandles(this._stream);
   }

   int getNextWriteIndex() {
      return getNextWriteIndex(this._stream);
   }

   public void erase() {
      erase(this._stream);
   }

   public void purge() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int available() {
      return this._totalSize - this._amountRead;
   }

   @Override
   public void close() {
   }

   public static boolean exists(long var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void erase(long var0) {
      FlashInputStream var2 = new FlashInputStream(var0);
      var2.erase();
      var2.close();
   }
}
