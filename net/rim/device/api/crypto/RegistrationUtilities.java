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

   public static final byte[] generateRandomSessionKey(int var0) {
      return RandomSource.getBytes(var0);
   }

   public static final byte[] generateRandomSessionKey() {
      return generateRandomSessionKey(32);
   }

   public static final byte[] encryptSessionKey(byte[] var0) {
      int var1 = PUBLIC_KEY_N.length << 3;
      byte[] var2 = new byte[var1 >> 3];
      var2[1] = 2;
      int var3 = var2.length - (3 + var0.length);

      for (int var4 = 0; var4 < var3; var4++) {
         byte var5;
         do {
            var5 = (byte)RandomSource.getInt();
         } while (var5 == 0);

         var2[2 + var4] = var5;
      }

      System.arraycopy(var0, 0, var2, var2.length - var0.length, var0.length);
      byte[] var6 = new byte[var2.length];
      NativeRSA.publicKeyOperation(var1, PUBLIC_KEY_E, PUBLIC_KEY_N, var2, 0, var6, 0);
      return var6;
   }

   public static final int getCipherTextLength(int var0) {
      int var1 = 16 - var0 % 16;
      if (var1 == 0) {
         var1 = 16;
      }

      return var0 + var1;
   }

   public static final void encryptBulkData(byte[] var0, byte[] var1, byte[] var2) {
      if (var2.length != getCipherTextLength(var1.length)) {
         throw new Object();
      }

      byte[] var3 = new byte[getCipherTextLength(var1.length)];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      int var4 = var3.length - var1.length;
      Arrays.fill(var3, (byte)var4, var1.length, var4);
      int var5 = var2.length / 16;
      NativeBlockCipher var6 = NativeBlockCipher.initializeAES(var0, 16, true);
      byte[] var7 = new byte[16];
      System.arraycopy(IV, 0, var7, 0, IV.length);

      for (int var8 = 0; var8 < var5; var8++) {
         int var9 = var8 * 16;

         for (int var10 = 0; var10 < 16; var10++) {
            var7[var10] ^= var3[var9 + var10];
         }

         var6.crypt(var7, 0, var2, var9);
         System.arraycopy(var2, var9, var7, 0, var7.length);
      }
   }

   public static final int decryptBulkData(byte[] var0, byte[] var1, byte[] var2) {
      if (var1.length % 16 != 0) {
         throw new Object();
      }

      int var3 = var1.length / 16;
      byte[] var4 = new byte[16];
      System.arraycopy(IV, 0, var4, 0, IV.length);
      NativeBlockCipher var5 = NativeBlockCipher.initializeAES(var0, 16, false);

      for (int var6 = 0; var6 < var3; var6++) {
         int var7 = var6 * 16;
         var5.crypt(var1, var7, var2, var7);

         for (int var8 = 0; var8 < 16; var8++) {
            var2[var7 + var8] = (byte)(var2[var7 + var8] ^ var4[var8]);
         }

         System.arraycopy(var1, var7, var4, 0, var4.length);
      }

      byte var9 = var2[var2.length - 1];
      if (var9 > 0 && var9 <= 16) {
         Arrays.fill(var2, (byte)0, var2.length - var9, var9);
         return var2.length - var9;
      } else {
         return var2.length;
      }
   }

   public static final int getMACLength() {
      return 20;
   }

   public static final void mac(byte[] var0, byte[] var1, byte[] var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean checkMAC(byte[] var0, byte[] var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
