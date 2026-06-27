package net.rim.device.api.crypto;

import net.rim.device.api.util.Arrays;

public final class RIMSignature {
   private static final byte[] DEFAULT_RSA_E;
   private static final byte[] RSA_PKCS1_SHA1_DIGEST_PREFIX;
   private static final byte[] RSA_PKCS1_MD5_DIGEST_PREFIX;

   private RIMSignature() {
   }

   public static final long verify(byte[] var0, int var1, int var2, byte[] var3, int var4, byte[] var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final long verifyRIMFileSignerSignature(byte[] var0, int var1, int var2, byte[] var3, int var4, byte[] var5) {
      assertByte(var3, var4++, 31);
      assertByte(var3, var4++, 45);
      assertByte(var3, var4++, 200);
      assertByte(var3, var4++, 215);
      var4 += 4;
      int var6 = var4;
      long var7 = var3[var4++] & 0xFF;
      var7 |= (var3[var4++] & 255) << 8;
      var7 |= (var3[var4++] & 255) << 16;
      var7 |= (var3[var4++] & 255) << 24;
      int var9 = var3[var4++] & 255;
      var9 |= (var3[var4++] & 255) << 8;
      var9 |= (var3[var4++] & 255) << 16;
      var9 |= (var3[var4++] & 255) << 24;
      byte[] var10 = DEFAULT_RSA_E;
      byte[] var11 = var5;
      byte[] var12 = getBytes(var3, var4, var9, var11.length);
      SHA1Digest var13 = new SHA1Digest();
      var13.update(var0, var1, var2);
      var13.update(var3, var6, 4);
      return !verifyRSASignature(var10, var11, var12, var13, RSA_PKCS1_SHA1_DIGEST_PREFIX) ? 0 : var7 * 1000;
   }

   private static final int checkPGPPacketHeader(byte[] var0, int var1, int var2) {
      int var3 = var0[var1] & 255;
      int var4 = 128 | var2 << 2;
      int var5 = var3 ^ var4;
      if (var5 >= 3) {
         throw new Object();
      } else {
         return 1 + (1 << var5);
      }
   }

   private static final long verifyPGPSignature(byte[] var0, int var1, int var2, byte[] var3, int var4, byte[] var5) {
      var4 += checkPGPPacketHeader(var3, var4, 2);
      assertByte(var3, var4++, 3);
      assertByte(var3, var4++, 5);
      int var6 = var4;
      assertByte(var3, var4++, 0);
      long var7 = getInteger(var3, var4, 4) & -1;
      var4 += 12;
      byte var9 = var3[var4++];
      byte var10 = var3[var4++];
      AbstractDigest var11;
      byte[] var12;
      switch (var10) {
         case 0:
            throw new Object();
         case 1:
         default:
            var11 = new MD5Digest();
            var12 = RSA_PKCS1_MD5_DIGEST_PREFIX;
            break;
         case 2:
            var11 = new SHA1Digest();
            var12 = RSA_PKCS1_SHA1_DIGEST_PREFIX;
      }

      var11.update(var0, var1, var2);
      var11.update(var3, var6, 5);
      var4 += 2;
      int var13 = checkPGPPacketHeader(var5, 0, 6);
      byte var14 = var5[var13++];
      switch (var14) {
         case 2:
            throw new Object();
         case 3:
         default:
            var13 += 6;
            break;
         case 4:
            var13 += 4;
      }

      boolean var15;
      switch (var9) {
         case 1:
         case 3:
            var15 = verifyPGPRSASignature(var11, var12, var3, var4, var5, var13);
            break;
         case 17:
            var15 = verifyPGPDSASignature(var11.getDigest(), var3, var4, var5, var13);
            break;
         default:
            throw new Object();
      }

      return !var15 ? 0 : var7 * 1000;
   }

   private static final boolean verifyPGPRSASignature(Digest var0, byte[] var1, byte[] var2, int var3, byte[] var4, int var5) {
      assertByte(var4, var5++, 1);
      byte[] var6 = getMPI(var4, var5);
      var5 += 2 + var6.length;
      byte[] var7 = getMPI(var4, var5);
      byte[] var8 = new byte[var6.length];
      var3 += getMPI(var2, var3, var8);
      return verifyRSASignature(var7, var6, var8, var0, var1);
   }

   private static final boolean verifyRSASignature(byte[] var0, byte[] var1, byte[] var2, Digest var3, byte[] var4) {
      int var5 = var1.length;
      byte[] var6 = new byte[var5];
      NativeRSA.publicKeyOperation(var1.length << 3, var0, var1, var2, 0, var6, 0);
      int var7 = var3.getDigestLength();
      int var8 = var4.length;
      byte[] var9 = new byte[var5];
      var9[1] = 1;
      Arrays.fill(var9, (byte)-1, 2, var5 - var7 - var8 - 1 - 2);
      System.arraycopy(var4, 0, var9, var5 - var7 - var8, var8);
      var3.getDigest(var9, var5 - var7);
      return Arrays.equals(var9, var6);
   }

   private static final boolean verifyPGPDSASignature(byte[] var0, byte[] var1, int var2, byte[] var3, int var4) {
      assertByte(var3, var4++, 17);
      byte[] var5 = getMPI(var3, var4);
      var4 += 2 + var5.length;
      byte[] var6 = new byte[20];
      byte[] var7 = new byte[var5.length];
      byte[] var8 = new byte[var5.length];
      var4 += getMPI(var3, var4, var6);
      var4 += getMPI(var3, var4, var7);
      var4 += getMPI(var3, var4, var8);
      byte[] var9 = new byte[var6.length];
      byte[] var10 = new byte[var6.length];
      var2 += getMPI(var1, var2, var9);
      var2 += getMPI(var1, var2, var10);
      return NativeDL.verifyDSA(var5, var6, var7, var8, var0, var9, var10);
   }

   private static final void assertByte(byte[] var0, int var1, int var2) {
      if (var2 != (var0[var1++] & 255)) {
         throw new Object();
      }
   }

   private static final int getInteger(byte[] var0, int var1, int var2) {
      int var3 = var0[var1++] & 255;

      while (--var2 > 0) {
         var3 <<= 8;
         var3 |= var0[var1++] & 255;
      }

      return var3;
   }

   private static final byte[] getBytes(byte[] var0, int var1, int var2, int var3) {
      byte[] var4 = new byte[var3];
      System.arraycopy(var0, var1, var4, var3 - var2, var2);
      return var4;
   }

   private static final int getMPILength(byte[] var0, int var1) {
      return getInteger(var0, var1, 2) + 7 >> 3;
   }

   private static final byte[] getMPI(byte[] var0, int var1) {
      int var2 = getMPILength(var0, var1);
      byte[] var3 = new byte[var2];
      getMPI(var0, var1, var3);
      return var3;
   }

   private static final int getMPI(byte[] var0, int var1, byte[] var2) {
      int var3 = getMPILength(var0, var1);
      System.arraycopy(var0, var1 + 2, var2, var2.length - var3, var3);
      return 2 + var3;
   }
}
