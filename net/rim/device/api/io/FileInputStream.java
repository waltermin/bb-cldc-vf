package net.rim.device.api.io;

import java.io.InputStream;
import net.rim.device.api.system.ApplicationProcess;
import net.rim.vm.Process;

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
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public final synchronized void close() {
      File.close(this._handle);
      this._handle = -1;
      ((ApplicationProcess)Process.currentProcess()).removeCleanupRunnable(this._cleanupRunnable);
   }

   private final void doRead() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
