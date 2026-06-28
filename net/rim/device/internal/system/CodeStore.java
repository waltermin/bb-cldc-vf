package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.ControlledAccessException;
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

   public static byte[] addDRMTrailers(byte[] codfile) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public static boolean checkDRMTrailer(int moduleHandle) {
      int i = 0;

      while (true) {
         byte[] trailer = CodeModuleManager.getModuleTrailer(moduleHandle, 4, i);
         if (trailer == null) {
            return i == 0;
         }

         if (DRMServices.checkTrailerBytes(trailer)) {
            return true;
         }

         i++;
      }
   }

   public static void assertIsPartOfCurrentApp(int caller) {
      if (!isPartOfCurrentApp(caller)) {
         throw new ControlledAccessException();
      }
   }

   public static void generateDependencyList() {
      if (_dependencyList == null) {
         ApplicationRegistry ar = ApplicationRegistry.getApplicationRegistry();
         _dependencyList = (CodeStore$DependencyList)ar.getOrWaitFor(ID);
         if (_dependencyList == null) {
            _dependencyList = new CodeStore$DependencyList();
            ar.put(ID, _dependencyList);
         }
      }
   }

   public static boolean isPartOfCurrentApp(int caller) {
      if (caller == 0) {
         return true;
      }

      int processModuleHandle = Process.currentProcess().getModuleHandle();
      if (processModuleHandle == caller) {
         return true;
      }

      generateDependencyList();
      return _dependencyList.isDependency(processModuleHandle, caller);
   }
}
