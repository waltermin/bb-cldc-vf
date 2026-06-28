package net.rim.device.resources;

import java.util.Vector;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.util.ToIntHashtable;
import net.rim.vm.Process;
import net.rim.vm.WeakReference;

public final class Resource$Internal {
   private static final int MAX_MODULES;
   private static String _moduleName;
   private static WeakReference _moduleNameResourceWF;

   private Resource$Internal() {
   }

   private static final String getExtension(String name) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final synchronized Resource findResourceClass(String moduleName, int moduleHandle, String ext, boolean cacheResult) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Resource getResourceClass(String moduleName) {
      return getResourceClass(moduleName, true);
   }

   public static final Resource getResourceClass(String moduleName, boolean cacheResult) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final byte[] FindResourceInDependencyGraph(int firstRoot, int secondRoot, String name) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte[] getResource(String name, int moduleHandle) {
      String moduleName = CodeModuleManager.getModuleName(moduleHandle, 0);
      if (moduleName != null) {
         String ext = getExtension(name);
         Resource resource = findResourceClass(moduleName, moduleHandle, ext, true);
         if (resource != null) {
            return resource.findResource(name);
         }
      }

      return null;
   }

   public static final ToIntHashtable getResourceCache(String endsWithText) {
      ToIntHashtable cache = (ToIntHashtable)(new Object());
      String ext = getExtension(endsWithText);

      for (int moduleHandle = 0; moduleHandle < MAX_MODULES; moduleHandle++) {
         String moduleName = Process.getModuleName(moduleHandle, 0);
         if (moduleName != null) {
            Resource resource = findResourceClass(moduleName, moduleHandle, ext, true);
            if (resource != null) {
               Vector matches = resource.listResourcesEndingWith(endsWithText);
               if (matches.size() > 0) {
                  for (int j = 0; j < matches.size(); j++) {
                     cache.put(matches.elementAt(j), moduleHandle);
                  }
               }
            }
         }
      }

      return cache;
   }
}
