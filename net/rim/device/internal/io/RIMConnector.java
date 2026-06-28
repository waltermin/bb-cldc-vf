package net.rim.device.internal.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.io.Connection;
import javax.microedition.io.InputConnection;
import javax.microedition.io.OutputConnection;
import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.internal.firewall.FirewallContext;
import net.rim.device.internal.midlet.MIDPSupport;

public class RIMConnector {
   private RIMConnector() {
   }

   public static Connection open(int moduleHandle, String name) {
      return open(moduleHandle, name, 3, false, null);
   }

   public static Connection open(int moduleHandle, String name, int mode, boolean timeouts) {
      return open(moduleHandle, name, mode, timeouts, null);
   }

   public static Connection open(int moduleHandle, String name, int mode, boolean timeouts, int[] additionalModules, ApplicationDescriptor requestingDescriptor) {
      FirewallContext context = null;
      if (additionalModules != null || requestingDescriptor != null) {
         context = new FirewallContext();
         context.setAdditionalModules(additionalModules);
         context.setRequestingDescriptor(requestingDescriptor);
      }

      return open(moduleHandle, name, mode, timeouts, context);
   }

   private static Connection open(int moduleHandle, String name, int mode, boolean timeouts, FirewallContext context) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void doFirewallCheck(int moduleHandle, String name, int[] additionalModules, ApplicationDescriptor requestingDescriptor) {
      throw new RuntimeException("cod2jar: ldc");
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static DataInputStream openDataInputStream(int moduleHandle, String name) {
      InputConnection con = (InputConnection)open(moduleHandle, name, 1, false, null);
      boolean var4 = false /* VF: Semaphore variable */;

      DataInputStream var3;
      try {
         var4 = true;
         var3 = con.openDataInputStream();
         var4 = false;
      } finally {
         if (var4) {
            con.close();
         }
      }

      con.close();
      return var3;
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static DataOutputStream openDataOutputStream(int moduleHandle, String name) {
      OutputConnection con = (OutputConnection)open(moduleHandle, name, 2, false, null);
      boolean var4 = false /* VF: Semaphore variable */;

      DataOutputStream var3;
      try {
         var4 = true;
         var3 = con.openDataOutputStream();
         var4 = false;
      } finally {
         if (var4) {
            con.close();
         }
      }

      con.close();
      return var3;
   }

   private static void checkConnectionSupported(String name) {
      if (MIDPSupport.connectionNotSupported(name)) {
         throw new Object();
      }
   }
}
