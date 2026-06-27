package net.rim.device.cldc.io.nativebase;

import javax.microedition.io.Connection;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.DatagramBase;
import net.rim.device.api.io.DatagramConnectionBase;
import net.rim.device.api.system.EventLogger;

public class NativeConnectionBase extends DatagramConnectionBase {
   @Override
   public Connection openPrim(String var1, int var2, boolean var3) {
      if (!this.checkNetwork()) {
         throw new Object();
      }

      super.openPrim(var1, var2, var3);
      EventLogger.logEvent(super._transport.GUID, 1229874030, 0);
      return this;
   }

   @Override
   protected boolean isAddressed(DatagramAddressBase var1) {
      return super._receiveFilter.equals(var1);
   }

   protected boolean checkNetwork() {
      return true;
   }

   @Override
   public int getProperties(String var1) {
      return 2;
   }

   public DatagramBase receiveDatagramBase() {
      return null;
   }
}
