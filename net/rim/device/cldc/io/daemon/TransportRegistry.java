package net.rim.device.cldc.io.daemon;

import java.util.Hashtable;
import net.rim.device.api.io.TransportBase;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.internal.io.TrafficLogger;

public final class TransportRegistry extends Thread {
   private Hashtable _instances = (Hashtable)(new Object());
   private TransportRegistry$Request[] _requests = new TransportRegistry$Request[0];
   private TrafficLogger _tLogger;
   private static final long ID;
   public static final long REGISTER_NOW_RUNNABLE;
   public static final long REGISTER_NOW_RESULT;

   private TransportRegistry() {
   }

   private final TransportBase getSingleton(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final TransportRegistry getRegistry() {
      return (TransportRegistry)ApplicationRegistry.getApplicationRegistry().waitFor(-3443331856084987690L);
   }

   public static final TransportBase get(String var0) {
      return getRegistry().getSingleton(var0);
   }

   static final void init() {
      TransportRegistry var0 = new TransportRegistry();
      ApplicationRegistry.getApplicationRegistry().put(-3443331856084987690L, var0);
      var0.start();
   }

   public static final void setTrafficLogger(TrafficLogger var0) {
      getRegistry().setTrafficLoggerInternal(var0);
   }

   private final synchronized void setTrafficLoggerInternal(TrafficLogger var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }
}
