package net.rim.device.internal.ipc;

import net.rim.device.api.system.Application;

class IPCInvokerStrong extends IPCInvoker {
   private Object _listener;

   IPCInvokerStrong(Application var1, Object var2) {
      this(var1, var2, null);
   }

   IPCInvokerStrong(Application var1, Object var2, IPCRunnable var3) {
      super(var1, var3);
      this._listener = var2;
   }

   @Override
   public Object getListener() {
      return this._listener;
   }
}
