package net.rim.device.api.streaming;

import net.rim.device.api.system.ApplicationRegistry;

public final class StreamingFactory {
   private static final long ID;

   private StreamingFactory() {
   }

   public static final void setStreamingSystem(Streaming var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Streaming getStreamingSystem() {
      ApplicationRegistry var2 = ApplicationRegistry.getApplicationRegistry();
      return (Streaming)var2.waitFor(-7849240269607795087L);
   }
}
