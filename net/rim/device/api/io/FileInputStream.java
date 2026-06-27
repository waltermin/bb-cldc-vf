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

   public FileInputStream(int var1, String var2) {
   }

   @Override
   public final synchronized int read() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final synchronized int read(byte[] var1, int var2, int var3) {
      if (var2 >= 0 && var3 >= 0 && var1.length - var3 >= var2) {
         int var4 = 0;

         while (var4 < var3) {
            if (this._available == 0) {
               this.doRead();
               if (this._available == 0) {
                  if (var4 == 0) {
                     return -1;
                  }

                  return var4;
               }
            }

            int var5 = Math.min(this._available, var3 - var4);
            System.arraycopy(this._buffer, this._offset, var1, var2 + var4, var5);
            var4 += var5;
            this._available -= var5;
            this._offset += var5;
         }

         return var4;
      } else {
         throw new Object();
      }
   }

   @Override
   public final synchronized void close() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final void doRead() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
