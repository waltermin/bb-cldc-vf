package net.rim.device.internal.ui;

import net.rim.device.api.math.Fixed32;

class LanczosFilter implements Filter {
   int sincFP(int var1) {
      if (var1 == 0) {
         return 65536;
      }

      var1 = Fixed32.mul(var1, 205887);
      return Fixed32.div(Fixed32.Sin(var1), var1);
   }

   @Override
   public int weightFP(int var1) {
      var1 = Fixed32.abs(var1);
      return var1 < Fixed32.toFP(3) ? Fixed32.mul(this.sincFP(var1), this.sincFP(var1 / 3)) : 0;
   }

   @Override
   public int widthFP() {
      return Fixed32.toFP(3);
   }
}
