package net.rim.device.internal.media;

import java.io.InputStream;

class HTTPBufferingManager$HTTPBufferedInputStream extends InputStream {
   private final byte[] readBuffer;
   private boolean _closed;
   private int _markingPos;
   private boolean _skipping;
   private final HTTPBufferingManager this$0;

   HTTPBufferingManager$HTTPBufferedInputStream(HTTPBufferingManager _1) {
      this.this$0 = _1;
      this.readBuffer = new byte[1];
   }

   @Override
   public void mark(int readlimit) {
      this._markingPos = this.this$0._readOffset;
   }

   @Override
   public int available() {
      return this.this$0._dataLength;
   }

   @Override
   public void reset() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean markSupported() {
      return this.this$0._totalInputLength <= 1048576;
   }

   @Override
   public long skip(long n) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int read() {
      synchronized (this.this$0._lock) {
         int read = this.read(this.readBuffer, 0, 1);
         return read > 0 ? this.readBuffer[0] & 0xFF : -1;
      }
   }

   @Override
   public int read(byte[] buffer, int start, int length) {
      if (this._closed) {
         return -1;
      }

      synchronized (this.this$0._lock) {
         while (this.this$0._dataLength == 0) {
            if (this.this$0._shutdown || this._closed) {
               return -1;
            }

            try {
               this.this$0._lock.wait();
            } catch (InterruptedException var8) {
            }
         }

         int bytesRead = Math.min(length, this.this$0._dataLength);
         int len1 = Math.min(bytesRead, this.this$0._buffer.length - this.this$0._readOffset);
         if (!this._skipping) {
            this.arrayCopy(this.this$0._buffer, this.this$0._readOffset, buffer, start, len1);
         }

         HTTPBufferingManager.access$512(this.this$0, len1);
         if (len1 < bytesRead) {
            start += len1;
            this.this$0._readOffset = 0;
            len1 = bytesRead - len1;
            if (!this._skipping) {
               this.arrayCopy(this.this$0._buffer, this.this$0._readOffset, buffer, start, len1);
            }

            HTTPBufferingManager.access$512(this.this$0, len1);
         }

         if (this.this$0._readOffset == this.this$0._buffer.length) {
            this.this$0._readOffset = 0;
         }

         synchronized (this.this$0._lock) {
            HTTPBufferingManager.access$620(this.this$0, bytesRead);
            this.this$0._lock.notifyAll();
         }

         return bytesRead;
      }
   }

   private void arrayCopy(byte[] src, int srcIndex, byte[] dest, int destIndex, int length) {
      if (length == 1) {
         dest[destIndex] = src[srcIndex];
      } else {
         System.arraycopy(src, srcIndex, dest, destIndex, length);
      }
   }

   @Override
   public void close() {
      this._closed = true;
      synchronized (this.this$0._lock) {
         this.this$0._lock.notifyAll();
      }
   }
}
