package net.rim.device.internal.io.tunnel;

import net.rim.device.cldc.io.tunnel.Tunnel;
import net.rim.device.cldc.io.tunnel.TunnelConfig;
import net.rim.device.cldc.io.tunnel.TunnelListener;

public final class TunnelWorker implements TunnelListener {
   private int _tunnelState;
   private int _tunnelCode;
   private static final int TUNNEL_OPEN_TIMEOUT;

   public final Tunnel open(TunnelConfig config) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void timeout(long quitTime) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final synchronized int getTunnelState() {
      return this._tunnelState;
   }

   private final synchronized int getTunnelCode() {
      return this._tunnelCode;
   }

   @Override
   public final void statusChanged(int status, int code) {
      synchronized (this) {
         this._tunnelState = status;
         this._tunnelCode = code;
         this.notify();
      }
   }
}
