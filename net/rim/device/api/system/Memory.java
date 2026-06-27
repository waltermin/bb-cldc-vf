package net.rim.device.api.system;

public final class Memory {
   private Memory() {
   }

   public static final MemoryStats getRAMStats() {
      MemoryStats var0 = new MemoryStats();
      net.rim.vm.Memory.getRAMStats(var0);
      return var0;
   }

   public static final MemoryStats getFlashStats() {
      MemoryStats var0 = new MemoryStats();
      net.rim.vm.Memory.getFlashStats(var0);
      return var0;
   }

   public static final MemoryStats getTransientStats() {
      MemoryStats var0 = new MemoryStats();
      net.rim.vm.Memory.getTransientStats(var0);
      return var0;
   }

   public static final MemoryStats getPersistentStats() {
      MemoryStats var0 = new MemoryStats();
      net.rim.vm.Memory.getPersistentStats(var0);
      return var0;
   }

   public static final MemoryStats getObjectStats() {
      MemoryStats var0 = new MemoryStats();
      net.rim.vm.Memory.getObjectStats(var0);
      return var0;
   }

   public static final MemoryStats getCodeStats() {
      MemoryStats var0 = new MemoryStats();
      net.rim.vm.Memory.getCodeStats(var0);
      return var0;
   }

   public static final int getMemoryNeeded() {
      return net.rim.vm.Memory.getFlashNeeded(false);
   }

   public static final int getFlashFree() {
      return net.rim.vm.Memory.getFlashFree();
   }

   public static final int getFlashTotal() {
      return net.rim.vm.Memory.getFlashTotal();
   }
}
