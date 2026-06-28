package javax.microedition.io;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.internal.firewall.Firewall;
import net.rim.device.internal.io.PushRegistryHelper;
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

   public static String getFilter(String connection) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static String getMIDlet(String connection) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static String[] listConnections(boolean available) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static long registerAlarm(String midlet, long time) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static void checkPermission(int moduleHandle, String permission, String url) {
      MIDletApplication a = (MIDletApplication)Application.getApplication();
      if (!a.hasEventThread()) {
         PushRegistry$PermissionCheckRunnable pcr = new PushRegistry$PermissionCheckRunnable(moduleHandle, permission, url);
         checkPermissionLater(a, pcr);
         if (pcr._failed) {
            throw pcr._re;
         }
      } else {
         checkPermissionPrimitive(moduleHandle, permission, url);
      }
   }

   private static void checkAlarmPermission() {
      MIDletApplication a = (MIDletApplication)Application.getApplication();
      if (!a.hasEventThread()) {
         PushRegistry$AlarmPermissionCheckRunnable apcr = new PushRegistry$AlarmPermissionCheckRunnable(null);
         checkPermissionLater(a, apcr);
         if (apcr._failed) {
            throw apcr._re;
         }
      } else {
         MIDletSecurity.checkPermission(8);
      }
   }

   private static void checkPermissionLater(MIDletApplication app, Runnable permChecker) {
      app.invokeLater(permChecker);
      app.setForegroundable(false);

      try {
         app.enterEventDispatcher();
      } catch (PushRegistry$PushRegistryPermissionCheckExitEvent var3) {
      }
   }

   private static void checkPermissionPrimitive(int moduleHandle, String permission, String url) {
      if (!ControlledAccess.verifyRRISignatures(true) && !Firewall.getInstance().allowConnection(permission, url, false)) {
         throw new SecurityException("Permission denied");
      }
   }

   private static boolean isMidletInSuite(String midlet) {
      StringBuffer sb = new StringBuffer("MIDlet-n");
      int charindex = sb.length() - 1;
      int i = 1;

      while (true) {
         sb.setCharAt(charindex, (char)(i + 48));
         String s = PushRegistryHelper.getMidletProperty(sb.toString());
         if (s == null) {
            return false;
         }

         if (s.indexOf(midlet) != -1) {
            return true;
         }

         i++;
      }
   }

   private static void launchMidlet(String midletClassName, String[] args, boolean grabForeground) {
      MIDletApplication ma = (MIDletApplication)Application.getApplication();
      ma.bringToForeground();
   }

   private static int getModuleHandleForMidletClass(String midletname) {
      return isMidletInSuite(midletname) ? Process.currentProcess().getModuleHandle() : -1;
   }

   public static void registerConnection(String connection, String midlet, String filter) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static boolean unregisterConnection(String connection) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static boolean removeStaleConnection(String connection) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static void removeStaleEntries() {
      throw new RuntimeException("cod2jar: field: unresolved slot");
   }
}
