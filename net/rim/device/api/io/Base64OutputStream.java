package net.rim.device.api.io;

import java.io.IOException;
import java.io.OutputStream;
import net.rim.device.internal.compress.CompressUtilities;
import net.rim.vm.Array;

public class Base64OutputStream extends OutputStream {
   private byte[] _inputBuffer;
   private int _inputBufferLength;
   private byte[] _outputBuffer;
   private boolean _insertCR;
   private boolean _insertLF;
   private OutputStream _outputStream;
   private IOException _lastException;
   private static final int INPUT_BUFFER_LENGTH;
   private static final int OUTPUT_BUFFER_LENGTH;

   public Base64OutputStream(OutputStream var1) {
      this(var1, false, false);
   }

   public Base64OutputStream(OutputStream var1, boolean var2, boolean var3) {
      if (var1 == null) {
         throw new Object();
      }

      this._inputBuffer = new byte[1539];
      this._inputBufferLength = 0;
      this._outputBuffer = new byte[2106];
      this._insertCR = var2;
      this._insertLF = var3;
      this._outputStream = var1;
      this._lastException = null;
   }

   @Override
   public void write(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void write(byte[] var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void flush() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void encode(byte[] var1, int var2, int var3) {
      int var4 = encode(var1, var2, var3, this._outputBuffer, this._insertCR, this._insertLF);
      this._outputStream.write(this._outputBuffer, 0, var4);
   }

   public static String encodeAsString(byte[] var0, int var1, int var2, boolean var3, boolean var4) {
      return CompressUtilities.convertToString(encode(var0, var1, var2, var3, var4));
   }

   public static byte[] encode(byte[] var0, int var1, int var2, boolean var3, boolean var4) {
      if (var0 != null && var1 >= 0 && var2 >= 0 && var0.length - var2 >= var1) {
         int var5 = (var2 + 2) / 3 * 4;
         if (var3 || var4) {
            var5 += var5 / 75 * 2 + 2;
         }

         byte[] var6 = new byte[var5];
         var5 = encode(var0, var1, var2, var6, var3, var4);
         if (var5 != var6.length) {
            Array.resize(var6, var5);
         }

         return var6;
      } else {
         throw new Object();
      }
   }

   private static native int encode(byte[] var0, int var1, int var2, byte[] var3, boolean var4, boolean var5);
}
