package net.rim.device.api.crypto;

final class NativeARC4PRNG {
   private int _i;
   private int _j;
   private byte[] _s;

   public NativeARC4PRNG(byte[] var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var3 > 0 && var1.length - var3 >= var2) {
         this._s = new byte[256];

         for (int var4 = 0; var4 < 256; var4++) {
            this._s[var4] = (byte)var4;
         }

         int var5 = 0;

         for (int var7 = 0; var7 < 256; var7++) {
            var5 = var1[var2 + var7 % var3] + this._s[var7] + var5 & 0xFF;
            byte var6 = this._s[var7];
            this._s[var7] = this._s[var5];
            this._s[var5] = var6;
         }
      } else {
         throw new Object();
      }
   }

   public final native void xorBytes(byte[] var1, int var2, int var3);
}
