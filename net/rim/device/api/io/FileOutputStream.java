package net.rim.device.api.io;

import java.io.OutputStream;
import net.rim.device.api.system.ApplicationProcess;
import net.rim.vm.Process;

public final class FileOutputStream extends OutputStream {
   private FileEventDispatcher _dispatcher;
   private int _handle;
   private byte[] _buffer;
   private int _offset;
   private int _available;
   private Runnable _cleanupRunnable;

   public FileOutputStream(int fs, String fileName) {
   }

   @Override
   public final synchronized void write(int b) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final synchronized void write(byte[] b, int off, int len) {
      if (off >= 0 && len >= 0 && b.length - len >= off) {
         int bytesCopied = 0;

         while (bytesCopied < len) {
            if (this._available == 0) {
               this.flush();
            }

            int bytesToCopy = Math.min(this._available, len - bytesCopied);
            System.arraycopy(b, off + bytesCopied, this._buffer, this._offset, bytesToCopy);
            bytesCopied += bytesToCopy;
            this._available -= bytesToCopy;
            this._offset += bytesToCopy;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public final synchronized void flush() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final synchronized void close() {
      this.flush();
      File.close(this._handle);
      this._handle = -1;
      ((ApplicationProcess)Process.currentProcess()).removeCleanupRunnable(this._cleanupRunnable);
   }
}
