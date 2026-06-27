package net.rim.device.internal.ui;

import net.rim.device.api.math.Fixed32;

class Weights {
   int _left;
   int _right;
   int[] _weightsFP;

   Weights(Filter var1, int var2, int var3, int var4, int var5) {
      this._left = var2;
      this._right = var3;
      this._weightsFP = new int[var3 - var2 + 1];
      int var6 = 0;

      for (int var7 = this._left; var7 <= this._right; var7++) {
         int var8 = Fixed32.mul(var5, var1.weightFP(Fixed32.mul(var5, var4 - Fixed32.toFP(var7))));
         this._weightsFP[var7 - this._left] = var8;
         var6 += var8;
      }

      for (int var9 = this._left; var9 <= this._right; var9++) {
         this._weightsFP[var9 - this._left] = Fixed32.div(this._weightsFP[var9 - this._left], var6);
      }
   }

   int filter(Fetcher var1) {
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;

      for (int var6 = this._left; var6 <= this._right; var6++) {
         int var7 = var1.get(var6);
         int var8 = this._weightsFP[var6 - this._left];
         var5 += (var7 & 0xFF) * var8;
         var4 += (var7 >> 8 & 0xFF) * var8;
         var3 += (var7 >> 16 & 0xFF) * var8;
         var2 += (var7 >> 24 & 0xFF) * var8;
      }

      return this.clampFP(var2) << 24 | this.clampFP(var3) << 16 | this.clampFP(var4) << 8 | this.clampFP(var5);
   }

   int clampFP(int var1) {
      int var2 = Fixed32.toInt(var1);
      if (var2 < 0) {
         return 0;
      } else {
         return var2 > 255 ? 255 : var2;
      }
   }
}
