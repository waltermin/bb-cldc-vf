package net.rim.device.api.rtp;

import net.rim.device.api.system.ApplicationRegistry;

public class RTPRegistry {
   private static final long ID;

   private RTPRegistry() {
   }

   public static void setRTPSystem(RTPSystem var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static RTPSystem getRTPSystem() {
      ApplicationRegistry var2 = ApplicationRegistry.getApplicationRegistry();
      return (RTPSystem)var2.waitFor(6854459907021350219L);
   }
}
