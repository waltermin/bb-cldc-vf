package net.rim.device.api.system;

import net.rim.vm.MemStats;

public final class MemoryStats extends MemStats {
   @Override
   public final int getAllocated() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public final int getFree() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public final int getObjectSize() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public final int getObjectCount() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }
}
