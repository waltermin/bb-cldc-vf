package net.rim.device.api.io;

import java.io.IOException;
import java.io.InputStream;
import net.rim.device.api.util.Arrays;
import net.rim.vm.Array;

public class Base64InputStream extends InputStream {
   private byte[] _inputBuffer;
   private int _inputBufferLength;
   private byte[] _outputBuffer;
   private int _outputBufferLength;
   private int _outputBufferOffset;
   private InputStream _inputStream;
   private IOException _lastException;
   private boolean _treatErrorAsEOF;
   private boolean _exceptionThrown;
   private static final int INPUT_BUFFER_LENGTH;
   private static final int OUTPUT_BUFFER_LENGTH;

   public Base64InputStream(InputStream var1) {
      this(var1, false);
   }

   public Base64InputStream(InputStream var1, boolean var2) {
      if (var1 == null) {
         throw new Object();
      }

      this._treatErrorAsEOF = var2;
      this._exceptionThrown = false;
      this._inputBuffer = new byte[2048];
      this._inputBufferLength = 0;
      this._outputBuffer = new byte[1536];
      this._outputBufferOffset = 0;
      this._outputBufferLength = 0;
      this._inputStream = var1;
      this._lastException = null;
   }

   @Override
   public int read() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int read(byte[] var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int available() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private int decode(byte[] var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static byte[] decode(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static byte[] decode(String var0, int var1, int var2) {
      return decode(var0.getBytes(), var1, var2);
   }

   public static byte[] decode(byte[] var0, int var1, int var2) {
      if (var0 != null && var1 >= 0 && var2 >= 0 && var0.length - var2 >= var1) {
         byte[] var3 = new byte[var2];
         if (var1 > 0) {
            var0 = Arrays.copy(var0, var1, var2);
         }

         int var4 = decode(var0, var2, var3, 0);
         int var5 = var4 >> 16;
         var4 &= 65535;
         if (var5 > 0) {
            throw new Object();
         }

         Array.resize(var3, var4);
         return var3;
      } else {
         throw new Object();
      }
   }

   private static native int decode(byte[] var0, int var1, byte[] var2, int var3);
}
