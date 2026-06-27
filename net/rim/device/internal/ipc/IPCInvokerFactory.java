package net.rim.device.internal.ipc;

import net.rim.device.api.system.Application;

public class IPCInvokerFactory {
   private IPCInvokerFactory() {
   }

   public static IPCInvoker getIPCInvoker(Object var0) {
      return getIPCInvoker(null, var0, null);
   }

   public static IPCInvoker getIPCInvoker(Application var0, Object var1) {
      return getIPCInvoker(var0, var1, null);
   }

   public static IPCInvoker getIPCInvoker(Object var0, IPCRunnable var1) {
      return getIPCInvoker(null, var0, var1);
   }

   public static IPCInvoker getIPCInvoker(Application var0, Object var1, IPCRunnable var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static IPCInvoker getStrongIPCInvoker(Object var0) {
      return getStrongIPCInvoker(null, var0, null);
   }

   public static IPCInvoker getStrongIPCInvoker(Application var0, Object var1) {
      return getStrongIPCInvoker(var0, var1, null);
   }

   public static IPCInvoker getStrongIPCInvoker(Object var0, IPCRunnable var1) {
      return getStrongIPCInvoker(null, var0, var1);
   }

   public static IPCInvoker getStrongIPCInvoker(Application var0, Object var1, IPCRunnable var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
