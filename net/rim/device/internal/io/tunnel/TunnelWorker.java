package net.rim.device.internal.io.tunnel;

import net.rim.device.cldc.io.tunnel.Tunnel;
import net.rim.device.cldc.io.tunnel.TunnelConfig;
import net.rim.device.cldc.io.tunnel.TunnelListener;

public final class TunnelWorker implements TunnelListener {
   private int _tunnelState;
   private int _tunnelCode;
   private static final int TUNNEL_OPEN_TIMEOUT;

   public final Tunnel open(TunnelConfig var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void timeout(long var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final synchronized int getTunnelState() {
      return this._tunnelState;
   }

   private final synchronized int getTunnelCode() {
      return this._tunnelCode;
   }

   @Override
   public final void statusChanged(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
