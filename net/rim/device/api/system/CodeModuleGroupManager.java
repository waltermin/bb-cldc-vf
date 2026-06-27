package net.rim.device.api.system;

import java.util.Enumeration;
import net.rim.device.internal.applicationcontrol.ApplicationControl;

public class CodeModuleGroupManager {
   private CodeModuleGroupManager() {
   }

   private static void assertPermission() {
      ApplicationControl.assertCMMApiAllowed(true);
   }

   public static CodeModuleGroup load(String var0) {
      return load(getGroupHandle(var0));
   }

   public static CodeModuleGroup load(int var0) {
      if (var0 == 0) {
         return null;
      }

      CodeModuleGroup var1 = new CodeModuleGroup(var0);
      return var1.load() ? var1 : null;
   }

   public static CodeModuleGroup[] loadAll() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void deleteEmptyGroups() {
      assertPermission();
      CodeModuleGroup[] var0 = loadAll();
      if (var0 != null) {
         int var1 = var0.length;
         CodeModuleGroup var2 = null;
         Enumeration var3 = null;
         Object var4 = null;

         for (int var5 = 0; var5 < var1; var5++) {
            var2 = var0[var5];
            var3 = var2.getModules();
            boolean var6 = false;

            while (var3.hasMoreElements()) {
               var4 = var3.nextElement();
               if (CodeModuleManager.getModuleHandle((String)var4) != 0) {
                  var6 = true;
                  break;
               }
            }

            if (!var6) {
               var2.delete();
            }
         }
      }
   }

   public static native int getNumberOfGroups();

   public static native byte[] getGroupData(int var0);

   public static int createGroup(byte[] var0) {
      assertPermission();
      return createGroupImpl(var0);
   }

   private static native int createGroupImpl(byte[] var0);

   private static native int[] getGroupHandles();

   private static native int getGroupHandle(String var0);
}
