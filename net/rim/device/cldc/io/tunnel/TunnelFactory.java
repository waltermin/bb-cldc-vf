package net.rim.device.cldc.io.tunnel;

import net.rim.device.api.system.ApplicationRegistry;

public class TunnelFactory {
   public static final long ID;
   public static final String STR;

   public static Tunnel openTunnel(TunnelConfig var0) {
      return getTunnelFactory().open(var0);
   }

   public static Object setupTunnel(int var0, Object var1) {
      return getTunnelFactory().setup(var0, var1);
   }

   public static TunnelFactory getTunnelFactory() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      return (TunnelFactory)var0.waitFor(4292459735430940092L);
   }

   public Tunnel open(TunnelConfig var1) {
      throw null;
   }

   public Object setup(int var1, Object var2) {
      throw null;
   }
}
