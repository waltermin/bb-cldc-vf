package net.rim.device.internal.browser.util;

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

   public static final String encrypt(String var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void formatAndEncrypt(byte[] var0, byte[] var1, byte[] var2, int var3, int var4, byte[] var5, int var6) {
      if (var2 != null && var3 >= 0 && var4 >= 0 && var3 + var4 <= var2.length && var5 != null && var6 >= 0 && var6 + 128 <= var5.length && var4 <= 100) {
         int var7 = 0;
         var1[var7++] = 0;
         var1[var7++] = 2;
         int var8 = var1.length - (3 + var4);
         RandomSource.getBytes(var1, var7, var8);

         for (int var9 = var8; var9 > 0; var9--) {
            if (var1[var7++] == 0) {
               byte var10;
               do {
                  var10 = (byte)RandomSource.getInt();
               } while (var10 == 0);

               var1[var7 - 1] = var10;
            }
         }

         var1[var7++] = 0;
         System.arraycopy(var2, var3, var1, var7, var4);
         nativeRSAPublicKeyOperation(1024, RSA_E, var0, var1, 0, var5, var6);
      } else {
         throw new Object();
      }
   }

   private static final native void nativeRSAPublicKeyOperation(int var0, byte[] var1, byte[] var2, byte[] var3, int var4, byte[] var5, int var6);

   public static final byte[] getPGPPublicKey() {
      return getPGPPublicKey(RSA_E, RSA_N_0);
   }

   private static final byte[] getPGPPublicKey(byte[] var0, byte[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
