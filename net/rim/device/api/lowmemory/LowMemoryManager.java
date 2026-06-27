package net.rim.device.api.lowmemory;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.vm.Memory;

public class LowMemoryManager {
   private static LowMemoryManager _instance;
   public static final long GUID_INSTANCE;
   public static final long GUID_FLASH_LOW;

   protected LowMemoryManager() {
   }

   private static LowMemoryManager getInstance() {
      if (_instance == null) {
         _instance = (LowMemoryManager)ApplicationRegistry.getApplicationRegistry().waitFor(7979320271643693911L);
      }

      return _instance;
   }

   public static void addLowMemoryFailedListener(LowMemoryFailedListener var0) {
      getInstance().doAddLowMemoryFailedListener(var0);
   }

   public static void addLowMemoryListener(LowMemoryListener var0) {
      getInstance().doAddLowMemoryListener(var0);
   }

   public static void removeLowMemoryListener(LowMemoryListener var0) {
      getInstance().doRemoveLowMemoryListener(var0);
   }

   public static void removeLowMemoryFailedListener(LowMemoryFailedListener var0) {
      getInstance().doRemoveLowMemoryFailedListener(var0);
   }

   public static void poll() {
      getInstance().doPoll(true);
   }

   public static void markAsRecoverable(Object var0) {
      Memory.markAsRecoverable(var0);
   }

   protected void doAddLowMemoryListener(LowMemoryListener var1) {
      throw null;
   }

   protected void doRemoveLowMemoryListener(LowMemoryListener var1) {
      throw null;
   }

   protected void doAddLowMemoryFailedListener(LowMemoryFailedListener var1) {
      throw null;
   }

   protected void doRemoveLowMemoryFailedListener(LowMemoryFailedListener var1) {
      throw null;
   }

   protected void doPoll(boolean var1) {
      throw null;
   }
}
