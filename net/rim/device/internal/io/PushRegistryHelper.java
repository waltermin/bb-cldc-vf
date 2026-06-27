package net.rim.device.internal.io;

import java.util.Hashtable;
import javax.microedition.io.Connection;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.resources.Resource;
import net.rim.device.resources.Resource$Internal;
import net.rim.vm.Process;

public class PushRegistryHelper {
   private Hashtable _pushRegsitryConnections = (Hashtable)(new Object());
   public Hashtable _weakreferencemap = (Hashtable)(new Object());
   public Hashtable _filterMap = (Hashtable)(new Object());
   public Hashtable _connectionMap = (Hashtable)(new Object());
   public Hashtable _alarmMap = (Hashtable)(new Object());
   private static final long ID;
   public static String APPLICATION_DESCRIPTOR_NAME_PUSH_REGISTRY_WORK;
   public static String APPLICATION_DESCRIPTOR_ARG_PUSH_REGISTRY_WORK;
   public static String APPLICATION_DESCRIPTOR_ARG_DYNAMIC_PUSH_REGISTRY_WORK;
   public static String APPLICATION_DESCRIPTOR_ARG_SHUTDOWN_PUSH_REGISTRY_WORK;
   public static final String MIDLET_NAME;
   public static String MIDLET_PUSH_PROPERTY_NAME_PREFIX;
   public static String MIDLET_PROPERTY_NAME_PREFIX;
   private static final String[] PUSH_TRANSPORTS;
   private static final String[] PUSH_TRANSPORT_PERMISSIONS;

   public static PushRegistryHelper getInstance() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static String getCallingMidletName() {
      String var0 = null;
      if (CodeModuleManager.isMidlet()) {
         var0 = Process.currentProcess().getModuleName();
      }

      return var0;
   }

   public static String getMidletProperty(String var0) {
      Resource var1 = Resource$Internal.getResourceClass(Process.currentProcess().getModuleName());
      if (var1 != null) {
         byte[] var2 = var1.getProperty(var0);
         if (var2 != null) {
            return (String)(new Object(var2, 2, var2.length - 2));
         }
      }

      return null;
   }

   private PushRegistryHelper() {
   }

   public void put(String var1, Connection var2) {
      this._pushRegsitryConnections.put(var1, var2);
   }

   public Connection get(String var1) {
      return (Connection)this._pushRegsitryConnections.get(var1);
   }

   public Connection remove(String var1) {
      return (Connection)this._pushRegsitryConnections.remove(var1);
   }

   public static String[] getPushPropertyValues(String var0) {
      String var1 = var0;
      int var2 = var1.indexOf(44, 0);
      String var3 = var1.substring(0, var2);
      var3 = var3.trim();
      int var4 = var2 + 1;
      var2 = var1.indexOf(44, var4);
      String var5 = var1.substring(var4, var2);
      var5 = var5.trim();
      var4 = var2 + 1;
      String var6 = var1.substring(var4);
      var6 = var6.trim();
      return new String[]{var3, var5, var6};
   }

   public static boolean isConnectionSupported(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static boolean isPushTransportPermissionRequested(String var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static Connection checkConnection(String var0) {
      throw new RuntimeException("cod2jar: type check");
   }
}
