package java.lang;

import java.io.OutputStream;

final class DebugOutputStream extends OutputStream {
   private byte[] _c = new byte[129];
   private int _size;
   private static final int BUFF_SIZE;

   @Override
   public final synchronized void write(int var1) {
      int var2 = this._size;
      this._c[var2++] = (byte)var1;
      if (var2 >= 128 || var1 == 10) {
         this._c[var2] = 0;
         printInternal(this._c);
         var2 = 0;
      }

      this._size = var2;
   }

   private static final native void printInternal(byte[] var0);
}
