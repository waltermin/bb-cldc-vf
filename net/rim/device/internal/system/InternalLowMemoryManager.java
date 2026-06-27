package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationRegistry;

public class InternalLowMemoryManager {
   private InternalLowMemoryManager() {
   }

   public static void exactPoll() {
      ExactLowMemoryManager var0 = (ExactLowMemoryManager)ApplicationRegistry.getApplicationRegistry().waitFor(7979320271643693911L);
      var0.exactPoll();
   }
}
