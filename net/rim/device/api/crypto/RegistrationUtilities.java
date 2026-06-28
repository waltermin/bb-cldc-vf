package net.rim.device.api.crypto;

import net.rim.device.api.util.Arrays;

public final class RegistrationUtilities {
   public static final int AES_256_KEY_LENGTH;
   public static final int AES_BLOCK_LENGTH;
   public static final byte[] PUBLIC_KEY_E;
   public static final byte[] IV;
   public static final byte[] PUBLIC_KEY_N;

   private RegistrationUtilities() {
   }

   public static final byte[] generateRandomSessionKey(int length) {
      return RandomSource.getBytes(length);
   }

   public static final byte[] generateRandomSessionKey() {
      return generateRandomSessionKey(32);
   }

   public static final byte[] encryptSessionKey(byte[] sessionKey) {
      int modulusLength = PUBLIC_KEY_N.length << 3;
      byte[] encoded = new byte[modulusLength >> 3];
      encoded[1] = 2;
      int randomnessLength = encoded.length - (3 + sessionKey.length);

      for (int i = 0; i < randomnessLength; i++) {
         byte randomByte;
         do {
            randomByte = (byte)RandomSource.getInt();
         } while (randomByte == 0);

         encoded[2 + i] = randomByte;
      }

      System.arraycopy(sessionKey, 0, encoded, encoded.length - sessionKey.length, sessionKey.length);
      byte[] result = new byte[encoded.length];
      NativeRSA.publicKeyOperation(modulusLength, PUBLIC_KEY_E, PUBLIC_KEY_N, encoded, 0, result, 0);
      return result;
   }

   public static final int getCipherTextLength(int plainTextLength) {
      int paddingAmount = 16 - plainTextLength % 16;
      if (paddingAmount == 0) {
         paddingAmount = 16;
      }

      return plainTextLength + paddingAmount;
   }

   public static final void encryptBulkData(byte[] sessionKey, byte[] plainText, byte[] cipherText) {
      if (cipherText.length != getCipherTextLength(plainText.length)) {
         throw new IllegalArgumentException();
      }

      byte[] paddedData = new byte[getCipherTextLength(plainText.length)];
      System.arraycopy(plainText, 0, paddedData, 0, plainText.length);
      int paddingAmount = paddedData.length - plainText.length;
      Arrays.fill(paddedData, (byte)paddingAmount, plainText.length, paddingAmount);
      int numBlocks = cipherText.length / 16;
      NativeBlockCipher aes = NativeBlockCipher.initializeAES(sessionKey, 16, true);
      byte[] buffer = new byte[16];
      System.arraycopy(IV, 0, buffer, 0, IV.length);

      for (int i = 0; i < numBlocks; i++) {
         int offset = i * 16;

         for (int j = 0; j < 16; j++) {
            buffer[j] ^= paddedData[offset + j];
         }

         aes.crypt(buffer, 0, cipherText, offset);
         System.arraycopy(cipherText, offset, buffer, 0, buffer.length);
      }
   }

   public static final int decryptBulkData(byte[] sessionKey, byte[] cipherText, byte[] plainText) {
      if (cipherText.length % 16 != 0) {
         throw new IllegalArgumentException();
      }

      int numBlocks = cipherText.length / 16;
      byte[] buffer = new byte[16];
      System.arraycopy(IV, 0, buffer, 0, IV.length);
      NativeBlockCipher aes = NativeBlockCipher.initializeAES(sessionKey, 16, false);

      for (int i = 0; i < numBlocks; i++) {
         int offset = i * 16;
         aes.crypt(cipherText, offset, plainText, offset);

         for (int j = 0; j < 16; j++) {
            plainText[offset + j] = (byte)(plainText[offset + j] ^ buffer[j]);
         }

         System.arraycopy(cipherText, offset, buffer, 0, buffer.length);
      }

      int paddingAmount = plainText[plainText.length - 1];
      if (paddingAmount > 0 && paddingAmount <= 16) {
         Arrays.fill(plainText, (byte)0, plainText.length - paddingAmount, paddingAmount);
         return plainText.length - paddingAmount;
      } else {
         return plainText.length;
      }
   }

   public static final int getMACLength() {
      return 20;
   }

   public static final void mac(byte[] data, byte[] macKey, byte[] macValue, int offset) {
      try {
         Digest digest = new SHA1Digest();
         HMACKey key = new HMACKey(macKey);
         HMAC hMac = new HMAC(key, digest);
         hMac.update(data);
         hMac.getMAC(macValue, offset);
      } catch (CryptoException var7) {
      }
   }

   public static final boolean checkMAC(byte[] data, byte[] macKey, byte[] macValue) {
      try {
         Digest digest = new SHA1Digest();
         HMACKey key = new HMACKey(macKey);
         HMAC hMac = new HMAC(key, digest);
         hMac.update(data);
         byte[] checkMAC = new byte[getMACLength()];
         hMac.getMAC(checkMAC, 0);
         return Arrays.equals(macValue, checkMAC);
      } catch (CryptoException e) {
         return false;
      }
   }
}
