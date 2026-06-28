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

   public Base64OutputStream(OutputStream outputStream) {
      this(outputStream, false, false);
   }

   public Base64OutputStream(OutputStream outputStream, boolean insertCR, boolean insertLF) {
      if (outputStream == null) {
         throw new Object();
      }

      this._inputBuffer = new byte[1539];
      this._inputBufferLength = 0;
      this._outputBuffer = new byte[2106];
      this._insertCR = insertCR;
      this._insertLF = insertLF;
      this._outputStream = outputStream;
      this._lastException = null;
   }

   @Override
   public void write(int data) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void write(byte[] data) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public void write(byte[] data, int dataOffset, int dataLength) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void flush() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void close() {
      if (this._lastException != null) {
         throw this._lastException;
      }

      try {
         if (this._outputStream != null) {
            this.encode(this._inputBuffer, 0, this._inputBufferLength);
            this._outputStream.close();
            this._inputBuffer = null;
            this._inputBufferLength = 0;
            this._outputBuffer = null;
            this._outputStream = null;
         }
      } catch (IOException e) {
         this._lastException = e;
         throw e;
      }
   }

   private void encode(byte[] input, int inputOffset, int inputLength) {
      int outputBufferLength = encode(input, inputOffset, inputLength, this._outputBuffer, this._insertCR, this._insertLF);
      this._outputStream.write(this._outputBuffer, 0, outputBufferLength);
   }

   public static String encodeAsString(byte[] input, int inputOffset, int inputLength, boolean insertCR, boolean insertLF) {
      return CompressUtilities.convertToString(encode(input, inputOffset, inputLength, insertCR, insertLF));
   }

   public static byte[] encode(byte[] input, int inputOffset, int inputLength, boolean insertCR, boolean insertLF) {
      if (input != null && inputOffset >= 0 && inputLength >= 0 && input.length - inputLength >= inputOffset) {
         int outLength = (inputLength + 2) / 3 * 4;
         if (insertCR || insertLF) {
            outLength += outLength / 75 * 2 + 2;
         }

         byte[] output = new byte[outLength];
         outLength = encode(input, inputOffset, inputLength, output, insertCR, insertLF);
         if (outLength != output.length) {
            Array.resize(output, outLength);
         }

         return output;
      } else {
         throw new Object();
      }
   }

   private static native int encode(byte[] var0, int var1, int var2, byte[] var3, boolean var4, boolean var5);
}
