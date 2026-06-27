package net.rim.device.internal.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.io.Connection;
import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.internal.firewall.FirewallContext;
import net.rim.device.internal.midlet.MIDPSupport;

public class RIMConnector {
   private RIMConnector() {
   }

   public static Connection open(int var0, String var1) {
      return open(var0, var1, 3, false, null);
   }

   public static Connection open(int var0, String var1, int var2, boolean var3) {
      return open(var0, var1, var2, var3, null);
   }

   public static Connection open(int var0, String var1, int var2, boolean var3, int[] var4, ApplicationDescriptor var5) {
      FirewallContext var6 = null;
      if (var4 != null || var5 != null) {
         var6 = new FirewallContext();
         var6.setAdditionalModules(var4);
         var6.setRequestingDescriptor(var5);
      }

      return open(var0, var1, var2, var3, var6);
   }

   private static Connection open(int var0, String var1, int var2, boolean var3, FirewallContext var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void doFirewallCheck(int var0, String var1, int[] var2, ApplicationDescriptor var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static DataInputStream openDataInputStream(int var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static DataOutputStream openDataOutputStream(int var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static void checkConnectionSupported(String var0) {
      if (MIDPSupport.connectionNotSupported(var0)) {
         throw new Object();
      }
   }
}
