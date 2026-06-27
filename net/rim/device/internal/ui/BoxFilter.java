package net.rim.device.internal.ui;

import net.rim.device.api.math.Fixed32;

class BoxFilter implements Filter {
   @Override
   public int widthFP() {
      return 32768;
   }

   @Override
   public int weightFP(int var1) {
      return Fixed32.abs(var1) <= 32768 ? 65536 : 0;
   }
}
