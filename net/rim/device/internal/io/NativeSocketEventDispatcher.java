package net.rim.device.internal.io;

import net.rim.device.cldc.io.daemon.ProtocolDaemon;
import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

public final class NativeSocketEventDispatcher extends EventDispatcher {
   private int _protocolDaemonPid;
   private static final int JVM_DEVICE_SOCKET;
   private static final int SOC_CONNECT_CNF;
   private static final int SOC_CLOSE_CNF;
   private static final int SOC_CONNECT_IND;
   private static final int SOC_SEND_CNF;
   private static final int SOC_RECEIVE_IND;
   private static final int SOC_DISCONNECT_IND;
   private static final int SOC_READY_IND;

   private NativeSocketEventDispatcher(int var1) {
      this._protocolDaemonPid = var1;
   }

   @Override
   public final int getNotifyProcessId(Message var1) {
      return this._protocolDaemonPid;
   }

   public static final void addListener(Object var0) {
      ProtocolDaemon.getInstance().addListener(39, var0);
   }

   public static final void removeListener(Object var0) {
      ProtocolDaemon.getInstance().removeListener(39, var0);
   }

   public static final void register(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void dispatch(Message var1, Object var2) {
      throw new RuntimeException("cod2jar: type check");
   }
}
