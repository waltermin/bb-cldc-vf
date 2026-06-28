package net.rim.device.api.rtp;

import net.rim.device.api.system.ApplicationRegistry;

public class RTPRegistry {
   private static final long ID;

   private RTPRegistry() {
   }

   public static void setRTPSystem(RTPSystem rtpSystem) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static RTPSystem getRTPSystem() {
      ApplicationRegistry applicationRegistry = ApplicationRegistry.getApplicationRegistry();
      return (RTPSystem)applicationRegistry.waitFor(6854459907021350219L);
   }
}
