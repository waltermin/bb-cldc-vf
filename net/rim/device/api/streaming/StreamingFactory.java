package net.rim.device.api.streaming;

import net.rim.device.api.system.ApplicationRegistry;

public final class StreamingFactory {
   private static final long ID;

   private StreamingFactory() {
   }

   public static final void setStreamingSystem(Streaming streamingSystem) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Streaming getStreamingSystem() {
      ApplicationRegistry applicationRegistry = ApplicationRegistry.getApplicationRegistry();
      return (Streaming)applicationRegistry.waitFor(-7849240269607795087L);
   }
}
