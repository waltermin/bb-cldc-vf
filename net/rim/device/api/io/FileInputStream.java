package net.rim.device.api.io;

import java.io.InputStream;

public final class FileInputStream extends InputStream {
   private FileEventDispatcher _dispatcher;
   private int _handle;
   private byte[] _buffer;
   private int _offset;
   private int _available;
   private boolean _eof;
   private Runnable _cleanupRunnable;

   public FileInputStream(int fs, String fileName) {
   }

   @Override
   public final synchronized int read() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final synchronized int read(byte[] b, int off, int len) {
      if (off >= 0 && len >= 0 && b.length - len >= off) {
         int bytesCopied = 0;

         while (bytesCopied < len) {
            if (this._available == 0) {
               this.doRead();
               if (this._available == 0) {
                  if (bytesCopied == 0) {
                     return -1;
                  }

                  return bytesCopied;
               }
            }

            int bytesToCopy = Math.min(this._available, len - bytesCopied);
            System.arraycopy(this._buffer, this._offset, b, off + bytesCopied, bytesToCopy);
            bytesCopied += bytesToCopy;
            this._available -= bytesToCopy;
            this._offset += bytesToCopy;
         }

         return bytesCopied;
      } else {
         throw new Object();
      }
   }

   @Override
   public final synchronized void close() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final void doRead() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
