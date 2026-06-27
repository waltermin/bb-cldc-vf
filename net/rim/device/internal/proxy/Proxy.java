package net.rim.device.internal.proxy;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationRegistry;

public final class Proxy extends Application {
   private RunnableThread _runnableThread;
   private static final long ID;

   public final void submitRunnable(Runnable var1) {
      this.invokeLater(new Proxy$UtilRunnable(var1, false));
   }

   public final void startThread(Thread var1) {
      this.invokeLater(new Proxy$UtilRunnable(var1, true));
   }

   public final void invokeRunnable(Runnable var1) {
      this._runnableThread.add(var1);
   }

   @Override
   public final void addListener(int var1, Object var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Proxy getInstance() {
      return (Proxy)ApplicationRegistry.getApplicationRegistry().waitFor(2026383997602970267L);
   }

   public static final Application ProxyMain() {
      Proxy var0 = new Proxy();
      ApplicationRegistry.getApplicationRegistry().put(2026383997602970267L, var0);
      var0._runnableThread = new RunnableThread();
      var0._runnableThread.start();
      return var0;
   }
}
