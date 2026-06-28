package net.rim.device.internal.browser.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import net.rim.device.api.crypto.RandomSource;

public final class IdEncryptor {
   private static final int VERSION;
   private static final int BLOCK_LEN;
   private static final int MODULUS_BIT_LENGTH;
   private static final int MODULUS_LENGTH;
   private static final byte[] RSA_E;
   private static final byte[] RSA_N_0;
   private static final byte[] RSA_N_1;

   private IdEncryptor() {
   }

   public static final String encrypt(String value, int key) {
      throw new RuntimeException("cod2jar: unsupported opcode");
   }

   private static final void formatAndEncrypt(byte[] n, byte[] temp, byte[] input, int inputOffset, int inputLength, byte[] output, int outputOffset) {
      if (input != null
         && inputOffset >= 0
         && inputLength >= 0
         && inputOffset + inputLength <= input.length
         && output != null
         && outputOffset >= 0
         && outputOffset + 128 <= output.length
         && inputLength <= 100) {
         int encodedMessageOffset = 0;
         temp[encodedMessageOffset++] = 0;
         temp[encodedMessageOffset++] = 2;
         int randomDataLength = temp.length - (3 + inputLength);
         RandomSource.getBytes(temp, encodedMessageOffset, randomDataLength);

         for (int i = randomDataLength; i > 0; i--) {
            if (temp[encodedMessageOffset++] == 0) {
               byte randomByte;
               do {
                  randomByte = (byte)RandomSource.getInt();
               } while (randomByte == 0);

               temp[encodedMessageOffset - 1] = randomByte;
            }
         }

         temp[encodedMessageOffset++] = 0;
         System.arraycopy(input, inputOffset, temp, encodedMessageOffset, inputLength);
         nativeRSAPublicKeyOperation(1024, RSA_E, n, temp, 0, output, outputOffset);
      } else {
         throw new Object();
      }
   }

   private static final native void nativeRSAPublicKeyOperation(int var0, byte[] var1, byte[] var2, byte[] var3, int var4, byte[] var5, int var6);

   public static final byte[] getPGPPublicKey() {
      return getPGPPublicKey(RSA_E, RSA_N_0);
   }

   private static final byte[] getPGPPublicKey(byte[] e, byte[] n) {
      try {
         ByteArrayOutputStream bytesOut = (ByteArrayOutputStream)(new Object());
         bytesOut.write(3);
         long time = System.currentTimeMillis() / 1000;
         bytesOut.write((int)(time >> 24) & 0xFF);
         bytesOut.write((int)(time >> 16) & 0xFF);
         bytesOut.write((int)(time >> 8) & 0xFF);
         bytesOut.write((int)time & 0xFF);
         bytesOut.write(1);
         int length = n.length << 3;
         bytesOut.write(length >> 8);
         bytesOut.write(length);
         bytesOut.write(n);
         length = e.length << 3;
         bytesOut.write(length >> 8);
         bytesOut.write(length);
         bytesOut.write(e);
         return bytesOut.toByteArray();
      } catch (IOException var6) {
         return null;
      }
   }
}
