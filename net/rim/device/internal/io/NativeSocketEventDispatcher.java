package net.rim.device.internal.io;

import net.rim.device.cldc.io.daemon.ProtocolDaemon;
import net.rim.device.internal.system.EventDispatchManager;
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

   private NativeSocketEventDispatcher(int protocolDaemonPid) {
      this._protocolDaemonPid = protocolDaemonPid;
   }

   @Override
   public final int getNotifyProcessId(Message message) {
      return this._protocolDaemonPid;
   }

   public static final void addListener(Object obj) {
      ProtocolDaemon.getInstance().addListener(39, obj);
   }

   public static final void removeListener(Object obj) {
      ProtocolDaemon.getInstance().removeListener(39, obj);
   }

   public static final void register(int protocolDaemonPid) {
      if (NativeSocket.isMultiRATSupported()) {
         EventDispatchManager dispatchManager = EventDispatchManager.getInstance();
         synchronized (dispatchManager) {
            if (dispatchManager.getDispatcher(39) == null) {
               dispatchManager.setDispatcher(39, new NativeSocketEventDispatcher(protocolDaemonPid));
            }
         }
      }
   }

   @Override
   public final void dispatch(Message message, Object listener) {
      throw new RuntimeException("cod2jar: type check");
   }
}
