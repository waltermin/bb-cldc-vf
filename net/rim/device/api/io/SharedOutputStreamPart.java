package net.rim.device.api.io;

import java.io.OutputStream;

class SharedOutputStreamPart extends OutputStream {
   private int _index;
   private SharedOutputStream _sink;
   private byte[] _buffer;
   private NoCopyByteArrayOutputStream _stream;
   private boolean _writable;

   public SharedOutputStreamPart(SharedOutputStream var1, int var2) {
      this._index = var2;
      this._sink = var1;
      this._buffer = null;
      this._stream = new NoCopyByteArrayOutputStream();
      this._writable = true;
   }

   @Override
   public void write(int var1) {
      if (this._buffer == null) {
         this._buffer = new byte[1];
      }

      this._buffer[0] = (byte)var1;
      this.write(this._buffer, 0, 1);
   }

   @Override
   public void write(byte[] var1) {
      this.write(var1, 0, var1.length);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      if (var2 >= 0 && var3 >= 0 && var1.length - var3 >= var2) {
         this._sink.write(this._index, var1, var2, var3);
      } else {
         throw new Object();
      }
   }

   @Override
   public void flush() {
      this._sink.flush(this._index);
   }

   @Override
   public void close() {
      this._sink.close(this._index);
   }

   public NoCopyByteArrayOutputStream getStream() {
      return this._stream;
   }

   public void setStream(NoCopyByteArrayOutputStream var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean isWritable() {
      return this._writable;
   }

   public void setIsWritable(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
