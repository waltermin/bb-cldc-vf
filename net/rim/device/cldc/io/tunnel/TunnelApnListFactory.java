package net.rim.device.cldc.io.tunnel;

import net.rim.device.api.system.ApplicationRegistry;

public class TunnelApnListFactory {
   public static final long ID;
   private static TunnelApnListFactory _theInstance;

   public static TunnelApnListFactory getTunnelApnListFactory() {
      if (_theInstance == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _theInstance = (TunnelApnListFactory)var0.waitFor(-977265756801572775L);
      }

      return _theInstance;
   }

   public TunnelApnList createTunnelApnList() {
      throw null;
   }
}
