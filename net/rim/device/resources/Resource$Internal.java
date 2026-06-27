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

   private static final String getExtension(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final synchronized Resource findResourceClass(String var0, int var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Resource getResourceClass(String var0) {
      return getResourceClass(var0, true);
   }

   public static final Resource getResourceClass(String var0, boolean var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final byte[] FindResourceInDependencyGraph(int var0, int var1, String var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte[] getResource(String var0, int var1) {
      String var2 = CodeModuleManager.getModuleName(var1, 0);
      if (var2 != null) {
         String var3 = getExtension(var0);
         Resource var4 = findResourceClass(var2, var1, var3, true);
         if (var4 != null) {
            return var4.findResource(var0);
         }
      }

      return null;
   }

   public static final ToIntHashtable getResourceCache(String var0) {
      Object var1 = new Object();
      String var3 = getExtension(var0);

      for (int var4 = 0; var4 < MAX_MODULES; var4++) {
         String var2 = Process.getModuleName(var4, 0);
         if (var2 != null) {
            Resource var5 = findResourceClass(var2, var4, var3, true);
            if (var5 != null) {
               Vector var6 = var5.listResourcesEndingWith(var0);
               if (var6.size() > 0) {
                  for (int var7 = 0; var7 < var6.size(); var7++) {
                     ((ToIntHashtable)var1).put(var6.elementAt(var7), var4);
                  }
               }
            }
         }
      }

      return (ToIntHashtable)var1;
   }
}
