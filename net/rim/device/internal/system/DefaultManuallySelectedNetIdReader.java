package net.rim.device.internal.system;

public final class DefaultManuallySelectedNetIdReader implements SIMCardEfTask {
   public final void setManuallySelectedNetId(int var1) {
      if (RadioInternal.getManuallySelectedNetworkID() == -1) {
         RadioInternal.setManuallySelectedNetworkID(var1);
      }
   }

   @Override
   public final void doWork(SIMCardEfHandler var1) {
      int var2 = var1.infoRequest(17);
      if (var2 == 0) {
         byte[] var3 = new byte[var1.getFileSize()];
         int var4 = var1.readRequest(0, var3);
         if (var4 == 0) {
            if (var3.length >= 7) {
               byte var5 = var3[4];
               byte var6 = var3[5];
               byte var7 = var3[6];
               if ((var5 & var6 & var7) == 255) {
                  return;
               }

               int var8 = (var5 & 15) << 8;
               var8 |= var5 & 240;
               var8 |= var6 & 15;
               int var12;
               if ((var6 & 240) == 240) {
                  var12 = (var7 & 15) << 4;
                  var12 |= (var7 & 240) >> 4;
               } else {
                  var12 = (var7 & 15) << 8;
                  var12 |= var7 & 240;
                  var12 |= (var6 & 240) >> 4;
               }

               this.setManuallySelectedNetId(var12 << 16 | var8);
            }
         }
      }
   }

   public DefaultManuallySelectedNetIdReader() {
      RadioInternal.setManuallySelectedNetworkID(-1);
   }
}
