package net.rim.device.internal.gps;

import net.rim.device.api.system.ApplicationRegistry;

public final class GPSFirewall {
   public static final GPSFirewallInterface getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      return (GPSFirewallInterface)var0.waitFor(-3752949794647167067L);
   }
}
