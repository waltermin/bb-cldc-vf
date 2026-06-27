package javax.microedition.io;

import net.rim.device.api.system.Application;
import net.rim.device.internal.system.MIDletSecurity;
import net.rim.device.internal.ui.MIDletApplication;
import net.rim.vm.Process;

public class PushRegistry {
   private static final String MIDLET_NAME;
   private static final long MAX_DELAY;
   private static String PUSHREGISTRY_PERMISSION_TOKEN;
   private static String SMS_PERMISSION_TOKEN;

   private PushRegistry() {
   }

   public static String getFilter(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static String getMIDlet(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static String[] listConnections(boolean var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static long registerAlarm(String var0, long var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static void checkPermission(int var0, String var1, String var2) {
      Object var3 = Application.getApplication();
      if (!((MIDletApplication)var3).hasEventThread()) {
         PushRegistry$PermissionCheckRunnable var4 = new PushRegistry$PermissionCheckRunnable(var0, var1, var2);
         checkPermissionLater((MIDletApplication)var3, var4);
         if (var4._failed) {
            throw var4._re;
         }
      } else {
         checkPermissionPrimitive(var0, var1, var2);
      }
   }

   private static void checkAlarmPermission() {
      Object var0 = Application.getApplication();
      if (!((MIDletApplication)var0).hasEventThread()) {
         PushRegistry$AlarmPermissionCheckRunnable var1 = new PushRegistry$AlarmPermissionCheckRunnable(null);
         checkPermissionLater((MIDletApplication)var0, var1);
         if (var1._failed) {
            throw var1._re;
         }
      } else {
         MIDletSecurity.checkPermission(8);
      }
   }

   private static void checkPermissionLater(MIDletApplication var0, Runnable var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static void checkPermissionPrimitive(int var0, String var1, String var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static boolean isMidletInSuite(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static void launchMidlet(String var0, String[] var1, boolean var2) {
      Object var3 = Application.getApplication();
      ((MIDletApplication)var3).bringToForeground();
   }

   private static int getModuleHandleForMidletClass(String var0) {
      return isMidletInSuite(var0) ? Process.currentProcess().getModuleHandle() : -1;
   }

   public static void registerConnection(String var0, String var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static boolean unregisterConnection(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static boolean removeStaleConnection(String var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static void removeStaleEntries() {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }
}
