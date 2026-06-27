package net.rim.device.api.memorycleaner;

public final class MemoryCleanerDaemon {
   private static MemoryCleanerManager _manager;

   private MemoryCleanerDaemon() {
   }

   public static final void addListener(MemoryCleanerListener var0) {
      addListener(var0, true);
   }

   public static final void addListener(MemoryCleanerListener var0, boolean var1) {
      _manager.addListener(var0, false, var1);
   }

   public static final void addWeakListener(MemoryCleanerListener var0) {
      addWeakListener(var0, true);
   }

   public static final void addWeakListener(MemoryCleanerListener var0, boolean var1) {
      _manager.addListener(var0, true, var1);
   }

   public static final void removeListener(MemoryCleanerListener var0) {
      _manager.removeListener(var0);
   }

   public static final void cleanAll() {
      _manager.cleanAll();
   }
}
