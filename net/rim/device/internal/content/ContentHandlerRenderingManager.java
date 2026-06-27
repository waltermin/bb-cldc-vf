package net.rim.device.internal.content;

import net.rim.device.api.system.ApplicationRegistry;

public class ContentHandlerRenderingManager {
   protected static final long APP_REGISTRY_KEY;

   protected ContentHandlerRenderingManager() {
   }

   public static ContentHandlerRenderingManager getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      return (ContentHandlerRenderingManager)var0.waitFor(972161364679736566L);
   }

   public void register(String var1) {
      throw null;
   }
}
