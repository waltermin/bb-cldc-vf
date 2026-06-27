package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.vm.Process;

public class CodeStore {
   private static long ID;
   private static CodeStore$DependencyList _dependencyList;

   private CodeStore() {
   }

   public static native int scheduleDeleteModule(int var0);

   public static native int getModuleHandles(int[] var0);

   public static native boolean isEldestSiblingModule(int var0);

   public static native int[] getSiblingHandles(int var0);

   public static native int getDependencyModuleHandle(int var0, int var1);

   public static byte[] addDRMTrailers(byte[] var0) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public static boolean checkDRMTrailer(int var0) {
      int var1 = 0;

      while (true) {
         byte[] var2 = CodeModuleManager.getModuleTrailer(var0, 4, var1);
         if (var2 == null) {
            return var1 == 0;
         }

         if (DRMServices.checkTrailerBytes(var2)) {
            return true;
         }

         var1++;
      }
   }

   public static void assertIsPartOfCurrentApp(int var0) {
      if (!isPartOfCurrentApp(var0)) {
         throw new Object();
      }
   }

   public static void generateDependencyList() {
      if (_dependencyList == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _dependencyList = (CodeStore$DependencyList)var0.getOrWaitFor(ID);
         if (_dependencyList == null) {
            _dependencyList = new CodeStore$DependencyList();
            var0.put(ID, _dependencyList);
         }
      }
   }

   public static boolean isPartOfCurrentApp(int var0) {
      if (var0 == 0) {
         return true;
      }

      int var1 = Process.currentProcess().getModuleHandle();
      if (var1 == var0) {
         return true;
      }

      generateDependencyList();
      return _dependencyList.isDependency(var1, var0);
   }
}
