package net.rim.device.api.gps;

import net.rim.device.api.util.IntEnumeration;

class GPSRegistry$GPSListenerImpl implements GPSListener {
   int _errorCount;
   private final GPSRegistry this$0;

   GPSRegistry$GPSListenerImpl(GPSRegistry var1) {
      this.this$0 = var1;
   }

   @Override
   public void gpsModeChangeComplete(boolean var1, int var2) {
   }

   @Override
   public void gpsPDEChangeComplete(boolean var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void gpsLocationUpdated(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void gpsResponseGetLPS(int var1) {
   }

   @Override
   public void gpsResponseSetLPS(int var1) {
   }

   @Override
   public void gpsResponseEnablePIN(int var1) {
   }

   @Override
   public void gpsResponseChangePIN(int var1) {
   }

   @Override
   public void gpsEphemerisDataRequired(int var1) {
   }

   @Override
   public void gpsCredentialChangeComplete(boolean var1, int var2) {
      IntEnumeration var3 = this.this$0._pdeTable.keys();

      while (var3.hasMoreElements()) {
         int var4 = var3.nextElement();
         GPSRegistry$PDEInfoStatus var5 = (GPSRegistry$PDEInfoStatus)this.this$0._pdeTable.get(var4);
         if (var5 != null) {
            GPS$GPSPDEInfo var6 = var5.getPDEInfo();
            if (var6 != null && var6.getCredential() != null && var6.getCredential().getAppId() == var2) {
               var5.setCredStatus(var1);
               if (!var1) {
                  this.this$0._criteriaTable.remove(var4);
                  this.this$0._assistFixConsumers.remove(var4);
               }
            }
         }
      }

      if (!var1) {
         this.this$0.restartCDMAAssistedLocationUpdate();
      }
   }

   @Override
   public void gpsLocationAidingRequest() {
   }
}
