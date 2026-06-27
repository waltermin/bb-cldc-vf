package net.rim.device.api.crypto;

import net.rim.device.api.util.CRC32;

public final class HashCodeCalculator {
   private static SHA1Digest _digest;

   private HashCodeCalculator() {
   }

   public static final int getCRC32(byte[] var0) {
      return var0 == null ? 0 : CRC32.update(-var0.length, var0, 0, var0.length);
   }

   public static final int getCRC32(byte[] var0, int var1, int var2) {
      if (var0 == null) {
         return 0;
      } else if (var1 >= 0 && var2 >= 0 && var0.length - var2 >= var1) {
         return CRC32.update(-var2, var0, var1, var2);
      } else {
         throw new Object();
      }
   }

   public static final long getDigest64(byte[] var0) {
      byte[] var1 = getDigest(var0);
      return (long)var1[0] << 56
         | (long)var1[1] << 48
         | (long)var1[2] << 40
         | (long)var1[3] << 32
         | (long)var1[4] << 24
         | (long)var1[5] << 16
         | (long)var1[6] << 8
         | var1[7];
   }

   public static final int getDigest32(byte[] var0) {
      byte[] var1 = getDigest(var0);
      return var1[0] << 24 | var1[1] << 16 | var1[2] << 8 | var1[3];
   }

   private static final synchronized byte[] getDigest(byte[] var0) {
      if (var0 == null) {
         return new byte[8];
      }

      _digest.reset();
      _digest.update(var0);
      return _digest.getDigest();
   }
}
