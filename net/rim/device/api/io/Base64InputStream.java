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

   public Base64InputStream(InputStream inputStream) {
      this(inputStream, false);
   }

   public Base64InputStream(InputStream inputStream, boolean treatErrorAsEOF) {
      if (inputStream == null) {
         throw new IllegalArgumentException();
      }

      this._treatErrorAsEOF = treatErrorAsEOF;
      this._exceptionThrown = false;
      this._inputBuffer = new byte[2048];
      this._inputBufferLength = 0;
      this._outputBuffer = new byte[1536];
      this._outputBufferOffset = 0;
      this._outputBufferLength = 0;
      this._inputStream = inputStream;
      this._lastException = null;
   }

   @Override
   public int read() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int read(byte[] buffer) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public int read(byte[] buffer, int bufferOffset, int bufferLength) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int available() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void close() {
      if (this._lastException != null) {
         throw this._lastException;
      }

      try {
         if (this._inputStream != null) {
            this._inputStream.close();
            this._inputBuffer = null;
            this._inputBufferLength = 0;
            this._outputBuffer = null;
            this._outputBufferOffset = 0;
            this._outputBufferLength = 0;
            this._inputStream = null;
         }
      } catch (IOException e) {
         this._lastException = e;
         throw e;
      }
   }

   private int decode(byte[] buffer, int bufferOffset) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static byte[] decode(String input) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static byte[] decode(String input, int inputOffset, int inputLength) {
      return decode(input.getBytes(), inputOffset, inputLength);
   }

   public static byte[] decode(byte[] input, int inputOffset, int inputLength) {
      if (input != null && inputOffset >= 0 && inputLength >= 0 && input.length - inputLength >= inputOffset) {
         byte[] output = new byte[inputLength];
         if (inputOffset > 0) {
            input = Arrays.copy(input, inputOffset, inputLength);
         }

         int outputLength = decode(input, inputLength, output, 0);
         int remainder = outputLength >> 16;
         outputLength &= 65535;
         if (remainder > 0) {
            throw new IOException();
         }

         Array.resize(output, outputLength);
         return output;
      } else {
         throw new IllegalArgumentException();
      }
   }

   private static native int decode(byte[] var0, int var1, byte[] var2, int var3);
}
