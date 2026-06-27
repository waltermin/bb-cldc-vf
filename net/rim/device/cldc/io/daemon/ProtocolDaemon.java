package net.rim.device.cldc.io.daemon;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.ui.UiApplication;

public final class ProtocolDaemon extends UiApplication {
   private static final long ID;

   private ProtocolDaemon() {
   }

   @Override
   protected final boolean acceptsForeground() {
      return false;
   }

   public final void submitRunnable(Runnable var1) {
      this.invokeLater(new ProtocolDaemon$UtilRunnable(var1, false));
   }

   public final void startThread(Thread var1) {
      this.invokeLater(new ProtocolDaemon$UtilRunnable(var1, true));
   }

   public static final ProtocolDaemon getInstance() {
      return (ProtocolDaemon)ApplicationRegistry.getApplicationRegistry().waitFor(6860522476510630950L);
   }

   public static final void start() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
